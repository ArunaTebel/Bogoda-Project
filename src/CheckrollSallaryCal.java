
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//not completed
/**
 *
 * @author acer
 */
public class CheckrollSallaryCal {

    public static void main(String[] args) {
//        CheckrollSallaryCal abc = new CheckrollSallaryCal();
//        System.out.println(abc.getColumnsize("checkroll_personalinfo", "code"));
//        int array[]=new int[5];
//        array=abc.getIntArray("checkroll_personalinfo", "code");
//         
//        for(int i=0;i<5;i++){
//            System.out.println(array[i]);
//            
//        }
    CheckrollSallaryCal abc = new CheckrollSallaryCal();
//        System.out.println(abc.getColumnsize("checkroll_personalinfo", "code"));
//        int array[]=new int[4];
//        double arraySal[]=new double[4];
//        array=abc.getIntArray("checkroll_personalinfo", "code");
//         
//        for(int i=0;i<4;i++){
//            //System.out.println(array[i]);
//            abc.setEmployCode(array[i]);
//            arraySal[i]=abc.getFinalSalary();
//            System.out.println(arraySal[i]);
            
   //     }
//    abc.setEmployCode(110);
//    System.out.println(abc.getNormalDaysAmount());
//        System.out.println(abc.getSundayAmount());
//    System.out.println(abc.getTotalIncentiveAmount());
//    System.out.println(abc.getTotalBasicSallary());
//        System.out.println(abc.getWelfareContribution());
//        System.out.println(abc.getFinalSalary());
  }

    DatabaseManager dbm = DatabaseManager.getDbCon();

    private int employCode;
    private int normalDays;//Total worked normal days Calculate using database
    private double normalDaysAmount;
    private double normalDaysRate;//should be read from Rate database
    private int sundays;
    private double sundaysAmount;
    private double sundayRate;
    private int workdays;//total workdays in a month(normal days+sundays)
    private double totalIncentiveAmount;//total amount added from incentive1 and incentive2
    private double incentive1Rate;//read from database
    private double incentive2Rate;//read from database
    private int margin;
    private double incentive1Amount;
    private double incentive2Amount;
    private double OTBeforeRate;
    private int OTBeforeHours;
    private double OTBeforeAmount;
    private double OTAfterRate;
    private int OTAfterHours;
    private double OTAfterAmount;
    private double totalBasicSalary;
    private double EPFRate;//%
    private double EPFContribution;
    private double ETFRate;//%
    private double ETFContribution;
    private double welfareRate;//%
    private double welfareContribution;
    private double loanRate;
    private int loanTime;
    private double loanDeductions;
    private double fineDeductions;
    private double storeDeductions;
    private double pettyCash;
    private double FinalSalary;
    private int n_5000;
    private int n_2000;
    private int n_1000;
    private int n_500;
    private int n_100;
    private int n_50;
    private int n_20;
    private int n_10;

    

    public CheckrollSallaryCal() {//NC
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
        this.EPFContribution = 0;
        this.ETFContribution = 0;
        this.welfareContribution = 0;
        this.loanDeductions = 0;
        this.fineDeductions = 0;
        this.storeDeductions = 0;
        this.pettyCash = 0;
        this.n_5000=0;
    this.n_2000=0;
    this.n_1000=0;
    this.n_500=0;
    this.n_100=0;
    this.n_50=0;
    this.n_20=0;
    this.n_10=0;
    }

    //setters
    public void setEmployCode(int employCode) {
        this.employCode = employCode;
    }

