<template>
  <div id="login-pane">
    <b-card title="Login">
      <b-alert :show="wrongLoginAlert" variant="danger">Wrong Username or Password</b-alert>
      <b-form @submit.prevent="onSubmit">
        <b-form-group label="Username:" label-for="username" description="Enter your your username">
          <b-form-input id="username" v-model="form.username" type="text" required />
        </b-form-group>
        <b-form-group label="Password:" label-for="password" description="Enter your your password">
          <b-form-input id="password" v-model="form.password" type="password" required />
        </b-form-group>
        <b-button type="submit" variant="primary">Login</b-button>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { LoginManager, login } from "../../model/rest/RestUtils";
export default {
  data() {
    return {
      form: {
        username: "",
        password: ""
      },
      wrongLoginAlert: false
    };
  },
  methods: {
    async onSubmit() {
      try {
        await login(this.form.username, this.form.password);
        // sucecss

        if (window.history.length == 0) {
          this.$router.push("/");
        } else {
          this.$router.back();
        }
      } catch (e) {
        // failed
        this.wrongLoginAlert = true;
      }
    }
  }
};
</script>

<style>
</style>