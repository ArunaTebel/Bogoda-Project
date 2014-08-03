
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
 * @author Dhananjaya
 */
public class PRCR_Staff_Salary_Cal {

    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    PRCR_NoteAnalysis naObject3 = new PRCR_NoteAnalysis();

    private int employCode;

    private double incentive1Amount;
    private double incentive2Amount;

    private double totalBasicSalary;
    private double grosspay;
    private double EPFRate;//%
    private double EPFContribution;
    private double EPFRate2;//%
    private double EPFContribution2;
    private double totalEPF;
    private double ETFRate;//%
    private double ETFContribution;
    private double tea_ded;
    private double salary_adv;
    private double fest_adv;
    private double food_ded;
    private double loan;
    private double ceb_ded;
    private double teacher_ded;
    private double chemical_ded;
    private double payslip_ded;
    private double fine_ded;
    private double welfare_ded;
    private double kovil_ded;
    private double other_ded1;
    private double meals_ded;
    private double other_ded2;
    private double pension_ded;
    private double other_ded3;
    private double pre_debt;
    private double total_ded;
    private double debit_pay;
    int normaldaysbfr17 = 0;
    int sundaysbfr17 = 0;
    private double prv_month_coins;
    ResultSet query;

    private double pettyCash;//this months remaining coins
    private double FinalSalary;
    private double paid_amount;
    private double next_month;

    private String st;
    private String division;
   
    private String s = null;

    double basic;
    double nopay;
    double allowance1;
    double allowance2;
    double allowance3;
    double ot_hours;
    double ot_pay;
    double extra1;
    double extra2;
    double coinsbf;
    String year_month;

