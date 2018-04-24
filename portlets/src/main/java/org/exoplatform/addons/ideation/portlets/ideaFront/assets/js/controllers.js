define("ideaFrontControllers", [ "SHARED/jquery", "SHARED/juzu-ajax"], function($, jz)  {
    let ideaFrontCtrl = function($scope, $q, $timeout, $http,Upload) {
        let ideaFrontContainer = $('#ideaFront');
        let deferred = $q.defer();
        $scope.showAlert = false;
        $scope.newIdea={
            id:0

        };
        $scope.files=[];
        $scope.ideaToDelete=null;
        $scope.attachements=[];
        $scope.ideas=[];
        $scope.idea=null;
        $scope.currentUser="";
        $scope.comments = [];
        $scope.newComment = {
            id:0
        };
        $scope.newideaId= {
            id:null
        };
        $scope.user = {state: 'CA'
        };
        $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
            'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
            'WY').split(' ').map(function(state) {
            return {abbrev: state};
        });
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


        $scope.saveComment = function() {
                $scope.showAlert = false;
            var $this = $(this);

            var ideaId = $this.attr('data-idea-id');
                console.log(ideaId);
                $scope.newComment.ideaId= ideaId;
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

        $scope.saveIdea = function() {

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
            $http({
                data : $scope.newIdea,
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeaFrontController.saveDraft')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
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


        //   $scope.loadBundle();
        //  $scope.loadData();
        $('#ideaFront').css('display', 'block');
        $scope.loadIdeas();

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