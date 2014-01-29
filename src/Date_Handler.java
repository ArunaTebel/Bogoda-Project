
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// This class is used to store specific dates such as green leaf pay date (10) to the database and to return dates when necessary
// glcashadvances is using this class
public class Date_Handler {

    int glcash_advance_starting_date_int;

    // Conscructor
    public Date_Handler() {
        glcash_advance_starting_date_int = 0;
    }
    // Setters

    public void set_glcash_advance_starting_date_int(int glcash_advance_starting_date_int) {
        this.glcash_advance_starting_date_int = glcash_advance_starting_date_int;
    }

    // Getters
    public int get_glcash_advance_starting_date_int() {
        return glcash_advance_starting_date_int;
    }

     // Methods to get dates
    public String get_glcash_advance_starting_date(Date date) {
        String s = date.toString();
        String[] array = new String[3];
        array = s.split("-");
        return array[0] + "-" + array[1] + "-" + get_glcash_advance_starting_date_int();
    }

    public String get_date_as_a_String(Date date) {
        String s = date.toString();
        return s;
    }

     // Method to get today date
    public String get_today_date(){
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

}
