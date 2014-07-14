
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Enumeration;
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
    
    
    public String[] get_MAC(){
    String macs[] = new String[15];
    //macs = null;
    try {
    InetAddress ip = InetAddress.getLocalHost();
       
       // JT.setText(JT.getText()+"--------------------------------------------------------"+"\n"+"Current IP address : " + ip.getHostAddress()+"\n");
  int j =0;
    Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
    while(networks.hasMoreElements()) {
      NetworkInterface network = networks.nextElement();
      byte[] mac = network.getHardwareAddress();

      if(mac != null) {
        //JT.setText(JT.getText()+"MAC address "+(j+1)+" : ");
       

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        //JT.setText(JT.getText()+sb.toString()+"\n");
          
        try {
        macs[j]= sb.toString();
        
       
        } catch (Exception e){
            System.out.println(e);
        }
        j++;
      }
    }
  } catch (SocketException e){
    e.printStackTrace();
  }     catch (UnknownHostException ex) {
          //  Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    return macs;
    
    
    
    
    }
    
    
    public void validate(){
    String as=null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Pramo\\Documents\\NetBeansProjects\\Bogoda-Project\\dist\\log.dat"));
            String line = null;
            int jk = 0;
while ((line = reader.readLine()) != null&& jk<20) {
  as = as+line;
  jk++;
}
            System.out.println(as.substring(9, 26));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "The system returned with an unexpected error"+"\n"+"error code: 291343Fa"+"\n"+"please contact Admin","BogodaNL Error" , JOptionPane.ERROR_MESSAGE);
         System.exit(0);
            
        }
        int k = 0;
        int Stop = 0;
        
        while(k<15){
        if(as.substring(9, 26).equals(get_MAC()[k])){ Stop=1;}
           k++;
            }
         if(Stop==0){JOptionPane.showMessageDialog(null, "The system returned with an unexpected error"+"\n"+"error code: 291343Fa"+"\n"+"please contact Admin","Bogoda NL Error" , JOptionPane.ERROR_MESSAGE);
         System.exit(0);
         }
    
    
    
    
    }

}
