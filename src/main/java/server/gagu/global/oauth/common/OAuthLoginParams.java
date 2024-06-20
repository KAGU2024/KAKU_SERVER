package server.gagu.global.oauth.common;


import org.springframework.util.MultiValueMap;
import server.gagu.domain.member.domain.entity.SocialType;

public interface OAuthLoginParams {
    SocialType oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
