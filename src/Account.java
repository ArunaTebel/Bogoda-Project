
public class Account {

    private String accountName;
    private String accountClass;
    private String accountCode;
    private double openingBal;
    private double currentBal;

    //private double closingBal;  // think this is not needed
    Account(String accountName, String accountClass, String accountCode, double openingBal,double currentBal) {
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.accountCode = accountCode;
        this.openingBal = openingBal;
        this.currentBal=currentBal;
    }

    Account() {
        accountName = null;
        accountClass = null;
        accountCode = null;
        openingBal = 0;
        currentBal=0;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }

    public String getAccountname() {
        return accountName;
    }

    public String getAccountClass() {
        return accountClass;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public double getOpeningBal() {
        return openingBal;
    }

    public void addToDataBase() {

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
