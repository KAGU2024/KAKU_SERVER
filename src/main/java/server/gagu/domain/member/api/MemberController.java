package server.gagu.domain.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.gagu.domain.member.application.MemberService;
import server.gagu.domain.member.dto.response.NewMemberResponseDto;
import server.gagu.domain.member.exception.MemberNotFoundException;
import server.gagu.global.exception.dto.ErrorResponse;
import server.gagu.global.template.ResponseTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "신규 회원 확인", description = "신규 회원 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신규 회원 확인 조회 성공", content = @Content(schema = @Schema(implementation = NewMemberResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/new")
    public ResponseTemplate<NewMemberResponseDto> isNewMember(@AuthenticationPrincipal String email) throws MemberNotFoundException {
        return new ResponseTemplate<>(HttpStatus.OK, "신규 회원 확인 조회 성공", memberService.isNewMember(email));
    }
}
