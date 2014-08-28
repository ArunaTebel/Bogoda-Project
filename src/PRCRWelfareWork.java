
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class PRCRWelfareWork {
    
    DatabaseManager dbm;
    
    public PRCRWelfareWork(){
        this.dbm =  DatabaseManager.getDbCon();
    }
    
    public String getMonth(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        String day;
        int month;
        
        day = formatter.format(currentDate.getTime());
        month = Integer.parseInt(day.substring(5, 7));
        
        return (day.substring(0, 5) + month);
    }
    
    public boolean checkIfUpdated(){
        String thisMonth = this.getMonth();
        String lastMonth = thisMonth.substring(0, 5) + (Integer.parseInt(thisMonth.substring(5)) - 1);
        int[] currentCodes = dbm.getValuesArray("prcr_welfare", "month", "code", thisMonth);
        if(currentCodes.length==0)
            return false;
        else{
            int[] previousCodes = dbm.getValuesArray("prcr_welfare", "month", "code", lastMonth);
            for(int i=0;i<currentCodes.length;i++){
                for(int j=0;j<previousCodes.length;j++){
                    if(currentCodes[i]==previousCodes[j])
                        return true;
                }
            }
            return false;
        }
    }
}
