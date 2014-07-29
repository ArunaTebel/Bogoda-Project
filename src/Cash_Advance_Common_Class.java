
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cash_Advance_Common_Class {

    UserAccountControl UserAC = new UserAccountControl();
    Date_Handler date_handler = new Date_Handler();
    DatabaseManager dbm = new DatabaseManager();
    int sup_id, bank;
    String sup_name, emrg, ref_no, cheque, date_time, special_permission;
    double max_allowable;
    double amount;
    Date date, issue_date, cheque_date;
    String pay;
    String user = UserAC.get_current_user();
    int month_tr;

    public Cash_Advance_Common_Class() {
        sup_id = 0;
        sup_name = null;
        max_allowable = 0;
        amount = 0;
        date = null;
        pay = null;
        month_tr = 0;
    }

    // Setters
    public void set_sup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public void set_bank(int sup_id) {
        this.bank = sup_id;
    }

    public void set_sup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public void set_emergency(boolean emr) {
        if (emr) {
            emrg = "YES";
        } else {
            emrg = "NO";
        }
    }

    public void set_permission(boolean emr) {
        if (emr) {
            special_permission = "YES";
        } else {
            special_permission = "NO";
        }
    }

    public void set_ref_no(String sup_name) {
        this.ref_no = sup_name;
    }

    public void set_cheque_no(String sup_name) {
        this.cheque = sup_name;
    }

    public void set_max_allowable(double max_allowable) {
        this.max_allowable = max_allowable;
    }

    public void set_date(Date date) {
        this.date = date;
    }

    public void set_issue_date(Date date) {
        this.issue_date = date;
    }

    public void set_cheque_date(Date date) {
        this.cheque_date = date;
    }

    public void set_amount(double amount) {
        this.amount = amount;
    }

    public void set_pay_type(String amount) {
        this.pay = amount;
    }

    public void set_month_tr(int amount) {
        this.month_tr = amount;
    }

    // Getters
    public int get_sup_id() {
        return sup_id;
    }

    public String get_sup_name() {
        return sup_name;
    }

    public double get_max_allowable() {
        return max_allowable;
    }

    public Date get_date() {
        return date;
    }

    public double get_amount() {
        return amount;
    }

    // Add to database
    public boolean addToDataBase() {

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO gl_cash_advance_book(sup_id,sup_name,date,issue_date,max_allowable,amount,pay_type,special_permission,user) VALUES('" + sup_id + "','" + sup_name + "','" + date + "','" + issue_date + "','" + max_allowable + "','" + amount + "','" + pay + "','" + special_permission + "','" + user + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean addToDataBasemain() {
        date_time = date_handler.get_today_date_time();

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO gl_cash_advance(month_tr_no,sup_id,pay_type,ordered_date,issued_date,emergency,special_permission,amount,ref_no,bank_code,cheque_no,cheque_date,date_time,user)"
                    + " VALUES('" + month_tr + "','" + sup_id + "','" + pay + "','" + date + "','" + issue_date + "','" + emrg + "','" + special_permission + "','" + amount + "','" + ref_no + "','" + bank + "','" + cheque + "','" + cheque_date + "','" + date_time + "','" + user + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean tranfer() {

        GL_Over_Advance overadvance = new GL_Over_Advance();
        date_time = date_handler.get_today_date_time();
       //issue_date = java.sql.Date.valueOf(date_handler.get_today_date());                 // untill cheque payment part is implimented cheque values stay default
        cheque_date = java.sql.Date.valueOf(date_handler.get_today_date());                //

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbCon.query("SELECT * FROM gl_cash_advance_book");

            while (query.next()) {
               // System.out.println("writing");

//                if ("YES".equals(query.getString("special_permission"))) {
//                    overadvance.Set_Sup_ID(query.getInt("sup_id"));
//                    overadvance.Set_Sup_Name();
//                    overadvance.Set_Category_Code();
//                    overadvance.Set_Advance_Taken(query.getDouble("amount"));
//                    overadvance.Set_Issued_Date(issue_date);
//                    overadvance.AddToOverAdvanceDatabase();
//                }

                dbCon.insert("INSERT INTO gl_cash_advance(month_tr_no,sup_id,pay_type,ordered_date,issued_date,emergency,special_permission,amount,ref_no,bank_code,cheque_no,cheque_date,date_time,user)"
                        + " VALUES('" + query.getInt("entry_no") + "','" + query.getInt("sup_id") + "','" + query.getString("pay_type") + "','" + query.getDate("date") + "','" + query.getDate("issue_date") + "','" + "NO" + "','" + query.getString("special_permission") + "','" + query.getDouble("amount") + "','" + ref_no + "','" + bank + "','" + cheque + "','" + cheque_date + "','" + date_time + "','" + user + "')");
            }

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean truncate() {

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("Truncate table gl_cash_advance_book");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean AddToDeleteDataBase() {
        date_time = date_handler.get_today_date_time();

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO gl_cash_advance_book_delete(book_tr_no,sup_id,sup_name,amount,date_time,user)"
                    + " VALUES('" + month_tr + "','" + sup_id + "','" + sup_name + "','" + amount + "','" + date_time + "','" + user + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
    public void update_database(int entry){
    
    DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("UPDATE gl_cash_advance SET month_tr_no ='" + 0 + "', sup_id= '" + sup_id + "',pay_type = '" + pay + "',ordered_date = '" + date + "',issued_date = '" + date + "' ,emergency = '" + emrg + "',special_permission = '" + special_permission + "',amount = '" + amount + "',ref_no = '" + null + "',bank_code = '" + 0 + "',cheque_no = '" + null + "',cheque_date = '" + date + "',date_time = '" + date_time + "',user = '" + user + "'  WHERE entry_no = '" + entry + "'" );
        } catch (SQLException ex) {
            Logger.getLogger(Cash_Advance_Common_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
