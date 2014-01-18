
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class BankBranch {
    
    Connection conn=null;
    Statement stmt=null;

    private String branchCode;
    private String branchName;

    BankBranch(String branchName, String branchCode) {
        this.branchName = branchName;
        this.branchCode = branchCode;
    }

    BankBranch() {
        branchCode = null;
        branchName = null;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }
    public void OpenDatabase() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bogoda", "root", "");
            System.out.println("Connected");

        } catch (Exception e) {
            System.out.println("Error in connection");
        }
    }

    public void CloseDatabase() {
        try {
            conn.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void addToDataBase() {
        OpenDatabase();
         try {
            Statement st = conn.createStatement();
           // System.out.println(amount + accountType);
            st.executeUpdate("INSERT INTO bankbranch(Code,BranchName) VALUES('" + branchCode + "','" + branchName + "')");
            JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Problem in Database connectivity  dsd or Data", "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        }
         CloseDatabase();

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }

}
