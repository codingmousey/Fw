<script>
  import Button from "./Button.svelte";

  let obj = {
    username: "",
    pw: "",
    pwCheck: "",
    error: "",
  };

  const registerHandler = async () => {
    console.log("register button clicked");
    const registerData = {
      username: obj.username,
      pw: obj.pw,
      pwCheck: obj.pwCheck,
    };

    try {
      const response = await fetch("http://localhost:6969/api/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(registerData),
      });

      if (response) {
        const res = await response.json();
        if (res.error) {
          obj.error = res.error;
          console.log("there are some erros: " + res.error);
        } else {
          console.log("registration success:", res);
          obj.username = "";
          obj.pw = "";
          obj.pwCheck = "";
          obj.error = "";
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
    <label for="username">Username:</label>
    <input type="text" id="username" bind:value={obj.username} />
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
