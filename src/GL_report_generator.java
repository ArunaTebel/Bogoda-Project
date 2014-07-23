
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
/**
 *
 * @author Pramo
 */
public class GL_report_generator {

    DatabaseManager dbm = new DatabaseManager();
    Date_Handler date_handler = new Date_Handler();

    public GL_report_generator() {

    }

    public double[] get_day_totals(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {
        double[] total = new double[33];
        int j = 0;
        while (j < 33) {
            total[j] = 0;
            j++;

        }
        j = 0;
        int i = 0;

        Date Sdate = java.sql.Date.valueOf(year + "-" + month + "-" + "01");

        Date Ldate = java.sql.Date.valueOf(year + "-" + month + "-" + "31");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "green_leaf_transactions" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "tr_date" + " BETWEEN'" + Sdate + "' AND '" + Ldate + "'");

            while (query.next()) {

                int dates = Integer.parseInt(date_handler.get_day(query.getDate("tr_date")));
                total[dates] += query.getDouble("net_qty");
                total[32] += (query.getDouble("transport") * query.getDouble("net_qty"));
                //  System.out.println(total[32]+"-----------------------------------");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return total;
    }

    public void daily_transaction_calc(String year, String month) throws SQLException {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        //int  k =0 ;
        int j = 0;
        String task = "1";
        //   String done = "DONE";
        int size = getColumnsize("suppliers", "sup_id");
        Task_manager.dailyTprog.setMaximum(size);

        ResultSet query = dbCon.query("SELECT * FROM suppliers");

        while (query.next()) {

            int sup = query.getInt("sup_id");
            // System.out.println(sup);
            String name = query.getString("sup_name");
            // System.out.println(sup);
            int i = 0;
            double[] day_totals = new double[33];

            double total = 0;
            day_totals = get_day_totals(sup, year, month);
            while (i < 32) {
                total += day_totals[i];

                i++;
            }
            try {

                dbCon.insert("INSERT INTO daily_transactions_current(entry,year,month,sup_id,sup_name,day_1,day_2,day_3,day_4,day_5,day_6,day_7,day_8,day_9,day_10,day_11,day_12,day_13,day_14,day_15,day_16,day_17,day_18,day_19,day_20,day_21,day_22,day_23,day_24,day_25,day_26,day_27,day_28,day_29,day_30,day_31,Total,trans) VALUES('" + year + month + sup + "','" + year + "','" + month + "','" + sup + "','" + name + "','" + day_totals[1] + "','" + day_totals[2] + "','" + day_totals[3] + "','" + day_totals[4] + "','" + day_totals[5] + "','" + day_totals[6] + "','" + day_totals[7] + "','" + day_totals[8] + "','" + day_totals[9] + "','" + day_totals[10] + "','" + day_totals[11] + "','" + day_totals[12] + "','" + day_totals[13] + "','" + day_totals[14] + "','" + day_totals[15] + "','" + day_totals[16] + "','" + day_totals[17] + "','" + day_totals[18] + "','" + day_totals[19] + "','" + day_totals[20] + "','" + day_totals[21] + "','" + day_totals[22] + "','" + day_totals[23] + "','" + day_totals[24] + "','" + day_totals[25] + "','" + day_totals[26] + "','" + day_totals[27] + "','" + day_totals[28] + "','" + day_totals[29] + "','" + day_totals[30] + "','" + day_totals[31] + "','" + total + "','" + day_totals[32] + "')");

                //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                //  k++;
            } catch (SQLException ex) {
                try {

                    dbCon.insert("UPDATE  daily_transactions_current  SET year ='" + year + "',month ='" + month + "',sup_id ='" + sup + "',sup_name ='" + name + "',day_1 ='" + day_totals[1] + "',day_2 ='" + day_totals[2] + "',day_3 ='" + day_totals[3] + "',day_4 ='" + day_totals[4] + "',day_5 ='" + day_totals[5] + "',day_6 ='" + day_totals[6] + "',day_7 ='" + day_totals[7] + "',day_8 ='" + day_totals[8] + "',day_9 ='" + day_totals[9] + "',day_10 ='" + day_totals[10] + "',day_11 ='" + day_totals[11] + "',day_12 ='" + day_totals[12] + "',day_13 ='" + day_totals[13] + "',day_14 ='" + day_totals[14] + "',day_15 ='" + day_totals[15] + "',day_16 ='" + day_totals[16] + "',day_17 ='" + day_totals[17] + "',day_18 ='" + day_totals[18] + "',day_19 ='" + day_totals[19] + "',day_20 ='" + day_totals[20] + "',day_21 ='" + day_totals[21] + "',day_22 ='" + day_totals[22] + "',day_23 ='" + day_totals[23] + "',day_24 ='" + day_totals[24] + "',day_25 ='" + day_totals[25] + "',day_26 ='" + day_totals[26] + "',day_27 ='" + day_totals[27] + "',day_28 ='" + day_totals[28] + "',day_29 ='" + day_totals[29] + "',day_30 ='" + day_totals[30] + "',day_31 ='" + day_totals[31] + "',Total ='" + total + "',trans ='" + day_totals[32] + "' WHERE entry='" + year + month + sup + "'");

                } catch (SQLException exe) {
                    MessageBox.showMessage(exe.getMessage(), "SQL ERROR", "error");

                }

            }

            j++;
            Task_manager.dailyTprog.setValue(j);
        }
        //update_taskmanager(year, month, task);

    }

    public double[] get_week_totals(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {
        double[] cash_total = new double[32];
        double[] other_total = new double[32];
        double[] output = new double[11];
        double[] cash_new_month = new double[32];
        double[] other_new_month = new double[32];
        double[] cat_totals = new double[7];

        double[] a = new double[10];

        int j = 0;
        while (j < 32) {
            cash_total[j] = 0;
            other_total[j] = 0;
            cash_new_month[j] = 0;
            other_new_month[j] = 0;

            j++;
        }
        //j = 0;
        int i = 0;
        while (i < 11) {

            if (i < 7) {
                cat_totals[i] = 0;
            }
            output[i] = 0;

            i++;
        }

        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");
        Date date2 = java.sql.Date.valueOf(year + "-" + month + "-" + "21");
        Date date3 = java.sql.Date.valueOf(year + "-" + date_handler.get_next_month(month) + "-" + "28");
        Date date4;
        if (month.equals("12")) {
            date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year) + 1) + "-" + date_handler.get_next_month(month) + "-" + "07");
        } else {
            date4 = java.sql.Date.valueOf(year + "-" + date_handler.get_next_month(month) + "-" + "07");
        }

