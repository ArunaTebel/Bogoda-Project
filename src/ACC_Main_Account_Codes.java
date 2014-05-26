
import java.sql.SQLException;


public class ACC_Main_Account_Codes {
    
    int main_account_code;
    String description;
    String account_class;

    public ACC_Main_Account_Codes() {
        
        main_account_code=0;
        description=null;
        account_class=null;
        
    }
    
    //Setters
    
    public void set_main_account_code(int main_account_code){
        this.main_account_code=main_account_code;
    }
    
    public void set_description(String description){
        this.description=description;
    }
    
    public void set_account_class(String account_class){
        this.account_class=account_class;
    }
    
    //Getters
    
    public int get_main_account_code(){
        return main_account_code;
    }
    
    public String get_description(){
        return description;
    }
            
    public String get_account_class(){
        return account_class;
    }
    
    
    // Adding to the database
    
      public boolean addToDataBase() {
        
       
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO main_account_details(main_account_code,description,account_class) VALUES('" + main_account_code + "','" + description + "','" + account_class + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
    
}
