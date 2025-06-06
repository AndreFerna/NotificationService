package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.NotificationDto;
import co.com.pragma.model.notification.Notification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationMapper {
    public static Notification toDomain(NotificationDto notificationDto) {
        return Notification.builder()
                .viewIdentifier(notificationDto.getViewIdentifier())
                .build();
    }

}
