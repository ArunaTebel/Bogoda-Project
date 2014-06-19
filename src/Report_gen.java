
import java.awt.Desktop;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class Report_gen {
     DatabaseManager dbm=DatabaseManager.getDbCon();
    public Report_gen(){
    
    
    }
    
    public String create(String file_name, String save_location,HashMap param,String report_location,String report_name){
     try{
         
        // JOptionPane.showMessageDialog(null, "Inside");
            Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNow = formatter.format(currentDate.getTime());
       
            //HashMap param = new HashMap();
            //param.put("Date",jTextField1.getText() );
       // String report="C:\\Users\\Pramo\\Documents\\NetBeansProjects\\Lec\\src\\report4.jrxml";
       
         JasperPrint jp = null;
       
             JOptionPane.showMessageDialog(null, "complilng");
            // JasperReport jr = (JasperReport)JRLoader.loadObjectFromFile(report_location+report_name);
              JasperReport jr=JasperCompileManager.compileReport(report_location+report_name);
              JOptionPane.showMessageDialog(null, "compliled");
              
        
        jp=JasperFillManager.fillReport(jr, param,dbm.conn);
       // JOptionPane.showMessageDialog(null, "filled");
        
           // JasperViewer.viewReport(jp);
           // File pdf = File.createTempFile("output.", ".pdf");
JasperExportManager.exportReportToPdfFile(jp,save_location+file_name+dateNow+".pdf" );
//JOptionPane.showMessageDialog(null, "pdf convert");
File myFile = new File(save_location+file_name+dateNow+".pdf");
//JOptionPane.showMessageDialog(null, "File saved");
        Desktop.getDesktop().open(myFile);
       
        return dateNow;
        }catch(Exception e){
           // System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }}
    
    public void savename(String dateNow, String file_name,String yearmonth) throws SQLException{
      try {
            dbm.insert("INSERT INTO last_report(type,filename,month) VALUES('" + file_name+ "','"  + file_name+dateNow+ "','"+yearmonth+"')");

        } catch (SQLException ex) {
            dbm.insert("UPDATE last_report SET filename = '"+file_name+dateNow+"' WHERE type = '"+file_name+"'");
        }
    
    
    
    
    
    }
}
