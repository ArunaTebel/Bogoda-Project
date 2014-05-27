
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankBranch {

    Connection conn = null;
    Statement stmt = null;

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
    
      public void from_databse_to_the_table(javax.swing.JTable table){
        
            int i=0;
         DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM bank_branch");
            while (query.next()) {
                table.setValueAt(query.getString("branch_id"),i , 0);
                table.setValueAt(query.getString("branch_name"),i , 1);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }

}
