<!-- Scripts -->
<script>
  import Header from "./Header.svelte";
  import Footer from "./Footer.svelte";
  import JuniorJobLst from "./JuniorJobLst.svelte";
  import Map from "./Map.svelte";
  import AddJuniorJobForm from "./AddJuniorJobForm.svelte";
  import Login from "./Login.svelte";
  let current_item;
  let data = [
    {
      id: 1,
      company: "Cegeka",
      jobtitle: "Junior .NET developer",
      addres: "Hasselt",
      tags: [".NET", "NodeJS"],
    },
    {
      id: 2,
      company: "Apple",
      jobtitle: "Junior System Architect",
      addres: "Antwerpen",
      tags: ["Cpp", "CMake"],
    },
    {
      id: 3,
      company: "Microsoft",
      jobtitle: "Fullstack Developer",
      addres: "Gent",
      tags: ["Java", "Springboot"],
    },
  ];
  const listenAdd = (e) => {
    const obj = e.detail;
    data = [obj, ...data];
    console.log(data);
    current_item = 'Home';
  }
</script>

<!-- Scripts -->
<!--
	Binding the current_item so that when its updated in the header it will also be
	updated here, basically to keep track of what to display
-->
<Header bind:current_item />
<main>
  <!-- svelte-ignore empty-block -->
  {#if current_item === "Home"}
    <JuniorJobLst {data} />
    <Map {data}/>
  {:else if current_item === "About us"}
    <div><h2>Here comes the About us page</h2></div>
  {:else if current_item === "Post Junior Job"}
    <AddJuniorJobForm  on:add={listenAdd}/>
  {:else if current_item === "My Profile"}
    <div><h2>Here comes the profile page</h2></div>
  {:else if current_item === "Statistics"}
    <div><h2>Here comes some interesting statistics</h2></div>
  {:else if current_item === "Sign In"}
    <Login/>
  {/if}
</main>
<Footer />

<!-- Css -->
<style>
  main {
    max-width: 80%;
    margin: 30px auto;
    display: flex;
  }
</style>
