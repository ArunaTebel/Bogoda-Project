
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
    private int n_5000;
    private int n_2000;
    private int n_1000;
    private int n_500;
    private int n_100;
    private int n_50;
    private int n_20;
    private int n_10;
    private double pettyCash;

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public Payroll_Salary() {
        this.employCode = 0;
        this.name = null;
        this.basic = 0;
        this.etfPer = 0;
        this.epfPer = 0;
        this.welfarePer = 0;
        this.otRate = 0;
        this.otHours = 0;
        this.n_5000 = 0;
        this.n_2000 = 0;
        this.n_1000 = 0;
        this.n_500 = 0;
        this.n_100 = 0;
        this.n_50 = 0;
        this.n_20 = 0;
        this.n_10 = 0;
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
        noteAnalysis(fullpay);
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "full_pay", fullpay);
        return (fullpay);
    }

    public void noteAnalysis(double salary) {

        n_5000 = (int) (salary / 5000);
        double rem = salary % 5000;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_5000", n_5000);

        n_2000 = (int) (rem / 2000);
        rem = rem % 2000;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_2000", n_2000);

        n_1000 = (int) (rem / 1000);
        rem = rem % 1000;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_1000", n_1000);

        n_500 = (int) (rem / 500);
        rem = rem % 500;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "N_500", n_500);

        n_100 = (int) (rem / 100);
        rem = salary % 100;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_100", n_100);

        n_50 = (int) (rem / 50);
        rem = rem % 50;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_50", n_50);

        n_20 = (int) (rem / 20);
        rem = rem % 20;
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_20", n_20);

        n_10 = (int) (rem / 10);
        dbm.updateDatabase("staff_personalinfo", "code", employCode, "n_10", n_10);

        pettyCash = rem % 10;
       // System.out.println("5000-" + n_5000 + "-2000-" + n_2000 + "-1000-" + n_1000 + "," + n_500 + "," + n_100);

    }

    public int getN5000() {
        return this.n_5000;
    }

    public int getN2000() {
        return this.n_2000;
    }

    public int getN1000() {
        return this.n_1000;
    }

    public int getN500() {
        return this.n_500;
    }

    public int getN100() {
        return this.n_100;
    }

    public int getN50() {
        return this.n_50;
    }

    public int getN20() {
        return this.n_20;
    }

    public int getN10() {
        return this.n_10;
    }

    public int[] getIntArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }

        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

}
