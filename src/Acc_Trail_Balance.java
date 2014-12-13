
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acc_Trail_Balance {

    DatabaseManager dbm = DatabaseManager.getDbCon();
    ACC_ledger ledg = new ACC_ledger();

    public void create_table(String date, boolean chk) {

        try {
            try {
                dbm.insert("Truncate acc_trail_balance");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {
                if (chk) {
                   // System.out.println(query.getString("account_id"));
                    double bal = ledg.opening_balance_calc_for_tb(query.getInt("account_id"), date);
                   
                    dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + bal + "')");
                } else {
                    double bal = ledg.opening_balance_calc_without_op(query.getInt("account_id"), date);
                   
                    dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + bal + "')");
                }

            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
