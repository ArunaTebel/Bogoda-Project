
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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
            st.executeUpdate("INSERT INTO bank(Code,BankName) VALUES('" + bankCode + "','" + bankName + "')");
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
