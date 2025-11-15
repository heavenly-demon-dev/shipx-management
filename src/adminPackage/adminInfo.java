/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminPackage;

/**
 *
 * @author USER
 */

import java.io.Serializable;
import java.util.Scanner;

public class adminInfo implements Serializable { 
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
    private String status;
    
    public adminInfo(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.status = "active";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return status;
    }
    @Override
    public String toString() {
        return "\n Name: " + name + 
                "\n Email: " + email + 
                "\n Password : " + password +
                "\n Status: " + status ;
    }
    
     
}



