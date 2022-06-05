package fun.krol.oauthtwo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class AccessToken{

      private String accessToken;


     private String tokenType;

     private Long expiresIn;

     private String refreshToken;

     Set<String> scope ;

     public AccessToken() {
     }


     public AccessToken(String t) {
          System.out.printf(t);
     }

     public AccessToken(String accessToken, String tokenType, Long expiresIn, String refreshToken, Set<String> scope) {
          this.accessToken = accessToken;
          this.tokenType = tokenType;
          this.expiresIn = expiresIn;
          this.refreshToken = refreshToken;
          this.scope = scope;
     }

     @JsonProperty("access_token")
     public String getAccessToken() {
          return accessToken;
     }

     @JsonProperty("access_token")
     public void setAccessToken(String accessToken) {
          this.accessToken = accessToken;
     }

     @JsonProperty("token_type")
     public String getTokenType() {
          return tokenType;
     }

     @JsonProperty("token_type")
     public void setTokenType(String tokenType) {
          this.tokenType = tokenType;
     }

     @JsonProperty("expires_in")
     public Long getExpiresIn() {
          return expiresIn;
     }

     @JsonProperty("expires_in")
     public void setExpiresIn(Long expiresIn) {
          this.expiresIn = expiresIn;
     }

     @JsonProperty(value = "refresh_token", required = false)
     public String getRefreshToken() {
          return refreshToken;
     }

     @JsonProperty(value = "refresh_token", required = false)
     public void setRefreshToken(String refreshToken) {
          this.refreshToken = refreshToken;
     }

     @JsonProperty(value = "scope",required = false)
     public Set<String> getScope() {
          return scope;
     }

     @JsonProperty(value = "scope",required = false)
     public void setScope(Set<String> scope) {
          this.scope = scope;
     }
}
