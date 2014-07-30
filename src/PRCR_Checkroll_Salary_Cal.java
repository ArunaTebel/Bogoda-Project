
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
public class PRCR_Checkroll_Salary_Cal {

    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    PRCR_NoteAnalysis naObject3 = new PRCR_NoteAnalysis();

    private int employCode;
    private int normalDays;//Total worked normal days Calculate using database
    private double normalDaysAmount;
    private double normalDaysRate;//should be read from Rate database
    private int sundays;
    private double sundaysAmount;
    private double sundayRate;
    private int workdays;//total workdays in a month(normal days+sundays)
    private double coinsbf;
    private double totalIncentiveAmount;//total amount added from incentive1 and incentive2
    private double incentive1Rate;//read from database
    private double incentive2Rate;//read from database
    private int margin;
    private double incentive1Amount;
    private double incentive2Amount;
    private double OTBeforeRate;
    private double OTBeforeHours;
    private double OTBeforeAmount;
    private double OTAfterRate;
    private double OTAfterHours;
    private double OTAfterAmount;
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
    String year_month;



    private double pettyCash;//this months remaining coins
    private double FinalSalary;
    private double paid_amount;
    private double next_month;
   
    private String st;
    private String division;
    private double extrapaycash;
    private double extrapayoverkilos;
    private double extrapayholidaypay;
    private double extrapaymay;
    private double totalextrapay;
    private String s = null;

    public PRCR_Checkroll_Salary_Cal() {

          this.employCode = 0;
        this.normalDays = 0;
        this.normalDaysAmount = 0;
        this.sundays = 0;
        this.sundaysAmount = 0;
        this.workdays = 0;
        this.totalIncentiveAmount = 0;
        this.incentive1Amount = 0;
        this.incentive2Amount = 0;
        this.OTBeforeAmount = 0;
        this.OTAfterAmount = 0;
        this.grosspay=0;
        this.EPFContribution = 0;
        this.EPFContribution2 = 0;
        this.ETFContribution = 0;
        
        this.pettyCash = 0;
        this.totalextrapay = 0;
       
    }

    
    public void inputParameters(String st, String division) {
        this.st = st;
        this.division = division;
       
    }
    
    public void setEmpCode(int code){
    
        this.employCode = code;
    }

    public void CalculateSalary() {

        this.normalDays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_days"));
        this.normalDaysRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "normalday_rate"));
        this.normalDaysAmount = normalDays * normalDaysRate;

        this.sundays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sundays"));
        this.sundayRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "sunday_rate"));
        this.sundaysAmount = sundays * sundayRate;

        this.workdays = sundays + normalDays;
        this.totalBasicSalary = sundaysAmount + normalDaysAmount;
        
        this.coinsbf= Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "coinsbf"));
       

        this.incentive1Rate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "incentive_1"));
        this.incentive2Rate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "incentive_2"));

        this.margin = Integer.parseInt(dbm.filterReturn2StringData("prcr_margin_dates", "month", st, "division", division, "margin"));

        this.incentive1Amount = workdays * incentive1Rate;

        if (workdays > margin) {//done
            incentive2Amount = workdays * incentive2Rate;
        } else {
            incentive2Amount = 0;
        }

        this.totalIncentiveAmount = incentive1Amount + incentive2Amount;

        this.OTBeforeHours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_before_hours"));
        this.OTBeforeRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "otrate_before"));
        this.OTBeforeAmount = OTBeforeHours * OTBeforeRate;

        this.OTAfterHours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_hours"));
        this.OTAfterRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "otrate_after"));
        this.OTAfterAmount = OTAfterHours * OTAfterRate;

        this.normaldaysbfr17 = dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_days_bfr17") != null ? Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_days_bfr17")) : 0;
        this.sundaysbfr17 = dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sundays_bfr17") != null ? Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sundays_bfr17")) : 0;
        this.extrapaymay = normaldaysbfr17 * normalDaysRate + sundaysbfr17 * sundayRate;
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "extra_pay_may", this.extrapaymay);

        this.extrapaycash = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_cash"));
        this.extrapayoverkilos = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_overkilos"));
        this.extrapayholidaypay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay_holiday"));

        this.totalextrapay = extrapaycash + extrapayoverkilos + extrapayholidaypay + extrapaymay;

        this.grosspay = totalBasicSalary + totalIncentiveAmount + OTBeforeAmount + OTAfterAmount + totalextrapay+coinsbf;

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

       /* dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "normal_pay", normalDaysAmount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "sunday_pay", sundaysAmount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "incentive1", incentive1Amount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "incentive2", incentive2Amount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "ot_before_amount", OTBeforeAmount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "ot_after_amount", OTAfterAmount);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "extra_pay", totalextrapay);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_pay", totalBasicSalary);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "gross_pay", grosspay);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_epf", totalEPF);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf10", EPFContribution);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf12", EPFContribution2);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "etf", ETFContribution);
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "other_ded1", other_ded1);*/
      //  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
      //  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", paid_amount);
        
        year_month="pr_workdata_" + st;
        try {
            dbm.insert("UPDATE "+year_month+" SET normal_pay = '"+normalDaysAmount+"',sunday_pay='"+sundaysAmount+"',incentive1='"+incentive1Amount+"',incentive2='"+incentive2Amount+"',"
                    + "ot_before_amount='"+OTBeforeAmount+"',ot_after_amount='"+OTAfterAmount+"',extra_pay='"+totalextrapay+"',total_pay='"+totalBasicSalary+"',gross_pay='"+grosspay+"',"
                    + "total_epf='"+totalEPF+"',epf10='"+EPFContribution+"',epf12='"+EPFContribution2+"',etf='"+ETFContribution+"',other_ded1='"+other_ded1+"'  WHERE code = '"+employCode+"'");
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
