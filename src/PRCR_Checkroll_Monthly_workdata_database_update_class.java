//floor or ceil???
//delete random number generator in workdetails update

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dhananjaya
 */
public class PRCR_Checkroll_Monthly_workdata_database_update_class implements Runnable {

    DatabaseManager dbm = DatabaseManager.getDbCon();
    PRCR_HolidayPay_Database_Handling hdh = new PRCR_HolidayPay_Database_Handling();
   // PRCR_Staff_Salary_Cal scal = new PRCR_Staff_Salary_Cal();
  //  PRCR_checkroll_salary_process csp = new PRCR_checkroll_salary_process();
    
    
    ResultSet query;
    String st;
    public static int columnsize;
    public static int salarycalprogressbar = 0;

    double pre_debt_amount = 0;
    double prvmnth_normaldays = 0;
    double prvmnth_sundays = 0;
    int prvmnthdays = 0;
    double coinsbf = 0;
    int no_of_codes = 0;
    int codes[];
    String tempst;
    String division[];
    int nofdivisions;
    String year_month;
    String regorcas[] = {"Register", "Casual"};
    double payslip;
    double normalDayRate;
    //PRCR_Checkroll_Salary_Cal ccal = new PRCR_Checkroll_Salary_Cal();

    public PRCR_Checkroll_Monthly_workdata_database_update_class(String st) {

        this.st = st;
       
        payslip= Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "payslip"));
         normalDayRate=Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "normalday_rate"));

    }

    public void CreateNewMonthTable(String yr_mnth) throws SQLException {

        Task_manager.newmonthL.setText("New Month Table is being created");
        Task_manager.newmonthC.setSelected(false);
        Task_manager.newmonthP.setValue(0);

        //DatabaseManager dbm = DatabaseManager.getDbCon();

        if (dbm.TableExistence("pr_workdata_" + yr_mnth)) {
            dbm.DeleteTable("pr_workdata_" + yr_mnth);
            dbm.insert("DROP TABLE pr_workdata_" + yr_mnth + "");
        }
        System.out.println("new table is being created");
        try {
            //use new_1 and new_2 if any other deduction type is needd to be added
            dbm.insert("CREATE TABLE pr_workdata_" + yr_mnth + "(code INT,"
                    + "division VARCHAR(15)," + "register_or_casual INT,"
                    + "normal_days INT DEFAULT '0'," + "normal_pay DOUBLE DEFAULT '0'," + "sundays INT DEFAULT '0',"
                    + "sunday_pay DOUBLE DEFAULT '0'," + "total_pay DOUBLE DEFAULT '0'," + "ot_before_hours DOUBLE DEFAULT '0',"
                    + "ot_before_amount DOUBLE DEFAULT '0'," + "ot_after_hours DOUBLE DEFAULT '0'," + "ot_after_amount DOUBLE DEFAULT '0'," + "coinsbf DOUBLE DEFAULT '0',"
                    + "incentive1 DOUBLE DEFAULT '0'," + "incentive2 DOUBLE DEFAULT '0'," + "extra_pay_cash DOUBLE DEFAULT '0'," + "extra_pay_overkilos DOUBLE DEFAULT '0',"
                    + "working_days_prvmnth DOUBLE DEFAULT '0'," + "extra_pay_holiday DOUBLE DEFAULT '0'," + "holidays_thsyr INT DEFAULT '0'," + "normal_days_bfr17 INT DEFAULT '0'," + "sundays_bfr17 INT DEFAULT '0'," + "extra_pay_may DOUBLE DEFAULT '0'," + "extra_pay DOUBLE DEFAULT '0',"
                    + "gross_pay DOUBLE DEFAULT '0'," + "tea DOUBLE DEFAULT '0'," + "salary_adv DOUBLE DEFAULT '0'," + "fest_adv DOUBLE DEFAULT '0',"
                    + "food DOUBLE DEFAULT '0'," + "loan DOUBLE DEFAULT '0'," + "bank DOUBLE DEFAULT '0'," + "epf10 DOUBLE DEFAULT '0'," + "epf12 DOUBLE DEFAULT '0',"
                    + "total_epf DOUBLE DEFAULT '0'," + "etf DOUBLE DEFAULT '0'," + "ceb DOUBLE DEFAULT '0'," + "teacher DOUBLE DEFAULT '0'," + "chemical DOUBLE DEFAULT '0',"
                    + "pay_slip DOUBLE DEFAULT '0'," + "fine DOUBLE DEFAULT '0'," + "welfare DOUBLE DEFAULT '0'," + "kovil DOUBLE DEFAULT '0'," + "new_1 DOUBLE DEFAULT '0'," + "new_2 DOUBLE DEFAULT '0'," + "other_ded1 DOUBLE DEFAULT '0',"
                    + "meals DOUBLE DEFAULT '0'," + "other_ded2 DOUBLE DEFAULT '0'," + "pension DOUBLE DEFAULT '0',"
                    + "other_ded3 DOUBLE DEFAULT '0'," + "stamp DOUBLE DEFAULT '0'," + "pre_debt DOUBLE DEFAULT '0'," + "total_ded DOUBLE DEFAULT '0',"
                    + "full_salary DOUBLE DEFAULT '0'," + "coins DOUBLE DEFAULT '0'," + "paid_amount DOUBLE DEFAULT '0'," + "active INT DEFAULT '0'," + "next_month DOUBLE DEFAULT '0'," + "prvs_debts_paid DOUBLE DEFAULT '0'," + "n_5000 INT DEFAULT '0',"
                    + "n_2000 INT DEFAULT '0'," + "n_1000 INT DEFAULT '0'," + "n_500 INT DEFAULT '0'," + "n_100 INT DEFAULT '0'," + "n_50 INT DEFAULT '0'," + "n_20 INT DEFAULT '0'," + "n_10 INT DEFAULT '0');");

            //  System.out.println("new table created");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in ex");
        }
        ///DONT DELETE checkroll_personalinfo SQL table
        //copying worker's codes to newly created table
        CopyTable3Columns("checkroll_personalinfo", "code", "division", "register_or_casual", "pr_workdata_" + yr_mnth, "code", "division", "register_or_casual");
        //copy the previous month's 'next_month' amount to 'pre_debt' column in this month
        Task_manager.newmonthC.setSelected(true);
        Task_manager.newmonthL.setText("New Month Table has been created");

        Task_manager.prvdebtsL.setText("Previous Debts are being updated");
        GetPreDebts(yr_mnth);
        Task_manager.prvdebtsL.setText("Previous Debts has been updated");
        Task_manager.prvdebtsC.setSelected(true);

        System.out.println("table copied");
        // JOptionPane.showMessageDialog(null, "New checkroll table is created for this month\n", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    //method to copy the previous month's 'next_month' amount to 'pre_debt' column in this month and insert previous month working days in to new month table
    public void GetPreDebts(String yrmnth) {
        System.out.println("in getpre debts");
        String prv_yrmnth = ReturnPrvMnthTableName(yrmnth);
       // no_of_codes = getActiveColumnsize("pr_workdata_" + prv_yrmnth, "code", "active", "1");
        //codes = new int[no_of_codes];
        codes = getIntArray("pr_workdata_" + prv_yrmnth, "code", "active", "0");
        no_of_codes=codes.length;
        pre_debt_amount = 0;
        prvmnth_normaldays = 0;
        prvmnth_sundays = 0;
        coinsbf = 0;

        for (int i = 0; i < no_of_codes; i++) {
            Task_manager.prvdebtsP.setValue((100 * (i + 1)) / no_of_codes);
            try {
//                if (dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month") != null) {

                    pre_debt_amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month"));//+Double.parseDouble(dbm.checknReturnData("pr_workdata_" + yrmnth, "code", codes[i], "prvs_debts_paid"));
                    pre_debt_amount = -pre_debt_amount;
//                } else {
//                    pre_debt_amount = 0;
//                }

                prvmnth_normaldays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "normal_days"));
                prvmnth_sundays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "sundays"));
                coinsbf = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "coins"));
                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", codes[i], "coinsbf", (coinsbf));

                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", codes[i], "working_days_prvmnth", (prvmnth_normaldays + prvmnth_sundays));
                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", codes[i], "pre_debt", pre_debt_amount);
                if (coinsbf > 0) {
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", codes[i], "active", 3);//actve = 2 codes will not be in ledger
                }
                if (pre_debt_amount != 0 ) {
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", codes[i], "active", 2);
                }

