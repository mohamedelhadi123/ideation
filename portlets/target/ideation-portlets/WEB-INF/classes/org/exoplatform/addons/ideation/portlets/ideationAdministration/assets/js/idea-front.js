require( ["SHARED/jquery", "ideationAdministrationControllers"], function ( $,  ideationAdministrationControllers)
{
    $( document ).ready(function() {
        var ideaFrontAppRoot = $('#ideaAdmin');
        var ideaFrontApp = angular.module('ideaFrontApp', ['ngFileUpload']);
        try {
            ideaFrontApp.controller('ideaFrontCtrl', ideationAdministrationControllers);
            angular.bootstrap(ideaFrontAppRoot, ['ideaFrontApp']);
        } catch(e) {
            console.log(e);
        }

    });

});
