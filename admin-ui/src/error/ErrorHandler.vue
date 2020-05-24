<template>
  <div>
    <notifications />
  </div>
</template>

<script>
import Vue from "vue";
export default {
  created() {
    Vue.config.errorHandler = (err, vm, info) => {
      this.notifyError(err.name, err.message, err);
    };
    window.onerror = (message, source, lineno, colno, error) => {
      this.notifyError(err.name, message);
    };
  },
  methods: {
    notifyError(title, errorMsg, error = null) {
      console.error(`${title} - ${errorMsg}`, error);
      this.$notify({
        title: title,
        text: errorMsg,
        duration: 10000,
        type: "error"
      });
    }
  }
};
</script>

<style>
</style>