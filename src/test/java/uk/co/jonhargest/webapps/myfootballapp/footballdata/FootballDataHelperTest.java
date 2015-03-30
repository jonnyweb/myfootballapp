package uk.co.jonhargest.webapps.myfootballapp.footballdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.League;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.LeagueStanding;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.LeagueTable;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.Team;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FootballDataHelperTest {

    @Test
    public void shouldCreateTeamList() {
        // Given
        String name = "Chelsea FC";
        String code = "CFC";

        List<Team> teams = new ArrayList();
        teams.add(givenTeam(name, code));

        League league = new League();
        league.setTeams(teams);

        List<LeagueStanding> standings = new ArrayList();
        standings.add(givenStanding(name));

        LeagueTable table = new LeagueTable();
        table.setStanding(standings);

        // When
        List<Team> result = FootballDataHelper.createTeamList(league, table);

        // Then
        Team t = result.get(0);
        assertThat(t.getName(), is(name));
        assertThat(t.getCode(), is(code));
        assertThat(t.getLeagueStanding().getPosition(), is(1));

    }

    private Team givenTeam(String name, String code) {
        Team t = new Team();
        t.setName(name);
        t.setCode(code);
        return t;
    }

    private LeagueStanding givenStanding(String name){
        LeagueStanding ls = new LeagueStanding();
        ls.setTeamName(name);
        ls.setPosition(1);
        return ls;
    }


//    public static List<Team> createTeamList(League league, LeagueTable table){
//        List<Team> teams = new LinkedList();
//
//        for (LeagueStanding leagueStanding : table.getStanding()) {
//            Iterator<Team> iterator = league.getTeams().iterator();
//
//            while(iterator.hasNext()){
//                Team team = iterator.next();
//
//                if(team.getName().equals(leagueStanding.getTeamName())){
//                    team.setLeagueStanding(leagueStanding);
//                    teams.add(team);
//
//                    iterator.remove();
//                    break;
//                }
//            }
//        }
//
//        return teams;
//    }

}
