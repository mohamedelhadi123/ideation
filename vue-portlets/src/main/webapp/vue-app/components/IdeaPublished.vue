<template>
  <div class="crd">
    <div
      v-if="chargement"
      id="AjaxLoadingMask"
      class="ajaxLoadingMask clearfix"
      style="display: block; margin: auto; z-index: 2001; position: absolute; left: 624.5px; top: 395.278px;"
    >
      <a class="uiIconClose uiIconWhite pull-right"></a>
      <i class="loadingProgressBar pull-left"></i>
      <div class="loadingText">Chargement en cours...</div>
    </div>
    <div v-if="alt" class="alert alert-info">
      <i class="uiIconInfo"></i>Aucun Idée publiée
    </div>

    <div
      v-if="componet"
      id="UIActivitiesContainer_welcomeActivity"
      class="UIActivitiesContainer_welcomeActivity"
    >
      <div id="welcomeActivity" class="activityStream uiDefaultActivity">
        <div class="activityTimeLine pull-left"></div>
        <!--end activityTimeLine-->

        <div id="boxContainer" class="boxContainer">
          <div id="ContextBoxWelcomeActivity" class="uiBox contentBox">
            <div id="ActivityContextBoxWelcomeActivity">
              <div class="heading">
                <button class="btn-primary" @click="showMyIdeaPublished">Mes Idées</button>
                <button class="btn" @click="AllPublished">Tous les idées</button>
              </div>
              <!--end heading-->
              <div class="heading">
                <v-expansion-panel>
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
                      <strong>{{ d.title }}</strong>
                      <div class="dataInfor">
                        <span class="dateTime">
                          <i class="uiIconClock uiIconLightGray"></i>
                          &nbsp;le {{ d.createdTime }}
                        </span>
                      </div>
                    </div>
                    <v-card>
                      <v-card-text class="px-4 grey--text" style="    width: 95%">
                        <strong>Resumé :</strong>
                        {{ d.resume }}
                        <router-link :to="`/ideainfo/${d.id}`">... Lire la suite</router-link>
                        <p>
                          <br>

                          <a
                            href="#"
                            @click="GetUrlSpace(d.id)"
                            v-if="d.isProject===false"
                          >Visiter le projet</a>

                          <button
                            v-if="(userNameConnected===d.user && d.isProject!==false)"
                            class="btn btn-small btn-primary"
                            type="button"
                            :disabled="disable"
                            @click.prevent="CreateEspace(d.id,d.title,d.description,d.resume,d.explanation)"
                          >Créer une espace</button>

                          <button
                            v-if="d.isProject!==false"
                            class="btn btn-small"
                            @click="AffichDialog"
                          >Modifier</button>

                          <button
                            v-if="showbtn===true && d.isProject!==false"
                            class="btn btn-small"
                            @click.prevent="AddToArchived(d.id,d.title,d.description,d.resume,d.explanation)"
                          >Ajouter aux listes Archivée</button>
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
      donnes: [],
      dataspace: {},
      altDiag: false,
      datajson: {
        id: null,
        title: '',
        description: '',
        status: '',
        resume: '',
        explanation: '',
        isProject: null,
      },
      dataupdate: {
        id: null,

        title: '',
        description: '',
        status: '',
        resume: '',
        explanation: '',
      },
      linkToSpace: '',
      componet: true,
      alt: false,
      dialog: false,
      chargement: false,
      showbtn: false,
      disable: false,
      urlToSpace: '',
      userNameConnected: eXo.env.portal.userName,
    };
  },
  mounted() {
    this.AllPublished();
    if (eXo.env.portal.userName === 'root') {
      this.showbtn = true;
    }
  },
  methods: {
    showMyIdeaPublished() {
      this.donnes = null;
      axios
        .get('/portal/rest/idea/AllIdeaByUserAndStatus/PUBLISHED')
        .then((response) => {
          this.donnes = response.data;
          if (this.donnes.length === 0) {
            this.alt = true;
            this.componet = false;
          }
        })
        .catch((error) => {
          console.log(error);
          this.errored = true;
        });
    },
    AllPublished() {
      axios
        .get('/portal/rest/idea/all/PUBLISHED')
        .then((response) => {
          this.donnes = response.data;
          if (this.donnes.length === 0) {
            this.alt = true;
            this.componet = false;
          }
        })
        .catch((error) => {
          console.log(error);
          this.errored = true;
        });
    },
    AddToArchived: function(id, title, description, resume, explanation) {
      this.datajson.status = 'ARCHIVED';
      this.datajson.createdTime = new Date();
      this.datajson.description = description;
      this.datajson.title = title;
      this.datajson.explanation = explanation;
      this.datajson.resume = resume;
      this.datajson.id = id;
      axios
        .put('/portal/rest/idea/update', this.datajson, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((response) => {
          this.donnes = this.donnes.filter((d) => d.id !== id);
          if (this.donnes.length === 0) {
            this.alt = true;
            this.componet = false;
          }
        });
    },
    CreateEspace(id, title, description, resume, explanation, isProject) {
      this.chargement = true;
      this.disable = true;

      axios
        .post(`/portal/rest/idea/addSapace/${id}`, this.dataspace, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((test) => {
          const baseUrl = window.location.origin;
          this.urlToSpace = `${baseUrl}/portal/intranet/spaces`;
          window.location.href = this.urlToSpace;
        });
    },
    GetUrlSpace(title) {
      axios
        .get('/portal/rest/idea/geturl/' + title)
        .then((response) => {
          const baseUrl = window.location.origin;
          const urlSpace = `${baseUrl}` + response.data.url;

          window.location.href = urlSpace;
        })
        .catch((error) => {
          console.log(error);
          this.errored = true;
        });
    },
    UpdateIdea: function(id, title, description, resume, explanation) {
      this.dataupdate.status = 'PUBLISHED';
      this.dataupdate.description = description;
      this.dataupdate.title = title;
      this.dataupdate.id = id;
      this.dataupdate.explanation = explanation;
      this.dataupdate.resume = resume;
      axios
        .put('/portal/rest/idea/update', this.dataupdate, {
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

