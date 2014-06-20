
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
    DatabaseManager dbm=DatabaseManager.getDbCon();
    
    
    public void CreateReport(String division,String st){
    
        createNewDatabaseTableforAmalgamation();
        
       
        int columnsize = getColumnsize("workcode_details", "code");

        
        String workCode[] = new String[columnsize];
        String Section[] = new String[columnsize];
        int j = 0;

        workCode = getStringArray("workcode_details", "code");
        Section = getStringArray("workcode_details", "work");
       // ((DefaultTableModel) jTable1.getModel()).setNumRows(columnsize + 2);
       // ClearTable(columnsize + 2);
        double normaldays = 0;
        double sundays = 0;
        double otnighthrs = 0;
        double otdayhrs = 0;
        double normalDaysrate = Integer.parseInt(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "normalday_rate"));
        double sundayrate = Integer.parseInt(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "sunday_rate"));
        double otDayrate = Integer.parseInt(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "otrate_before"));
        double otNightRate = Integer.parseInt(dbm.checknReturnData("checkroll_pay_info", "checkroll", 1, "otrate_after"));

        double workdaysT = 0;
        double workdayspayT = 0;
        double othoursT = 0;
        double othourspayT = 0;
        double coinsT = 0;
        double grandtotalT = 0;

        /*   int k=checknReturnNoOfData("prcr_checkroll_workentry", "date", "2014-03",
         "division", "BG", "work_code", "ABVF", "normalday_or_sunday", "n");
         System.out.println(k);*/
        
        
        
       j=0;
        
        for (int i = 0; i < columnsize; i++) {
           

            normaldays = checknReturnNoOfData("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "normalday_or_sunday", "n");

            sundays = checknReturnNoOfData("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "normalday_or_sunday", "s");
            otdayhrs = checknReturnTotal("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "ot_day");
            otnighthrs = checknReturnTotal("prcr_checkroll_workentry", "date", st,
                    "division", division, "work_code", workCode[i], "ot_night");

            if (normaldays != 0 || sundays != 0 || otdayhrs != 0 || otnighthrs != 0) {

                
                workdaysT = workdaysT + (normaldays + sundays);
                
                workdayspayT = workdayspayT + (normaldays * normalDaysrate + sundays * sundayrate);

               
                othoursT = othoursT + (otdayhrs + otnighthrs);
                
                othourspayT = othourspayT + (otdayhrs * otDayrate + otnighthrs * otNightRate);

                
                grandtotalT = grandtotalT + (normaldays * normalDaysrate + sundays * sundayrate + otdayhrs * otDayrate + otnighthrs * otNightRate);
                
                //write data in to database table for amalgamation to generate reports
                 try {
                     double workdayss=normaldays + sundays;
                     double workdayspayy=normaldays * normalDaysrate + sundays * sundayrate;
                     double othourss=otdayhrs + otnighthrs;
                     double othourspayy=otdayhrs * otDayrate + otnighthrs * otNightRate;
                     double grandtotall=normaldays * normalDaysrate + sundays * sundayrate + otdayhrs * otDayrate + otnighthrs * otNightRate;
                    dbm.insert("INSERT INTO prcr_checkroll_amalgamation_report(work_code,section,work_days,work_days_pay,ot_hours,ot_hours_pay,grand_total) VALUES('" + workCode[i]+ "','" + Section[i] + "','" + workdayss + "','" + workdayspayy + "','" + othourss + "','" + othourspayy + "','"+grandtotall+"')");
                } catch (SQLException ex) {
                     System.out.println("error-couldnt write data in to amalgamation database table");
                }
                

            }
        }

    
    
    }
    
    
    
    public void createNewDatabaseTableforAmalgamation(){
       
     try {
         if(dbm.TableExistence("prcr_checkroll_amalgamation_report")){
         dbm.DeleteTable("prcr_checkroll_amalgamation_report");
         dbm.insert("DROP TABLE prcr_checkroll_amalgamation_report");}
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
                if (query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode) && query.getString(column_need_to_check).equals(element_to_check)) {
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
    public double checknReturnTotal(String table_name, String date_column, String st, String divCodeColumn, String divCode, String workCodeColumn, String workCode, String column_need_to_get_total) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if (query.getString(date_column).substring(0, 7).equals(st) && query.getString(divCodeColumn).equals(divCode) && query.getString(workCodeColumn).equals(workCode)) {
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
    
}