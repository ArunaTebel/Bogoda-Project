
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
public class Rate_Details {

    private String code;
    private String name;
    private String codename;
    private String type;
    private double rate;
    int inst;
  
    private String ratedisc;

    public Rate_Details(int inst,String code, String name, String codename, double rate, String debitOrCredit, String type, String unit, String ratediscription) {
        this.code = code;
        this.name = name;
        this.codename = codename;
        this.rate = rate;

        this.type = type;
       
        this.ratedisc= ratediscription;
        this.inst = inst;
    }

    public Rate_Details() {
        this.code = null;
        this.name = null;
        this.codename = null;
        this.rate = 0;
      
        this.type = null;
        this.ratedisc= null;
        this.inst=0;
    }

    //setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void settype(String type) {
        this.type = type;
    }
    public void setDiscription(String disc) {
        this.ratedisc = disc;
    }

     public void setInstallments(int disc) {
        this.inst = disc;
    }
    
    //getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return codename;
    }

    public double getRate() {
        return rate;
    }

    public String gettype() {
        return type;
    }
    
     public String getRatedisc() {
        return ratedisc;
    }

    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO rate_details(Code_name,Name,type,rate) VALUES('" + code + "','" + name +  "','" + type + "','" + rate + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
     public void addToDataBase2() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO rate_manegement_details(Code_name,Name,rate) VALUES('" + code + "','" + name +  "','" + rate + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    

}


