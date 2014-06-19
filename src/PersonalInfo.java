
//import com.sun.imageio.plugins.common.BogusColorSpace;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class PersonalInfo {
    
    private String name;
    private String sinhalaName;
    private int code;
    private String division;
    private String NIC;
    private Date DOB;
    private String telNo;
    private String bloodGrp;
    private String registerOrNot;
    private String checkrollOrStaff;
    private Date joinedDate;
    private Date permanentDate;
    private double basicSalary;//only for staff
    private String ETF; //this variable is used to check whether employee has ETF or not
    private int EPF;
    private String welfare;
    
    public PersonalInfo(String name,int code,String NIC,Date DOB,String telNo,String bloodGrp,
            String registerOrNot,String checkrollOrStaff,Date joinedDate,Date permanentDate,
            double basicSalary){
    
        this.name=name;
        this.code=code;
        this.NIC=NIC;
        this.DOB=DOB;
        this.telNo=telNo;
        this.bloodGrp=bloodGrp;
        this.registerOrNot=registerOrNot;
        this.checkrollOrStaff=checkrollOrStaff;
        this.joinedDate=joinedDate;
        this.permanentDate=permanentDate;
        this.basicSalary=basicSalary;
        
    }
    
     public PersonalInfo(){
    
        this.name=null;
        this.sinhalaName=null;
        this.code=0;
        this.division=null;
        this.NIC=null;
        this.DOB=null;
        this.telNo=null;
        this.bloodGrp=null;
        this.registerOrNot=null;
        this.checkrollOrStaff=null;
        this.joinedDate=null;
        this.permanentDate=null;
        this.basicSalary=0;
        this.EPF=0;
        this.ETF=null;
        this.welfare=null;
    }
    
     public void setName(String name){
         this.name=name;
     }
     public void setSinhalaName(String sinname){
         this.sinhalaName=sinname;
     }
     
     public void setCode(int code){
         this.code=code;
     }
     public void setDivision(String division){
         this.division=division;
     }
     public void setNIC(String NIC){
         this.NIC=NIC;
     }
     public void setDOB(Date DOB){
         this.DOB=DOB;
     }
     public void setTelNo(String telNo){
         this.telNo=telNo;
     }
     public void setBloodGrp(String bloodGrp){
         this.bloodGrp=bloodGrp;
     }
     public void setJoinedDate(Date joinedDate){
         this.joinedDate=joinedDate;
     }
     public void setPermanentDate(Date permanentDate){
         this.permanentDate=permanentDate;
     }
     public void setRegisterOrNot(boolean BregisterOrNot){
         if(BregisterOrNot==true){
         this.registerOrNot="Registered";}
         else{
         this.registerOrNot="Not Registered";
         }
         
     }
     public void setCheckrollOrStaff(String checkrollOrStaff){
         this.checkrollOrStaff=checkrollOrStaff;
     }
     public void setBasicSallary(double basicSalary){
         this.basicSalary=basicSalary;
     }
     public void setETF(boolean BooETF){
         if(BooETF==true){
             this.ETF="ETF-Yes";
         }
         else{
             this.ETF="ETF-No";
         }
     }
     public void setEPF(int EPF){
         this.EPF=EPF;
     }
     public void setWelfare(boolean Bwelfare){
         if(Bwelfare==true){
             this.welfare="Welfare-Yes";
         }
         else{
             this.welfare="Welfare-No";
         }
     }
     
    
     //getters
     public String getName(){
         return name;
     }
     public String getSinhalaName(){
         return sinhalaName;
     }
     
     public int getCode(){
         return code;
     }
     public String getNIC(){
         return NIC;
     }
     public Date getDOB(){
         return DOB;
     }
     public String getTelNo(){
         return telNo;
     }
     public String getBloodGrp(){
         return bloodGrp;
     }
     public Date getJoinedDate(){
         return joinedDate;
     }
     public Date getPermanentDate(){
         return permanentDate;
     }
     public String getRegisterOrNot(){
         return registerOrNot;
     }
     public String checkrollOrStaff(){
         return checkrollOrStaff;
     }
     public double getBasicSallary(){
         return basicSalary;
     }
     public String getETF(){
         return ETF;
     }
     public int getEPF(){
         return EPF;
     }
     public String getWelfare(){
         return welfare;
     }
     
     public void addToDataBase() {
         System.out.println(permanentDate+" "+joinedDate);
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            
            dbCon.insert("INSERT INTO personal_info(name,sinhala_name,code,nic,dob,tel_no,blood_group,register_or_not,checkroll_or_staff,basic_salary,etf_enable,epf_no,welfare_enable) VALUES('" + name + "','" + sinhalaName + "','" + code + "','"+NIC+"','"+DOB+"','"+telNo+"','"+bloodGrp+"','"+registerOrNot+"','"+checkrollOrStaff+"','"+basicSalary+"','"+ETF+"','"+EPF+"','"+welfare+"')");
            
            if(permanentDate!=null){//if permanent date is not given show message else save it
            dbCon.updateDatabase( "personal_info","code",code,"permanent_date",permanentDate);
            }else{
            JOptionPane.showMessageDialog(null, "Registered date is not saved ", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            
            if(joinedDate!=null){//if joined date is not given show message else save it
            dbCon.updateDatabase( "personal_info","code",code,"joined_date",joinedDate);
            }else{
            JOptionPane.showMessageDialog(null, "Joined date is not saved", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
//dbCon.insert("INSERT INTO checkroll_personalinfo(code) VALUES('"+code+"')");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL error! Please check again and save ", "Message", JOptionPane.INFORMATION_MESSAGE);
      
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
    
    
}
