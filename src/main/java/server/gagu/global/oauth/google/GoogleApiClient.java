package server.gagu.global.oauth.google;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import server.gagu.domain.member.domain.entity.SocialType;
import server.gagu.global.oauth.common.OAuthApiClient;
import server.gagu.global.oauth.common.OAuthInfoResponse;
import server.gagu.global.oauth.common.OAuthLoginParams;

@Component
@RequiredArgsConstructor
public class GoogleApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth.google.auth.token-uri}")
    private String tokenUri;

    @Value("${oauth.google.auth.resource-uri}")
    private String resourceUri;

    @Value("${oauth.google.auth.redirect-uri}")
    private String redirectUrl;

    @Value("${oauth.google.client-id}")
    private String clientId;

    @Value("${oauth.google.secret}")
    private String clientSecret;
    private final RestTemplate restTemplate;

    @Override
    public SocialType oAuthProvider() {
        return SocialType.GOOGLE;
    }


    public String requestAccessToken(OAuthLoginParams params) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUrl);

        System.out.println(body);
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        GoogleTokens response = restTemplate.postForObject(tokenUri, request, GoogleTokens.class);

        assert response != null;
        return response.getAccess_token();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        System.out.println(restTemplate.exchange(resourceUri, HttpMethod.GET, request, JsonNode.class).getBody());
        return restTemplate.postForObject(resourceUri, request, GoogleInfoResponse.class);
    }
}
