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
    int normaldaysbfr17 = 0;
    int sundaysbfr17 = 0;
    private double prv_month_coins;

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

    public PRCR_Staff_Salary_Cal() {

        this.employCode = 0;

        this.incentive1Amount = 0;
        this.incentive2Amount = 0;
       
        this.grosspay = 0;
        this.EPFContribution = 0;
        this.EPFContribution2 = 0;
        this.ETFContribution = 0;

        this.pettyCash = 0;
       

    }

    public void inputParameters(String st, String division) {
        this.st = st;
        this.division = division;

    }

    public void setEmpCode(int code) {

        this.employCode = code;
    }

    public void CalculateSalary() {

        basic = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_pay"));
        nopay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sunday_pay"));
        allowance1 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_hours"));
        allowance2 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_amount"));
        allowance3 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_may"));

        totalBasicSalary = basic + allowance1 + allowance2 + allowance3;

        ot_pay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_before_amount"));
        incentive1Amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "incentive1"));
        incentive2Amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "incentive2"));
        extra1 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_cash"));
        extra2 = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_overkilos"));
        coinsbf=Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "coinsbf"));
       
        
        grosspay = totalBasicSalary + ot_pay + incentive1Amount + incentive2Amount+coinsbf;

        this.EPFRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "epf"));
        EPFRate2 = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "epf2"));
        ETFRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "etf"));

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

        this.tea_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "tea"));
        this.salary_adv = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "salary_adv"));
        this.fest_adv = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fest_adv"));
        this.food_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "food"));
        this.loan = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "loan"));
        this.ceb_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ceb"));
        this.teacher_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "teacher"));
        this.chemical_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "chemical"));
        this.payslip_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pay_slip"));
        this.fine_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fine"));
        this.welfare_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "welfare"));
        this.kovil_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "kovil"));

        this.other_ded1 = ceb_ded + teacher_ded + chemical_ded + payslip_ded + fine_ded + kovil_ded + welfare_ded;

        this.meals_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "meals"));

        this.other_ded2 = meals_ded;//???

        this.pension_ded = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pension"));
        this.other_ded3 = pension_ded;
        this.pre_debt = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pre_debt"));

        this.total_ded = EPFContribution + tea_ded + salary_adv + fest_adv + food_ded + loan + other_ded1 + other_ded2 + other_ded3 + pre_debt;
        this.FinalSalary = grosspay - total_ded;//+ getPreMonthCoins();

        
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_pay", totalBasicSalary);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "gross_pay", grosspay);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_epf", totalEPF);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf10", EPFContribution);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf12", EPFContribution2);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "etf", ETFContribution);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "other_ded1", other_ded1);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", paid_amount);

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
