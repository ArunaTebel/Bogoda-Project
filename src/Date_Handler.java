
import java.sql.Date;

// This class is used to store specific dates such as green leaf pay date (10) to the database and to return dates when necessary

// glcashadvances is using this class
public class Date_Handler {
    
    int glcash_advance_starting_date_int;
    
    
    
    
    // Setters
    
    public void set_glcash_advance_starting_date_int(int glcash_advance_starting_date_int){
        this.glcash_advance_starting_date_int=glcash_advance_starting_date_int;
    }
    
    // Getters
    
     public int get_glcash_advance_starting_date_int(){
         return glcash_advance_starting_date_int;
    }
    
    
     
     // Methods to get dates
    
     public String get_glcash_advance_starting_date(Date date){
         String s = date.toString();
         String[] array = new String[3];
         array=s.split(":");
         return array[0] + "-" + array[1] + "-" + "+get_glcash_advance_starting_date_int()" ;
         
     }
    
    
}
