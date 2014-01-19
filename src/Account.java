
import java.sql.SQLException;

public class Account {

    private String accountName;
    private String accountClass;
    private int accountCode;
    private double openingBal;

    //private double closingBal;  // think this is not needed
    Account(String accountName, String accountClass, int accountCode, double openingBal) {
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.accountCode = accountCode;
        this.openingBal = openingBal;
    }

    Account() {
        accountName = null;
        accountClass = null;
        accountCode = 0;
        openingBal = 0;
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
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_names(account_name,account_id,account_class,opening_balance) VALUES('" + accountName + "','" + accountCode + "','" + accountClass + "','" + openingBal + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
