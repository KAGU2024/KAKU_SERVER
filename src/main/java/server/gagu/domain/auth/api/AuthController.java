package server.gagu.domain.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.gagu.domain.auth.application.AuthService;
import server.gagu.domain.auth.dto.ReIssueRequestDto;
import server.gagu.domain.auth.exception.InvalidTokenException;
import server.gagu.domain.member.exception.MemberNotFoundException;
import server.gagu.global.jwt.dto.response.TokenDto;
import server.gagu.global.oauth.exception.OAuthException;
import server.gagu.global.oauth.google.GoogleLoginParams;
import server.gagu.global.oauth.kakao.KakaoLoginParams;
import server.gagu.global.oauth.naver.NaverLoginParams;
import server.gagu.global.template.ResponseTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "카카오 소셜 로그인", description = "카카오 소셜 로그인입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카카오 소셜 로그인 성공")
    })
    @PostMapping("/kakao")
    public ResponseTemplate<TokenDto> loginKakao(@RequestBody KakaoLoginParams params) throws OAuthException, MemberNotFoundException {
        return new ResponseTemplate<>(HttpStatus.OK, "카카오 로그인 성공", authService.login(params));
    }

    @Operation(summary = "네이버 소셜 로그인", description = "네이버 소셜 로그인입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네이버 소셜 로그인 성공")
    })
    @PostMapping("/naver")
    public ResponseTemplate<TokenDto> loginNaver(@RequestBody NaverLoginParams params) throws OAuthException, MemberNotFoundException {
        return new ResponseTemplate<>(HttpStatus.OK, "네이버 로그인 성공", authService.login(params));
    }

    @Operation(summary = "구글 소셜 로그인", description = "구글 소셜 로그인입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "구글 소셜 로그인 성공")
    })
    @PostMapping("/google")
    public ResponseTemplate<TokenDto> loginGoogle(@RequestBody GoogleLoginParams params) throws OAuthException, MemberNotFoundException {
        return new ResponseTemplate<>(HttpStatus.OK, "구글 로그인 성공", authService.login(params));
    }

    @Operation(summary = "액세스 토큰 재발급", description = "토큰 만료 시, 액세스 토큰을 재발급 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "액세스 토큰 재발급 성공")
    })
    @PostMapping("/access")
    public ResponseTemplate<TokenDto> newAccessToken(@RequestBody ReIssueRequestDto reIssueRequestDto) throws MemberNotFoundException, InvalidTokenException {
        TokenDto newToken = authService.generateAccessToken(reIssueRequestDto);
        return new ResponseTemplate<>(HttpStatus.OK, "액세스 토큰 발급", newToken);
    }
}