        // System.out.println(date1+"----"+date2+"----"+date3+"-----"+date4);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_cash_advance" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "ordered_date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                //System.out.println("query 1");
                int dates = Integer.parseInt(date_handler.get_day(query.getDate("ordered_date")));
                // System.out.println(query.getDouble("amount"));
                cash_total[dates] += query.getDouble("amount");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_other_advances" + " where " + "id" + " = '" + sup_id + " 'AND " + "Date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                ///System.out.println("query 2");
                int dates = Integer.parseInt(date_handler.get_day(query.getDate("Date")));
                double tot = query.getDouble("total_amount");
                other_total[dates] += tot;
                String type = "other";

                type = query.getString("item_type");
                if (type.equals("Tea")) {
                    cat_totals[0] += tot;
                } else if (type.equals("Manure")) {
                    cat_totals[1] += tot;
                } else if (type.equals("Chemicals")) {
                    cat_totals[2] += tot;
                } else if (type.equals("Shop")) {
                    cat_totals[3] += tot;
                } else if (type.equals("Coir Bags")) {
                    cat_totals[4] += tot;
                } else if (type.equals("Spray Tanks")) {
                    cat_totals[5] += tot;
                } else {
                    cat_totals[6] += tot;
                }

                // 
            }

            try {
                dbm.insert("INSERT INTO other_advance_totals(entry,year,month,sup_id,tea,manure,chem,shop,bags,tanks,other) VALUES('" + year + month + sup_id + "','" + year + "','" + month + "','" + sup_id + "','" + cat_totals[0] + "','" + cat_totals[1] + "','" + cat_totals[2] + "','" + cat_totals[3] + "','" + cat_totals[4] + "','" + cat_totals[5] + "','" + cat_totals[6] + "')");
            } catch (Exception e) {
                try {
                    dbm.insert("UPDATE  other_advance_totals SET year='" + year + "',month='" + month + "',sup_id='" + sup_id + "',tea='" + cat_totals[0] + "',manure='" + cat_totals[1] + "',chem='" + cat_totals[2] + "',shop='" + cat_totals[3] + "',bags='" + cat_totals[4] + "',tanks='" + cat_totals[5] + "',other='" + cat_totals[6] + "' WHERE entry = '" + year + month + sup_id + "'");
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());

                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        int p = 8;
        int q = 0;

        while (p < 15) {
            output[0] += cash_total[p];
            output[5] += other_total[p];
            p++;
        }
        while (p < 22) {
            output[1] += cash_total[p];
            output[6] += other_total[p];
            p++;
        }
        while (p < 29) {
            output[2] += cash_total[p];
            output[7] += other_total[p];
            p++;
        }
        while (p < 32) {
            output[3] += cash_total[p];
            output[8] += other_total[p];
            p++;
        }
        while (q < 8) {
            output[3] += cash_total[q];
            output[8] += other_total[q];
            q++;
        }
        output[4] = output[0] + output[1] + output[2] + output[3];
        output[9] = output[5] + output[6] + output[7] + output[8];
        output[10] = output[4] + output[9];

        return output;
    }

