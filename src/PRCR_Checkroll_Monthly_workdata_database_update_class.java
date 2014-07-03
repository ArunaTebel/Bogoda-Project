
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    String st;
    public static int columnsize;
    public static int salarycalprogressbar = 0;

    public PRCR_Checkroll_Monthly_workdata_database_update_class(String st) {

        this.st = st;
    }
//    public void UpdateMonthlyWorkdataDatabase(String st) throws SQLException{
//    
//     //   createNewDatabaseTableforAmalgamation();
//       
//        //CreateNewMonthTable(st);
//        int active=0;int inactive=0;
//        int columnsize = getColumnsize("pr_workdata_"+st, "code");
//
//        //PRCR_test_jframe.MessageTex.append("Number of Workers:"+columnsize);
//        System.out.println("workers"+columnsize);
//        int Code[] = new int[columnsize];
//        String Section[] = new String[columnsize];
//        int j = 0;
//
//        Code = dbm.getArray("pr_workdata_"+st, "code");
//       // Section = getStringArray("workcode_details", "work");
//       // ((DefaultTableModel) jTable1.getModel()).setNumRows(columnsize + 2);
//       // ClearTable(columnsize + 2);
//        double normaldays = 0;
//        double sundays = 0;
//        double otnighthrs = 0;
//        double otdayhrs = 0;
//        
//
//        double workdaysT = 0;
//        double workdayspayT = 0;
//        double othoursT = 0;
//        double othourspayT = 0;
//        double coinsT = 0;
//        double grandtotalT = 0;
//
//        /*   int k=checknReturnNoOfData("prcr_checkroll_workentry", "date", "2014-03",
//         "division", "BG", "work_code", "ABVF", "normalday_or_sunday", "n");
//         System.out.println(k);*/
//        
//        
//        
//       j=0;
//       // PRCR_test_jframe.workdaysProgressBar.setValue(0);
//       String workentryst=st.replace("_","-");
//        System.out.println(workentryst);
//        for (int i = 0; i < columnsize; i++) {
//           PRCR_test_jframe.newmonthP.setValue((100*i)/columnsize);
//           normaldays=sundays=otdayhrs=otnighthrs=0;
//
//            normaldays = checknReturnNoOfData("prcr_checkroll_workentry", "date", workentryst,
//                   "emp_code", Code[i], "normalday_or_sunday", "n");
//
//            sundays = checknReturnNoOfData("prcr_checkroll_workentry", "date", workentryst,
//                    "emp_code", Code[i], "normalday_or_sunday", "s");
//            otdayhrs = checknReturnTotal("prcr_checkroll_workentry", "date", workentryst,
//                     "emp_code", Code[i], "ot_day");
//            otnighthrs = checknReturnTotal("prcr_checkroll_workentry", "date", workentryst,
//                     "emp_code", Code[i], "ot_night");
//            try{
//            dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"normal_days", normaldays);
//            dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"sundays", sundays);
//            dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"ot_before_hours", otdayhrs);
//            dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"ot_after_hours", otnighthrs);
//            }catch(Exception e){
//                System.out.println("Error in sql "+e.getMessage());
//            }
//            
//            if (normaldays == 0 && sundays == 0 && otdayhrs == 0 && otnighthrs == 0) {
//
//             dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"active",0);   
//             inactive++;
//            }
//            else{
//             dbm.updateDatabase("pr_workdata_"+st, "code",Code[i],"active",1);  
//             active++;
//                System.out.println("active"+Code[i]);
//            }
//           // PRCR_test_jframe.MessageTex.append("Number of Active Workers:"+active+"Inactive Workers"+inactive);
//              
//        }
//
//    
//    
//    }

    public void CreateNewMonthTable(String yr_mnth) throws SQLException {

        PRCR_test_jframe.newmonthL.setText("New Month Table is being created");
        PRCR_test_jframe.newmonthC.setSelected(false);
        PRCR_test_jframe.newmonthP.setValue(0);

        DatabaseManager dbm = DatabaseManager.getDbCon();

        if (dbm.TableExistence("pr_workdata_" + yr_mnth)) {
            dbm.DeleteTable("pr_workdata_" + yr_mnth);
            dbm.insert("DROP TABLE pr_workdata_" + yr_mnth + "");
        }
        System.out.println("new table is being created");
        try {
            //use new_1 and new_2 if any other deduction type is needd to be added
            dbm.insert("CREATE TABLE pr_workdata_" + yr_mnth + "(code INT,"
                    + "division VARCHAR(15)," + "register_or_casual INT,"
                    + "normal_days INT," + "normal_pay DOUBLE," + "sundays INT,"
                    + "sunday_pay DOUBLE," + "total_pay DOUBLE," + "ot_before_hours INT,"
                    + "ot_before_amount DOUBLE," + "ot_after_hours INT," + "ot_after_amount DOUBLE,"
                    + "incentive1 DOUBLE," + "incentive2 DOUBLE," + "extra_pay DOUBLE,"
                    + "gross_pay DOUBLE," + "tea DOUBLE," + "salary_adv DOUBLE," + "fest_adv DOUBLE,"
                    + "food DOUBLE," + "loan DOUBLE," + "bank DOUBLE," + "epf10 DOUBLE," + "epf12 DOUBLE,"
                    + "total_epf DOUBLE," + "etf DOUBLE," + "ceb DOUBLE," + "teacher DOUBLE," + "chemical DOUBLE,"
                    + "pay_slip DOUBLE," + "fine DOUBLE," + "welfare DOUBLE," + "kovil DOUBLE," + "new_1 DOUBLE," + "new_2 DOUBLE," + "other_ded1 DOUBLE,"
                    + "meals DOUBLE," + "other_ded2 DOUBLE," + "pension DOUBLE,"
                    + "other_ded3 DOUBLE," + "stamp DOUBLE," + "pre_debt DOUBLE," + "total_ded DOUBLE,"
                    + "full_salary DOUBLE," + "coins DOUBLE," + "paid_amount DOUBLE," + "active INT," + "next_month DOUBLE," + "n_5000 INT,"
                    + "n_2000 INT," + "n_1000 INT," + "n_500 INT," + "n_100 INT," + "n_50 INT," + "n_20 INT," + "n_10 INT);");
            //  System.out.println("new table created");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in ex");
        }
        ///DONT DELETE checkroll_personalinfo SQL table
        //copying worker's codes to newly created table
        CopyTable3Columns("checkroll_personalinfo", "code", "division", "register_or_casual", "pr_workdata_" + yr_mnth, "code", "division", "register_or_casual");
        //copy the previous month's 'next_month' amount to 'pre_debt' column in this month
        PRCR_test_jframe.newmonthC.setSelected(true);
        PRCR_test_jframe.newmonthL.setText("New Month Table has been created");

        PRCR_test_jframe.prvdebtsL.setText("Previous Debts are being updated");
        GetPreDebts(yr_mnth);
        PRCR_test_jframe.prvdebtsL.setText("Previous Debts has been updated");
        PRCR_test_jframe.prvdebtsC.setSelected(true);

        System.out.println("table copied");
        JOptionPane.showMessageDialog(null, "New checkroll table is created for this month\n", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    //method to copy the previous month's 'next_month' amount to 'pre_debt' column in this month
    public void GetPreDebts(String yrmnth) {
        System.out.println("in getpre debts");
        String prv_yrmnth = ReturnPrvMnthTableName(yrmnth);
        int no_of_codes = getColumnsize("pr_workdata_" + yrmnth, "code");
        int codes[] = new int[no_of_codes];
        codes = getIntArray("pr_workdata_" + prv_yrmnth, "code");
        double pre_debt_amount = 0;
        for (int i = 0; i < no_of_codes; i++) {
            PRCR_test_jframe.prvdebtsP.setValue((100 * (i + 1)) / no_of_codes);
            try {
                if (dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month") != null) {

                    pre_debt_amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month"));
                    pre_debt_amount = -pre_debt_amount;
                } else {
                    pre_debt_amount = 0;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + " i=" + i);
            }
            dbm.updateDatabase("pr_workdata_" + yrmnth, "code", codes[i], "pre_debt", pre_debt_amount);

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

    public int[] getIntArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public void CopyTable3Columns(String table_name1, String table1_column1, String table1_column2, String table1_column3, String table_name2, String table2_column1, String table2_column2, String table2_column3) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            int i = 0;
            while (query.next()) {

                i++;
                PRCR_test_jframe.newmonthP.setValue((100 * i) / columnsize);
                try {
                    // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + "," + table2_column3 + ") VALUES('" + query.getString(table1_column1) + "','" + query.getString(table1_column2) + "','" + query.getString(table1_column3) + "')");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }
    }


    public void duplicateTable(String original_table_name, String table_copy_name) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            if (dbCon.TableExistence(table_copy_name)) {
                dbCon.insert("DROP TABLE " + table_copy_name + "");
            }
            dbCon.insert("CREATE TABLE " + table_copy_name + " LIKE " + original_table_name + "");
            dbCon.insert("INSERT " + table_copy_name + " SELECT * FROM " + original_table_name + "");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
    // Date column is the column in your database which dates are entered
    // String month is the month you want to check. Give month as a string ex:- 01 for January, 09 for September
    // String column_need_to_check is the column that you need to filter in your case the column that has n's and s's :)
    // Object element is the element you need to check in that column. Ex:- Either "s" or "n"

    public int checknReturnNoOfData(String table_name, String date_column, String st, String workCodeColumn, int Code, String column_need_to_check, Object element_to_check) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

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
    
        public int getActiveWorkersColumnsize(String table_name) {
 System.out.println("in getactive "+table_name);
        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                System.out.println(query.getInt("active")+"\n");
                if(query.getInt("active")==1){
                count++;
                   System.out.println("count "+count);
                }
            }
            return count;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
        //return null;

    }


    @Override
    public void run() {

//      columnsize = getColumnsize("checkroll_personalinfo", "code");//uncomment below lines !
//
//        try {
//            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            CreateNewMonthTable(st);
//        } catch (SQLException ex) {
//            Logger.getLogger(PRCR_Checkroll_Monthly_workdata_database_update_class.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        
        columnsize = getActiveWorkersColumnsize("pr_workdata_" + st);
        
       // columnsize=8;
        int active = 0;
        int inactive = 0;

        //PRCR_test_jframe.MessageTex.append("Number of Workers:"+columnsize);
        System.out.println("workers" + columnsize);
        int Code[] = new int[columnsize];
        String Section[] = new String[columnsize];
        int j = 0;

        Code = dbm.getArray("pr_workdata_" + st, "code");
        // Section = getStringArray("workcode_details", "work");
        // ((DefaultTableModel) jTable1.getModel()).setNumRows(columnsize + 2);
        // ClearTable(columnsize + 2);
      

        //   int k=checknReturnNoOfData("prcr_checkroll_workentry", "date", "2014-03",
        //  "division", "BG", "work_code", "ABVF", "normalday_or_sunday", "n");
        //  System.out.println(
        j = 0;
        // PRCR_test_jframe.workdaysProgressBar.setValue(0);
        String workentryst = st.replace("_", "-");
        // System.out.println(workentryst);
        PRCR_test_jframe.workdetailsL.setText("Work Details are being updated");
        UpdateWorkDetails("prcr_checkroll_workentry", "date", workentryst);
      
        PRCR_test_jframe.workdetailsL.setText("Work Details has been updated");
        PRCR_test_jframe.workdetailsC.setSelected(true);
        updateCashNOtherAdvances("date", workentryst);

        //Salary Calculaion
        PRCR_checkroll_salary_process csp = new PRCR_checkroll_salary_process();
        int nofdivisions = getColumnsize("division_details", "code");
        PRCR_test_jframe.salarycaloverallL.setText("Salary is being Calculated");

        String division[] = new String[nofdivisions];
        String regorcas[] = {"Register", "Casual"};
        division = dbm.getStringArray("division_details", "code");
        for (int i = 0; i < nofdivisions; i++) {

            for (int k = 0; k < 2; k++) {
                csp.setDivision(division[i]);
                csp.setReg(regorcas[k]);

                csp.Set_year(PRCR_test_jframe.year.getText());
                csp.Set_month(PRCR_test_jframe.month.getText());//set the month(ex:2014_03) in the object

                csp.processCheckrollSalary();        // TODO add your handling code here:
                PRCR_test_jframe.MessageTex.append("Salary Calculated for division   " + division[i] + "(" + regorcas[k] + ")" + "\n");

            }
        }
        PRCR_test_jframe.salarycalL.setText("Salary has been calculated for all divisions");

        PRCR_test_jframe.salarycaloverallL.setText("Salary has been Calculated");
        PRCR_test_jframe.salarycaloverallC.setSelected(true);

    }

    public void UpdateWorkDetails(String table_name, String date_column, String st) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if (query.getString(date_column).substring(0, 7).equals(st)) {
                    count++;//get the number of entrys in that month(to display prograssbar)
//
                }

            }
            query = dbm.query("SELECT * FROM " + table_name + "");
            int i = 0;
            int code;
            int normaldays = 0;
            int sundays = 0;
            double otbeforehrs = 0;

            double otafterhrs = 0;
            while (query.next()) {

//                 System.out.println(query.getString(date_column).substring(0,7)+"  "+st);
//                 System.out.println(query.getString(divCodeColumn)+" "+divCode);
//                 System.out.println(query.getString(workCodeColumn)+"  "+workCode);
//                 System.out.println(query.getString(column_need_to_check)+"  "+element_to_check);
//                 System.out.println(query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode) && query.getString(column_need_to_check).equals(element_to_check));
//                 
//System.out.println("gssgsdg   gsdg "+division_jc.getSelectedItem().toString());
//                if (query.getString(date_column).substring(0, 7).equals(st) && query.getInt(workCodeColumn) == Code && query.getString(column_need_to_check).equals(element_to_check)) {
//                    count++;
//
//                }
                if (query.getString(date_column).substring(0, 7).equals(st)) {
                    code = query.getInt("emp_code");
                     dbm.updateDatabase("pr_workdata_"+this.st, "code",code,"active",1); 

                    if (query.getString("normalday_or_sunday").equals("n")) {

                        if (dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "normal_days") != null) {
                            normaldays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "normal_days"));
                        } else {
                            normaldays = 0;
                        }
                        normaldays = normaldays + 1;
                        dbm.updateDatabase("pr_workdata_" + this.st, "code", code, "normal_days", normaldays);

                    } else if (query.getString("normalday_or_sunday").equals("s")) {

                        if (dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "sundays") != null) {
                            sundays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "sundays"));
                        } else {
                            sundays = 0;
                        }

                        sundays = sundays + 1;
                        dbm.updateDatabase("pr_workdata_" + this.st, "code", code, "sundays", sundays);
                    }

                    if (dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ot_before_hours") != null) {
                        otbeforehrs = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ot_before_hours"));
                    } else {
                        otbeforehrs = 0;
                    }

                    if (dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ot_after_hours") != null) {
                        otafterhrs = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ot_after_hours"));
                    } else {
                        otafterhrs = 0;
                    }

                    otbeforehrs = query.getDouble("ot_day") + otbeforehrs;
                    otafterhrs = query.getDouble("ot_night") + otafterhrs;
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", code, "ot_before_hours", otbeforehrs);
                    dbm.updateDatabase("pr_workdata_" + this.st, "code", code, "ot_after_hours", otafterhrs);

                    System.out.println("code,normaldays,sundays,otbrf,otaftr-" + code + "," + normaldays + "," + sundays + "," + otbeforehrs + "," + otafterhrs);
                }
                PRCR_test_jframe.workdetailsP.setValue((100 * i) / count);
                i++;
            }
            //return count;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        //return count;
    }

    public double updateCashNOtherAdvances(String date_column, String st) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        double tea = 0;
        double fes_adv = 0;
        double cash_advance = 0;
        double food = 0;
        double loan = 0;
        double ceb = 0;
        double teacher = 0;
        double chemical = 0;
        double payslip = 0;
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
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_other_advance_book");
            ResultSet query2 = dbm.query("SELECT * FROM prcr_cash_advance_book");
            PRCR_test_jframe.advanceL.setText("Advances are being updated");

            while (query.next()) {
                if (query.getString(date_column).substring(0, 7).equals(st)) {
                    nofentries1++;
                }
            }
            while (query2.next()) {
                if (query2.getString(date_column).substring(0, 7).equals(st)) {
                    nofentries2++;
                }
            }
            while (query.next()) {
                PRCR_test_jframe.advanceP.setValue((count * 100) / (nofentries1 + nofentries2 + 1));
                count++;
                code = query.getInt("code");
                 dbm.updateDatabase("pr_workdata_"+this.st, "code",code,"active",1); 

                if (query.getString(date_column).substring(0, 7).equals(st)) {

                    tea = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "tea") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "tea")) : 0);
                    tea = tea + ((query.getString("type").equals("TEA")) ? query.getDouble("amount") : 0);

                    fes_adv = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "fest_adv") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "fest_adv")) : 0);
                    fes_adv = fes_adv + ((query.getString("type").equals("FESTIVAL")) ? query.getDouble("amount") : 0);

                    food = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "food") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "food")) : 0);
                    food = food + ((query.getString("type").equals("FOODSTUFFS")) ? query.getDouble("amount") : 0);

                    loan = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "loan") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "loan")) : 0);
                    loan = loan + ((query.getString("type").equals("LOAN")) ? query.getDouble("amount") : 0);

                    ceb = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ceb") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "ceb")) : 0);
                    ceb = ceb + ((query.getString("type").equals("CEB")) ? query.getDouble("amount") : 0);

                    teacher = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "teacher") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "teacher")) : 0);
                    teacher = teacher + ((query.getString("type").equals("TEACHER")) ? query.getDouble("amount") : 0);

                    chemical = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "chemical") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "chemical")) : 0);
                    chemical = chemical + ((query.getString("type").equals("CHEMICAL")) ? query.getDouble("amount") : 0);

                    payslip = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "pay_slip") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "pay_slip")) : 0);
                    payslip = payslip + ((query.getString("type").equals("PAYSLIP")) ? query.getDouble("amount") : 0);

                    fine = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "fine") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "fine")) : 0);
                    fine = fine + ((query.getString("type").equals("FINE")) ? query.getDouble("amount") : 0);

                    meals = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "meals") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "meals")) : 0);
                    meals = meals + ((query.getString("type").equals("MEALS")) ? query.getDouble("amount") : 0);

                    pension = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "pension") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "pension")) : 0);
                    pension = pension + ((query.getString("type").equals("PENSION")) ? query.getDouble("amount") : 0);

                    welfare = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "welfare") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "welfare")) : 0);
                    welfare = welfare + ((query.getString("type").equals("WELFARE")) ? query.getDouble("amount") : 0);

                    kovil = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "kovil") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "kovil")) : 0);
                    kovil = kovil + ((query.getString("type").equals("KOVIL")) ? query.getDouble("amount") : 0);

                    other1 = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "new_1") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "new_1")) : 0);
                    other1 = other1 + ((query.getString("type").equals("OTHER_1")) ? query.getDouble("amount") : 0);

                    other2 = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "new_2") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "new_2")) : 0);
                    other2 = other2 + ((query.getString("type").equals("OTHER_2")) ? query.getDouble("amount") : 0);

                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "tea", tea);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "fest_adv", fes_adv);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "food", food);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "loan", loan);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "ceb", ceb);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "teacher", teacher);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "chemical", chemical);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "pay_slip", payslip);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "fine", fine);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "meals", meals);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "pension", pension);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "welfare", welfare);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "kovil", kovil);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "new_1", other1);
                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "new_2", other2);
                }

            }

            while (query2.next()) {
                count++;
                PRCR_test_jframe.advanceP.setValue((count * 100) / (nofentries1 + nofentries2 + 1));
                code = query2.getInt("code");
                 dbm.updateDatabase("pr_workdata_"+this.st, "code",code,"active",1); 

                if (query2.getString(date_column).substring(0, 7).equals(st)) {

                    cash_advance = ((dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "salary_adv") != null) ? Double.parseDouble(dbm.checknReturnData("pr_workdata_" + this.st, "code", code, "salary_adv")) : 0);
                    cash_advance = cash_advance + query2.getDouble("amount");

                    dbm.updateDatabase("pr_workdata_" + st, "code", code, "salary_adv", tea);

                }

            }

            PRCR_test_jframe.advanceP.setValue(100);
            PRCR_test_jframe.advanceL.setText("Advances has been updated");
            PRCR_test_jframe.advanceC.setSelected(true);
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

}
