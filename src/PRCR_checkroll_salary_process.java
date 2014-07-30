
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_checkroll_salary_process {
    String month;
    String year;
    String st;
    String division;
    int reg;
    // private int employCode;
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    
    public PRCR_checkroll_salary_process(){
    
     this.month = null;
        this.year = null;
        this.st = null;
        this.division=null;
        this.reg=0;
    }
    
     public void setDivision(String div){
        this.division=div;
    }
    public void setReg(String register){
        if(register=="Register"){
        reg=1;
        }else{
        reg=0;  //casual
        }
        
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

    public void processCheckrollSalary(){
    if(division.equals("STAFF")){
        PRCR_Staff_lSallaryCal scal=new PRCR_Staff_lSallaryCal();
     int columnSize = 0;
        columnSize=checknReturnNumberOfEntriesForNoteAnalysis("pr_workdata_"+st, "register_or_casual", reg, "division",division,"active","1","code");
        
        System.out.println("colmnsiz"+columnSize);

        int array[] = new int[columnSize];
       
        array=checknReturnIntArrayForNoteAnalysis("pr_workdata_"+st, "register_or_casual", reg, "division",division,"active","1","code");
        
        scal.setString(st);
        scal.setDivision(division);
        
                Task_manager.salarycalL.setText("Salary is being calculated for division    "+division+"("+((reg==1)?"Register":"Casual")+")");
        for (int i = 0; i < columnSize; i++) {
            Task_manager.salarycaloverallP.setValue(((PRCR_Checkroll_Monthly_workdata_database_update_class.salarycalprogressbar+1)*100)/(PRCR_Checkroll_Monthly_workdata_database_update_class.columnsize+1));
           PRCR_Checkroll_Monthly_workdata_database_update_class.salarycalprogressbar++;
            Task_manager.salaryCalP.setValue((100*(i+1))/columnSize);
            
            scal.setEmployCode(array[i]);
           
            scal.getFinalSalary();//Calculate final salary and update all the fields in workdata table
           
        }
    
    
    
    
    
    }else{
        CheckrollSallaryCal abcd = new CheckrollSallaryCal();
        int columnSize = 0;
        columnSize=checknReturnNumberOfEntriesForNoteAnalysis("pr_workdata_"+st, "register_or_casual", reg, "division",division,"active","1","code");
        
        System.out.println("colmnsiz"+columnSize);

        int array[] = new int[columnSize];
       
        array=checknReturnIntArrayForNoteAnalysis("pr_workdata_"+st, "register_or_casual", reg, "division",division,"active","1","code");
        
        abcd.setString(st);
        abcd.setDivision(division);
        
        abcd.setMargin();
        Task_manager.salarycalL.setText("Salary is being calculated for division    "+division+"("+((reg==1)?"Register":"Casual")+")");
        for (int i = 0; i < columnSize; i++) {
            Task_manager.salarycaloverallP.setValue(((PRCR_Checkroll_Monthly_workdata_database_update_class.salarycalprogressbar+1)*100)/(PRCR_Checkroll_Monthly_workdata_database_update_class.columnsize+1));
           PRCR_Checkroll_Monthly_workdata_database_update_class.salarycalprogressbar++;
            Task_manager.salaryCalP.setValue((100*(i+1))/columnSize);
            
            abcd.setEmployCode(array[i]);
           
            abcd.getFinalSalary();//Calculate final salary and update all the fields in workdata table
           
        }
    }
    
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
    public int[] checknReturnIntArrayForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2,String table_column_giving3, Object row_element3, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count=0;
        int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2,table_column_giving3, row_element3,table_column_need);
        int[] arr = new int[num];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "' AND "+table_column_giving3+" LIKE '"+row_element3+"'");

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
    
    
      public double[] getDoubleArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            double[] array = new double[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getDouble(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }
//      public double[] checknReturnDoubleArrayForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
//        DatabaseManager dbm = DatabaseManager.getDbCon();
//        int count=0;
//        int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2,table_column_need);
//        double[] arr = new double[num];
//        try {
//            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
//            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");
//
//            while (query.next()) {
//                arr[count]=query.getDouble(table_column_need);
//                count++;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            //return ""+ex.getErrorCode();            
//        }
//        return arr;
//    }
    
      
//used in earlier version
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

   //used to get the number of codes in the "code" column where column "register_or_casual"=1 and "division"=BG  
    public int checknReturnNumberOfEntriesForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_giving3, Object row_element3, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "' AND "+table_column_giving3+" LIKE '"+row_element3+"'");

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
