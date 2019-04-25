   
<template v-slot:extension>
  <div>
    <v-tabs
      centered
      color="cyan"
      dark
      icons-and-text>
      <v-tabs-slider color="yellow" />

      <v-tab
        v-for="item in links"
        :key="item"
        router
        :to="item.route">
        {{ item.text }}
        <v-icon>{{ item.icon }}</v-icon>
      </v-tab>
    </v-tabs>
    <test />
  </div>
</template>
       

<script>
import test from './test.vue';
    export default {
         components: {test},
        data () {
            return {
                tab: null,
                links: [
                    { icon: 'check_circle', text: 'Idées Publiées', route: '/' },
                    { icon: 'favorite', text: 'Idées Favoris', route: '/ideafav' },
                    { icon: 'fas fa-edit', text: 'Idées redigées', route: '/ideadrafted' },
                    { icon: 'present_to_all', text: 'Idées Archivées', route: '/ideaarchived' },
                ]
            }
        }
    }
</script>