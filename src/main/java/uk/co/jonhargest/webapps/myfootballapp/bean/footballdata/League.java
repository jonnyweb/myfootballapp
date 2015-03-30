package uk.co.jonhargest.webapps.myfootballapp.bean.footballdata;

import uk.co.jonhargest.webapps.myfootballapp.bean.JsonObject;

import java.util.List;

public class League extends JsonObject {

    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
