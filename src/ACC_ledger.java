
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ACC_ledger {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public void fill_account_code(int account_code) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            DatabaseManager dbm =DatabaseManager.getDbCon();
            String dt;
            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;
            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    //// we 
    public void fill_account_code_filtered_date(int account_code, String date1, String date2) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2);*/
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside WHERE credit_account_id LIKE '" + account_code + "' AND date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside WHERE debit_account_id LIKE '" + account_code + "' AND date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    /* Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2);*/
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside_all WHERE credit_account_id LIKE '" + account_code + "' AND date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all WHERE debit_account_id LIKE '" + account_code + "' AND date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside_all", "tr_no", query.getInt("tr_no"), "date");

                    /* Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + account_code + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void fill_all() {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside_all");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside_all", "tr_no", query.getInt("tr_no"), "date");

                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void fill_all_filtered_date(String date1, String date2) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2);*/
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    /* Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;

            try {
                dbm.insert("Truncate account_ledger_temp");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2);*/
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-J" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside_all", "tr_no", query.getInt("tr_no"), "date");

                    /*  Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + dt + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside_all WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    //      dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-P" + "','" + query.getString("date") + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {

                    // dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");
                    dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("debit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + query.getString("date") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "',0)");

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside_all", "tr_no", query.getInt("tr_no"), "date");

                    /* Date dtD = java.sql.Date.valueOf(dt);
                     Date date1D = java.sql.Date.valueOf(date1);
                     Date date2D = java.sql.Date.valueOf(date2); */
                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date2D = null;
                    try {
                        date2D = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((dtD.before(date2D) && dtD.after(date1D)) || dtD.equals(date1D) || dtD.equals(date2D)) {
                        dbm.insert("INSERT INTO account_ledger_temp(account_code,tr_no,date,description,debit,credit) VALUES('" + query.getInt("credit_account_id") + "','" + query.getInt("tr_no") + "-R" + "','" + dt + "','" + query.getString("credit_description") + "',0,'" + query.getDouble("credit_amount") + "')");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public double opening_balance_calc(int account_code, String date1) {
        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;
            int a;
            double op_bal = 0;
            double temp = 0;

            int main_ac_id = Integer.parseInt(dbm.checknReturnData("account_names", "account_id", account_code, "main_account_code"));

            // op_bal = Double.parseDouble(dbm.checknReturnData("account_names","account_id",account_code,"opening_balance"));
            String year = date1.substring(0, 4);
            String month = date1.substring(5, 7);

            if (Integer.parseInt(month) < 4) {
                year = "" + (Integer.parseInt(year) - 1);
            }
            
            String table_name = year + "_balances";
            
            //table_name="2014_balances";

            // op_bal=Double.parseDouble(dbm.checknReturnData(table_name,"account_code",account_code,"op_bal"));
            double opd = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_code, "op_bal_d"));
            double opc = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_code, "op_bal_c"));

            /*  if(main_ac_id==1||main_ac_id==2||main_ac_id==7||main_ac_id==8){
            
             a=1;
             op_bal=op_bal;
            
             }
        
             else{
             a=-1;
             op_bal=-op_bal;
             }*/
            if (opc == 0 && opd != 0) {
                op_bal = opd;
            } else if (opd == 0 && opc != 0) {
                op_bal = -opc;
            } else {
                op_bal = opd - opc;
            }

            temp = op_bal;

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {

                        temp = temp - query.getDouble("credit_amount");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside WHERE credit_account_id LIKE '" + account_code + "' AND date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp - query.getDouble("credit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside WHERE debit_account_id LIKE '" + account_code + "' AND date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp + query.getDouble("debit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp - query.getDouble("credit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            return temp;

        } else {

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;
            int a;
            double op_bal = 0;
            double temp = 0;

            int main_ac_id = Integer.parseInt(dbm.checknReturnData("account_names", "account_id", account_code, "main_account_code"));

            // op_bal = Double.parseDouble(dbm.checknReturnData("account_names","account_id",account_code,"opening_balance"));
            String year = date1.substring(0, 4);
            String month = date1.substring(5, 7);

            if (Integer.parseInt(month) < 4) {
                year = "" + (Integer.parseInt(year) - 1);
            }
            String table_name = year + "_balances";
            String year_begining = year + "-04-01";
            java.util.Date dtYear = null;
            try {
                dtYear = new SimpleDateFormat("yyyy-MM-dd").parse(year_begining);
            } catch (ParseException ex) {
                Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
            }

            // op_bal=Double.parseDouble(dbm.checknReturnData(table_name,"account_code",account_code,"op_bal"));
            double opd = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_code, "op_bal_d"));
            double opc = Double.parseDouble(dbm.checknReturnData(table_name, "account_code", account_code, "op_bal_c"));

            /*  if(main_ac_id==1||main_ac_id==2||main_ac_id==7||main_ac_id==8){
            
             a=1;
             op_bal=op_bal;
            
             }
        
             else{
             a=-1;
             op_bal=-op_bal;
             }*/
            if (opc == 0 && opd != 0) {
                op_bal = opd;
            } else if (opd == 0 && opc != 0) {
                op_bal = -opc;
            } else {
                op_bal = opd - opc;
            }

            temp = op_bal;

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D) && dtD.after(dtYear)) {//dddf

                        temp = temp - query.getDouble("credit_amount");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main_all", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D) && dtD.after(dtYear)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside_all WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside_all", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D) && dtD.after(dtYear)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside_all WHERE credit_account_id LIKE '" + account_code + "' AND date > '" + year_begining + "' AND date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp - query.getDouble("credit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all WHERE debit_account_id LIKE '" + account_code + "' AND date > '" + year_begining + "' AND  date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp + query.getDouble("debit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {///

                    dt = dbm.checknReturnData("account_reciept_debitside_all", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D) && dtD.after(dtYear)) {
                        temp = temp - query.getDouble("credit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            return temp;

        }

    }
    
    
     public double opening_balance_calc_without_op(int account_code, String date1) {
        
        

            DatabaseManager dbm = DatabaseManager.getDbCon();
            String dt;
            int a;
            
            double temp = 0;

            int main_ac_id = Integer.parseInt(dbm.checknReturnData("account_names", "account_id", account_code, "main_account_code"));

            // op_bal = Double.parseDouble(dbm.checknReturnData("account_names","account_id",account_code,"opening_balance"));
            String year = date1.substring(0, 4);
            String month = date1.substring(5, 7);

            if (Integer.parseInt(month) < 4) {
                year = "" + (Integer.parseInt(year) - 1);
            }
            
            String table_name = year + "_balances";
            
            //table_name="2014_balances";

            // op_bal=Double.parseDouble(dbm.checknReturnData(table_name,"account_code",account_code,"op_bal"));
            
            /*  if(main_ac_id==1||main_ac_id==2||main_ac_id==7||main_ac_id==8){
            
             a=1;
             op_bal=op_bal;
            
             }
        
             else{
             a=-1;
             op_bal=-op_bal;
             }*/
           

            // Search and fill in journals
            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (Exception e) {
                       // Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(e);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (Exception ex) {
                       // Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex);
                    }

                    if (dtD.before(date1D)) {

                        temp = temp - query.getDouble("credit_amount");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_journal_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_journal_main", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Payments
            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_debitside WHERE debit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_payment_creditside", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp + query.getDouble("debit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_payment_creditside WHERE credit_account_id LIKE '" + account_code + "' AND date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp - query.getDouble("credit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Search and fill in Receipts
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside WHERE debit_account_id LIKE '" + account_code + "' AND date < '" + date1 + "'");
                while (query.next()) {

                    temp = temp + query.getDouble("debit_amount");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside WHERE credit_account_id LIKE '" + account_code + "'");
                while (query.next()) {

                    dt = dbm.checknReturnData("account_reciept_debitside", "tr_no", query.getInt("tr_no"), "date");

                    java.util.Date dtD = null;
                    try {
                        dtD = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.util.Date date1D = null;
                    try {
                        date1D = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(ACC_ledger.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (dtD.before(date1D)) {
                        temp = temp - query.getDouble("credit_amount");
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Acc_Update_And_Additional_Data.class.getName()).log(Level.SEVERE, null, ex);
            }

            return temp;

       

    }
    
    
    

}
