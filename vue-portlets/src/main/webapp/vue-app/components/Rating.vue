<template>
  <v-layout row class="my-5 styletocard">
    <v-flex xs12 sm8 offset-sm1>
      <v-card>
        <div class="altm">
        <div class="alert alert-error" v-if="alt" >
    <i class="uiIconError"></i>Aucun classement Disponible pour le moment !!
</div>
        </div>
       <v-list two-line>
          <template v-for="(idea, index) in DonneRating">
            <v-list-tile
              :key="idea.id"
              avatar
              ripple
              @click="toggle(index)"
            >
              <v-list-tile-content>
                <v-list-tile-title>{{ idea.title }}</v-list-tile-title>
                <v-list-tile-sub-title class="text--primary"> Créer par {{ idea.user }}</v-list-tile-sub-title>
            
              </v-list-tile-content>
            <div class="rat">
           {{idea.nb}}
           </div>
                <v-icon
                
                  color="yellow darken-2"
                >
                 star
                </v-icon>

            </v-list-tile>
            <v-divider
              v-if="index + 1 < ideas.length"
              :key="index"
            ></v-divider>
          </template>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>
<script>
import axios from 'axios';

  export default {
    data () {
      return {
        DonneRating:[],
        alt:false,
        selected: [2],
         ideas: [
        { titre: 'The Net Ninja', user: 'Web developer',date :"01-03-2019" },
        { titre: 'Ryu', user: 'Graphic designer' ,date :"01-03-2019"},
        { titre: 'Chun Li', user: 'Web developer' ,date :"01-03-2019" },
        { titre: 'Gouken', user: 'Social media maverick' ,date :"01-03-2019"  },
        { titre: 'Yoshi', user: 'Sales guru' ,date :"01-03-2019"}
      ]
        
      }
    },mounted(){
   axios
      .get('/portal/rest/fav/getallordered')
      .then(response => { this.DonneRating=response.data;
      if(this.DonneRating.length===0){
        this.alt=true;
      }  
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
    },

    methods: {
      toggle (index) {
        const i = this.selected.indexOf(index)

        if (i > -1) {
          this.selected.splice(i, 1)
        } else {
          this.selected.push(index)
        }
      }
      
    }
  }
</script>
<style>
.styletocard{
  width: 143%;
  margin-left: -9%;
}
.rat{
      margin-top: 1%;

}
.altm{
      width: 102%;

}
</style>






