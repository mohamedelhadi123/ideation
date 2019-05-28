<template>
  <div class="crd">
    <div v-if="alt" class="alert alert-info">
      <i class="uiIconInfo"></i>Aucune idée trouvée
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
                  <div v-if="alt" class="alert alert-warning">
                    <i class="uiIconWarning"></i>Aucun Idée redigée .
                  </div>
                  <v-expansion-panel-content v-for="d in donnes" :key="d.id">
                    <!-------------------------------- Dialog for Update ------------------------------------->

                    <div
                      v-if="altDiag"
                      class="UIPopupWindow uiPopup UIDragObject NormalStyle"
                      style="width: 875px; position: relative; top: auto; left: auto; margin: 0 auto 20px; z-index: 1; max-width: 100%;"
                    >
                      <div class="popupHeader ClearFix">
                        <a class="uiIconClose pull-right" aria-hidden="true" @click="DontShow"></a>
                        <span class="PopupTitle popupTitle">Modifier Votre Idée</span>
                      </div>
                      <div class="PopupContent popupContent">
                        <div class="form-horizontal resizable">
                          <div class="popupContent">
                            <div class="control-group">
                              <label for="label" class="control-label">Titre:</label>
                              <div class="controls">
                                <input
                                  style="width: 76%;"
                                  id="label"
                                  v-model="d.title"
                                  type="text"
                                  name="label"
                                >
                              </div>
                            </div>
                            <div class="control-group">
                              <label for="label" class="control-label">L'objectif:</label>
                              <div class="controls">
                                <input style="width: 76%;" v-model="d.explanation" type="text">
                              </div>
                            </div>
                            <div class="control-group">
                              <label for="label" class="control-label">Resumé:</label>
                              <div class="controls">
                                <textarea style="width: 76%;" v-model="d.resume" type="text"></textarea>
                              </div>
                            </div>
                            <div class="control-group">
                              <label for="label" class="control-label">Discription:</label>
                              <div class="controls">
                                <textarea
                                  id="label"
                                  v-model="d.description"
                                  type="text"
                                  name="label"
                                  style="width: 76%;"
                                ></textarea>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="uiAction uiActionBorder">
                          <button
                            class="btn"
                            @click.prevent="UpdateIdea(d.id,d.title,d.description,d.resume,d.explanation)"
                          >Enregistrer</button>
                          <button class="btn" type="button" @click="DontShow">Annuler</button>
                        </div>
                      </div>
                      <span class="uiIconResize pull-right uiIconLightGray"></span>
                    </div>
                    <!--------------------------------------- END -------------------------------------------->
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

                        <button class="btn btn-small" @click="AffichDialog">Modifier</button>
                        <button class="btn btn-small" @click.prevent="DeleteIdea(d.id)">Supprimer</button>
                        <button
                          class="btn btn-small btn-primary"
                          @click.prevent="AddToPublished(d.id,d.title,d.description,d.resume,d.explanation)"
                        >Publier</button>
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
      donnes: [],
      altDiag: false,
      alt: false,
      compont: true,
      dialog: false,
      datajson: {
        id: null,
        title: '',
        description: '',
        status: '',
        resume: '',
        explanation: '',
      },
    };
  },

  mounted() {
    axios
      .get('/portal/rest/idea/AllIdeaByUserAndStatus/DRAFT')
      .then((response) => {
        this.donnes = response.data;
        if (this.donnes.length === 0) {
          this.alt = true;
          this.compont = false;
        }
      })
      .catch((error) => {
        this.errored = true;
      });
  },

  methods: {
    DeleteIdea: function(event) {
      axios
        .delete(`/portal/rest/idea/delete/${event}`, {
          headers: {
            'Content-Type': 'application/json',
          },
        })
        .then((response) => {
          this.donnes = this.donnes.filter((d) => d.id !== event);
          if (this.donnes.length === 0) {
            this.alt = true;
            this.compont = false;
          }
        })
        .catch((error) => {
          this.errored = true;
        });
    },
    AddToPublished: function(id, title, description, resume, explanation) {
      this.datajson.status = 'PUBLISHED';
      this.datajson.explanation = explanation;
      this.datajson.resume = resume;
      this.datajson.createdTime = new Date();
      this.datajson.description = description;
      this.datajson.title = title;
      this.datajson.id = id;
      axios
        .put('/portal/rest/idea/update', this.datajson, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((response) => {
          this.$router.push('/');
        });
    },
    UpdateIdea: function(id, title, description, resume, explanation) {
      this.datajson.status = 'DRAFET';
      this.datajson.description = description;
      this.datajson.title = title;
      this.datajson.id = id;
      this.datajson.explanation = explanation;
      this.datajson.resume = resume;
      axios
        .put('/portal/rest/idea/update', this.datajson, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((response) => {
          this.altDiag = false;
          axios
            .get('/portal/rest/idea/AllIdeaByUserAndStatus/DRAFT')
            .then((response) => {
              this.donnes = response.data;
            })
            .catch((error) => {
              this.errored = true;
            });
        });
    },
    reset() {
      this.titre = null;
      this.description = null;
    },
    DontShow() {
      this.altDiag = false;
    },
    annuler() {
      this.dialog = false;
    },
    AffichDialog() {
      this.altDiag = true;
    },
  },
};
</script>

<style>
.crd {
  width: 80%;
  margin-left: 9%;
}
.cmt {
  width: 101%;
}
</style>

