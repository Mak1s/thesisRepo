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
public class File {
    int fid,pid;
    int type;
    String contents;
    
    public int getFid(){
        return fid;
    }
    
    public void setFid(int fid){
        this.fid=fid;
    }

    public int getPid(){
        return pid;
    }
    
    public void setPid(int pid){
        this.pid=pid;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int type){
        this.type=type;
    }
    
    public String getContents(){
        return contents;
    }
    
    public void setContents(String contents){
        this.contents=contents;
    }
}
