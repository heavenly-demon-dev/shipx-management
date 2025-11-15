

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boxPackage;

import java.io.Serializable;

/**
 *
 * @author USER
 */

public class box implements Serializable {
     private static final long serialVersionUID = 1L;
             
    private String status;
    private int receiptID;
    private int shipmentDate;
    private int deliveredDate;
    private String senderName;
    private String senderAddress;
    private String senderContact;
    private String senderEmail; 
    private String receiverName;
    private String receiverAddress;
    private String receiverContact;
    private String receiverEmail;
    private String items; 
    private String region; 
    private double price; 
    private String receiptTimestamp;
    private String expiryTimestamp;
    private int boxID;
    private String userEmail; // Email of the user who created this box
    

    public box(String status, int receiptID, String senderName, String senderAddress, String senderContact, String senderEmail,
               String receiverName, String receiverAddress, String receiverContact, String receiverEmail,
               String items, String region, double price,
               String receiptTimestamp, String expiryTimestamp, String userEmail) {

        this.status = status;
        this.receiptID = receiptID;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.senderContact = senderContact;
        this.senderEmail = senderEmail;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverContact = receiverContact;
        this.receiverEmail = receiverEmail;
        this.items = items;
        this.region = region;
        this.price = price;
        this.receiptTimestamp = receiptTimestamp;
        this.expiryTimestamp = expiryTimestamp;
        this.boxID = boxID;
        this.userEmail = userEmail;
    }
     public void setReceiptID(int receiptID) { 
         this.receiptID = receiptID; 
     }
     public void setBoxID(int boxID){
         this.boxID = boxID;
     }
     public void setStatus(String status){
          this.status = status;
      }
    public void setSenderName(String senderName){
        this.senderName = senderName;
    } 
    
    public void setShipmentDate(int shipmentDate){
        this.shipmentDate = shipmentDate;
    }
    public void setDeliveredDate(int deliveredDate){
        this.deliveredDate = deliveredDate;
    }
  
    public String getStatus(){
        return status;
    }
    public int getBoxID(){
        return boxID;
    }
    public int getReceiptID(){
        return receiptID;
    }
    
        public int getShipmentDate(){
            return shipmentDate;
        }
        
  
    public int getDeliveredDate(){
        return deliveredDate;
    }
    
    public String getSenderName() {
        return senderName; 
    }
    
        public String getSenderAddress() { 
            return senderAddress; 
        }

        public String getSenderContact() {
            return senderContact; 
        }
    
    public String getSenderEmail() { 
        return senderEmail; 
    }
    
    public String getReceiverName() { 
        return receiverName; 
    }
    
        public String getReceiverAddress() {
            return receiverAddress; 
        }

    public String getReceiverContact() {
        return receiverContact; 
    }
    
    public String getReceiverEmail() {
        return receiverEmail; 
    }
    public String getReceiptTimestamp() { 
        return receiptTimestamp; 
    }
    public String getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return "\n----------- RECEIPT " + receiptID + " -----------\n" +
                "Status: " + status + "\n" +
                "Receipt printed: " + receiptTimestamp + "\n" +
                "Valid until: " + expiryTimestamp + "\n" +
                "Items: " + items + "\n" +
                "Region: " + region + "\n" +
                "Price: " + price + "\n" +
                "\n----------- SENDER INFO -----------\n" +
                "Name: " + senderName + "\n" +
                "Address: " + senderAddress + "\n" +
                "Contact: " + senderContact + "\n" +
                "Email: " + senderEmail + "\n" +
                "\n----------- RECEIVER INFO -----------\n" +
                "Name: " + receiverName + "\n" +
                "Address/Drop-off: " + receiverAddress + "\n" +
                "Contact: " + receiverContact + "\n" +
                "Email: " + receiverEmail + "\n" +
                "----------------------------------------";
    }
}