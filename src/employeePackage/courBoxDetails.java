/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeePackage;
/**
 *
 * @author USER
 */

import boxPackage.box;
import customerPackage.NotificationManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.InputMismatchException;


public class courBoxDetails {

    public void displayBoxMenu(){
        Scanner sc = new Scanner(System.in);
        int choice;

        while(true) {

            System.out.println("------------- MANAGE BOX DETAILS -------------");
            System.out.println("1 - PICK UP");
            System.out.println("2 - DROP OFF");
            System.out.println("3 - VIEW NON DELIVERED BOXES");
            System.out.println("4 - Log Out");
            System.out.print("Choose an option: ");
            
            try { 
                choice = sc.nextInt();
                sc.nextLine();
             } catch (InputMismatchException e) { 
                System.out.println("INVALID INPUT: Please enter a valid number (1-5).");
                sc.nextLine(); 
                choice = -1; 
                continue; 
            }
            switch (choice) {
                case 1:
                    pickUpBox();
                    break;
                case 2:
                    dropOffBox();
                    break;
                case 3:
                    viewNonDeliveredBoxes();
                    break;
                case 4:
                    System.out.println(" ");
                    return;
                default:
                    System.out.println("====== INVALID CHOICE: Please select a valid option (1-4) ======");
                    break;
            }

            

        }
    }

    /**
     * Pick up a box - Only allows boxes with status "Waiting for Courier"
     * Automatically updates status to "PICKED UP" and notifies customer
     */
    private void pickUpBox() {
        Scanner sc = new Scanner(System.in);
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();

        // Load boxes
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error loading box data: " + e.getMessage());
                return;
            }
        }

        if (boxList.isEmpty()) {
            System.out.println("NO BOXES AVAILABLE FOR PICK UP.");
            return;
        }

        System.out.println("\n------------- PICK UP BOX -------------");
        System.out.print("Enter Receipt ID: ");
        int receiptID;

        try {
            receiptID = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("INVALID RECEIPT ID.");
            sc.nextLine();
            return;
        }

        boolean found = false;
        for (box b : boxList) {
            if (b.getReceiptID() == receiptID) {
                found = true;

                // Validate: Only allow boxes with status "Waiting for Courier"
                if (!b.getStatus().equalsIgnoreCase("Waiting for Courier")) {
                    System.out.println("ERROR: This box cannot be picked up.");
                    System.out.println("Current Status: " + b.getStatus());
                    System.out.println("Only boxes validated by manager (status: 'Waiting for Courier') can be picked up.");
                    return;
                }

                // Update status to PICKED UP
                b.setStatus("PICKED UP");

                // Save updated box list
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(boxList);
                    System.out.println("\n---------------------------");
                    System.out.println("SUCCESS: Box #" + receiptID + " has been PICKED UP");
                    System.out.println("---------------------------");
                } catch (IOException e) {
                    System.out.println("ERROR saving box data: " + e.getMessage());
                    return;
                }

                // Send notification to customer
                if (b.getUserEmail() != null) {
                    NotificationManager.addNotification(
                        b.getUserEmail(),
                        receiptID,
                        "Your box has been picked up by the courier and is now in transit."
                    );
                    System.out.println("Notification sent to customer.");
                } else {
                    System.out.println("Warning: Box has no associated user email. Notification not sent.");
                }

                break;
            }
        }

        if (!found) {
            System.out.println("ERROR: No box found with Receipt ID: " + receiptID);
        }
    }

    /**
     * Drop off a box to recipient - Only allows boxes with status "PICKED UP"
     * Automatically updates status to "DELIVERED" and notifies customer
     */
    private void dropOffBox() {
        Scanner sc = new Scanner(System.in);
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();

        // Load boxes
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error loading box data: " + e.getMessage());
                return;
            }
        }

        if (boxList.isEmpty()) {
            System.out.println("NO BOXES AVAILABLE FOR DROP OFF.");
            return;
        }

        System.out.println("\n------------- DROP OFF BOX -------------");
        System.out.print("Enter Receipt ID: ");
        int receiptID;

        try {
            receiptID = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("INVALID RECEIPT ID.");
            sc.nextLine();
            return;
        }

        boolean found = false;
        for (box b : boxList) {
            if (b.getReceiptID() == receiptID) {
                found = true;

                // Validate: Only allow boxes with status "PICKED UP"
                if (!b.getStatus().equalsIgnoreCase("PICKED UP")) {
                    System.out.println("ERROR: This box cannot be dropped off.");
                    System.out.println("Current Status: " + b.getStatus());
                    System.out.println("Only boxes that have been picked up (status: 'PICKED UP') can be dropped off.");
                    return;
                }

                // Update status to DELIVERED
                b.setStatus("DELIVERED");

                // Save updated box list
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(boxList);
                    System.out.println("\n---------------------------");
                    System.out.println("SUCCESS: Box #" + receiptID + " has been DELIVERED");
                    System.out.println("---------------------------");
                } catch (IOException e) {
                    System.out.println("ERROR saving box data: " + e.getMessage());
                    return;
                }

                // Send notification to customer
                if (b.getUserEmail() != null) {
                    NotificationManager.addNotification(
                        b.getUserEmail(),
                        receiptID,
                        "Your box has been successfully delivered to the recipient."
                    );
                    System.out.println("Notification sent to customer.");
                } else {
                    System.out.println("Warning: Box has no associated user email. Notification not sent.");
                }

                break;
            }
        }

        if (!found) {
            System.out.println("ERROR: No box found with Receipt ID: " + receiptID);
        }
    }

    /**
     * View all boxes that haven't been delivered yet
     * Shows boxes with status: Waiting for Courier, PICKED UP, etc. (excludes DELIVERED and EXPIRED)
     */
    private void viewNonDeliveredBoxes() {
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();

        // Load boxes
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error loading box data: " + e.getMessage());
                return;
            }
        }

        if (boxList.isEmpty()) {
            System.out.println("NO BOXES FOUND.");
            return;
        }

        System.out.println("\n------------- NON-DELIVERED BOXES -------------");
        boolean found = false;

        for (box b : boxList) {
            // Show all boxes except DELIVERED and EXPIRED
            if (!b.getStatus().equalsIgnoreCase("DELIVERED") &&
                !b.getStatus().equalsIgnoreCase("EXPIRED")) {
                System.out.println(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("NO NON-DELIVERED BOXES FOUND. All boxes have been delivered or expired.");
        }
    }
}
