<script>
  import {
    filteredJobListings,
    applications,
    fetchJobApplications,
  } from "./db.js";
  import { onMount } from "svelte";
  import { createEventDispatcher } from "svelte";
  import Button from "./Button.svelte";

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

  let sorted = [];

  $: sorted = $filteredJobListings.slice().sort((a, b) => {
    if (a.internExtern === b.internExtern) {
      return new Date(b.createdDate) - new Date(a.createdDate);
    } else {
      return a.internExtern ? 1 : -1;
    }
  });

  let currPage = 1;
  const rowsPPage = 5;

  $: start = (currPage - 1) * rowsPPage;
  $: end = Math.min(start + rowsPPage, sorted.length);

  function nextPage() {
    if (end < sorted.length) {
      currPage++;
    }
  }

  function prevPage() {
    if (currPage > 1) {
      currPage--;
    }
  }

  $: range = `${start + 1} - ${Math.min(end, sorted.length)}`;
</script>

<div class="lst">
  <div class="pagination">
    <Button on:click={prevPage} disabled={currPage === 1}>&lt;&lt;</Button>
    <Button on:click={nextPage} disabled={end >= sorted.length}>&gt;&gt;</Button
    >
  </div>
  <div class="pagination"><p>Showing {range} of {sorted.length} results</p></div>
  {#each sorted.slice(start, end) as i (i.id)}
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

  .pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    color: rgb(70, 11, 11);
    margin-bottom: 10px;
  }
</style>
