package fun.krol.oauthtwo.client;

import fun.krol.oauthtwo.AccessToken;

import java.io.IOException;

public interface AccessTokenReceiver {
    AccessToken takeAccessTokens(String token , String state) throws IOException, InterruptedException;
}
