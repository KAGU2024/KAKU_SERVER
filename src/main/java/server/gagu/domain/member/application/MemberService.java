package server.gagu.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.gagu.domain.member.domain.entity.Member;
import server.gagu.domain.member.domain.repository.MemberRepository;
import server.gagu.domain.member.dto.response.NewMemberResponseDto;
import server.gagu.domain.member.exception.MemberNotFoundException;
import server.gagu.global.utils.GlobalUtil;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final GlobalUtil globalUtil;

    public NewMemberResponseDto isNewMember(String email) throws MemberNotFoundException {
        Member member = globalUtil.findByMemberWithEmail(email);
        return NewMemberResponseDto.builder()
                .isNew(member.getLocation() == null)
                .build();
    }
}
