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
public class ClassOnly {
    int pid,typeID;
    String classFrom,classTo;
    
    public int getTypeID(){
        return typeID;
    }
    
    public void setTypeID(int typeID){
        this.typeID=typeID;
    }
    
    public int getPid(){
        return pid;
    }
    
    public void setPid(int pid){
        this.pid=pid;
    }
        
    public String getClassFrom(){
        return classFrom;
    }
    
    public void setClassFrom(String classFrom){
        this.classFrom=classFrom;
    }
    
    public String getClassTo(){
        return classTo;
    }
    
    public void setClassTo(String classTo){
        this.classTo=classTo;
    }
}