    public void setNormalDays() {
        this.normalDays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "normal_days"));

        //should be calculated using database
    }

    public void setNormalDaysRate() {
        try {

            ResultSet rs = dbm.query("SELECT normalday_rate FROM checkroll_pay_info");
            rs.next();
            normalDaysRate = rs.getDouble("normalday_rate");
            //System.out.println(normalDaysRate);
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNormalDaysAmount() {
        normalDaysAmount = getNormalDays() * getNormaldaysRate();
    }

    public void setSundays() {
        this.sundays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "sundays"));

        //should be calculated using database
    }

    public void setSundayRate() {
        try {

            ResultSet rs = dbm.query("SELECT sunday_rate FROM checkroll_pay_info");
            rs.next();
            sundayRate = rs.getDouble("sunday_rate");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSundayAmount() {
        sundaysAmount = getSundayRate() * getSundays();
    }

    public void setWorkdays() {
        workdays = getSundays() + getNormalDays();
    }

    public void setIncentive1Rate() {
        try {
            ResultSet rs = dbm.query("SELECT incentive_1 FROM checkroll_pay_info");
            rs.next();
            incentive1Rate = rs.getDouble("incentive_1");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIncentive2rate() {
        try {
            ResultSet rs = dbm.query("SELECT incentive_2 FROM checkroll_pay_info");
            rs.next();
            incentive2Rate = rs.getDouble("incentive_2");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setMargin() {
        try {
            ResultSet rs = dbm.query("SELECT margin FROM checkroll_pay_info");
            rs.next();
            margin = rs.getInt("margin");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setIncentive1Amount() {//
        incentive1Amount = getWorkdays() * getIncentive1Rate();
    }

    public void setIncentive2Amount() {

        if (workdays > getMargin()) {//done
            incentive2Amount = getWorkdays() * getIncentive2Rate();
        } else {
            incentive2Amount = 0;
        }
    }

    public void setTotalIncentiveAmount() {

        totalIncentiveAmount = getIncentive1Amount() + getIncentive2Amount();
    }

    public void setOTBeforeRate() {
        try {
            ResultSet rs = dbm.query("SELECT otrate_before FROM checkroll_pay_info");
            rs.next();
            OTBeforeRate = rs.getDouble("otrate_before");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOTBeforeHours() {
        this.OTBeforeHours = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "ot_before_hours"));

        //calculate using database
    }

    public void setOTBeforeAmount() {

        OTBeforeAmount = getOTBeforeHours() * getOTBeforeRate();
    }

    public void setOTAfterRate() {
        try {
            ResultSet rs = dbm.query("SELECT otrate_after FROM checkroll_pay_info");
            rs.next();
            OTAfterRate = rs.getDouble("otrate_after");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOTAfterHours() {
        this.OTAfterHours = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", employCode, "ot_after_hours"));

        //calculate using database
    }

    public void setOTAfterAmount() {
        OTAfterAmount = getOTAfterHours() * getOTAfterRate();
    }

    public void setTotalBasicSallary() {
        totalBasicSalary = getNormalDaysAmount() + getSundayAmount() + getTotalIncentiveAmount(); //+ getOTAfterAmount() + getOTBeforeAmount();
    }

    public void setEPFRate() {
        try {
            ResultSet rs = dbm.query("SELECT epf FROM checkroll_pay_info");
            rs.next();
            EPFRate = rs.getDouble("epf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEPFContribution() {
        EPFContribution = getTotalBasicSallary() * (getEPFRate() / 100);
    }

    public void setETFRate() {
        try {
            ResultSet rs = dbm.query("SELECT etf FROM checkroll_pay_info");
            rs.next();
            ETFRate = rs.getDouble("etf");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setETFContribution() {
        ETFContribution = getTotalBasicSallary() * (getETFRate() / 100);
    }

    public void setWelfareRate() {
        try {
            ResultSet rs = dbm.query("SELECT welfare FROM checkroll_pay_info");
            rs.next();
            welfareRate = rs.getDouble("welfare");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setWelfareContribution() {
        welfareContribution = getTotalBasicSallary() * (getWelfareRate() / 100);
    }

    public void setLoanRate() {
        //read from database
    }

    public void setLoanTime() {
        //calculate using database
    }

    public void setLoanDeductions() {
        //
    }

    public void setFineDeductions() {
        //
    }

    public void setStoreDeductions() {
        //
    }

    public void setPettyCash() {
        //
    }

    public void setFinalSalary() {
        FinalSalary = getTotalBasicSallary()+ getOTAfterAmount() + getOTBeforeAmount() - getETFContribution() - getEPFContribution()-getWelfareContribution() - getLoanDeductions() - getFineDeductions() - getStoreDeductions(); //+ getPettyCash();
    }

    //getters
    public int getNormalDays() {//nc
        setNormalDays();
        return normalDays;
    }

    public double getNormaldaysRate() {
        setNormalDaysRate();
        return normalDaysRate;
    }

    public double getNormalDaysAmount() {
        setNormalDaysAmount();
        return normalDaysAmount;
    }

    public int getSundays() {
        setSundays();
        return sundays;
    }

    public double getSundayRate() {
        setSundayRate();
        return sundayRate;
    }

    public double getSundayAmount() {
        setSundayAmount();
        return sundaysAmount;
    }

    public int getWorkdays() {
        setWorkdays();
        return workdays;
    }

    public double getIncentive1Rate() {
        setIncentive1Rate();
        return incentive1Rate;
    }

    public double getIncentive2Rate() {
        setIncentive2rate();
        return incentive2Rate;
    }

    public int getMargin() {
        setMargin();
        return margin;
    }

    public double getIncentive1Amount() {
        setIncentive1Amount();
        return incentive1Amount;
    }

    public double getIncentive2Amount() {
        setIncentive2Amount();
        return incentive2Amount;
    }

    public double getTotalIncentiveAmount() {
        setTotalIncentiveAmount();
        return totalIncentiveAmount;
    }

    public double getOTBeforeRate() {
        setOTBeforeRate();
        return OTBeforeRate;
    }

    public int getOTBeforeHours() {
        setOTBeforeHours();
        return OTBeforeHours;
    }

    public double getOTBeforeAmount() {
        setOTBeforeAmount();
        return OTBeforeAmount;
    }

    public double getOTAfterRate() {
        setOTAfterRate();
        return OTAfterRate;
    }

    public int getOTAfterHours() {
        setOTAfterHours();
        return OTAfterHours;
    }

    public double getOTAfterAmount() {
        setOTAfterAmount();
        return OTAfterAmount;
    }

    public double getTotalBasicSallary() {
        setTotalBasicSallary();
        return totalBasicSalary;
    }

    public double getEPFRate() {
        setEPFRate();
        return EPFRate;
    }

    public double getEPFContribution() {
        setEPFContribution();
        return EPFContribution;
    }

    public double getETFRate() {
        setETFRate();
        return ETFRate;
    }

    public double getETFContribution() {
        setETFContribution();
        return ETFContribution;
    }

    public double getWelfareRate() {
        setWelfareRate();
        return welfareRate;
    }

    public double getWelfareContribution() {
        setWelfareContribution();
        return welfareContribution;
    }

    public double getLoanRate() {
        setLoanRate();
        return loanRate;
    }

    public int getLoanTime() {
        setLoanTime();
        return loanTime;
    }

    public double getLoanDeductions() {
        setLoanDeductions();
        return loanDeductions;
    }

    public double getFineDeductions() {
        setFineDeductions();
        return fineDeductions;
    }

    public double getStoreDeductions() {
        setStoreDeductions();
        return storeDeductions;
    }

    public double getPettyCash() {
        setPettyCash();
        return pettyCash;
    }

    public double getFinalSalary() {
        setFinalSalary();
        dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "full_salary", FinalSalary);
        noteAnalysis(FinalSalary);//set the note values and update data base
        return FinalSalary;
    }
    
    public void noteAnalysis(double salary){
        
            
            n_5000=(int) (salary/5000);
            double rem=salary%5000;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_5000", n_5000);
        
            n_2000=(int) (rem/2000);
            rem=rem%2000;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_2000", n_2000);
        
            n_1000=(int) (rem/1000);
            rem=rem%1000;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_1000", n_1000);
        
            n_500=(int) (rem/500);
            rem=rem%500;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "N_500", n_500);
        
            n_100=(int) (rem/100);
            rem=salary%100;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_100", n_100);
        
            n_50=(int) (rem/50);
            rem=rem%50;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_50", n_50);
        
            n_20=(int) (rem/20);
            rem=rem%20;
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_20", n_20);
        
            n_10=(int) (rem/10);
            dbm.updateDatabase("checkroll_personalinfo", "code", employCode, "n_10", n_10);
        
            pettyCash=rem%10;
            System.out.println("5000-"+n_5000+"-2000-"+n_2000+"-1000-"+n_1000+","+n_500+","+n_100);
        
        
    
    }
    public int getN5000(){
      return this.n_5000;
    }
    public int getN2000(){
      return this.n_2000;
    }
    public int getN1000(){
      return this.n_1000;
    }
    public int getN500(){
      return this.n_500;
    }
    public int getN100(){
      return this.n_100;
    }
    public int getN50(){
      return this.n_50;
    }
    public int getN20(){
      return this.n_20;
    }
    public int getN10(){
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
