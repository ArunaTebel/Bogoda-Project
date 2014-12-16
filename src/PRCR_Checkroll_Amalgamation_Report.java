
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class PRCR_Checkroll_Amalgamation_Report {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public void CreateReport(String division, String st) {

        createNewDatabaseTableforAmalgamation();

        /*  int columnsize = getColumnsize("workcode_details", "code");

        
         String workCode[] = new String[columnsize];
         String Section[] = new String[columnsize]; */
        String workCode[];
        String Section[];

        int j = 0;

        workCode = getStringArray("workcode_details", "code");
        Section = getStringArray("workcode_details", "work");

        int columnsize = workCode.length;
        // ((DefaultTableModel) jTable1.getModel()).setNumRows(columnsize + 2);
        // ClearTable(columnsize + 2);
        double normaldays = 0;
        double sundays = 0;
        double otnighthrs = 0;
        double otdayhrs = 0;
        double normalDaysrate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "normalday_rate"));
        double sundayrate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "sunday_rate"));
        double otDayrate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "otrate_before"));
        double otNightRate = Double.parseDouble(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "otrate_after"));

        double workdaysT = 0;
        double workdayspayT = 0;
        double othoursT = 0;
        double othourspayT = 0;
        double coinsT = 0;
        double grandtotalT = 0;
        double workdayss = 0;
        double workdayspayy = 0;
        double othourss = 0;
        double othourspayy = 0;
        //double grandtotall=normaldays * normalDaysrate + sundays * sundayrate + otdayhrs * otDayrate + otnighthrs * otNightRate;
        double grandtotall = 0;

        String workdatast = st.replace("-", "_");

        double normaldaysstotal = checknReturnIntTotal("pr_workdata_" + workdatast, "division", division, "normal_days");
   
        double sundaysstotal = checknReturnIntTotal("pr_workdata_" + workdatast, "division", division, "sundays");
     
        double grosspay = checknReturnDoubleTotal("pr_workdata_" + workdatast, "division", division, "gross_pay");
       
        double otbframnt = checknReturnDoubleTotal("pr_workdata_" + workdatast, "division", division, "ot_before_amount");
        double otaftramnt = checknReturnDoubleTotal("pr_workdata_" + workdatast, "division", division, "ot_after_amount");
        
        double otbfrhrsttotal = checknReturnDoubleTotal("pr_workdata_" + workdatast, "division", division, "ot_before_hours");
        double otaftrhrstotal = checknReturnDoubleTotal("pr_workdata_" + workdatast, "division", division, "ot_after_hours");

        double dayRate = (grosspay-otbframnt-otaftramnt) / (normaldaysstotal + sundaysstotal);
       // double otRate=(otbframnt+otaftramnt)/(otbfrhrsttotal+otaftrhrstotal);
        //System.out.println("Ot rate-"+otRate+","+otbfrhrsttotal+","+otaftrhrstotal);
        System.out.println("gross pay-"+grosspay);
        System.out.println("otbframnt-"+otbframnt);
        System.out.println("otaftramnt-"+otaftramnt);
        System.out.println("work days-"+normaldaysstotal+sundaysstotal);
        

        j = 0;
        System.out.println(columnsize);

        for (int i = 0; i < columnsize; i++) {
   
            System.out.println(workCode[i] + "--------->" + i);
            normaldays = checknReturnNoOfData("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "normalday_or_sunday", "n");

            sundays = checknReturnNoOfData("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "normalday_or_sunday", "s");
            otdayhrs = checknReturnTotal("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "ot_day");
            otnighthrs = checknReturnTotal("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "ot_night");

            if (normaldays != 0 || sundays != 0 || otdayhrs != 0 || otnighthrs != 0) {

               
                System.out.println(workCode[i] + "---------" + normaldays + "------------" + sundays);
             
                //write data in to database table for amalgamation to generate reports
                workdayss = normaldays + sundays;
                 workdaysT=workdaysT+workdayss;
                
                 workdayspayy = workdayss * dayRate;
                workdayspayT=workdayspayT+workdayspayy;
                
                othourss = otdayhrs + otnighthrs;
                othoursT=othoursT+othourss;
              othourspayy = otdayhrs * otDayrate + otnighthrs * otNightRate;
              // othourspayy=othourss*otRate;
               othourspayT=othourspayT+othourspayy;
                

                //double grandtotall=normaldays * normalDaysrate + sundays * sundayrate + otdayhrs * otDayrate + otnighthrs * otNightRate;
                grandtotall = workdayspayy + othourspayy;
                grandtotalT=grandtotalT+grandtotall;
                try {
                    dbm.insert("INSERT INTO prcr_checkroll_amalgamation_report(work_code,section,work_days,work_days_pay,ot_hours,ot_hours_pay,grand_total) VALUES('" + workCode[i] + "','" + Section[i] + "','" + workdayss + "','" + workdayspayy + "','" + othourss + "','" + othourspayy + "','" + grandtotall + "')");
                } catch (SQLException ex) {
                    System.out.println("error-couldnt write data in to amalgamation database table"+ ex.getMessage());
                }

            }
        }
        
         try {
                    dbm.insert("INSERT INTO prcr_checkroll_amalgamation_report(work_code,section,work_days,work_days_pay,ot_hours,ot_hours_pay,grand_total) VALUES('" + " " + "','" + "TOTAL     " + "','" + workdaysT + "','" + workdayspayT + "','" + othoursT + "','" + othourspayT + "','" + grandtotalT + "')");
                } catch (SQLException ex) {
                    System.out.println("error-couldnt write data in to amalgamation database table"+ ex.getMessage());
                }

    }

    public double checknReturnIntTotal(String table_name, String division_column, String division, String column_need_to_get_total) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int tot = 0;

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE active LIKE '1'");

            while (query.next()) {

                if (query.getString(division_column).equals(division)) {
                    System.out.println(tot);
                    tot = tot + query.getInt(column_need_to_get_total);
                    System.out.println(tot);
                }
            }
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("SQL error-Some Normal days may be null ");
        }

        return tot;
    }

    public double checknReturnDoubleTotal(String table_name, String division_column, String division, String column_need_to_get_total) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE active LIKE '1'");
            while (query.next()) {

                if (query.getString(division_column).equals(division)) {

                    tot = tot + query.getDouble(column_need_to_get_total);

                }
            }
            query.close();
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("SQL error-Some Amounts may be null ");

        }
        return tot;
    }

    public void createNewDatabaseTableforAmalgamation() {

        try {
            if (dbm.TableExistence("prcr_checkroll_amalgamation_report")) {
                dbm.DeleteTable("prcr_checkroll_amalgamation_report");
                dbm.insert("DROP TABLE prcr_checkroll_amalgamation_report");
            }
            //use new_1 and new_2 if any other deduction type is needd to be added
            dbm.insert("CREATE TABLE prcr_checkroll_amalgamation_report(work_code VARCHAR(30),"
                    + "section VARCHAR(50)," + "work_days DOUBLE,"
                    + "work_days_pay DOUBLE," + "ot_hours DOUBLE," + "ot_hours_pay DOUBLE,"
                    + "coins DOUBLE," + "grand_total DOUBLE);");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
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

    public int checknReturnNoOfData(String table_name, String date_column, String st, String divCodeColumn, String divCode, String workCodeColumn, String workCode, String column_need_to_check, Object element_to_check) {

        String start = st + "-01";
        String end = st + "-31";
        int count = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + divCodeColumn + " LIKE'" + divCode + "' AND " + workCodeColumn + " LIKE '" + workCode + "' AND " + column_need_to_check + " LIKE '" + element_to_check + "' AND " + date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
//                 System.out.println(query.getString(date_column).substring(0,7)+"  "+st);
//                 System.out.println(query.getString(divCodeColumn)+" "+divCode);
//                 System.out.println(query.getString(workCodeColumn)+"  "+workCode);
//                 System.out.println(query.getString(column_need_to_check)+"  "+element_to_check);
//                 System.out.println(query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode) && query.getString(column_need_to_check).equals(element_to_check));
//                 
//System.out.println("gssgsdg   gsdg "+division_jc.getSelectedItem().toString());
                //     if (query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode) && query.getString(column_need_to_check).equals(element_to_check)) {
                count++;

                //  }
            }
            query.close();
            return count;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return count;
    }

    // column_need_to_get_total is Either Day OT hour column or Night OT hour column.. All other parameters are similar to the above method
    public double checknReturnTotal(String table_name, String date_column, String st, String divCodeColumn, String divCode, String workCodeColumn, String workCode, String column_need_to_get_total) {

        String start = st + "-01";
        String end = st + "-31";

        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + divCodeColumn + " LIKE'" + divCode + "' AND " + workCodeColumn + " LIKE '" + workCode + "' AND " + date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                //if (query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode)) {
                tot = tot + query.getDouble(column_need_to_get_total);
                //}
            }
            query.close();
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return tot;
    }

    public String[] getStringArray(String table_name, String column_name) {

        int count = 0;

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
            query.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;

        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            query.close();
        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

}
