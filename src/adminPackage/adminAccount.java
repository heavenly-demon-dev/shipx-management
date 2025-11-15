/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminPackage;

/**
 *
 * @author USER
 */
import App.*;
import employeePackage.*;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Iterator;


public class adminAccount  {
    Scanner sc = new Scanner(System.in);
    
public static void loginAdmin() {
                   
                   Scanner sc = new Scanner(System.in);
    
                    
                        System.out.println("----------ADMIN LOGIN----------");
                        System.out.print("Enter admin email: ");
                        String adEmail = sc.nextLine();
                        System.out.print("Enter password: ");
                        String adPassword = sc.nextLine();     
                        
                       if(adLogin(adEmail, adPassword)){
                       System.out.println(" ");
                       System.out.println("---------------------------");
                       System.out.println("ADMIN HAS BEEN LOG IN!");
                       System.out.println("---------------------------");
                       adminManagement adMan = new adminManagement();
                       adMan.adminMenu();
                        }
                    }

 public static boolean adLogin(String email, String password){
     File file = new File("adminAccounts.txt");
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            ArrayList<adminInfo> adminList = (ArrayList<adminInfo>)
                    ois.readObject();
            for(adminInfo adIn : adminList){
                if(adIn.getEmail().equals(email) && adIn.getPassword().equals(password)){
                    if(adIn.getStatus().equalsIgnoreCase("Disabled")){ 
                       System.out.println("\n---------------------------");
                       System.out.println("YOUR ACCOUNT HAS BEEN DISABLED");
                       System.out.println("---------------------------\n");
                       return false; 
                    }
                    return true;
                }
            }
        
    }catch(Exception exc){
            System.out.println("Error loading admin data : "  + exc.getMessage());
    }
        return false;
 }
}