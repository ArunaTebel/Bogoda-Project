/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class WorkCodeDetails {
    private int code;
    private String work;
    private String description;
    
    public WorkCodeDetails(int code,String work,String description){
        this.code=code;
        this.work=work;
        this.description=description;
    }
    public WorkCodeDetails(){
        this.code=0;
        this.work=null;
        this.description=null;
    }
    
    //setters
    public void setCode(int code){
        this.code=code;
    }
    public void setWork(String work){
        this.work=work;
    }
    public void setDescription(String description){
        this.description=description;
    }
    
    //getters
    public int getCode(){
        return code;
    }
    public String getWork(){
        return work;
    }
    public String getDescription(){
        return description;
    }
}
