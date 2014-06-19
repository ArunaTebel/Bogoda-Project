/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_cashadvance_edit_class {
    String date;
    String tableName;
    String month;
    String year;
    int normaldays;
    int sundays;
    double cash;
    double otnight;
    int empCode;
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    
     public void Set_year(String year) {
        this.year = year;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setTableName(){//date should be set to get the table name
        
        StringBuilder sbdate=new StringBuilder(date.toString());
        sbdate.setCharAt(4, '_');
        sbdate.setCharAt(7, '_');
        String newDate=sbdate.toString().substring(0,7);
        this.tableName="pr_workdata_"+newDate;
    }
    
     public void setCashInMonthlyTable(int entry){//set the tableName,employCode before call this
        
            
        
            cash=Double.parseDouble(dbm.checknReturnData(tableName,"code",empCode,"salary_adv"));
            double edt=Double.parseDouble(dbm.checknReturnData("prcr_cash_advance_book", "entry", entry, "amount"));
            cash=cash-edt;
            dbm.updateDatabase(tableName, "code", empCode, "salary_adv", cash);
            
        
    }
     
     public void setEmpcode(int entry){
       empCode=Integer.parseInt(dbm.checknReturnData("prcr_cash_advance_book", "entry", entry, "code"));
    }
}