    public PRCR_Staff_Salary_Cal() {

        this.employCode = 0;

        this.incentive1Amount = 0;
        this.incentive2Amount = 0;
       
        this.grosspay = 0;
        this.EPFContribution = 0;
        this.EPFContribution2 = 0;
        this.ETFContribution = 0;

        this.pettyCash = 0;
        
        
           try {
            query = dbm.query("SELECT * FROM checkroll_pay_info WHERE checkroll LIKE '" + 1 + "'");
            while (query.next()) {

               
                this.EPFRate = query.getDouble("epf");
                EPFRate2 = query.getDouble("epf2");
                ETFRate = query.getDouble("etf");

            }
        } catch (SQLException ex) {
            Logger.getLogger(PRCR_Checkroll_Salary_Cal.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    public void inputParameters(String st, String division) {
        this.st = st;
        this.division = division;

    }

    public void setEmpCode(int code) {

        this.employCode = code;
    }
int k=0;
    public void CalculateSalary(String st, String division,int employCode) {

         System.out.println(employCode+"---staff----"+k++);
        year_month = "pr_workdata_" + st;
        try {
           query = dbm.query("SELECT * FROM " + year_month + " WHERE code LIKE '" + employCode + "'");
            while (query.next()) {
                basic = query.getDouble("normal_pay");
                      nopay = query.getDouble("sunday_pay");
        allowance1 = query.getDouble("ot_after_hours");
        allowance2 = query.getDouble("ot_after_amount");
        allowance3 = query.getDouble("extra_pay_may");

       

        ot_pay =query.getDouble("ot_before_amount");
        incentive1Amount =query.getDouble("incentive1");
        incentive2Amount = query.getDouble("incentive2");
        extra1 = query.getDouble( "extra_pay_cash");
        extra2 = query.getDouble( "extra_pay_overkilos");
        coinsbf=query.getDouble("coinsbf");
       
                
                tea_ded = query.getDouble("tea");
                salary_adv = query.getDouble("salary_adv");
                fest_adv = query.getDouble("fest_adv");
                food_ded = query.getDouble("food");
                loan = query.getDouble("loan");
                ceb_ded = query.getDouble("ceb");
                teacher_ded = query.getDouble("teacher");
                chemical_ded = query.getDouble("chemical");
                payslip_ded = query.getDouble("pay_slip");
                fine_ded = query.getDouble("fine");
                welfare_ded = query.getDouble("welfare");
                kovil_ded = query.getDouble("kovil");
                 debit_pay= query.getDouble("prvs_debts_paid");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PRCR_Staff_Salary_Cal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
//        basic = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_pay"));
//        nopay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sunday_pay"));
//        allowance1 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_hours"));
//        allowance2 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_amount"));
//        allowance3 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_may"));

        totalBasicSalary = basic + allowance1 + allowance2 + allowance3;

//        ot_pay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_before_amount"));
//        incentive1Amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "incentive1"));
//        incentive2Amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "incentive2"));
//        extra1 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_cash"));
//        extra2 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_overkilos"));
//        coinsbf=Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "coinsbf"));
//       
//        
        grosspay = totalBasicSalary + ot_pay + incentive1Amount + incentive2Amount+coinsbf;

//        this.EPFRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "epf"));
//        EPFRate2 = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "epf2"));
//        ETFRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "etf"));

        s = dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "register_or_casual");
        if (s.equals("1")) {
            EPFContribution = totalBasicSalary * (EPFRate / 100);
            EPFContribution2 = totalBasicSalary * (EPFRate2 / 100);
            ETFContribution = totalBasicSalary * (ETFRate / 100);
        } else {
            EPFContribution = 0;
            EPFContribution2 = 0;
            ETFContribution = 0;
        }

//        this.tea_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "tea"));
//        this.salary_adv = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "salary_adv"));
//        this.fest_adv = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fest_adv"));
//        this.food_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "food"));
//        this.loan = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "loan"));
//        this.ceb_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ceb"));
//        this.teacher_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "teacher"));
//        this.chemical_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "chemical"));
//        this.payslip_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pay_slip"));
//        this.fine_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fine"));
//        this.welfare_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "welfare"));
//        this.kovil_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "kovil"));

        this.other_ded1 = ceb_ded + teacher_ded + chemical_ded + payslip_ded + fine_ded + kovil_ded + welfare_ded;

        this.meals_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "meals"));

        this.other_ded2 = meals_ded;//???

        this.pension_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pension"));
        this.other_ded3 = pension_ded;
        this.pre_debt = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pre_debt"));

        this.total_ded = EPFContribution + tea_ded + salary_adv + fest_adv + food_ded + loan + other_ded1 + other_ded2 + other_ded3 + pre_debt;
        this.FinalSalary = grosspay+debit_pay - total_ded;//+ getPreMonthCoins();
        
//        
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_pay", totalBasicSalary);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "gross_pay", grosspay);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_epf", totalEPF);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf10", EPFContribution);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf12", EPFContribution2);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "etf", ETFContribution);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "other_ded1", other_ded1);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
//        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", paid_amount);
//        
        year_month = "pr_workdata_" + st;
        try {
            dbm.insert("UPDATE " + year_month + " SET total_pay = '" + totalBasicSalary + "',gross_pay='" + grosspay + "',total_epf='" + totalEPF + "',epf10='" + EPFContribution + "',"
                    + "epf12='" + EPFContribution2 + "',etf='" + ETFContribution + "',other_ded1='" + other_ded1 + "',coins='" + pettyCash + "',total_ded='" + total_ded + "',full_salary='" + FinalSalary + "' WHERE code = '" + employCode + "'");
        } catch (SQLException ex) {
            Logger.getLogger(PRCR_Checkroll_Salary_Cal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (FinalSalary > 0) {
            pettyCash = FinalSalary % 10;//"coins" and "paid_amount" will be calculated and updated to database when the getFinalSalary() is called from out side
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
            paid_amount = FinalSalary - pettyCash;
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", paid_amount);
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "next_month", 0);
        } else {
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", 0);
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", 0);
            dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "next_month", FinalSalary);
        }

    }
}
