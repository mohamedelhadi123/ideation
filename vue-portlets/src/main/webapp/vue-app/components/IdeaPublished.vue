<template>
<v-container>

  <div>

   <v-stepper labels>
    <v-stepper-header>
      <v-stepper-step step="3" complete>
         
     <v-checkbox v-model="checkbox1" :label="'Mes Idées'" color="indigo"></v-checkbox>
      </v-stepper-step>
    </v-stepper-header>
   </v-stepper>
      <v-expansion-panel >
        <v-expansion-panel-content v-for="project in projects" :key="project.title">
          <div slot="header" class="py-1">{{ project.title }}</div>
          <v-card>
            <v-card-text class="px-4 grey--text">
              <div class="font-weight-bold">Due by {{ project.due }}</div>
              <div>{{ project.content }}  </div>
              <router-link to="/ideainfo">Lire la suite ...</router-link>

            </v-card-text>
            
          </v-card>
         
        </v-expansion-panel-content>
      </v-expansion-panel>
    
  </div>
</v-container>

</template>

<script>
export default {
    
  data() {
    return {
     dialog: false,

       checkbox1: false,
        
      projects: [
        { title: 'Design a new website', person: 'The Net Ninja', due: '1st Jan 2019', status: 'ongoing', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Code up the homepage', person: 'Chun Li', due: '10th Jan 2019', status: 'complete', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Design video thumbnails', person: 'Ryu', due: '20th Dec 2018', status: 'complete', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
        { title: 'Create a community forum', person: 'Gouken', due: '20th Oct 2018', status: 'overdue', content: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt consequuntur eos eligendi illum minima adipisci deleniti, dicta mollitia enim explicabo fugiat quidem ducimus praesentium voluptates porro molestias non sequi animi!'},
      ]
    }
  },
 
}
</script>
<style>

</style>


