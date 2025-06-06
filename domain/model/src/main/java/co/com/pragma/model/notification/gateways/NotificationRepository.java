package co.com.pragma.model.notification.gateways;

import co.com.pragma.model.notification.Notification;

import java.util.List;
import java.util.Set;

public interface NotificationRepository {
    void sendNotification(Notification notification, Set<String> emails);
}
