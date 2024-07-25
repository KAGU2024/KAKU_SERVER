package server.gagu.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.gagu.domain.member.domain.entity.Member;
import server.gagu.domain.member.domain.repository.MemberRepository;
import server.gagu.domain.member.exception.MemberNotFoundException;
import server.gagu.global.exception.dto.ErrorCode;

@Component
@RequiredArgsConstructor
public class GlobalUtil {
    private final MemberRepository memberRepository;

    public Member findByMemberWithEmail(String email) throws MemberNotFoundException {
        return memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
