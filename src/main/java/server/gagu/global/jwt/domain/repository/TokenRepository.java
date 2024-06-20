package server.gagu.global.jwt.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.gagu.domain.member.domain.entity.Member;
import server.gagu.global.jwt.domain.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByMember(Member member);

    Token findByRefreshToken(String refreshToken);
}
