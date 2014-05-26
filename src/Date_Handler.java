
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
    
    public String get_advance_month_split_day (){
     String day= null;
    
    day = "07";
    
    
    
    return day;
    }

    // Methods to get dates
    public String get_glcash_advance_starting_date(Date date) {
        String s = date.toString();
        String[] array = new String[3];
        array = s.split("-");
        return array[0] + "-" + array[1] + "-" + get_glcash_advance_starting_date_int();
    }

    public String get_month(Date date) {
        String s = date.toString();
        String month= null;
        String[] array = new String[3];
        array = s.split("-");

        switch (array[1]) {
            case "01":
                month = "Jan";
                break;
            case "02":
                month = "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "Jun";
                break;
            case "07":
                month = "Jul";
                break;
            case "08":
                month = "Aug";
                break;
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;

        }

        return month;
    }
    
        
         public String get_month_as_num(Date date) {
        String s = date.toString();
        String month= null;
        String[] array = new String[3];
        array = s.split("-");
         return array[1];
         }
         
        
          
          public String get_next_month(String month) {
       // String s = date.toString();
        String next_month= null;
        //String[] array = new String[3];
        //array = s.split("-");
        
        if(Integer.parseInt(month)<9){
         next_month = "0"+(Integer.parseInt(month)+1);
        }
        else if(month.equals("12")){
        next_month = "01";
        }
        
        else if(month.equals("09")){
        next_month = "10";
        }
        else{
        next_month = (Integer.parseInt(month)+1)+"";
        }
        return next_month;
         }
           public String get_prev_month(String month) {
       // String s = date.toString();
        String next_month= null;
        //String[] array = new String[3];
        //array = s.split("-");
        
        if(Integer.parseInt(month)<10){
         next_month = "0"+(Integer.parseInt(month)-1);
        }
        else if(month.equals("01")){
        next_month = "12";
        }
        
        else if(month.equals("10")){
        next_month = "09";
        }
        else{
        next_month = (Integer.parseInt(month)-1)+"";
        }
        return next_month;
         }
    
    public String get_year(Date date) {
        String s = date.toString();
        String month= null;
        String[] array = new String[3];
        array = s.split("-");
        return array[0];

    }
public String get_day(Date date) {
        String s = date.toString();
        String month= null;
        String[] array = new String[3];
        array = s.split("-");
        int i = Integer.parseInt(array[2]);
        return ""+i;

    }

    public String get_date_as_a_String(Date date) {
        String s = date.toString();
        return s;
    }

    // Method to get today date
    public String get_today_date() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }
    
    
     public String get_today_date_time() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }
    
     public String get_today_day() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
         String month= null;
        String[] array = new String[3];
        array = dateNow.split("-");
        return array[2];
    }
     
      public String get_today_month() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
         String month= null;
        String[] array = new String[3];
        array = dateNow.split("-");
         switch (array[1]) {
            case "01":
                month = "Jan";
                break;
            case "02":
                month = "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "Jun";
                break;
            case "07":
                month = "Jul";
                break;
            case "08":
                month = "Aug";
                break;
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;

        }

        return month;
    }
      
       public String get_today_year() {
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
         String month= null;
        String[] array = new String[3];
        array = dateNow.split("-");
        return array[0];
    }
       
        public int return_index(String month) {
        int index = 0;

        switch (month) {
            case "Jan":
                index = 1;
                break;
            case "Feb":
                index = 2;
                break;
            case "Mar":
                index = 3;
                break;
            case "Apr":
                index = 4;
                break;
            case "May":
                index = 5;
                break;
            case "Jun":
                index = 6;
                break;
            case "Jul":
                index = 7;
                break;
            case "Aug":
                index = 8;
                break;
            case "Sep":
                index = 9;
                break;
            case "Oct":
                index = 10;
                break;
            case "Nov":
                index = 11;
                break;
            case "Dec":
                index = 12;
                break;

        }

        return index;
    }

    public String Return_month(int index) {
        String month = null;

        switch (index) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sep";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;

        }

        return month;

    }
   public String return_month_as_num(String month) {
        String index = null;

        switch (month) {
            case "Jan":
                index = "01";
                break;
            case "Feb":
                index = "02";
                break;
            case "Mar":
                index = "03";
                break;
            case "Apr":
                index = "04";
                break;
            case "May":
                index = "05";
                break;
            case "Jun":
                index = "06";
                break;
            case "Jul":
                index = "07";
                break;
            case "Aug":
                index = "08";
                break;
            case "Sep":
                index = "09";
                break;
            case "Oct":
                index = "10";
                break;
            case "Nov":
                index = "11";
                break;
            case "Dec":
                index = "12";
                break;

        }
        return index;
}
   
}
