<template>
  <div class="crd">
    <div v-if="alt" class="alert alert-info">
      <i class="uiIconInfo"></i>Aucun Idée Favori
    </div>
    <div
      v-if="compont"
      id="UIActivitiesContainer_welcomeActivity"
      class="UIActivitiesContainer_welcomeActivity"
    >
      <div id="welcomeActivity" class="activityStream uiDefaultActivity">
        <div class="activityTimeLine pull-left"></div>
        <!--end activityTimeLine-->

        <div id="boxContainer" class="boxContainer">
          <div id="ContextBoxWelcomeActivity" class="uiBox contentBox">
            <div id="ActivityContextBoxWelcomeActivity">
              <!--end heading-->
              <div class="heading">
                <v-expansion-panel>
                  <v-expansion-panel-content v-for="d in donneedatafav" :key="d.id">
                    <div slot="header" class="py-1">
                      <strong>Titre :</strong>
                      {{ d.title }}
                      <div class="dataInfor">
                        <span class="dateTime">
                          <i class="uiIconClock uiIconLightGray"></i>
                          &nbsp;le {{ d.createdTime }}
                        </span>
                      </div>
                    </div>

                    <v-card>
                      <v-card-text class="px-4 grey--text" style="    width: 95%">
                        <div>
                          <strong>Resumé :</strong>
                          {{ d.resume }}
                        </div>
                        <br>
                        <p>
                          <router-link :to="`/ideainfo/${d.id}`">Lire la suite ...</router-link>
                        </p>
                      </v-card-text>
                    </v-card>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </div>
              <!-- Welcome content -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      alt: false,
      donneedatafav: [],
      compont: true,
    };
  },
  mounted() {
    axios
      .get('/portal/rest/fav/getfavbyuser')
      .then((response) => {
        if (response.data.length === 0) {
          this.alt = true;
          this.compont = false;
        } else {
          for (let i = 0; i < response.data.length; i++) {
            axios
              .get(`/portal/rest/idea/getideabyid/${response.data[i].id_Ideaf}`)
              .then((response) => {
                this.donneedatafav.push(response.data);
              })
              .catch((error) => {
                this.errored = true;
              });
          }
        }
      })
      .catch((error) => {
        this.errored = true;
      });
  },
};
</script>
<style>
.crd {
  width: 80%;
  margin-left: 9%;
}
</style>
