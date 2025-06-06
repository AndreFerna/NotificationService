package co.com.pragma.api.controller;

import co.com.pragma.api.dto.NotificationDto;
import co.com.pragma.api.dto.ResponseErrorDto;
import co.com.pragma.api.mapper.NotificationMapper;
import co.com.pragma.model.config.ErrorCode;
import co.com.pragma.model.config.PragmaException;
import co.com.pragma.model.notification.Notification;
import co.com.pragma.usecase.notification.NotificationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class NotificationController {

    private HealthEndpoint healthEndpoint;
    private final NotificationUseCase notificationUseCase;

    @Operation(
            summary = "Verifica el estado del servicio",
            description = "Este endpoint permite monitorear si el servicio está disponible.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El servicio está activo"),
            }
    )
    @RequestMapping(path = "/health", method = RequestMethod.HEAD)
    public ResponseEntity<Void> health() {
        HealthComponent healthComponent = healthEndpoint.health();

        if (Status.UP.equals(healthComponent.getStatus())) {
            return ResponseEntity.ok().build();
        } else {
            throw new PragmaException(ErrorCode.SP503);
        }
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseErrorDto.class))}),
            @ApiResponse(responseCode = "409", description = "Se presentan conflictos con los datos de la solicitud", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseErrorDto.class))})
    })
    public void sendNotification(@Valid @RequestBody NotificationDto notificationDto) {
        Notification notification = NotificationMapper.toDomain(notificationDto);
        notificationUseCase.sendNotification(notification);
    }
}
