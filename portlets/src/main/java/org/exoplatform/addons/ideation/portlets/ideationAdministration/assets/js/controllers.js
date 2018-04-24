define("ideationAdministrationControllers", [ "SHARED/jquery", "SHARED/juzu-ajax"], function($, jz)  {
    let ideaFrontCtrl = function($scope, $q, $timeout, $http,Upload) {
        let ideaFrontContainer = $('#ideaAdmin');
        let deferred = $q.defer();
        $scope.showAlert = false;
        $scope.files=[];
        $scope.ideaToDelete=null;
        $scope.attachements=[];
        $scope.ideas=[];
        $scope.currentUser="";
        $scope.newIdea={
            id:0

        };
        $scope.template = { name: 'template',
            url: 'template'};

        $scope.templateUdata = { name: 'templateUdata',
            url: 'templateUdata'};

        $scope.loadBundle = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeationAdministrationController.getBundle') + "&locale=" + eXo.env.portal.language
            }).then(function successCallback(data) {
                $scope.i18n = data.data;
                deferred.resolve(data);
                /*$scope.setResultMessage(data, "success");*/
                $scope.showAlert = false;
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };


        $scope.updateIdea = function() {
            $http({
                data : {
                    "id": $scope.id,
                    "title": $scope.title,
                    "createdBy":$scope.createdBy,
                    "createdTime":$scope.createdTime,
                    "description": $scope.description,
                },
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                url : ideaFrontContainer.jzURL('IdeationAdministrationController.updateIdea')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
                //  $scope.setResultMessage($scope.i18n.thanks, "success");
            }, function errorCallback(data) {
                //  $scope.setResultMessage($scope.i18n.defaultError, "error");
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

        $scope.deleteIdea = function (idea) {
            $http({
                data: idea,
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: ideaFrontContainer.jzURL('IdeationAdministrationController.deleteIdea')
            }).then(function successCallback(data) {
                $scope.loadIdeas();
                $scope.setResultMessage($scope.i18n.ideadelete, "success");
            }, function errorCallback(data) {
                $scope.setResultMessage($scope.i18n.defaultError, "error");
            });
        };

        $scope.loadData = function () {

            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeationAdministrationController.getData')
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



        $scope.loadIdeas = function () {
            $http({
                method: 'GET',
                url: ideaFrontContainer.jzURL('IdeationAdministrationController.getIdeas')
            }).then(function successCallback(data) {
                $scope.ideas = data.data;
            }, function errorCallback(data) {
                $scope.loadIdeas();

            });

        };

        //   $scope.loadBundle();
        //  $scope.loadData();
        $('#ideaAdmin').css('display', 'block');
        $scope.loadIdeas();



    };
    return ideaFrontCtrl;


});