package fun.krol.oauthtwo.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class DefaultСlientIdentifier implements СlientIdentifier{
    private final String redirectUrl;
    private final String clientId;
    private final String autorisationbaseHost;

    public DefaultСlientIdentifier(String redirectUrl, String clientId, String autorisationbaseHost) {
        this.redirectUrl = redirectUrl;
        this.clientId = clientId;
        this.autorisationbaseHost = autorisationbaseHost;
    }

    public URL identifyСlient(Set<String> scops) throws MalformedURLException {
        StringBuilder sb = new StringBuilder(autorisationbaseHost);
        sb.append("response_type=code");
        sb.append("&client_id="+clientId);
        sb.append("&redirect_uri="+redirectUrl);
        return new URL(sb.toString());
    }
}
