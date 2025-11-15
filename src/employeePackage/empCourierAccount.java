/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeePackage;

import java.util.Scanner;

/**
 *
 * @author USER
 */
import adminPackage.*;
import boxPackage.*;
import employeePackage.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;



//THIS CLASS IS FOR LOG IN VALIDATION: 
public class empCourierAccount {
    
    Scanner sc = new Scanner(System.in);
    
    public void courierLogIn() 
                   {
                       System.out.println("----------COURIER LOGIN----------");
                   System.out.print("Enter courier email: ");
                   String courEmail = sc.nextLine();
                   System.out.print("Enter password: ");
                   String courPass = sc.nextLine();
                   
                   
                   //same dun sa admin account, titignan nya if verified ba na may gantong nakasave na acc dun sa file
                   if (courLogIn(courEmail, courPass)) {
                       System.out.println("\n---------------------------");
                       System.out.println("COURIER HAS BEEN LOG IN!");
                       System.out.println("---------------------------");
                       
                       courBoxDetails boxD = new courBoxDetails();
                       boxD.displayBoxMenu(); 
                   }
               }
    
    public static boolean courLogIn(String email, String password){ /*chinecheck nya if ung mga email and pass is
                                                nagm match sa mga employee data na naka store dun sa storage.*/
        File file = new File("courierAccounts.txt"); //storage file for employee accounts
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            ArrayList<adminEmp> courList = (ArrayList<adminEmp>)
                    ois.readObject();
            for(adminEmp adEmp : courList){
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
            System.out.println("\n ---------------------------");
            System.out.println("INVALID ACCOUNT! ACCESS DENIED");
            System.out.println("---------------------------");
        }catch(Exception exc){
            System.out.println("Error loading courier data: " + exc.getMessage());
        }
        return false;
}
}
    
