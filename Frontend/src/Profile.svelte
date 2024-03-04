<script>
  import {
    applications,
    fetchUserPrefs,
    deleteUserPref,
    addUserPref,
    prefs,
    uploadCv,
    getCvForUser,
  } from "./db.js";
  import Button from "./Button.svelte";
  import { getCookie } from "./Helpers.svelte";
  import { onMount } from "svelte";

  function removeApplication(id) {
    applications.update((apps) => apps.filter((app) => app !== id));
  }

  let pref = "";
  let showPreferences = false;
  let showMessage = false;
  let showUploadMessage = false;

  async function addPreference() {
    addUserPref(pref);
    pref = "";
    showMessage = true;
    setTimeout(() => {
      showMessage = false;
    }, 1000);
  }

  async function togglePreferences() {
    showPreferences = !showPreferences;
    if (showPreferences) {
      await fetchUserPrefs();
    }
  }

  // let yes = false;

  async function handlePressingEnter(e) {
    if (e.key === "Enter") {
      await addPreference();
    }
  }

  let file = null;

  function handleCvChange(e) {
    file = e.target.files[0];
  }
  async function handleCvUpload() {
    if (!file) {
      console.log("please select a file");
      return;
    }

    try {
      await uploadCv(file);
      console.log("cv sent success");
      showUploadMessage = true;
      await loadCV();
      setTimeout(() => {
        showUploadMessage = false;
      }, 1000);
    } catch (error) {
      console.error("errro sending cv:", error);
    }
  }

  let cvUrl = null;
  async function loadCV() {
    cvUrl = await getCvForUser(getCookie("userIdForSession"));
  }

  onMount(loadCV);

  function openCV() {
    if (cvUrl) {
      window.open(cvUrl, "_blank");
    }
  }
</script>

<div class="container">
  <div class="column">
    <h2>Jobs I applied to:</h2>
    <ul>
      {#each $applications as jobId}
        <div>
          Job ID: {jobId}
          <button id="removeButton" on:click={() => removeApplication(jobId)}
            >Remove</button
          >
        </div>
      {/each}
    </ul>
  </div>

  <div class="column">
    <h2>My preferences</h2>
    <div>
      <input
        type="text"
        bind:value={pref}
        placeholder="Enter Preference"
        on:keydown={handlePressingEnter}
      />
      <button id="addButton" on:click={addPreference}>Add</button>
    </div>

    <button class="toggleButton" on:click={togglePreferences}>
      {#if showPreferences}
        Hide
      {:else}
        Show
      {/if}
    </button>

    {#if showMessage}
      <p class="message">Added succesfully</p>
    {/if}

    {#if showPreferences}
      <div>
        <ul>
          {#each $prefs as pref, i}
            <li>
              {pref}
              <button id="removeButton2" on:click={() => deleteUserPref(pref)}
                >Remove</button
              >
            </li>
          {/each}
        </ul>
      </div>
    {/if}
    <!-- <br/><br/>
    <label>-->
    <!-- https://svelte.dev/examples/checkbox-inputs -->
    <!-- <input type="email" placeholder="Enter email" /><br />
      <input type="checkbox" bind:checked={yes} />
      Yes, send me an email update!
    </label>

    {#if yes}
      <p>Thank you. We will keep you updated!</p>
    {:else}
      <p>Check the checkbox to confirm.</p>
    {/if}

    <button class="toggleButton" disabled={!yes}> Subscribe </button> -->
  </div>

  <div class="column">
    {#if showUploadMessage}
      <p class="message">CV uploaded!</p>
    {/if}
    <h2>My resume:</h2>
    <label for="avatar">Upload my CV:</label>
    <input type="file" on:change={handleCvChange} />
    <Button on:click={handleCvUpload}>Upload</Button>
    <div>
      {#if cvUrl}
        <Button on:click={openCV}>View my Cv</Button>
      {:else}
        <p>No Cv found ;_; ...</p>
      {/if}
    </div>
  </div>
</div>

<style>
  .container {
    display: flex;
    width: 100%;
  }

  .column {
    flex: 1;
    /* border: 3px solid black; */
    padding: 20px;
  }

  #removeButton {
    background-color: rgb(102, 21, 18);
    color: rgb(0, 0, 0);
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }

  #addButton {
    background-color: rgb(82, 153, 24);
    color: rgb(0, 0, 0);
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }

  #removeButton2 {
    background-color: rgb(184, 91, 91);
    color: rgb(0, 0, 0);
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }
  .toggleButton {
    background-color: rgb(49, 41, 41);
    color: white;
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }

  .toggleButton:disabled {
    background-color: gray;
    cursor: not-allowed;
  }

  h2 {
    color: #6e3434;
  }

  .message {
    color: green;
  }
</style>
