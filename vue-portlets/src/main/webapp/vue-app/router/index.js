import Vue from 'vue'
import Router from 'vue-router'
import IdeaPublished from '../components/IdeaPublished.vue';
import test from '../components/test.vue';
import IdeaInfo from '../components/IdeaInfo.vue';
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IdeaPublished',
      component: IdeaPublished
    },
    {
      path: '/test',
      name: 'test',
      component: test
    },
    {
      path: '/ideainfo',
      name :'IdeaInfo',
      component: IdeaInfo

    }
    
  ]
})
