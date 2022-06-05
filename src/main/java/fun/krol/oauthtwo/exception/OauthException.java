package fun.krol.oauthtwo.exception;

import java.io.IOException;

public class OauthException  extends IOException {
    public OauthException(String message) {
        super(message);
    }
}
