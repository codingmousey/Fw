<!-- modal.svelte -->
<script>
  export let modalVisible = false;
  export let item;
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();

  $: console.log("modalVisible atm is: " + modalVisible);

  function handleClose() {
    dispatch("closeModal");
  }
  import { applications } from "./db.js";
  function handleApply() {
    console.log(`saved job with id: ${item.id} to your applications`);
    applications.update((apps) => [...apps, item.id]);
    alert(`Successfully sent your CV to ${item.company}!`);
  }
</script>

{#if modalVisible}
  <!-- svelte-ignore a11y-click-events-have-key-events -->
  <div class="blackout" on:click|self={handleClose}>
    <div class="modal">
      <button id="closeButton" on:click|self={handleClose}>X</button>
      <pre>{JSON.stringify(item, null, 2)}</pre>
      <button id="applyButton" on:click={handleApply}>Apply for this job</button
      >
    </div>
  </div>
{/if}

<style>
  .blackout {
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.8);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000; /* stackoverflow.com/questions/2305669/all-about-z-index */
  }

  .modal {
    color: white;
    padding: 10px;
    border-radius: 10px;
    max-width: 400px;
    margin: 10% auto;
    text-align: center;
    background: #6e3434;
    z-index: 1001; /* stackoverflow.com/questions/2305669/all-about-z-index */
    display: flex;
    flex-direction: column;
  }

  #closeButton {
    align-self: flex-end;
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.69);
    cursor: pointer;
  }
  #applyButton {
    background-color: rgb(147, 180, 139);
    color: rgb(0, 0, 0);
    padding: 5px 10px;
    border: none;
    border-radius: 4;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }
</style>
