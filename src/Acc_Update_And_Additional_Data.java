
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acc_Update_And_Additional_Data {

    public void update_table(String table1, String table2, String field, String user,String date) {
        try {
            DatabaseManager dbm = DatabaseManager.getDbCon();
            Date_Handler dt = new Date_Handler();
            int yearo;
            yearo=Integer.parseInt(date.substring(0,4));
            
            
            String yearo_yearn= yearo + "-"+(yearo-1999);
            
            
            dbm.insert("INSERT INTO " + table2 + " SELECT * FROM " + table1 + "");
            dbm.DeleteTable(table1);
            dbm.insert("INSERT INTO account_update_details(field,date,user) VALUES ('" + field + "','" + date + "','" + user + "')");
            dbm.insert("Truncate acc_current_period_act");
            dbm.insert("INSERT INTO acc_current_period_act (period) VALUES('"+yearo_yearn+"')");

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

        String new_table_name = dt.get_today_year() + "_balances";
        String previous_year_table_name = (Integer.parseInt(dt.get_today_year()) - 1) + "_balances";

        double temp = 0;

        if (dbm.TableExistence(new_table_name)) {
            try {
                dbm.insert("Truncate " + new_table_name + "");
            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                dbm.insert("CREATE TABLE " + new_table_name + "(account_code varchar(50),op_bal_d double,op_bal_c double,clo_bal_d double,clo_bal_c double)");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dbm.TableExistence(previous_year_table_name) == true) {

            try {
                ResultSet query = dbm.query("SELECT * FROM account_names");
                while (query.next()) {
                    temp = Double.parseDouble(query.getString("current_balance"));

                    dbm.updateDatabase(previous_year_table_name, "account_code", query.getString("account_id"), "clo_bal", temp);

                    //   dbm.insert("INSERT INTO "+new_table_name+"(account_code,op_bal,clo_bal) VALUES('"+query.getString("account_id")+"','"+temp+"',0)");
                    //   dbm.updateDatabase("account_names","account_id",Integer.parseInt(query.getString("account_id")),"opening_balance", temp);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {
                temp = Double.parseDouble(query.getString("current_balance"));

                    //dbm.updateDatabase(previous_year_table_name,"account_code",query.getString("account_id"),"clo_bal",temp);
           /*     dbm.insert("INSERT INTO " + new_table_name + "(account_code,op_bal,clo_bal) VALUES('" + query.getString("account_id") + "','" + temp + "',0)");

                 dbm.updateDatabase("account_names", "account_id", Integer.parseInt(query.getString("account_id")), "opening_balance", temp);*/
                // Here I took that other than in expense and income accounts balances will be carried forward as op balances
                if (query.getInt("main_account_code") == 1 || query.getInt("main_account_code") == 2) {
                    dbm.insert("INSERT INTO " + new_table_name + "(account_code,op_bal_d,op_bal_c,clo_bal) VALUES('" + query.getString("account_id") + "','" + temp + "',0,0)");

                    dbm.updateDatabase("account_names", "account_id", Integer.parseInt(query.getString("account_id")), "opening_balance", temp);

                } else if (query.getInt("main_account_code") == 3 || query.getInt("main_account_code") == 4) {
                    dbm.insert("INSERT INTO " + new_table_name + "(account_code,op_bal_d,op_bal_c,clo_bal) VALUES('" + query.getString("account_id") + "',0,'" + temp + "',0)");

                    dbm.updateDatabase("account_names", "account_id", Integer.parseInt(query.getString("account_id")), "opening_balance", temp);

                } else {
                    dbm.insert("INSERT INTO " + new_table_name + "(account_code,op_bal_d,op_bal_c,clo_bal) VALUES('" + query.getString("account_id") + "',0,0,0)");

                    dbm.updateDatabase("account_names", "account_id", Integer.parseInt(query.getString("account_id")), "opening_balance", 0);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public void complete_op_bal_temp() {
        DatabaseManager dbm = new DatabaseManager();
        try {
            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {

                if (dbm.checkWhetherDataExists("2015_balances", "account_code", query.getString("account_id")) == 0) {
                    dbm.insert("INSERT INTO 2015_balances(account_code,op_bal_d,op_bal_c,clo_bal)VALUES('" + query.getString("account_id") + "',0,0,0)");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // This method is to get the current balance initially..... P.S - change the opening_balance_calc method  in Acc Ledger first  op_balance = op_c not op_balance=-op_c in line 626
    
  //  public void complete_op_bal_temp() {
      /*  DatabaseManager dbm = new DatabaseManager();
        ACC_ledger ledg = new ACC_ledger();
        double op=0;
        
        try {
            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {
              op=0;
              op= ledg.opening_balance_calc(query.getInt("account_id"),"2014-12-12");
              dbm.updateDatabase("account_names","account_id",query.getInt("account_id"),"current_balance",op);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
        }*/
   // }
}
