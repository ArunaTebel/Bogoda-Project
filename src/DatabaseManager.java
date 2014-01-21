
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseManager {

    public Connection conn;
    private Statement statement;
    public static DatabaseManager db;
    

    DatabaseManager() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "bogoda";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized DatabaseManager getDbCon() {
        if (db == null) {
            db = new DatabaseManager();
        }
        return db;
    }

    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not
     * available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }
    
    
    public String checknReturnData(String table_name,String table_column_giving,Object row_element,String table_column_need){
        DatabaseManager dbm = DatabaseManager.getDbCon();
         try {
            ResultSet query = dbm.query("SELECT * FROM "+table_name+" WHERE "+table_column_giving+" =" + row_element + "");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            
        }
        return null;
    }
    
    public boolean updateDatabase(String table_name,String table_column_giving,Object row_element,String table_column_need,Object update_element){
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
          
            dbm.insert("UPDATE "+table_name+" SET "+table_column_need+" ='"+update_element+"' WHERE "+table_column_giving+"='"+row_element+"'");
           
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }
    
    public void newDayTable(Date date){
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            dbm.insert("CREATE TABLE "+date.toString()+"(name varchar(25),"
                    + "employee_code varchar(10),"
                    + "workCode varchar(10));");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getStringArray(String table_name,String column_name){
        
        int count=0; 
        DatabaseManager dbm = DatabaseManager.getDbCon();
         try {
            ResultSet query = dbm.query("SELECT "+column_name+" FROM "+table_name+"");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count];
            count=0;
            ResultSet query2 = dbm.query("SELECT "+column_name+" FROM "+table_name+"");
            while (query2.next()) {
                array[count]=query2.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {
            
        }
        return null;
    }
    
    public int[] search_PRCR(String table_name,String column_1,String column_2,Object element_1,Object element_2,String needed_column){
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int workcodes[] = null;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + column_1 + " =" + element_1 + " AND " + column_2 +" =" + element_2);
            int Fetchsize = query.getFetchSize();
            workcodes = new int[Fetchsize]; 
            int i = 0;
            while (query.next()) {
                workcodes[i] = Integer.parseInt(query.getString(needed_column));
                i++;
            }
        }catch (SQLException ex) {
           
        }
        return workcodes;
    }
}
