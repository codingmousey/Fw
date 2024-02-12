<!-- Scripts -->
<script>
  import Button from "./Button.svelte";
</script>

<!-- HTML -->
<header>
  <div>
    <h1>All junior jobs in one place</h1>
    <h3>
      Find your first job or look around where you can find your dream job!
    </h3>
  </div>

  <form class="div" on:submit|preventDefault>
    <div class="form-field">
      <input type="text" id="language" placeholder="Language" />
    </div>
    <div class="form-field">
      <input type="text" id="location" placeholder="Location" />
    </div>
    <Button>Search</Button>
  </form>
</header>

<!-- CSS -->
<style>
  header {
    justify-content: space-between;
    align-items: flex-end;
    padding: 0 10%;
    display: flex;
    grid-template-columns: 1fr 1fr;
  }

  .div {
    display: flex;
    align-items: center;
  }

  .form-field {
    margin-right: 10px;
  }
</style>
