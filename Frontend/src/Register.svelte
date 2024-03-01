<script>
  import Button from "./Button.svelte";
  import { createEventDispatcher } from "svelte";

  const dispatch = createEventDispatcher();

  let obj = {
    firstName: "",
    lastName: "",
    email: "",
    pw: "",
    pwCheck: "",
    error: "",
  };

  const registerHandler = async () => {
    console.log("register button clicked");

    try {
      const response = await fetch("http://localhost:6969/api/users/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(obj),
      });

      if (response) {
        const res = await response.json();
        if (res.error) {
          obj.error = res.error;
          console.log("there are some erros: " + res.error);
        } else {
          console.log("registration success:", res);
          obj.firstName = "";
          obj.lastName = "",
          obj.email = "";
          obj.email = "";
          obj.pwCheck = "";
          obj.error = "";
          dispatch("registerSuccess");
        }
      }
    } catch (error) {
      console.error("error registering:", error.message);
    }
  };
</script>

<form on:submit|preventDefault={registerHandler}>
  <div class="error">{obj.error}</div>
  <div class="form-field">
    <label for="username">First name:</label>
    <input type="text" id="firstName" bind:value={obj.firstName} />
  </div>
  <div class="form-field">
    <label for="username">Last name:</label>
    <input type="text" id="lastName" bind:value={obj.lastName} />
  </div>
  <div class="form-field">
    <label for="username">Email:</label>
    <input type="text" id="email" bind:value={obj.email} />
  </div>
  <div class="form-field">
    <label for="pw">Password:</label>
    <input type="password" id="pw" bind:value={obj.pw} />
  </div>
  <div class="form-field">
    <label for="pwCheck">Confirm password:</label>
    <input type="password" id="pwCheck" bind:value={obj.pwCheck} />
  </div>

  <Button>Register</Button>
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

  .error {
    color: red;
    margin-top: 5px;
  }
</style>
