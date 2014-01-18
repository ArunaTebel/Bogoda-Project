/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Rate_Details {
    private int code;
    private String name;
    private String description;
    private String unit;
    private double rate;
    private String debitOrCredit;
    
    public Rate_Details(int code,String name,String description,double rate,String debitOrCredit){
        this.code=code;
        this.name=name;
        this.description=description;
        this.rate=rate;
        this.debitOrCredit=debitOrCredit;
    }
    
    public Rate_Details(){
        this.code=0;
        this.name=null;
        this.description=null;
        this.rate=0;
        this.debitOrCredit=null;
    }
    
    //setters
    public void setCode(int code){
        this.code=code;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setRate(double rate){
        this.rate=rate;
    }
    public void setDebitOrCredit(String debitOrCredit){
        this.debitOrCredit=debitOrCredit;
    }
    
    //getters
    public int getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public double getRate(){
        return rate;
    }
    public String getDebitOrCredit(){
        return debitOrCredit;
    }
    
}
