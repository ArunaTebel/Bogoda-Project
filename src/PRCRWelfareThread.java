
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
public class PRCRWelfareThread implements Runnable{
    public void run(){
        DatabaseManager dbm = new DatabaseManager();
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        int isUpdated = -1;
        String day = formatter.format(currentDate.getTime());
        int month = Integer.parseInt(day.substring(5, 7));
        int year = Integer.parseInt(day.substring(0, 4));
        String thisMonth, lastMonth, thismonth2;
        int entry = 0, i;
        
        try {
            isUpdated = dbm.readFirstRow("is_prcr_updated", "is_updated");
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
                entry = dbm.readLastRow("prcr_welfare", "entry");
            } catch (SQLException ex) {
                Logger.getLogger(WelfareThread.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            int[] codeArray = dbm.getValuesArray("prcr_welfare", "month", "code", lastMonth);
            int[] monthsArray = dbm.getValuesArray("prcr_welfare", "month", "months_on_welfare", lastMonth);
            int[] newOldArray = dbm.getValuesArray("prcr_welfare", "month", "new_old", lastMonth);
            int[] suspendedArray = dbm.getValuesArray("prcr_welfare", "month", "suspended_months", lastMonth);
            int[] remainingArray = dbm.getValuesArray("prcr_welfare", "month", "suspended_remain", lastMonth);
            int[] beforeAfterArray = dbm.getValuesArray("prcr_welfare", "month", "before_after", lastMonth);
            int[] entryArray = new int[codeArray.length];
            
            for(i=0;i<codeArray.length;i++){
                entryArray[i] = Integer.parseInt(day.substring(0, 4) + day.substring(5, 7) + codeArray[i]);
            }
            
            for(i=0;i<codeArray.length;i++){
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
            
            
            for(i=0;i<codeArray.length;i++){
                try {
                    dbm.insert("INSERT INTO prcr_welfare(entry,month,code,months_on_welfare,new_old,suspended_months,suspended_remain,before_after) VALUES('" + entryArray[i] + "','" + thisMonth + "','" + codeArray[i] + "','" + monthsArray[i] + "','" + newOldArray[i] + "','" + suspendedArray[i] + "','" + remainingArray[i] + "','" + beforeAfterArray[i] + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(WelfareThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            dbm.updateDatabase("is_prcr_updated", "is_updated", isUpdated, "is_updated", month);
            
            JOptionPane.showMessageDialog(null, "Done");
        }
    }
}
