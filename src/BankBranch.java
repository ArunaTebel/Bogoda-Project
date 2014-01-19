
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class BankBranch {
    
    Connection conn=null;
    Statement stmt=null;

    private int branchCode;
    private String branchName;

    BankBranch(String branchName, int branchCode) {
        this.branchName = branchName;
        this.branchCode = branchCode;
    }

    BankBranch() {
        branchCode = 0;
        branchName = null;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getBranchCode() {
        return branchCode;
    }
    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO bank_branch(branch_id,branch_name) VALUES('" + branchCode + "','" + branchName + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }

}
