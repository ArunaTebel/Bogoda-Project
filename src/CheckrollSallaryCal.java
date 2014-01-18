/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class CheckrollSallaryCal {

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

    public CheckrollSallaryCal() {//NC
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
    }

    //setters
    public void setNormalDays() {
        //should be calculated using database
    }

    public void setNormalDaysRate() {
        //should be read from database
    }

    public void setNormalDaysAmount() {
        normalDaysAmount = getNormaDays() * getNormaldaysRate();
    }

    public void setSundays() {
        //should be calculated using database
    }

    public void setSundayRate() {
        //should be read from database
    }

    public void setSundayAmount() {
        sundaysAmount = getSundayRate() * getSundays();
    }

    public void setWorkdays() {
        workdays = getSundays() + getNormaDays();
    }

    public void setIncentive1Rate() {
        //should be read from database
    }

    public void setIncentive2rate() {
        //should be read from database
    }

    public void setIncentive1Amount() {//
        incentive1Amount = getWorkdays() * getIncentive12Rate();
    }

    public void setIncentive2Amount() {

        if (workdays > 20) {//check this again whether its 20 or not
            incentive2Amount = getWorkdays() * getIncentive12Rate();
        } else {
            incentive2Amount = 0;
        }
    }

    public void setTotalIncentiveAmount() {

        totalIncentiveAmount = getIncentive1Amount() + getIncentive2Amount();
    }

    public void setOTBeforeRate() {
        //read from database
    }

    public void setOTBeforeHours() {
        //calculate using database
    }

    public void setOTBeforeAmount() {

        OTBeforeAmount = getOTBeforeHours() * getOTBeforeRate();
    }

    public void setOTAfterRate() {
        //read from database
    }

    public void setOTAfterHours() {
        //calculate using database
    }

    public void setOTAfterAmount() {
        OTAfterAmount = getOTAfterHours() * getOTAfterRate();
    }

    public void setTotalBasicSallary() {
        totalBasicSalary = getNormalDaysAmount() + getSundayAmount() + getTotalIncentiveAmount() + getOTAfterAmount() + getOTBeforeAmount();
    }

    public void setEPFRate() {
        //read from database
    }

    public void setEPFContribution() {
        EPFContribution = getTotalBasicSallary() * (getEPFRate() / 100);
    }

    public void setETFRate() {
        //read from database
    }

    public void setETFContribution() {
        ETFContribution = getTotalBasicSallary() * (getETFRate() / 100);
    }

    public void setWelfareRate() {
        //read from database
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
        FinalSalary = getTotalBasicSallary() - getETFContribution() - getEPFContribution() - getLoanDeductions() - getFineDeductions() - getStoreDeductions() + getPettyCash();
    }

    //getters
    public int getNormaDays() {//nc
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

    public double getIncentive12Rate() {
        setIncentive2rate();
        return incentive2Rate;
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
        setNormalDaysAmount();
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
        return FinalSalary;
    }

}
