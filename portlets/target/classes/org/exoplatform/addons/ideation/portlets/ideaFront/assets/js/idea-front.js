require( ["SHARED/jquery", "ideaFrontControllers"], function ( $,  ideaFrontControllers)
{
    $( document ).ready(function() {
        var ideaFrontAppRoot = $('#ideaFront');
        var ideaFrontApp = angular.module('ideaFrontApp', ['ngFileUpload']);
        try {
            ideaFrontApp.controller('ideaFrontCtrl', ideaFrontControllers);
            angular.bootstrap(ideaFrontAppRoot, ['ideaFrontApp']);
        } catch(e) {
            console.log(e);
        }

    });

});
//