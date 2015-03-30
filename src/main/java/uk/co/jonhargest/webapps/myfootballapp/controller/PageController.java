package uk.co.jonhargest.webapps.myfootballapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.League;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.LeagueTable;
import uk.co.jonhargest.webapps.myfootballapp.footballdata.FootballDataRestTemplate;

import static uk.co.jonhargest.webapps.myfootballapp.footballdata.FootballDataHelper.createTeamList;

@Controller
public class PageController {

    private static final String PREMIER_LEAGUE_URL = "http://api.football-data.org/alpha/soccerseasons/354/teams";
    private static final String PREMIER_LEAGUE_TABLE_URL = "http://api.football-data.org/alpha/soccerseasons/354/leagueTable";

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private FootballDataRestTemplate footballDataRest;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        RestTemplate rest = new RestTemplate();

        League league = footballDataRest.getForObject(PREMIER_LEAGUE_URL, League.class);
        LeagueTable table = footballDataRest.getForObject(PREMIER_LEAGUE_TABLE_URL, LeagueTable.class);

        model.addAttribute("teams", createTeamList(league, table));
        model.addAttribute("twitterAuthd", (connectionRepository.findPrimaryConnection(Twitter.class) == null));

        return "app";
    }

}
