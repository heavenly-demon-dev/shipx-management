    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package adminPackage;
    import customerPackage.User;
    import employeePackage.*;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.util.ArrayList;
    import java.util.Scanner;
import java.util.Set;

    /**
     *
     * @author USER
     */


    public class adminManagement {
        
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
            while(true){
            System.out.println("----------ADMIN MENU------------");
            System.out.println("A. MANAGE ADMINS");
            System.out.println("B. MANAGE COURIER");
            System.out.println("C. MANAGE MANAGERS");
            System.out.println("D. MANAGE USER");
            System.out.println("E. EXIT");
            System.out.println("--------------------------------");
            System.out.print("SELECT A CHOICE: ");

            char input = sc.next().charAt(0);
            sc.nextLine();

            if(input == 'A' || input == 'a'){
                manageAdminMenu();
            }else if(input == 'B' || input == 'b'){
                manageCourierMenu();
            }else if(input == 'C' || input == 'c'){
                    manageManagerMenu();
            } else if(input == 'D' || input == 'd'){
               manageUserMenu();
            }else if(input == 'E' || input == 'e'){
                employeeAccount empAcc = new employeeAccount();
                empAcc.selectId();
            }
            else{
                System.out.println("======ENTER VALID INPUT======");
            }
            }
        }

        public static void manageAdminMenu() {
            Scanner sc = new Scanner(System.in);

            ArrayList<adminInfo> adminList = new ArrayList<>();
            File file = new File("adminAccounts.txt");

            if (file.exists() && file.length() > 0) {
                try {
                   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    adminList = (ArrayList<adminInfo>) 
                            ois.readObject(); 
                    ois.close();
                } catch (Exception exc) { //checks if file is corrupted, or if the file doesnt contain expected object
                    System.out.println("Error loading admin data: " + exc.getMessage());
                    adminList = new ArrayList<>();
                }
            }

            int choice = -1;
            do {
                System.out.println("-------MANAGE ADMIN ACCOUNT-------");
                System.out.println("1 - Add new admin");
                System.out.println("2 - View admin list");
                System.out.println("3 - Search admin");
                System.out.println("4 - Remove admin");
                System.out.println("5 - Update Account Status");
                System.out.println("6 - Exit");
                System.out.print("Enter a choice: ");

                try {
                    choice = sc.nextInt();
                    sc.nextLine(); 

                    switch (choice) {

                        case 1:
                            while (true) {
                                System.out.print("Enter admin name           : ");
                                String adminName = sc.nextLine();
                                System.out.print("Enter admin email address  : ");
                                String adminEmail = sc.nextLine();
                                System.out.print("Enter admin password       : ");
                                String adminPass = sc.nextLine();

                                if(adminName.isBlank() || adminEmail.isBlank() || adminPass.isBlank()){
                                    System.out.println("PLEASE FILL UP ALL THE REQUIREMENTS. TRY AGAIN.");
                                    continue;
                                }

                                boolean emailExists = false;
                                for(adminInfo adIn : adminList){
                                        if(adIn.getEmail().equalsIgnoreCase(adminEmail)){
                                            emailExists = true;
                                            break;
                                        }
                                }
                                if(emailExists) {
                                    System.out.println("AN ACCOUNT WITH THE THIS EMAIL ALREADY EXISTS. PLEASE USE ANOTHER EMAIL.");
                                    continue;
                                }

                                adminInfo newAdmin = new adminInfo(adminName, adminEmail, adminPass);
                                newAdmin.setStatus("Active"); 
                                adminList.add(newAdmin);
                                
                                saveAdmins(adminList);
                                System.out.println("\n--------------------------");
                                System.out.println("ADMIN ADDED SUCCESSFULLY!");
                                System.out.println("--------------------------");
                                break;
                            }
                            break;

                        case 2:
                            while(true){
                                if (adminList.isEmpty()) {
                                System.out.println("NO ADMIN ACCOUNT(S) FOUND.");
                            } else {
                                System.out.println("------ADMIN LIST------");
                                for (adminInfo adminInfo : adminList) {
                                    System.out.println(adminInfo);
                                }
                            }

                            System.out.print("\n Press [0] to go back to MENU : ");
                            int back = sc.nextInt();
                            sc.nextLine();
                                System.out.println(" ");
                            if(back == 0)
                                break;
                            }
                            break;
                        case 3:
                            while (true) {
                                System.out.print("Enter admin email to search: ");
                                String searchEmail = sc.nextLine();
                                boolean found = false;

                                for (adminInfo adInfo : adminList) {
                                    if (adInfo.getEmail().equals(searchEmail)) {
                                        System.out.println("\n----------------------");
                                        System.out.println("ADMIN FOUND: " + adInfo);
                                        System.out.println("----------------------\n");
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    System.out.println("\n----------------------");
                                    System.out.println("ACCOUNT NOT EXISTING.");
                                    System.out.println("----------------------");
                                }
                                break;
                            }
                            break;

                        case 4:
                            while (true) {
                                System.out.print("Enter admin email to remove: ");
                                String removeEmail = sc.nextLine();
                                boolean remove = false;

                                for(int i = 0; i < adminList.size(); i++){
                                    adminInfo adIn = adminList.get(i);
                                    if(adIn.getEmail().equalsIgnoreCase(removeEmail)){
                                        adminList.remove(i);
                                        remove = true;
                                        break;
                                    }
                                }

                                if (remove) {
                                   saveAdmins(adminList);
                                    System.out.println("\n----------------------");
                                    System.out.println("ADMIN HAS BEEN REMOVED");
                                    System.out.println("----------------------");
                                } else {
                                    System.out.println("\n----------------------");
                                    System.out.println("ADMIN NOT EXISTING");
                                    System.out.println("----------------------");
                                }
                                break;
                            }
                            break;
                        case 5:
                         System.out.print("Enter admin email to modify account status: ");
                         String adEmail = sc.nextLine();
                         boolean foundEmail = false;
                         
                         for(adminInfo adEmp : adminList){
                             if(adEmp.getEmail().equalsIgnoreCase(adEmail)){
                                 foundEmail = true;
                                 System.out.println("Current Account Status : " + adEmp.getStatus());
                        System.out.println("1 - Disable Account");
                        System.out.println("2 - Enable Account");
                        System.out.print("Enter choice: ");
                        int statusChoice = sc.nextInt();
                        sc.nextLine();
                        
                        switch(statusChoice){
                            case 1:
                                adEmp.setStatus("Disabled");
                                saveAdmins(adminList);
                                break;
                            case 2:
                                adEmp.setStatus("Active");
                                saveAdmins(adminList);
                                break;
                            default: System.out.println("Enter a valid choice. [1] and [2] only.");
                        }
                             }
                         }
                         break;
                    }
                } catch (Exception exc) {
                    System.out.println("====== INVALID INPUT: Please enter a valid number (1-5) ======");
                    sc.nextLine();
                }
            } while (choice != 6);
        }

        // SAVE ADMINS TO FILE
        public static void saveAdmins(ArrayList<adminInfo> adminList ) {
           File file = new File("adminAccounts.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(adminList); 
            } catch (Exception exc) {
                System.out.println("Error saving admins: " + exc.getMessage());
            }
        }

       public static void manageCourierMenu() {
        Scanner sc = new Scanner(System.in);
          ArrayList<adminEmp> cour = new ArrayList<adminEmp>();
         File file = new File("courierAccounts.txt");

        if (file.exists() && file.length() > 0) {
        try { 
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            cour = (ArrayList<adminEmp>) 
            ois.readObject();
            ois.close();
        } catch (Exception exc) {
            System.out.println("Error loading courier data: " + exc.getMessage());
            cour = new ArrayList<>();
        }
            }

            int choice = -1;
            do{

                System.out.println("B. MANAGE COURIER");
                System.out.println("----------------------");
                System.out.println("1 - Add new courier");
                System.out.println("2 - View courier list");
                System.out.println("3 - Search courier");
                System.out.println("4 - Remove courier");
                System.out.println("5 - Update Account Status");
                System.out.println("6 - Exit");

                  try {
                      System.out.print("Enter choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                 switch(choice){
                     case 1:

                          while (true) { 
                            System.out.println("---- ADD NEW COURIER ----");

                            System.out.print("Enter courier name               : ");
                                String courName = sc.nextLine();
                            System.out.print("Enter courier email address      : ");
                                String courEmail = sc.nextLine();
                            System.out.print("Enter courier password           : ");
                                String courPass = sc.nextLine();

                                if(courName.isBlank() || courEmail.isBlank() || courPass.isBlank()){
                                   System.out.println("PLEASE FILL UP ALL THE REQUIREMENTS. TRY AGAIN.");
                                    continue;
                                }
                                boolean emailExists = false;
                                for(adminEmp adEmp : cour){
                                        if(adEmp.getEmail().equalsIgnoreCase(courEmail)){
                                            emailExists = true;
                                            continue;
                                        }
                                }
                                if(emailExists) {
                                    System.out.println("AN ACCOUNT WITH THE THIS EMAIL ALREADY EXISTS. PLEASE USE ANOTHER EMAIL.");
                                    break;
                                }

                        adminEmp newCour = new adminEmp(courName, courEmail, courPass);
                        newCour.setStatus("Active");
                        cour.add(newCour);
                                 saveCouriers(cour);

                                System.out.println("\n--------------------------");
                                System.out.println("COURIER HAS BEEN ADDED");
                                System.out.println("--------------------------");
                            break;
                        }
                        break; 
                     case 2:
                         while (true) { 

                            if (cour.isEmpty()) {
                                System.out.println("No courier(s) found.");
                            } else {
                                System.out.println("------- COURIER LIST ------");
                                for (adminEmp adEmp : cour) {
                                    System.out.println(adEmp);
                                }
                            }
                             System.out.print("ENTER [0] to go back to MENU: ");
                            int back = sc.nextInt();
                            sc.nextLine();
                             System.out.println(" ");
                            if (back == 0) break; 
                        }
                        break;

                     case 3:
                         while (true) {

                            System.out.print("Enter courier email to search: ");
                            String searchEmail = sc.nextLine();
                            boolean found = false;

                            for (adminEmp adm : cour) {
                                if (adm.getEmail().equals(searchEmail)) {
                                    System.out.println("\n----------------------");
                                    System.out.println("COURIER FOUND: " + adm);
                                    System.out.println("----------------------");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("\n----------------------");
                                System.out.println("COURIER NOT EXISTING");
                                System.out.println("----------------------\n");
                            }
                            break;
                        }
                         break;
                     case 4:
                          while (true) {
                            System.out.print("Enter courier email to remove : ");
                            String removeEmail = sc.nextLine();
                            boolean remove = false;

                           for(int i = 0; i<cour.size(); i++){
                               adminEmp adm = cour.get(i);
                               if(adm.getEmail().equalsIgnoreCase(removeEmail)){
                               cour.remove(i);
                               remove = true;
                               break;
                           }
                           }
                           if(remove){
                               saveCouriers(cour);
                               System.out.println("\n--------------------------");
                                System.out.println("COURIER HAS BEEN REMOVED");
                                System.out.println("--------------------------\n");
                           }else{
                                    System.out.println("\n----------------------");
                                    System.out.println("COURIER NOT EXISTING");
                                    System.out.println("----------------------\n");
                           }
                          break;
                        }
                     case 5:
                         System.out.print("Enter courier email to modify account status: ");
                         String courEmail = sc.nextLine();
                         boolean foundEmail = false;
                         
                         for(adminEmp adEmp : cour){
                             if(adEmp.getEmail().equalsIgnoreCase(courEmail)){
                                 foundEmail = true;
                                 System.out.println("Current Account Status : " + adEmp.getStatus());
                        System.out.println("1 - Disable Account");
                        System.out.println("2 - Enable Account");
                        System.out.print("Enter choice: ");
                        int statusChoice = sc.nextInt();
                        sc.nextLine();
                        
                        switch(statusChoice){
                            case 1:
                                adEmp.setStatus("Disabled");
                                saveCouriers(cour);
                                break;
                            case 2:
                                adEmp.setStatus("Active");
                                saveCouriers(cour);
                                break;
                            default: System.out.println("Enter a valid choice. [1] and [2] only.");
                        }
                             }
                         }
                         break;
                    }
                } catch (Exception e) {
                    System.out.println("====== INVALID INPUT: Please enter a valid number (1-6) ======");
                    sc.nextLine(); 
                    choice = -1;
                }
            }while(choice !=6);
        }

    public static void saveCouriers(ArrayList<adminEmp> cour) {
              File file = new File("courierAccounts.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(cour);
            } catch (Exception exc) {
                System.out.println("ERROR SAVING COURIERS ! " + exc.getMessage());
            }
        }

    public static void manageManagerMenu(){
        
        Scanner sc = new Scanner(System.in);
        ArrayList<adminEmp> managerList = new ArrayList<>();
        File file = new File("managerAccounts.txt");

        if(file.exists() && file.length() >0){
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                managerList = (ArrayList<adminEmp>)
                        ois.readObject();
            }catch(Exception exc){
                System.out.println("Error loading manager data: " + exc.getMessage());
                managerList = new ArrayList<>();
            }
        }
        int choice = -1;
        do{
             System.out.println("C. MANAGE MANAGERS");
                System.out.println("----------------------");
                System.out.println("1 - Add new manager");
                System.out.println("2 - View manager list");
                System.out.println("3 - Search manager");
                System.out.println("4 - Remove manager");
                System.out.println("5 - Update Account status");
                System.out.println("6 - Exit");
            try{
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch(choice){
                    case 1:
                        while(true){
                            System.out.println("---- ADD NEW MANAGER ----");
                                System.out.print("Enter manager name        : ");
                                String manName = sc.nextLine();
                                System.out.print("Enter manager email       : ");
                                String manEmail = sc.nextLine();
                                System.out.print("Enter manager password    : ");
                                String manPass = sc.nextLine();
                                if(manName.isBlank() || manEmail.isBlank() || manPass.isBlank()){
                                    System.out.println("====== PLEASE FILL UP ALL THE REQUIREMENTS. TRY AGAIN.======\n");
                                    continue;
                                }

                                boolean exist = false;
                                for(adminEmp adEmp : managerList){
                                    if(adEmp.getEmail().equalsIgnoreCase(manEmail)){
                                        exist = true;
                                        break;
                                    }
                                    if(exist){
                                        System.out.println("AN ACCOUNT WITH THIS EMAIL ALREADY EXISTS. PLEASE USE ANOTHER EMAIL.");
                                    continue;
                                    }
                                }
                                adminEmp newMan = new adminEmp(manName, manEmail, manPass);
                                newMan.setStatus("Active"); 
                                managerList.add(newMan);
                                saveManagers(managerList);
                               System.out.println("\n--------------------------");
                                System.out.println("MANAGER HAS BEEN ADDED");
                                System.out.println("--------------------------");
                            break;
                        }
                        break;
                    case 2:
                        if(managerList.isEmpty()){
                            System.out.println("    NO MANAGERS FOUND.");
                        }else{
                            System.out.println("------- MANAGER LIST ------");
                            for(adminEmp adEmp : managerList){
                                System.out.println(adEmp);
                            }
                        }
                        break;
                    case 3: 
                        System.out.println("Enter manager email to search: ");
                        String searchManEmail = sc.nextLine();
                        boolean found = false;

                        for(adminEmp adEmp : managerList){
                            if(adEmp.getEmail().equals(searchManEmail)){
                                System.out.println("\n----------------------");
                                    System.out.println("MANAGER FOUND: " + adEmp);
                                    System.out.println("----------------------");
                            found = true;
                           break;
                            }
                        if(!found){
                            System.out.println("\n----------------------");
                                System.out.println("MANAGER NOT EXISTING");
                                System.out.println("----------------------\n");
                        }
                        }
                        break;
                    case 4:
                        System.out.println("Enter manager email to remove");
                        String removeManEmail = sc.nextLine();
                        boolean remove = false;

                        for(adminEmp adEmp : managerList){
                        if(adEmp.getEmail().equalsIgnoreCase(removeManEmail)){
                            managerList.remove(removeManEmail);
                            remove = true;
                            break;
                        }
                        if(remove){
                            saveManagers(managerList);
                            System.out.println("\n--------------------------");
                            System.out.println("MANAGER HAS BEEN REMOVED");
                            System.out.println("--------------------------");
                        }else{
                             System.out.println("\n----------------------");
                            System.out.println("MANAGER NOT EXISTING");
                            System.out.println("----------------------");
                        }
                        break;
                    }
                    case 5:
                        System.out.println("Enter manager email to modify status: ");
                        String manEmail = sc.nextLine();
                        boolean foundManEmail = false;
                        
                        for(adminEmp adEmp : managerList){
                       if(adEmp.getEmail().equalsIgnoreCase(manEmail)){
                           foundManEmail = true;
                           
                        System.out.println("Current Account Status : " + adEmp.getStatus());
                        System.out.println("1 - Disable Account");
                        System.out.println("2 - Enable Account");
                        System.out.print("Enter choice: ");
                        int statusChoice = sc.nextInt();
                        sc.nextLine();
                        
                        switch(statusChoice){
                            case 1:
                                adEmp.setStatus("Disabled");
                                saveManagers(managerList);
                                break;
                            case 2: 
                                adEmp.setStatus("Active");
                                saveManagers(managerList);
                                break;
                            default: System.out.println("Enter a valid choice. [1] and [2] only.");
                        }
                       }
                       if(!foundManEmail){
                           System.out.println("    MANAGER NOT FOUND");
                       }
                    }
                }
            }catch(Exception exc ){
                System.out.println("====== INVALID INPUT: Please enter a valid number (1-6) ======");
            }
        }while(choice != 6);
    }

    private static void saveManagers(ArrayList<adminEmp> managerList){
        File file = new File("managerAccounts.txt");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(managerList);
        }catch(Exception exc){
            System.out.println("Error loading manager data : " + exc.getMessage());
        }
    }

    public  static void  manageUserMenu(){
            Scanner sc = new Scanner(System.in);
            int choice;
            ArrayList<User> userList = new ArrayList<>();
            File file = new File("userAccounts.txt");

            if(file.exists() && file.length() > 0) {
                try{
                    ObjectInputStream ios = new ObjectInputStream(new FileInputStream(file));
                    userList = (ArrayList<User>)
                            ios.readObject();
                    ios.close();

                }catch (Exception exc){
                    System.out.println("Error loading data" + exc.getMessage());
                    userList = new ArrayList<>();
                }
            }
            do {
                System.out.println("-------MANAGE USER ACCOUNTS-------");
                System.out.println("1 - View All User Accounts");
                System.out.println("2 - Delete User Account");
                System.out.println("3 - Update Account Status");
                System.out.println("4 - Back to Admin Menu");
                System.out.println("----------------------------------");
                System.out.print("Choose an option: ");

                try {
                    choice = sc.nextInt();
                    sc.nextLine(); 

                    switch (choice) {
                        case 1:
                            if (userList.isEmpty()) {
                                System.out.println("    NO CUSTOMER ACCOUNTS FOUND.");
                            } else {
                                System.out.println("\n----------- CUSTOMER ACCOUNT LIST -----------");
                                for (User user : userList) {
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("Name             : " + user.getName());
                                    System.out.println("Age              : " + user.getAge());
                                    System.out.println("Email            : " + user.getEmail());
                                    System.out.println("Password         : " + user.getPassword()); 
                                    System.out.println("Acoount Status : " + user.getStatus()); 
                                }
                                System.out.println("-----------------------------------------------");
                            }
                            break;
                        case 2:
                            while(true){
                                System.out.println("Enter user email to delete : ");
                                String removeEmail = sc.nextLine();
                                boolean remove = false;

                                for(User us : userList){
                                   if(us.getEmail().equalsIgnoreCase(removeEmail)){
                                       userList.remove(removeEmail);
                                       remove = true;
                                       break;
                                   }
                                }
                               if(remove){
                                   saveUsers(userList);
                                   System.out.println("\n--------------------------");
                                System.out.println("USER HAS BEEN REMOVED");
                                System.out.println("--------------------------\n");
                               }else{
                                    System.out.println("\n----------------------");
                                    System.out.println("USER NOT EXISTING");
                                    System.out.println("----------------------\n");
                               }
                               break;
                            }
                                break;
                        case 3:
                            System.out.println("Enter customer email to modify account status: ");
                            String usrEmail = sc.nextLine();
                            boolean found = false;
                            
                            for(User us : userList){
                                if(us.getEmail().equals(usrEmail)){
                                    found = true;
                        System.out.println("Current Account Status : " + us.getStatus());
                        System.out.println("1 - Disable Account");
                        System.out.println("2 - Enable Account");
                        System.out.print("Enter choice: ");
                        int statusChoice = sc.nextInt();
                        sc.nextLine();
                        
                        switch(statusChoice){
                            case 1:
                                us.setStatus("Disabled");
                                saveUsers(userList);
                                break;
                            case 2:
                            us.setStatus("Active");
                            saveUsers(userList);
                            break;
                            default:
                                System.out.println("Enter a valid choice. [1] and [2] only.");
                        }
                        if(!found){
                            System.out.println("    CUSTOMER ACCOUNT NOT FOUND");
                        }
                         }
                            break;
                                }
                       case 4: 
                            return;
                    }
                } catch (Exception e) {
                    System.out.println("====== INVALID INPUT: Please enter a valid number (1-4)======");
                    sc.nextLine(); 
                    choice = -1; 
                }
            } while (choice != 4);
        }
     public static void saveUsers(ArrayList<User> userList) {
            File file = new File("userAccounts.txt");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(userList);
            } catch (IOException exc) {
                System.out.println("Error saving user accounts: " + exc.getMessage());
            }
        }
    }