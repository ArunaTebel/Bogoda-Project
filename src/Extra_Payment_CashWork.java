
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Extra_Payment_CashWork {
    
    Connection conn = null;
    Statement stmt = null;
    DateChooser_text month_num = new DateChooser_text();
      
    int emp_code;
    String work_code;
    int no_days;
    double rate;
    double amount;
    String month;
    String year;
    double tot_extrapay;
    String s;

    // Constructers
    
    public Extra_Payment_CashWork() {
        emp_code=0;
        work_code=null;
        no_days=0;
        rate=0;
        amount=0;
        month =null;
        year= null;
        s=null;
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
    public void Set_amount(){
        this.amount=rate*no_days;
    }
    public void Set_month(String month){
        this.month=month;
        s=year+"_0"+month_num.return_index(month);
    }
    public void Set_year(String year){
        this.year=year;
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
    public String Get_month(){
        return month;
    }
    public String Get_year(){
        return year;
    }

  
      
    // Add to database
   // String v=year+"_0"+1;
    
      public void addtoDatabase() {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        tot_extrapay=Double.parseDouble(dbm.checknReturnData("pr_workdata"+"s","code",emp_code,"extra_pay"))+amount;
        try {

           // dbm.insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' WHERE " + table_column_giving + "='" + row_element + "'");
            dbm.insert("UPDATE  pr_workdata_"+s+"   SET   extra_pay = "+tot_extrapay+" WHERE code= " + emp_code +" ");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
           
        }
       
    }
    
    
    
    
    
    
}
