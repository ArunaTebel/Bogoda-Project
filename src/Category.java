
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Category {

    private String categoryName;
    private String categoryCode;
    private double extraRate;

    Category(String categoryName, String categoryCode, double extraRate) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.extraRate = extraRate;
    }

    Category() {
        categoryName = null;
        categoryCode = null;
        extraRate = 0;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setExtraRate(double extraRate) {
        this.extraRate = extraRate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public double getExtraRate() {
        return extraRate;
    }
    
    
    public int returnnext(){
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        int max = 0;
    try {
            
            ResultSet query = dbCon.query("SELECT * FROM category ");
            while (query.next()) {
                if(query.getInt("sup_id")> max){max = query.getInt("sup_id");}
            }
            query.close();
            System.out.println(max);
        } catch (SQLException ex) {
            Logger.getLogger(GL_report_generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return max+1;
    }

    public void addToDataBase() {
        
        if(returnnext()>10000){ JOptionPane.showMessageDialog(null, "Entry limiy exceeded");}
        else{
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO category(sup_id,category_name,category_id,extra_rate) VALUES('" + returnnext() + "','" + categoryName + "','" + categoryCode + "','" + extraRate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
        }
    }
    
     public void updateDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("UPDATE  category SET category_name ='"+ categoryName +"',extra_rate='" + extraRate + "' WHERE category_id ='" + categoryCode + "'");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
