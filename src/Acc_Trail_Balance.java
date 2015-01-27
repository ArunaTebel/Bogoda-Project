
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

    public double return_debit_or_credit_sum_of_a_table(String tabel, String account_id, String debit_credit, java.util.Date date) {
        double amount = 0;
        try {
            ResultSet query = dbm.query("SELECT SUM(`amount`) as sum FROM `" + tabel + "` WHERE `debit_credit` LIKE '" + debit_credit + "' AND `account_id` LIKE '" + account_id + "' AND `date` < '" + date + "' ");
            while (query.next()) {
                amount = query.getDouble("sum");
            }
            query.close();
        } catch (SQLException ex) {
            amount = 0;
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
        amount = Math.round(amount * 100.0) / 100.0;
        return amount;
    }

    public double return_total_debit_or_credit_sum(String account_id, String debit_credit, java.util.Date date) {
        return (return_debit_or_credit_sum_of_a_table("account_reciept", account_id, debit_credit, date) + return_debit_or_credit_sum_of_a_table("account_payment", account_id, debit_credit, date) + return_debit_or_credit_sum_of_a_table("account_journal", account_id, debit_credit, date));
    }

    public double get_op_bal(String account_id, java.util.Date date) {

        double op_bal = 0;

        String date_string = date.toString();
        String year = date_string.substring(0, 4);
        String month = date_string.substring(5, 7);

        if (Integer.parseInt(month) < 4) {
            year = "" + (Integer.parseInt(year) - 1);
        }

        String table_name = year + "_balances";

        double opd;
        try {
            opd = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_id, "op_bal_d"));
        } catch (Exception e) {
            opd = 0;
        }
        double opc;
        try {
            opc = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_id, "op_bal_c"));
        } catch (Exception e) {
            opc = 0;
        }

        op_bal = opd - opc;

        return op_bal;
    }

    public void create_table_from_new_tables(java.util.Date date) {

        double totDebit = 0;
        double totCredit = 0;
        double returnAmount = 0;
        try {
            try {
                dbm.insert("Truncate acc_trail_balance");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {
                totDebit = return_total_debit_or_credit_sum(query.getString("account_id"), "debit", date);
                totCredit = return_total_debit_or_credit_sum(query.getString("account_id"), "credit", date);

                returnAmount = totDebit - totCredit + get_op_bal(query.getString("account_id"), date);

                dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + returnAmount + "')");

            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void create_table_from_new_tables_without_op_bal(java.util.Date date) {

        double totDebit = 0;
        double totCredit = 0;
        double returnAmount = 0;
        try {
            try {
                dbm.insert("Truncate acc_trail_balance");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet query = dbm.query("SELECT * FROM account_names");
            while (query.next()) {
                totDebit = return_total_debit_or_credit_sum(query.getString("account_id"), "debit", date);
                totCredit = return_total_debit_or_credit_sum(query.getString("account_id"), "credit", date);

                returnAmount = totDebit - totCredit;

                dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + returnAmount + "')");

            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public double op_balance_of_single_account_from_new_tables(String account_id, java.util.Date date) {

        double totDebit = 0;
        double totCredit = 0;
        double returnAmount = 0;

        totDebit = return_total_debit_or_credit_sum(account_id, "debit", date);
        totCredit = return_total_debit_or_credit_sum(account_id, "credit", date);

        returnAmount = totDebit - totCredit + get_op_bal(account_id, date);
        
        return returnAmount;

    }

    public void create_table_cop(String date1, String date2, int startCode, int endCode) {

        try {
            try {
                dbm.insert("Truncate acc_trail_balance");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id >= '" + startCode + "' and account_id < '" + endCode + "' ");
            while (query.next()) {

                double bal = ledg.balance_cal_for_cop(query.getInt("account_id"), date1, date2);
                dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + bal + "')");

            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void create_table_cop_new_databases(String date1, String date2, int startCode, int endCode) {

        try {
            try {
                dbm.insert("Truncate acc_trail_balance");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id >= '" + startCode + "' and account_id < '" + endCode + "' ");
            while (query.next()) {

                double bal = ledg.tot_balance_between_two_dates(query.getString("account_id"), date1, date2);
                dbm.insert("INSERT INTO acc_trail_balance(code,op_bal) VALUES('" + Integer.parseInt(query.getString("account_id")) + "','" + bal + "')");

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
                totd = Math.round(totd * 100.0) / 100.0;
                ResultSet query2 = dbm.query("SELECT * from account_journal_creditside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double totc = 0;
                while (query2.next()) {
                    totc = totc + query2.getDouble("credit_amount");
                }
                query2.close();
                totc = Math.round(totc * 100.0) / 100.0;
                if (totd != totc) {
                    System.out.println("Tr. No. " + query.getString("tr_no") + "totd" + totd + "  totc" + totc);
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void check_n_enter_to_the_tables(javax.swing.JTable reciepts, javax.swing.JTable payments, javax.swing.JTable journals) {

        DefaultTableModel jrModel = (DefaultTableModel) journals.getModel();
        DefaultTableModel reModel = (DefaultTableModel) reciepts.getModel();
        DefaultTableModel paModel = (DefaultTableModel) payments.getModel();

        int r = 0, p = 0, j = 0;

        // reciept debit side
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
                    reModel.setRowCount(r + 1);
                    //  System.out.println("Tr. No. " + query.getString("tr_no"));
                    reciepts.setValueAt(query.getString("tr_no"), r, 0);
                    r++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        // check in the reciept creditside
        try {
            ResultSet query = dbm.query("SELECT * from account_reciept_creditside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_reciept_debitside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                int count = 0;
                while (query1.next()) {
                    count++;
                }
                query1.close();
                if (count == 0) {
                    reModel.setRowCount(r + 1);
                    //  System.out.println("Tr. No. " + query.getString("tr_no"));
                    reciepts.setValueAt(query.getString("tr_no"), r, 0);
                    r++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        // payment credit side
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
                    paModel.setRowCount(p + 1);
                    // System.out.println("Tr. No. " + query.getString("tr_no"));
                    payments.setValueAt(query.getString("tr_no"), p, 0);
                    p++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Search in payment debitside
        try {
            ResultSet query = dbm.query("SELECT * from account_payment_debitside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_payment_creditside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                int count = 0;
                while (query1.next()) {
                    count++;
                }
                query1.close();
                if (count == 0) {
                    paModel.setRowCount(p + 1);
                    // System.out.println("Tr. No. " + query.getString("tr_no"));
                    payments.setValueAt(query.getString("tr_no"), p, 0);
                    p++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        // journal main
        try {
            ResultSet query = dbm.query("SELECT * from account_journal_main");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_journal_debitside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double totd = 0;
                while (query1.next()) {
                    totd = totd + query1.getDouble("debit_amount");
                }
                query1.close();

                totd = Math.round(totd * 100.0) / 100.0;

                ResultSet query2 = dbm.query("SELECT * from account_journal_creditside WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                double totc = 0;
                while (query2.next()) {
                    totc = totc + query2.getDouble("credit_amount");
                }
                query2.close();

                totc = Math.round(totc * 100.0) / 100.0;

                if (totd != totc) {
                    jrModel.setRowCount(j + 1);
                    //  System.out.println("Tr. No. " + query.getString("tr_no")+"totd"+totd +"  totc"+totc);
                    journals.setValueAt(query.getString("tr_no"), j, 0);
                    j++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  Search in journal debitside
        try {
            ResultSet query = dbm.query("SELECT * from account_journal_debitside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_journal_main WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                int count = 0;
                while (query1.next()) {
                    count++;
                }
                query1.close();
                if (count == 0) {
                    jrModel.setRowCount(j + 1);
                    // System.out.println("Tr. No. " + query.getString("tr_no"));
                    journals.setValueAt(query.getString("tr_no"), j, 0);
                    j++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Search in journal creditside
        try {
            ResultSet query = dbm.query("SELECT * from account_journal_creditside");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * from account_journal_main WHERE tr_no LIKE '" + query.getString("tr_no") + "'");
                int count = 0;
                while (query1.next()) {
                    count++;
                }
                query1.close();
                if (count == 0) {
                    jrModel.setRowCount(j + 1);
                    // System.out.println("Tr. No. " + query.getString("tr_no"));
                    journals.setValueAt(query.getString("tr_no"), j, 0);
                    j++;
                }
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

        MessageBox msg = new MessageBox();
        if (r == 0 && p == 0 && j == 0) {
            msg.showMessage("There are no Errors", "No Errors", "info");
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
            double bald = 0;
            double balc = 0;
            ResultSet query = dbm.query("SELECT * FROM `acc_trail_balance` WHERE `code` < 50000");
            while (query.next()) {
                bald = 0;
                balc = 0;
                if (query.getDouble("op_bal") >= 0) {
                    bald = query.getDouble("op_bal");
                } else {
                    balc = -query.getDouble("op_bal");
                }
                dbm.insert("INSERT INTO 2014_balances(account_code,op_bal_d,op_bal_c,clo_bal) VALUES('" + query.getInt("code") + "','" + bald + "','" + balc + "',0)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Trail_Balance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
