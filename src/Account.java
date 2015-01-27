
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Account {

    private String accountName;
    private String accountClass;
    private int accountCode;
    private double openingBal;
    private double currentBalance;
    private int main_account_code;
    private int sub_account_code;

    //private double closingBal;  // think this is not needed
    Account(String accountName, String accountClass, int accountCode, double openingBal, double currentBalance) {
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.accountCode = accountCode;
        this.openingBal = openingBal;
        this.currentBalance = currentBalance;
    }

    Account() {
        accountName = null;
        accountClass = null;
        accountCode = 0;
        openingBal = 0;
        currentBalance = 0;
        main_account_code = 0;
        sub_account_code = 0;
    }

    // Setters
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountCode(int accountCode) {
        this.accountCode = accountCode;
    }

    public void setOpeningBal(double openingBal) {
        this.openingBal = openingBal;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void set_main_account_code(int accountCode) {
        main_account_code = accountCode / 10000;
    }

    public void set_sub_account_code(int accountCode) {
        sub_account_code = accountCode / 100;
    }

    public void setAccountClass(int main_account_code) {
        /*  if(main_account_code==1){
         accountClass= "Fixed Asset";
         }
         else if(main_account_code==2){
         accountClass="Current Asset";
         }
         else if(main_account_code==3){
         accountClass="Current Liability";
         }
         else if(main_account_code==4){
         accountClass="Equity";
         }
         else if(main_account_code==5){
         accountClass="Income";
         }
         else if(main_account_code==6){
         accountClass="Income";
         }
         else if(main_account_code==7){
         accountClass="Expense";
         }
         else if(main_account_code==8){
         accountClass="Expense";
         }
         // check this control accounts
         else if(main_account_code==9){
         accountClass=null;
         }*/

        DatabaseManager dbm = DatabaseManager.getDbCon();
        accountClass = dbm.checknReturnData("main_account_details", "main_account_code", main_account_code, "account_class");
    }

    // Getters
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

    public int get_main_account_code() {
        return main_account_code;
    }

    public int get_sub_account_code() {
        return sub_account_code;
    }

    public boolean addToDataBase() {

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_names(account_name,account_id,account_class,opening_balance,current_balance,main_account_code,sub_account_code) VALUES('" + accountName + "','" + accountCode + "','" + accountClass + "','" + openingBal + "','" + currentBalance + "','" + main_account_code + "','" + sub_account_code + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public void setDebitCombo(javax.swing.JComboBox combo) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM `account_control_panel_details` where `control` LIKE 'debit_ac_code_cache' ");
            while (query.next()) {
                combo.setSelectedItem(query.getString("value"));
            }
            query.close();
        } catch (SQLException ex) {
            combo.setSelectedIndex(0);
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setCreditCombo(javax.swing.JComboBox combo) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM `account_control_panel_details` where `control` LIKE 'credit_ac_code_cache' ");
            while (query.next()) {
                combo.setSelectedItem(query.getString("value"));
            }
            query.close();
        } catch (SQLException ex) {
            combo.setSelectedIndex(0);
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getFromDebitCombo(javax.swing.JComboBox combo) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        System.out.println("thisss" + combo.getSelectedItem().toString());
        dbm.updateDatabase("account_control_panel_details", "control", "debit_ac_code_cache", "value", combo.getSelectedItem().toString());

    }

    public void getFromCreditCombo(javax.swing.JComboBox combo) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        System.out.println("thisss" + combo.getSelectedItem().toString());
        dbm.updateDatabase("account_control_panel_details", "control", "credit_ac_code_cache", "value", combo.getSelectedItem().toString());

    }

    public void setDebitCache(String set) {
        DatabaseManager dbm = DatabaseManager.getDbCon();

        dbm.updateDatabase("account_control_panel_details", "control", "debit_ac_code_cache", "value", set);

    }

    public void setCreditCache(String set) {
        DatabaseManager dbm = DatabaseManager.getDbCon();

        dbm.updateDatabase("account_control_panel_details", "control", "credit_ac_code_cache", "value", set);

    }

    public int checkCache(String str) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String name = str + "_cache";
        try {
            ResultSet query = dbm.query("SELECT * FROM `account_control_panel_details` where `control` LIKE '" + name + "' ");
            while (query.next()) {
                if (query.getInt("value") == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
            query.close();
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public void setCacheReceipts(javax.swing.JRadioButton debit, javax.swing.JRadioButton credit, javax.swing.JRadioButton both, javax.swing.JRadioButton none) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        if (debit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_credit_cache", "value", 0);
        }
        else if (credit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_credit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_debit_cache", "value", 0);
        }
        else if (both.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_credit_cache", "value", 1);
        }
        else if (none.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_debit_cache", "value", 0);
            dbm.updateDatabase("account_control_panel_details", "control", "receipt_credit_cache", "value", 0);
        }
    }
    
     public void setCachePayments(javax.swing.JRadioButton debit, javax.swing.JRadioButton credit, javax.swing.JRadioButton both, javax.swing.JRadioButton none) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        if (debit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "payment_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "payment_credit_cache", "value", 0);
        }
        else if (credit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "payment_credit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "payment_debit_cache", "value", 0);
        }
        else if (both.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "payment_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "payment_credit_cache", "value", 1);
        }
        else if (none.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "payment_debit_cache", "value", 0);
            dbm.updateDatabase("account_control_panel_details", "control", "payment_credit_cache", "value", 0);
        }
    }
     
      public void setCacheJournals(javax.swing.JRadioButton debit, javax.swing.JRadioButton credit, javax.swing.JRadioButton both, javax.swing.JRadioButton none) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        if (debit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "journal_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "journal_credit_cache", "value", 0);
        }
        else if (credit.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "journal_credit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "journal_debit_cache", "value", 0);
        }
        else if (both.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "journal_debit_cache", "value", 1);
            dbm.updateDatabase("account_control_panel_details", "control", "journal_credit_cache", "value", 1);
        }
        else if (none.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "journal_debit_cache", "value", 0);
            dbm.updateDatabase("account_control_panel_details", "control", "journal_credit_cache", "value", 0);
        }
    }
      
       public void setEditMode(javax.swing.JRadioButton newMode, javax.swing.JRadioButton oldMode) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        if (newMode.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "editInterface", "value", 1);
        }
        else if (oldMode.isSelected()) {
            dbm.updateDatabase("account_control_panel_details", "control", "editInterface", "value", 0);
        }
    }
       
    public void setEditRadioButtonAtTheBegining(javax.swing.JRadioButton newMode, javax.swing.JRadioButton oldMode) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        
        int editInterface =  Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control", "editInterface", "value"));
        
        if(editInterface==1){
            newMode.setSelected(true);
            oldMode.setSelected(false);
        }else{
            newMode.setSelected(false);
            oldMode.setSelected(true);
        }
    }
       
      
      public void setSelectedRadioButtonAtTheBegining(javax.swing.JRadioButton debit, javax.swing.JRadioButton credit, javax.swing.JRadioButton both, javax.swing.JRadioButton none,String str){
          debit.setSelected(false);
          credit.setSelected(false);
          both.setSelected(false);
          none.setSelected(false);
          
          DatabaseManager dbm = DatabaseManager.getDbCon();
          String debitName = str + "_debit_cache";
          String creditName = str + "_credit_cache";
          int debitVal = Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control",debitName, "value"));
          int creditVal = Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control",creditName, "value"));
          if(debitVal==1 && creditVal==1){
              both.setSelected(true);
          }
          else if(debitVal==1){
              debit.setSelected(true);
          }
          else if(creditVal==1){
              credit.setSelected(true);
          }
          else{
              none.setSelected(true);
          }
      }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
