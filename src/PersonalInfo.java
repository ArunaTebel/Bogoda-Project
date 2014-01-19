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
    private boolean registerOrNot;
    private boolean checkrollOrStaff;
    private String joinedDate;
    private String permanentDate;
    
    public PersonalInfo(String name,int code,String NIC,String DOB,String telNo,String bloodGrp,
            boolean registerOrNot,boolean checkrollOrStaff,String joinedDate,String permanentDate){
    
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
     public void setJoinedDate(String joinedDate){
         this.joinedDate=joinedDate;
     }
     public void setPermanentDate(String permanentDate){
         this.permanentDate=permanentDate;
     }
     public void setName(boolean registerOrNot){
         this.registerOrNot=registerOrNot;
     }
     public void setCode(boolean checkrollOrStaff){
         this.checkrollOrStaff=checkrollOrStaff;
     }
    
    
    
    
    

    
}
