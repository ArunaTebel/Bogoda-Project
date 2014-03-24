
import java.sql.Date;
import java.sql.SQLException;

public class GL_other_advances_class {
    
    int sup_id;
    String sup_name;
    double max_allowable;
    double amount;
    Date date;
    String item_name;
    String item_type;
    double item_rate;
    double quantity;
    
    
    public GL_other_advances_class(){
        sup_id=0;
        sup_name=null;
        max_allowable=0;
        amount=0;
        date=null;
        item_name=null;
        item_type=null;
     item_rate=0;
     quantity=0;
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
     public void set_item_rate(double amount){
        this.item_rate=amount;
    }
      public void set_quantity(double amount){
        this.quantity=amount;
    }
       public void set_item_name(String sup_name){
        this.item_name=sup_name;
    }
        public void set_item_type(String sup_name){
        this.item_type=sup_name;
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
            dbCon.insert("INSERT INTO gl_other_advance_book(Date,id,name,max_allow,item_name,item_type,item_rate,item_quantity,total_amount) VALUES('" + date + "','" + sup_id + "','" + sup_name + "','" + max_allowable + "','"+ item_name + "','"+item_type + "','"+ item_rate+ "','"+ quantity + "','"+amount+ "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
   
    
}
