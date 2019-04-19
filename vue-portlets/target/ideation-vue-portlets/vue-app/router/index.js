import Vue from 'vue'
import Router from 'vue-router'
import CreateIdea from '../components/CreateIdea.vue'
import IdeaD from '../components/IdeaD.vue'
import tabs from '../components/tabs.vue'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/createidea',
      name: 'CreateIdee',
      component: CreateIdea
    },
    {
      path : '/ideapubsh',
      name :'IdeaD',
      component :IdeaD
    },{
      path:'/tabs',
      name:'tabs',
      component:tabs
    }
  ]
})
