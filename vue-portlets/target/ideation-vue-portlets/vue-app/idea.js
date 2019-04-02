import './../css/main.less';

import IdeaApp from './components/IdeaApp.vue';
import router from './router/index';

Vue.use(Vuetify);

const vueInstance = new Vue({
  el: '#IdeaApp',
  router,
  render: (h) => h(IdeaApp),
});
