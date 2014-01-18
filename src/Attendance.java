/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Attendance {
    private String arrivalTime;
    private String departureTime;
    
    public Attendance(String arrivalTime,String departureTime){
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
    }
    public Attendance(){
        this.arrivalTime=null;
        this.departureTime=null;
    }
    
    //setters
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime=arrivalTime;
    }
    public void setDepartureTime(String departureTime){
        this.departureTime=departureTime;
    }
    
    //getters
    public String getArrivaltime(){
         return arrivalTime;
    }
    public String getDepartureTime(){
         return departureTime;
    }
}
