
public class Account {

    private String accountName;
    private String accountClass;
    private int accountCode;
    private double openingBal;

    //private double closingBal;
    Account(String accountName, String accountClass, int accountCode, double openingBal) {
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.accountCode = accountCode;;
        this.openingBal = openingBal;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    public void setAccountCode(int accountCode) {
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

    public int getAccountCode() {
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
