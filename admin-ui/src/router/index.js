import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/pages/main/MainPage.vue'
import ArticlesPageVue from '@/pages/articles/ArticlesPage.vue'
import ArticlesEditPageVue from '@/pages/articles/ArtcileEditPage.vue'
import LoginPageVue from '@/pages/login/LoginPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/articles',
      name: "Articles",
      component: ArticlesPageVue,
      children: [
      ]
    },
    {
      name: "Edit Article",
      path: '/articles/:id/edit',
      component: ArticlesEditPageVue
    },
    {
      name: "Add Article",
      path: '/articles/edit',
      component: ArticlesEditPageVue
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginPageVue
    },
  ]
})
