<template>
    <div class="max">
        <v-container class=" my-5 back"  elevation-20>
            <div class="altm">
                <div class="alert alert-error" v-if="alt">
                   <i class="uiIconError" @click="cancelAlt"></i>
                   SVP remplire tous les champs  !!
                </div>
            </div>
            <v-card
                    class="mb-5"
                    height="700px">
                <v-card-text>
                    <v-form ref="form" class="px-3">
                        <v-container>
                            <v-flex md10>
                                <v-text-field
                                        v-model="name"
                                        label="Entrer le nom de votre idée"
                                        prepend-icon="edit"
                                        :rules="inputRules" />
                            </v-flex>
                            <v-layout>
                                <v-flex md6>
                                    <p class="styleP">Theme de votre idée</p>
                                    <select
                                            class="styleSelect"
                                            v-model="selectedTheme">
                                        <option v-for="s in subject" :value="s.id" :key="s.id">
                                            {{s.subject}}
                                        </option>
                                    </select>
                                </v-flex>
                                <v-flex md4>
                                    <br>
                                    <template>
                                        <v-dialog max-width="600px" v-if="dialogs">
                                            <button slot="activator" class="classbutton "><i class="fas fa-plus-square"></i></button>
                                            <v-card>
                                                <v-card-title>
                                                    <div class="text_diag">
                                                        <h8>Ajouter Nouveau theme</h8>
                                                    </div>
                                                </v-card-title>
                                                <v-card-title>
                                                    <v-text-field
                                                            v-model="nameTheme"
                                                            label="Entrer le Theme"
                                                            prepend-icon="edit"
                                                             :rules="inputRules"/>
                                                    <button  class="btn_diag" @click="AddTheme"><i class="fas fa-plus-square"></i></button>
                                                </v-card-title>
                                            </v-card>
                                        </v-dialog>
                                    </template>
                                </v-flex>
                            </v-layout>
                            <v-layout>
                                <v-flex md10>
                                </v-flex>
                                <v-flex>
                                    <app-category-cours />
                                </v-flex>
                            </v-layout>
                            <v-flex md10>
                                <v-textarea
                                        v-model="description"
                                        prepend-icon="edit"
                                        label="Entrer la Description de votre idée " :rules="inputRules"/>
                            </v-flex>
                            <v-flex md10>
                                <v-textarea
                                        v-model="resume"
                                        prepend-icon="edit"
                                        label="Entrer le résumé de votre idée " :rules="inputRules"/>
                            </v-flex>
                            <v-flex md10>
                                <v-text-field
                                        v-model="cles"
                                        label="Entrer l'objectif"
                                        prepend-icon="edit"
                                         :rules="inputRules"/>
                            </v-flex>
                            <div class="para_border">
                                <v-layout>
                                    <v-flex>
                                        <p class=" styleP">Ajouter un fichier</p>
                                        <upload-btn icon >
                                            <template slot="icon">
                                                <v-icon size="60px">attachment</v-icon>
                                            </template>
                                        </upload-btn>
                                    </v-flex>
                                    <v-flex>
                                    </v-flex>
                                </v-layout>
                            </div>
                            <div class="but">
                                <v-flex md10>
                                    <button class="btn-primary" type="button"  @click="addIdea">Enregistrer</button>
                                </v-flex>
                            </div>
                            <div class="btn-sec">
                                <v-flex md10>
                                    <button class="btn"  @click="reset">Réinitialiser</button>
                                </v-flex>
                            </div>
                        </v-container>
                    </v-form>
                </v-card-text>
            </v-card>
        </v-container>
    </div>
</template>
<script>
    import axios from 'axios';

    import UploadButton from 'vuetify-upload-button';
    export default {
       
        data(){
            
            return{
                name:'',
                selectedTheme:"",
                alt:false,
                resume:'',
                cles:'',
                dialogs:true,
                nameTheme:'',
                description:'',
                datajson:{
                    title:'',
                    description:'',
                    status:"DRAFET",
                    resume:'',
                    explanation:'',
                    id_themet:'',
                },
                dataTheme:{
                    subject:''
                },
                subject:[ ],
                 inputRules: [
                v => !!v || 'This field is required',
            v => v.length >= 3 || 'Minimum length is 3 characters'
        ]
            }
        },
        components:{
            'upload-btn': UploadButton

        },mounted(){
            axios
                .get('/portal/rest/theme/getalltheme/')
                .then(response => { this.subject=response.data;
                    console.log(this.subject);

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })


        } , methods: {
            addIdea(){
                this.datajson.title=this.name;
                this.datajson.description=this.description;
                this.datajson.resume=this.resume;
                this.datajson.explanation=this.cles;
                this.datajson.id_themet=this.selectedTheme;

                if((this.name==='') || (this.description==='') || (this.resume==='') || (this.cles==='') || (this.selectedTheme==='')){
                    this.alt=true;
                }

                if(this.alt===false){
                    console.log(this.datajson)
                    axios.post('/portal/rest/idea/addIdea', this.datajson, {
                        headers: {
                            'Content-type': 'application/json',
                        }
                    }).then( response=>{this.$router.push('/IdeaDrafted') ;
                        this.name='';
                        this.description='';
                        this.cles='';
                        this.resume='';

                    })


                }



            },reset() {
                this.name= null;
                this.description=null;
                this.cles="";
                this.resume="";

            },AddTheme(){
                this.dataTheme.subject=this.nameTheme;
                console.log(this.dataTheme);
                axios.post('/portal/rest/theme/addtheme', this.dataTheme, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }) .then(response => {this.subject.push(response.data);
                    this.dialogs=false;
                })
                    .catch(e => {
                        this.errors.push(e)
                    })
            },cancelAlt(){
                this.alt=false;
            }

        }
    }
</script>

<style>
    .styleSelect{
        margin-left: 6%;
        width: 96%;
    }
    .styleP{
        margin-left: 5%;

    }
    .back{
        background-color: #00bcd4;



    }
    .max{
        width: 92%;
        margin-left: 2%

    }
    .classbutton{
        margin-top: 35%;
        color: rebeccapurple;


    }
    .but{
        margin-left: 84%;


    }
    .btn-sec{
        margin-left: 70%;
        margin-top: -4%;
    }
    .btn_diag{
        margin-left: 3%;
        margin-top: -2%;
        color: blue;
    }
    .text_diag{
        margin-top: -1%;
        margin-bottom: -6%;
    }
    .altm{
        width: 101%;

    }

</style>
