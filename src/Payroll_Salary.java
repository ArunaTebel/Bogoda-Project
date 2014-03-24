
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
public class Payroll_Salary {

    private int employCode;
    private String name;
    private double basic;
    private double etfPer;
    private double epfPer;
    private double welfarePer;
    private double otRate;
    private int otHours;
    String month;
    String year;
    String st;

    private double pettyCash;

    DatabaseManager dbm = DatabaseManager.getDbCon();
    PRCR_NoteAnalysis naObject=new PRCR_NoteAnalysis();
    Date_Handler month_num = new Date_Handler();

    public Payroll_Salary() {
        this.employCode = 0;
        this.name = null;
        this.basic = 0;
        this.etfPer = 0;
        this.epfPer = 0;
        this.welfarePer = 0;
        this.otRate = 0;
        this.otHours = 0;
        
        this.pettyCash=0;
        month = null;
        year = null;
        st = null;

    }

    public void Set_month(String month) {
        this.month = month;
        if (month_num.return_index(month) < 10) {
            st = year + "_0" + month_num.return_index(month);
        } else {
            st = year + "_" + month_num.return_index(month);
        }
        System.out.println(st);
    }

    public void Set_year(String year) {
        this.year = year;
    }

    public void setEmployCode(int employCode) {
        this.employCode = employCode;
    }

    public double setEtfPer() {
        try {
            ResultSet rs = dbm.query("SELECT etf FROM staff_pay_info");
            rs.next();
            this.etfPer = rs.getDouble("etf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.etfPer;
    }

    public double setEpfPer() {
        try {
            ResultSet rs = dbm.query("SELECT epf FROM staff_pay_info");
            rs.next();
            this.epfPer = rs.getDouble("epf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.epfPer;
    }

    public double setWelfarePer() {
        try {
            ResultSet rs = dbm.query("SELECT welfare FROM staff_pay_info");
            rs.next();
            this.welfarePer = rs.getDouble("welfare");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.welfarePer;
    }

    public double setOtRate() {
        try {
            ResultSet rs = dbm.query("SELECT ot_rate FROM staff_pay_info");
            rs.next();
            this.otRate = rs.getDouble("ot_rate");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.otRate;
    }

    public String setName() {
        this.name = dbm.checknReturnData("personal_info", "code", employCode, "name");
        return this.name;
    }
    MessageBox msg=new MessageBox();
    public double setBasic() {
        if(dbm.checknReturnData("prcr_staffworkdata_" + st, "code", employCode, "basic_salary")!=null){
        this.basic = Double.parseDouble(dbm.checknReturnData("prcr_staffworkdata_" + st, "code", employCode, "basic_salary"));
        }else{
        msg.showMessage("Enter Basic Salary", "warning", "warning");
        }
        return this.basic;
    }

    public int setOtHours() {
        if(dbm.checknReturnData("prcr_staffworkdata_" + st, "code", employCode, "ot_hours")!=null){
        this.otHours = Integer.parseInt(dbm.checknReturnData("prcr_staffworkdata_" + st, "code", employCode, "ot_hours"));
        }else{
        this.otHours=0;
        }
        return this.otHours;
    }

    public double getEtfAmount() {
        return setBasic() * setEtfPer() * 0.01;
    }

    public double getEpfAmount() {
        return setBasic() * setEpfPer() * 0.01;
    }

    public double getWelfareAmount() {
        return setBasic() * setWelfarePer() * 0.01;
    }

    public double getOtAmount() {
        return setOtRate() * setOtHours();
    }

    public double getFullPay(String ss) {
        this.st=ss;
        double fullpay=setBasic() - getEtfAmount() - getEpfAmount() - getWelfareAmount() + getOtAmount();
        System.out.println(fullpay);
       naObject.StNoteAnalysis(fullpay,employCode,ss);
        dbm.updateDatabase("prcr_staffworkdata_"+ ss, "code", employCode, "full_pay", fullpay);
       
        return (fullpay);
    }
    public double getFullPay() {
        double fullpay=setBasic() - getEtfAmount() - getEpfAmount() - getWelfareAmount() + getOtAmount();
     
       
        return (fullpay);
    }


    
    
}
