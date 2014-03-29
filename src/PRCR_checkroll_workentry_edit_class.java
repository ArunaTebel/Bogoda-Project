
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_checkroll_workentry_edit_class {
    String date;
    String tableName;
    String month;
    String year;
    int normaldays;
    int sundays;
    double otday;
    double otnight;
    int empCode;
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();
    
public PRCR_checkroll_workentry_edit_class(){
    this.date=null;
    this.tableName=null;
    this.month=null;
    this.year=null;
    this.normaldays=0;
    this.sundays=0;
    this.otday=0;
    this.otnight=0;
    this.empCode=0;
    
   }
    
   
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
    public void setNormalDays(int entry){//set the tableName,employCode before call this
        if(dbm.checknReturnData("prcr_checkroll_workentry", "entry", entry, "normalday_or_sunday").equals("n")){
            normaldays=Integer.parseInt(dbm.checknReturnData(tableName,"code",empCode,"normal_days"));
            normaldays=normaldays-1;
            dbm.updateDatabase(tableName, "code", empCode, "normal_days", normaldays);
        }else{
            sundays=Integer.parseInt(dbm.checknReturnData(tableName,"code",empCode,"sundays"));
            sundays=sundays-1;
            dbm.updateDatabase(tableName, "code", empCode, "sundays", sundays);
        }
        
    }
    public void setSundays(int sundays){
        this.sundays=sundays;
    }
    public void setOtday(int entry){
        otday=Integer.parseInt(dbm.checknReturnData(tableName,"code",empCode,"ot_before_hours"));
        double hours=Double.parseDouble(dbm.checknReturnData("prcr_checkroll_workentry", "entry", entry, "ot_day"));
        otday=otday-hours;
        dbm.updateDatabase(tableName, "code", empCode, "ot_before_hours", otday);
        
    }
    public void setOtNight(int entry){
        otnight=Integer.parseInt(dbm.checknReturnData(tableName,"code", empCode,"ot_after_hours"));
        double hours=Double.parseDouble(dbm.checknReturnData("prcr_checkroll_workentry", "entry", entry, "ot_night"));
        otnight=otnight-hours;
        dbm.updateDatabase(tableName, "code", empCode, "ot_after_hours", otnight);
    }
    public void setEmpcode(int entry){
       empCode=Integer.parseInt(dbm.checknReturnData("prcr_checkroll_workentry", "entry", entry, "emp_code"));
    }
    
    public String getDate(){return date;
}
    public String getTableName(){return tableName;
}
    public int getNormalDays(){return normaldays;
}
    public int getSundays(){return sundays;
}
    public double getOtday(){return otday;
}
    public double getOtNight(){return otnight;
}
    public int getEmpcode(){return empCode;
}
    
    
}
