<template>
  <div>
    <v-stepper labels>
      <v-stepper-header>
        <v-stepper-step step="3" complete>
          {{ this.donnesidea.title }}     
        </v-stepper-step>
        <v-stepper-step step="3" complete>
          <router-link to="/ideaclass">voir classement </router-link>
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
    <v-card>
      <v-card-text class="tet">
        <div>{{ this.donnesidea.description }}  </div>
      </v-card-text>
      <v-card-text>
        <div class="font-weight-bold">créer le  {{ this.donnesidea.createdTime }} par {{ this.donnesidea.user }}</div>
      </v-card-text>
      <v-stepper labels>
        <v-stepper-header>
          <v-stepper-step step="3" complete>
            <v-layout row wrap>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  color="pink"
                  @click="addFavori"
                  :disabled=this.okFav>
                  {{ this.favlength}}
                  <v-icon>favorite</v-icon>
                </v-btn>
              </v-flex>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  @click="addLike"
                  color="deep-orange"
                  :disabled=this.okLike>
                  {{ this.like }}
                  <v-icon>thumb_up</v-icon>
                </v-btn>
                
              </v-flex>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  @click="addDisLike"
                  color="deep-orange"
                  :disabled=this.okDislike>
                  {{ this.dislike }}
                  <v-icon>thumb_down</v-icon>
                </v-btn>
              </v-flex>
            </v-layout>
          </v-stepper-step>
        </v-stepper-header>
      </v-stepper>
      <ul >
        <li v-for="d in commentDonne" :key="d.id">
          <v-card-text class="tet">
            <div class="font-weight-bold">{{ d.user }}</div>
            <div><h6>{{ d.commentText }}</h6></div>
            <br>
            <h9>{{d.createdTime}}</h9>
            <br>
          </v-card-text>
        </li>
      </ul>
      <v-card class="tet">
        <v-textarea
        
          v-model="commentTextt"
          class="dem"
          placeholder="Entrer votre commentaire ..." /> 
      
    
      
        <v-btn icon color="deep-orange" @click="addComment"><i class="fas fa-arrow-alt-circle-right"></i> </v-btn>
      </v-card>
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
            id_idear:this.$route.query.id
          },
          okFav:false,
          okLike:false,
          okDislike:false,
          like:null,
          dislike:null,
          favlength:null,
         
          donnes:[],
          commentDonne:[],
          donnesidea:[],
          commentTextt:'',
          
        datajson:{
             user:'',
             commentText:'',
             id_ideac:this.$route.query.id,
             createdTime:'' 



          },
          datajsonfav:{
            id_Ideaf:this.$route.query.id,
          },
         
          no:this.$route.query.id,
     items: [
      { message: 'bravoo !!' ,id:"1",name:"Ahmed"},
      { message: 'good frd .. ;) ',id:"2",name:'Mohamed' }
    ],
    idea:{titre:"Idea 1",createur:"alioua",description:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!",waket:"1-01-2018",like:'12',dislike:'14',fav:'3'}

        }
    }, 
    
    mounted () { 


this.verifyRating();
     
     axios
      .get('http://127.0.0.1:8080/portal/rest/idea/getone/'+this.no)
      .then(response => { this.donnesidea=response.data;
      console.log(this.donnesidea);
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })

     axios
      .get('http://127.0.0.1:8080/portal/rest/rating/getratingbyidea/'+this.no+'/DISLIKE')
      .then(response => { this.dislike=response.data.length;
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
axios
      .get('http://127.0.0.1:8080/portal/rest/fav/getbyidea/'+this.no)
      .then(response => { this.favlength=response.data.length;
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
        axios
      .get('http://127.0.0.1:8080/portal/rest/rating/getratingbyidea/'+this.no+'/LIKE')
      .then(response => { this.like=response.data.length;
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })

        axios
      .get('http://127.0.0.1:8080/portal/rest/comment/allcommentbyid/'+this.no)
      .then(response => { this.commentDonne=response.data;
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
      
      
  } ,methods:{
    addComment(){
    this.datajson.commentText=this.commentTextt;
    this.datajson.createdTime=new Date();
    console.log(this.datajson);
       axios.post('http://127.0.0.1:8080/portal/rest/comment/add', this.datajson, {
    headers: {
      'Content-type': 'application/json',
    }
   }) .then(response => {this.commentDonne.push(response.data)})
    .catch(e => {
      this.errors.push(e)
    })
   this.commentTextt='';

       
    },verifyRating(){
        const sat=null;
      axios
      .get('http://127.0.0.1:8080/portal/rest/rating/verif/'+this.no )
      .then(response => { if(response.data.length===0){
        this.okLike=false;
        this.okDislike=false;
      }else if(response.data[0].status==="LIKE"){
        this.okLike=true;
        console.log("ok ******"+this.okLike);
      }else if(response.data[0].status==="DISLIKE"){
        this.okDislike=true;
        console.log("ok dis *********"+this.okDislike);
      }
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
    
        axios
      .get('http://127.0.0.1:8080/portal/rest/fav/verif/'+this.no)
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
        axios.post('http://127.0.0.1:8080/portal/rest/rating/addrating', this.datal, {
    headers: {
      'Content-type': 'application/json',
    }
   }) .then(response => {this.like++;
   this.okLike=true})
    .catch(e => {
      this.errors.push(e)
    })

    },addDisLike(){
     this.datal.status="DISLIKE";
      console.log(this.datal);
        axios.post('http://127.0.0.1:8080/portal/rest/rating/addrating', this.datal, {
    headers: {
      'Content-type': 'application/json',
    }
   }) .then(response => {this.dislike++;
   this.okDislike=true})
    .catch(e => {
      this.errors.push(e)
    })

    },addFavori(){
      console.log(this.datajsonfav);
 axios.post('http://127.0.0.1:8080/portal/rest/fav/addfav', this.datajsonfav, {
    headers: {
      'Content-type': 'application/json',
    }
   }) .then(response => {this.favlength++;
   this.okFav=true})
    .catch(e => {
      this.errors.push(e)
    })

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
</style>


