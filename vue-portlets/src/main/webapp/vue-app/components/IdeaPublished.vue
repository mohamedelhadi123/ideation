<template>
  <v-container>
    <div>
      <v-stepper labels>
        <v-stepper-header>
          <v-stepper-step step="3" complete>
               <v-flex xs12 sm3>
            <v-btn flat icon color="green">
              <v-icon>cached</v-icon>
                Voir mes idées

            </v-btn>
          </v-flex>
          </v-stepper-step>
        </v-stepper-header>
      </v-stepper>
      <v-expansion-panel>
        <v-expansion-panel-content v-for="d in donnes" :key="d.id">
          <div slot="header" class="py-1">{{ d.title }}</div>
          <v-card>
            <v-card-text class="px-4 grey--text">
             <div>{{ d.description }}  </div>

              <div class="font-weight-bold">créer par {{d.user}} le {{ d.createdTime.year }}</div>
              <a v-bind:href="'http://127.0.0.1:8080/portal/intranet/ideation/#/ideainfo?id='+ d.id">Lire la suite ...</a>
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
     dialog: false,

        
      projects: [
        { title: 'Design a new website', person: 'The Net Ninja', due: '1st Jan 2019', status: 'ongoing', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Code up the homepage', person: 'Chun Li', due: '10th Jan 2019', status: 'complete', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Design video thumbnails', person: 'Ryu', due: '20th Dec 2018', status: 'complete', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Create a community forum', person: 'Gouken', due: '20th Oct 2018', status: 'overdue', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
      ]
    }
  },  mounted () { 
    
    axios
      .get('http://127.0.0.1:8080/portal/rest/idea/json')
      .then(response => { this.donnes=response.data;
        
      })
      .catch(error => {
        console.log(error)
        this.errored = true
      })
  }
     

}
</script>
<style>

</style>


