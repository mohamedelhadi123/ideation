$ = $ || jQuery;

$(function () {
    var ideationCtrl = function ($scope, $q, $timeout, $http, $filter, PagerService, Upload) {
        var $githubDiv = $('#ideation');
        var deferred = $q.defer();

        $scope.ideas = [];
        $scope.showAlert = false;


        $scope.loadIdeas = function () {
            $http({
                method: 'GET',
                url: $githubDiv.jzURL('IdeationController.getAllIdeas')
            }).then(function successCallback(data) {
                $scope.ideas = data.data;
                $scope.showAlert = false;
            });

        };
        $('.new-idea-form').on('submit', function (e) {
            e.preventDefault();
            var modal = document.getElementById('simpleModal');
            modal.style.display = 'none';
            var $this = $(this);
            var urlcall = $githubDiv.jzURL("IdeationController.add");
            var title = $('.new-idea-input').val();
            var description = $('.description-input').val();
            if (title.trim().length > 0 && description.trim().length > 0)
                $.ajax({
                    url: urlcall,
                    type: 'POST',
                    data: {
                        title: title,
                        description: description
                    },
                    success: function (results) {
                        $('.new-idea-input').val('');
                        $('.description-input').val('');
                        $('.ideas').append('<div class="feedItem">' + '<span class="profIcon">Nichola Tex</span>\n' +
                            '<div class="content scroll scroll2">' + '<strong>' + title + '</strong>' + '<br>'
                            + description + ' </div>' + '</div>');
                    },
                });
        });
        // Get modal element
        var modal = document.getElementById('simpleModal');
// Get open modal button
        var modalBtn = document.getElementById('modalBtn');
        var modalBtn1 = document.getElementById('modalBtn1');

// Get close button
        var closeBtn = document.getElementsByClassName('closeBtn')[0];

// Listen for open click
        modalBtn.addEventListener('click', openModal);
        modalBtn1.addEventListener('click', openModal);

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

    return ideationCtrl;

});