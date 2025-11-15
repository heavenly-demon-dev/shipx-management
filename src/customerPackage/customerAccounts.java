/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerPackage;

/**
 *
 * @author USER
 */

import App.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import java.util.Scanner;

public class customerAccounts {
    private Scanner sc = new Scanner(System.in);
    
  ArrayList<User> usrList = new ArrayList<>();
    File userAccountsFile = new File("userAccounts.txt"); //DITO PAPASOK LAHAT NG ACCOUNT SA TXT FILE 
    User user;
    
    
   public void displayMenu(){
       while(true)
       {
           try{
               System.out.println(" ");
               System.out.println("------------- CUSTOMER MENU -------------");
               System.out.println("1 - Sign Up");
               System.out.println("2 - Log in");
               System.out.println("3 - Exit");
               System.out.print("Choose an option : ");
               int option = sc.nextInt();
               sc.nextLine();
               
           switch (option){
               case 1:
                   registerAccount();
                   break;
               case 2:
                  logIn();
                   break;
               case 3:
                   App a = new App();
                   a.main(null);
                   break;
            }
           }catch(Exception ioExc){
               System.out.println("\n=====Enter Valid Choice=====");
                sc.nextLine();
           }
       }    
   }
    public void registerAccount() {
        System.out.println("-------SIGN UP FOR AN ACCOUNT-------");
        System.out.print("Enter complete name: ");
        String name = sc.nextLine();
        int age = 0;

    while (true) {
        System.out.print("Enter age          : ");
        String ageRaw = sc.nextLine().trim(); 
    if (ageRaw.isBlank()) {
        System.out.println("=======INPUT CANNOT BE EMPTY ========");
        continue;
    }
    try {
        age = Integer.parseInt(ageRaw); 
        if (age < 18) {
             System.out.println("------------------------------------------------------------------");
            System.out.println("YOU MUST BE 18 YEARS OLD AND ABOVE TO CREATE AN ACCOUNT.");
             System.out.println("-----------------------------------------------------------------");
            continue;
        }
        break; 
    } catch (NumberFormatException e) {
        System.out.println("ENTER A VALID NUMBER FOR AGE.");
    }
}
        System.out.print("Enter email        : ");
        String email = sc.nextLine();
        System.out.print("Enter password     : ");
        String password = sc.nextLine();
         if(name.isBlank() || email.isBlank() || password.isBlank()){
            System.out.println("====== PLEASE FILL UP ALL THE REQUIREMENTS. TRY AGAIN.======\n");
                       return;
                                }
         
        //i r run lang nya ung mga old users bago icheck ung duplicate para masave ung prev accs
        if (userAccountsFile.exists() && userAccountsFile.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userAccountsFile))) {
                usrList = (ArrayList<User>) 
                        ois.readObject();
            } catch (Exception exc) {
            }
        }
         
        // c check if ung email is nageexsits na para d magkaron ng duplicate
        boolean usrEmailExists = false;
         for (User us : usrList) {
            if (us.getEmail().equalsIgnoreCase(email)) {
                usrEmailExists = true;
                break;
            }
        }
         if(usrEmailExists){
             System.out.println("AN ACCOUNT WITH THE THIS EMAIL ALREADY EXISTS. \nPLEASE USE ANOTHER EMAIL OR TRY LOGGING IN");
             return;
         }

        // gagawa ng new user tapos s save nya dun sa file
        User newUser = new User(name, age, email, password);
        newUser.setStatus("Active");
        usrList.add(newUser);
        saveUsers(usrList);
        displayAccountInfo();
        
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("ACCOUNT HAS BEEN REGISTERED SUCCESSFULLY. YOU CAN NOW LOG IN.");
             System.out.println("-----------------------------------------------------------------");
    }

    public void displayAccountInfo() {
        if (user != null) {
            System.out.println("-------ACCOUNT INFORMATION-------");
            System.out.println("Name        : " + user.getName());
            System.out.println("Age         : " + user.getAge());
            System.out.println("Email       : " + user.getEmail());
        } 
    }

    private static void saveUsers(ArrayList<User> userList){
        File file = new File("userAccounts.txt");
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(userList); 
        } catch (Exception exc) {
        }
    }

    public void logIn() {
    System.out.println("\n------- SHIPX CUSTOMER LOG IN -------");
    System.out.print("Enter email: ");
    String email = sc.nextLine();
    System.out.print("Enter password: ");
    String password = sc.nextLine();

    user = customerLogIn(email, password);

    if (user != null) {
        System.out.println("\n---------------------------");
        System.out.println("      LOG IN SUCCESSFUL!");
        System.out.println("---------------------------");
        customerMenu cusMenu = new customerMenu(user); 
        cusMenu.userMenu();
    }
}
    
   public static User customerLogIn(String email, String password){
    File file = new File("userAccounts.txt");

    if (!file.exists() || file.length() == 0) {
        System.out.println("\n--------------------------------------------");
        System.out.println("NO EXISTING ACCOUNTS FOUND. PLEASE SIGN UP FIRST.");
        System.out.println("--------------------------------------------");
        return null;
    }

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        ArrayList<User> usrList = (ArrayList<User>) ois.readObject();

        for (User us : usrList) {
            if (us.getEmail().equalsIgnoreCase(email)) {
                
                // Check if the account is disabled, and ensure "status" comparison is done safely
                if (us.getStatus().trim().equalsIgnoreCase("Disabled")) { 
                    System.out.println("\n---------------------------");
                    System.out.println("YOUR ACCOUNT HAS BEEN DISABLED");
                    System.out.println("---------------------------\n");
                    return null; // Prevent login if the account is disabled
                }

                // Check if the password is correct
                if (!us.getPassword().equals(password)) {
                    System.out.println("\n---------------------------");
                    System.out.println("PASSWORD INCORRECT! ACCESS DENIED");
                    System.out.println("---------------------------");
                    return null; // Prevent login if password is incorrect
                }

                // If both email and password are correct, return the user
                return us;
            }
        }

        // If email is not found
        System.out.println("\n---------------------------");
        System.out.println("EMAIL NOT FOUND! ACCESS DENIED");
        System.out.println("---------------------------");
        return null;

    } catch (Exception exc) {
        System.out.println("Error reading user accounts file.");
        return null;
    }
}
}