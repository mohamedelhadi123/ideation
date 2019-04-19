<template>
  <v-container class="my-5 backgroundTop" elevation-20>
    <v-stepper
      v-model="e1"
      flat
      class="text-xs-center ma-3 elevation-20"
      height="650px">
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          step="1"
          color="blue">
          <h5 class="blue--text .headline">Ajouter Idea</h5>
        </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card
            class="mb-5"
            height="350px">
            <v-card-text>
              <v-form ref="form" class="px-3">
                <v-container>
                  <v-flex md12 lg12>
                    <v-text-field
                      v-model="libelle"
                      label="Titre"
                      prepend-icon="info"
                      :rules="inputRules"
                      ma-5
                      pa-5 />
                  </v-flex>
                  
                  <v-flex md12 lg12>
                    <v-textarea
                      v-model="nbre_pers"
                      label="Description"
                      prepend-icon="edit"
                      ma-5
                      pa-5 />
                  </v-flex>
                  <!--  @click="submit"  -->
                </v-container>
              </v-form>
            </v-card-text>
           
            <v-select :items="items" label="Standard" > </v-select>
            
          </v-card>
           
          <v-btn
            depressed
            large
            color="#1867c0"
            class="white--text"
            @click="e1 = 2">
            Enregistrer
          </v-btn>
         <v-btn
            depressed
            large
            color="#FF0000"
            class="white--text"
            @click="e1 = 2">
            Reset
          </v-btn>
          <v-btn
            v-btn
            outline
            large
            color="blue darken-3"
            class="indigo--text">
            Cancel
          </v-btn>
        </v-stepper-content>
        <v-stepper-content step="2">
          <v-card
            class="mb-5"
            height="350px" />

         
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
  
</template>

<script>
 
    import 'vue-material/dist/vue-material.min.css'
    import 'vue-material/dist/theme/default.css'
    import vSelect from 'vue-select'
    import UploadButton from 'vuetify-upload-button';
    import TuiEditor from 'vue-tui-editor';
    Vue.use(TuiEditor)
    Vue.component('v-select', vSelect)
    Vue.component('upload-btn',UploadButton)
    export default {
       data: () => ({
    
 
    coworkers: [
  
    ]
  })
                 
              
    }
</script>
<style>
    .para_border{
        border: 4px dotted lightslategrey;
    }
      .backgroundTop{
        background:linear-gradient(to bottom, #1867c0, #5CBBF6);
    }
 
</style>