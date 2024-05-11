package industrialaccident.service;

import industrialaccident.domain.*;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> getAllNotifications() throws Exception;
    Optional<Notification> getNotificationById(Long id) throws Exception;
    Notification createNotification(Notification notification) throws Exception;
    Notification updateNotification(Notification notification) throws Exception;
    void deleteNotification(Long id) throws Exception;
}
