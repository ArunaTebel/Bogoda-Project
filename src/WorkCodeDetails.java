
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
public class WorkCodeDetails {
    private String code;
    private String work;
    private String description;
    
    public WorkCodeDetails(String code,String work,String description){
        this.code=code;
        this.work=work;
        this.description=description;
    }
    public WorkCodeDetails(){
        this.code=null;
        this.work=null;
        this.description=null;
    }
    
    //setters
    public void setCode(String code){
        this.code=code;
    }
    public void setWork(String work){
        this.work=work;
    }
    public void setDescription(String description){
        this.description=description;
    }
    
    //getters
    public String getCode(){
        return code;
    }
    public String getWork(){
        return work;
    }
    public String getDescription(){
        return description;
    }
    
    public void addTodatabase(){
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            
            dbCon.insert("INSERT INTO workcode_details(work,code) VALUES('" + work + "','" + code + "')");
            
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
}
