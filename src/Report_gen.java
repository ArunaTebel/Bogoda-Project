
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
    
    public boolean create(String file_name, String save_location,HashMap param,String report_location){
     try{
            Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateNow = formatter.format(currentDate.getTime());
            //HashMap param = new HashMap();
            //param.put("Date",jTextField1.getText() );
       // String report="C:\\Users\\Pramo\\Documents\\NetBeansProjects\\Lec\\src\\report4.jrxml";
        JasperReport jr=JasperCompileManager.compileReport(report_location);
        JasperPrint jp=JasperFillManager.fillReport(jr, param,dbm.conn);
        
           // JasperViewer.viewReport(jp);
           // File pdf = File.createTempFile("output.", ".pdf");
JasperExportManager.exportReportToPdfFile(jp,save_location+file_name+dateNow+".pdf" );
File myFile = new File(save_location+file_name+dateNow+".pdf");
        Desktop.getDesktop().open(myFile);
        return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }}
    
}
