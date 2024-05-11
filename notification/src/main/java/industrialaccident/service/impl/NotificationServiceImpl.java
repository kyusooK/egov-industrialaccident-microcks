package industrialaccident.service.impl;

import industrialaccident.domain.Notification;
import industrialaccident.domain.NotificationRepository;
import industrialaccident.service.NotificationService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notificationService")
@Transactional
public class NotificationServiceImpl
    extends EgovAbstractServiceImpl
    implements NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        NotificationServiceImpl.class
    );

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() throws Exception {
        // Get all notifications
        return StreamSupport
            .stream(notificationRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Notification> getNotificationById(Long id)
        throws Exception {
        // Get a notification by ID
        return notificationRepository.findById(id);
    }

    @Override
    public Notification createNotification(Notification notification)
        throws Exception {
        // Create a new notification
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification)
        throws Exception {
        // Update an existing notification via NotificationService
        if (notificationRepository.existsById(notification.getId())) {
            return notificationRepository.save(notification);
        } else {
            throw processException("info.nodata.msg");
        }
    }

    @Override
    public void deleteNotification(Long id) throws Exception {
        // Delete a notification
        notificationRepository.deleteById(id);
    }
}
