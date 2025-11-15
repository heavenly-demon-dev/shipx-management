/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeePackage;

/**
 *
 * @author USER
 */
//IMPORTING FILE PACKAGE:
import adminPackage.*;
import App.*;


import java.util.Scanner;
import managerPackage.managerAccount;

public class employeeAccount {
    
    Scanner sc = new Scanner(System.in);
    
        public  void selectId(){ //select whose employee r u
        Scanner sc = new Scanner(System.in);
       while(true)
       {
           try{
               System.out.println(" ");
               System.out.println("-------------LOG IN AS:-------------");
               System.out.println(" ");
               System.out.println("1 - ADMIN");
               System.out.println("2 - COURIER");
               System.out.println("3 - MANAGER");
               System.out.println("4 - EXIT");
               System.out.print("Choose an option : ");
               int option = sc.nextInt();
               sc.nextLine();
               
           switch (option){
               case 1:
                   adminAccount adAcc = new adminAccount();
                 //adAcc.loginAdmin();
                adminManagement aa = new adminManagement();
                aa.adminMenu();
                   break;
                   
               case 2:
                   empCourierAccount empCo = new empCourierAccount();
                   empCo.courierLogIn();
                   break;
               case 3:
                   managerAccount manAcc = new managerAccount();
                   manAcc.managerLogin();
                   break;
               case 4:
                        App a = new App();
                        a.main(null);
                        return;
               default:
                     System.out.println("===== INVALID CHOICE: Please select 1-4 =====");
                    
                        break;
            }
           }catch(Exception ioExc){
               System.out.println(" ");
               System.out.println("===== Enter Valid Choice=====");
                sc.nextLine();
           }
       } 
    
}
}
