package fun.krol.oauthtwo;

import fun.krol.oauthtwo.client.*;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpClient;
import java.time.Duration;

public class OAuthBuilder {
    private String autorisationBaseHost = "http://localhost";
    private СlientIdentifier clientIdentifier;
    private AccessTokenReceiver accessTokenReceiver;
    private String redirectUrl = "/redirectUrl";
    private String clientId;
    private HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();
    private String tokenEndpoint;

    public OAuthBuilder setAutorisationbaseHost(String host) {
        this.autorisationBaseHost = host;
        return this;
    }

    public OAuthBuilder setAutorisationBaseHost(String autorisationBaseHost) {
        this.autorisationBaseHost = autorisationBaseHost;
        return this;
    }

    public OAuthBuilder setClientIdentifier(СlientIdentifier clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
        return this;
    }

    public OAuthBuilder setAccessTokenReceiver(AccessTokenReceiver accessTokenReceiver) {
        this.accessTokenReceiver = accessTokenReceiver;
        return this;
    }

    public OAuthBuilder setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public OAuthBuilder setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public OAuthBuilder setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }


    public OAuthClient buildClient() {
        if (clientIdentifier == null) {
            createClientIdentifier();
        }
        if (accessTokenReceiver == null) {
            createAccessTokenReceiver();
        }
        return new OAuthClient(clientIdentifier, accessTokenReceiver);
    }

    public OAuthBuilder createAccessTokenReceiver() {
        if(tokenEndpoint == null){
            throw new IllegalArgumentException("tokenEndpoint is null");
        }
        accessTokenReceiver = new DefaultAccessTokenReceiver( httpClient,
                 autorisationBaseHost+tokenEndpoint,
                 clientId,
                 redirectUrl
                );
        return this;
    }

    public OAuthBuilder createClientIdentifier() {
        clientIdentifier = new DefaultСlientIdentifier(redirectUrl,
                clientId,
                autorisationBaseHost);
        return this;
    }

    public OAuthBuilder setTokenAccessEndpont(@NotNull String endpoint) {
        this.tokenEndpoint =  endpoint;
        return this;
    }
}
