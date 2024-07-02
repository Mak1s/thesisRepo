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
public class Project {
    int pid,userID;
    String name,date;
    
    public int getPid(){
        return pid;
    }
    
    public void setPid(int pid){
        this.pid=pid;
    }
    
    public int getUserID(){
        return userID;
    }
    
    public void setUserID(int userID){
        this.userID=userID;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }

    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date=date;
    }

}
