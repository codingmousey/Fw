<!-- Scripts -->
<script>
  import Header from "./Header.svelte";
  import Footer from "./Footer.svelte";
  import JuniorJobLst from "./JuniorJobLst.svelte";
  import Map from "./Map.svelte";
  let current_item;
  let data = [
    {
      id: 1,
      company: 'Cegeka',
      jobtitle: 'Junior .NET developer',
      addres: 'Hasselt',
      tags: ['.NET', 'NodeJS'],
    },
    {
      id: 2,
      company: 'Apple',
      jobtitle: 'Junior System Architect',
      addres: 'Antwerpen',
      tags: ['Cpp', 'CMake'],
    },
    {
      id: 3,
      company: 'Microsoft',
      jobtitle: 'Fullstack Developer',
      addres: 'Gent',
      tags: ['Java', 'Springboot'],
    },
  ];

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
	<JuniorJobLst {data}/>
	<Map/>
  {:else if current_item === "About us"}
    <div><h2>Here comes the About us page</h2></div>
  {:else if current_item === "Post Junior Job"}
    <div><h2>Here comes the form to post junior jobs</h2></div>
  {:else if current_item === "My Profile"}
    <div><h2>Here comes the profile page</h2></div>
  {:else if current_item === "Statistics"}
    <div><h2>Here comes some interesting statistics</h2></div>
  {:else if current_item === "Sign In"}
    <div><h2>Here comes the sign in page</h2></div>
  {/if}
</main>
<Footer />

<!-- Css -->
<style>
  main {
    max-width: 80%;
    margin: 30px auto;
	display:flex;
 
  }
</style>
