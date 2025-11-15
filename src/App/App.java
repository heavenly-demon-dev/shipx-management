/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package App;

/**
 *
 * @author USER
 */

import customerPackage.*;
import employeePackage.employeeAccount;


import java.util.Scanner;
public class App {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
         Scanner sc = new Scanner(System.in);
         
        System.out.println("------------------------------------");
        System.out.println("|             WELCOME TO           |");
        System.out.println("|              SHIPX!              |");
        System.out.println("------------------------------------");
        
    int option = 0;
           do{
               try{
               System.out.println(" ");
               System.out.println("-------------LOG IN AS:-------------");
               System.out.println(" ");
               System.out.println("1 - CUSTOMER");
               System.out.println("2 - SHIPX EMPLOYEE");
                   System.out.println("3 - EXIT");
               System.out.print("Choose an option : ");
               option = sc.nextInt();
               sc.nextLine();
               
           switch (option){
               case 1:
                   customerAccounts userAcc = new customerAccounts();
                   userAcc.displayMenu();
                   return;
               case 2:
                   employeeAccount empAcc = new employeeAccount();
                   empAcc.selectId();
                   break;
               case 3:
                   System.out.println("THANK YOU FOR CHOOSING SHIPX");
                    System.exit(0);
            }
           }catch(Exception ioExc){
               System.out.println(" ");
               System.out.println("=====Enter Valid Choice=====");
                sc.nextLine();
           }
       }while(option != 3);
           }
    }


    
    

