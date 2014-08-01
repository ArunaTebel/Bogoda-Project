
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
public class PRCR_Checkroll_Summary_Report {
    
    DatabaseManager dbm=DatabaseManager.getDbCon();
    public void createReport(String st,String division,String regorcas){
    
        createNewDatabaseTable_CheckrollSummaryReport();
        try {
            System.err.println("pr_workdata_"+st);
                double normaldayss= checknReturnIntTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "normal_days") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Normal days"+ "','" + normaldayss + "')");
                double sundayss= checknReturnIntTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "sundays") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Sundays"+ "','" + sundayss + "')");
                double normalpayy= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "normal_pay") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Normal day pay"+ "','" + normalpayy + "')");
                double sundaypayy= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "sunday_pay") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Sunday pay"+ "','" + sundaypayy + "')");
                double totalpayy= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "total_pay") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Total pay"+ "','" + totalpayy + "')");
                double otbrfhrs= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "ot_before_hours") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"OT Before Hours"+ "','" + otbrfhrs+ "')");
                double otbframnt= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "ot_before_amount") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"OT Before pay"+ "','" + otbframnt + "')");
                double otaftrhrs= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "ot_after_hours") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"OT After hours"+ "','" + otaftrhrs + "')");
                double otaftramnt= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "ot_after_amount") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"OT After Amount"+ "','" + otaftramnt + "')");
                double incentive1= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "incentive1") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Incentive 1"+ "','" + incentive1 + "')");
                double incentive2= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "incentive2") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Incentive 2"+ "','" + incentive2 + "')");
                 double coinsbf= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "coinsbf") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Coins BF"+ "','" + coinsbf + "')");
                double grosspay= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "gross_pay") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Gross pay"+ "','" + grosspay + "')");
                double tea= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "tea") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Tea"+ "','" + tea + "')");
                double salaryadv= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "salary_adv") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Salary Advance"+ "','" + salaryadv + "')");
                double festadv= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "fest_adv") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Festival Advance"+ "','" + festadv + "')");
                double food= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "food") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Food"+ "','" + food + "')");
                double loan= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "loan") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Loan"+ "','" + loan + "')");
                double epf10= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "epf10") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"EPF 10"+ "','" + epf10 + "')");
                double epf12= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "epf12") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"EPF 12"+ "','" + epf12 + "')");
                double totepf= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "total_epf") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Total EPF "+ "','" + totepf + "')");        
                double etf= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "etf") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"ETF"+ "','" + etf + "')");
                double ceb= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "ceb") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"CEB"+ "','" + ceb + "')");
                double teacher= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "teacher") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Teacher"+ "','" + teacher + "')");
                double chem= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "chemical") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"CHEMICAL"+ "','" + chem + "')");
                double payslip= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "pay_slip") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Pay slip"+ "','" + payslip + "')");
                double fine= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "fine") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Fines"+ "','" + fine + "')");
                double welf= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "welfare") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Welfare"+ "','" + welf + "')");
                double kovil= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "kovil") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Kovil"+ "','" + kovil + "')");
                double new_1= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "new_1") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"New 1"+ "','" + new_1 + "')");
                double new_2= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "new_2") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"New 2"+ "','" + new_2 + "')");
                double other1= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "other_ded1") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Other Deduction 1"+ "','" + other1 + "')");
                double meals= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "meals") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Meals"+ "','" + meals + "')");
                double other2= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "other_ded2") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Other Deduction 2"+ "','" + other2 + "')");
                double pension= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "pension") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Pension"+ "','" + pension + "')");
                double predebt= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "pre_debt") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Pre Debts"+ "','" + predebt + "')");
                double totalded= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "total_ded") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Total Deduction"+ "','" + totalded + "')");
                double fullsal= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "full_salary") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Full Salary"+ "','" + fullsal + "')");
                double coins= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "coins") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Coins"+ "','" + coins + "')");
                double paid= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "paid_amount") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Paid Amount"+ "','" + paid + "')");
                double nextmnth= checknReturnDoubleTotal("pr_workdata_"+st, "division", division,"register_or_casual",regorcas, "next_month") ;
                dbm.insert("INSERT INTO prcr_checkroll_summary_report(column_1,amount) VALUES('" +"Next Month"+ "','" + nextmnth+ "')");
                
                
                
                
                
                
                
        } catch (SQLException ex) {
                     System.out.println("error-couldnt write data in to checkrollsummaryreport database table");
                }
        
        

    
    }
    
     public void createNewDatabaseTable_CheckrollSummaryReport(){
          try {
         dbm.DeleteTable("prcr_checkroll_summary_report");
            //use new_1 and new_2 if any other deduction type is needd to be added
            dbm.insert("CREATE TABLE prcr_checkroll_summary_report(column_1 VARCHAR(60),"
                    + "amount DOUBLE);");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }
     
     
     
       public double checknReturnDoubleTotal(String table_name, String division_column, String division,String reg_column,String reg,String column_need_to_get_total) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot=0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE "+division_column+" LIKE '"+division+"' AND "+reg_column+" LIKE '"+reg+"'");
            while (query.next()) {
                
               
                  
                    tot=tot+query.getDouble(column_need_to_get_total);
                    
                
            }
            query.close();
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("SQL error-Some Amounts may be null ");
            
        }
        return tot;
    }
    public double checknReturnIntTotal(String table_name, String division_column, String division,String reg_column,String reg,String column_need_to_get_total) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int tot=0;
       
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE "+division_column+" LIKE '"+division+"' AND "+reg_column+" LIKE '"+reg+"'");
          
            while (query.next()) {
                
                if(query.getString(division_column).equals(division) ){
                    System.out.println(tot);
                    tot=tot+query.getInt(column_need_to_get_total);
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
}
