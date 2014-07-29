
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Checkroll_Pay_Info {
    
    private double normalDayRate;
    private double sundayRate;
    private double incentive_1;
    private double incentive_2;
    private int margin;
    private double OTRateBefore;//OT Rate - 4pm to 6pm
    private double OTRateAfter;// OT Rate - after 6pm
    private double ETFallowance;// %
    private double EPFallowance;
    private double EPFallowance2;
    private double welfareAllowance;
    private double holidayRate;
    
    public Checkroll_Pay_Info(double normalDayRate,double incentive_1,double incentive_2,double OTRateBefore,
            double OTRateAfter,double ETFallowance,double EPFallowance,double welfareAllowance,double holidayRate){
    
        this.normalDayRate=normalDayRate;
        this.incentive_1=incentive_1;
        this.incentive_2=incentive_2;
        this.OTRateBefore=OTRateBefore;
        this.OTRateAfter=OTRateAfter;
        this.ETFallowance=ETFallowance;
        this.EPFallowance=EPFallowance;
        this.welfareAllowance=welfareAllowance;
        this.holidayRate=holidayRate;
                
    }
    public Checkroll_Pay_Info(){
        this.normalDayRate=0;
        this.sundayRate=0;
        this.incentive_1=0;
        this.incentive_2=0;
        this.margin=0;
        this.OTRateBefore=0;
        this.OTRateAfter=0;
        this.ETFallowance=0;
        this.EPFallowance=0;
        this.EPFallowance2=0;
        this.welfareAllowance=0;
        this.holidayRate=0;
    }
    
    //setters
    public void setNormalDayRate(double normalDayRate){
        this.normalDayRate=normalDayRate;
    }
    public void setsundayRate(double sundayRate){
        this.sundayRate=sundayRate;
    }
    public void setIncentive1(double incentive_1){
        this.incentive_1=incentive_1;
    }
    public void setIncentive2(double incentive_2 ){
        this.incentive_2=incentive_2;
    }
    public void setMargin(int margin){
        this.margin=margin;
    }
    public void setOTRateBefore(double OTRateBefore){
        this.OTRateBefore=OTRateBefore;
    }
    public void setOTRateAfter(double OTRateAfter){
        this.OTRateAfter=OTRateAfter;
    }
    public void setETFallowance(double ETFallowance){
        this.ETFallowance=ETFallowance;
    }
    public void setEPFallowance(double EPFallowance){
        this.EPFallowance=EPFallowance;
    }
    public void setEPFallowance2(double EPFallowance2){
        this.EPFallowance2=EPFallowance2;
    }
    public void setWelfareAllowance(double wefareAllowance){
        this.welfareAllowance=wefareAllowance;
    }
    public void setHolidayRate(double holidayRate){
        this.holidayRate=holidayRate;
    }
    
    //getters
    public double getNormalDayRate(){
        return normalDayRate;
    }
    public double getSundayRate(){
        return sundayRate;
    }
    public double getIncentive1(){
        return incentive_1;
    }
    public double getIncentive2(){
        return incentive_2;
    }
    private int getMargin(){
        return margin;
    }
    public double getOTRateBefore(){
        return OTRateBefore;
    }
    public double getOTRateAfter(){
        return OTRateAfter;
    }
    public double getETFallowance(){
        return ETFallowance;
    }
    public double getEPFallowance(){
        return EPFallowance;
    }
    public double getWelfareAllowance(){
        return welfareAllowance;
    }
    public double getHolidayRate(){
        return holidayRate;
    }
    public void addTodatabase(){
        try {
            DatabaseManager dbm=DatabaseManager.getDbCon();
            dbm.insert("UPDATE checkroll_pay_info SET normalday_rate='"+normalDayRate+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET sunday_rate='"+sundayRate+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET incentive_1='"+incentive_1+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET incentive_2='"+incentive_2+"' WHERE checkroll='1'");
            //dbm.insert("UPDATE checkroll_pay_info SET margin='"+margin+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET otrate_before='"+OTRateBefore+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET otrate_after='"+OTRateAfter+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET epf='"+EPFallowance+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET epf2='"+EPFallowance2+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET etf='"+ETFallowance+"' WHERE checkroll='1'");
            dbm.insert("UPDATE checkroll_pay_info SET welfare='"+welfareAllowance+"' WHERE checkroll='1'");
            //dbm.insert("UPDATE checkroll_pay_info SET holiday_rate='"+holidayRate+"' WHERE checkroll='1'");
            JOptionPane.showMessageDialog(null, "Successfully Updated \n" , "Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
        }
        
    
    }
}
