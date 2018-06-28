define("ideaFrontControllers", [ "SHARED/jquery", "SHARED/juzu-ajax"], function($, jz)  {
    var ideaFrontCtrl = function($scope, $q, $timeout, $http,Upload) {
        var ideaFrontContainer = $('#ideaFront');
        var deferred = $q.defer();
        $scope.showAlert = false;
        $scope.newIdea={

        };
        $scope.totrate =[];
        $scope.drafts = [];
        $scope.files=[];
        $scope.ideaToDelete=null;
        $scope.attachements=[];
        $scope.ideas=[];
        $scope.idea=null;
        $scope.rate=null;
        $scope.currentUser="";
        $scope.comments = [];
        $scope.newComment = {
        };
        $scope.rates=[];
        $scope.newRate = {
        };
        $scope.Favs =[];
        $scope.likes =[];
        $scope.newFav={
        };
        $scope.newLike = {

        };
        $scope.showList = true;
        $scope.showForm = false;
        $scope.newideaId= {
        };

        $scope.ratings = [{number : $scope.result}];

        $scope.getStars = function(rating) {
            // Get the value
            var val = parseFloat(rating);
            // Turn value into number/100
            var size = val/5*100;
            return size + '%';
        }


        $scope.loadresults = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getResults')
            }).then(function successCallback(data) {
                $scope.ratings = data.data;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        }


        $scope.loadBundle = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getBundle') + "&locale=" + eXo.env.portal.language
            }).then(function successCallback(data) {
                $scope.i18n = data.data;
                deferred.resolve(data);
                /*$scope.setResultMessage(data, "success");*/
                $scope.showAlert = false;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.deleteIdea = function (idea) {
            $http({
                data: idea,
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeaFrontController.deleteIdea')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };




        $scope.deleteFavorite = function (idea) {
            $http({
                data: idea,
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeaFrontController.removeFavorite')
            }).then(function successCallback(data) {
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.loadDraftedIdeas = function(status) {
            var url="";
            if(status!=null){
                url=url+ "&status="+status;
            }
            $http({
                method : 'GET',
                url : ideaFrontContainer.jzURL('IdeaFrontController.getDraftedIdeasOfCurrentUser')+url
            }).then(function successCallback(data) {
                $scope.ideas = data.data;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.hide = function (idea) {
            $('#edit-container_' + idea.id).hide();
        };

        $scope.Edit = function (idea) {
            $('#edit-container_' + idea.id).show();
            $scope.id = idea.id;
            $scope.status = idea.status;
            $scope.title = idea.title;
            $scope.description = idea.description;
            $scope.createdBy = idea.createdBy;
            $scope.createdTime = idea.createdTime;
            $scope.commentText = idea.commentText;
            $scope.fav = idea.fav;
            $scope.like = idea.like;
        };


        $('.create-idea').on('click', function (e) {
            e.preventDefault();
            $('.create-idea-container').slideToggle();
            $('.create-idea-input').focus();
        });


        $('.cancel').on('click', function (e) {
            e.preventDefault();
            $('.create-idea-container').slideToggle();
        });


        $scope.comment = function (idea) {
            $scope.id = idea.id;
            document.getElementById("comtext_"+ idea.id).value = "";
            document.getElementById("newcomment_"+ idea.id).value = "";
            $('#new-comment-container_' + idea.id).slideToggle();
            $('#newcomment_'+ idea.id).css("display", "block");

        };
        $scope.updateIdea = function(idea) {
            $http({
                data : {
                    "id": idea.id,
                    "title": idea.title,
                    "createdBy":idea.createdBy,
                    "createdTime":idea.createdTime,
                    "description": idea.description,
                    "status": idea.status,
                    "fav": idea.fav,
                    "like":idea.like,
                    "commentText":idea.commentText
                },
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.updateIdea')
            }).then(function successCallback(data) {
                $('#edit-container'+ idea.id).hide();
                $scope.loadIdeas();
                 $scope.setResultMessage($scope.i18n.thanks, "success");
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
            };

        $scope.updateRate = function(rate) {
            $http({
                data : rate,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.updateRate')
            }).then(function successCallback(data) {
                $scope.loadRates();
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });

        };



        $scope.loadData = function () {

            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getData')
            }).then(function successCallback(data) {
                $scope.messageForm = data.data.messageForm;

                deferred.resolve(data);


                $('#ideaFront').css('display', 'block');

                $timeout(function () {
                }, 10);

            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.uploadFiles = function(file, errFiles) {
            $scope.f = file;
            $scope.errFile = errFiles && errFiles[0];
            if (file) {
                file.upload = Upload.upload({
                    url: ideaFrontContainer.jzURL('IdeaFrontController.uploadFile'),
                    data: {requestId: $scope.newIdea,
                        file: file}
                });
                file.upload.then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                        $scope.attachements = $scope.loadAttachments($scope.newIdea);
                        file.progress =undefined;
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                        evt.loaded / evt.total));
                });
            }
        };



        $scope.saveComment = function(idea) {

            $scope.newComment.ideaId = idea.id;
            $scope.newComment.commentText = idea.commentText;
            $scope.newComment.author = idea.createdBy;
            $scope.newComment.createdTime = idea.createdTime;
            $scope.newComment.posterAvatar = idea.posterAvatar;
            console.log($scope.newComment.posterAvatar);

            idea.numcomments+=1;

            $scope.newComment.numcomments = idea.numcomments;
            $scope.showAlert = false;
            document.getElementById("newcomment_"+ idea.id).value = "";

            $('#new-comment-container_' + idea.id).slideToggle();

            $http({
                data : $scope.newComment,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.saveComment')
            }).then(function successCallback(data) {
                $scope.loadComments();
                $scope.showAlert = false;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });

        };

        $scope.loadAttachments = function () {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeaFrontController.getAttachements')
            }).then(function successCallback(data) {
                $scope.attachements = data.data;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };



        $scope.deleteAttachement = function(fileName) {
            $http({
                url : ideaFrontContainer.jzURL('IdeaFrontController.deleteFile')+"&fileName="+fileName
            }).then(function successCallback(data) {
                $scope.attachements = $scope.loadAttachments($scope.idea);

            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };


        $scope.geti18n = function(id) {

            for(var propt in $scope.i18n){
                if(id==propt) return $scope.i18n[propt];
            }
            return id;
        };

        $scope.saveIdea = function(idea) {
            $scope.newIdea.status = "PUBLISHED" ;
            $scope.showAlert = false;
            document.getElementById("txtTitle").value = "";
            document.getElementById("txtDescription").value = "";
            //$scope.slideUp();
            $http({
                data : $scope.newIdea,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.saveIdea')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
                //  $scope.setResultMessage($scope.i18n.thanks, "success");
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
            });

        };

        $scope.saveDraft = function(idea) {
            $scope.newIdea.status = "DRAFTED" ;
            document.getElementById("txtTitle").value = "";
            document.getElementById("txtDescription").value = "";
            $http({
                data : $scope.newIdea,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.SaveDraft')
            }).then(function successCallback(data) {
                $scope.loadDraftIdeas();
                //  $scope.setResultMessage($scope.i18n.thanks, "success");
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
            });

        };

       $scope.saveRate = function (idea) {
           $scope.newRate.ideaId = idea.id;
           $scope.newRate.numRate = idea.numRate;


           $http({
               data : $scope.newRate,
               method : 'POST',
               headers : {
                   'Content-Type' : 'application/json'
               },
               url : ideaFrontContainer.jzURL('IdeaFrontController.saveRate')
           }).then(function successCallback(data) {
               $scope.loadRates();
           }, function errorCallback(data) {
               //  $scope.setResultMessage($scope.i18n.defaultError, "error");
           });

       };






        $scope.saveFavorite = function(idea) {
            $scope.newFav.ideaId = idea.id;

            if(idea.fav == true){
                idea.fav = false;
            }else if(idea.fav == false){
                idea.fav = true ;
            }
            if(idea.fav == true){
                idea.numfav +=1;
            }else if(idea.fav == false){
                idea.numfav -=1;
            }
            $scope.newFav.numfav = idea.numfav;

            $scope.newFav.fav = idea.fav;

            $http({
                data : $scope.newFav,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.SaveFavorite')
            }).then(function successCallback(data) {
                $scope.LoadFavorite();
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };



        $scope.saveLike = function(idea) {
            $scope.newLike.ideaId = idea.id;
            if(idea.like == true){
                idea.like = false;
            }else if(idea.like == false){
                idea.like = true ;
            }
            if(idea.like == true){
                idea.numlike +=1;
            }else if(idea.like == false){
                idea.numlike -=1;
            }
            $scope.newLike.numlike = idea.numlike;
            $scope.newLike.like = idea.like;

            $http({
                data : $scope.newLike,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.saveLIke')
            }).then(function successCallback(data) {
                $scope.loadLikes();
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };


        $scope.loadContext = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getContext')
            }).then(function successCallback(data) {
                $scope.currentUser = data.data.currentUser;
                $scope.currentUserAvatar = data.data.currentUserAvatar;
                $scope.currentUserName = data.data.currentUserName;
                deferred.resolve(data);
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };


        $scope.loadRates = function () {

            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getRates')
            }).then(function successCallback(data) {
                $scope.rates = data.data;
            }, function errorCallback(data) {

            });

        };




        $scope.loadComments = function () {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeaFrontController.getComments')
            }).then(function successCallback(data) {
                $scope.comments = data.data;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };





        $scope.loadIdeas = function (idea) {
            // $scope.isfav = idea.fav;
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getIdeas')
            }).then(function successCallback(data) {
                $scope.ideas = data.data;

            }, function errorCallback(data) {

            });

        };



        $scope.loadIdea = function (idea) {
            var url = "";
            url=url+ "&idea="+idea.id;
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getIdea')+url
            }).then(function successCallback(data) {
                $scope.ideas = data.data;

            }, function errorCallback(data) {

            });

        };






        $scope.LoadFavorite = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getFavorite')
            }).then(function successCallback(data) {
                $scope.favs = data.data;

            }, function errorCallback(data) {

            });

        };



        $scope.loadDraftIdeas = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getDraftIdeas')
            }).then(function successCallback(data) {
                $scope.ideas = data.data;
            }, function errorCallback(data) {

            });

        };

        $scope.loadLikes = function (idea) {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getLikes')
            }).then(function successCallback(data) {
                $scope.likes = data.data;
            }, function errorCallback(data) {
            });
        };

        $scope.LoadFavorite();
        $scope.loadComments();
        $scope.loadRates();
        $('#ideaFront').css('display', 'block');
        $scope.loadIdeas();




        var header = document.getElementById("myDIV");
        var btns = header.getElementsByClassName("bouton");
        for (var i = 0; i < btns.length; i++) {
            btns[i].addEventListener("click", function() {
                var current = document.getElementsByClassName("filter-active");
                current[0].className = current[0].className.replace(" filter-active", "");
                this.className += " filter-active";
            });
        }

        };
    return ideaFrontCtrl;



});