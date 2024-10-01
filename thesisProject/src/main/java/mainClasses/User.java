/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;
/**
 *
 * @author gerry
 */
public class User {
    int userID;
    String FName,LName,username,password;
    
    
    public int getUserID(){
        return userID;
    }
 
    public void setUserID(int userID){
        this.userID=userID;
    }

    
    public String getFName(){
        return FName;
    }
 
    public void setFName(String FName){
        this.FName=FName;
    }
    
    public String getLName(){
        return LName;
    }
 
    public void setLName(String LName){
        this.LName=LName;
    }
    
    public String getUsername(){
        return username;
    }
 
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
  
}
