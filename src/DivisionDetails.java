/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class DivisionDetails {
    private int code;
    private String division;
    private String description;
    
    public DivisionDetails(int code,String division,String description){
        this.code=code;
        this.division=division;
        this.description=description;
    }
    public DivisionDetails(){
        this.code=0;
        this.division=null;
        this.description=null;
    }
    
    //setters
    public void setCode(int code){
        this.code=code;
    }
    public void setDivision(String division){
        this.division=division;
    }
    public void setDescription(String description){
        this.description=description;
    }
    
    //getters
    public int getCode(){
        return code;
    }
    public String getDivision(){
        return division;
    }
    public String getDescription(){
        return description;
    }
}
