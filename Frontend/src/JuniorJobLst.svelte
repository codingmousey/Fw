<!-- Scripts -->
<script>
  import Button from "./Button.svelte";
  import JuniorJobItemCard from "./JuniorJobItemCard.svelte";
  export let data = [];
</script>

<!-- HTML -->
<!--
    After receiving the 'for the moment' hardcoded data from App.svelte, each lst item is
    read and displayed here
-->
<div class="div">
  {#each data as i (i.id)}
    <JuniorJobItemCard>
      <div class="detail">
        <h3>{i.jobtitle}</h3>
        <p>{i.company} in {i.addres}</p>
        <div>
          <!-- The tags are showed as buttons that can be clicked to select it as filter -->
          {#each i.tags as tag}
            <Button>{tag}</Button>
          {/each}
        </div>
      </div>
    </JuniorJobItemCard>
  {/each}
</div>

<!-- Css -->
<style>
  .div {
    display: grid;
    grid-template-columns: 1fr 1fr;
    flex: 1;
    grid-gap: 20px;
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
