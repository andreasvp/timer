<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light" style="background-color: #e3f2fd;">
      <router-link to="/" class="navbar-brand">Metimer - AvP</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/cards" class="nav-link">Cards</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/metimers" class="nav-link">Liste</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Hinzufügen</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>

    <!-- Footer -->
    <footer class="page-footer font-small special-color-dark pt-4">

      <!-- Copyright -->
      <div class="footer-copyright text-center py-3">© 2021 Copyright:
        <a href="http://vpein.net/">vpein.net</a>
      </div>
      <!-- Copyright -->

    </footer>
    <!-- Footer -->

  </div>
</template>

<script>

export default {
  name: "app",
    components: {
    }
};
</script>

