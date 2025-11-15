    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package customerPackage;

import java.io.Serializable;

    /**
     *
     * @author USER
     */
   
   
    public class User implements Serializable{
      private static final long serialVersionUID = 1L; //compatibility checkkkkkkkkk
      
        private String name;
        private int age;
        private String email;
        private String password;
        private String status;
        private User loggedInUser;
        
       public User(String name, int age, String email, String password){
           this.name = name;
           this.age = age;
           this.email = email;
           this.password = password;
           this.status = "Active";
       }
       
       public String getName(){
           return name;
       }

       public int getAge(){
           return age;
       }

       public String getEmail(){
           return email;
       }
       
       public void setPassword(String password) { 
           this.password = password; 
       }
       
       public String getPassword(){
           return password;
       }
       
       public void setStatus(String status){
           this.status = status;
       }
       public String getStatus(){
           return status;
       }
       
      
       }
    
    
