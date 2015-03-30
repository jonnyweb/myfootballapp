var app = function() {
    var _self = this;
    var tweetTemplate = '<div class="tweet row"><div class="profile col-xs-2"><img src="PROFILE"/></div><div class="text col-xs-7">TEXT</div><div class="col-xs-3">RETWEET</div>';
    var retweetTemplate = '<a data-id="TWEETID" class="btn btn-RETWEETED retweet">TEXT</a>';

    function initSlick() {
        $('#teamCrests').slick({
            infinite: true,
            slidesToShow: 8,
            slidesToScroll: 4
        });
    }

    function initToggle() {
        $('#teamCrests').find('a').bind('click', function() {
            var self = $(this).parent();
            var code = self.attr('data-id');
            var current = $('#teams .selected');
            var selected = $('#team_' + code);

            current.fadeOut({
                duration: 400,
                complete: function(){
                    toggleTeamData(current, selected);
                }
            });

            getTweets(code);
        });
    }

    function toggleTeamData(curr, sel){
        curr.removeClass('selected');
        sel.addClass('selected').fadeIn(400);
    }

    function formatTweets(tweets){
        var el = $('.selected').find('.tweets');
        el.html('');

        for(var i = 0; i < tweets.length; i++) {
            var tweet = tweets[i];
            var auth = $('#twitter-auth');

            var retweet = retweetTemplate.replace('TWEETID', tweet.id);
            retweet = retweet.replace('RETWEETED', (tweet.retweeted) ? 'success disabled' : 'primary');
            retweet = retweet.replace('TEXT', (tweet.retweeted) ? 'Retweeted' : 'Retweet');

            var text = tweetTemplate.replace('PROFILE', tweet.profileImageUrl);
            var text = text.replace('TEXT', tweet.text);
            var text = text.replace('RETWEET', retweet);

            el.append(text);
        }

        el.find('a.retweet').bind('click', function(){
            var self = $(this);
            var text = self.html();

            if(auth.length > 0){
                auth.find('form').submit();
            } else {
                self.html('...');

                $.ajax({
                    url: '/twitter/retweet/' + self.attr('data-id'),
                    method: 'GET',
                    success: function() {
                        self.removeClass('btn-primary').addClass('btn-success disabled').html(text);
                    },
                    error: function() {
                        self.removeClass('btn-primary').addClass('btn-danger').html(text);
                    }
                });
            }
        });
    }

    function getTweets(code) {
        $.ajax({
            url: '/twitter/tweets/' + code,
            method: 'GET',
            dataType: 'json',
            success: formatTweets
        });
    }

    return {
        init: function() {
            initSlick();
            initToggle();
        }
    }

}();
$(document).ready(app.init());