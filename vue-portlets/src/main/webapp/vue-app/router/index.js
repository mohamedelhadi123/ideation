import Vue from 'vue'
import Router from 'vue-router'
import IdeaPublished from '../components/IdeaPublished.vue';
import test from '../components/test.vue';
import IdeaInfo from '../components/IdeaInfo.vue';
import IdeaFavori from '../components/IdeaFavori.vue';
import IdeaArchived from '../components/IdeaArchived.vue';
import IdeaDrafted from '../components/IdeaDrafted.vue';
import Rating from '../components/Rating.vue';
import AddIdea from '../components/AddIdea.vue';

Vue.use(Router)

export default new Router({
    routes: [{
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
            path: '/ideainfo/:id',
            name: 'IdeaInfo',
            component: IdeaInfo

        }, {
            path: '/ideafav',
            name: 'IdeaFavori',
            component: IdeaFavori

        }, {
            path: '/ideaarchived',
            name: 'IdeaArchived',
            component: IdeaArchived

        }, {
            path: '/ideadrafted',
            name: 'IdeaDrafted',
            component: IdeaDrafted
        }, {
            path: '/rating',
            name: 'Rating',
            component: Rating
        }, {
            path: '/addidea',
            name: 'AddIdea',
            component: AddIdea
        }

    ]
})
