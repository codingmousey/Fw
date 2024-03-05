<script>
  import Button from "./Button.svelte";
  import {
    filteredJobListings,
    applications,
    fetchJobApplications,
  } from "./db.js";
  import { createEventDispatcher } from "svelte";
  import { onMount } from "svelte";
  const dispatch = createEventDispatcher();
  function handleDivClick(i) {
    console.log("div clicked with id: " + i.id);
    dispatch("getIdFromDivClick", i);
  }

  $: appliedJobIds = $applications.map(
    (application) => application.jobListing.id
  );

  onMount(() => {
    fetchJobApplications();
  });
</script>

<div class="div">
  {#each $filteredJobListings as i (i.id)}
    <!-- svelte-ignore a11y-click-events-have-key-events -->
    <div class="card" on:click={() => handleDivClick(i)}>
      <div class="detail">
        <h3>
          {i.name}
          {#if appliedJobIds.includes(i.id)}
            <span style="color: green; font-size: 15px;">(Applied)</span>
          {/if}
        </h3>
        <p>{i.companyName} in {i.city}</p>
        <p>{i.description} </p>
        <div>
          <!-- The tags are shown as buttons that can be clicked to select them as filters -->
          {#each i.programmingLanguages as tag}
            <Button>{tag}</Button>
          {/each}
        </div>
      </div>
    </div>
  {/each}
</div>

<style>
  .div {
    display: grid;
    grid-template-columns: 1fr 1fr;
    flex: 1;
    grid-gap: 20px;
  }

  .card {
    background: #ffffff;
    padding: 20px;
    border-radius: 6px;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  }

  .detail h3 {
    margin: 0 auto;
    color: black;
  }

  .detail p {
    margin-top: 6px;
    font-size: 14px;
    color: grey;
  }
</style>
