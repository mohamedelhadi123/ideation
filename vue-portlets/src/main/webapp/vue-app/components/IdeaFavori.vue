<template>
    <div class="team">
        <v-container >
            <div class="malt">
                <div class="alert alert-info" v-if="alt">
                    <i class="uiIconInfo"></i>Aucun Idée Favori
                </div>
            </div>
            <v-layout row wrap>
                <v-flex xs12 sm6 md4 lg3 v-for="idee in donneedatafav" :key="idee.id" >
                    <v-card flat class="text-xs-left ma-3 " >
                        <v-card-text>
                            <div >
                                <i class="far fa-address-card" color="blue"></i>
                                Titre :{{ idee.title }}
                            </div>
                            <div class="grey--text">
                                <i class="fas fa-user-graduate"></i>
                                Crée par :{{ idee.user }}</div>
                            <div class="grey--text">
                                <i class="fas fa-calendar"></i>
                                le :{{ idee.createdTime}}</div>
                        </v-card-text>
                        <v-card-actions>
                            <router-link :to="`/ideainfo/${idee.id}`"><i class="fas fa-angle-double-right"></i> </router-link>
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
                donneedatafav:[],

            }
        },mounted(){
            axios
                .get('/portal/rest/fav/getfavbyuser')
                .then(response => { if(response.data.length===0){
                    this.alt=true;
                }else{
                    for(let i=0;i<response.data.length;i++){


                        axios
                            .get('/portal/rest/idea/getideabyid/'+response.data[i].id_Ideaf)
                            .then(response => { this.donneedatafav.push(response.data);
                                


                            })
                            .catch(error => {
                                console.log(error)
                                this.errored = true
                            })


                    }
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
    .malt{
        width: 92%;
        margin-left: 3%;
    }
</style>
