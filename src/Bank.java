
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Bank {

    Connection conn = null;
    Statement stmt = null;

    private int bankCode;
    private String bankName;

    Bank(String bankName, int bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    Bank() {
        bankCode = 0;
        bankName = null;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;

    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
