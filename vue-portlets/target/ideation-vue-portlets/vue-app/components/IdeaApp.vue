<template>
 
  <div id="IdeaApp">

     <tabs/>
    
        <router-view />
    </div>
</template>


 <script>
//import NavApp from './NavApp.vue'
import tabs from './tabs.vue'
export default {
       components:{tabs},
  
 
  }
  </script>
  <style ></style>
   