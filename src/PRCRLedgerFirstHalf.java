
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class PRCRLedgerFirstHalf implements Runnable{
    DatabaseManager dbm=DatabaseManager.getDbCon();
    int[] workCodes;
    int year;
    
    public PRCRLedgerFirstHalf(int year){
       // dbm = new DatabaseManager();
           this.year = year;
            try {
          
            dbm.insert("Truncate prcr_ledger_first_half");
          
        } catch (SQLException e) {
            //Logger.getLogger(PRCRLedgerFirstHalf.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(e.getMessage());
        }
      
    } 
    
    public void getWorkCodes(){
        String table = "checkroll_personalinfo";
        String coloumn = "code";
        workCodes = getArray(table, coloumn);
    }
    
    public void updateWorker(int i){
        
        Report_PRCR_EPF_6Month.epfPrgrsbr.setValue((100*i)/workCodes.length+1);
        int code = workCodes[i];
        int entry = Integer.parseInt(year + "" + code);
        int j;
        String[] months = {"jan", "feb", "mar", "apr", "may", "jun"};
        String table, coloumn = "code";
        String target;
        String destTable = "prcr_ledger_first_half";
        double x, y,active=0;
        double check=1;
        try {
            dbm.insert("INSERT INTO prcr_ledger_first_half(entry,code) VALUES('" + entry + "','" + code + "')");
            
        } catch (SQLException ex) {
           // Logger.getLogger(PRCRLedgerFirstHalf.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        for(j=1;j<=6;j++){
            table = "pr_workdata_" + year + "_0" + j;
           
            check=1;
           // System.out.println(dbm.checknReturnData(table, coloumn, code, "active"));
            
           // System.err.println((Integer.parseInt(dbm.checknReturnData(table, coloumn, code, "active"))==1));
            if(Integer.parseInt(dbm.checknReturnData(table, coloumn, code, "active"))!=1){
                
               check=0;
            }
            
            
            if(check==1){
               
            active=1;
            target = "total_pay";
            x = dbm.checknReturnDoubleData(table, coloumn, code, target);
            target = months[j-1] + "_total_pay";
            dbm.updateDatabase(destTable, "entry", entry, target, x);
            
            target = "epf10";
            x = dbm.checknReturnDoubleData(table, coloumn, code, target);
            target = months[j-1] + "_epf10";
            dbm.updateDatabase(destTable, "entry", entry, target, x);
            
            target = "epf12";
            y = dbm.checknReturnDoubleData(table, coloumn, code, target);
            target = months[j-1] + "_epf12";
            dbm.updateDatabase(destTable, "entry", entry, target, y);
            
            x = x + y;
            target = months[j-1] + "_epf";
            dbm.updateDatabase(destTable, "entry", entry, target, x);
            
            target = "etf";
            x = dbm.checknReturnDoubleData(table, coloumn, code, target);
            target = months[j-1] + "_etf";
            dbm.updateDatabase(destTable, "entry", entry, target, x);
            }
        }
        if(active==1){
             dbm.updateDatabase("prcr_ledger_first_half",coloumn,code,"active","1");
        }
    }
    
    public void updateTable(){
        int i;
        for(i=0;i<workCodes.length;i++){
            updateWorker(i);
            System.out.println(i+" done -"+workCodes[i]);
        }
        JOptionPane.showMessageDialog(null, "Updated!");
    }
    
       public int[] getArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + " WHERE register_or_casual LIKE'"+1+"'");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + " WHERE register_or_casual LIKE'"+1+"'");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args){
        DatabaseManager dbm=DatabaseManager.getDbCon();
        PRCRLedgerFirstHalf ex = new PRCRLedgerFirstHalf(2014);

        ex.getWorkCodes();
        int i;
        for(i=0;i<ex.workCodes.length;i++)
            System.out.println(ex.workCodes[i]);
        System.out.println(ex.workCodes.length);
        ex.updateTable();
    }

    @Override
    public void run() {
      getWorkCodes();
      int i;
      for(i=0;i<workCodes.length;i++)
            System.out.println(workCodes[i]);
        System.out.println(workCodes.length);
        
        updateTable();
        Report_PRCR_EPF_6Month.epfPrgrsbr.setValue(100);
    
    }
}
