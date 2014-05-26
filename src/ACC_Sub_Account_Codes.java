
import java.sql.SQLException;


public class ACC_Sub_Account_Codes {
    
    int sub_account_code;
    String description;

    public ACC_Sub_Account_Codes() {
        
        sub_account_code=0;
        description=null;
    }
    
    // Setters
    
    public void set_sub_account_code(int sub_account_code){
        this.sub_account_code=sub_account_code;
    }
    
    public void set_description(String description){
        this.description=description;
    }
    
    // Getters
    
    public int get_sub_account_code(){
        return sub_account_code;
    }
    
    public String get_description(){
        return description;
    }
    
    // Add to database
    
     public boolean addToDataBase() {
        
       
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO sub_account_details(sub_account_code,description) VALUES('" + sub_account_code + "','" + description + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
    
    
}
