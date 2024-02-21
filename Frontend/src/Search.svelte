<!-- Scripts -->
<script>
  import Button from "./Button.svelte";
  import lst from "./db.js";
  import { derived } from "svelte/store";
  /* https://eternaldev.com/blog/introduction-to-svelte-derived-store */
  /* https://stackoverflow.com/questions/1960473/get-all-unique-values-in-a-javascript-array-remove-duplicates */

  const languages = derived(lst, ($lst) => {
    const tags = $lst.map((i) => i.tags).flat();
    return [...new Set(tags)];
  });

  const locations = derived(lst, ($lst) => {
    return [...new Set($lst.map((i) => i.addres))];
  });
  let language = "";
  let location = "";

  let filteredJobs = [];
  function handleSearch() {
    console.log("search button clicked");
    console.log(language);
    console.log(location);
    filteredJobs = $lst.filter((i) => {
      /* https://d7k.medium.com/js-includes-vs-some-b3cd546a7bc3 */
      const filterLanguage = !language || i.tags.some(tag => tag.toLowerCase().includes(language.toLowerCase()));
      const filterLocation = !location || i.addres.toLowerCase().includes(location.toLowerCase());
      return filterLanguage && filterLocation;
    });
    console.log("filtered jobs:", filteredJobs);
    alert(JSON.stringify(filteredJobs, null, 2));

  }
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
      <input
        type="text"
        bind:value={language}
        id="language"
        list="languages"
        placeholder="Language"
      />
      <datalist id="languages">
        {#each $languages as potentialLanguage}
          <option value={potentialLanguage} />
        {/each}
      </datalist>
    </div>
    <div class="form-field">
      <input
        type="text"
        bind:value={location}
        id="location"
        list="locations"
        placeholder="Location"
      />
      <datalist id="locations">
        {#each $locations as potentialLocation}
          <option value={potentialLocation} />
        {/each}
      </datalist>
    </div>
    <Button on:click={handleSearch}>Search</Button>
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
