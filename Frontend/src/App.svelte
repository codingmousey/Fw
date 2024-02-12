<!-- Scripts -->
<script>
  import Header from "./Header.svelte";
  import Footer from "./Footer.svelte";
  let current_item;
</script>

<!-- HTML -->
<!--
	Binding the current_item so that when its updated in the header it will also be
	updated here, basically to keep track of what to display
-->
<Header bind:current_item />
<main>
  <h2>Welcome to Junior Job main page!</h2>
  <p>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean sed erat
    lectus. Cras aliquet ut dolor sed volutpat. Nullam elementum nulla quam.
    Donec sapien eros, congue at urna vel, ornare condimentum mi. Donec aliquet,
    tortor ac hendrerit rutrum, arcu leo consequat dolor, non viverra urna nulla
    vel dolor. Nam at elit ligula. Donec velit risus, placerat ut justo ac,
    sollicitudin fringilla lacus. Duis diam nisi, consectetur at sapien sed,
    pharetra malesuada dui. Quisque ornare scelerisque scelerisque. Aliquam sit
    amet interdum sapien, ut egestas nibh. Curabitur molestie rhoncus tempor.
    Aliquam placerat eros vel elit tincidunt, nec feugiat mauris molestie.
  </p>
  <h2>The current navbar value is: {current_item}</h2>
</main>
<Footer />

<!-- Css -->
<style>
  main {
    max-width: 80%;
    margin: 30px auto;
  }
</style>
