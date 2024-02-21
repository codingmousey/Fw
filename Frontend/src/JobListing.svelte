<script>
  import lst from "./db.js";
</script>

<div class="lst">
  {#each $lst as job}
    <article>
      <span>{job.id}</span>
      <h2><a>{job.jobtitle}</a></h2>
      <p class="meta">by {job.company}</p>
    </article>
  {/each}
</div>

<style>
  article {
    position: relative;
    padding: 0 0 0 2em;
    border-bottom: 1px solid #330303;
  }

  a {
    color: #330303;
  }

  h2 {
    font-size: 1em;
    margin: 0.5em 0;
  }

  span {
    position: absolute;
    left: 0;
  }

  .lst {
    position: relative;
    max-width: 800px;
    margin: 0 auto;
    flex: 1;
  }
</style>
