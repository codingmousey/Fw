<!-- Scripts -->
<script>
  import Button from "./Button.svelte";
  import { jobListings } from "./db.js";
  import { filteredJobListings } from "./db.js";
  import { derived } from "svelte/store";
  import { prefs, fetchUserPrefs } from "./db.js";
  /* https://eternaldev.com/blog/introduction-to-svelte-derived-store */
  /* https://stackoverflow.com/questions/1960473/get-all-unique-values-in-a-javascript-array-remove-duplicates */

  const languages = derived(jobListings, ($jobListings) => {
    const languages = $jobListings.map((i) => i.programmingLanguages).flat();
    console.log("languages: " + languages);
    return [...new Set(languages)];
  });

  const locations = derived(jobListings, ($jobListings) => {
    const locations = $jobListings.map((i) => i.city).flat();
    console.log("locations: " + locations);
    return [...new Set(locations)];
  });

  let language = "";
  let location = "";

  function handleSearch() {
    console.log(
      "search button clicked with chosen language: " +
        language +
        " and chosen location: " +
        location
    );
    const filteredJobs = $jobListings.filter((i) => {
      /* https://d7k.medium.com/js-includes-vs-some-b3cd546a7bc3 */
      const filterLanguage =
        !language ||
        i.programmingLanguages.some((tag) =>
          tag.toLowerCase().includes(language.toLowerCase())
        );
      const filterLocation =
        !location || i.city.toLowerCase().includes(location.toLowerCase());
      return filterLanguage && filterLocation;
    });
    console.log("filtered jobs:", JSON.stringify(filteredJobs, null, 2));
    filteredJobListings.set(filteredJobs);
  }

  
  function calculateMatches(job) {
    let matchCount = 0;

    for (const language of job.programmingLanguages) {
      if ($prefs.includes(language.toLowerCase())) {
        matchCount++;
      }
    }

    for (const pref of $prefs) {
      if (job.city.toLowerCase().includes(pref.toLowerCase())) {
        matchCount++;
        break;
      }
    }
    return matchCount;
  }

  async function handleLoadPreference() {
    console.log("loading preferences and triggering search");
    await fetchUserPrefs();
    const filteredJobs = $jobListings
      .filter((job) => calculateMatches(job) > 0)
      .sort((a, b) => calculateMatches(b) - calculateMatches(a));

    console.log("filtered jobs:", JSON.stringify(filteredJobs, null, 2));
    filteredJobListings.set(filteredJobs);
  }
</script>

<!-- HTML -->
<header>
  <div class="left">
    <h1>All junior jobs in one place</h1>
    <h3>
      Find your first job or look around where you can find your dream job! Load
      up your <Button on:click={handleLoadPreference}>preferences</Button> OR search:
    </h3>
  </div>

  <form class="right" on:submit|preventDefault>
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

  .right {
    display: flex;
    align-items: center;
  }

  .form-field {
    margin-right: 10px;
  }
</style>
