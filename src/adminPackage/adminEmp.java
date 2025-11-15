/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package adminPackage;
import customerPackage.*;
import java.io.Serializable;

/**
 *
 * @author USER
 */

public class adminEmp extends User implements Serializable { //ininherit ko ung name, email, password ni user
     private static final long serialVersionUID = 1L; // para d magkaron ng serialization errors
    
    private String status;

    public adminEmp() {
        super("", 0, "", "");
        this.status = "active";
    }


    public adminEmp(String name, String email, String password) {
        super(name, 0, email, password);
          this.status = "active";
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    @Override
    public String toString() {
        return  "\nEmployee Name: " + getName() +
               "\nEmail: " + getEmail() +
               "\nPassword: " + getPassword() + 
                "\n Status: " + getStatus() + "\n";
    }
}