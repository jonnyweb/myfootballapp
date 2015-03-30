package uk.co.jonhargest.webapps.myfootballapp.footballdata;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

public class FootballDataRestInterceptor implements ClientHttpRequestInterceptor {

    private static final String X_AUTH_TOKEN_HEADER = "X-Auth-Token";
    private static final String X_AUTH_TOKEN = "7d54d7c6dc2e4cefa82d3892b804128a";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set(X_AUTH_TOKEN_HEADER, X_AUTH_TOKEN);
        return execution.execute(wrapper, body);
    }
}
