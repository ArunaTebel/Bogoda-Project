
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acc_Update_And_Additional_Data {

    public void update_table(String table1, String table2, String field, String user) {
        try {
            DatabaseManager dbm = DatabaseManager.getDbCon();
            Date_Handler dt = new Date_Handler();
            dbm.insert("INSERT INTO " + table2 + " SELECT * FROM " + table1 + "");
            dbm.DeleteTable(table1);
            dbm.insert("INSERT INTO account_update_details(field,date,user) VALUES ('" + field + "','" + dt.get_today_date_time() + "','" + user + "')");

        } catch (SQLException ex) {
            Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void copy_table(String table1, String table2) {
        try {
            DatabaseManager dbm = DatabaseManager.getDbCon();
            //  ResultSet query = dbm.query("INSERT INTO "+table2+" SELECT * FROM "+table1+"");
            dbm.insert("CREATE TABLE account_journal_main_all LIKE account_journal_main");
            dbm.insert("CREATE TABLE account_journal_debitside_all LIKE account_journal_debitside");
            dbm.insert("CREATE TABLE account_journal_creditside_all LIKE account_journal_creditside");

            dbm.insert("CREATE TABLE account_payment_creditside_all LIKE account_payment_creditside");
            dbm.insert("CREATE TABLE account_payment_debitside_all LIKE account_payment_debitside");

            dbm.insert("CREATE TABLE account_reciept_creditside_all LIKE account_reciept_creditside");
            dbm.insert("CREATE TABLE account_reciept_debitside_all LIKE account_reciept_debitside");
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update_account_balances() {
        
        
        // under this method all the current balances of the accounts will be transfered in to their op balances + the current balances
        // will be recorded in a new table as the op balances for the new year + they also be recorded in the previous years table as closing balances
        
        DatabaseManager dbm = DatabaseManager.getDbCon();
        Date_Handler dt = new Date_Handler();
        
        String new_table_name = dt.get_today_year()+"_balances";
        String previous_year_table_name = (Integer.parseInt(dt.get_today_year())-1)+"_balances";
        
        double temp =0;
         
        try {
            dbm.insert("CREATE TABLE " + new_table_name + "(account_code varchar(50),op_bal double,clo_bal double)");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(dbm.TableExistence(previous_year_table_name)==true){
            
            try {
                ResultSet query = dbm.query("SELECT * FROM account_names");
                while(query.next()){
                    temp=Double.parseDouble(query.getString("current_balance"));
                    
                    dbm.updateDatabase(previous_year_table_name,"account_code",query.getString("account_id"),"clo_bal",temp);
                    
                 //   dbm.insert("INSERT INTO "+new_table_name+"(account_code,op_bal,clo_bal) VALUES('"+query.getString("account_id")+"','"+temp+"',0)");
                    
                 //   dbm.updateDatabase("account_names","account_id",Integer.parseInt(query.getString("account_id")),"opening_balance", temp);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
                ResultSet query = dbm.query("SELECT * FROM account_names");
                while(query.next()){
                    temp=Double.parseDouble(query.getString("current_balance"));
                    
                    //dbm.updateDatabase(previous_year_table_name,"account_code",query.getString("account_id"),"clo_bal",temp);
                    
                    dbm.insert("INSERT INTO "+new_table_name+"(account_code,op_bal,clo_bal) VALUES('"+query.getString("account_id")+"','"+temp+"',0)");
                    
                    dbm.updateDatabase("account_names","account_id",Integer.parseInt(query.getString("account_id")),"opening_balance", temp);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                
        
    }
}


