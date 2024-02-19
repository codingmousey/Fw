<script>
  import { createEventDispatcher } from "svelte";
  import Button from "./Button.svelte";
  import lst from './db.js';

  let dispatch = createEventDispatcher();

  let obj = {
    company: "",
    jobtitle: "",
    addres: "",
    tags: [],
  };

  const customSubmitHandler = () => {
    let lst_tags = obj.tags.split(",").map((tag) => tag.trim());
    console.log("obj");
    let juniorJob = { ...obj, id: Math.random(), tags: lst_tags };
    lst.update(conData => {
      return [juniorJob, ...conData];
    })
    dispatch("add");
  };
</script>

<form on:submit|preventDefault={customSubmitHandler}>
  <div class="form-field">
    <label for="company">Company:</label>
    <input type="text" id="company" bind:value={obj.company} />
  </div>
  <div class="form-field">
    <label for="jobtitle">Jobtitle:</label>
    <input type="text" id="jobtitle" bind:value={obj.jobtitle} />
  </div>
  <div class="form-field">
    <label for="addres">Addres:</label>
    <input type="text" id="addres" bind:value={obj.addres} />
  </div>
  <div class="form-field">
    <label for="tags">Tags (separate by comma):</label>
    <input type="text" id="tags" bind:value={obj.tags} />
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
