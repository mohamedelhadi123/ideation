<template>
  <div>
    <v-stepper labels>
      <v-stepper-header>
        <v-stepper-step step="3" complete>
          {{ idea.titre }}     
        </v-stepper-step>
        <v-stepper-step step="3" complete>
          <router-link to="/ideaclass">voir classement</router-link>
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
    <v-card>
      <v-card-text class="tet">
        <div>{{ idea.description }}  </div>
      </v-card-text>
      <v-card-text>
        <div class="font-weight-bold">créer le  {{ idea.waket }} par {{ idea.createur }}</div>
      </v-card-text>
      <v-stepper labels>
        <v-stepper-header>
          <v-stepper-step step="3" complete>
            <v-layout row wrap>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  color="pink">
                  {{ idea.fav }}
                  <v-icon>favorite</v-icon>
                </v-btn>
              </v-flex>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  color="deep-orange">
                  {{ idea.like }}
                  <v-icon>thumb_up</v-icon>
                </v-btn>
              </v-flex>
              <v-flex xs12 sm3>
                <v-btn
                  flat
                  icon
                  color="deep-orange">
                  {{ idea.dislike }}
                  <v-icon>thumb_down</v-icon>
                </v-btn>
              </v-flex>
            </v-layout>
          </v-stepper-step>
        </v-stepper-header>
      </v-stepper>
      <ul id="example-1">
        <li v-for="item in items" :key="item.id">
          <v-card-text class="tet">
            <div class="font-weight-bold">{{ item.name }}</div>
            <div>{{ item.message }}</div>
          </v-card-text>
        </li>
      </ul>
      <v-card class="tet">
        <v-textarea
          v-model="text"
          class="dem"
          placeholder="Entrer votre commentaire ..." /> 
      
    
      
        <v-btn icon color="deep-orange"><i class="fas fa-arrow-alt-circle-right"></i> </v-btn>
      </v-card>
    </v-card>
  </div>
</template>
<script>
export default {
    data(){
        return{
          no:window.location.href.substring(61,window.location.href.length),
     items: [
      { message: 'bravoo !!' ,id:"1",name:"Ahmed"},
      { message: 'good frd .. ;) ',id:"2",name:'Mohamed' }
    ],
    idea:{titre:"Idea 1",createur:"alioua",description:"Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!",waket:"1-01-2018",like:'12',dislike:'14',fav:'3'}

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


