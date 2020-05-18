<template>
  <div id="app">
    <Navigation></Navigation>
    <router-view />
  </div>
</template>

<script>
import NavigationVue from "./pages/common/Navigation.vue";
import Routes from "./router/Routes";
export default {
  name: "App",
  created() {
    Routes._init(this.$router);
  },
  components: {
    Navigation: NavigationVue
  }
};
</script>

<style>
</style>
