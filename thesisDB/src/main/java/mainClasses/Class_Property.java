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
public class Class_Property {
    int pid;
    String classFrom,classTo,additionalClass,propertyFrom,propertyTo,URI;
    
    
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

    public String getAdditionalClass(){
        return additionalClass;
    }
    
    public void setAdditionalClass(String additionalClass){
        this.additionalClass=additionalClass;
    }
    
    public String getPropertyFrom(){
        return propertyFrom;
    }
    
    public void setPropertyFrom(String propertyFrom){
        this.propertyFrom=propertyFrom;
    }
    
    public String getPropertyTo(){
        return propertyFrom;
    }
    
    public void setPropertyTo(String propertyTo){
        this.propertyTo=propertyTo;
    }
    
    public String getURI(){
        return URI;
    }
    
    public void setURI(String URI){
        this.URI=URI;
    }
}
