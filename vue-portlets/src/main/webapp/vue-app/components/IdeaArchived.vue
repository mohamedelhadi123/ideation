<template>
    <div class="team">
        <div class="alert alert-success" v-if="alt">
            <i class="uiIconSuccess"></i>Aucun Idée Archivée
        </div>
        <v-container >
            <v-layout row wrap>
                <v-flex xs12 sm6 md4 lg3 v-for="idea in ideas" :key="idea.id">
                    <v-card flat class="text-xs-left ma-3">
                        <v-card-text>
                            <div class="subheading" >
                                <spacer></spacer>
                                <i class="far fa-address-card" color="blue"></i>
                                Titre :{{ idea.title }}
                            </div>
                            <div class="grey--text">
                                <i class="fas fa-user-graduate"></i>
                                Crée par :{{ idea.user }}
                            </div>
                            <div class="grey--text">
                                <i class="fas fa-calendar"></i>
                                le :{{ idea.createdTime}}
                            </div>
                        </v-card-text>
                        <v-card-actions>
                            <router-link :to="`/ideainfo/${idea.id}`"><i class="fas fa-angle-double-right"></i> </router-link>

                        </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>
<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                alt:false,
                ideas: []
            }
        },mounted(){
            axios
                .get('/portal/rest/idea/all/ARCHIVED')
                .then(response => { this.ideas=response.data;
                    if(this.ideas.length===0){
                        this.alt=true;
                    }

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })
        }
    }
</script>
<style>

    .cardstyle{
        margin-left: 20%;
    }
</style>


