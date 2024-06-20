package server.gagu.global.oauth.google;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import server.gagu.domain.member.domain.entity.SocialType;
import server.gagu.global.oauth.common.OAuthLoginParams;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Getter
@NoArgsConstructor
public class GoogleLoginParams implements OAuthLoginParams {
    private String authorizationCode;

    @Override
    public SocialType oAuthProvider() {
        return SocialType.GOOGLE;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String decode = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);
        body.add("code", decode);
        return body;
    }
}
