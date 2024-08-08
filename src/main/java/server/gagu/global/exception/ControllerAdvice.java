package server.gagu.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.gagu.domain.member.exception.MemberNotFoundException;
import server.gagu.global.exception.dto.ErrorCode;
import server.gagu.global.exception.dto.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    /*
     * HTTP 405 Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    /*
     * HTTP 404 NOT_FOUND
     */
    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> memberNotFoundException(final MemberNotFoundException e) {
        log.error("MemberNotFoundException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }
    /*
     * HTTP 500 Exception
     */
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
//        log.error("handleException: {}", e.getMessage());
//        return ResponseEntity
//                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
//                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
//    }
}
