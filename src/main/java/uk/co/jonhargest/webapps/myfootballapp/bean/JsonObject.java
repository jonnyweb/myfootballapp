package uk.co.jonhargest.webapps.myfootballapp.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JsonObject {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
