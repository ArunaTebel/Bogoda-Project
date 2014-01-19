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
    private double incentive_1;
    private double incentive_2;
    private double OTRateBefore;//OT Rate - 4pm to 6pm
    private double OTRateAfter;// OT Rate - after 6pm
    private double ETFallowance;// %
    private double EPFallowance;
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
        this.normalDayRate=normalDayRate;
                
    }
    public Checkroll_Pay_Info(){
        this.normalDayRate=0;
        this.incentive_1=0;
        this.incentive_2=0;
        this.OTRateBefore=0;
        this.OTRateAfter=0;
        this.ETFallowance=0;
        this.EPFallowance=0;
        this.welfareAllowance=0;
        this.normalDayRate=0;
    }
    
    //setters
    public void setNormalDayRate(double normalDayRate){
        this.normalDayRate=normalDayRate;
    }
    public void setIncentive1(double incentive_1){
        this.incentive_1=incentive_1;
    }
    public void setIncentive2(double incentive_2 ){
        this.incentive_2=incentive_2;
    }
    public void setOTRateBefore(double OTRateBefore){
        this.OTRateBefore=OTRateBefore;
    }

    
}
