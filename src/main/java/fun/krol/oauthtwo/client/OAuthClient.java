package fun.krol.oauthtwo.client;

import fun.krol.oauthtwo.AccessToken;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class OAuthClient implements СlientIdentifier, AccessTokenReceiver {

    private final СlientIdentifier clientIdentifier;
    private final AccessTokenReceiver accessTokenReceiver;

    public OAuthClient(СlientIdentifier clientIdentifier, AccessTokenReceiver accessTokenReceiver) {
        this.clientIdentifier = clientIdentifier;
        this.accessTokenReceiver = accessTokenReceiver;
    }

    @Override
    public AccessToken takeAccessTokens(String token, String state) throws IOException, InterruptedException {
        return accessTokenReceiver.takeAccessTokens(token,state);
    }

    public AccessToken takeAccessTokens(String token) throws IOException, InterruptedException {
        return this.takeAccessTokens(token,"");
    }

    @Override
    public URL identifyСlient(Set<String> scops) throws MalformedURLException {
        return clientIdentifier.identifyСlient(scops);
    }
}
