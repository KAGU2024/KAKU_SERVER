package server.gagu.domain.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.gagu.global.exception.dto.ErrorCode;

@Getter
@AllArgsConstructor
public class InvalidTokenException extends Throwable {
    private final ErrorCode errorCode;
}
