<template>
  <div class="crd">
    <div v-if="alt" class="alert alert-info">
      <i class="uiIconInfo"></i>Aucun Classement Disponible 
    </div>
    <div
      v-for="d in DonneRating"
      id="UIActivitiesContainer_welcomeActivity"
      :key="d.id"
      class="UIActivitiesContainer_welcomeActivity">
      <div
        v-if="show"
        id="welcomeActivity"
        class="activityStream uiDefaultActivity">
        <div class="activityTimeLine pull-left">
          <div class="activityAvatar avatarCircle">
            <a>
              <img alt="Bienvenue sur votre nouvelle plateforme collaborative" src="/eXoSkin/skin/images/themes/default/social/skin/Activity/starterAvt.png">
            </a>
          </div>
        </div>
        <!--end activityTimeLine-->

        <div id="boxContainer" class="boxContainer">
          <div id="ContextBoxWelcomeActivity" class="uiBox contentBox">
            <div id="ActivityContextBoxWelcomeActivity">
              <div class="heading">
                
                <span class="arrowLeft"></span>
                <div class="author">
                  <a title="welcome to Ideation"><strong>Titre :</strong>{{ d.title }}</a>
                 
                  <p style="        margin-left: 92%;">
                    <a ><strong>{{ d.nb }}</strong></a> <v-icon color="yellow darken-2"> star </v-icon>
                  </p>
                </div>
              </div>
              <!--end heading-->

              <!-- Welcome content -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
    data(){
        return{
        DonneRating:[],
        alt:false,
        show:true,
        }
    },mounted(){
         axios
      .get('/portal/rest/fav/getallordered')
      .then(response => { this.DonneRating=response.data;
      
      if(this.DonneRating.length===0){
        this.alt=true;
        this.show=false;
      }  
      })
      .catch(error => {
        this.errored = true
      })
    },updated(){
           axios
      .get('/portal/rest/fav/getallordered')
      .then(response => { this.DonneRating=response.data;
      
      if(this.DonneRating.length===0){
        this.alt=true;
        this.show=false;
      }  
      })
      .catch(error => {
        this.errored = true
      }) 
    }
}
</script>

<style>
.crd{
         width: 73%;
    margin-left: 12%;
}
</style>

