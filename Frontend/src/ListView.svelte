<script>
  import {
    filteredJobListings,
    applications,
    fetchJobApplications,
  } from "./db.js";
  import { onMount } from "svelte";
  import { createEventDispatcher } from "svelte";

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

<div class="lst">
  {#each $filteredJobListings as i (i.id)}
    <!-- svelte-ignore a11y-click-events-have-key-events -->
    <article on:click={() => handleDivClick(i)}>
      <!-- svelte-ignore a11y-missing-attribute -->
      <h2>
        <span>{i.name}</span>
        {#if appliedJobIds.includes(i.id)}
          <span class="applied">(Applied)</span>
        {/if}
      </h2>

      <div class="details">
        <p class="nameNCity">{i.companyName} in {i.city}</p>
        <p class="date">
          {new Date(i.createdDate).toLocaleString("en-GB", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: false,
          })}
        </p>
      </div>
    </article>
  {/each}
</div>

<style>
  article {
    position: relative;
    padding: 0 2em;
    border-bottom: 1px solid #330303;
  }

  h2 {
    font-size: 1em;
    margin: 0.5em 0;
  }

  span {
    left: 0;
  }

  .lst {
    position: relative;
    max-width: 800px;
    margin: 0 auto;
    flex: 1;
  }

  .details {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .date {
    color: rgba(61, 28, 0, 0.815) !important;
    text-align: right;
    font-size: 12;
    font-weight: bold;
  }

  .nameNCity {
    color: rgba(110, 1, 1, 0.815) !important;
    text-align: left;
    font-size: 16;
    font-weight: bold;
  }
  .applied {
    text-align: right;
    color: green;
  }

  article:hover {
    background-color: #a0bdba;
  }
</style>
