/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managerPackage;

/**
 *
 * @author USER
 */
import adminPackage.*;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class managerAccount {
    Scanner sc = new Scanner(System.in);
    public void managerLogin(){
                   System.out.println("----------MANAGER LOGIN----------");
                   System.out.print("Enter manager email: ");
                   String manEmail = sc.nextLine();
                   System.out.print("Enter password: ");
                   String manPass = sc.nextLine();
                   
                   if(manLogin(manEmail, manPass)){
                       System.out.println("\n---------------------------");
                       System.out.println("MANAGER HAS BEEN LOG IN!");
                       System.out.println("---------------------------");
                       managerMenu manMenu = new managerMenu();
                       manMenu.manDisplayMenu();
                   }
    }
    
    public static boolean manLogin(String email, String password){
   File file = new File("managerAccounts.txt");
   try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
       ArrayList<adminEmp> managerList = (ArrayList<adminEmp>) 
               ois.readObject();
       for(adminEmp adEmp : managerList){
           if(adEmp.getEmail().equals(email) && adEmp.getPassword().equals(password)){
               if(adEmp.getStatus().equalsIgnoreCase("Disabled")){
                        System.out.println("\n---------------------------");
                       System.out.println("YOUR ACCOUNT HAS BEEN DISABLED");
                       System.out.println("---------------------------\n");
               return false;
               }
           return true;
           }
       }
        System.out.println("\n---------------------------");
       System.out.println("INVALID ACCOUNT! ACCESS DENIED");
       System.out.println("---------------------------");
   }catch(Exception exc){
   System.out.println("Error loading manager data: " + exc.getMessage());
   }
      return false;
   }
}