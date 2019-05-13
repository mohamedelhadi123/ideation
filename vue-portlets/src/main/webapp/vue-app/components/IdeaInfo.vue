<template>
    <div class="cdtop">
        <v-card class="my-5">
            <v-card-title>
                <div class="lab"> Titre :</div>
                <div class="tl">
                    {{ this.donnesidea.title }}
                </div>
                <div class="lin">
                    <router-link to="/rating">voir classement </router-link>
                </div>
            </v-card-title>
             <v-card-title>
                <div class="lab"> Théme :</div>
                <div class="des">{{ this.nameOfTheme }}</div>
            </v-card-title>
               <v-card-title>
                <div class="lab"> Objectif :</div>
                <div class="des">{{ this.donnesidea.explanation }}</div>
            </v-card-title>
            <v-card-title>
                <div class="lab"> Description :</div>
                <div class="des">{{ this.donnesidea.description }}</div>
            </v-card-title>

            <v-card-title>
                <div class="lab"> Resume :</div>
                <div class="des">{{ this.donnesidea.resume }}</div>
            </v-card-title>
            <v-card-title>
                <v-btn flat icon color="pink" @click="addFavori" :disabled=this.okFav>
                    {{ this.favlength}}
                    <v-icon>favorite</v-icon>
                </v-btn>
                <v-btn flat icon @click="addLike" color="deep-orange" :disabled=this.okLike>
                    {{ this.like }}
                    <v-icon>thumb_up</v-icon>
                </v-btn>
                <v-btn flat icon @click="addDisLike" color="deep-orange" :disabled=this.okDislike>
                    {{ this.dislike }}
                    <v-icon>thumb_down</v-icon>
                </v-btn>
                <v-btn flat icon @click="openComment" >
                    <v-icon large color="blue darken-2">chat</v-icon>
                </v-btn>
            </v-card-title>
            <div v-if="yesCom">
                <v-card-title >
                    <div class="cmtHeader">Commentaire ...</div>
                </v-card-title>
            </div>
            <div>
                <v-card-text>
                    <ul >
                        <li v-for="d in commentDonne" :key="d.id" >
                            <v-card-text class="tet">
                                <div class="namec">{{ d.user }}:</div>
                                <div class="cmt">{{ d.commentText }}</div>
                                <div class="dt">{{d.createdTime}}</div>
                            </v-card-text>
                        </li>
                    </ul>
                </v-card-text>
            </div>
            <div v-if="clik">
                <div  class="textar">
                    <v-textarea
                            v-model="commentTextt"
                            box
                            name="input-7-4"
                            label="Ajouter Votre commentaire"
                    ></v-textarea>
                </div>
                <div class="btn-ma">
                    <button class="btn-primary" @click="addComment">Enregistrer</button>
                </div>
            </div>
        </v-card>
    </div>
