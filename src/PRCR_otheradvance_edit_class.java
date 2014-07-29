/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_otheradvance_edit_class {
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
        
            
        
            
            
            double amount;
            if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("TEA")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "tea"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "tea", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FESTIVAL")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "fest_adv"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "fest_adv", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FOODSTUFFS")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "food"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "food", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("LOAN")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "loan"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "loan", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("CEB")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "ceb"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "ceb", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("TEACHER")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "teacher"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "teacher", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("CHEMICAL")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "chemical"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "chemical", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("PAYSLIP")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "pay_slip"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "pay_slip", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FINE")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "fine"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "fine", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("MEALS")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "meals"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "meals", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("PENSION")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "pension"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "pension", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("WELFARE")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "welfare"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "welfare", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("KOVIL")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "kovil"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "kovil", amount);
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("OTHER_1")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "new_1"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "new_1", amount);
            }
              else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("OTHER_2")){
                amount=Double.parseDouble(dbm.checknReturnData(tableName, "code", empCode, "new_2"));
                double edt=Double.parseDouble(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount"));
                amount=amount-edt;
                dbm.updateDatabase(tableName, "code", empCode, "new_2", amount);
            }
    }
     
     public void setEmpcode(int entry){
         System.err.println(entry);
         try{
       empCode=Integer.parseInt(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "code"));
             System.out.println(empCode);
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
}
