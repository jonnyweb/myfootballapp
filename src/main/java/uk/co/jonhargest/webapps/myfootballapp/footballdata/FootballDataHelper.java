package uk.co.jonhargest.webapps.myfootballapp.footballdata;

import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.League;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.LeagueStanding;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.LeagueTable;
import uk.co.jonhargest.webapps.myfootballapp.bean.footballdata.Team;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class FootballDataHelper {

    public static List<Team> createTeamList(League league, LeagueTable table) {
        List<Team> teams = new LinkedList();

        for (LeagueStanding leagueStanding : table.getStanding()) {
            Iterator<Team> iterator = league.getTeams().iterator();

            while (iterator.hasNext()) {
                Team team = iterator.next();

                if (team.getName().equals(leagueStanding.getTeamName())) {
                    team.setLeagueStanding(leagueStanding);
                    teams.add(team);

                    iterator.remove();
                    break;
                }
            }
        }

        return teams;
    }
}
