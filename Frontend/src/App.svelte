<!-- Scripts -->
<script>
  import Header from "./Header.svelte";
  import Footer from "./Footer.svelte";
  import CardView from "./CardView.svelte";
  import Map from "./Map.svelte";
  import AddJuniorJobForm from "./AddJuniorJobForm.svelte";
  import Login from "./Login.svelte";
  import Search from "./Search.svelte";
  import { lst } from "./db.js";
  import ListView from "./ListView.svelte";
  import Modal from "./Modal.svelte";
  import Profile from "./Profile.svelte";
  import Register from "./Register.svelte";
  import { onMount } from "svelte";
  import {getCookie} from './Helpers.svelte';
  let current_item;
  let signedIn = false;
  let username = "";
  $: console.log("signedIn NOW is : " + signedIn);

  onMount(() => {
    const userIDCookie = getCookie("userIdForSession");
    if (userIDCookie) {
      signedIn = userIDCookie;
    }
  });

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
    console.log('handling signout xd!!!');
    console.log('logout clicked');
    console.log("user signed out");
    document.cookie = "userIdForSession=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    username = "";
    signedIn = false;
    console.log("signedIn: " + signedIn);
    current_item = "Home";
  };

  let currentView = "card";

  function toggleView() {
    currentView = currentView === "list" ? "card" : "list";
  }

  let modalVisible = false;
  let item;

  function toggleModal() {
    modalVisible = !modalVisible;
  }

  function handleGetIdFromDivClick(e) {
    console.log("div clicked w/ id:", e.detail.id);
    item = { ...e.detail };
    console.log("item : " + item);
    toggleModal();
  }

  const handleRegisterSuccess = () => {
    current_item = "Sign In";
  };
</script>

<!-- Scripts -->
<!--
	Binding the current_item so that when its updated in the header it will also be
	updated here, basically to keep track of what to display
-->
<Header bind:current_item {signedIn} {username} on:signOut={handleSignOut} />
<button class="view-toggle" on:click={toggleView}>
  {#if currentView === "list"}
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="icon icon-tabler icon-tabler-layout-list"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      stroke-width="2"
      stroke="#330303"
      fill="none"
      stroke-linecap="round"
      stroke-linejoin="round"
    >
      <path stroke="none" d="M0 0h24v24H0z" fill="none" />
      <rect x="4" y="4" width="16" height="16" rx="2" />
    </svg>
  {:else}
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="icon icon-tabler icon-tabler-layout-grid"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      stroke-width="2"
      stroke="#330303"
      fill="none"
      stroke-linecap="round"
      stroke-linejoin="round"
    >
      <path stroke="none" d="M0 0h24v24H0z" fill="none" />
      <rect x="4" y="4" width="6" height="6" rx="1" />
      <rect x="14" y="4" width="6" height="6" rx="1" />
      <rect x="4" y="14" width="6" height="6" rx="1" />
      <rect x="14" y="14" width="6" height="6" rx="1" />
    </svg>
  {/if}
</button>
<Search />

<main>
  <Modal {item} {modalVisible} on:closeModal={toggleModal} />
  <!-- svelte-ignore empty-block -->
  {#if current_item === "Home"}
    {#if currentView === "list"}
      <ListView />
    {:else}
      <CardView {$lst} on:getIdFromDivClick={handleGetIdFromDivClick} />
    {/if}

    <Map {$lst} />
  {:else if current_item === "About us"}
    <div><h2>Here comes the About us page</h2></div>
  {:else if current_item === "Post Junior Job"}
    <AddJuniorJobForm on:add={listenAdd} />
  {:else if current_item === "My Profile"}
    <Profile />
  {:else if current_item === "Statistics"}
    <div><h2>Here comes some interesting statistics</h2></div>
  {:else if current_item === "Register"}
    <Register on:registerSuccess={handleRegisterSuccess}/>
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
