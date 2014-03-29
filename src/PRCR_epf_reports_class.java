
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_epf_reports_class {
    String month;
    String year;
    String st;
    String division;
     int columnSize = 0;
    
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    
    public PRCR_epf_reports_class(){
    this.month = null;
        this.year = null;
        this.st = null;
        this.division=null;
    
    }
    
    public void setDivision(String div){
        this.division=div;
    }
    
     public void Set_month(String month) {
        this.month = month;
        if (month_num.return_index(month) < 10) {
            st = year + "_0" + month_num.return_index(month);
        } else {
            st = year + "_" + month_num.return_index(month);
        }
        System.out.println("st-"+st);
    }

    public void Set_year(String year) {
        this.year = year;
    }
    
    public void generateCheckrollReport(){
         DeleteTable("prcr_cr_epf_temp_report");
        CheckrollSallaryCal abc = new CheckrollSallaryCal();
       
       // columnSize = getColumnsize("pr_workdata_"+st, "code");
        columnSize=checknReturnNumberOfEntriesForNoteAnalysis("pr_workdata_"+st, "register_or_casual", 1, "division",division,"code");
        
        System.out.println("colmnsiz"+columnSize);

        int array[] = new int[columnSize];
       double total_pay[] = new double[columnSize];
       int epf_no[]=new int[columnSize];
       double epf10[]=new double[columnSize];
       double epf12[]=new double[columnSize];
       double etf[]=new double[columnSize];
       double total_epf[]=new double[columnSize];
       
       //array = getIntArray("pr_workdata_"+st, "code");
        array=checknReturnIntArrayForNoteAnalysis("pr_workdata_"+st, "register_or_casual", 1 , "division",division,"code");
        
        String[] datearray=new String[2];   //get the current year and month
        String currentSt=null;
        datearray=get_today_day();
        currentSt=datearray[0]+"_"+datearray[1];
        if(st.equals(currentSt)){                 //if current month's report is needed calculate the data using checkrollsalarycal,previous month's report is needed get the data from database tables
       
            abc.setString(st);                     //set the st of abc object
            for (int i = 0; i < columnSize; i++) {
            System.out.println(array[i]);
            abc.setEmployCode(array[i]);
            total_pay[i] = abc.getTotalBasicSallary();
            epf10[i]=abc.getEPFContribution();
            epf12[i]=abc.getEPFContribution2();
            
            total_epf[i]=epf10[i]+epf12[i];
            
            epf_no[i]=Integer.parseInt(dbm.checknReturnData("personal_info", "code", array[i], "epf_no"));
                try {
                    
                    dbm.insert("INSERT INTO prcr_cr_epf_temp_report(code,epf_no,total_pay,epf10,epf12,total_epf,etf) VALUES('" + array[i] + "','" + epf_no[i] + "','"+total_pay[i]+"','"+epf10[i]+"','"+epf12[i]+"','"+total_epf[i]+"','"+etf[i]+"')");
                } catch (SQLException ex) {
                    Logger.getLogger(PRCR_epf_reports_class.class.getName()).log(Level.SEVERE, null, ex);
                }
            

        }
        }else{
            for(int i = 0; i < columnSize; i++){
                total_pay[i] = Double.parseDouble(dbm.checknReturnData("pr_workdata_"+st, "code", array[i], "total_pay"));
            epf10[i]=Double.parseDouble(dbm.checknReturnData("pr_workdata_"+st, "code", array[i], "epf10"));
            epf12[i]=Double.parseDouble(dbm.checknReturnData("pr_workdata_"+st, "code", array[i], "epf12"));
            
            total_epf[i]=epf10[i]+epf12[i];
            
            epf_no[i]=Integer.parseInt(dbm.checknReturnData("personal_info", "code", array[i], "epf_no"));
            
            
            try {
                   
                    dbm.insert("INSERT INTO prcr_cr_epf_temp_report(code,epf_no,total_pay,epf10,epf12,total_epf,etf) VALUES('" + array[i] + "','" + epf_no[i] + "','"+total_pay[i]+"','"+epf10[i]+"','"+epf12[i]+"','"+total_epf[i]+"','"+etf[i]+"')");
                } catch (SQLException ex) {
                    Logger.getLogger(PRCR_epf_reports_class.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            
        }
    }
    
    public void setCheckrollTable() {
        
    }
    
   public void DeleteTable(String table_name) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("DELETE FROM " + table_name + " ");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
    public String getString(){
        return st;
    }
    public String getDivision(){
        return division;
    }
    
  public String[] get_today_day() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
         String month= null;
        String[] array = new String[3];
        array = dateNow.split("-");
        return array;
    }
     
    
    public int[] checknReturnIntArrayForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count=0;
        int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2,table_column_need);
        int[] arr = new int[num];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                arr[count]=query.getInt(table_column_need);
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return arr;
    }
    public int checknReturnNumberOfEntriesForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return count;
    }
}
