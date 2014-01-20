
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private double otBRate;
    private int otBHours;
    private double otARate;
    private int otAHours;
    private double incentive1;
    private double incentive2;
    private int margin4Incentive;
    
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
        this.otBRate = 0;
        this.otBHours = 0;
        this.otARate = 0;
        this.otAHours = 0;
        this.incentive1 = 0;
        this.incentive2 = 0;
        this.margin4Incentive = 0;
    }
    
    public void setEmployCode(int employCode){
        this.employCode = employCode;
    }
    public double setDayRate(){
        try {
            ResultSet rs = dbm.query("SELECT normalday_rate FROM checkroll_pay_info");
            rs.next();
            this.dayRate = rs.getDouble("normalday_rate");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.dayRate;
    } 
    public double setSundayRate(){
        try {
            ResultSet rs = dbm.query("SELECT sunday_rate FROM checkroll_pay_info");
            rs.next();
            this.sundayRate = rs.getDouble("sunday_rate");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.sundayRate;
    } 
    public double setEtfPer(){
        try {
            ResultSet rs = dbm.query("SELECT etf FROM checkroll_pay_info");
            rs.next();
            this.etfPer = rs.getDouble("etf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.etfPer;
    } 
    public double setEpfPer(){
        try {
            ResultSet rs = dbm.query("SELECT epf FROM checkroll_pay_info");
            rs.next();
            this.epfPer = rs.getDouble("epf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.epfPer;
    } 
    public double setWelfarePer(){
        try {
            ResultSet rs = dbm.query("SELECT welfare FROM checkroll_pay_info");
            rs.next();
            this.welfarePer = rs.getDouble("welfare");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.welfarePer;
    } 
    public double setOtBRate(){
        try {
            ResultSet rs = dbm.query("SELECT otrate_before FROM checkroll_pay_info");
            rs.next();
            this.otBRate = rs.getDouble("otrate_before");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.otBRate;
    } 
    public double setOtARate(){
        try {
            ResultSet rs = dbm.query("SELECT otrate_after FROM checkroll_pay_info");
            rs.next();
            this.otARate = rs.getDouble("otrate_after");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.otARate;
    } 
    public double setIncentive1(){
        try {
            ResultSet rs = dbm.query("SELECT incentive_1 FROM checkroll_pay_info");
            rs.next();
            this.incentive1 = rs.getDouble("incentive_1");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.incentive1;
    } 
    public double setIncentive2(){
        try {
            ResultSet rs = dbm.query("SELECT incentive_2 FROM checkroll_pay_info");
            rs.next();
            this.incentive2 = rs.getDouble("incentive_2");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.incentive2;
    } 
    public double setMargin(){
        try {
            ResultSet rs = dbm.query("SELECT margin FROM checkroll_pay_info");
            rs.next();
            this.margin4Incentive = rs.getInt("margin");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.margin4Incentive;
    } 
    public String setName(){
        this.name = dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "name");
        return this.name;
    }
    public int setNumOfDays(){
        this.numOfdays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "normal_days"));
        return this.numOfdays;
    }
    public int setNumOfSundays(){
        this.numOfSundays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "sundays"));
        return this.numOfSundays;
    }
    public int setOtBHours(){
        this.otBHours = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "ot_before_hours"));
        return this.otBHours;
    }
    public int setOtAHours(){
        this.otAHours = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "ot_before_hours"));
        return this.otAHours;
    }
    
    public double getNormalDayAmount(){
        if(this.setNumOfDays()>=this.setMargin())
            return setNumOfDays()*(setIncentive1()+setIncentive2()+setDayRate());
        else 
            return setNumOfDays()*(setIncentive1()+setDayRate());
    }
    public double getSundayAmount(){
        return setNumOfSundays()*setSundayRate();
    }
    public double getEtfAmount(){
        return (getNormalDayAmount()+getSundayAmount())*setEtfPer()*0.01;
    }
    public double getEpfAmount(){
        return (getNormalDayAmount()+getSundayAmount())*setEpfPer()*0.01;
    }
    public double getWelfareAmount(){
        return (getNormalDayAmount()+getSundayAmount())*setWelfarePer()*0.01;
    }
    public double getOtBAmount(){
        return setOtBHours()*setOtBRate();
    }
    public double getOtAAmount(){
        return setOtAHours()*setOtARate();
    }
    public double getFullPay(){
        return (getNormalDayAmount()+getSundayAmount()+getOtAAmount()+getOtBAmount()-getEpfAmount()-getEtfAmount()-getWelfareAmount());
    }
}
