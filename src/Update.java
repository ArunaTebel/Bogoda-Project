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
     public void update_month_check(javax.swing.JButton update_button, javax.swing.JTextField year, javax.swing.JTextField month){
     String a = year.getText()+datehandler.return_month_as_num(month.getText());
        // System.out.println(a);
     String aa = "";
     
     try{
     aa = dbm.checknReturnData("update_dates", "month", a, "last_update");
         if(aa==null){
         aa= "";
         }
     } catch (Exception ee) {
            // System.out.println("Not found");
             
             }
     
      if(aa.equals("YES")){
      
      update_button.setEnabled(false);
      
      }
      else{
      
      update_button.setEnabled(true);
      }
     
     
     }
    
}
