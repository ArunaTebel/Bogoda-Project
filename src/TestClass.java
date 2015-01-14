
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aceri3
 */
public class TestClass {
    static DatabaseManager dbm = DatabaseManager.getDbCon();
    public static void main(String[] args) {
        System.out.println(checknReturnDoubleTotal("pr_workdata_2014_09", "division", "FAC", "gross_pay"));
    }
    
    
    public static double checknReturnDoubleTotal(String table_name, String division_column, String division, String column_need_to_get_total) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        double tot = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE register_or_casual LIKE '0' AND active LIKE '1'");
            while (query.next()) {

                if (query.getString(division_column).equals(division)) {

                    tot = tot + query.getDouble(column_need_to_get_total);
                    System.out.println(query.getInt("code")+"-"+query.getDouble(column_need_to_get_total));

                }
            }
            query.close();
            return tot;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("SQL error-Some Amounts may be null ");

        }
        return tot;
    }

}
