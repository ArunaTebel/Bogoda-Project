/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class LoanInfo {
    private double rate;
    private double amount;
    private int time;
    
    public LoanInfo(double rate,double amount,int time){
        this.rate=rate;
        this.amount=amount;
        this.time=time;
    }
    public LoanInfo(){
        this.rate=0;
        this.amount=0;
        this.time=0;
    }
    
    //setters
    public void setRate(double rate){
        this.rate=rate;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setTime(int time){
        this.time=time;
    }
    
    //getters
    public double getRate(){
        return rate;
    }
    public double setAmount(){
        return amount;
    }
    public int getTime(){
        return time;
    }
}
