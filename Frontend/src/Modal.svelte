<!-- modal.svelte -->
<script>
    export let modalVisible = false;
    export let item;
    import {createEventDispatcher} from 'svelte';
    const dispatch = createEventDispatcher();

    $: console.log('modalVisible atm is: ' + modalVisible);

    function handleClose(){
      dispatch('closeModal');
    }
  </script>
  
  {#if modalVisible}
    <!-- svelte-ignore a11y-click-events-have-key-events -->
    <div class="blackout" on:click={handleClose}>
      <div class="modal">
        <pre>{JSON.stringify(item, null, 2)}</pre>
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
    }
  </style>
  