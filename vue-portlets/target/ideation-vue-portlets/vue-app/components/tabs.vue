   
            <template v-slot:extension>
               <v-tabs
                centered
               color="cyan"
                  dark
                 icons-and-text
                 >
                    <v-tabs-slider color="yellow"></v-tabs-slider>

                    <v-tab v-for="item in links" :key="item"  router
                           :to="item.route">
                        {{ item.text }}
                    <v-icon >{{ item.icon }}</v-icon>

                    </v-tab>
               <v-tab>
                   <v-btn fab dark small color="indigo">
                  <v-icon dark>add</v-icon>
                   </v-btn>
               </v-tab>
                </v-tabs>
            </template>
       

<script>
    export default {
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