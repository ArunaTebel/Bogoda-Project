
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Extra_Payment_CashWork {
    
    Connection conn = null;
    Statement stmt = null;
    
    int emp_code;
    String work_code;
    int no_days;
    double rate;
    double amount;

    // Constructers
    
    public Extra_Payment_CashWork() {
        emp_code=0;
        work_code=null;
        no_days=0;
        rate=0;
        amount=0;
    }
    
    // Setters
    
    public void Set_emp_code(int emp_code){
        this.emp_code=emp_code;
    }
    public void Set_work_code(String work_code){
        this.work_code=work_code;
    }
    public void Set_no_days(int no_days){
        this.no_days=no_days;
    }
    public void Set_rate(double rate){
        this.rate=rate;
    }
    public void Set_amount(double amount){
        this.amount=amount;
    }
    
    // Getters
    
    public int Get_emp_code(){
        return emp_code;
    }
    public String Get_work_code(){
        return work_code;
    }
    public int Get_no_days(){
        return no_days;
    }
    public double Get_rate(){
        return rate;
    }
    public double Get_amount(){
        return amount;
    }
    
    // Add to database
    
     public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + amount + "','" + rate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
    
    
    
    
    
    
}
