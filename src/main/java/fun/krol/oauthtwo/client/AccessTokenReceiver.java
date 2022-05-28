package fun.krol.oauthtwo.client;

import fun.krol.oauthtwo.AccessToken;
import fun.krol.oauthtwo.exception.OauthException;

import java.io.IOException;

public interface AccessTokenReceiver {
    AccessToken takeAccessTokens(String token , String state) throws OauthException;
}
