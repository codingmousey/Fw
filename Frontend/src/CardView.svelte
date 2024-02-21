<script>
  import Button from "./Button.svelte";
  import lst from "./db.js";
</script>

<div class="div">
  {#each $lst as i (i.id)}
    <!-- svelte-ignore a11y-click-events-have-key-events -->
    <div class="card" on:click={ () => console.log('div clicked with id: ' + i.id)}>
      <div class="detail">
        <h3>{i.jobtitle}</h3>
        <p>{i.company} in {i.address}</p>
        <div>
          <!-- The tags are shown as buttons that can be clicked to select them as filters -->
          {#each i.tags as tag}
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
