package server.gagu.global.oauth.service;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import server.gagu.domain.member.domain.entity.SocialType;
import server.gagu.global.exception.dto.ErrorCode;
import server.gagu.global.oauth.common.OAuthApiClient;
import server.gagu.global.oauth.common.OAuthInfoResponse;
import server.gagu.global.oauth.common.OAuthLoginParams;
import server.gagu.global.oauth.exception.OAuthException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<SocialType, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) throws OAuthException {
        try {
            OAuthApiClient client = clients.get(params.oAuthProvider());
            String accessToken = client.requestAccessToken(params);
            return client.requestOauthInfo(accessToken);
        } catch (RestClientException e) {
            throw new OAuthException(ErrorCode.OAUTH_BAD_REQUEST);
        }
    }
}
