
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Bank {

    Connection conn = null;
    Statement stmt = null;

    private String bankCode;
    private String bankName;

    Bank(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    Bank() {
        bankCode = null;
        bankName = null;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;

    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO bank(Code,BankName) VALUES('" + bankCode + "','" + bankName + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", bankCode);
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
