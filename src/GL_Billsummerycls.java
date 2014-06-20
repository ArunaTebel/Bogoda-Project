
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
public class GL_Billsummerycls {

    DatabaseManager dbm = new DatabaseManager();
    Date_Handler datehandler = new Date_Handler();
    int id;
    double Total_kg;

    GL_Billsummerycls() {
        double Total_kg = 0;
    }

    public double Total_KG(int ID, Date date) {

        int gl_cashadvance_set_date_int = 10;   // This has to be taken from the database later

        datehandler.set_glcash_advance_starting_date_int(gl_cashadvance_set_date_int);
        Total_kg = dbm.checknReturnDataForCashAdvances("green_leaf_transactions", "sup_id", ID, "tr_date", datehandler.get_glcash_advance_starting_date(date), datehandler.get_date_as_a_String(date), "net_qty");

        return Total_kg;
    }

    public String[][] GL_table(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {
        String[][] total = new String[1000][5];
        int k = 0;
        int j = 0;
        while (j < 1000) {
            while (k < 5) {
                total[k][j] = null;
                k++;
            }

            j++;
        }
        j = 0;
        int i = 0;
    //    System.out.println("on");
        Date Sdate = java.sql.Date.valueOf(year + "-" + month + "-" + "01");

        Date Ldate = java.sql.Date.valueOf(year + "-" + month + "-" + "31");
//System.out.println(Sdate+"   "+ Ldate);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "green_leaf_transactions" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "tr_date" + " BETWEEN'" + Sdate + "' AND '" + Ldate + "'");

            while (query.next()) {

                total[i][0] = datehandler.get_date_as_a_String(query.getDate("tr_date"));
                total[i][1] = String.valueOf(query.getDouble("no_of_sacks"));
                total[i][2] = String.valueOf(query.getDouble("total_kg"));
                total[i][3] = String.valueOf(query.getDouble("sack_kg") + query.getDouble("water_kg") + query.getDouble("coarse_leaf_kg") + query.getDouble("other"));
                total[i][4] = String.valueOf(query.getDouble("net_qty"));
                // System.out.println(total[i][4]);
                i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return total;
    }

    public double Total_kg2(int sup_id, String year, String month) //take year and month and return daily totals for the specific user 
    {

        //int k =0;
        // int j = 0;
        double total = 0;
        // j = 0;
        // int i = 0;
        //System.out.println("on");
        Date Sdate = java.sql.Date.valueOf(year + "-" + month + "-" + "01");

        Date Ldate = java.sql.Date.valueOf(year + "-" + month + "-" + "31");
//System.out.println(Sdate+"   "+ Ldate);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "green_leaf_transactions" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "tr_date" + " BETWEEN'" + Sdate + "' AND '" + Ldate + "'");

            while (query.next()) {

                total += query.getDouble("net_qty");
              
                // i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return total;
    }

    public String[][] advance_table(int sup_id, String year, String month) {
        String[][] total = new String[1000][5];
        int k = 0;
        int j = 0;
        while (j < 1000) {
            while (k < 5) {
                total[k][j] = null;
                k++;
            }

            j++;
        }
        j = 0;
        int i = 0;
         Date date4;                      //INPUT MONTH AS NUMBER EX: 03
           
        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");
        
         if(month.equals("12")){
         date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year)+1) + "-" + datehandler.get_next_month(month) + "-" + "07");
         }
         
         else{ 
             date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");}
           
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_cash_advance" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "ordered_date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                total[i][0] = datehandler.get_date_as_a_String(query.getDate("ordered_date"));
                total[i][1] = "cash";
                total[i][2] = "none";
                total[i][3] = "N/A";
                total[i][4] = String.valueOf(query.getDouble("amount"));
                // System.out.println(total[i][4]);
                i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return total;
    }
    
     public String[][] other_advance_table(int sup_id, String year, String month) {
        String[][] total = new String[1000][5];
        int k = 0;
        int j = 0;
        while (j < 1000) {
            while (k < 5) {
                total[k][j] = null;
                k++;
            }

            j++;
        }
        j = 0;
        int i = 0;
        Date date4;
         Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");
        
         if(month.equals("12")){
         date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year)+1) + "-" + datehandler.get_next_month(month) + "-" + "07");
         }
         
         else{ 
             date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");}
         //System.out.println(date1);
        // System.out.println(date4);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_other_advances" + " where " + "id" + " = '" + sup_id + " 'AND " + "Date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                total[i][0] = datehandler.get_date_as_a_String(query.getDate("Date"));
                total[i][1] = query.getString("item_type");
                total[i][2] = query.getString("item_name");
                total[i][3] = String.valueOf(query.getInt("installments"));
                total[i][4] = String.valueOf(query.getDouble("total_amount"));
                // System.out.println(total[i][4]);
               // System.out.println("hit");
                i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return total;
    }

    /**
     *
     * @param sup
     * @return
     */
    public double total_advance(int sup_id, String year, String month) {
        double total = 0;

        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");

        Date date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_cash_advance" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "ordered_date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
               
                // int dates = Integer.parseInt(date_handler.get_day(query.getDate("ordered_date")));

                total += query.getDouble("amount");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return total;
    }

    public double total_other_advance(int sup_id, String year, String month) {
        double total = 0;

        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");

        Date date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_other_advances" + " where " + "id" + " = '" + sup_id + " 'AND " + "Date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                
                // int dates = Integer.parseInt(date_handler.get_day(query.getDate("ordered_date")));

                total += query.getDouble("total_amount");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return total;
    }
     //public double set_gl_table(int sup, String month, javax){
    //double total =0;
    // return total;
    // }
    // 
    
    public double bill_sum_cal (int sup , String year ,String month, Double set) {
    double total= 0;
    
    total = (Total_kg2(sup, year, month)*set)- total_advance(sup, year, month)- total_other_advance(sup, year, month);
    
    
    
    
    return total;
    }
    
    
     public String[][] loans_table(int sup_id, String year, String month) {
        String[][] total = new String[1000][6];
        int k = 0;
        int j = 0;
        while (j < 1000) {
            while (k < 6) {
                total[k][j] = null;
                k++;
            }

            j++;
        }
        j = 0;
        int i = 0;
         Date date4;                      //INPUT MONTH AS NUMBER EX: 03
           
        Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");
        
         if(month.equals("12")){
         date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year)+1) + "-" + datehandler.get_next_month(month) + "-" + "07");
         }
         
         else{ 
             date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");}
           
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            ResultSet query = dbm.query("SELECT * FROM " + "gl_loans" + " where " + "sup_id" + " = '" + sup_id + " 'AND " + "date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");

            while (query.next()) {
                total[i][0] = datehandler.get_date_as_a_String(query.getDate("date"));
                
                total[i][1] = datehandler.get_date_as_a_String(query.getDate("issue_date"));
                total[i][2] = query.getString("amount");
                total[i][3]= query.getString("installments");
                total[i][4] = String.valueOf(query.getDouble("monthly_amount"));
                 System.out.println(total[i][4]);
                i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return total;
    }
     public String two_dec_places(String num){
          String re= null;
         if(num.contains(".")){
         System.out.println(num);
     String[] temp;
         
     temp = num.split(".");
     System.out.println(temp[0]+"-----------------"+temp[1]);
      re = temp[0]+"."+temp[1].substring(0, 1);
     
     
     
         }
     return re;
     }
}
