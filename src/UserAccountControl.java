
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class UserAccountControl {

    DatabaseManager dbm = new DatabaseManager();
     DatabaseManager dbCon = DatabaseManager.getDbCon();

    public String getIP() {
        String addr = "Default";
        try {
            addr = Inet4Address.getLocalHost().getHostAddress();

        } catch (UnknownHostException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        return addr;

    }

    public boolean Checkip() {                                // Check whether ip is in the current user database
        String addr = "default";
        int i = 0;

        int lnth = dbm.getStringArray("user_current", "ip").length;
       
        if (lnth != 1) {
            try {
                addr = Inet4Address.getLocalHost().getHostAddress();
              
            } catch (UnknownHostException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            while (i < lnth - 1) {
                if (dbm.getStringArray("user_current", "ip")[i+1].equals(addr)) {
                   
                    return false;
                }
                  i++;
            }

        }
        
        return true;

    }
    
    public void Add_ip()
            
    {
        String addr = "default";
        String date = " default";
        String user = "default";
        try {
                addr = Inet4Address.getLocalHost().getHostAddress();

            } catch (UnknownHostException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    
     try {
            dbCon.insert("INSERT INTO user_current(ip,date_time,user) VALUES('" + addr + "','"+ date + "','"+ user + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    
    
     
    }
    
    
    public String get_current_user (){
       String user="Default_user";
        String addr = "default";
        String date = " default";
        
        try {
                addr = Inet4Address.getLocalHost().getHostAddress();

            } catch (UnknownHostException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       user= dbm.checknReturnStringData("user_current", "ip", addr, "user");
       
    
       return user;
    }

}
