/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managerPackage;

import boxPackage.box;
import customerPackage.customerMenu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */

public class managerMenu {
    
    Scanner sc = new Scanner(System.in);
    public void manDisplayMenu(){
        
        while (true) {
            System.out.println("\n------------- MANAGER MENU -------------");
            System.out.println("1 - VALIDATE RECEIPT");
            System.out.println("2 - SEARCH RECEIPTS");
            System.out.println("3 - SEARCH BOXES");
            System.out.println("4 - VIEW ALL BOXES");
            System.out.println("5 - LOG OUT");
            System.out.print("Choose an option: ");
            int choice = -1;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\n=====Please Enter Valid Choice=====");
                sc.nextLine();
                continue;
            }
            
            switch(choice ){
                case 1: 
                    validateReceipt();
                    break;
                case 2:
                    searchReceiptID();
                    break;
                case 3:
                    searchBoxID();
                    break;
                case 4:
                    viewAllBoxes();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("====== INVALID INPUT. TRY AGAIN ======");
                  break;  
            }
    }
    }
    
    public void validateReceipt(){
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();
        if(file.exists() && file.length() > 0){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                boxList = (ArrayList<box>)
                        ois.readObject();
            }catch(Exception exc){
                System.out.println("Error loading box data: " + exc.getMessage());
                return;
            }
        }
        int searchID;
        System.out.print("Search Receipt ID: ");
        try{
          searchID  = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("\n=====Enter Valid Choice=====");
            sc.nextLine();
            return;
        }
        
        box found = null;
        for (box b : boxList) {
        if (b.getReceiptID() == searchID) {
            found = b;
            break;
        }
         }
        
        if (found == null) {
            System.out.println("Receipt not found.");
            
            return;
        }
        if(found.getStatus().equalsIgnoreCase("EXPIRED")){
            System.out.println("\n------------------------------------");
            System.out.println("THIS RECEIPT HAS ALREADY BEEN EXPIRED.");
            System.out.println("------------------------------------\n");

            return;
        }
        System.out.println("\n------------- RECEIPT DETAILS -------------");
        System.out.println("Receipt ID: " + found.getReceiptID());
        System.out.println("Current Status: " + found.getStatus());
        System.out.print("Confirm receipt? [Y/N]: ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
        found.setBoxID(found.getReceiptID()); 
        found.setStatus("Waiting for Courier");
           
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(boxList);
            System.out.println("\n---------------------------");
            System.out.println("RECEIPT HAS BEEN VALIDATED!");
            System.out.println("---------------------------\n");
            
            System.out.println("Box ID: " + found.getBoxID());
            System.out.println("New Status: " + found.getStatus());
        } catch (Exception exc) {
            System.out.println("Error saving box data: " + exc.getMessage());
        }
        }else {
        System.out.println(" Validation has been cancelled.");
         
     }
    }

    
    public void searchReceiptID(){
        ArrayList<box> boxList = new ArrayList<>();
        File file = new File("boxList.txt");
        if(file.exists() && file.length() > 0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                boxList = (ArrayList<box>)
                        ois.readObject();
            }catch(Exception exc ){
                System.out.println("Error loading box data: " + exc.getMessage());
            }
        }
        
        System.out.println("Search Receipt ID: ");
        int searchID = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        for(box b : boxList){
            if(b.getReceiptID() == searchID){
                found = true;
                System.out.println("Receipt has been found.");
                System.out.println(b);
                break;
            }
        }
        if(!found){
            System.out.println("   RECEIPT NOT FOUND");
        }
    }
    
    public void searchBoxID(){
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();
        if(file.exists() && file.length() > 0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                boxList = (ArrayList<box>)
                        ois.readObject();
            }catch(Exception exc){
                
            }
        }
        System.out.print("Search box ID: ");
       int searchBoxID = sc.nextInt();
       sc.nextLine();
       
       boolean found = false;
       for(box b : boxList){
           if(b.getBoxID() == searchBoxID){
               found = true;
               
               System.out.println("-------------BOX FOUND -------------");
               System.out.println(b);
               break;
           }
       }
       if(!found){
           System.out.println("BOX NOT FOUND");
       }
    }
    
    public void viewAllBoxes(){
        File file = new File("boxList.txt");
        ArrayList<box> boxList = new ArrayList<>();
        
        
        if(file.exists() && file.length() > 0){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                boxList = (ArrayList<box>)
                        ois.readObject();
            }catch(Exception exc){
                
            }
        }
        
        if(boxList.isEmpty()){
            System.out.println("-------------NO BOX AVAILABLE-------------");
            return;
        }
        
        System.out.println("-------------VIEW ALL BOXES -------------");
        for(box b: boxList){
            if(b.getBoxID() != 0){
                System.out.println(b);
            }
        }
        System.out.println("------------------------------------------");
    }
    
    
}
