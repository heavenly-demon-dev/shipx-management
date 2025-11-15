package customerPackage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a notification for a customer
 */
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userEmail;  // Email of the user who receives this notification
    private int receiptID;     // Receipt ID of the box
    private String message;    // Notification message
    private String timestamp;  // When the notification was created
    private boolean isRead;    // Whether the notification has been read

    public Notification(String userEmail, int receiptID, String message) {
        this.userEmail = userEmail;
        this.receiptID = receiptID;
        this.message = message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
        this.isRead = false;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        String status = isRead ? "[READ]" : "[NEW]";
        return status + " [" + timestamp + "] Receipt #" + receiptID + ": " + message;
    }
}
