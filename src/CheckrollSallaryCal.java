
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

 //   public static void main(String[] args) {
//        CheckrollSallaryCal abc = new CheckrollSallaryCal();
//        System.out.println(abc.getColumnsize("checkroll_personalinfo", "code"));
//        int array[]=new int[5];
//        array=abc.getIntArray("checkroll_personalinfo", "code");
//         
//        for(int i=0;i<5;i++){
//            System.out.println(array[i]);
//            
//        }
       // CheckrollSallaryCal abc = new CheckrollSallaryCal();
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
 //   }

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
    private String month;
    private String year;
    private String st;
    private double extrapay;

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
        this.extrapay = 0;
        this.month = null;
        this.year = null;
        this.st = null;

    }

    //setters
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

    public void setNormalDays() {
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_days")!=null){
        this.normalDays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "normal_days"));
        }else{
        this.normalDays=0;
        }
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
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sundays")!=null){
        this.sundays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "sundays"));
        }else{
        this.sundays=0;
        }
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

    public void setMargin() {//should change
        if (checkDataAvailability("prcr_margin_dates", "month", st, "margin")) {
            this.margin = Integer.parseInt(checknReturnDataForStrings("prcr_margin_dates", "month", st, "margin"));
        }else{
        PRCR_add_margin_dates amd=new PRCR_add_margin_dates(st);
        amd.setVisible(true);
        System.out.println("back to main");
//        this.margin = Integer.parseInt(checknReturnDataForStrings("prcr_margin_dates", "month", st, "margin"));
            System.out.println("back to main2");
        }

    }

    public boolean checkDataAvailability(String table_name, String table_column_giving, String row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String s;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                s = query.getString(table_column_need);
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    public String checknReturnDataForStrings(String table_name, String table_column_giving, Object row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        return null;
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
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_before_hours")!=null){
        this.OTBeforeHours = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_before_hours"));
        }else{
        this.OTBeforeHours=0;
        }
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
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_hours")!=null){
        this.OTAfterHours = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ot_after_hours"));
        }else{
        this.OTAfterHours=0;
        }
        //calculate using database
    }

    public void setOTAfterAmount() {
        OTAfterAmount = getOTAfterHours() * getOTAfterRate();
    }

    public void setExtrapayAmount() {
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay")!=null){
        this.extrapay = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "extra_pay"));
        }else{
        this.extrapay=0;
        }
    }

    public void setTotalBasicSallary() {
        totalBasicSalary = getNormalDaysAmount() + getSundayAmount() + getTotalIncentiveAmount(); //+ getOTAfterAmount() + getOTBeforeAmount();
        System.err.println("normal amount"+getNormalDaysAmount());
        System.err.println("sunday amount"+getSundayAmount());
        System.err.println("incentive"+getTotalIncentiveAmount());
        System.err.println("incentive1"+incentive1Amount);
        System.err.println("margin"+margin);
        System.err.println("incentive2"+incentive2Amount);
        System.err.println("total basic"+totalBasicSalary);
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
        FinalSalary = getTotalBasicSallary() + getOTAfterAmount() + getOTBeforeAmount() + getExtrapayAmount() - getETFContribution() - getEPFContribution() - getWelfareContribution() - getLoanDeductions() - getFineDeductions() - getStoreDeductions(); //+ getPettyCash();
        System.out.println("********8***********");
        
        System.out.println("total basic-"+totalBasicSalary);
        System.out.println("normal-"+normalDaysAmount);
        System.out.println("sunday-"+sundaysAmount);
        System.out.println("Incentive1-"+incentive1Amount);
        
        System.out.println("Ot aftr-"+OTAfterAmount);
        System.out.println("Ot before-"+OTBeforeAmount);
        System.out.println("Extrapay amount-"+extrapay);
        System.out.println("EPF-"+EPFContribution);
        System.out.println("ETF-"+ETFContribution);
        System.out.println("Welfare-"+welfareContribution);
        
        System.out.println("setFinalSalary()-FinalSalary="+FinalSalary);
         System.out.println("********8***********");
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
        //setMargin();
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

    public double getExtrapayAmount() {
        setExtrapayAmount();
        return extrapay;
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
    public String getString(){
        return st;
    }
    public double getFinalSalary(String ss) {
        this.st=ss;
        setMargin();
        //PRCR_NoteAnalysis naObject3 = new PRCR_NoteAnalysis();
        setFinalSalary();
        System.out.println("final salary-noteanalysis"+FinalSalary);
        dbm.updateDatabase("pr_workdata_" + ss, "code", employCode, "full_salary", FinalSalary);
        naObject3.ChNoteAnalysis(FinalSalary, employCode,ss);//set the note values and update data base
        return FinalSalary;
    }
    public double getFinalSalary() {
        
        setFinalSalary();
        System.out.println("final salary-checkroll"+FinalSalary);
        return FinalSalary;
    }

}
