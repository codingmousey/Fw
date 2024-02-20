<!-- Scripts -->
<script>
  import Header from "./Header.svelte";
  import Footer from "./Footer.svelte";
  import JuniorJobLst from "./JuniorJobLst.svelte";
  import Map from "./Map.svelte";
  import AddJuniorJobForm from "./AddJuniorJobForm.svelte";
  import Login from "./Login.svelte";
  import Search from "./Search.svelte";
  import lst from "./db.js";
  let current_item;
  let signedIn = false;
  let username = "";
  $: console.log("signedIn NOW is : " + signedIn);

  const listenAdd = (e) => {
    console.log(e.detail);
    current_item = "Home";
  };

  const handleSignIn = (userInfo) => {
    console.log("work??" + userInfo.detail.username);
    console.log("user signed in");
    username = userInfo.detail.username;
    signedIn = true;
    console.log("signedIn: " + signedIn);
    console.log("username logged in: " + username);
    console.log("username logged in: " + userInfo.detail.username);
    current_item = "Home";
  };

  const handleSignOut = () => {
    console.log("user signed out");
    username = "";
    signedIn = false;
    console.log("signedIn: " + signedIn);
    current_item = "Home";
  };
</script>

<!-- Scripts -->
<!--
	Binding the current_item so that when its updated in the header it will also be
	updated here, basically to keep track of what to display
-->
<Header bind:current_item {signedIn}{username} on:signOut={handleSignOut} />
<Search />
<main>
  <!-- svelte-ignore empty-block -->
  {#if current_item === "Home"}
    <JuniorJobLst {$lst} />
    <Map {$lst} />
  {:else if current_item === "About us"}
    <div><h2>Here comes the About us page</h2></div>
  {:else if current_item === "Post Junior Job"}
    <AddJuniorJobForm on:add={listenAdd} />
  {:else if current_item === "My Profile"}
    <div><h2>Here comes the profile page</h2></div>
  {:else if current_item === "Statistics"}
    <div><h2>Here comes some interesting statistics</h2></div>
  {:else if current_item === "Sign In"}
    <Login on:signIn={handleSignIn} />
  {/if}
</main>
<Footer />

<!-- Css -->
<style>
  main {
    max-width: 80%;
    margin: 20px auto;
    display: flex;
  }
</style>
