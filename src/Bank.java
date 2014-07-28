
import java.sql.Connection;
import java.sql.ResultSet;
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
    
    public void updateDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("UPDATE  bank SET bank_name='"+bankName+"' WHERE bank_id ='" + bankCode + "'");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
    public void  from_databse_to_the_table(javax.swing.JTable table){
        
            int i=0;
         DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM bank");
            while (query.next()) {
                table.setValueAt(query.getString("bank_id"),i , 0);
                table.setValueAt(query.getString("bank_name"),i , 1);
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
