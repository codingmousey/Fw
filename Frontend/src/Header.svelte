<!-- Scripts -->
<script>
  import Navbar from "./Navbar.svelte";
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();
  import { getCookie } from "./Helpers.svelte";

  let arr = [
    // array with the navbar items
    "Home",
    "About us",
    "Post Junior Job",
    "My Profile",
    "Statistics",
    "Register",
  ];

  export let current_item = arr[0]; // starting with the Homepage being highlighted
  export let signedIn = false;
  export let username = "";

  const customEventListen_clickNavItem = (e) => {
    console.log("clicked on : " + e.detail);
    current_item = e.detail; // change the current_item to whichever was clicked
  };
  $: {
    let isAdmin = getCookie("userRole") === "admin";
    let newArr = [];
    if (signedIn) {
      newArr.push("Home", "About us", "My Profile", "Statistics");
      if (isAdmin) {
        newArr.push("Post Junior Job");
      }
      newArr.push("Sign Out");
    } else {
      newArr.push("Home", "About us", "Statistics", "Register", "Sign In");
    }
    arr = newArr;
  }
</script>

<!-- HTML -->
<header>
  <div class="title">
    <span class="red_text">Junior</span> Job
  </div>
  <img src="/img/icon.png" alt="Zed Icon" class="icon" />
  {#if signedIn && username !== ""}
    <div>Welcome {username}!</div>
  {/if}
  <Navbar
    {arr}
    {current_item}
    {signedIn}
    on:customEvent_clickNavItem={customEventListen_clickNavItem}
    on:signOut
  />
</header>

<!-- Css -->
<style>
  header {
    background: #ffffff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10%;
  }

  .title {
    font-size: 26px;
    font-weight: bold;
  }

  .icon {
    max-width: 100px;
  }

  .red_text {
    color: red;
  }
</style>
