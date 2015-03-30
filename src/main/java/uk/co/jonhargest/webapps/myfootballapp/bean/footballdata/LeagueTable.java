package uk.co.jonhargest.webapps.myfootballapp.bean.footballdata;

import uk.co.jonhargest.webapps.myfootballapp.bean.JsonObject;

import java.util.List;

public class LeagueTable extends JsonObject {

    private List<LeagueStanding> standing;

    public List<LeagueStanding> getStanding() {
        return standing;
    }

    public void setStanding(List<LeagueStanding> standing) {
        this.standing = standing;
    }
}
