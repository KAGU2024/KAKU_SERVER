package server.gagu.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReIssueRequestDto {
    @NotBlank
    private String refreshToken;
}
