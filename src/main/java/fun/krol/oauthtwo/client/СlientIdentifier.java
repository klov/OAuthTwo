package fun.krol.oauthtwo.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public interface СlientIdentifier {
    URL identifyСlient(Set<String> scops) throws MalformedURLException;
}
