<!-- Scripts -->
<script>
  import Navbar from "./Navbar.svelte";

  let arr = [
    // array with the navbar items
    "Home",
    "About us",
    "Post Junior Job",
    "My Profile",
    "Statistics",
    "Sign In",
  ];

  export let current_item = arr[0]; // starting with the Homepage being highlighted

  const customEventListen_clickNavItem = (e) => {
    current_item = e.detail; // change the current_item to whichever was clicked
  };
</script>

<!-- HTML -->
<header>
  <div class="title">
    <span class="red_text">Junior</span> Job
  </div>
  <img src="/img/icon.png" alt="Zed Icon" class="icon" />
  <Navbar
    {arr}
    {current_item}
    on:customEvent_clickNavItem={customEventListen_clickNavItem}
  />
</header>

<!-- Css -->
<style>
  header {
    background: #f7f5f5;
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
    max-width: 69px;
  }

  .red_text {
    color: red;
  }
</style>
