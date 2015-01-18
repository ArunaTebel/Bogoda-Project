
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ACC_Database_Modify {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public void create_new_tables() {
        try {
            dbm.insert("CREATE TABLE `account_journal` ("
                    + "`tr_no` int(11) NOT NULL,"
                    + "`debit_credit` varchar(20) NOT NULL, "
                    + "`ref_no` varchar(60) NOT NULL, "
                    + "`date` date NOT NULL, "
                    + "`pay_type` varchar(10) NOT NULL, "
                    + "`description_long` varchar(500) DEFAULT NULL, "
                    + "`bank_id` int(11) DEFAULT NULL, "
                    + "`bank_name` varchar(80) DEFAULT NULL, "
                    + "`branch_id` int(11) DEFAULT NULL, "
                    + "`branch_name` varchar(80) DEFAULT NULL, "
                    + "`cheque_no` varchar(100) DEFAULT NULL, "
                    + "`cheque_date` date DEFAULT NULL, "
                    + "`account_id` int(11) NOT NULL, "
                    + "`account_name` varchar(100) NOT NULL, "
                    + "`description` varchar(100) NOT NULL, "
                    + "`amount` double NOT NULL, "
                    + " FOREIGN KEY (`account_id`) REFERENCES `account_names`(`account_id`)) ");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dbm.insert("CREATE TABLE `account_payment`("
                    + " `tr_no` int(11) NOT NULL,"
                    + "`debit_credit` varchar(20) NOT NULL, "
                    + " `ref_no` varchar(50) DEFAULT NULL,"
                    + " `payment_no` varchar(50) DEFAULT NULL,"
                    + " `date` date NOT NULL,"
                    + " `pay_type` varchar(20) NOT NULL,"
                    + " `description_long` varchar(500) NOT NULL,"
                    + " `bank_id` int(11) DEFAULT NULL,"
                    + " `bank_name` varchar(80) DEFAULT NULL,"
                    + " `branch_id` int(11) DEFAULT NULL,"
                    + " `branch_name` varchar(100) DEFAULT NULL,"
                    + " `cheque_no` varchar(100) DEFAULT NULL,"
                    + " `cheque_date` date DEFAULT NULL,"
                    + " `account_id` int(11) NOT NULL,"
                    + " `account_name` varchar(100) NOT NULL,"
                    + " `description` varchar(300) NOT NULL,"
                    + " `amount` double NOT NULL,"
                    + "  FOREIGN KEY (`account_id`) REFERENCES `account_names`(`account_id`)"
                    + ") ");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dbm.insert("CREATE TABLE `account_reciept` ("
                    + " `tr_no` int(11) NOT NULL,"
                    + "`debit_credit` varchar(20) NOT NULL, "
                    + " `reciept_no` varchar(100) DEFAULT NULL,"
                    + " `ref_no` varchar(100) DEFAULT NULL,"
                    + " `date` date NOT NULL,"
                    + " `pay_type` varchar(50) NOT NULL,"
                    + " `description_long` varchar(500) NOT NULL,"
                    + " `bank_id` int(11) DEFAULT NULL,"
                    + " `bank_name` varchar(100) DEFAULT NULL,"
                    + " `branch_id` int(11) DEFAULT NULL,"
                    + " `branch_name` varchar(100) DEFAULT NULL,"
                    + " `cheque_no` varchar(100) DEFAULT NULL,"
                    + " `cheque_date` date DEFAULT NULL,"
                    + " `account_id` int(11) NOT NULL,"
                    + " `account_name` varchar(100) NOT NULL,"
                    + " `description` varchar(300) NOT NULL,"
                    + " `amount` double NOT NULL,"
                    + "  FOREIGN KEY (`account_id`) REFERENCES `account_names`(`account_id`)"
                    + ")");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void copy_tables() {

        try {
            dbm.insert("CREATE TABLE `account_journal_2008-09` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2009-10` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2010-11` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2011-12` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2012-13` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2013-14` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2015-16` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2016-17` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2017-18` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2018-19` LIKE account_journal");
            dbm.insert("CREATE TABLE `account_journal_2019-20` LIKE account_journal");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dbm.insert("CREATE TABLE `account_reciept_2008-09` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2009-10` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2010-11` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2011-12` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2012-13` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2013-14` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2015-16` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2016-17` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2017-18` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2018-19` LIKE account_reciept");
            dbm.insert("CREATE TABLE `account_reciept_2019-20` LIKE account_reciept");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dbm.insert("CREATE TABLE `account_payment_2008-09` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2009-10` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2010-11` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2011-12` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2012-13` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2013-14` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2015-16` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2016-17` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2017-18` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2018-19` LIKE account_payment");
            dbm.insert("CREATE TABLE `account_payment_2019-20` LIKE account_payment");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// This is for Receipts

    public void copy_entries(String fromTable, String toTable, String fromSupportTable) {
        try {
            ResultSet query = dbm.query("SELECT * FROM `" + fromTable + "` ");

            while (query.next()) {
                if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                    dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,reciept_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("reciept_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getInt("debit_account_id") + "','" + query.getString("debit_account_name") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "')");
                } else {
                    dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,reciept_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("reciept_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query.getInt("debit_account_id") + "','" + query.getString("debit_account_name") + "','" + query.getString("debit_description") + "','" + query.getDouble("debit_amount") + "')");
                }

                ResultSet query2 = dbm.query("SELECT * FROM `" + fromSupportTable + "` WHERE tr_no LIKE '" + query.getInt("tr_no") + "' ");

                while (query2.next()) {
                    if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,reciept_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("reciept_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query2.getInt("credit_account_id") + "','" + query2.getString("credit_account_name") + "','" + query2.getString("credit_description") + "','" + query2.getDouble("credit_amount") + "')");
                    } else {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,reciept_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("reciept_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query2.getInt("credit_account_id") + "','" + query2.getString("credit_account_name") + "','" + query2.getString("credit_description") + "','" + query2.getDouble("credit_amount") + "')");
                    }
                }
                query2.close();
            }
            query.close();

        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 // This is for Payments
    public void copy_entries_payments(String fromTable, String toTable, String fromSupportTable) {
        try {
            ResultSet query = dbm.query("SELECT * FROM `" + fromTable + "` ");

            while (query.next()) {
                if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                    dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,payment_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("payment_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getInt("credit_account_id") + "','" + query.getString("credit_account_name") + "','" + query.getString("credit_description") + "','" + query.getDouble("credit_amount") + "')");
                } else {
                    dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,payment_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("payment_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query.getInt("credit_account_id") + "','" + query.getString("credit_account_name") + "','" + query.getString("credit_description") + "','" + query.getDouble("credit_amount") + "')");
                }

                ResultSet query2 = dbm.query("SELECT * FROM `" + fromSupportTable + "` WHERE tr_no LIKE '" + query.getInt("tr_no") + "' ");

                while (query2.next()) {
                    if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,payment_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("payment_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query2.getInt("debit_account_id") + "','" + query2.getString("debit_account_name") + "','" + query2.getString("debit_description") + "','" + query2.getDouble("debit_amount") + "')");
                    } else {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,payment_no,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("payment_no") + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query2.getInt("debit_account_id") + "','" + query2.getString("debit_account_name") + "','" + query2.getString("debit_description") + "','" + query2.getDouble("debit_amount") + "')");
                    }
                }
                query2.close();
            }
            query.close();

        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  // This is for Journals
    public void copy_entries_journals(String fromTable, String toTable, String fromSupportTable1, String fromSupportTable2) {
        try {
            ResultSet query = dbm.query("SELECT * FROM `" + fromTable + "` ");

            while (query.next()) {

                ResultSet query1 = dbm.query("SELECT * FROM `" + fromSupportTable2 + "` WHERE tr_no LIKE '" + query.getInt("tr_no") + "' ");

                while (query1.next()) {
                    if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query1.getInt("credit_account_id") + "','" + query1.getString("credit_account_name") + "','" + query1.getString("credit_description") + "','" + query1.getDouble("credit_amount") + "')");
                    } else {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "credit" + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query1.getInt("credit_account_id") + "','" + query1.getString("credit_account_name") + "','" + query1.getString("credit_description") + "','" + query1.getDouble("credit_amount") + "')");
                    }
                }
                query1.close();

                ResultSet query2 = dbm.query("SELECT * FROM `" + fromSupportTable1 + "` WHERE tr_no LIKE '" + query.getInt("tr_no") + "' ");

                while (query2.next()) {
                    if (query.getString("cheque_date") == null || query.getString("cheque_date") == "NULL" || query.getString("cheque_date") == "null") {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query2.getInt("debit_account_id") + "','" + query2.getString("debit_account_name") + "','" + query2.getString("debit_description") + "','" + query2.getDouble("debit_amount") + "')");
                    } else {
                        dbm.insert("INSERT INTO `" + toTable + "`(tr_no,debit_credit,ref_no,date,pay_type,description_long,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,account_id,account_name,description,amount) VALUES('" + query.getInt("tr_no") + "','" + "debit" + "','" + query.getString("ref_no") + "','" + query.getDate("date") + "','" + query.getString("pay_type") + "','" + query.getString("description") + "','" + query.getInt("bank_id") + "','" + query.getString("bank_name") + "','" + query.getInt("branch_id") + "','" + query.getString("branch_name") + "','" + query.getString("cheque_no") + "','" + query.getDate("cheque_date") + "','" + query2.getInt("debit_account_id") + "','" + query2.getString("debit_account_name") + "','" + query2.getString("debit_description") + "','" + query2.getDouble("debit_amount") + "')");
                    }
                }
                query2.close();
            }
            query.close();

        } catch (SQLException ex) {
            Logger.getLogger(ACC_Database_Modify.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
