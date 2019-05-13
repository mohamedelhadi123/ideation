<template>
    <v-container>
        <div>
            <v-stepper labels>
                <v-stepper-header>
                    <v-stepper-step step="3" complete>
                        <v-flex xs12 sm3>
                            <v-btn flat icon color="green" @click="showMyIdeaPublished" >
                                <v-icon>cached</v-icon>
                                Voir mes idées
                            </v-btn>
                            <v-btn flat icon color="green" @click="showMyIdeaPublished" >
                                <v-icon>cached</v-icon>
                                Voir mes idées
                            </v-btn>
                        </v-flex>

                    </v-stepper-step>
                </v-stepper-header>
            </v-stepper>

            <v-expansion-panel>
                <div class="alert alert-info" v-if="alt">
                    <i class="uiIconInfo"></i>Aucun Idee puiblier . </div>
                <v-expansion-panel-content v-for="d in donnes" :key="d.id">
                    <div slot="header" class="py-1">{{ d.title }}</div>
                    <v-card>
                        <v-card-text class="px-4 grey--text">
                            <div>{{ d.description }}  </div>

                            <div class="font-weight-bold">créer par {{d.user}} le {{ d.createdTime }}</div>
                            <router-link :to="`/ideainfo/${d.id}`">Lire la suite ...</router-link>
                        </v-card-text>
                    </v-card>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </div>
    </v-container>
</template>

<script>
    import axios from 'axios';

    export default {

        data() {
            return {
                donnes:[],
                alt:false,
                dialog: false,



            }
        },  mounted () {

            axios
                .get('/portal/rest/idea/all/PUBLISHED')
                .then(response => { this.donnes=response.data;

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })

        }, methods:{
            showMyIdeaPublished(){
                this.donnes=null;
                axios
                    .get('/portal/rest/idea/AllIdeaByUserAndStatus/PUBLISHED')
                    .then(response => { this.donnes=response.data;
                        if(this.donnes.length===0){
                            this.alt=true;
                        }

                    })
                    .catch(error => {
                        console.log(error)
                        this.errored = true
                    })
            }
        }


    }
</script>



