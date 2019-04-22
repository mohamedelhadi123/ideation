    
<template>

  <v-dialog max-width="600px" >
    
    <template >
    <v-btn flat slot="activator" class="red">Add New Project</v-btn>
    </template>
     <v-card >
       <v-container >
        <v-card-title>
          <span class="headline">Créer une Idée</span>
        </v-card-title>
        <v-card-text >
          <v-form
    ref="form"
    v-model="valid"
    lazy-validation
    class="textet"
  >
  <v-text-field
      v-model="titre"
      :counter="10"
      :rules="nameRules"
      label="Name"
      required
    ></v-text-field>
      <v-textarea class="dem"  v-model="description" placeholder="Entrer votre commentaire ..."  /> 
                    <v-flex md10>
                   
                      <select
                        class="select_style"
                        :options="option"
                        placeholder="Categories" >
                        <option v-for="option in options" :key="option.id">
                          {{ option.status }}
                        </option>
                      </select>
                  
                    </v-flex>
          </v-form>
        
        </v-card-text>
        </v-container>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red" flat @click="reset">reset</v-btn>
          <v-btn color="blue darken-1" flat slot="activator">Annule</v-btn>
          <v-btn color="blue darken-1" flat @click="this.dialog = false">Enregistrer</v-btn>
        </v-card-actions>
           

      </v-card>
     
  </v-dialog>


</template>

<script>
export default {
  data() {
    return { titre:null,
             description:null,
            dialog: false,
            options: [
            {status:"publiée",id:"1"},
            {status:"archiver",id:"2"},
            {status:"drafted",id:"3"}
            ]

    }
  },
   methods: {
    reset() {
       this.titre= null;
       this.description=null;
       console.log("ok");
       
    },
    annuler(){
      this.dialog=false;
     
      console.log(this.dialog);
    }
   }
}
</script>
<style>

.textet{
  width: 72%;
 

}
</style>



