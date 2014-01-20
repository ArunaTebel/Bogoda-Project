
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
    private String employCode;
    private String name;
    private double basic;
    private double etfPer;
    private double epfPer;
    private double welfarePer;
    private double otRate;
    private int otHours;
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    
    public Payroll_Salary(){
        this.employCode = null;
        this.name = null;
        this.basic = 0;
        this.etfPer = 0;
        this.epfPer = 0;
        this.welfarePer = 0;
        this.otRate = 0;
        this.otHours = 0;
    }
    
    public void setEtfPer(){
        try {
            ResultSet rs = dbm.query("SELECT etf FROM staff_pay_info");
            rs.next();
            this.etfPer = rs.getDouble("etf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void setEpfPer(){
        try {
            ResultSet rs = dbm.query("SELECT epf FROM staff_pay_info");
            rs.next();
            this.epfPer = rs.getDouble("epf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setWelfarePer(){
        try {
            ResultSet rs = dbm.query("SELECT welfare FROM staff_pay_info");
            rs.next();
            this.welfarePer = rs.getDouble("welfare");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
