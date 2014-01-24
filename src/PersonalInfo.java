
//import com.sun.imageio.plugins.common.BogusColorSpace;
import java.sql.Date;
import java.sql.SQLException;

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
    private int code;
    private String NIC;
    private String DOB;
    private String telNo;
    private String bloodGrp;
    private String registerOrNot;
    private String checkrollOrStaff;
    private Date joinedDate;
    private Date permanentDate;
    private double basicSalary;//only for staff
    private String ETF; //this variable is used to check whether employee has ETF or not
    private String EPF;
    private String welfare;
    
    public PersonalInfo(String name,int code,String NIC,String DOB,String telNo,String bloodGrp,
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
        this.code=0;
        this.NIC=null;
        this.DOB=null;
        this.telNo=null;
        this.bloodGrp=null;
        this.registerOrNot=null;
        this.checkrollOrStaff=null;
        this.joinedDate=null;
        this.permanentDate=null;
        this.basicSalary=0;
        this.EPF=null;
        this.ETF=null;
        this.welfare=null;
    }
    
     public void setName(String name){
         this.name=name;
     }
     public void setCode(int code){
         this.code=code;
     }
     public void setNIC(String NIC){
         this.NIC=NIC;
     }
     public void setDOB(String DOB){
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
     public void setEPF(boolean BooEPF){
         if(BooEPF==true){
             this.EPF="EPF-Yes";
         }
         else{
             this.EPF="EPF-No";
         }
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
     public int getCode(){
         return code;
     }
     public String getNIC(){
         return NIC;
     }
     public String getDOB(){
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
     public String getEPF(){
         return EPF;
     }
     public String getWelfare(){
         return welfare;
     }
     
     public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            
            dbCon.insert("INSERT INTO personal_info(name,code,nic,dob,tel_no,blood_group,register_or_not,checkroll_or_staff,joined_date,permanent_date,basic_salary,etf_enable,epf_enable,welfare_enable) VALUES('" + name + "','" + code + "','"+NIC+"','"+DOB+"','"+telNo+"','"+bloodGrp+"','"+registerOrNot+"','"+checkrollOrStaff+"','"+joinedDate+"','"+permanentDate+"','"+basicSalary+"','"+ETF+"','"+EPF+"','"+welfare+"')");
            //dbCon.insert("INSERT INTO checkroll_personalinfo(code) VALUES('"+code+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
    
    
}
