<template>
<v-layout class="bt">
    <v-dialog v-model="dialog" persistent max-width="500px">
      <v-btn  slot="activator"  color="#0000FF" dark><i class="fas fa-plus-circle"></i> créer une idée </v-btn>
      <v-card>
          <v-container class="backgroundTop" >
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
      placeholder="Titre" 
      required
      prepend-icon="edit"
    ></v-text-field>
   
      <v-textarea   prepend-icon="edit" v-model="description" placeholder="Description ..."  /> 
  
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
        
        <v-card-actions >
          <v-spacer></v-spacer>
           <v-btn color="red" flat @click="reset">reset</v-btn>

          <v-btn color="blue darken-1" flat @click="annuler">Close</v-btn>
          <v-btn color="blue darken-1" flat @click="annuler">Save</v-btn>
        </v-card-actions>
       
      </v-card>
    </v-dialog>
</v-layout>
 
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
.bt{
justify-content: flex-end;
width: 95%;
}

 .backgroundTop{
        background:linear-gradient(to bottom, #1867c0, #5CBBF6);
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
    }
    .select_style{
      margin-left: 12%;
      width: 108%;
    }
</style>