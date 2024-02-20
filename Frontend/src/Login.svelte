<script>
  import Button from "./Button.svelte";
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();
  let obj = {
    username: "",
    pw: ""
  };

  const customSubmitHandler = () => {
    console.log('login customSubmitHandler: ' + obj);
    const userInfo = { username: obj.username };
    console.log('userInfo obj: ' + userInfo);
    console.log('userInfo.username: ' + userInfo.username);
    dispatch('signIn', userInfo);
  };
</script>

<form on:submit|preventDefault={customSubmitHandler}>
  <div class="form-field">
    <label for="username">Username:</label>
    <input type="text" id="username" bind:value={obj.username} />
  </div>
  <div class="form-field">
    <label for="pw">Password:</label>
    <input type="password" id="pw" bind:value={obj.pw} />
  </div>

  <Button>Sign In</Button>
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
