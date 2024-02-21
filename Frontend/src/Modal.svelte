<!-- modal.svelte -->
<script>
    let modalVisible = false;
    export let msg;

  </script>
  
  {#if modalVisible}
    <div class="blackout">
      <div class="modal">
        <p>{msg}</p>
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
      z-index: 1000;
    }
  
    .modal {
      padding: 10px;
      border-radius: 10px;
      max-width: 400px;
      margin: 10% auto;
      text-align: center;
      background: rgb(100, 48, 48);
      z-index: 1001;
      /* Add other modal styles as needed */
    }
  </style>
  