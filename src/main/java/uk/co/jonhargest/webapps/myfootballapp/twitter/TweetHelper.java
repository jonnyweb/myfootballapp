package uk.co.jonhargest.webapps.myfootballapp.twitter;

import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.UrlEntity;
import uk.co.jonhargest.webapps.myfootballapp.bean.twitter.SimpleTweet;

import java.util.ArrayList;
import java.util.List;

public abstract class TweetHelper {

    private static final String URL_ENTITY = "<a href=\"%s\" target=\"_blank\">%s</a>";
    private static final String MENTION_ENTITY = "<a href=\"https://twitter/%s\" target=\"_blank\">@%s</a>";

    public static List<SimpleTweet> convertToSimpleTweets(List<Tweet> tweets) {
        List<SimpleTweet> simpleTweets = new ArrayList();

        for (Tweet tweet : tweets) {
            simpleTweets.add(convert(tweet));
        }

        return simpleTweets;
    }

    private static SimpleTweet convert(Tweet tweet) {
        SimpleTweet simple = new SimpleTweet();

        String text = tweet.getUnmodifiedText();
        text = TweetHelper.replaceUrls(text, tweet.getEntities().getUrls());
        text = TweetHelper.replaceMedia(text, tweet.getEntities().getMedia());
        text = TweetHelper.replaceMentions(text, tweet.getEntities().getMentions());

        simple.setId(tweet.getId());
        simple.setProfileImageUrl(tweet.getProfileImageUrl());
        simple.setRetweeted(tweet.isRetweeted());
        simple.setText(text);
        simple.setUser(tweet.getFromUser());

        return simple;
    }

    public static String replaceUrls(String tweet, List<UrlEntity> entities) {
        if(entities != null) {
            for (UrlEntity urlEntity : entities) {
                String url = String.format(URL_ENTITY, urlEntity.getExpandedUrl(), urlEntity.getDisplayUrl());
                tweet = replaceTextInTweet(tweet, urlEntity.getUrl(), url);
            }
        }

        return tweet;
    }

    public static String replaceMedia(String tweet, List<MediaEntity> entities) {
        if(entities != null) {
            for (MediaEntity mediaEntity : entities) {
                String url = String.format(URL_ENTITY, mediaEntity.getExpandedUrl(), mediaEntity.getUrl());
                tweet = replaceTextInTweet(tweet, mediaEntity.getUrl(), url);

            }
        }

        return tweet;
    }

    public static String replaceMentions(String tweet, List<MentionEntity> entities) {
        if(entities != null) {
            for (MentionEntity mentionEntity : entities) {
                String mention = String.format(MENTION_ENTITY, mentionEntity.getScreenName(), mentionEntity.getScreenName());
                tweet = replaceTextInTweet(tweet, "@" + mentionEntity.getScreenName(), mention);
            }
        }

        return tweet;
    }

    private static String replaceTextInTweet(String tweet, String replace, String formatted){
        return tweet.replaceAll("(?i)" + replace, formatted);
    }

}
