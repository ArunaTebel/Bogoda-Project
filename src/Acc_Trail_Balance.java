
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

    public void check() {
        try {
            ResultSet query = dbm.query("SELECT * from account_reciept_debitside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_reciept_creditside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double tot = 0;
                while (query1.next()) {
                    tot = tot + query1.getDouble("credit_amount");
                }
                query1.close();
                if (query.getDouble("debit_amount") != tot) {
                    System.out.println("Tr. No. " + query.getString("tr_no"));
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet query = dbm.query("SELECT * from account_payment_creditside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_payment_debitside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double tot = 0;
                while (query1.next()) {
                    tot = tot + query1.getDouble("debit_amount");
                }
                query1.close();
                if (query.getDouble("credit_amount") != tot) {
                    System.out.println("Tr. No. " + query.getString("tr_no"));
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet query = dbm.query("SELECT * from account_journal_main");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_journal_debitside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double totd = 0;
                while (query1.next()) {
                    totd = totd + query1.getDouble("debit_amount");
                }
                query1.close();
                ResultSet query2 = dbm.query("SELECT * from account_journal_creditside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double totc = 0;
                while (query2.next()) {
                    totc = totc + query1.getDouble("credit_amount");
                }
                query2.close();
                if (totd != totc) {
                    System.out.println("Tr. No. " + query.getString("tr_no"));
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void move_arbour() {
        try {
            int tran_no;
            ResultSet query = dbm.query("SELECT * from `account_reciept_debitside_2013-14`");
            while (query.next()) {
                tran_no = query.getInt("tr_no");
                dbm.insert("INSERT `account_reciept_creditside_2013-14` SELECT * FROM `account_reciept_creditside_all` where `tr_no` LIKE '" + tran_no + "' ");
                dbm.insert("DELETE FROM `account_reciept_creditside_all` where `tr_no` LIKE '" + tran_no + "' ");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clean_op_bal() {
        try {
            double bald=0;
            double balc=0;
            ResultSet query = dbm.query("SELECT * FROM `acc_trail_balance` WHERE `code` < 50000");
            while(query.next()){
                bald=0;
                balc=0;
                if(query.getDouble("op_bal")>=0){
                    bald=query.getDouble("op_bal");
                }
                else{
                    balc=-query.getDouble("op_bal");
                }
                dbm.insert("INSERT INTO 2014_balances(account_code,op_bal_d,op_bal_c,clo_bal) VALUES('"+query.getInt("code")+"','"+bald+"','"+balc+"',0)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
