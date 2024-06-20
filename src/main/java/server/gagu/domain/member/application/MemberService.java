package server.gagu.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.gagu.domain.member.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

}
