import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import Blog from '../components/Blog.vue'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/createCours',
      name: 'Blog',
      component: Blog
    }
  ]
})