</template>
<script>
    import axios from 'axios';

    export default {
  
        data(){
            return{
                datal:{
                    status:"",
                    user:"",
                    yesCom:false,
                    id_idear:this.$route.params.id
                },
                okFav:false,
                okLike:false,
                okDislike:false,
                like:null,
                dislike:null,
                favlength:null,
                clik:false,
                donnes:[],
                nameOfTheme:'',
                commentDonne:[],
                donnesidea:[],
                commentTextt:'',

                datajson:{
                    user:'',
                    commentText:'',
                    id_ideac:this.$route.params.id,



                },
                datajsonfav:{
                    id_Ideaf:this.$route.params.id,
                },

                no:this.$route.params.id,

            }
        },

        mounted () {


            this.verifyRating();
            axios
                .get('/portal/rest/idea/getideabyid/'+this.no)
                .then(response => { this.donnesidea=response.data;
                    
                  axios
                .get('/portal/rest/theme/getthemebyid/'+response.data.id_themet)
                .then(response => { this.nameOfTheme=response.data.subject;
                    

                })
            
                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })

            axios
                .get('/portal/rest/rating/getratingbyidea/'+this.no+'/DISLIKE')
                .then(response => { this.dislike=response.data.length;

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })
            axios
                .get('/portal/rest/fav/getbyidea/'+this.no)
                .then(response => { this.favlength=response.data.length;

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })
            axios
                .get('/portal/rest/rating/getratingbyidea/'+this.no+'/LIKE')
                .then(response => { this.like=response.data.length;

                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })

            axios
                .get('/portal/rest/comment/allcommentbyidea/'+this.no)
                .then(response => { this.commentDonne=response.data;
                    if(this.commentDonne.length!==0){
                        this.yesCom=true;
                    }
                })
                .catch(error => {
                    console.log(error)
                    this.errored = true
                })


        } ,methods:{
            addComment(){
                this.datajson.commentText=this.commentTextt;
                console.log(this.datajson);
                axios.post('/portal/rest/comment/add', this.datajson, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }) .then(response => {this.commentDonne.push(response.data);
                    this.yesCom=true;})
                    .catch(e => {
                        this.errors.push(e)
                    })
                this.commentTextt='';


            },verifyRating(){
                const sat=null;
                axios
                    .get('/portal/rest/rating/verif/'+this.no )
                    .then(response => { if(response.data.length===0){
                        this.okLike=false;
                        this.okDislike=false;
                    }else if(response.data[0].status==="LIKE"){
                        this.okLike=true;
                        this.okDislike=true;
                        console.log("ok ******"+this.okLike);
                    }else if(response.data[0].status==="DISLIKE"){
                        this.okDislike=true;
                        this.okLike=true;
                        console.log("ok dis *********"+this.okDislike);
                    }

                    })
                    .catch(error => {
                        console.log(error)
                        this.errored = true
                    })

                axios
                    .get('/portal/rest/fav/verif/'+this.no)
                    .then(response => { if(response.data.length===0){
                        this.okFav=false;
                    }else {
                        this.okFav=true;
                    }

                    })
                    .catch(error => {
                        console.log(error)
                        this.errored = true
                    })


            },addLike(){
                this.datal.status="LIKE";
                console.log("*******"+this.$route.query.id);
                console.log(this.datal);
                axios.post('/portal/rest/rating/addrating', this.datal, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }) .then(response => {this.like++;
                    this.okLike=true;
                    this.okDislike=true;})
                    .catch(e => {
                        this.errors.push(e)
                    })

            },addDisLike(){
                this.datal.status="DISLIKE";
                console.log(this.datal);
                axios.post('/portal/rest/rating/addrating', this.datal, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }) .then(response => {this.dislike++;
                    this.okDislike=true;
                    this.okLike=true;})
                    .catch(e => {
                        this.errors.push(e)
                    })

            },addFavori(){
                console.log(this.datajsonfav);
                axios.post('/portal/rest/fav/addfav', this.datajsonfav, {
                    headers: {
                        'Content-type': 'application/json',
                    }
                }) .then(response => {this.favlength++;
                    this.okFav=true})
                    .catch(e => {
                        this.errors.push(e)
                    })

            },
            openComment(){
                this.clik=true;
            }




        }

    }
</script>
<style>
    .dem{
        width: 755px
    }
    .backgroundTop{
        background:linear-gradient(to bottom, #1867c0, #5CBBF6);
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: justify;
        color: #2c3e50;
    }
    .tet{
        margin-left: 20px;
        margin-right: 20px;
    }
    .cont{
        width: 89%;

    }
    .lin{
        margin-left: 75%;
        font-style:italic;
    }
    .tl{
            margin-left: 2%;
    font-family: monospace;
    }
    .des{
            margin-left: 2%;
    }
    .leb{
        font-family: -webkit-body;
        font-size: large;
        font-style: italic;
    }

    .namec{
    margin-top: -3%;
    font-family: -webkit-body;
    font-size: 131%;
    color: #476a9c;
    }
    .cmt{
     margin-top: 2%;
    margin-left: 6%;
    font-size: 92%;
    font-family: sans-serif;
    }
    .dt{
    margin-left: 0%;
    margin-top: 2%;
    font-size: 75%;
    font-style: italic;
    margin-bottom: 3%;

    }
    .textar{
        width: 96%;

        margin-left: 1%;
    }

    .btn-ma{
        margin-left: 1%;

    }
    .cmtHeader{
            font-size: 113%;
    font-family: cursive;
    }
    .lab{
            font-size: 130%;
    font-family: auto;
    color: #476a9c;
    }
    .cdtop{
            margin-top: -4%;
   
    }
</style>


