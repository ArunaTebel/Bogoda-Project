
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class GL_addTransport_rate  {

   
   
    private final double transRate;
   
    private final String Trans_name;
           
    //retreiving the database connection
    DatabaseManager dbCon = DatabaseManager.getDbCon();

    
    public  GL_addTransport_rate (String name, double transRate) {

        
        this.Trans_name = name;
        
        this.transRate = transRate;
    }

    /*This constructor will be created when the supplier details have to be edited 
     or supplier has to be deleted*/
    public  GL_addTransport_rate () {
       
        this.Trans_name = null;
        this.transRate = 0;
    }

    //Editing details of an existing entry of a customer
    public void editDetails(int code) {
        /*
         UPDATE THE RELEVANT ENTRY ACCORDING TO THE 'code' IN THE DATABASE
         */
        try {
            ResultSet rs = dbCon.query("SELECT * FROM suppliers WHERE `trans_id`=" + code);
            while (rs.next()) {
                System.out.println(rs.getString("trans_rate"));
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }

    //Deletion of a supplier entry
    public void removeSupplier(int code) {
        /*
         GET THE RELEVANT ENTRY ACCORDING TO THE 'code' FROM THE DATABASE, AND DELETE THE ROW
         */
    }

    public void addToDatabase() {
        try {
            
            dbCon.insert("INSERT INTO tranport_rates(Trans_id,  Trans_rate) VALUES('" + Trans_name + "','" + transRate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
}


    

