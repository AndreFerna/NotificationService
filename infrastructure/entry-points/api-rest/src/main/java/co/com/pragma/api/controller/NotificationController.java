package co.com.pragma.api.controller;

import co.com.pragma.api.dto.NotificationDto;
import co.com.pragma.api.mapper.NotificationMapper;
import co.com.pragma.model.notification.Notification;
import co.com.pragma.usecase.notification.NotificationUseCase;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class NotificationController {

    private final NotificationUseCase notificationUseCase;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta exitosa", content = {@Content(mediaType = "appliction/json", schema = @Schema(implementation = NotificationDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDto.class))}),
            @ApiResponse(responseCode = "409", description = "Se presentan conflictos con los datos de la solicitud", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDto.class))})
    })
    public void sendNotification(@Valid @RequestBody NotificationDto notificationDto) {
        Notification notification = NotificationMapper.toDomain(notificationDto);
        notificationUseCase.sendNotification(notification);
    }
}
