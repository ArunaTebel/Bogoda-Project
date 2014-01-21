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
        System.out.println(this.date);
    }
    public void setWorkCode(String work_code){
        this.work_code = work_code;
        System.out.println(this.work_code);
    }
    public void setDivisionCode(String division_code){
        this.division_code = division_code;
        System.out.println(this.division_code);
    }
    
    public String searchAndReturn(){
        /*String codes[] = dbm.search_PRCR(this.date, "work_code", "division", this.work_code, this.division_code, "emp_code"); 
        int i;
        String s = " ";
        for(i=0;i<codes.length;i++){
            s = s + codes[i] + " ";
        }
        return s;*/
        return dbm.checknReturnData(this.date, "work_code", this.work_code, "emp_code");
    }
}
