/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Checkroll_Salary {
    private int employCode;
    private String name;
    public int numOfdays;
    public double dayRate;
    public int numOfSundays;
    public double sundayRate;
    private double etfPer;
    private double epfPer;
    private double welfarePer;
    private double ot1Rate;
    private int ot1Hours;
    private double ot2Rate;
    private int ot2Hours;
    private double incentive1;
    private double incentive2;
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    
    public Checkroll_Salary(){
        this.employCode = 0;
        this.name = null;
        this.numOfdays = 0;
        this.dayRate = 0;
        this.numOfSundays = 0;
        this.sundayRate = 0;
        this.etfPer = 0;
        this.epfPer = 0;
        this.welfarePer = 0;
        this.ot1Rate = 0;
        this.ot1Hours = 0;
        this.ot2Rate = 0;
        this.ot2Hours = 0;
        this.incentive1 = 0;
        this.incentive2 = 0;
    }
    
    public void setEmployCode(int employCode){
        this.employCode = employCode;
    }
}
