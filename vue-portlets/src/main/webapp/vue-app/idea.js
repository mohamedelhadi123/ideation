import IdeaApp from './components/IdeaApp.vue';
import router from './router/index';
import UploadButton from 'vuetify-upload-button';

import '../css/main.less';

Vue.use(Vuetify);
new Vue({
    el: '#IdeaApp',
    router,
    render: (h) => h(IdeaApp),
});