//FOR GIVEN MONTH, ABOVE FUNCTION WILL CALCULATE THE DATE RANGE WHEN ADVANCES FOR THE RELEVENT MONTH ARE ISSUED. THEN IT WILL CALCULATE DAILY TOTALS
// THESE DAILY TOTALS WILL BE ADDED ACCORDING TO THEIR WEEKS. OUTPUT STRING CONTAINS 11 VALUES
// 0-3 ----> CASH ADVANCE WEEK TOTALS
// 4 ----> CASH ADVANCE MONTH TOTAL
//5-8 ---->OTHER ADVANCE WEEK TOTALS
// 9-----> CASH ADVACE MONTH TOTAL
// 10-----> GRAND TOTAL
    public void weekly_advance_calc(String year, String month) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        // int  k =0 ;
        // System.out.println("in:");
        String task = "2";
        //  String done = "DONE";
        try {
            int j = 0;
            ResultSet query = dbCon.query("SELECT * FROM suppliers");
            int size = getColumnsize("suppliers", "sup_id");
            Task_manager.weeklyadprog.setMaximum(size);
            while (query.next()) {
                /// System.out.println("query");
                int sup = query.getInt("sup_id");
                String name = query.getString("sup_name");
                //  System.out.println(sup);
                int i = 0;
                double[] output = new double[11];

                output = get_week_totals(sup, year, month);
               //  System.out.println("week totals done:");

                // System.out.println("init:");
                try {
                    dbCon.insert("INSERT INTO weekly_advance_current(entry,year,month,sup_id,sup_name,c_w1,c_w2,c_w3,c_w4,cash_total,o_w1,o_w2,o_w3,o_w4,other_total,total) VALUES('" + year + month + sup + "','" + year + "','" + month + "','" + sup + "','" + name + "','" + output[0] + "','" + output[1] + "','" + output[2] + "','" + output[3] + "','" + output[4] + "','" + output[5] + "','" + output[6] + "','" + output[7] + "','" + output[8] + "','" + output[9] + "','" + output[10] + "')");
                } catch (Exception e) {

                    dbCon.insert("UPDATE  weekly_advance_current SET year='" + year + "',month='" + month + "',sup_id='" + sup + "',sup_name='" + name + "',c_w1='" + output[0] + "',c_w2='" + output[1] + "',c_w3='" + output[2] + "',c_w4='" + output[3] + "',cash_total='" + output[4] + "',o_w1='" + output[5] + "',o_w2='" + output[6] + "',o_w3='" + output[7] + "',o_w4='" + output[8] + "',other_total='" + output[9] + "',total='" + output[10] + "' WHERE entry = '" + year + month + sup + "'");
                    // System.out.println("UPS");
                } //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                j++;

                Task_manager.weeklyadprog.setValue(j);

            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");

        }
        //update_taskmanager(year, month, task);
    }

    public double get_loans(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {
        // double[] cash_total = new double[32];
        // double[] other_total = new double[32];
        double output = 0;
        // double[] cash_new_month = new double[32];
        // double[] other_new_month = new double[32];

        double[] a = new double[10];

        //j = 0;
        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");

        Date date4;
        if (month.equals("12")) {
            date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year) + 1) + "-" + date_handler.get_next_month(month) + "-" + "07");
        } else {
            date4 = java.sql.Date.valueOf(year + "-" + date_handler.get_next_month(month) + "-" + "07");
        }

        // System.out.println(date1+"----"+date2+"----"+date3+"-----"+date4);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_loans" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                //System.out.println("query 1");
                // int dates = Integer.parseInt(date_handler.get_day(query.getDate("ordered_date")));
                // System.out.println(query.getDouble("amount"));
                output += query.getDouble("monthly_amount");

            }

        } catch (SQLException ex) {
            // System.out.println("Loans error");
            output = 0;

        }

        return output;
    }

    public void monthly_ledger_calc(String year, String month) throws SQLException {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        int k = 0;
        String task = "3";

        ResultSet query = dbCon.query("SELECT * FROM suppliers");
        int size = getColumnsize("suppliers", "sup_id");
        Task_manager.monthlegprog.setMaximum(size);
        while (query.next()) {
            int sup = query.getInt("sup_id");
            String name = query.getString("sup_name");
            String pay = query.getString("sup_pay_type");
            //String trans_code = query.getString("")
            // System.out.println(pay);
            double loans = get_loans(sup, year, month);
            // String trans_code = dbm.checknReturnData("suppliers", "sup_id", sup, "trans_rate");
            //System.out.println(trans_code);
            double trans = dbm.checknReturnDoubleData("daily_transactions_current", "entry", year + month + sup, "trans");
            //System.out.println(trans_rate);
            double total_kg = dbm.checknReturnDoubleData("daily_transactions_current", "entry", year + month + sup, "Total");
            //System.out.println(total_kg);
            String leaf_code = dbm.checknReturnData("suppliers", "sup_id", sup, "leaf_rate_code");
            double leaf_rate = dbm.checknReturnDoubleData("leaf_category", "category_name", leaf_code, "category_id");
            double pre_debts;
            double coinsbf;
            try {
                pre_debts = dbm.checknReturnDoubleData("supplier_pre_debt_coins", "entry", year + month + sup, "pre_debts");
                coinsbf = dbm.checknReturnDoubleData("supplier_pre_debt_coins", "entry", year + month + sup, "coins");

            } catch (Exception e) {
                pre_debts = 0;
                coinsbf = 0;
            }

            double welf = 0;

            welf = dbm.checknReturnDoubleData("welfare", "entry", year + month + sup, "amount");
            // System.out.println("WELFARE OK"+welf);

            double cash_advance = dbm.checknReturnDoubleData("weekly_advance_current", "entry", year + month + sup, "cash_total");
            double other_advance = dbm.checknReturnDoubleData("weekly_advance_current", "entry", year + month + sup, "other_total");
            double tax = dbm.checknReturnDoubleData("rate_details", "Code_name", "INTAX", "rate");
            double cards = dbm.checknReturnDoubleData("rate_details", "Code_name", "CARDS", "rate");
            // if()
            if (total_kg == 0) {
                cards = 0;
            }
            double net_amount = ((coinsbf + (total_kg * leaf_rate)) - (pre_debts + cash_advance + other_advance + cards + loans + trans + welf));
            double plusTax;
            if (net_amount > 25000) {
                plusTax = tax;
            } else {
                plusTax = 0.0;
            }
            double coinscf = ((net_amount + plusTax) % 10);
            if (net_amount < 0) {
                coinscf = 0;
            }
            double bal_cf = 0;
            double final_total = ((net_amount + plusTax) - coinscf);
            if (final_total < 0) {
                bal_cf = 0 - final_total;

                final_total = 0;

            }
            try {
                //double Grand_total =
                dbCon.insert("INSERT INTO gl_monthly_ledger_current(entry,year,month,sup_id,name,pay,total_kg,set_value,gross_amount,coins_bf,total_payable,pre_debts,cash_advances,other_advances,loans,cards,transport,welfare,total_deduction,net_amount,tax,final_payable,coins_cf,final_amount,bal_cf) "
                        + "VALUES('" + year + month + sup + "','" + year + "','" + month + "','" + sup + "','" + name + "','" + pay + "','" + total_kg + "','" + leaf_rate + "','" + (total_kg * leaf_rate) + "','" + coinsbf + "','" + (coinsbf + (total_kg * leaf_rate)) + "','" + pre_debts + "','" + cash_advance + "','" + other_advance + "','" + loans + "','" + cards + "','" + trans + "','" + welf + "','" + (pre_debts + cash_advance + other_advance + cards + loans + trans + welf) + "','" + net_amount + "','" + plusTax + "','" + (net_amount + plusTax) + "','" + coinscf + "','" + final_total + "','" + bal_cf + "')");
                //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                k++;
                //    System.out.println(k);

            } catch (SQLException ex) {
                //MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                dbCon.insert("UPDATE  gl_monthly_ledger_current SET year='" + year + "',month='" + month + "',sup_id='" + sup + "',name='" + name + "',pay='" + pay + "',total_kg='" + total_kg + "',set_value='" + leaf_rate + "',gross_amount='" + (total_kg * leaf_rate) + "',coins_bf='" + coinsbf + "',total_payable='" + (coinsbf + (total_kg * leaf_rate)) + "',pre_debts='" + pre_debts + "',cash_advances='" + cash_advance + "',other_advances='" + other_advance + "',loans ='" + loans + "',cards='" + cards + "',transport='" + trans + "',welfare = '" + welf + "',total_deduction='" + (pre_debts + cash_advance + other_advance + cards + trans) + "',net_amount='" + net_amount + "',tax='" + plusTax + "',final_payable='" + (net_amount + plusTax) + "',coins_cf='" + coinscf + "',final_amount='" + final_total + "',bal_cf='" + bal_cf + "' Where entry = '" + year + month + sup + "'");

            }
            Task_manager.monthlegprog.setValue(k);
        }
        //   update_taskmanager(year, month, task);
    }

    public void update_taskmanager(String year, String month, String task) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        String Nowdate = date_handler.get_today_date_time();
        try {
            dbCon.insert("INSERT INTO task_manager(entry,status,date) VALUES('" + year + month + task + "','DONE','" + Nowdate + "')");
        } catch (SQLException ex) {
            try {
                JOptionPane.showMessageDialog(null, "Need to update");
                dbCon.insert("UPDATE task_manager SET status ='DONE', date ='" + Nowdate + "' WHERE entry='" + year + month + task + "'");

                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void pre_debt_and_coin_Update(String year, String month) {
        double coin = 0, debts = 0;
        int sup = 0;
        String task = "4";
        int k = 0;
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbCon.query("SELECT * FROM gl_monthly_ledger_current WHERE year = '" + year + "' AND month= '" + month + "' ");

            while (query.next()) {
                k++;
            }
        } catch (Exception ee) {
        }

        try {
            // System.out.println(k);
            Task_manager.predebprog.setMaximum(k);
            k = 0;
            String nextmonth = date_handler.forwad_months(year, month, 1);
            String[] temp = new String[2];
            temp = nextmonth.split("-");
            ResultSet query2 = dbCon.query("SELECT * FROM gl_monthly_ledger_current WHERE year = '" + year + "' AND month= '" + month + "' ");
            while (query2.next()) {

                coin = query2.getDouble("coins_cf");
                debts = query2.getDouble("bal_cf");
                sup = query2.getInt("sup_id");

                try {
                    dbCon.insert("INSERT INTO supplier_pre_debt_coins(entry,sup_id,pre_debts,coins,month) VALUES('" + temp[0] + temp[1] + sup + "','" + sup + "','" + debts + "','" + coin + "','" + temp[0] + temp[1] + "')");
                } catch (Exception e) {
                    dbCon.insert("UPDATE supplier_pre_debt_coins SET sup_id ='" + sup + "', pre_debts= '" + debts + "',coins = '" + coin + "',month = '" + temp[0] + temp[1] + "' WHERE entry = '" + temp[0] + temp[1] + sup + "'");
                }

                k++;
                Task_manager.predebprog.setValue(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex);
        }

        //update_taskmanager(year, month, task);
    }

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }

        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

    /*
     public void update_welfare(String year, String month) {
     DatabaseManager dbCon = DatabaseManager.getDbCon();
     int index = 0;
     String status = null;
     String due = null;
     String due_new = null;
     int sup = 0;
     try {
            
     ResultSet query = dbCon.query("SELECT * FROM suppliers ");

     while (query.next()) {
                
     index = query.getInt("welf_num");
     status = query.getString("welfare");
     due = query.getString("wel_due");
     due_new = query.getString("wel_due_new");
     sup = query.getInt("sup_id");
     System.out.println(sup);
               
     if (status.equals("Registered")) {
     if (due.equals(year + month)) {
     due = date_handler.forwad_months(year, month, 1);

     }

     }
     if (status.equals("")) {
                     

     }
     if (status.equals("New Registered")) {
     if (due_new.equals(year + month)) {
     status = "Registered";

     }
     if (due.equals(year + month)) {
     //status = "Registered";
     due = date_handler.forwad_months(year, month, 1);

     }

     }
     if (status.equals("Suspended")) {
                
     if (due.equals(year + month)) {
     status = "Registered";
     index = 1;
     due = date_handler.forwad_months(year, month, 1);
     }
     }
     if (status.equals("New Suspended")) {
                
     if (due.equals(year + month)) {
     status = "New Registered";
     index = 1;
     due = date_handler.forwad_months(year, month, 1);
                      
     }
     }   
     dbCon.insert("UPDATE suppliers SET welf_num ='"+index+"', welfare ='" + status + "', wel_due ='" + due + "' WHERE sup_id='" + sup + "'");
     dbCon.insert("INSERT INTO supplier_pre_debt_coins(entry,sup_id,pre_debts,coins,welfare,welf_num,wel_due,wel_due_new,month) VALUES('" + year + month + sup + "','" +  sup + "','" + 0.0 + "','" + 0.0 + "','" + status + "','"+index+"','" + due + "','" + due_new + "','" + year+month + "')");
     }
     } catch (SQLException ex) {
     try {
     dbCon.insert("UPDATE supplier_pre_debt_coins SET welf_num ='"+index+"', welfare ='" + status + "', wel_due ='" + due + "', month ='" + year+month+ "' WHERE entry='" + year+month+sup + "'");
     } catch (SQLException ex1) {
     Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex1);
     }
     Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex);
     }

     }

     public double welfare_calc(String year, String month) {
     int index = 0;
     String status = null;
     String due = null;
     double welfare = 0;
     try {
     DatabaseManager dbCon = DatabaseManager.getDbCon();
     ResultSet query = dbCon.query("SELECT * FROM suppliers");

     while (query.next()) {
     index = query.getInt("welf_num");
     status = query.getString("welfare");
     due = query.getString("wel_due");

     if (status.equals("Registered")) {
     if (due.equals(year + month)) {
     JOptionPane.showMessageDialog(null, "Please update..");

     }
     if (due.equals(date_handler.forwad_months(year, month, 1))) {
     welfare = Double.parseDouble(dbm.checknReturnData("rate_details", "Code_name", "WELF_RATE", "rate"));

     }

     }
     if (status.equals("New Registered")) {
     if (due.equals(year + month)) {
     JOptionPane.showMessageDialog(null, "Please update..");

     }
     if (due.equals(date_handler.forwad_months(year, month, 1))) {
     welfare = Double.parseDouble(dbm.checknReturnData("rate_details", "Code_name", "WELF_NEW", "rate"));

     }

     }
     if (status.equals("Suspended")) {
     if (due.equals(year + month)) {
     JOptionPane.showMessageDialog(null, "Please update..");

     }
     if (!due.equals(year + month)) {
     if (date_handler.forwad_months(year, month, index).equals(due)) {
     welfare = Double.parseDouble(dbm.checknReturnData("rate_details", "Code_name", "WELF_RATE", "rate")) * index;

     }

     }
     }
     if (status.equals("New Suspended")) {
     if (due.equals(year + month)) {
     JOptionPane.showMessageDialog(null, "Please update..");

     }
     if (!due.equals(year + month)) {
     if (date_handler.forwad_months(year, month, index).equals(due)) {
     welfare = Double.parseDouble(dbm.checknReturnData("rate_details", "Code_name", "WELF_NEW", "rate")) * index;

     }

     }
     }
     }
     return welfare;
     } catch (SQLException ex) {
     Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex);
     return 0;
     }
  
     }
  
     public void wel_new_reg(int sup){
     int index = Integer.parseInt(dbm.checknReturnData("rate_details", "Code_name", "WELF_M", "rate"));
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", "Suspended");
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", 1);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(date_handler.get_today_year(),date_handler.return_month_as_num(date_handler.get_today_month()), index));
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due_new", date_handler.forwad_months(date_handler.get_today_year(),date_handler.return_month_as_num(date_handler.get_today_month()), index));
  
  
  
     }

     public boolean write_welfare(String year, String month, String status, int index, int sup) {

     String due = null;
     DatabaseManager dbCon = DatabaseManager.getDbCon();
     String current = dbm.checknReturnData("suppliers", "sup_id", sup, "welfare");

     if (status.equals("Registered")) {
     if (current.equals("Suspended") && index !=0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", "Suspended");
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", index);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, index));
     return true;
     }
     else if (current.equals("Suspended") && index ==0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", status);
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", 1);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, 1));
     return true;
     }
     else if (current.equals("Registered") && (index ==0|| index ==1)) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", status);
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", 1);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, 1));
     return true;

     }
     else if (current.equals("Registered") &&  index !=0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", "Suspended");
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", index);
                
                 
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, index));
     return true;

     }
     else{
     JOptionPane.showMessageDialog(null, "Unsupported Function Check again");
     return false;
     }

     }
     else if (status.equals("Suspended")) {
            
     System.out.println(index+"--"+current);
     if (current.equals("New Registered") && index == 0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", "New Suspended");
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", index);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", "N/A");
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due_new", "N/A");
     return true;

     }
     else if (current.equals("New Registered") && index != 0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", "New Suspended");
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", index);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, index));
     String changemonth = dbm.checknReturnData("suppliers", "sup_id", sup, "wel_due_new");
     String M = changemonth.substring(4, 6);
     String Y = changemonth.substring(0,4);
     System.out.println(Y+"==="+M);
     date_handler.forwad_months(Y, M, index);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due_new", date_handler.forwad_months(Y, M, index));
     return true;

     }
     else if (current.equals("Registered") && index == 0) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", status);
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", index);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", "N/A");
     return true;

     }
     else if (current.equals("Registered") && (index != 0)) {
     dbm.updateDatabase("suppliers", "sup_id", sup, "welfare", status);
     dbm.updateDatabase("suppliers", "sup_id", sup, "welf_num", 0);
     dbm.updateDatabase("suppliers", "sup_id", sup, "wel_due", date_handler.forwad_months(year, month, index));
     return true;

     }
     else{ JOptionPane.showMessageDialog(null, "Unsupported Function Check again");
     return false;}
            

     }
     else { JOptionPane.showMessageDialog(null, "Unsupported Function Check again");
     return false;    }

     }
     */
}
