<template>
  <div>
    <div v-if="alt" class="alert alert-info" style="    margin-left: 11%;
    width: 74%;">
      <i class="uiIconClose" @click="DontShow"></i>
      <i class="uiIconInfo"></i>Remplire tout les champs
    </div>
    <div
      v-if="dialogs"
      class="UIPopupWindow uiPopup UIDragObject NormalStyle"
      style="width: 785px; position: relative; top: auto; left: auto; margin: 0 auto 20px; z-index: 1; max-width: 100%;"
    >
      <div class="popupHeader ClearFix">
        <a class="uiIconClose pull-right" aria-hidden="true" @click="CancelDialogs"></a>
        <span class="PopupTitle popupTitle">Ajouter un Theme</span>
      </div>
      <div class="PopupContent popupContent">
        <div class="form-horizontal resizable">
          <div class="popupContent">
            <div class="control-group">
              <label for="label" class="control-label">Theme:</label>
              <div class="controls">
                <input id="label" v-model="themeInput" type="text" name="label">
              </div>
            </div>
          </div>
        </div>
        <div class="uiAction uiActionBorder">
          <button class="btn" @click="AddTheme">Enregistrer</button>
          <button class="btn" type="button" @click="CancelDialogs">Annuler</button>
        </div>
      </div>
      <span class="uiIconResize pull-right uiIconLightGray"></span>
    </div>
    <div class="aff">
      <div>
        <strong>Créer une Idée :</strong>
        <br>

        <div class="activityCont">
          <div id="AccountInputSet-tab" class="tab-pane fade in active">
            <div class="uiTabContent resizable">
              <div id="AccountInputSet" class="UIFormInputSet AccountInputSet">
                <div class="form-horizontal">
                  <div class="control-group">
                    <label class="control-label">Titre:</label>
                    <div class="controls">
                      <input v-model="titre" type="text"> *
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Objectif:</label>
                    <div class="controls">
                      <input v-model="objectiv" type="text"> *
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Description:</label>
                    <div class="controls">
                      <textarea v-model="description" type="text"></textarea> *
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Resumé:</label>
                    <div class="controls">
                      <textarea v-model="resume" type="text"></textarea> *
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="username">Theme:</label>
                    <div class="controls">
                      <select v-model="selectedTheme" @change="AddToThemes">
                        <option disabled value>Choisissez</option>

                        <option
                          v-for="s in subject"
                          :key="s.subject"
                          :value="s.subject"
                          selected
                        >{{ s.subject }}</option>
                      </select>
                      <button class="btn" @click="ShowDialogs">Ajouter un theme</button>
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <div v-for="t in themes" :key="t" class="uiMention">
                        {{ t }}
                        <i class="uiIconClose" @click.prevent="DeleteTheme(t)"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <br>
          <br>
          <div class="btn-button">
            <button class="btn" @click="reset">Réinitialiser</button>
            <button class="btn-primary" @click="addIdea">Enregistrer</button>
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
      themeInput: '',
      dialogs: false,
      titre: '',
      selectedTheme: '',
      description: '',
      resume: '',
      objectiv: '',
      th: {
        id: null,
        subject: '',
        user: '',
      },

      datajson: {
        title: '',
        description: '',
        status: 'DRAFT',
        resume: '',
        explanation: '',
      },
      detTheme: [],
      themes: [],
      subject: [{id: null, subject: ''}],
      dataTheme: {
        subject: '',
      },
    };
  },
  mounted() {
    axios
      .get('/portal/rest/theme/getalltheme/')
      .then((response) => {
        this.subject = response.data;
      })
      .catch((error) => {
        this.errored = true;
      });
  },
  methods: {
    DeleteTheme(t) {
      this.themes = this.themes.filter((d) => d !== t);
      for (let i = 0; i < this.detTheme.length; i++) {
        if (this.detTheme[i].subject === t) {
          this.subject.push(this.detTheme[i]);
          this.detTheme = this.detTheme.filter((d) => d.subject !== t);
        }
      }
    },
    AddToThemes() {
      if (this.selectedTheme === '') {
        console.log('error ');
      } else {
        this.themes.push(this.selectedTheme);

        for (let i = 0; i < this.subject.length; i++) {
          if (this.subject[i].subject === this.selectedTheme) {
            this.detTheme.push(this.subject[i]);
          }
        }
        this.subject = this.subject.filter((d) => d.subject !== this.selectedTheme);
      }
    },
    addIdea() {
      this.datajson.title = this.titre;
      this.datajson.description = this.description;
      this.datajson.resume = this.resume;
      this.datajson.explanation = this.objectiv;

      if (this.titre === '' || this.description === '' || this.resume === '' || this.objectiv === '' || this.themes.length === 0) {
        this.alt = true;
      }

      if (this.alt === false) {
        axios
          .post('/portal/rest/idea/addIdea', this.datajson, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            for (let i = 0; i < this.detTheme.length; i++) {
              const datates = {
                id_ideat: response.data.id,
                id_themei: this.detTheme[i].id,
              };

              axios.post('/portal/rest/themeidea/addthemeidea', datates, {
                headers: {
                  'Content-type': 'application/json',
                },
              });
            }

            this.reset();
            this.$router.push('/IdeaDrafted');
          });
      }
    },
    reset() {
      this.titre = null;
      this.description = null;
      this.objectiv = '';
      this.resume = '';
    },
    DontShow() {
      this.alt = false;
    },
    ShowDialogs() {
      this.dialogs = true;
    },
    CancelDialogs() {
      this.dialogs = false;
    },
    AddTheme() {
      this.dataTheme.subject = this.themeInput;
      axios
        .post('/portal/rest/theme/addtheme', this.dataTheme, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((response) => {
          this.subject.push(response.data);
          this.dialogs = false;
          this.themeInput = '';
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
};
</script>

<style>
.aff {
  width: 98%;
  margin-left: 21%;
}
.lab-add {
  margin-left: 12%;
}
.btn-button {
  margin-left: 16%;
}
</style>


