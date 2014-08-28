
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
public class PRCR_DivisionDetails {
    private String code;
    private String division;
    private String description;
    
    public PRCR_DivisionDetails(String code,String division,String description){
        this.code=code;
        this.division=division;
        this.description=description;
    }
    public PRCR_DivisionDetails(){
        this.code=null;
        this.division=null;
        this.description=null;
    }
    
    //setters
    public void setCode(String code){
        this.code=code;
    }
    public void setDivision(String division){
        this.division=division;
    }
    public void setDescription(String description){
        this.description=description;
    }
    
    //getters
    public String getCode(){
        return code;
    }
    public String getDivision(){
        return division;
    }
    public String getDescription(){
        return description;
    }
    
    public void addTodatabase(){
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            
            dbCon.insert("INSERT INTO division_details(division,code) VALUES('" + division + "','" + code + "')");
            
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
}
