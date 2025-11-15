package customerPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Manages notification operations
 */
public class NotificationManager {
    private static final String NOTIFICATION_FILE = "notifications.txt";

    /**
     * Load all notifications from file
     */
    public static ArrayList<Notification> loadNotifications() {
        File file = new File(NOTIFICATION_FILE);
        ArrayList<Notification> notifications = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                notifications = (ArrayList<Notification>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error loading notifications: " + e.getMessage());
            }
        }
        return notifications;
    }

    /**
     * Save all notifications to file
     */
    public static void saveNotifications(ArrayList<Notification> notifications) {
        File file = new File(NOTIFICATION_FILE);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(notifications);
        } catch (Exception e) {
            System.out.println("Error saving notifications: " + e.getMessage());
        }
    }

    /**
     * Add a new notification for a user
     */
    public static void addNotification(String userEmail, int receiptID, String message) {
        ArrayList<Notification> notifications = loadNotifications();
        Notification newNotification = new Notification(userEmail, receiptID, message);
        notifications.add(newNotification);
        saveNotifications(notifications);
    }

    /**
     * Get all notifications for a specific user
     */
    public static ArrayList<Notification> getNotificationsForUser(String userEmail) {
        ArrayList<Notification> allNotifications = loadNotifications();
        ArrayList<Notification> userNotifications = new ArrayList<>();

        for (Notification notif : allNotifications) {
            if (notif.getUserEmail() != null && notif.getUserEmail().equalsIgnoreCase(userEmail)) {
                userNotifications.add(notif);
            }
        }
        return userNotifications;
    }

    /**
     * Mark all notifications as read for a specific user
     */
    public static void markAllAsRead(String userEmail) {
        ArrayList<Notification> allNotifications = loadNotifications();
        boolean modified = false;

        for (Notification notif : allNotifications) {
            if (notif.getUserEmail() != null && notif.getUserEmail().equalsIgnoreCase(userEmail) && !notif.isRead()) {
                notif.markAsRead();
                modified = true;
            }
        }

        if (modified) {
            saveNotifications(allNotifications);
        }
    }

    /**
     * Count unread notifications for a user
     */
    public static int countUnreadNotifications(String userEmail) {
        ArrayList<Notification> notifications = getNotificationsForUser(userEmail);
        int count = 0;
        for (Notification notif : notifications) {
            if (!notif.isRead()) {
                count++;
            }
        }
        return count;
    }
}
