
import java.sql.Connection;
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

    private DatabaseManager() {
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
    
}
