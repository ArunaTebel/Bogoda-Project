
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

}
