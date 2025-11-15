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
        ArrayList<box> boxList = new ArrayList<>();
        File boxDetailsFile = new File("boxDetails.txt");
        
        
           if (boxDetailsFile.exists() && boxDetailsFile.length() > 0) { 
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(boxDetailsFile))) { 
                boxList = (ArrayList<box>) 
                ois.readObject(); 
            } catch (Exception exc) { 
                System.out.println("Error loading box data: " + exc.getMessage());
                boxList = new ArrayList<>(); 
            }
        }
          int choice;
          
        while(true) {
           
            System.out.println("------------- MANAGE BOX DETAILS -------------");
            System.out.println("1 - Update box");
            System.out.println("2 - View box list");
            System.out.println("3 - Search box");
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
                  
                    if(boxList.isEmpty()){
                        System.out.println("NO BOXES AVAILABLE.");
                        break;
                    }
                    
                    System.out.println("UPDATE BOX");
                    System.out.print("ENTER BOX ID: ");
                    int updateID; 
                    try { 
                        updateID = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException exc) {
                        System.out.println("===== INVALID BOX ID. RETURNS TO MENU =====");
                        sc.nextLine();
                        break;
                    }
                    boolean updated = false;
                    
                    for(box bx: boxList){
                        if(bx.getReceiptID() == updateID){
                           
                            System.out.println("------- CURRENT BOX INFORMATION ------");
                            System.out.println("Status: " + bx.getStatus()); 
                            System.out.println("Shipment Date: " + bx.getShipmentDate()); 
                            System.out.println("Delivered Date: " + bx.getDeliveredDate()); 
                            System.out.println("----------------------------------");
                            
                            
                            System.out.print("Enter UPDATED STATUS (Shipped, In transit, Delivered): ");
                            String newStatus = sc.nextLine();
                            bx.setStatus(newStatus); 
                            
                           
                            try { 
                                System.out.print("Enter UPDATED SHIPMENT DATE (YYYY-MM-DD or 0 for no change): "); 
                                int newShipmentDate = sc.nextInt();
                                sc.nextLine();
                                if (newShipmentDate != 0) { 
                                    bx.setShipmentDate(newShipmentDate); 
                                }
                            } catch (InputMismatchException exc) {
                                System.out.println("=====INVALID INPUT FOR DATE=====");
                                sc.nextLine();
                            }
                            
                             
                             try { 
                                System.out.print("Enter UPDATED DELIVERED DATE (YYYYMMDD or 0 for no change): "); 
                                int newDeliveredDate = sc.nextInt();
                                sc.nextLine();
                                if (newDeliveredDate != 0) { 
                                    bx.setDeliveredDate(newDeliveredDate); 
                                }
                            } catch (InputMismatchException exc) {
                                System.out.println("=====INVALID INPUT FOR DELIVERED DATE====="); 
                                sc.nextLine();
                            }

                            updated = true;
                            System.out.println("Status, Shipment Date, and Delivered Date have been UPDATED."); 
                            break;
                        }
                    }
                    
                    if(updated){
                       
                        try (ObjectOutputStream updatedOos = new ObjectOutputStream(new FileOutputStream(boxDetailsFile))) { 
                           updatedOos.writeObject(boxList); 
                        } catch (IOException exc) {
                            System.out.println("ERROR saving updated box list: " + exc.getMessage()); 
                        }
                        System.out.println("BOX HAS BEEN SAVED SUCCESSFULLY to boxDetails.txt.");
                    } else {
                        System.out.println("ERROR: No box found with ID: " + updateID);
                    }
                    break;
                case 2:
                    
                    if (boxList.isEmpty()) {
                        System.out.println("NO BOXES FOUND");
                    } else {
                        System.out.println("------------- BOX LIST (COURIER VIEW) -------------"); 
                        for (box box : boxList) {
                            System.out.println(box); 
                        }
                    }
                    break;

                case 3:
                    if(boxList.isEmpty()){
                        System.out.println("NO BOXES AVAILABLE.");
                        break;
                    }
                    
                    System.out.println(" ");
                    System.out.print("Enter Box ID to search: ");
                    int searchID = sc.nextInt();
                    boolean found = false;

                    for (box b : boxList) {
                        if (b.getReceiptID() == searchID) {
                            System.out.println(" ");
                            System.out.println("BOX FOUND : " + b);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No box found with ID: " + searchID);
                    }
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
}
