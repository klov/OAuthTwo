package fun.krol.oauthtwo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.krol.oauthtwo.AccessToken;
import fun.krol.oauthtwo.exception.OauthException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultAccessTokenReceiver implements AccessTokenReceiver {
    private HttpClient httpClient;
    private final String autorisationBaseHost;
    private final String clientId;
    private final String redirectUrl;

    public DefaultAccessTokenReceiver(HttpClient httpClient,
                                      String autorisationBaseHost,
                                      String clientId,
                                      String redirectUrl) {
        if(clientId == null){
            throw new IllegalArgumentException("clientId is null");
        }
        if(redirectUrl == null){
            throw new IllegalArgumentException("redirectUrl is null");
        }
        if(httpClient == null){
            throw new IllegalArgumentException("httpClient is null");
        }
        if(autorisationBaseHost == null){
            throw new IllegalArgumentException("autorisationBaseHost is null");
        }


        this.httpClient = httpClient;
        this.autorisationBaseHost = autorisationBaseHost;
        this.clientId = clientId;
        this.redirectUrl = redirectUrl;
    }

    public AccessToken takeAccessTokens(String token, String state) throws OauthException {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&code=" + token);
        sb.append("&client_id=" + clientId);
        sb.append("&redirect_uri=" + redirectUrl);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(autorisationBaseHost))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
                .build();
        try {
            String tokenRequest = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return convertAccessToken(tokenRequest);
        } catch (IOException e) {
            throw new OauthException(e.getMessage());
        } catch (InterruptedException e) {
            throw new OauthException(e.getMessage());
        }


    }

    private AccessToken convertAccessToken(String tokenRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(tokenRequest, AccessToken.class);
    }

}

