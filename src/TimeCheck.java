
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class TimeCheck implements Runnable{
    public String date;
    public String hour;
    public String minute;
    public String month;
    public String year;
    public String user;
    
    public TimeCheck(String a){
        this.user = a;
    }
    
    public static String getTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
        String datenow = formatter.format(currentDate.getTime());
        return datenow;
    }
    
    public void checkTime() {
        String s = getTime();
        this.year = s.substring(0,4);
        this.month = s.substring(5, 7);
        this.date = s.substring(8, 10);
        this.hour = s.substring(12, 14);
        this.minute = s.substring(15, 17);
        MainWindow.topBar.setText("Welcome! " + user + "             " + hour + ":" + minute + "           " + month + "-" + date + "-" + year);
        
    }
    
    
    
    @Override
    public void run(){
        for(;;){
            this.checkTime();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
