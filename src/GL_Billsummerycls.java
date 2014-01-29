
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class GL_Billsummerycls {
    
   DatabaseManager dbm = new DatabaseManager();
   Date_Handler datehandler = new Date_Handler();
    int id;
    double Total_kg;
    
    GL_Billsummerycls(){
    double Total_kg= 0;
   }
    
    public double Total_KG( int ID, Date date){
     
        int gl_cashadvance_set_date_int=10;   // This has to be taken from the database later

        
        datehandler.set_glcash_advance_starting_date_int(gl_cashadvance_set_date_int);
     Total_kg=   dbm.checknReturnDataForCashAdvances("green_leaf_transactions", "sup_id", ID, "tr_date", datehandler.get_glcash_advance_starting_date(date),datehandler.get_date_as_a_String(date), "net_qty");
    
    
    
    return Total_kg;
    }
    
    
    
    
    
    
    
}
