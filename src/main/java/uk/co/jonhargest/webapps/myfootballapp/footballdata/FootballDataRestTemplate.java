package uk.co.jonhargest.webapps.myfootballapp.footballdata;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FootballDataRestTemplate extends RestTemplate {

    public FootballDataRestTemplate() {
        super();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new FootballDataRestInterceptor());

        this.setInterceptors(interceptors);
    }
}
