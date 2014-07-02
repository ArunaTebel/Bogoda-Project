/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class Update {
    DatabaseManager dbm = new DatabaseManager();
    Date_Handler datehandler = new Date_Handler();
    public Update(){}
    
    public boolean check_update(String year, String month){
      int a = Integer.parseInt(year+month);
      
      try{
       String aa = dbm.checknReturnData("update_dates", "month", a, "last_update");
         // System.out.println(aa);
          if(aa==null){ return false;}
          else{
       return true;}
      } catch(Exception e){
     return false;
      }
    
    
    }
     public void update_month_set(javax.swing.JButton update_button, String year, String month){
     String a = year+month;
     String aa = dbm.checknReturnData("update_dates", "month", a, "last_update");
     String today = datehandler.get_today_date();
     int index;
     
     if(aa!=null){
     String[] last_update = aa.split("-");
     String[] current = today.split("-");
    
       if(Integer.parseInt(last_update[0])<Integer.parseInt(current[0])){index = 1;}
       if((Integer.parseInt(last_update[0])==Integer.parseInt(current[0]))&&(Integer.parseInt(last_update[1])<Integer.parseInt(current[1]))){
           index = 1;
       }
       if((Integer.parseInt(last_update[0])==Integer.parseInt(current[0]))&&(Integer.parseInt(last_update[1])==Integer.parseInt(current[1]))&&
               (Integer.parseInt(last_update[2])<Integer.parseInt(current[2]))){
           
       }
     }
     
     
     
     }
    
}
