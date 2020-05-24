<template>
  <div id="app">
    <error-handler />
    <Navigation></Navigation>
    <router-view />
  </div>
</template>

<script>
import NavigationVue from "./pages/common/Navigation.vue";
import Routes from "./router/Routes";
import ErrorHandlerVue from "./error/ErrorHandler.vue";
export default {
  name: "App",
  created() {
    Routes._init(this.$router);
  },
  components: {
    Navigation: NavigationVue,
    ErrorHandler: ErrorHandlerVue
  }
};
</script>

<style>
</style>
