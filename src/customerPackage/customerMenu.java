/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerPackage;

/**
 *
 * @author USER
 */
import boxPackage.box;
import customerPackage.customerAccounts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class customerMenu {
     private User user;
     
     public customerMenu(User user){
         this.user = user;
     }
     public customerMenu(){
         
     }
    public void userMenu() {
        File file = new File("boxList.txt");
        Scanner sc = new Scanner(System.in);
        ArrayList<box> boxList = new ArrayList<>();
        customerAccounts cusAcc = new customerAccounts();
        
        while (true) {
            System.out.println("\n-------------  WELCOME TO SHIPX ------------- ");
            System.out.println("1. SEND A BOX");
            System.out.println("2. VIEW BOX STATUS");
            System.out.println("3. VIEW RECEIPT HISTORY");
            System.out.println("4. CONTACT SHIPX");
            System.out.println("5. NOTIFICATIONS");
            System.out.println("6. LOG OUT");
            System.out.println("---------------------- ------------------------");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("ENTER VALID INPUT");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    sendBox();
                    break;
                case 2:
                    viewBoxStatus();
                    break;
                case 3:
                    viewReceipts();
                    break;
                case 4:
                    contactShipX();
                    break;
                case 5:
                    viewNotifications();
                    break;
                case 6:
                    cusAcc.displayMenu();
                    break;
               default:
                    System.out.println("====== INVALID CHOICE. TRY AGAIN ====== ");
            }
        }
    }

    
    public void sendBox() {
        Scanner sc = new Scanner(System.in);
        File file = new File("boxList.txt");
        
        ArrayList<box> boxList = new ArrayList<>();
        
        if(file.exists() && file.length() > 0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                boxList = (ArrayList<box>)
                ois.readObject();
            }catch(Exception exc){
            }
        }
        String region = "";
        double price = 0;
        int size; 
        
        System.out.println("\n-------------  SEND A BOX ------------- ");
      
            try{
                System.out.println("REGION OF DESTINATION: ");
                System.out.println("[1] - NCR");
                System.out.println("[2] - LUZON");
                System.out.println("[3] - VISAYAS");
                System.out.println("[4] - MINDANAO");
                System.out.print("Choose region: ");
                int choice = sc.nextInt();
                sc.nextLine();
        
        System.out.println("----------SENDER INFORMATION----------");
        System.out.print("Enter Sender Name: ");
        String senderName = sc.nextLine();
        System.out.print("Enter Sender Address: ");
        String senderAddress = sc.nextLine();
        System.out.print("Enter Sender Contact Number: ");
        String senderContact = sc.nextLine();
        System.out.print("Enter Sender Email: ");
        String senderEmail = sc.nextLine();
        System.out.println("----------RECEIVER INFORMATION----------");
        System.out.print("Enter Receiver Name: ");
        String receiverName = sc.nextLine();
        System.out.print("Enter Receiver Address/Drop-off: ");
        String receiverAddress = sc.nextLine();
        System.out.print("Enter Receiver Contact Number: ");
        String receiverContact = sc.nextLine();
        System.out.print("Enter Receiver Email: ");
        String receiverEmail = sc.nextLine();
        
        
        System.out.println("Enter items to send: ");
        String items = sc.nextLine();
        
        
        switch(choice){
            case 1: 
                region = "NCR";
                System.out.println("\n BOX SIZE FOR ITEMS | Region: " + region + " :");
                System.out.println("[1] - MINI (1KG MAX)        Php: 120");
                System.out.println("[2] - SMALL (3KGS MAX)      Php: 160");
                System.out.println("[3] - MEDIUM (5KGS MAX)     Php: 220");
                System.out.println("[4] - LARGE (10KGS MAX)     Php: 420");
                System.out.println("[5] - XL (20KGS MAX)        Php: 820");
                System.out.print("Choose size: ");
                size = sc.nextInt();
                 if (size == 1) {
                    price = 120;
                }else if (size == 2) {
                    price = 160;
                }else if (size == 3) {
                    price = 220;
                }else if (size == 4) {
                    price = 420;
                }else if (size == 5) {
                    price = 820;
                }else{
                    System.out.println("====== INVALID BOX SIZE. TRY AGAIN ======");
                } break;
            case 2: 
                region = "LUZON";
                System.out.println("\n BOX SIZE FOR ITEMS | Region: " + region + " :");
                System.out.println("[1] - MINI (1KG MAX)        Php: 150");
                System.out.println("[2] - SMALL (3KGS MAX)      Php: 190");
                System.out.println("[3] - MEDIUM (5KGS MAX)     Php: 320");
                System.out.println("[4] - LARGE (10KGS MAX)     Php: 620");
                System.out.println("[5] - XL (20KGS MAX)        Php: 1220");
                System.out.print("Choose size: ");
                size = sc.nextInt();

                if (size == 1) {
                    price = 150;
                }else if (size == 2){
                    price = 190;
                }else if (size == 3) {
                    price = 320;
                }else if (size == 4) {
                    price = 620;
                }else if (size == 5) {
                    price = 1220;
                }else {
                    System.out.println("====== INVALID BOX SIZE. TRY AGAIN ======");
                } break;
            case 3: 
                region = "VISAYAS";
                System.out.println("\nBOX SIZE FOR ITEMS | Region: " + region + " :");
                System.out.println("[1] - MINI (1KG MAX)        Php: 150");
                System.out.println("[2] - SMALL (3KGS MAX)      Php: 370");
                System.out.println("[3] - MEDIUM (5KGS MAX)     Php: 720");
                System.out.println("[4] - LARGE (10KGS MAX)     Php: 1120");
                System.out.println("[5] - XL (20KGS MAX)        Php: 1420");
                System.out.print("Choose size: ");
                size = sc.nextInt();

                if (size == 1) {
                    price = 150;
                }else if (size == 2) {
                    price = 370;
                }else if (size == 3) {
                    price = 720;
                }else if (size == 4) {
                    price = 1120;
                }else if (size == 5) {
                    price = 1420;
                }else{
                    System.out.println("====== INVALID BOX SIZE. TRY AGAIN ======");
                } break;
            case 4:
                region = "MINDANAO";
                System.out.println("\n BOX SIZE FOR ITEMS | Region: " + region + " :");
                System.out.println("[1] - MINI (1KG MAX)        Php: 150");
                System.out.println("[2] - SMALL (3KGS MAX)      Php: 370");
                System.out.println("[3] - MEDIUM (5KGS MAX)     Php: 720");
                System.out.println("[4] - LARGE (10KGS MAX)     Php: 1120");
                System.out.println("[5] - XL (20KGS MAX)        Php: 1420");
                System.out.print("Choose size: ");
                size = sc.nextInt();

                if (size == 1) {
                    price = 150;
                }
                else if (size == 2) {
                    price = 370;
                }else if (size == 3) {
                    price = 720;
                }else if (size == 4) {
                    price = 1120;
                }else if (size == 5) {
                    price = 1420;
                }
                break;
            default: 
                System.out.println("====== INVALID REGION OF CHOICE. TRY AGAIN ======");
                return;
            }
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expiry = now.plusDays(3);
            Random r = new Random();
            
            int receiptID = 100000 + r.nextInt(100000);
        box newBox = new box("PENDING", receiptID, senderName, senderAddress, senderContact, senderEmail,
                receiverName, receiverAddress, receiverContact, receiverEmail,
                items, region, price, now.format(formatter), expiry.format(formatter), user.getEmail()); 
        
        boxList.add(newBox); 
        saveBoxes(boxList); 


    System.out.println("\n---------- SHIPX RECEIPT " + receiptID + "---------");
    System.out.println("Date Printed: " + now.format(formatter));
    System.out.println("Valid Until: " + expiry.format(formatter));
    System.out.println("\n----------SENDER INFORMATION---------");
    System.out.println("Sender: " + senderName);
    System.out.println("Sender address: " + senderAddress);
    System.out.println("Sender email: " + senderEmail);
    System.out.println("Sender contact number: " + senderContact);
    System.out.println("\n----------RECEIVER INFORMATION---------");
    System.out.println("Receiver: " + receiverName);
    System.out.println("Receiver address: " + receiverAddress);
    System.out.println("Receiver email: " + receiverEmail);
    System.out.println("Receiver contact number: " + receiverContact);
    System.out.println("\n----------BOX INFORMATION---------");
    System.out.println("Items: " + items);
    System.out.println("Region of Destination: " + region);
    System.out.println("Total Price: Php " + price);
    //System.out.println("Mode of Payment: ");
    System.out.println("----------IMPORTANT NOTICE---------");
    System.out.println("\n This receipt is only valid for 3 days. "
            + "\n If the package will not be dropped off \n at ShipX Branch within that time, \n"
            + "your delivery request will be canceled. ");
    
    System.out.println("\n-------------END OF RECEIPT-----------");
        
            }
            catch(Exception exc){
            System.out.println("====== INVALID INPUT. TRY AGAIN ======");
        }
    }
   
  public void saveBoxes(ArrayList<box> boxList){
      File file = new File("boxList.txt");
      try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
         oos.writeObject(boxList);
      }catch(Exception exc){
      }
  }

    private void viewBoxStatus() {
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>) ois.readObject();
            } catch (Exception e) {
                return;
            }
        }

        if (boxList.isEmpty()) {
            System.out.println("    NO BOXES FOUND.  ");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        boolean found = false;

        // Update expired boxes
        for (box b : boxList) {
            try {
                LocalDateTime expiry = LocalDateTime.parse(b.getExpiryTimestamp(), formatter);
                if (now.isAfter(expiry) && b.getStatus().equalsIgnoreCase("PENDING")) {
                    b.setStatus("EXPIRED");
                }
            } catch (Exception exc) {
            }
        }

        saveBoxes(boxList);

        System.out.println("------------- VIEW BOX STATUS------------- ");
        for (box b : boxList) {
            // Filter by logged-in user's email
            if (b.getUserEmail() != null && b.getUserEmail().equalsIgnoreCase(user.getEmail())) {
                System.out.println(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("    NO BOXES FOUND FOR YOUR ACCOUNT.    ");
        }
    }

    private void viewReceipts() {
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>) ois.readObject();
            } catch (Exception e) {
                return;
            }
        }

    if (boxList.isEmpty()) {
        System.out.println("    NO RECEIPTS FOUND.  ");
        return;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    boolean found = false;
    for (box b : boxList) {
        try {
            LocalDateTime expiry = LocalDateTime.parse(b.getExpiryTimestamp(), formatter);
            if (now.isAfter(expiry)) {
                b.setStatus("EXPIRED");
            }
        } catch (Exception exc) {
        }
    }
    
    saveBoxes(boxList);
    
        System.out.println("------------- VIEW RECEIPT HISTORY------------- ");
        for (box b : boxList) {
            // Filter by logged-in user's email AND pending/expired status
            if (b.getUserEmail() != null && b.getUserEmail().equalsIgnoreCase(user.getEmail())) {
                if (b.getStatus().equalsIgnoreCase("PENDING") || b.getStatus().equalsIgnoreCase("EXPIRED")) {
                    System.out.println(b);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("    NO PENDING/EXPIRED RECEIPTS WERE FOUND FOR YOUR ACCOUNT.    ");
        }
    }

    /**
     * View all notifications for the logged-in user
     */
    private void viewNotifications() {
        System.out.println("\n------------- YOUR NOTIFICATIONS ------------- ");

        ArrayList<Notification> notifications = NotificationManager.getNotificationsForUser(user.getEmail());

        if (notifications.isEmpty()) {
            System.out.println("    NO NOTIFICATIONS.    ");
            return;
        }

        // Count unread notifications
        int unreadCount = NotificationManager.countUnreadNotifications(user.getEmail());
        if (unreadCount > 0) {
            System.out.println("You have " + unreadCount + " new notification(s).\n");
        }

        // Display all notifications
        for (Notification notif : notifications) {
            System.out.println(notif);
        }

        // Mark all as read
        NotificationManager.markAllAsRead(user.getEmail());
        System.out.println("\n------------- ALL NOTIFICATIONS MARKED AS READ ------------- ");
    }

    public static void contactShipX() {
        System.out.println("\n-------------  CONTACT US! ------------- ");
        System.out.println(" Address: ShipX Main Branch, Unit 203, Skyline Business Center, Katipunan Avenue,\n "
                + "\n Loyola Heights, Quezon City, Metro Manila, 1108");
        System.out.println("️  Hotline: (02) 902-2006");
        System.out.println("  Phone #: +63 921 317 9195");
        System.out.println("  Email: support@shipx.com");
        System.out.println("  Website: www.shipx.com.ph");
        System.out.println("\n ------ SOCIAL MEDIA ACCOUNTS ------");
        System.out.println("  Facebook  : ShipX Official");
        System.out.println("  Instagram : @shipX_Official");
        System.out.println("  TikTok    : @shipX_Official");
    }
    }