//If thr is debit balance in previous month set active=1
            } catch (Exception e) {
                System.out.println(e.getMessage() + " i=" + i);
            }

        }
        
         try {
            ResultSet query=dbm.query("SELECT * FROM prcr_registration WHERE register_month LIKE '"+yrmnth+"'");
           System.out.println("162:"+yrmnth);
            while(query.next()){
                
                System.out.println("162:"+query.getInt("reg_code")+","+query.getInt("debits"));
                
                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", query.getInt("reg_code"), "coinsbf", query.getDouble("coins"));
                 dbm.updateDatabase("pr_workdata_" + yrmnth, "code", query.getInt("casual_code"), "coinsbf", 0);//remove debts from casual number,
                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", query.getInt("reg_code"), "pre_debt", query.getDouble("debits"));
                dbm.updateDatabase("pr_workdata_" + yrmnth, "code", query.getInt("casual_code"), "pre_debt", 0);
                
                 if (query.getDouble("debits") != 0 || query.getDouble("coins") > 0) {
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", query.getInt("reg_code"), "active", 2);
                }//If thr is debit balance in previous month set active=1
            }
            query.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PRCR_Checkroll_Monthly_workdata_database_update_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String ReturnPrvMnthTableName(String thisMnth) {
        String[] year_month = new String[2];
        int[] yr_mnth_int = new int[2];
        year_month = thisMnth.split("_");
        yr_mnth_int[0] = Integer.parseInt(year_month[0]);
        yr_mnth_int[1] = Integer.parseInt(year_month[1]);
        if (yr_mnth_int[1] == 1) {
            yr_mnth_int[0] = yr_mnth_int[0] - 1;
            yr_mnth_int[1] = 12;
        } else {
            yr_mnth_int[1] = yr_mnth_int[1] - 1;
        }
        String prvMnth;
        if (yr_mnth_int[1] > 9) {
            prvMnth = String.valueOf(yr_mnth_int[0]) + "_" + String.valueOf(yr_mnth_int[1]);
        } else {
            prvMnth = String.valueOf(yr_mnth_int[0]) + "_0" + String.valueOf(yr_mnth_int[1]);
        }
        return prvMnth;
    }

    public int[] getIntArray(String table_name, String column_name, String table_column_giving1, String row_element1) {

        int count = 0;
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            query = dbm.query("SELECT " + column_name + " FROM " + table_name + " WHERE " + table_column_giving1 + " >'" + row_element1 + "'");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            query = dbm.query("SELECT " + column_name + " FROM " + table_name + " WHERE " + table_column_giving1 + " >'" + row_element1 + "'");
            while (query.next()) {
                array[count] = query.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public void CopyTable3Columns(String table_name1, String table1_column1, String table1_column2, String table1_column3, String table_name2, String table2_column1, String table2_column2, String table2_column3) {

        //DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            int i = 0;
            while (query.next()) {

                i++;
                Task_manager.newmonthP.setValue((100 * i) / columnsize);
                try {

                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + "," + table2_column3 + ") VALUES('" + query.getString(table1_column1) + "','" + query.getString(table1_column2) + "','" + query.getString(table1_column3) + "')");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }
    }

    public void duplicateTable(String original_table_name, String table_copy_name) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            if (dbm.TableExistence(table_copy_name)) {
                dbm.insert("DROP TABLE " + table_copy_name + "");
            }
            dbm.insert("CREATE TABLE " + table_copy_name + " LIKE " + original_table_name + "");
            dbm.insert("INSERT " + table_copy_name + " SELECT * FROM " + original_table_name + "");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
    // Date column is the column in your database which dates are entered
    // String month is the month you want to check. Give month as a string ex:- 01 for January, 09 for September
    // String column_need_to_check is the column that you need to filter in your case the column that has n's and s's :)
    // Object element is the element you need to check in that column. Ex:- Either "s" or "n"

    public int checknReturnNoOfData(String table_name, String date_column, String st, String workCodeColumn, int Code, String column_need_to_check, Object element_to_check) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
//                 System.out.println(query.getString(date_column).substring(0,7)+"  "+st);
//                 System.out.println(query.getString(divCodeColumn)+" "+divCode);
//                 System.out.println(query.getString(workCodeColumn)+"  "+workCode);
//                 System.out.println(query.getString(column_need_to_check)+"  "+element_to_check);
//                 System.out.println(query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode) && query.getString(column_need_to_check).equals(element_to_check));
//                 
//System.out.println("gssgsdg   gsdg "+division_jc.getSelectedItem().toString());
                if (query.getString(date_column).substring(0, 7).equals(st) && query.getInt(workCodeColumn) == Code && query.getString(column_need_to_check).equals(element_to_check)) {
                    count++;

                }
            }
            return count;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return count;
    }

    // column_need_to_get_total is Either Day OT hour column or Night OT hour column.. All other parameters are similar to the above method
    public double checknReturnTotal(String table_name, String date_column, String st, String CodeColumn, int Code, String column_need_to_get_total) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
           query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if (query.getString(date_column).substring(0, 7).equals(st) && query.getInt(CodeColumn) == Code) {
                    tot = tot + query.getDouble(column_need_to_get_total);
                }
            }
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

    public double checknReturnAdvanceTotal(String table_name, String date_column, String st, String CodeColumn, int Code, String type_column, String type, String column_need_to_get_total) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if (query.getString(date_column).substring(0, 7).equals(st) && query.getInt(CodeColumn) == Code && query.getString(type_column).equals(type)) {
                    tot = tot + query.getDouble(column_need_to_get_total);
                }
            }
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

    public String[] getStringArray(String table_name, String column_name) {

        int count = 0;
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
           query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count];
            count = 0;
            query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                array[count] = query.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }

        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

 

    public int getActiveWorkersColumnsize(String table_name) {
        System.out.println("in getactive " + table_name);
        int count = 0;
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {

                if (query.getInt("active") == 1) {
                    count++;
                    System.out.println("count " + count);
                }
            }
            if (count == 0) {
                System.err.println("No active workers for this month or error in getActiveWorkersColumnSize method");
            }
            return count;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
        //return null;

    }

    String workentryst;


    @Override
    public void run() {

//        payslip= Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "payslip"));
//         normalDayRate=Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", "1", "normalday_rate"));
        
        columnsize = getColumnsize("checkroll_personalinfo", "code");//uncomment below lines !
        if (Task_manager.newmonthC.isSelected() == false) {
            try {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                CreateNewMonthTable(st);
            } catch (SQLException ex) {
                Logger.getLogger(PRCR_Checkroll_Monthly_workdata_database_update_class.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        workentryst = st.replace("_", "-");
        if (Task_manager.workdetailsC.isSelected() == false) {

            Task_manager.workdetailsL.setText("Work Details are being updated");
            UpdateWorkDetails("prcr_checkroll_workentry", "date", workentryst);

            Task_manager.workdetailsL.setText("Work Details has been updated");
            Task_manager.workdetailsP.setValue(100);
        }
        columnsize = getActiveWorkersColumnsize("pr_workdata_" + st);

        Task_manager.workdetailsC.setSelected(true);
        //staff details
        if (Task_manager.staffdetailsC.isSelected() == false) {
            Task_manager.staffdetailsL.setText("Staff Details are being updated");
            staff_fill("pr_workdata_" + st);
            Task_manager.staffdetailsC.setSelected(true);
            Task_manager.staffdetailsL.setText("Staff Details has been updated");
        }
        //Loans,Cash and Other aAdvances
        if (Task_manager.advanceC.isSelected() == false) {
            updateLoansCashNOtherAdvances("date", workentryst);
        }
        //Extra pay
        if (Task_manager.extrapayC.isSelected() == false) {
            updateExtraPayAndDebitpay("date", workentryst);
        }
        //Salary Calculaion
        if (Task_manager.salarycaloverallC.isSelected() == false) {

            //int nofdivisions = getColumnsize("division_details", "code");
            Task_manager.salarycaloverallL.setText("Salary is being Calculated");

       // String division[] = new String[nofdivisions];
            division = dbm.getStringArray("division_details", "code");
            nofdivisions = division.length;
            //auto margin dates
            // updateMarginDates(workentryst, division, nofdivisions);//(2014-03,division[])-a method to update margin dates for this month for each division,
            //manual margin dates
            
         
            year_month="pr_workdata_" + st;
            int i=0;
            
             PRCR_Checkroll_Salary_Cal ccal = new PRCR_Checkroll_Salary_Cal();
               
            try {
                 query=dbm.query("SELECT * FROM "+year_month+" WHERE active > '"+0+"' AND division NOT LIKE '"+"STAFF"+"'");
                while(query.next()){
                    i++;
                    Task_manager.salarycaloverallP.setValue((100*i)/300);
                   
            
                    ccal.CalculateSalary(st,query.getString("division"),query.getInt("code"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(PRCR_Checkroll_Monthly_workdata_database_update_class.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            PRCR_Staff_Salary_Cal scal = new PRCR_Staff_Salary_Cal();
            try {
                 
                 query=dbm.query("SELECT * FROM "+year_month+" WHERE active > '"+0+"' AND division LIKE '"+"STAFF"+"'");
                while(query.next()){
                    i++;
                    Task_manager.salarycaloverallP.setValue((100*i)/300);
                    scal.CalculateSalary(st,query.getString("division"),query.getInt("code"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(PRCR_Checkroll_Monthly_workdata_database_update_class.class.getName()).log(Level.SEVERE, null, ex);
            }
            Task_manager.salarycaloverallP.setValue(100);
            Task_manager.salarycalL.setText("Salary has been calculated for all divisions");

            Task_manager.salarycaloverallL.setText("Salary has been Calculated");
            Task_manager.salarycaloverallC.setSelected(true);
            ccal=null;
            scal=null;
        }
    }
String starttt;
        String enddd;
    public void updateMarginDates(String workentryst, String[] division, int nofdiv) {

        short days[];
         int workingdays = 0;
         starttt = st.replace('_','-') + "-01";
        enddd = st.replace('_','-') + "-31";
        for (int i = 1; i < nofdiv + 1; i++) {
            days = new short[31];
            
            workingdays = 0;
            try {
                
               query = dbm.query("SELECT * FROM prcr_checkroll_workentry WHERE division LIKE '"+division[i]+"' AND date BETWEEN '"+starttt+"' AND '"+enddd+"'");

                while (query.next()) {

                   
                        days[Integer.parseInt(query.getString("date").substring(8, 10).toString())] = 1;
                 

                }
               
                for (int j = 0; j < 31; j++) {
                    if (days[j] == 1) {
                        workingdays++;
                    }

                }
                Task_manager.MessageTex.append("Division:" + division[i] + " Working Days:" + workingdays + " Date Margin:" + Math.floor(workingdays * 0.75) + "\n");

                if (checkDataAvailability("prcr_margin_dates", "month", st, "division", division[i], "margin")) {
                    updateDatabase("prcr_margin_dates", "month", st, "division", division[i], "total", workingdays);
                    updateDatabase("prcr_margin_dates", "month", st, "division", division[i], "margin", Math.floor(workingdays * 0.75));
                } else {
                    dbm.insert("INSERT INTO prcr_margin_dates(month,division,margin,total) VALUES('" + st + "','" + division[i] + "','" + Math.floor(workingdays * 0.75) + "','" + workingdays + "')");
                }

            } catch (Exception e) {
                System.err.println(e.getMessage() + "Error:may be a entry in table:checkroll_workentry column:division is NULL");
            }

        }
days=null;
    }

    public void UpdateWorkDetails(String table_name, String date_column, String st) {//st=2014-03
        ////DatabaseManager dbm = DatabaseManager.getDbCon();
        int code;
        // String yrmnth = st.replace("-", "_");
        String start = st + "-01";
        String end = st + "-31";
        int count = 0;
        try {
    
            int i = 0;

            int normaldays = 0;
            int sundays = 0;
            double otbeforehrs = 0;
            int holidays = 0;

            double otafterhrs = 0;
            tempst = this.st;
            int activecodes[] = dbm.prcr_active_emp_codes_for_month("prcr_checkroll_workentry", st, "date", "emp_code");
            String[] columns = {"ot_day", "ot_night"};
            double[] total;
            int[] normalsun;
            int length = activecodes.length;
            System.err.println("------------active in workdetails-------" + length);
            System.out.println(length);
            if (!(st.substring(6, 7).equals("5"))) {
                for (i = 0; i < length; i++) {
                    

                    total = dbm.prcr_emp_code_month_totals("prcr_checkroll_workentry", st, "date", "emp_code", activecodes[i], columns);
                    normalsun = dbm.prcr_emp_code_month_totals_normalOrsunday("prcr_checkroll_workentry", st, "date", "emp_code", activecodes[i], "normalday_or_sunday");

                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "normal_days", normalsun[0]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "sundays", normalsun[1]);

                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "ot_before_hours", total[0]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "ot_after_hours", total[1]);

                    Task_manager.workdetailsP.setValue((100 * i) / (length + 1));

                    prvmnthdays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + this.st, "code", activecodes[i], "working_days_prvmnth"));

                    if (tempst.substring(5, 7).equals("02") || tempst.substring(5, 7).equals("04")) {

                        if (prvmnthdays > 0) {
                            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "extra_pay_holiday",normalDayRate);
                            System.out.println("551");
                        }
                    }

                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "active", 1);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i],"pay_slip",2);
                    System.out.println(activecodes[i]+"------payslip-----"+payslip);

                }
            } else {

                Random randomGenerator = new Random();//DELETE THIS
                for (i = 0; i < length; i++) {

                   

                    total = dbm.prcr_emp_code_month_totals("prcr_checkroll_workentry", st, "date", "emp_code", activecodes[i], columns);

                    try {
                        //holidays = hdh.num_of_holidays_for_willie(Integer.parseInt(st.substring(0, 4)), code);
                        holidays = randomGenerator.nextInt(17);//DELETE THIS
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        holidays = 0;
                    }

                    normalsun = dbm.prcr_emp_code_May_month_totals_normalOrsunday("prcr_checkroll_workentry", st, "date", "emp_code", activecodes[i], "normalday_or_sunday", holidays);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "holidays_thsyr", holidays);

                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "normal_days_bfr17", normalsun[0]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "sundays_bfr17", normalsun[1]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "normal_days", normalsun[2]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "sundays", normalsun[3]);

                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "ot_before_hours", total[0]);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "ot_after_hours", total[1]);

                    Task_manager.workdetailsP.setValue((100 * i) / (length + 1));

                    prvmnthdays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + this.st, "code", activecodes[i], "working_days_prvmnth"));

                    if ((prvmnthdays) > 0) {
                        dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "extra_pay_holiday", normalDayRate);
                    }
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "active", 1);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i],"pay_slip",payslip);

                }

            }
            
        } catch (Exception ex) {
            System.out.println("Error in workdetails--" + ex.getMessage());

        }
        //return count;
    }

    public double updateLoansCashNOtherAdvances(String date_column, String st) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        double tea = 0;
        double fes_adv = 0;
        double cash_advance = 0;
        double food = 0;
        double loan = 0;
        double ceb = 0;
        double teacher = 0;
        double chemical = 0;
        //double payslip = 0;
        double fine = 0;
        double meals = 0;
        double pension = 0;
        double welfare = 0;
        double kovil = 0;
        double other1 = 0;
        double other2 = 0;
        int code;
        int nofentries1 = 0;
        int nofentries2 = 0;
        int count = 0;

        Task_manager.advanceL.setText("Advances are being updated");
        int activecodes[] = dbm.prcr_active_emp_codes_for_month("prcr_other_advance_book", st, "date", "code");
        int activecodescash[] = dbm.prcr_active_emp_codes_for_month("prcr_cash_advance_book", st, "date", "code");

        nofentries1 = activecodes.length;
        nofentries2 = activecodescash.length;

        int codes[] = dbm.prcr_loan_emp_code_array(this.st.substring(0, 4), this.st.substring(5, 7));
        int welfarecodes[] = dbm.prcr_welfare_emp_code_array(this.st.substring(0, 4), this.st.substring(5, 7));
        int length = activecodes.length;
        //other advance
        for (int i = 0; i < length; i++) {
            tea = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "TEA", "amount");
            fes_adv = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "FESTIVAL", "amount");
            food = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "FOODSTUFFS", "amount");
            loan = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "LOAN", "amount");
            ceb = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "CEB", "amount");
            teacher = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "TEACHER", "amount");
            chemical = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "CHMICAL", "amount");
           // payslip = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "PAYSLIP", "amount");
            fine = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "FINE", "amount");
            meals = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "MEALS", "amount");
            pension = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "PENSION", "amount");
            welfare = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "WELFARE", "amount");
            kovil = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "KOVIL", "amount");
            other1 = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "OTHER_1", "amount");
            other2 = dbm.prcr_emp_code_other_advance_month_totals("prcr_other_advance_book", st, "date", "code", activecodes[i], "type", "OTHER_2", "amount");

            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "tea", tea);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "fest_adv", fes_adv);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "food", food);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "loan", loan);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "ceb", ceb);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "teacher", teacher);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "chemical", chemical);
           // dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "pay_slip", payslip);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "fine", fine);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "meals", meals);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "pension", pension);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "welfare", welfare);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "kovil", kovil);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "new_1", other1);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "new_2", other2);

            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "active", 1);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i],"pay_slip",payslip);
            Task_manager.advanceP.setValue(((i) * 100) / (nofentries1 + nofentries2 + codes.length + welfarecodes.length + 1));

        }

        //cash advance
        for (int i = 0; i < length; i++) {
            cash_advance = dbm.prcr_emp_code_other_advance_month_totals("prcr_cash_advance_book", st, "date", "code", activecodes[i], "type", "CASH ADVANCE", "amount");

            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "salary_adv", cash_advance);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "active", 1);
            dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i],"pay_slip",payslip);
            Task_manager.advanceP.setValue(((nofentries1 + i) * 100) / (nofentries1 + nofentries2 + codes.length + welfarecodes.length + 1));

        }

        try {

//loans
            // int codes[]=dbm.prcr_loan_emp_code_array(this.st.substring(0,4), this.st.substring(5,7));
            for (int j = 0; j < codes.length; j++) {
                Task_manager.advanceP.setValue(((nofentries1 + nofentries2 + j) * 100) / (nofentries1 + nofentries2 + codes.length + welfarecodes.length + 1));
                dbm.updateDatabase("pr_workdata_" + this.st, "code", codes[j], "active", 1);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", codes[j],"pay_slip",payslip);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", codes[j], "loan", dbm.prcr_loan(codes[j], this.st.substring(0, 4), this.st.substring(5, 7)));
                System.err.println(codes[j] + " - " + dbm.prcr_loan(codes[j], this.st.substring(0, 4), this.st.substring(5, 7)));
            
            }

