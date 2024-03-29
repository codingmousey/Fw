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

  function cutDescription(description) {
    const txt = description.split(" ");
    return txt.slice(0, 20).join(" ") + "...";
  }

  let sorted = [];

  $: sorted = $filteredJobListings.slice().sort((a, b) => {
    if (a.internExtern === b.internExtern) {
      return new Date(b.createdDate) - new Date(a.createdDate);
    } else {
      return a.internExtern ? 1 : -1;
    }
  });

  let currPage = 1;
  const rowsPerPage = 5;

  $: start = (currPage - 1) * rowsPerPage;
  $: end = Math.min(start + rowsPerPage, sorted.length);

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

<div class="div">
  <div class="pagination">
  <Button on:click={prevPage} disabled={currPage === 1}>&lt;&lt;</Button>
  <Button on:click={nextPage} disabled={end >= sorted.length}>&gt;&gt;</Button>
  <p>Showing {range} of {sorted.length} results</p>
</div>
  {#each sorted.slice(start, end) as i (i.id)}
    <!-- svelte-ignore a11y-click-events-have-key-events -->
    <div class="card" on:click={() => handleDivClick(i)}>
      <div class="detail">
        <h3>
          {i.name}
          {#if appliedJobIds.includes(i.id)}
            <span style="color: green; font-size: 15px;">(Applied)</span>
          {/if}
        </h3>
        <p class="nameNCity">{i.companyName} in {i.city}</p>
        <p>
          {cutDescription(i.description)}
          <span class="continue">continue reading</span>
        </p>
        <div>
          <!-- The tags are shown as buttons that can be clicked to select them as filters -->
          {#each i.programmingLanguages as tag}
            <Button>{tag}</Button>
          {/each}
        </div>
        <p class="date">
          {new Date(i.createdDate).toLocaleString("en-GB", {
            day: "2-digit",
            month: "2-digit",
            year: "numeric",
          })}
        </p>
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
    padding-bottom: 69px;
  }

  .card {
    background: #ffffff;
    padding: 20px;
    border-radius: 6px;
    box-shadow: 0px 2px 4px rgba(121, 42, 28, 0.486);
  }
  .detail {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    height: 100%;
  }

  .detail h3 {
    margin-top: 0px;
    color: rgb(7, 0, 14);
  }

  .detail p {
    margin-top: 6px;
    font-size: 14px;
    color: grey;
  }
  .date {
    color: rgba(61, 28, 0, 0.815) !important;
    text-align: right;
    font-size: 16;
    font-weight: bold;
  }

  .nameNCity {
    color: rgba(110, 1, 1, 0.815) !important;
    text-align: left;
    font-size: 16;
    font-weight: bold;
    display: flex;
    align-items: center;
  }
  .continue {
    display: block;
    color: rgb(14, 99, 47);
    cursor: pointer;
  }

  .pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    color: rgb(70, 11, 11);
    margin-bottom: 10px;
  }

  .div :hover {
    background-color: rgba(210, 212, 212, 0.795);
  }
</style>
