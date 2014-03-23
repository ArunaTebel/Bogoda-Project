
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PRCR_holiday_pay_rate_details {
    int lower;
    int upper;
    int days;
    
    Connection conn = null;
    Statement stmt = null;

    public PRCR_holiday_pay_rate_details() {
        lower=0;
        upper=0;
        days=0;
    }
    
    // setters
    
    public void Set_lower(int lower){
        this.lower=lower;
    }
    public void Set_upper(int upper){
        this.upper=upper;
    }
    public void Set_days(int days){
        this.days=days;
    }
    
    // getters
    
    public int Get_lower(){
        return lower;
    }
    public int Get_upper(){
        return upper;
    }
    public int Get_days(){
        return days;
    }
   
    // add to database
    
     public void addToDataBaseMale() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO holiday_pay_rate_details_male(lower,upper,days) VALUES('" + lower + "','" + upper + "','" + days + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
     public void addToDataBaseFemale() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO holiday_pay_rate_details_female(lower,upper,days) VALUES('" + lower + "','" + upper + "','" + days + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
    
    
}
