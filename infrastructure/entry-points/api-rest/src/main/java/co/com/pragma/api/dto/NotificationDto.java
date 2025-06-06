package co.com.pragma.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    @NotNull
    @Schema(example = "4f63ac55-7c3b-4d44-894d-d92851c822b3")
    private String viewIdentifier;
}
