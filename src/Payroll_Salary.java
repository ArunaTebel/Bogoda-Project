
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

    private double pettyCash;

    DatabaseManager dbm = DatabaseManager.getDbCon();
    PRCR_NoteAnalysis naObject=new PRCR_NoteAnalysis();

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

    public double setBasic() {
        this.basic = Double.parseDouble(dbm.checknReturnData("staff_personalinfo", "code", employCode, "basic_salary"));
        return this.basic;
    }

    public int setOtHours() {
        this.otHours = Integer.parseInt(dbm.checknReturnData("staff_personalinfo", "code", employCode, "ot_hours"));
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

    public double getFullPay() {
        double fullpay=setBasic() - getEtfAmount() - getEpfAmount() - getWelfareAmount() + getOtAmount();
        naObject.StNoteAnalysis(fullpay,employCode);
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "full_pay", fullpay);
        return (fullpay);
    }

    
    
}
