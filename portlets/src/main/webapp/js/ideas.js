$ = $ || jQuery;
(function ($) {
    alert("here");
    var cat = new Vue({
        el: '#ideation',
        data: {
            ideations: [],
            title: "",
            description: "",
            successMessage: "",
            errorMessage: "",
            showSuccessMessage: false,
            showErrorMessage: false
        },
        methods: {
                deleteidea: function (index, idea) {
                    var self = this;
                    var $githubDiv = $("#ideation");
                    var urlCall = $githubDiv.jzURL("IdeationController.delete");

                $.ajax({
                    type: 'GET',
                    url: urlCall,
                    data: idea

                }).done(function () {
                    self.successMessage = "idea has been removed successfully.";
                    self.showSuccessMessage = true;
                }).fail(function () {
                    self.errorMessage = "Error when dropping idea. Please try again!";
                    self.showErrorMessage = true
                }).always(function () {
                    self.ideation.splice(index, 1);
                });

            },
            addidea: function () {
                var self = this;
                var $githubDiv = $("#ideation");
                var urlCall = $githubDiv.jzURL("IdeationController.add");
                $.ajax({
                    type: 'GET',
                    url: urlCall,
                    data: {
                        "title": self.title,
                        "description": self.description,

                    },

                }).done(function (data) {
                    self.successMessage = "idea has been added successfully.";
                    self.showSuccessMessage = true;
                }).fail(function () {
                    self.errorMessage = "Error when adding idea. Please try again!";
                    self.showErrorMessage = true;
                }).always(function (data) {
                    this.ideation.push(data);

                });

            },
        },
    });
})(jQuery)

alert("here");