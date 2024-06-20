package server.gagu.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.gagu.global.exception.dto.ErrorCode;

@Getter
@AllArgsConstructor
public class MemberNotFoundException extends Exception {
    private final ErrorCode errorCode;
}
