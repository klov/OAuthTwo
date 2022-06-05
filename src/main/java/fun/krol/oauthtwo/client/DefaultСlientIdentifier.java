package fun.krol.oauthtwo.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class DefaultСlientIdentifier implements СlientIdentifier{
    private final String redirectUrl;
    private final String clientId;
    private final String autorisationBaseHost;

    public DefaultСlientIdentifier(String redirectUrl, String clientId, String autorisationBaseHost) {
        if(redirectUrl == null){
            throw new IllegalArgumentException("redirectUrl is null");
        }
        if(clientId == null){
            throw new IllegalArgumentException("clientId is null");
        }
        if(autorisationBaseHost == null){
            throw new IllegalArgumentException("autorisationBaseHost is null");
        }
        this.redirectUrl = redirectUrl;
        this.clientId = clientId;
        this.autorisationBaseHost = autorisationBaseHost;

    }

    public URL identifyСlient(Set<String> scops) throws MalformedURLException {
        StringBuilder sb = new StringBuilder(autorisationBaseHost);
        sb.append("response_type=code");
        sb.append("&client_id="+clientId);
        sb.append("&redirect_uri="+redirectUrl);
        sb.append("&scops="+String.join(" ", scops).trim() );
        return new URL(sb.toString());
    }
}
