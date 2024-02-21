<script>
  import { applications } from "./db.js";
  function removeApplication(id) {
    applications.update((apps) => apps.filter((app) => app !== id));
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
  <div class="column"></div>
  <div class="column"></div>
</div>

<style>
  .container {
    display: flex;
    width: 100%;
  }

  .column {
    flex: 1;
    border: 3px solid black;
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

  h2 {
    color: #6e3434;
  }
</style>
