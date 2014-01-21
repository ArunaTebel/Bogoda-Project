/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class PRCR_Search {
    private String date;
    private String work_code;
    private String division_code;
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    
    public PRCR_Search(){
        date = null;
        work_code = null;
        division_code= null;
    }
    
    public void setDate(String day,String month,String year){
        this.date  = "d_" + year + "_" + month + "_" + day;
    }
    public void setWorkCode(String work_code){
        this.work_code = work_code;
    }
    public void setDivisionCode(String division_code){
        this.division_code = division_code;
    }
    
    public String[] searchAndReturn(){
        return dbm.search_PRCR(this.date, "work_code", "division", this.work_code, this.division_code, "emp_code"); 
    }
}
