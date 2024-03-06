<!-- Scripts -->
<script>
   export let color = '#6e3434';
</script>
<!-- HTML -->
<button on:click style="background: {color}">
  <slot />
</button>
<!-- Css -->
<style>
  button {
    border: 0;
    cursor: pointer;
    border-radius: 6px;
    padding: 8px 12px;
    font-weight: bold;
    box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.69);
    color: white;
    margin-right: 5px;
  }
</style>
