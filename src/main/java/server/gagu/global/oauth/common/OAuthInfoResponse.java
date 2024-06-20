package server.gagu.global.oauth.common;

import server.gagu.domain.member.domain.entity.SocialType;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    SocialType getOAuthProvider();
}
