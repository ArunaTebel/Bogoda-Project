
import java.sql.SQLException;

public class Account {

    private String accountName;
    private String accountClass;
    private int accountCode;
    private double openingBal;
    private double currentBalance;
    private int main_account_code;
    private int sub_account_code;

    //private double closingBal;  // think this is not needed
    Account(String accountName, String accountClass, int accountCode, double openingBal,double currentBalance) {
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.accountCode = accountCode;
        this.openingBal = openingBal;
        this.currentBalance=currentBalance;
    }

    Account() {
        accountName = null;
        accountClass = null;
        accountCode = 0;
        openingBal = 0;
        currentBalance=0;
        main_account_code=0;
        sub_account_code=0;
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
    public void setCurrentBalance(double currentBalance){
        this.currentBalance=currentBalance;
    }
    
    public void set_main_account_code(int accountCode){
        main_account_code=accountCode/10000;
    }
    
    public void set_sub_account_code(int accountCode){
        sub_account_code=accountCode/100;
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
        accountClass=dbm.checknReturnData("main_account_details","main_account_code",main_account_code,"account_class");
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
    
    public int get_main_account_code(){
        return main_account_code;
    }
    
    public int get_sub_account_code(){
        return sub_account_code;
    }

    public boolean addToDataBase() {
        
       
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_names(account_name,account_id,account_class,opening_balance,current_balance,main_account_code,sub_account_code) VALUES('" + accountName + "','" + accountCode + "','" + accountClass + "','" + openingBal + "','"+currentBalance+ "','"+main_account_code+"','"+sub_account_code+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
