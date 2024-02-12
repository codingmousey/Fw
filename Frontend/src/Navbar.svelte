<!-- Scripts -->
<script>
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();
  export let arr;
  export let current_item;
</script>

<!-- HTML -->
<!--
    We take the array forwarded from Header.svelte containing all the navbar items 
    and go though them while also checking which the current navbar item is to highlight it
    Doing this by creating a custom event that sends data back whenever the li item is clicked
-->
<div class="arr">
  <ul>
    {#each arr as i}
      <!-- svelte-ignore a11y-click-events-have-key-events -->
      <li on:click={() => dispatch("customEvent_clickNavItem", i)}>
        <div class:current={i === current_item}>
          {i}
        </div>
      </li>
    {/each}
  </ul>
</div>

<!-- Css -->
<style>
  ul {
    display: flex;
    justify-content: center;
    padding: 0;
    list-style-type: none;
  }

  li {
    margin: 10px;
    font-size: 18px;
    color: #000000;
    cursor: pointer;
  }

  li:hover {
    transform: translateY(-3px);
  }

  .current {
    color: #ff0033;
    border-bottom: 3px solid #ff0033;
  }
</style>
