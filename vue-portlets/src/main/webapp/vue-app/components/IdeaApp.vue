<template>
  <div id="IdeaApp">
    <tabs />
    <router-view />
  </div>
</template>


 <script>

     import tabs from './tabs.vue';
     export default {
         components: {
             tabs
         },


     }
  </script>
   