<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex>
          <v-menu offset-y>
            <v-btn
              slot="activator"
              flat
              class="depressed white large blue--text">
              <v-icon left>expand_more</v-icon>
              <span>Createur</span>
            </v-btn>
            <v-list>
              <v-list-tile
                v-for="link in links1"
                :key="link.text"
                router
                :to="link.route">
                <v-list-tile-title >{{ link.text }}</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-flex>

        <v-flex>
          <v-menu offset-y>
            <v-btn
              slot="activator"
              flat
              outline
              color="white">
              <v-icon left>expand_more</v-icon>
              <span>Types des Idees</span>
            </v-btn>
            <v-list>
              <v-list-tile
                v-for="link in links2"
                :key="link.text"
                router
                :to="link.route">
                <v-list-tile-title>{{ link.text }}</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
    export default {
        data: () => ({
        links1: [
            {  text: 'Idee favorite', route: '/ideafav' },
            {  text: 'Ajouter Idee', route: '/createidea' },
            {  text: 'Afficher mon Idee Publier', route: '/ideapubsh' },
        ],
        links2: [
            {  text: 'Redige', route: '/rediger' },
            {  text: 'Publier', route: '/publier' },
            {  text: 'Archive', route: '/archive' },
        ]
    }),
    }
</script>