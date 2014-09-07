
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public final class DatabaseManager {

    public Connection conn;
    private Statement statement;
    public static DatabaseManager db;
     String a = null;
     double d = 0;
    int result;
     ResultSet res;

    private DatabaseManager() {

       // String url = "jdbc:mysql://192.168.1.50/";
      //  String userName = "BogodaUser";
        String url = "jdbc:mysql://localhost:3306/";
        //String dbName = "arbour";
        String dbName = "bogoda";
        String driver = "com.mysql.jdbc.Driver";
      String userName = "root";
       // String password = "ninelights@mora";
         String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException ex) {
             JOptionPane.showMessageDialog(null, "Connection failed");
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
        Statement st;
        st= db.conn.createStatement();
         res = st.executeQuery(query);
         st.closeOnCompletion();
        return res;
    }
    
    
    public void dishconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        Statement st ;
        
        st = db.conn.createStatement();
         result = st.executeUpdate(insertQuery);
       st.closeOnCompletion();
        return result;
    }

    public String checknReturnData(String table_name, String table_column_giving, Object row_element, String table_column_need) {
       a = null;
        try {
             ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
               a = (query.getString(table_column_need));
            }
            
           query.close();
            return a;
            //System.out.println(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        //return null;
    }

    public int readLastRow(String table, String coloumn) throws SQLException {

        ResultSet rs1 = query("SELECT " + coloumn + " FROM " + table + " ORDER BY " + coloumn + " DESC LIMIT 1");
        while (rs1.next()) {
            return (rs1.getInt(coloumn));
        }
        rs1.close();
        return 0;
    }

    public int readFirstRow(String table, String coloumn) throws SQLException {

        ResultSet rs1 = query("SELECT " + coloumn + " FROM " + table);
        if (rs1.next()) {
            return (rs1.getInt(coloumn));
        }
        return 0;
    }

    public String checknReturnStringData(String table_name, String table_column_giving, String row_element, String table_column_need) {
     String a = null;
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                a= (query.getString(table_column_need));
            }
            query.close();
            return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
       // return null;
    }

    public Date checknReturnData() {

        String temp;
        String max_date = "1900-01-01";
        Date date1 = java.sql.Date.valueOf("1900-01-01");
        try {
            ResultSet query = query("SELECT * FROM account_update_details");
            while (query.next()) {
                temp = query.getString("date");
                if (temp.compareTo(max_date) >= 0) {
                    max_date = temp;

                }
            }
            query.close();
            Date date = java.sql.Date.valueOf(max_date);

            return date;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return date1;
        }

    }

    public String filterReturn2StringData(String table_name, String table_column_giving, String row_element, String table_column_giving2, String row_element2, String table_column_need) {
  String a = null;
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'");
            while (query.next()) {
                a= (query.getString(table_column_need));
            }
            
            query.close();
            return  a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
       // return null;
    }
    
     public String filterReturn3StringData(String table_name, String table_column_giving, String row_element, String table_column_giving2, String row_element2,String table_column_giving3, String row_element3, String table_column_need) {
  String a = null;
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'AND " + table_column_giving3 + " LIKE '" + row_element3 + "'");
            while (query.next()) {
                a= (query.getString(table_column_need));
            }
            
            query.close();
            return  a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
       // return null;
    }

    public double checknReturnDoubleData(String table_name, String table_column_giving, Object row_element, String table_column_need) {
           d = 0;
        try{
               ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " = '" + row_element + "'"); 
            while (query.next()) {
                d= (query.getDouble(table_column_need));
            }
            query.close();
            return d;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // return "" + ex.getErrorCode();
        }
        return 0;
    }

    public String checknReturnStringDataReceipts(String table_name, String table_column_giving, Object row_element, String table_column_need) {
  String a= null;
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                a = (query.getString(table_column_need));
            }
            query.close();
            return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
       // return null;
    }

    public boolean updateDatabase(String table_name, String table_column_giving, Object row_element, String table_column_need, Object update_element) {

        try {

            insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' WHERE " + table_column_giving + "='" + row_element + "'");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }

    public void update(String table, String coloumn1, String coloumn2, Object first, Object second, String coloumnN, Object new1) {

        try {
            insert("UPDATE " + table + " SET " + coloumnN + " ='" + new1 + "' WHERE " + coloumn1 + "='" + first + " 'AND " + coloumn2 + " ='" + second + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newDayTable(Date date) {

        try {
            insert("CREATE TABLE " + date.toString() + "(name varchar(25),"
                    + "employee_code varchar(10),"
                    + "workCode varchar(10));");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getStringArray(String table_name, String column_name) {

        int count = 0;

        try {
            ResultSet query = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            query.close();
            String[] array = new String[count + 1];
            array[0] = null;
            count = 1;
            ResultSet query2 = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            query2.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public String[] getStringArray2(String table_name, String column_name) {

        int count = 0;

        try {
            ResultSet query = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            query.close();
            String[] array = new String[count];
            count = 0;
            ResultSet query2 = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            query2.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public int[] getArray(String table_name, String column_name) {

        int count = 0;

        try {
            ResultSet query = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            query.close();
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            query2.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public int[] getValuesArray(String table, String coloumn, String neededColoumn, String match) {
        try {
            int count = 0;
            // DatabaseManager dbm = DatabaseManager.getDbCon();
            ResultSet rs1 = query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + match + "'");
            while (rs1.next()) {
                count++;
            }
            rs1.close();
            int[] valuesArray = new int[count];
            count = 0;
            ResultSet rs2 = query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + match + "'");
            while (rs2.next()) {
                valuesArray[count] = rs2.getInt(neededColoumn);
                count++;
            }
            rs2.close();
            return valuesArray;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
        return null;
    }

    public int[] getValuesArrayN(String table, String coloumn, String neededColoumn, String match) {
        try {
            int count = 0;
            // DatabaseManager dbm = DatabaseManager.getDbCon();
            ResultSet rs1 = query("SELECT * FROM " + table + " WHERE " + coloumn + " NOT LIKE '" + match + "'");
            while (rs1.next()) {
                count++;
            }
            rs1.close();
            int[] valuesArray = new int[count];
            count = 0;
            ResultSet rs2 = query("SELECT * FROM " + table + " WHERE " + coloumn + " NOT LIKE '" + match + "'");
            while (rs2.next()) {
                valuesArray[count] = rs2.getInt(neededColoumn);
                count++;
            }
            rs2.close();
            return valuesArray;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
        return null;
    }

    public String[] getStringArray1(String table_name, String column_name) {

        int count = 0;
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            query.close();
            String[] array = new String[count + 2];
            array[0] = null;
            array[1] = "All";
            count = 2;
            ResultSet query2 = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            query2.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public String[] PRCRWorkerSearch(String table, String coloumn1, String coloumn2, String coloumn3, Object element1, Object element2, Object element3, String target) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        String workcodes[];
        int i = 0;
        try {
            ResultSet rs1 = query("SELECT * FROM " + table + " WHERE " + coloumn1 + " ='" + element1 + " 'AND " + coloumn2 + " ='" + element2 + " 'AND " + coloumn3 + " ='" + element3 + "'");
            while (rs1.next()) {
                i++;
            }
            rs1.close();
            workcodes = new String[i];
            i = 0;
            ResultSet rs2 = query("SELECT * FROM " + table + " WHERE " + coloumn1 + " ='" + element1 + " 'AND " + coloumn2 + " ='" + element2 + " 'AND " + coloumn3 + " ='" + element3 + "'");
            while (rs2.next()) {
                workcodes[i] = rs2.getString(target);
                i++;
            }
            rs2.close();
            return workcodes;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String[] search_PRCR(String table_name, String column_1, String column_2, Object element_1, Object element_2, String needed_column) {
        //  DatabaseManager dbm = DatabaseManager.getDbCon();
        //String workcodes[] = null;
        String workcodes[];
        int i = 0;
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + column_1 + " ='" + element_1 + " 'AND " + column_2 + " ='" + element_2 + "'");
            //int Fetchsize = query.getFetchSize();
            //workcodes = new String[Fetchsize]; 
            while (query.next()) {
                //workcodes[i] = query.getString(needed_column);
                i++;
            }
            
            query.close();
            workcodes = new String[i];
            i = 0;
            ResultSet query_1 = query("SELECT * FROM " + table_name + " WHERE " + column_1 + " ='" + element_1 + " 'AND " + column_2 + " ='" + element_2 + "'");
            while (query_1.next()) {
                workcodes[i] = query_1.getString(needed_column);
                i++;
            }
            query_1.close();
            return workcodes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();
        }
        return null;
    }

    public double checknReturnDataForCashAdvances(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, Object row_element3, String table_column_need) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        double value = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");

            while (query.next()) {
                value = value + query.getDouble(table_column_need);
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return value;
    }

    public String[] checknReturnDataForCashAdvances_onebyone(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, Object row_element3, String table_column_need) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();

        int i = 0;
        int j = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");

            while (query.next()) {
                i++;

            }
            query.close();

            String[] Arr = new String[i];
            ResultSet query2 = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");
            while (query2.next()) {
                Arr[j] = query2.getString(table_column_need);
                j++;
            }
            
            query2.close();
            return Arr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return null;
    }

    // when table name and a column is given returns the elements in that column in the acsending order
    public String[] ReturnSortedArray(String table_name, String table_column) {
        int count = 0;
        //  DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("SELECT * FROM " + table_name + " ORDER BY " + table_column + " ASC");
            while (query.next()) {
                count++;
            }
            query.close();
            String[] array = new String[count];
            count = 0;
            ResultSet query2 = query("SELECT * FROM " + table_name + " ORDER BY " + table_column + " ASC");
            while (query2.next()) {
                array[count] = query2.getString(table_column);
                count++;
            }
            query2.close();
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    
    
    // Get entries for the gl_cash_advance_book
    public boolean Inserting_To_The_Table(javax.swing.JTable table, String table_name, String column_name, int table_column_num, int bottom, int top) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int count = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        // DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            ResultSet query = query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
            
            query.close();
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = query("SELECT * FROM " + table_name + "");
                while (query.next()) {
                    count++;
                    if (count < bottom) {

                    } else if (count >= bottom && count <= top) {
                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                        num_of_rows_filled_in_table++;
                    } else {
                        break;
                    }
                }
                query.close();
            } catch (SQLException ex) {

            }
        } else {
            return false;

        }
        return true;
    }

    public boolean Inserting_To_The_Table_ordered(javax.swing.JTable table, String table_name, String column_name, int table_column_num, int bottom, int top, String Order_by) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int count = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        // DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " order by  " + Order_by + "  ");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
            query.close();
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = query("SELECT * FROM " + table_name + "  order by  " + Order_by + "");
                while (query.next()) {
                    count++;
                    if (count < bottom) {

                    } else if (count >= bottom && count <= top) {
                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                        num_of_rows_filled_in_table++;
                    } else {
                        break;
                    }
                }
                query.close();
            } catch (SQLException ex) {

            }
        } else {
            return false;

        }
        return true;
    }

    public int Inserting_To_The_Table_Filtered(javax.swing.JTable table, String table_name, String column_name, int table_column_num, int bottom, int top, String column_filtering, String element) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int count = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + column_filtering + " LIKE '" + element + "'");
            while (query.next()) {
                num_of_rows_in_the_database++;

            }
            query.close();
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = query("SELECT * FROM " + table_name + " where " + column_filtering + " LIKE '" + element + "'");
                while (query.next()) {
                    count++;
                    if (count < bottom) {

                    } else if (count >= bottom && count <= top) {
                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                        num_of_rows_filled_in_table++;
                    } else {
                        break;
                    }
                }
                query.close();
            } catch (SQLException ex) {

            }
        } else {

            return num_of_rows_in_the_database;

        }

        return num_of_rows_in_the_database;
    }

    public boolean Inserting_To_The_Table_Filtered_Between_Two(javax.swing.JTable table, String table_name, String column_name, int table_column_num, int bottom, int top, String column_filtering, String element1, String element2) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int count = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
            query.close();
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = query("SELECT * FROM " + table_name + " where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                while (query.next()) {
                    count++;
                    if (count < bottom) {

                    } else if (count >= bottom && count <= top) {
                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                        num_of_rows_filled_in_table++;
                    } else {
                        break;
                    }
                }
                query.close();
            } catch (SQLException ex) {

            }
        } else {
            return false;

        }
        return true;
    }

    public int Checking_Length_Of_The_Table(String table_name, String column_name) {

        int num_of_rows_in_the_database = 0;

        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
            query.close();
        } catch (SQLException ex) {

        }

        return num_of_rows_in_the_database;
    }

    public void CheckNDeleteFromDataBase(String table_name, String column_name, Object element) {
        // DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            insert("DELETE FROM " + table_name + " WHERE " + column_name + " = '" + element + "' ");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void DeleteTable(String table_name) {
        // DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            insert("DELETE FROM " + table_name + " ");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public boolean TableExistenceCheck(String table_name) throws SQLException {

        DatabaseMetaData meta = conn.getMetaData();
        try {
            ResultSet res = meta.getTables(null, null, table_name, null);

            if (!res.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean TableExistence(String table) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            if (dbm.TableExistenceCheck(table) == true) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

// Copy one column of a table to another column in another table
    public void CopyTableColumn(String table_name1, String table_column1, String table_name2, String table_column2) {

        //  DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    insert("INSERT INTO " + table_name2 + "(" + table_column2 + ") VALUES(" + query.getString(table_column1) + ")");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
            query.close();
        } catch (SQLException ex) {

        }
    }

    public void CopyTable2Columns(String table_name1, String table1_column1, String table1_column2, String table_name2, String table2_column1, String table2_column2) {

        // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + ") VALUES(" + query.getString(table1_column1) + "," + query.getString(table1_column2) + ")");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
            query.close();
        } catch (SQLException ex) {

        }
    }

    public double checknReturnTotalForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        //  DatabaseManager dbm = DatabaseManager.getDbCon();
        double value = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                
                value = value + query.getDouble(table_column_need);
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return value;
    }

    public int checknReturnNumberOfEntriesForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                count++;
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return count;
    }

    public int[] checknReturnIntArrayForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2, table_column_need);
        int[] arr = new int[num];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                arr[count] = query.getInt(table_column_need);
                count++;
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return arr;
    }

    public String checknReturnDataBetweenTwoDays(String table_name, String date_column, Object date1, Object date2, String column_other_data, Object other_data, String column_return_data) {
      String a = null;  // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + table_name + " where " + column_other_data + " LIKE '" + other_data + " 'AND " + date_column + " BETWEEN'" + date1 + "' AND '" + date2 + "'");

            while (query.next()) {
                a=  query.getString(column_return_data);
            }
            query.close();
            return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return null;
    }

    public int checkWhetherDataExists(String table_name, String table_column_giving, Object row_element) {
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            try (ResultSet query = query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'")) {
                if (!query.next()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return 0;
    }

    // Copy data from one table and append to another
    public void copy_and_append(String old_table, String new_table) {
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("INSERT INTO " + new_table + " SELECT * FROM " + old_table + "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String copy_and_ADD(String old_table, String new_table) {
        //   DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = query("INSERT INTO " + new_table + " SELECT * FROM " + old_table + "");
            return old_table;
        } catch (SQLException ex) {
            String abcd = " . ";
            return abcd;
        }
    }

    // copy data to a new table
    public void copy_to_new_table(String old_table, String new_table) {
        //  DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            query("SELECT * INTO aa FROM account_journal_creditside WHERE 1=0");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] apply() {
        // DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        String a[]= new String[2];
        //int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2, table_column_need);
        int[] arr = new int[10];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = query("SELECT * FROM " + count + " WHERE " + count + " ='" + count + " 'AND " + count + " LIKE'" + count + "'");
            a = null;
            while (query.next()) {
                arr[count] = query.getInt(count);
                count++;
            }
            query.close();
            
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            a[0] = "Windows";
            a[1]= "WINDOWS";
            //return ""+ex.getErrorCode();            
        }
        return a;

    }

    public double prcr_loan(int emp_code, String year, String month) {

        // DatabaseManager dbm = new DatabaseManager();
        double tot = 0;
        String year_month = year + "-" + month;
        try {
            ResultSet query = query("SELECT * FROM prcr_loans WHERE emp_id LIKE'" + emp_code + "' AND month_year LIKE '" + year_month + "'");
            while (query.next()) {
                tot = tot + query.getDouble("monthly_amount");
            }
            query.close();
            return tot;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int[] prcr_loan_emp_code_array(String year, String month) {
        // DatabaseManager dbm = new DatabaseManager();

        String year_month = year + "-" + month;
        int i = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_loans WHERE month_year LIKE '" + year_month + "'");
            while (query.next()) {
                i++;
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] arr = new int[i];
        i = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_loans WHERE month_year LIKE '" + year_month + "'");
            while (query.next()) {
                arr[i] = query.getInt("emp_id");
                i++;
            }
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    public double prcr_welfare(int emp_code, String year, String month) {

        String mnth = "" + Integer.parseInt(month);
        String year_month = year + "-" + mnth;
        double d=0;

       // DatabaseManager dbm = new DatabaseManager();
        try {
            ResultSet query = query("SELECT * FROM prcr_welfare WHERE code LIKE'" + emp_code + "' AND month LIKE '" + year_month + "'");
            while (query.next()) {
                d= query.getDouble("amount");
            }
            query.close();
            return 0;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int[] prcr_welfare_emp_code_array(String year, String month) {
        //   DatabaseManager dbm = new DatabaseManager();

        int i = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_welfare_members");
            while (query.next()) {
                i++;
            }
             query.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] arr = new int[i];
        i = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_welfare_members");
            while (query.next()) {
                arr[i] = query.getInt("emp_code");
                i++;
            }
             query.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    
    int num_required;
    double[] arr;
    public double[] prcr_emp_code_month_totals(String table_name, String year_month, String table_date_column, String table_emp_code_column, int emp_code, String[] required_columns) {

        num_required = required_columns.length;

        arr = new double[num_required];

        String start = year_month + "-01";
        String end = year_month + "-31";
        int i = 0;

        for (i = 0; i < num_required; i++) {
            arr[i] = 0;
        }
        i = 0;

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                for (i = 0; i < num_required; i++) {
                    arr[i] = arr[i] + query.getDouble(required_columns[i]);
                }
            }
            query.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return arr;

    }

    int[] arr_2 = new int[2];
    public int[] prcr_emp_code_month_totals_normalOrsunday(String table_name, String year_month, String table_date_column, String table_emp_code_column, int emp_code, String required_column) {

        

        String start = year_month + "-01";
        String end = year_month + "-31";
        int i = 0;

        for (i = 0; i < 2; i++) {
            arr_2[i] = 0;
        }
        i = 0;

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                if ("n".equals(query.getString(required_column))) {
                    arr_2[0] = arr_2[0] + 1;
                }
                if ("s".equals(query.getString(required_column))) {
                    arr_2[1] = arr_2[1] + 1;
                }
            }
             query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return arr_2;

    }
    int[] return_arr ;
    int[] arr_800 = new int[8000];

    public int[] prcr_active_emp_codes_for_month(String table_name, String year_month, String table_date_column, String table_emp_code_column) {

        int i = 0;
        int k = 0;
        int chk = 1;

      //  int[] arr = new int[8000];

        for (i = 0; i < 5000; i++) {
            arr_800[i] = 0;
        }

        String start = year_month + "-01";
        String end = year_month + "-31";

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE  " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                chk = 1;
                for (i = 0; i <= k; i++) {
                    if (query.getInt(table_emp_code_column) == arr_800[i]) {
                        chk = 0;
                        break;
                    }
                }
                if (chk == 1) {
                    arr_800[k] = query.getInt(table_emp_code_column);
                    k++;
                }
            }
             query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        i = 0;
        int num = 0;

        for (i = 0; i < 8000; i++) {
            if (arr_800[i] == 0) {
                break;
            }
            num++;
        }
      return_arr = new int[num];

        for (i = 0; i < num; i++) {
            return_arr[i] = arr_800[i];
        }

        return return_arr;

    }

    int[] arr_4 = new int[4];
    
    
        String start;
        String holy_first;
        String holy_second;
        String end;
    
    public int[] prcr_emp_code_May_month_totals_normalOrsunday(String table_name, String year_month, String table_date_column, String table_emp_code_column, int emp_code, String required_column, int holidays) {

        String holy_s;
        String holy_e;

        if (holidays < 10) {
            holy_s = "-0" + holidays;
        } else {
            holy_s = "-" + holidays;
        }

        int holiplusone = holidays + 1;

        if (holiplusone < 10) {
            holy_e = "-0" + holiplusone;
        } else {
            holy_e = "-" + holiplusone;
        }

        

        start = year_month + "-01";
        holy_first = year_month + holy_s;
        holy_second = year_month + holy_e;
        end = year_month + "-31";
        int i = 0;

        for (i = 0; i < 4; i++) {
            arr_4[i] = 0;
        }
        i = 0;

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_date_column + " BETWEEN '" + start + "' AND '" + holy_first + "'");
            while (query.next()) {
                if ("n".equals(query.getString(required_column))) {
                    arr_4[0] = arr_4[0] + 1;
                }
                if ("s".equals(query.getString(required_column))) {
                    arr_4[1] = arr_4[1] + 1;
                }
            }
             query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_date_column + " BETWEEN '" + holy_second + "' AND '" + end + "'");
            while (query.next()) {
                if ("n".equals(query.getString(required_column))) {
                    arr_4[2] = arr_4[2] + 1;
                }
                if ("s".equals(query.getString(required_column))) {
                    arr_4[3] = arr_4[3] + 1;
                }
            }
             query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return arr_4;

    }
double amount;
    public double prcr_emp_code_other_advance_month_totals(String table_name, String year_month, String table_date_column, String table_emp_code_column, int emp_code, String table_advance_type_column, String advance_type, String required_column) {

         amount = 0;

        start = year_month + "-01";
        end = year_month + "-31";

        try {
            ResultSet query = query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_advance_type_column + " LIKE '" + advance_type + "' AND " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {

                amount = amount + query.getDouble(required_column);

            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return amount;

    }

    int[] arr_int;
    public int[] active_staff_codes() {
        int count = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_staff_salary_info WHERE active LIKE '" + 1 + "'");
            while (query.next()) {
                count++;
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        arr_int = new int[count];
        int i = 0;
        try {
            ResultSet query = query("SELECT * FROM prcr_staff_salary_info WHERE active LIKE '" + 1 + "'");
            while (query.next()) {
                arr_int[i] = query.getInt("code");
                i++;
            }
            query.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr_int;
    }

    public void staff_fill(String table_name) {

        arr_int= active_staff_codes();
        int length = arr_int.length;
        int i = 0;
        for (i = 0; i < length; i++) {
            try {
                ResultSet query = query("SELECT * FROM prcr_staff_salary_info WHERE code LIKE '" + arr_int[i] + "' ");
                while (query.next()) {

                    updateDatabase(table_name,"code",arr_int[i],"active",1);
                    updateDatabase(table_name,"code",arr_int[i],"normal_pay",query.getDouble("basic_salary"));
                    updateDatabase(table_name,"code",arr_int[i],"ot_before_hours",query.getDouble("ot_hours"));
                    updateDatabase(table_name,"code",arr_int[i],"ot_before_amount",query.getDouble("ot_pay"));
                    updateDatabase(table_name,"code",arr_int[i],"ot_after_hours",query.getDouble("allowance1"));
                    updateDatabase(table_name,"code",arr_int[i],"ot_after_amount",query.getDouble("allowance2"));
                    updateDatabase(table_name,"code",arr_int[i],"incentive1",query.getDouble("Incentive1"));
                    updateDatabase(table_name,"code",arr_int[i],"incentive2",query.getDouble("Incentive2"));
                    updateDatabase(table_name,"code",arr_int[i],"extra_pay_may",query.getDouble("allowance3"));
                    updateDatabase(table_name,"code",arr_int[i],"extra_pay_cash",query.getDouble("Extra1"));
                    updateDatabase(table_name,"code",arr_int[i],"extra_pay_overkilos",query.getDouble("Extra2"));

                }
                query.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}

// category gategory id int------> String

