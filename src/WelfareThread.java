
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class WelfareThread implements Runnable{
    String day;
    String MNT;
    
    public WelfareThread(String year){
    day = year;
   // MNT = month;
    
    }
    
    public void run(){
        DatabaseManager dbm =DatabaseManager.getDbCon();
        //Calendar currentDate = Calendar.getInstance();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        int isUpdated = -1;
        //String day = formatter.format(currentDate.getTime());
        
        int month = Integer.parseInt(day.substring(5, 7));
        int year = Integer.parseInt(day.substring(0, 4));
        System.out.println(year+month);
        String thisMonth, lastMonth,thismonth2;
        int entry = 0, i;
        
        try {
            isUpdated = dbm.readFirstRow("is_welfare_updated", "is_updated");
        } catch (SQLException ex) {
            Logger.getLogger(WelfareThread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
        
        if(isUpdated!=month){
            thisMonth = day.substring(0, 5) + month;
            lastMonth = day.substring(0, 5) + (month-1);
            if(month==1){
                lastMonth = (year-1) + "-12";
            }
            if(month<10){
            thismonth2= day.substring(0,4)+"0"+month;}
            else{
            thismonth2= day.substring(0,4)+month;}
            
            /*try {
                entry = dbm.readLastRow("welfare", "entry");
            } catch (SQLException ex) {
                Logger.getLogger(WelfareThread.class.getName()).log(Level.SEVERE, null, ex);
            }*/
             System.out.println(lastMonth);
            int[] supIdArray = dbm.getValuesArray("welfare", "month", "sup_id", lastMonth);
            int[] monthsArray = dbm.getValuesArray("welfare", "month", "months_on_welfare", lastMonth);
            int[] newOldArray = dbm.getValuesArray("welfare", "month", "new_old", lastMonth);
            int[] suspendedArray = dbm.getValuesArray("welfare", "month", "suspended_months", lastMonth);
            int[] remainingArray = dbm.getValuesArray("welfare", "month", "suspended_remain", lastMonth);
            int[] beforeAfterArray = dbm.getValuesArray("welfare", "month", "before_after", lastMonth);
            int[] entryArray = new int[supIdArray.length];
            
            for(i=0;i<supIdArray.length;i++){
                //System.out.println(supIdArray[i]);
                entryArray[i] = Integer.parseInt(day.substring(0, 4) + day.substring(5, 7) + supIdArray[i]);
            }
            
            for(i=0;i<supIdArray.length;i++){
                if(monthsArray[i]==0){
                    newOldArray[i] = 0;
                    monthsArray[i] = -1;
                }
                else
                    monthsArray[i]--;
                if(remainingArray[i]==0){
                    suspendedArray[i] = -1;
                    remainingArray[i] = -1;
                    beforeAfterArray[i] = -1;
                }
                else
                    remainingArray[i]--;
            }
            

            
            for(i=0;i<supIdArray.length;i++){
                try {
                    dbm.insert("INSERT INTO welfare(entry,month,month2,sup_id,months_on_welfare,new_old,suspended_months,suspended_remain,before_after) VALUES('" + entryArray[i] + "','" + thisMonth + "','" + thismonth2 + "','" + supIdArray[i] + "','" + monthsArray[i] + "','" + newOldArray[i] + "','" + suspendedArray[i] + "','" + remainingArray[i] + "','" + beforeAfterArray[i] + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(WelfareThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
             String table = "welfare";
        String coloumnG = "month";
        String coloumnN = null;
        int supId = 0;
        int newOld = 0;
        int suspended = 0;
        int remain = 0;
        int before = 0;
        double amount = 0;
            System.out.println("Running qwelf");
        int oldRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_RATE", "rate");
        int newRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_NEW", "rate");
        
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table + " where " + coloumnG + " = '" + thisMonth + "'");
            while (query.next()) {
                coloumnN = "sup_id";
                supId = query.getInt(coloumnN);
                 String examount = dbm.filterReturn2StringData("welfare", "sup_id", supId+"","month",thisMonth, "amount");
        if(examount==null){
                coloumnN = "new_old";
                newOld = query.getInt(coloumnN);
                coloumnN = "suspended_months";
                suspended = query.getInt(coloumnN);
                coloumnN = "suspended_remain";
                remain = query.getInt(coloumnN);
                coloumnN = "before_after";
                before = query.getInt(coloumnN);
                
                if (remain >= 0) {
                    if ((remain == 0 && before == 0) || (remain == suspended && before == 1)) {
                        if (newOld == 0) {
                            amount = suspended * oldRate;
                        } else {
                            amount = suspended * newRate;
                        }
                    } else {
                        amount = 0;
                    }
                } else {
                    if (newOld == 0) {
                        amount = oldRate;
                    } else {
                        amount = newRate;
                    }                    
                }
                dbm.update("welfare", "month", "sup_id", thisMonth, supId, "amount", amount);                
            }}
        } catch (SQLException ex) {
            Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            dbm.updateDatabase("is_welfare_updated", "is_updated", isUpdated, "is_updated", month);
            
           /// JOptionPane.showMessageDialog(null, "Welfare Updated");
            
            
            
            
            
            
        }
        
         //String thisMonth = yearfield.getText() + "-" + Integer.parseInt(datehandler.return_month_as_num(monthfield.getText()));
        
       // DatabaseManager dbm = new DatabaseManager();
       
       

        
        
        
    }
    
}
