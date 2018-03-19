$ = $ || jQuery;
$(function () {
        $('.new-idea-form').on('submit', function (e) {
            e.preventDefault();
            let $this = $(this);
           // var $githubDiv = $("#ideation");
            //var urlcall = $githubDiv.jzURL("IdeationController.add");
            let IdeaTitle = $('.new-idea-input').val();
            if (IdeaTitle.trim().length > 0)
                $.ajax({
                    url: '/portal/intranet/ideation?portal:windowState=normal&amp;portal:cacheLevel=PAGE&amp;portal:portletMode=view&amp;portal:componentId=ac278d26-bfa7-4dda-91e3-97a010b0699b&amp;resourcestate=JBPNS_rO0ABXcuAAdqdXp1Lm9wAAAAAQAWSWRlYXRpb25Db250cm9sbGVyLmFkZAAHX19FT0ZfXw**&amp;portal:type=resource' ,
                    type: 'POST',
                    data: {
                        name: IdeaTitle,
                    },
                    success: function (results) {
                            $('.new-idea-input').val('');
                            $('.ideas').prepend('<div class="col-md-offset-5">' + IdeaTitle + "</div>");
                        },
                });

        });

    });