//welfare
            for (int j = 0; j < welfarecodes.length; j++) {
                Task_manager.advanceP.setValue(((nofentries1 + nofentries2 + codes.length + j) * 100) / (nofentries1 + nofentries2 + codes.length + welfarecodes.length + 1));
                dbm.updateDatabase("pr_workdata_" + this.st, "code", welfarecodes[j], "active", 1);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", welfarecodes[j],"pay_slip",payslip);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", welfarecodes[j], "welfare", dbm.prcr_welfare(welfarecodes[j], this.st.substring(0, 4), this.st.substring(5, 7)));
                System.err.println(welfarecodes[j] + " - " + dbm.prcr_welfare(welfarecodes[j], this.st.substring(0, 4), this.st.substring(5, 7)));
            }

            Task_manager.advanceP.setValue(100);
            Task_manager.advanceL.setText("Advances has been updated");
            Task_manager.advanceC.setSelected(true);
            return tot;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

    public double updateExtraPayAndDebitpay(String date_column, String st) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        double overkilos = 0;
        double cash = 0;
        double holiday = 0;
        double may = 0;
        double debitpay=0;

        int code;
        int nofentries1 = 0;
        int nofentries2 = 0;
        int count = 0;
        try {

            int activecodes[] = dbm.prcr_active_emp_codes_for_month("prcr_extrapayment_book", st, "date", "code");
            int activecodesdebitpay[]=dbm.prcr_active_emp_codes_for_month("prcr_debit_pay", st, "date", "code");
            
            nofentries1 = activecodes.length;
            nofentries2=activecodesdebitpay.length;
            
            for (int i = 0; i < nofentries1; i++) {
                overkilos = dbm.prcr_emp_code_other_advance_month_totals("prcr_extrapayment_book", st, "date", "code", activecodes[i], "type", "OVERKILOS", "amount");
                cash = dbm.prcr_emp_code_other_advance_month_totals("prcr_extrapayment_book", st, "date", "code", activecodes[i], "type", "CASHWORK", "amount");

                dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "extra_pay_overkilos", overkilos);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "extra_pay_cash", cash);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i], "active", 1);
                dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodes[i],"pay_slip",payslip);

                Task_manager.extrapayP.setValue((i * 100) / (nofentries1+nofentries2 + 1));
            }

            for (int i = 0; i < nofentries2; i++) {
                debitpay = dbm.prcr_emp_code_other_advance_month_totals("prcr_debit_pay", st, "date", "code",activecodesdebitpay[i], "type", "DEBIT PAY", "amount");
                
                dbm.updateDatabase("pr_workdata_" + this.st, "code", activecodesdebitpay[i], "prvs_debts_paid", debitpay);

                Task_manager.extrapayP.setValue(((nofentries2+i) * 100) / (nofentries1+nofentries2 + 1));
            }
 
            
            
            
            
            
          
            Task_manager.extrapayP.setValue(100);
            Task_manager.extrapayL.setText("Extra Pay and Debit pay Details has been updated");
            Task_manager.extrapayC.setSelected(true);
            return tot;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

    public boolean checkDataAvailability(String table_name, String table_column_giving1, String row_element1, String table_column_giving2, String row_element2, String table_column_need) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        
        try {
             ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving1 + " LIKE '" + row_element1 + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'");
            while (query.next()) {
               // s = query.getString(table_column_need);
                return true;
            }
            query.close();
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    public boolean updateDatabase(String table_name, String table_column_giving, Object row_element, String table_column_giving2, Object row_element2, String table_column_need, Object update_element) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            dbm.insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' WHERE " + table_column_giving + "='" + row_element + "' AND " + table_column_giving2 + "='" + row_element2 + "'");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }

    public int[] active_staff_codes() {
        int count = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_staff_salary_info WHERE active LIKE '" + 1 + "'");
            while (query.next()) {
                count++;
            }
             query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int arr[] = new int[count];
        int i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_staff_salary_info WHERE active LIKE '" + 1 + "'");
            while (query.next()) {
                arr[i] = query.getInt("code");
                i++;
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }

    public void staff_fill(String table_name) {

        int arr[] = active_staff_codes();
        int length = arr.length;
        int i = 0;
        for (i = 0; i < length; i++) {

            Task_manager.staffdetailsP.setValue((100 * i) / length + 1);
            try {
                ResultSet query = dbm.query("SELECT * FROM prcr_staff_salary_info WHERE code LIKE '" + arr[i] + "' ");
                while (query.next()) {

                    dbm.updateDatabase(table_name, "code", arr[i], "active", 1);
                   // dbm.updateDatabase(table_name, "code", arr[i],"pay_slip",payslip);
                    dbm.updateDatabase(table_name, "code", arr[i], "normal_pay", query.getDouble("basic_salary"));
                    dbm.updateDatabase(table_name, "code", arr[i], "ot_before_hours", query.getDouble("ot_hours"));
                    dbm.updateDatabase(table_name, "code", arr[i], "ot_before_amount", query.getDouble("ot_pay"));
                    dbm.updateDatabase(table_name, "code", arr[i], "ot_after_hours", query.getDouble("allowance1"));
                    dbm.updateDatabase(table_name, "code", arr[i], "ot_after_amount", query.getDouble("allowance2"));
                    dbm.updateDatabase(table_name, "code", arr[i], "incentive1", query.getDouble("Incentive1"));
                    dbm.updateDatabase(table_name, "code", arr[i], "incentive2", query.getDouble("Incentive2"));
                    dbm.updateDatabase(table_name, "code", arr[i], "extra_pay_may", query.getDouble("allowance3"));
                    dbm.updateDatabase(table_name, "code", arr[i], "extra_pay_cash", query.getDouble("Extra1"));
                    dbm.updateDatabase(table_name, "code", arr[i], "extra_pay_overkilos", query.getDouble("Extra2"));

                }
                query.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Task_manager.staffdetailsP.setValue(100);
    }
}
