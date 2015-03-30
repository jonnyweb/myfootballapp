package uk.co.jonhargest.webapps.myfootballapp.twitter;

import com.sun.javafx.TempState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.twitter.api.*;
import uk.co.jonhargest.webapps.myfootballapp.bean.twitter.SimpleTweet;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TweetHelperTest {

    @Test
    public void shouldConvertTweets() {
        // Given
        Long id = 123481295791L;
        String imageUrl = "imageUrl";
        String text = "text";
        String user = "user";
        boolean retweeted = true;

        Tweet t = givenTweet(id, imageUrl, text, user, retweeted);

        List<Tweet> tweets = new ArrayList();
        tweets.add(t);

        // When
        List<SimpleTweet> result = TweetHelper.convertToSimpleTweets(tweets);

        // Then
        SimpleTweet r = result.get(0);
        assertThat(r.getId(), is(Long.toString(id)));
        assertThat(r.getProfileImageUrl(), is(imageUrl));
        assertThat(r.getText(), is(text));
        assertThat(r.getUser(), is(user));
        assertThat(r.isRetweeted(), is(retweeted));
    }

    @Test
    public void shouldConvertUrls() {
        // Given
        String tweet = "some text http://google.com tweet";
        UrlEntity entity = new UrlEntity("google.com", "http://www.google.com", "http://google.com", new int[]{0, 1});

        List<UrlEntity> entities = new ArrayList();
        entities.add(entity);

        // When
        String result = TweetHelper.replaceUrls(tweet, entities);

        // Then
        assertThat(result, is("some text <a href=\"http://www.google.com\" target=\"_blank\">google.com</a> tweet"));
    }

    @Test
    public void shouldConvertMentions() {
        // Given
        String tweet = "some text @ChelseaFC tweet";
        MentionEntity entity = new MentionEntity(123L, "ChelseaFC", "Chelsea F.C.", new int[]{0, 1});

        List<MentionEntity> entities = new ArrayList();
        entities.add(entity);

        // When
        String result = TweetHelper.replaceMentions(tweet, entities);

        // Then
        assertThat(result, is("some text <a href=\"https://twitter/ChelseaFC\" target=\"_blank\">@ChelseaFC</a> tweet"));
    }

    @Test
    public void shouldConvertMedia() {
        // Given
        String tweet = "some text twitter.com/some/url tweet";
        MediaEntity entity = mock(MediaEntity.class);

        given(entity.getUrl()).willReturn("twitter.com/some/url");
        given(entity.getExpandedUrl()).willReturn("http://twitter.com/some/url");

        List<MediaEntity> entities = new ArrayList();
        entities.add(entity);

        // When
        String result = TweetHelper.replaceMedia(tweet, entities);

        // Then
        assertThat(result, is("some text <a href=\"http://twitter.com/some/url\" target=\"_blank\">twitter.com/some/url</a> tweet"));
    }


    private Tweet givenTweet(Long id, String imageUrl, String text, String user, boolean retweeted) {
        Tweet t = mock(Tweet.class);
        given(t.getId()).willReturn(id);
        given(t.getProfileImageUrl()).willReturn(imageUrl);
        given(t.getUnmodifiedText()).willReturn(text);
        given(t.getFromUser()).willReturn(user);
        given(t.isRetweeted()).willReturn(retweeted);

        Entities e = mock(Entities.class);
        given(t.getEntities()).willReturn(e);

        given(e.getMentions()).willReturn(null);
        given(e.getMedia()).willReturn(null);
        given(e.getUrls()).willReturn(null);

        return t;
    }

}
