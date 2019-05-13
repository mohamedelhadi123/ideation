<template>

    <v-container >

        <div class="ideas">



            <v-expansion-panel>
                <div class="alert alert-warning" v-if="alt">
                    <i class="uiIconWarning"></i>Aucun Idée redigée  .


                </div>
                <v-expansion-panel-content v-for="d in donnes" :key="d.id">
                    <!-------------------------------- Dialog for Update ------------------------------------->

                    <div v-if="altDiag" class="UIPopupWindow uiPopup UIDragObject NormalStyle" style="width: 560px; position: relative; top: auto; left: auto; margin: 0 auto 20px; z-index: 1; max-width: 100%;">
                        <div class="popupHeader ClearFix">
                            <a class="uiIconClose pull-right" aria-hidden="true" ></a>
                            <span class="PopupTitle popupTitle">Modifier Votre Idée</span>
                        </div>
                        <div class="PopupContent popupContent">
                            <div class="form-horizontal resizable">
                                <div class="popupContent">
                                    <div class="control-group">
                                        <label for="label" class="control-label">Titre:</label>
                                        <div class="controls"><input type="text" id="label" name="label" v-model="d.title"></div>
                                    </div>
                                    <div class="control-group">
                                        <label for="label" class="control-label">L'objectif:</label>
                                        <div class="controls"><input type="text"   v-model="d.explanation"></div>
                                    </div>
                                    <div class="control-group">
                                        <label for="label" class="control-label">Resumé:</label>
                                        <div class="controls"><textarea type="text"  v-model="d.resume"/></div>
                                    </div>
                                    <div class="control-group">
                                        <label for="label" class="control-label">Discription:</label>
                                        <div class="controls"><textarea type="text" id="label" name="label" v-model="d.description"/></div>
                                    </div>
                                    <p class=" styleP">Ajouter un fichier</p>
                                    <upload-btn icon >
                                        <template slot="icon">
                                            <v-icon size="60px">attachment</v-icon>
                                        </template>
                                    </upload-btn>
                                </div>
                            </div>
                            <div class="uiAction uiActionBorder">
                                <button class="btn"  @click.prevent="UpdateIdea(d.id,d.title,d.description,d.resume,d.explanation)" >Save</button>
                                <button class="btn" @click="DontShow" type="button">Cancel</button>
                            </div>
                        </div>
                        <span class="uiIconResize pull-right uiIconLightGray"></span>
                    </div>
                    <!--------------------------------------- END -------------------------------------------->
                    <div slot="header" class="py-1">{{ d.title }}</div>
                    <v-card>
                        <v-card-text class="px-4 grey--text">
                            <div>{{ d.description }}  </div>

                            <div class="font-weight-bold">créer par {{d.user}} le {{ d.createdTime }}</div>
                            <button class="btn" @click="AffichDialog">Modifier</button>
                            <button  class="btn" @click.prevent="DeleteIdea(d.id)">Supprimer </button>
                            <button  class="btn-primary" @click.prevent="AddToPublished(d.id,d.title,d.description,index)">Publier</button>





                        </v-card-text>
                    </v-card>
                </v-expansion-panel-content>
            </v-expansion-panel>


        </div>
    </v-container>
</template>

<script>
    import UploadButton from 'vuetify-upload-button';

    import axios from 'axios';
    export default {
        data() {
            return {
                donnes:[],
                altDiag:false,
                alt:false,
                dialog: false,
                datajson:{
                    id:null,
                    title:'',
                    description:'',
                    status:'',
                    resume:'',
                    explanation:'',

                },

            }}, components:{
            'upload-btn': UploadButton

        },




        mounted () {

            axios
                .get('/portal/rest/idea/AllIdeaByUserAndStatus/DRAFET')
                .then(response => { this.donnes=response.data;
                    if(this.donnes.length===0){
                        this.alt=true;
                    }
                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })
        },

        methods: {
            DeleteIdea: function(event){
                axios.delete('/portal/rest/idea/delete/'+event, {
                    headers : {
                        'Content-Type' : 'application/json'
                    }
                }).then( response=>{this.donnes=this.donnes.filter(d=>d.id !==event)
                        if(this.donnes.length===0){
                            this.alt=true;
                        }

                    }

                )
                    .catch(error => {
                        console.log(error)
                        this.errored = true
                    })

            },
            AddToPublished:function(id,title,description){
                const idx = this.donnes.indexOf();

                this.datajson.status="PUBLISHED";
                this.datajson.createdTime=new Date();
                this.datajson.description=description;
                this.datajson.title=title;
                this.datajson.id=id;
                axios.put('/portal/rest/idea/update', this.datajson, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }).then(response=>{this.$router.push('/')})

            },
            UpdateIdea:function(id,title,description,resume,explanation){

                this.datajson.status="DRAFET";
                this.datajson.description=description;
                this.datajson.title=title;
                this.datajson.id=id;
                this.datajson.explanation=explanation;
                this.datajson.resume=resume;
                console.log(this.datajson);
                axios.put('/portal/rest/idea/update', this.datajson, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }).then( response=>{this.altDiag=false;
                    axios
                        .get('/portal/rest/idea/AllIdeaByUserAndStatus/DRAFET')
                        .then(response => {
                            this.donnes=response.data;

                        })
                        .catch(error => {
                            console.log(error)
                            this.errored = true
                        })})
            }
            ,

            reset() {
                this.titre= null;
                this.description=null;
                console.log("ok");

            },DontShow(){
                this.altDiag=false;
            },
            annuler(){
                this.dialog=false;

                console.log(this.dialog);
            },AffichDialog(){
                this.altDiag=true;
            },
        }
    }






</script>
<style>
    .textet{
        width: 72%;

    }
    .bt{
        justify-content: flex-end;
        width: 95%;
    }

    .select_style{
        margin-left: 12%;
        width: 108%;
    }
</style>








