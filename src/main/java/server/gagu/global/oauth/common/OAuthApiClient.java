package server.gagu.global.oauth.common;

import server.gagu.domain.member.domain.entity.SocialType;

public interface OAuthApiClient {
    SocialType oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
