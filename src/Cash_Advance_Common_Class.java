
import java.sql.Date;
import java.sql.SQLException;


public class Cash_Advance_Common_Class {
    UserAccountControl UserAC = new UserAccountControl();
    int sup_id;
    String sup_name;
    double max_allowable;
    double amount;
    Date date;
    String pay;
    String user  = UserAC.get_current_user();
    
    public Cash_Advance_Common_Class(){
        sup_id=0;
        sup_name=null;
        max_allowable=0;
        amount=0;
        date=null;
        pay = null;
    }
    
    // Setters
    
    public void set_sup_id(int sup_id){
        this.sup_id=sup_id;
    }
    public void set_sup_name(String sup_name){
        this.sup_name=sup_name;
    }
    public void set_max_allowable(double max_allowable){
        this.max_allowable=max_allowable;
    }
    public void set_date(Date date){
        this.date=date;
    }
    public void set_amount(double amount){
        this.amount=amount;
    }
     public void set_pay_type(String amount){
        this.pay=amount;
    }
    
   
    
    
    // Getters
    
    public int get_sup_id(){
        return sup_id;
    }
    public String get_sup_name(){
        return sup_name;
    }
    public double get_max_allowable(){
        return max_allowable;
    }
    public Date get_date(){
        return date;
    }
    public double get_amount(){
        return amount;
    }
    
    // Add to database
    
      public boolean addToDataBase() {
         
        
       
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO gl_cash_advance_book(sup_id,sup_name,date,max_allowable,amount,pay_type,user) VALUES('" + sup_id + "','" + sup_name + "','" + date + "','" + max_allowable + "','"+ amount + "','"+ pay + "','"+user+ "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
   
    
}
