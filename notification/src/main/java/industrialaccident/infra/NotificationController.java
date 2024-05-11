package industrialaccident.infra;

import industrialaccident.domain.*;
import industrialaccident.service.*;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/notifications")
public class NotificationController {

    @Resource(name = "notificationService")
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() throws Exception {
        // Get all notifications via NotificationService
        return notificationService.getAllNotifications();
    }

    @GetMapping("/notifications/{id}")
    public Optional<Notification> getNotificationById(@PathVariable Long id)
        throws Exception {
        // Get a notification by ID via NotificationService
        return notificationService.getNotificationById(id);
    }

    @PostMapping("/notifications")
    public Notification createNotification(
        @RequestBody Notification notification
    ) throws Exception {
        // Create a new notification via NotificationService
        return notificationService.createNotification(notification);
    }

    @PutMapping("/notifications/{id}")
    public Notification updateNotification(
        @PathVariable Long id,
        @RequestBody Notification notification
    ) throws Exception {
        // Update an existing notification via NotificationService
        return notificationService.updateNotification(notification);
    }

    @DeleteMapping("/notifications/{id}")
    public void deleteNotification(@PathVariable Long id) throws Exception {
        // Delete a notification via NotificationService
        notificationService.deleteNotification(id);
    }
}
