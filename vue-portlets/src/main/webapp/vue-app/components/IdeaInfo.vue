<template>
  <div class="crd">
    <div id="UIActivitiesContainer_welcomeActivity" class="UIActivitiesContainer_welcomeActivity">
      <div id="welcomeActivity" class="activityStream uiDefaultActivity">
        <div class="activityTimeLine pull-left">
          <div class="activityAvatar avatarCircle">
            <a>
              <img
                alt="Bienvenue sur votre nouvelle plateforme collaborative"
                src="/eXoSkin/skin/images/themes/default/social/skin/Activity/starterAvt.png"
              >
            </a>
          </div>
        </div>
        <!--end activityTimeLine-->

        <div id="boxContainer" class="boxContainer">
          <div id="ContextBoxWelcomeActivity" class="uiBox contentBox">
            <div id="ActivityContextBoxWelcomeActivity">
              <div class="heading">
                <span class="arrowLeft"></span>
                <div class="author">
                  <a title="welcome to Ideation">Cette Idée est Céer par {{ this.donnesidea.user }}</a>
                </div>
                <div class="dataInfor">
                  <span class="dateTime">
                    <i class="uiIconClock uiIconLightGray"></i>
                    &nbsp;le {{ this.donnesidea.createdTime }}
                  </span>
                </div>
              </div>
              <!--end heading-->

              <!-- Welcome content -->
              <div class="description clearfix">
                <p>
                  <strong>Titre :</strong>
                  {{ this.donnesidea.title }}
                </p>
                <p>
                  <strong>Objectife :</strong>
                  {{ this.donnesidea.explanation }}
                </p>
                <p>
                  <strong>Description :</strong>
                  {{ this.donnesidea.description }}
                </p>

                <p>
                  <strong>Resume :</strong>
                  {{ this.donnesidea.resume }}
                </p>
                <p>
                  <strong>Theme :</strong>
                </p>
                <ul>
                  <li v-for="th in themeForIdea" :key="th.id">{{ th.subject }}</li>
                </ul>
                <div>
                  <div class="actionBar clearfix linkActivityIcon">
                    <div>
                      <ul class="pull-right statusAction kudoContainer">
                        <li>
                          <a
                            rel="tooltip"
                            data-placement="bottom"
                            data-original-title="Commentaire"
                          >
                            <i class="far fa-comment" :style="Scomment" @click="ShowComment"></i>
                            &nbsp;{{ this.NbComment }}
                          </a>
                        </li>
                        <li>
                          <a rel="tooltip" data-placement="bottom" data-original-title="j'aime pas">
                            <i class="far fa-thumbs-down" :style="Disl" @click="addDisLike"></i>
                            &nbsp;{{ this.dislike }}
                          </a>
                        </li>

                        <li>
                          <a rel="tooltip" data-placement="bottom" data-original-title="J'aime">
                            <i class="far fa-thumbs-up" :style="Li" @click="addLike"></i>
                            &nbsp;{{ this.like }}
                          </a>
                        </li>
                        <li>
                          <a rel="tooltip" data-placement="bottom" data-original-title="Favori">
                            <i class="far fa-heart" :style="favColor" @click="addFavori"></i>
                            &nbsp;{{ this.fav }}
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <br>

                <div
                  v-for="d in commentDonne"
                  id="CommentBlockBound246557"
                  :key="d.id"
                  class="commentBox"
                >
                  <div class="commentList">
                    <div class="CommentBlock">
                      <div class="commentItem commentItemLast">
                        <div class="commentRight">
                          <div class="author">
                            <a href="#">{{ d.user }}</a>
                          </div>
                          <div class="contentComment">
                            <p>{{ d.commentText }}</p>
                            <div class="dataInfor">
                              <span class="dateTime">
                                <i class="uiIconClock uiIconLightGray"></i>
                                &nbsp;{{ d.createdTime }}
                              </span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div v-if="alt" id="CommentBlockBound246557" class="commentBox">
                  <div class="commentList">
                    <div class="CommentBlock">
                      <div class="commentItem commentItemLast">
                        <div class="commentRight">
                          <textarea v-model="commentTextt" class="cmt" name="input-7-1" auto-grow></textarea>

                          <button class="btn-primary" @click="addComment">Enregistrer</button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br>
                </div>
              </div>
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
      datal: {id: null, status: '', id_idear: this.$route.params.id},
      datajsonfav: {
        id_Ideaf: this.$route.params.id,
      },
      datajson: {
        user: '',
        commentText: '',
        id_ideac: this.$route.params.id,
      },
      alt: false,
      Li: {
        color: '',
      },
      Disl: {
        color: '',
      },
      favColor: {
        color: '',
      },
      Scomment: {
        color: '',
      },
      commentDonne: [],
      idFavS: null,
      donnesidea: [],
      dislike: null,
      idDislike: null,
      idLike: null,
      like: null,
      fav: null,

      NbComment: null,
      commentTextt: '',
      donnes: [],
      themebyId: [],
      themeForIdea: [],
    };
  },
  mounted() {
    axios.get(`/portal/rest/idea/getideabyid/${this.$route.params.id}`).then((response) => {
      this.donnesidea = response.data;
    });

    axios.get(`/portal/rest/themeidea/getallthemebyidea/${this.$route.params.id}`).then((response) => {
      this.themebyId = response.data;
      for (let i = 0; i < this.themebyId.length; i++) {
        axios.get(`/portal/rest/theme/getthemebyid/${this.themebyId[i].id_themei}`).then((response) => {
          this.themeForIdea.push(response.data);
        });
      }
    });
  },
  updated() {
    this.verifyRating();
    axios
      .get(`/portal/rest/rating/getratingbyidea/${this.$route.params.id}/DISLIKE`)
      .then((response) => {
        this.dislike = response.data.length;
        if (this.dislike.length !== 0) {
          this.idDislike = response.data[0].id;
        }
      })
      .catch((error) => {
        this.errored = true;
      });
    axios
      .get(`/portal/rest/fav/getbyidea/${this.$route.params.id}`)
      .then((response) => {
        this.fav = response.data.length;
        if (this.fav.length !== 0) {
          this.idFavS = response.data[0].id;
        }
      })
      .catch((error) => {
        this.errored = true;
      });
    axios
      .get(`/portal/rest/rating/getratingbyidea/${this.$route.params.id}/LIKE`)
      .then((response) => {
        this.like = response.data.length;
        if (this.like.length !== 0) {
          this.idLike = response.data[0].id;
        }
      })
      .catch((error) => {
        this.errored = true;
      });

    axios
      .get(`/portal/rest/comment/allcommentbyidea/${this.$route.params.id}`)
      .then((response) => {
        this.commentDonne = response.data;
        this.NbComment = this.commentDonne.length;
        if (this.NbComment !== 0) {
          this.Scomment.color = 'blue';
        }
      })
      .catch((error) => {
        this.errored = true;
      });
  },
  methods: {
    ShowComment() {
      this.alt = true;
    },
    addComment() {
      this.datajson.commentText = this.commentTextt;
      axios
        .post('/portal/rest/comment/add', this.datajson, {
          headers: {
            'Content-type': 'application/json',
          },
        })
        .then((response) => {
          this.commentDonne.push(response.data);
        })
        .catch((e) => {
          this.errors.push(e);
        });
      this.commentTextt = '';
      this.alt = false;
    },
    verifyRating() {
      axios
        .get(`/portal/rest/rating/verif/${this.$route.params.id}`)
        .then((response) => {
          if (response.data[0].status === 'LIKE') {
            this.Li.color = 'blue';
          } else if (response.data[0].status === 'DISLIKE') {
            this.Disl.color = 'blue';
          }
        })
        .catch((error) => {
          this.errored = true;
        });

      axios
        .get(`/portal/rest/fav/verif/${this.$route.params.id}`)
        .then((response) => {
          if (response.data.length === 0) {
            this.favColor.color = '';
          } else {
            this.favColor.color = 'red';
          }
        })
        .catch((error) => {
          this.errored = true;
        });
    },
    addLike() {
      this.datal.status = 'LIKE';

      if (this.Li.color === 'blue') {
        axios
          .delete(`/portal/rest/rating/delete/${this.idLike}`, {
            headers: {
              'Content-Type': 'application/json',
            },
          })
          .then((response) => {
            this.Li.color = '';
          });
      } else if (this.Disl.color === 'blue') {
        this.datal.status = 'LIKE';
        this.datal.id = this.idDislike;
        axios
          .put('/portal/rest/rating/updaterating', this.datal, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            this.Disl.color = '';
            this.Li.color = 'blue';
          });
      } else {
        axios
          .post('/portal/rest/rating/addrating', this.datal, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            this.Li.color = 'blue';
          })
          .catch((e) => {
            this.errors.push(e);
          });
      }
    },
    addDisLike() {
      this.datal.status = 'DISLIKE';

      if (this.Disl.color === 'blue') {
        axios
          .delete(`/portal/rest/rating/delete/${this.idDislike}`, {
            headers: {
              'Content-Type': 'application/json',
            },
          })
          .then((response) => {
            this.Disl.color = '';
          });
      } else if (this.Li.color === 'blue') {
        this.datal.status = 'DISLIKE';
        this.datal.id = this.idLike;
        axios
          .put('/portal/rest/rating/updaterating', this.datal, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            this.Disl.color = 'blue';
            this.Li.color = '';
          });
      } else {
        axios
          .post('/portal/rest/rating/addrating', this.datal, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            this.Disl.color = 'blue';
          })
          .catch((e) => {
            this.errors.push(e);
          });
      }
    },
    addFavori() {
      if (this.favColor.color === 'red') {
        axios
          .delete(`/portal/rest/fav/delete/${this.idFavS}`, {
            headers: {
              'Content-Type': 'application/json',
            },
          })
          .then((response) => {
            this.favColor.color = '';
          });
      } else {
        axios
          .post('/portal/rest/fav/addfav', this.datajsonfav, {
            headers: {
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            this.favColor.color = 'red';
          })
          .catch((e) => {
            this.errors.push(e);
          });
      }
    },
  },
};
</script>

<style>
.crd {
  width: 82%;
  margin-left: 4%;
}
.cmt {
  width: 101%;
}
</style>

