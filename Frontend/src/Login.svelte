<script>
  import Button from "./Button.svelte";
  import { createEventDispatcher } from "svelte";
  import { getCookie } from "./Helpers.svelte";
  const dispatch = createEventDispatcher();
  let obj = {
    username: "",
    pw: "",
    error: "",
  };

  const loginHandler = async () => {
    console.log("loginHander clicker: " + obj);
    try {
      const response = await fetch("http://localhost:6969/api/login", {
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
          console.log("user: " + res.id);
          console.log("username: " + res.username);
          console.log('role: ' + res.role);
          document.cookie = `userIdForSession=${res.id}; path=/;`;
          document.cookie = `userRole=${res.role}; path=/;`;
          console.log('userIdCookie: ' + getCookie("userIdForSession"));
          console.log('userRole cookie: ' + getCookie("userRole"));
          console.log()
          dispatch("signIn", {
            username: obj.username,
            role: res.role,
          });
        }
      }
    } catch (error) {
      console.error("error loggin in:", error.message);
    }
  };
</script>

<form on:submit|preventDefault={loginHandler}>
  <div class="error">{obj.error}</div>
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

  .error {
    color: red;
    margin-top: 5px;
  }
</style>
