package fun.krol.oauthtwo.client;

import fun.krol.oauthtwo.AccessToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultAccessTokenReceiver implements AccessTokenReceiver {
    private HttpClient httpClient;
    private final String autorisationbaseHost;
    private final String clientId;
    private final String redirectUrl;

    public DefaultAccessTokenReceiver(HttpClient httpClient, String autorisationbaseHost, String clientId, String redirectEndpont, String redirectUrl) {
        this.httpClient = httpClient;
        this.autorisationbaseHost = autorisationbaseHost;
        this.clientId = clientId;
        this.redirectUrl = redirectUrl;
    }

     public AccessToken takeAccessTokens(String token, String state) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&code=" + token);
        sb.append("&client_id=" + clientId);
        sb.append("&redirect_uri=" + redirectUrl);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(autorisationbaseHost))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
                .build();
       return  httpClient.send(request, responseHandler()).body();
    }

    private HttpResponse.BodyHandler<AccessToken> responseHandler() {
        return new HttpResponse.BodyHandler<AccessToken>() {
            @Override
            public HttpResponse.BodySubscriber apply(HttpResponse.ResponseInfo responseInfo) {
                responseInfo
                return null;
            }
        }
    }


}

