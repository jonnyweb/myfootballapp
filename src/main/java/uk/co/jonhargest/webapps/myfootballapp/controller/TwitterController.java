package uk.co.jonhargest.webapps.myfootballapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.Club;
import uk.co.jonhargest.webapps.myfootballapp.bean.twitter.SimpleTweet;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static uk.co.jonhargest.webapps.myfootballapp.twitter.TweetHelper.convertToSimpleTweets;

@Controller
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @RequestMapping(
            value = "/retweet/{id}",
            method = GET
    )
    @ResponseBody
    public ResponseEntity<String> retweet(@PathVariable("id") long id) {
        ResponseEntity<String> response;

        if(twitter.isAuthorized()) {
            twitter.timelineOperations().retweet(id);
            response = new ResponseEntity<String>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }


    @RequestMapping(
            value = "/tweets/{code}",
            method = GET
    )
    @ResponseBody
    public List<SimpleTweet> getTweets(@PathVariable("code") String clubCode) {
        Club club = Club.valueOf(clubCode);
        List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(club.getTwitterProfile(), 20);
        return convertToSimpleTweets(tweets);
    }

}

