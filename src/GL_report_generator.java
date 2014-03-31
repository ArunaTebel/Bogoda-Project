
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        double[] total = new double[32];
        int j = 0;
        while (j < 32) {
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

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return total;
    }

    public void daily_transaction_calc(String year, String month) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        //int  k =0 ;

        try {
            ResultSet query = dbCon.query("SELECT * FROM suppliers");

            while (query.next()) {
                int sup = query.getInt("sup_id");
                String name = query.getString("sup_name");
               // System.out.println(sup);
                int i = 0;
                double[] day_totals = new double[32];
                double total = 0;
                day_totals = get_day_totals(sup, year, month);
                while (i < 32) {
                    total += day_totals[i];

                    i++;
                }

                dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,sup_name,day_1,day_2,day_3,day_4,day_5,day_6,day_7,day_8,day_9,day_10,day_11,day_12,day_13,day_14,day_15,day_16,day_17,day_18,day_19,day_20,day_21,day_22,day_23,day_24,day_25,day_26,day_27,day_28,day_29,day_30,day_31,Total) VALUES('" + year + "','" + month + "','" + sup + "','" + name + "','" + day_totals[1] + "','" + day_totals[2] + "','" + day_totals[3] + "','" + day_totals[4] + "','" + day_totals[5] + "','" + day_totals[6] + "','" + day_totals[7] + "','" + day_totals[8] + "','" + day_totals[9] + "','" + day_totals[10] + "','" + day_totals[11] + "','" + day_totals[12] + "','" + day_totals[13] + "','" + day_totals[14] + "','" + day_totals[15] + "','" + day_totals[16] + "','" + day_totals[17] + "','" + day_totals[18] + "','" + day_totals[19] + "','" + day_totals[20] + "','" + day_totals[21] + "','" + day_totals[22] + "','" + day_totals[23] + "','" + day_totals[24] + "','" + day_totals[25] + "','" + day_totals[26] + "','" + day_totals[27] + "','" + day_totals[28] + "','" + day_totals[29] + "','" + day_totals[30] + "','" + day_totals[31] + "','" + total + "')");

                //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                //  k++;
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");

        }
    }

    public double[] get_week_totals(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {
        double[] cash_total = new double[32];
        double[] other_total = new double[32];
        double[] output = new double[11];
        double[] cash_new_month = new double[32];
        double[] other_new_month = new double[32];

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
            output[i] = 0;

            i++;
        }

        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");
        Date date2 = java.sql.Date.valueOf(year + "-" + month + "-" + "21");
        Date date3 = java.sql.Date.valueOf(year + "-" + date_handler.get_next_month(month) + "-" + "28");

        Date date4 = java.sql.Date.valueOf(year + "-" + "04" + "-" + "07");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
       
            ResultSet query = dbm.query("SELECT * FROM " + "gl_cash_advance" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "ordered_date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                 //System.out.println("query 1");
                int dates = Integer.parseInt(date_handler.get_day(query.getDate("ordered_date")));

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

                other_total[dates] += query.getDouble("total_amount");

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
        output[10]= output[4]+output[9];

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
        try {
            ResultSet query = dbCon.query("SELECT * FROM suppliers");

            while (query.next()) {
                /// System.out.println("query");
                int sup = query.getInt("sup_id");
                String name = query.getString("sup_name");
               // System.out.println(sup);
                int i = 0;
                double[] output = new double[11];
                
                output = get_week_totals(sup, year, month);
               //  System.out.println("week totals done:");
                
                // System.out.println("init:");
                dbCon.insert("INSERT INTO weekly_advance_current(year,month,sup_id,sup_name,c_w1,c_w2,c_w3,c_w4,cash_total,o_w1,o_w2,o_w3,o_w4,other_total,total) VALUES('" + year + "','" + month + "','" + sup + "','" + name + "','" + output[0] + "','" + output[1] + "','" + output[2] + "','" + output[3] + "','" + output[4] + "','" + output[5] + "','" +output[6] + "','" + output[7] + "','" + output[8] + "','" + output[9] + "','" + output[10]  + "')");
                  // System.out.println("wrt:");
                //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                 // k++;
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");

        }
        
 }
 
 
   public void monthly_ledger_calc(String year, String month) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        int  k =0 ;

        try {
            ResultSet query = dbCon.query("SELECT * FROM suppliers");

            while (query.next()&&k<40) {
                int sup = query.getInt("sup_id");
                String name = query.getString("sup_name");
                //String trans_code = query.getString("")
               // System.out.println(sup);
               
            String    trans_code=dbm.checknReturnData("suppliers","sup_id", sup, "trans_rate");
            System.out.println(trans_code);
            double trans_rate = dbm.checknReturnDoubleData("tranport_rates", "Trans_id", trans_code, "Trans_rate");
          System.out.println(trans_rate);
                      double total_kg = dbm.checknReturnDoubleData("daily_transactions_current", "sup_id", sup, "Total");
            System.out.println(total_kg);
          
          
               // dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,sup_name,day_1,day_2,day_3,day_4,day_5,day_6,day_7,day_8,day_9,day_10,day_11,day_12,day_13,day_14,day_15,day_16,day_17,day_18,day_19,day_20,day_21,day_22,day_23,day_24,day_25,day_26,day_27,day_28,day_29,day_30,day_31,Total) VALUES('" + year + "','" + month + "','" + sup + "','" + name + "','" + day_totals[1] + "','" + day_totals[2] + "','" + day_totals[3] + "','" + day_totals[4] + "','" + day_totals[5] + "','" + day_totals[6] + "','" + day_totals[7] + "','" + day_totals[8] + "','" + day_totals[9] + "','" + day_totals[10] + "','" + day_totals[11] + "','" + day_totals[12] + "','" + day_totals[13] + "','" + day_totals[14] + "','" + day_totals[15] + "','" + day_totals[16] + "','" + day_totals[17] + "','" + day_totals[18] + "','" + day_totals[19] + "','" + day_totals[20] + "','" + day_totals[21] + "','" + day_totals[22] + "','" + day_totals[23] + "','" + day_totals[24] + "','" + day_totals[25] + "','" + day_totals[26] + "','" + day_totals[27] + "','" + day_totals[28] + "','" + day_totals[29] + "','" + day_totals[30] + "','" + day_totals[31] + "','" + total + "')");

                //  dbCon.insert("INSERT INTO daily_transactions_current(year,month,sup_id,day_1,Total) VALUES('" + year + "','" + month + "','"  + sup + "','" + day_totals[0] +  "','"+ total + "')");
                  k++;
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");

        }
    }

}


 