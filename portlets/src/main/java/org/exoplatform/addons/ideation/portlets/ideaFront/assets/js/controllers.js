define("ideaFrontControllers", [ "SHARED/jquery", "SHARED/juzu-ajax"], function($, jz)  {
    var ideaFrontCtrl = function($scope, $q, $timeout, $http,Upload) {
        var ideaFrontContainer = $('#ideaFront');
        var deferred = $q.defer();
        $scope.showAlert = false;
        $scope.newIdea={

        };
        $scope.ideasToVAlidateFilter = "active";

        $scope.drafts = [];
        $scope.files=[];
        $scope.ideaToDelete=null;
        $scope.attachements=[];
        $scope.ideas=[];
        $scope.idea=null;
        $scope.currentUser="";
        $scope.comments = [];
        $scope.newComment = {
        };
        $scope.Favs =[];
        $scope.newFav={
        };
        $scope.showList = true;
        $scope.showForm = false;
        $scope.newideaId= {
        };

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
                url: ideaFrontContainer.jzURL('IdeaFrontController.deleteFavorite')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
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
        $scope.activeEditForm =  function(idea) {
            $('.new-idea').on('click', function (e) {
                e.preventDefault();
                $('.new-idea-container').slideToggle();
                $('.new-idea-input').focus();
            });
            $scope.id = idea.id;
            $scope.title = idea.title;
            $scope.description = idea.description;
            $scope.createdBy = idea.createdBy;
            $scope.createdTime = idea.createdTime;

        };

        $scope.CommentForm =  function(idea) {
            $('.new-comment').on('click', function (e) {
                e.preventDefault();
                $('.new-comment-container').slideToggle();
                $('.new-comment-input').focus();

            });
        };
        $scope.updateIdea = function(status) {
            $http({
                data : {
                    "id": $scope.id,
                    "title": $scope.title,
                    "createdBy":$scope.createdBy,
                    "createdTime":$scope.createdTime,
                    "description": $scope.description,
                    "status": $scope.status,
                },
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.updateIdea')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
                //  $scope.setResultMessage($scope.i18n.thanks, "success");
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
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
            $scope.showAlert = false;
            var $this = $(this);

            $scope.newComment.ideaId= idea.id;

            //$scope.newComment.createdTime= Date.now();
            $scope.newComment.author=$scope.currentUser;

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
                data: $scope.newIdea,
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeaFrontController.getAttachements')
            }).then(function successCallback(data) {
                $scope.attachements = data.data;
//                $timeout(function() {
//                    $scope.setResultMessage(data, "success")
//                }, 1000);
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.saveIdea = function(Idea) {
            $scope.newIdea.status = "PUBLISHED" ;
            $scope.showAlert = false;
            var modal = document.getElementById('simpleModal');
            modal.style.display = 'none';
            document.getElementById("txtTitle").value = "";
            document.getElementById("txtDescription").value = "";
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

        $scope.saveDraft = function() {
            $scope.newIdea.status = "DRAFTED" ;
            var modal = document.getElementById('simpleModal');
            modal.style.display = 'none';
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


        $scope.Cancel = function () {
            var modal = document.getElementById('simpleModal');
            modal.style.display = 'none';
        };

        $scope.saveFavorite = function(idea) {
            $scope.newFav.ideaId = idea.id;
            $http({
                data : $scope.newFav,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.saveFavorite')
            }).then(function successCallback(data) {
                //  $scope.setResultMessage($scope.i18n.thanks, "success");
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




        $scope.loadComments = function () {
            $http({
                data: $scope.newIdea,
                method: 'POST',
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


        $scope.loadIdeas = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getIdeas')
            }).then(function successCallback(data) {
                $scope.ideas = data.data;

            }, function errorCallback(data) {
                $scope.loadIdeas();

            });

        };

        $scope.loadDraftIdeas = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getDraftIdeas')
            }).then(function successCallback(data) {
                $scope.drafts = data.data;
            }, function errorCallback(data) {
                $scope.loadDraftIdeas();

            });

        };


        $scope.loadDraftIdeas = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getDraftIdeas')
            }).then(function successCallback(data) {
                $scope.drafts = data.data;
            }, function errorCallback(data) {
                $scope.loadDraftIdeas();

            });

        };



        $scope.loadFavorites = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeaFrontController.getFavoritesByUserId')
            }).then(function successCallback(data) {
                $scope.Favs = data.data;
            }, function errorCallback(data) {
                $scope.loadDraftIdeas();

            });

        };
        $scope.fav = function($scope) {
            $scope.item = {
                star: false
            };
        }


        //   $scope.loadBundle();
        //  $scope.loadData();
        $('#ideaFront').css('display', 'block');
        $scope.loadIdeas();
        $scope.loadDraftedIdeas();
        // Get modal element
        var modal = document.getElementById('simpleModal');
// Get open modal button
        var modalBtn = document.getElementById('modalBtn');

// Get close button
        var closeBtn = document.getElementsByClassName('closeBtn')[0];

// Listen for open click
        modalBtn.addEventListener('click', openModal);

// Listen for close click
        closeBtn.addEventListener('click', closeModal);
// Listen for outside click
        window.addEventListener('click', outsideClick);

// Function to open modal
        function openModal() {
            modal.style.display = 'block';
            modal.style.top = '-120 px';
        }

// Function to close modal
        function closeModal() {
            modal.style.display = 'none';
        }

// Function to close modal if outside click
        function outsideClick(e) {
            if (e.target == modal) {
                modal.style.display = 'none';
            }
        }


    };
    return ideaFrontCtrl;


});