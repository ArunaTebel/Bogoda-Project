
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
    
    private double prv_month_coins;
    
    
    private double welfareRate;//%
    private double welfareContribution;
    private double loanRate;
    private int loanTime;
    private double loanDeductions;
    private double fineDeductions;
    private double storeDeductions;
    
    
    private double pettyCash;//this months remaining coins
    private double FinalSalary;
    private double paid_amount;
    private double next_month;
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
        this.grosspay=0;
        this.EPFContribution = 0;
        this.EPFContribution2 = 0;
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
    
    public void setString(String sss){
        this.st=sss;
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
        totalBasicSalary = getNormalDaysAmount() + getSundayAmount();// + getTotalIncentiveAmount(); //+ getOTAfterAmount() + getOTBeforeAmount();
        
    }
    public void setGrosspay(){
        grosspay=getTotalBasicSallary()+getTotalIncentiveAmount()+getOTAfterAmount()+getOTBeforeAmount()+getExtrapayAmount();
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
        String s=null;
        s = dbm.checknReturnData("personal_info","code" , employCode,"register_or_not");
        if(s.equals("Registered")){
        EPFContribution = getTotalBasicSallary() * (getEPFRate() / 100);
        }else{
        EPFContribution=0;
        }
        System.out.println(s);
        System.out.println(EPFContribution);
    }

    public void setEPFRate2() {
        try {
            ResultSet rs = dbm.query("SELECT epf2 FROM checkroll_pay_info");
            rs.next();
            EPFRate2 = rs.getDouble("epf2");
        } catch (SQLException ex) {
            Logger.getLogger(CheckrollSallaryCal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEPFContribution2() {
         String s=null;
        s = dbm.checknReturnData("personal_info","code" , employCode,"register_or_not");
        if(s.equals("Registered")){
        EPFContribution2 = totalBasicSalary * (getEPFRate2() / 100);
        }else{
        EPFContribution2=0;
        }
        System.out.println("EPF2"+EPFContribution2);
        
    }
    
     public void setTotalEPF(){
     totalEPF=EPFContribution+EPFContribution2;
         System.out.println(totalEPF+"total EPF");
     dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "total_epf", totalEPF);
         System.out.println("total_epf was updated");
     
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
         String s=null;
        s = dbm.checknReturnData("personal_info","code" , employCode,"register_or_not");
        if(s.equals("Registered")){
        ETFContribution = totalBasicSalary * (getETFRate() / 100);
        }else{
        ETFContribution=0;
        }
        
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
         String s=null;
        s = dbm.checknReturnData("personal_info","code" , employCode,"register_or_not");
        if(s.equals("Registered")){
        welfareContribution = getWelfareRate();
        }else{
        welfareContribution=0;
        }
        
       //should be changed
    }

    public void setTeaDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "tea")!=null){
        this.tea_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "tea"));
        }else{
        this.tea_ded=0;
        }
    
    }
    public void setSalaryAdv(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "salary_adv")!=null){
        this.salary_adv = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "salary_adv"));
        }else{
        this.salary_adv=0;
        }
    
    }
    public void setFestAdv(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fest_adv")!=null){
        this.fest_adv = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fest_adv"));
        }else{
        this.fest_adv=0;
        }
    
    }
    public void setFoodDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "food")!=null){
        this.food_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "food"));
        }else{
        this.food_ded=0;
        }
    
    }
    public void setLoanDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "loan")!=null){
        this.loan = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "loan"));
        }else{
        this.loan=0;
        }
    
    }
    
    public void setCEBDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ceb")!=null){
        this.ceb_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "ceb"));
        }else{
        this.ceb_ded=0;
        }
    
    }
    
    public void setTeacherDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "teacher")!=null){
        this.teacher_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "teacher"));
        }else{
        this.teacher_ded=0;
        }
    
    }
    public void setChemicalDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "chemical")!=null){
        this.chemical_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "chemical"));
        }else{
        this.chemical_ded=0;
        }
    
    }
    
    public void setPayslipDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pay_slip")!=null){
        this.payslip_ded= Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pay_slip"));
        }else{
        this.payslip_ded=0;
        }
    
    }
    
    public void setFineDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fine")!=null){
        this.fine_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "fine"));
        }else{
        this.fine_ded=0;
        }
    
    }
    
    public void setWelafareDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "welfare")!=null){
        this.welfare_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "welfare"));
        }else{
        this.welfare_ded=0;
        }
    
    }
    
    public void setKovilDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "kovil")!=null){
        this.tea_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "kovil"));
        }else{
        this.tea_ded=0;
        }
    
    }
    
    public void setOther_Ded1(){
        this.other_ded1= getCEBDed()+getTeacherDed()+getChemicalDed()+getPayslipDed()+getFineDed()+getKovilDed()+getWelafareDed();
    
    }
   
    public void setMealsDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "meals")!=null){
        this.meals_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "meals"));
        }else{
        this.meals_ded=0;
        }
    
    }
    public void setOther_Ded2(){
        this.other_ded2=getMealsDed();
    
    }
     public void setPensionDed(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pension")!=null){
        this.pension_ded = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pension"));
        }else{
        this.pension_ded=0;
        }
    
    }
     public void setOther_Ded3(){
     
     this.other_ded3=getPensionDed();
     }
    
     
     public void setPreDebt(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pre_debt")!=null){
        this.pre_debt = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "pre_debt"));
        }else{
        this.pre_debt=0;
        }
    
    }
     
     public void setTotalDed(){
        this.total_ded=getEPFContribution()+getTeaDed()+getSalaryAdv()
                +getFestAdv()+getFoodDed()+getLoanDed()+getOther_Ded1()+getOther_Ded2()+getOther_Ded3()+getPreDebt();//+getETFContribution()
        getEPFContribution2();//this has to be called here to update database column total_EPF
        getETFContribution();
    }
       public void setPreMonthCoins(){
        if(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "prev_month_coins")!=null){
        this.prv_month_coins = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + st, "code", employCode, "prev_month_coins"));
        }else{
        this.prv_month_coins=0;
        }
    
    }
     

    public void setPettyCash() {
        pettyCash=getFinalSalary()%10;
    
    }

    public void setFinalSalary() {
        FinalSalary = getGrosspay()-getTotalDed()+ getPreMonthCoins();
        
    }

    public void setPaidAmount(){
        paid_amount=getFinalSalary()-getPettyCash();
    
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
         dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "normal_pay",normalDaysAmount);
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
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "sunday_pay", sundaysAmount);
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
         dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "incentive1", incentive1Amount);
        return incentive1Amount;
    }

    public double getIncentive2Amount() {
        setIncentive2Amount();
        dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "incentive2",incentive2Amount);
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
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "ot_before_amount", OTBeforeAmount);
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
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "ot_after_amount", OTAfterAmount);

        return OTAfterAmount;
    }

    public double getExtrapayAmount() {
        setExtrapayAmount();
        return extrapay;
    }

    public double getTotalBasicSallary() {
        setTotalBasicSallary();
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "total_pay", totalBasicSalary);
        return totalBasicSalary;
    }
    public double getGrosspay(){
        setGrosspay();
        dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "gross_pay", grosspay);
        return grosspay;
    }

    public double getEPFRate() {
        setEPFRate();
        return EPFRate;
    }

    public double getEPFContribution() {
        setEPFContribution();
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf10", EPFContribution);
        return EPFContribution;
    }
      public double getEPFRate2() {
        setEPFRate2();
        return EPFRate2;
    }

    public double getEPFContribution2() {
        setEPFContribution2();
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "epf12",EPFContribution2);
          setTotalEPF();//y?-total EPF has to be written in to the database when getSalary() method is called,(when we call getSalary() method it will call getEPF..2()method )
        return EPFContribution2;
      
    }


    public double getETFRate() {
        setETFRate();
        return ETFRate;
    }

    public double getETFContribution() {
        setETFContribution();
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "etf", ETFContribution);
        return ETFContribution;
    }

    public double getTeaDed(){
        setTeaDed();
        return tea_ded;
    
    }
    public double getSalaryAdv(){
        setSalaryAdv();
        return  salary_adv;
    }
    public double getFestAdv(){
        setFestAdv();
        return fest_adv;
    
    }
    public double getFoodDed(){
        setFoodDed();
        return food_ded;
    
    }
    public double getLoanDed(){
        setLoanDed();
        return loan;
    
    }
    
    public double getCEBDed(){
        setCEBDed();
        return ceb_ded;
    }
    
    public double getTeacherDed(){
        setTeacherDed();
        return teacher_ded;
    
    }
    public double getChemicalDed(){
       setChemicalDed();
       return chemical_ded;
    }
    
    public double getPayslipDed(){
        setPayslipDed();
        return payslip_ded;
    }
    
    public double getFineDed(){
        setFineDed();
        return fine_ded;
    
    }
    
    public double getWelafareDed(){
        setWelafareDed();
        return welfare_ded;
    }
    
    public double getKovilDed(){
       setKovilDed();
       return kovil_ded;
    }
    
    public double getOther_Ded1(){
        setOther_Ded1();
        dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "other_ded1", other_ded1);
        System.out.println("other deduction 1 updated");
        return other_ded1;
    
    }
   
    public double getMealsDed(){
        setMealsDed();
        return meals_ded;
    }
    public double getOther_Ded2(){
        setOther_Ded2();
        return other_ded2;
    
    }
     public double getPensionDed(){
        setPensionDed();
        return pension_ded;
    
    }
     public double getOther_Ded3(){
         setOther_Ded3();
         return other_ded3;
     }
    
     
     public double getPreDebt(){
         setPreDebt();
         return pre_debt;
    
    }
     
     public double getTotalDed(){
        setTotalDed();
        dbm.updateDatabase("pr_workdata_" + st, "code",employCode, "total_ded", total_ded);
        return total_ded;
    
    }
     
     public double getPreMonthCoins(){
        setPreMonthCoins();
        return prv_month_coins;
    }
    public double getWelfareRate() {
        setWelfareRate();
        return welfareRate;
    }

    public double getWelfareContribution() {
        setWelfareContribution();
        return welfareContribution;
    }

    
    public double getPettyCash() {
        setPettyCash();
        dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
        return pettyCash;
    }
    public String getString(){
        return st;
    }
    /*public double getFinalSalary(String ss) {
        this.st=ss;
        setMargin();
        //PRCR_NoteAnalysis naObject3 = new PRCR_NoteAnalysis();
        setFinalSalary();
        System.out.println("final salary-noteanalysis"+FinalSalary);
        dbm.updateDatabase("pr_workdata_" + ss, "code", employCode, "full_salary", FinalSalary);
        naObject3.ChNoteAnalysis(FinalSalary, employCode,ss);//set the note values according to the salary and update data base
        return FinalSalary;
    }*/
    public double getFinalSalary() {
        
        setFinalSalary();
        System.out.println("final salary-checkroll"+FinalSalary);
                  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "full_salary", FinalSalary);
                 if(FinalSalary>0){
                  pettyCash=FinalSalary%10;//"coins" and "paid_amount" will be calculated and updated to database when the getFinalSalary() is called from out side
                  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", pettyCash);
                  paid_amount=FinalSalary-pettyCash;
                  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", paid_amount);
                  dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "next_month", pettyCash);
                 }
                 else{
                 dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "coins", 0);
                 dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "paid_amount", 0);
                 dbm.updateDatabase("pr_workdata_" + st, "code", employCode, "next_month", FinalSalary);
                 }
        return FinalSalary;
    }
    
    public double getPaidAmount(){
        setPaidAmount();
        return paid_amount;
    
    }

}
