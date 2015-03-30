package uk.co.jonhargest.webapps.myfootballapp.bean.footballdata;

public enum Club {

    AFC("Arsenal"),
    AVFC("AVFCOfficial"),
    BFC("BurnleyOfficial"),
    CFC("ChelseaFC"),
    CRY("CPFC"),
    EFC("Everton"),
    HUL("HullCity"),
    LCFC("Official_FOXES"),
    LFC("LFC"),
    MCFC("MCFC"),
    MUFC("ManUtd"),
    NUFC("NUFC"),
    QPR("QPRFC"),
    SCFC("stokecity"),
    SFC("SouthamptonFC"),
    SUN("SunderlandAFC"),
    SWA("SwansOfficial"),
    THFC("SpursOfficial"),
    WBA("WBAFCOfficial"),
    WHU("whufc_official");

    private String twitterProfile;

    Club(String twitterProfile) {
        this.twitterProfile = twitterProfile;
    }

    public String getTwitterProfile() {
        return twitterProfile;
    }

}
