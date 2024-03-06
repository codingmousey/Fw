<script>
  import { createEventDispatcher } from "svelte";
  import Button from "./Button.svelte";
  import { fetchJobListings } from './db.js';
  import { getCookie } from "./Helpers.svelte";

  let dispatch = createEventDispatcher();

  let jobListing = {
    companyName:"",
    url: "",
    internExtern: false,
    name: "",
    description: "",
    programmingLanguages: "",
    city: "",
    country: "Belgium",
    companyId: getCookie("userIdForSession"),
  };

  const customSubmitHandler = async () => {
    let programmingLanguages = jobListing.programmingLanguages
      .split(",")
      .map((lang) => lang.trim());

    let juniorJob = {
      ...jobListing,
      programmingLanguages,
    };

    console.log("add new job submitted");
    console.log("data sent to backend:", juniorJob);

    await fetch("http://localhost:6969/api/add_joblisting", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(juniorJob),
    });
    await fetchJobListings()
    dispatch("add");
  };
</script>

<form on:submit|preventDefault={customSubmitHandler}>
  <div class="form-field">
    <label for="description">Company:</label>
    <input type="text" id="company" bind:value={jobListing.companyName} />
  </div>
  <div class="form-field">
    <label for="name">Title:</label>
    <input type="text" id="name" bind:value={jobListing.name} />
  </div>
  <div class="form-field">
    <label for="description">Description:</label>
    <input type="text" id="description" bind:value={jobListing.description} />
  </div>
  <div class="form-field">
    <label for="programmingLanguages"
      >Programming Languages (separated by comma):</label
    >
    <input
      type="text"
      id="programmingLanguages"
      bind:value={jobListing.programmingLanguages}
    />
  </div>
  <div class="form-field">
    <label for="city">City:</label>
    <input type="text" id="city" bind:value={jobListing.city} />
  </div>
  <div class="form-field">
    <label for="country">Country:</label>
    <input type="text" id="country" bind:value={jobListing.country} />
  </div>
  <Button>Add Junior Job</Button>
</form>

<style>
  form {
    width: 400px;
    margin: 0 auto;
    text-align: center;
  }

  .form-field {
    margin: 18px auto;
  }

  input {
    width: 100%;
    border-radius: 6px;
  }

  label {
    margin: 10px auto;
    text-align: left;
  }
</style>
