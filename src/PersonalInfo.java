
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
    private Date DOB;
    private String telNo;
    private String bloodGrp;
    private boolean registerOrNot;
    private boolean checkrollOrStaff;
    private Date joinedDate;
    private Date permanentDate;
    private double basicSalary;//only for staff
    
    public PersonalInfo(String name,int code,String NIC,Date DOB,String telNo,String bloodGrp,
            boolean registerOrNot,boolean checkrollOrStaff,Date joinedDate,Date permanentDate,
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
        this.registerOrNot=false;
        this.checkrollOrStaff=false;
        this.joinedDate=null;
        this.permanentDate=null;
        this.basicSalary=0;
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
     public void setRegisterOrNot(boolean registerOrNot){
         this.registerOrNot=registerOrNot;
     }
     public void setCheckrollOrStaff(boolean checkrollOrStaff){
         this.checkrollOrStaff=checkrollOrStaff;
     }
     public void setBasicSallary(double basicSalary){
         this.basicSalary=basicSalary;
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
     public boolean getRegisterOrNot(){
         return registerOrNot;
     }
     public boolean checkrollOrStaff(){
         return checkrollOrStaff;
     }
     public double getBasicSallary(){
         return basicSalary;
     }
     public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO personal_info(name,code,nic,dob,tel_no,blood_group,register_or_not,checkroll_or_staff,joined_date,permanent_date,basic_salary) VALUES('" + name + "','" + code + "','"+NIC+"','"+DOB+"','"+telNo+"','"+bloodGrp+"','"+registerOrNot+"','"+checkrollOrStaff+"','"+joinedDate+"','"+permanentDate+"','"+basicSalary+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
    
    
}
