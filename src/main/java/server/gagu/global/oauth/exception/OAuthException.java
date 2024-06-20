package server.gagu.global.oauth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.gagu.global.exception.dto.ErrorCode;

@Getter
@AllArgsConstructor
public class OAuthException extends Exception {
    private final ErrorCode errorCode;
}
