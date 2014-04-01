
import java.sql.SQLException;

public class LeafCategory {
    private String categoryName;
    private double categoryCode;

   public LeafCategory() {
        categoryCode=0;
        categoryName=null;
    }
   public LeafCategory(String categoryName, double categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }
   public void setCategoryName(String categoryName){
       this.categoryName=categoryName;
   }
   public void setCategoryCode(int categoryCode){
       this.categoryCode=categoryCode;
   }
   public String getCategoryName(){
       return categoryName;
   }
   public double getCategoryCode(){
       return categoryCode;
   }
   public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO leaf_category(category_id,category_name) VALUES('" + categoryCode + "','" + categoryName + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
       

