
import java.sql.ResultSet;
import java.sql.SQLException;


public class temp_test {
    
    
    // Date column is the column in your database which dates are entered
    // String month is the month you want to check. Give month as a string ex:- 01 for January, 09 for September
    // String column_need_to_check is the column that you need to filter in your case the column that has n's and s's :)
    // Object element is the element you need to check in that column. Ex:- Either "s" or "n"
    public int checknReturnNoOfData(String table_name, String date_column, String month,String column_need_to_check,Object element_to_check) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count=0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if(query.getString(date_column).split("-")[1]==month && query.getString(column_need_to_check)==element_to_check){
                    count++;
                }
            }
            return count;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return count;
    }
    
    
   // column_need_to_get_total is Either Day OT hour column or Night OT hour column.. All other parameters are similar to the above method
    
       public double checknReturnTotal(String table_name, String date_column, String month,String column_need_to_get_total) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot=0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                if(query.getString(date_column).split("-")[1]==month ){
                    tot=tot+query.getDouble(column_need_to_get_total);
                }
            }
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return tot;
    }
    
}
