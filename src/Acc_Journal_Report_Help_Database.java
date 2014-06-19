
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acc_Journal_Report_Help_Database {

    DatabaseManager dbm = new DatabaseManager();

    int tr_no;
    int dr_count = 0;
    int cr_count = 0;
    int count = 0;

    public void create_database() {
        try {
            dbm.insert("Truncate table account_journal_report");
        } catch (SQLException ex) {
            Logger.getLogger(Acc_Journal_Report_Help_Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_main ");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query1.next()) {
                    dr_count++;
                }

                ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query2.next()) {
                    cr_count++;
                }
                
                System.out.println("Dr"+dr_count);
                System.out.println("Cr"+cr_count);
                if (dr_count >= cr_count) {

                    count = 0;

                    query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                    query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {

                        query2.next();
                        count++;
                        if (count <= cr_count) {

                            try {
                                dbm.insert("INSERT INTO account_journal_report(tr_no,ref_no,date,pay_type,bank_id,branch_id,cheque_no,cheque_date,debit_account_id,debit_account_name,debit_description,debit_amount,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + query.getString("tr_no") + "','" + query.getString("ref_no") + "','" + query.getString("date") + "','" + query.getString("pay_type") + "','" + query.getInt("bank_id") + "','" + query.getInt("branch_id") + "','" + query.getString("cheque_no") + "','" + query.getString("cheque_date") + "','" + query1.getString("debit_account_id") + "','" + query1.getString("debit_account_name") + "','" + query1.getString("debit_description") + "','" + query1.getString("debit_amount") + "','" + query2.getString("credit_account_id") + "','" + query2.getString("credit_account_name") + "','" + query2.getString("credit_description") + "','" + query2.getString("credit_amount") + "')");
                            } catch (SQLException ex) {
                                MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                            }

                        } else {

                            try {
                                dbm.insert("INSERT INTO account_journal_report(tr_no,ref_no,date,pay_type,bank_id,branch_id,cheque_no,cheque_date,debit_account_id,debit_account_name,debit_description,debit_amount,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + query.getString("tr_no") + "','" + query.getString("ref_no") + "','" + query.getString("date") + "','" + query.getString("pay_type") + "','" + query.getInt("bank_id") + "','" + query.getInt("branch_id") + "','" + query.getString("cheque_no") + "','" + query.getString("cheque_date") + "','" + query1.getString("debit_account_id") + "','" + query1.getString("debit_account_name") + "','" + query1.getString("debit_description") + "','" + query1.getString("debit_amount") + "','" + 0 + "','" + null + "','" + null + "','" + 0 + "')");
                            } catch (SQLException ex) {
                                MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                            }

                        }

                    }

                    dr_count = 0;
                    cr_count = 0;
                    count = 0;

                } else {

                    count = 0;

                    query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                    query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query2.next()) {

                        query1.next();
                        count++;
                        if (count <= dr_count) {

                            try {
                                dbm.insert("INSERT INTO account_journal_report(tr_no,ref_no,date,pay_type,bank_id,branch_id,cheque_no,cheque_date,debit_account_id,debit_account_name,debit_description,debit_amount,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + query.getString("tr_no") + "','" + query.getString("ref_no") + "','" + query.getString("date") + "','" + query.getString("pay_type") + "','" + query.getInt("bank_id") + "','" + query.getInt("branch_id") + "','" + query.getString("cheque_no") + "','" + query.getString("cheque_date") + "','" + query1.getString("debit_account_id") + "','" + query1.getString("debit_account_name") + "','" + query1.getString("debit_description") + "','" + query1.getString("debit_amount") + "','" + query2.getString("credit_account_id") + "','" + query2.getString("credit_account_name") + "','" + query2.getString("credit_description") + "','" + query2.getString("credit_amount") + "')");
                            } catch (SQLException ex) {
                                MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                            }

                        } else {

                            try {
                                dbm.insert("INSERT INTO account_journal_report(tr_no,ref_no,date,pay_type,bank_id,branch_id,cheque_no,cheque_date,debit_account_id,debit_account_name,debit_description,debit_amount,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + query.getString("tr_no") + "','" + query.getString("ref_no") + "','" + query.getString("date") + "','" + query.getString("pay_type") + "','" + query.getInt("bank_id") + "','" + query.getInt("branch_id") + "','" + query.getString("cheque_no") + "','" + query.getString("cheque_date") + "','" + 0 + "','" + null + "','" + null + "','" + 0 + "','" + query2.getString("credit_account_id") + "','" + query2.getString("credit_account_name") + "','" + query2.getString("credit_description") + "','" + query2.getString("credit_amount") + "')");
                            } catch (SQLException ex) {
                                MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                            }

                        }

                    }

                    dr_count = 0;
                    cr_count = 0;
                    count = 0;
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

}
