package uk.co.jonhargest.webapps.myfootballapp.bean.footballdata;

import uk.co.jonhargest.webapps.myfootballapp.bean.JsonObject;

public class Team extends JsonObject {

    private String name;
    private String code;
    private String shortName;
    private String squadMarketValue;
    private String crestUrl;
    private LeagueStanding leagueStanding;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public LeagueStanding getLeagueStanding() {
        return leagueStanding;
    }

    public void setLeagueStanding(LeagueStanding leagueStanding) {
        this.leagueStanding = leagueStanding;
    }
}
