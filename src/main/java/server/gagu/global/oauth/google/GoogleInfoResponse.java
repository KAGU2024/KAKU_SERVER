package server.gagu.global.oauth.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import server.gagu.domain.member.domain.entity.SocialType;
import server.gagu.global.oauth.common.OAuthInfoResponse;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleInfoResponse implements OAuthInfoResponse {

    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getNickname() {
        return this.name;
    }

    @Override
    public SocialType getOAuthProvider() {
        return SocialType.GOOGLE;
    }
}
