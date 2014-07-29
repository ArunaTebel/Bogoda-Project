
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    DatabaseManager() {

        //String url = "jdbc:mysql://192.168.1.71/";
        // String userName = "BG";
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

    public String checknReturnData(String table_name, String table_column_giving, Object row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        return null;
    }

    public int readLastRow(String table, String coloumn) throws SQLException {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        ResultSet rs1 = dbm.query("SELECT " + coloumn + " FROM " + table + " ORDER BY " + coloumn + " DESC LIMIT 1");
        while (rs1.next()) {
            return (rs1.getInt(coloumn));
        }
        return 0;
    }

    public int readFirstRow(String table, String coloumn) throws SQLException {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        ResultSet rs1 = dbm.query("SELECT " + coloumn + " FROM " + table);
        if (rs1.next()) {
            return (rs1.getInt(coloumn));
        }
        return 0;
    }

    public String checknReturnStringData(String table_name, String table_column_giving, String row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        return null;
    }

    public String filterReturn2StringData(String table_name, String table_column_giving, String row_element, String table_column_giving2, String row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        return null;
    }

    public double checknReturnDoubleData(String table_name, String table_column_giving, Object row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " = '" + row_element + "'");
            while (query.next()) {
                return (query.getDouble(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // return "" + ex.getErrorCode();
        }
        return 0;
    }

    public String checknReturnStringDataReceipts(String table_name, String table_column_giving, Object row_element, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            while (query.next()) {
                return (query.getString(table_column_need));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "" + ex.getErrorCode();
        }
        return null;
    }

    public boolean updateDatabase(String table_name, String table_column_giving, Object row_element, String table_column_need, Object update_element) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            dbm.insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' WHERE " + table_column_giving + "='" + row_element + "'");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }

    public void update(String table, String coloumn1, String coloumn2, Object first, Object second, String coloumnN, Object new1) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            dbm.insert("UPDATE " + table + " SET " + coloumnN + " ='" + new1 + "' WHERE " + coloumn1 + "='" + first + " 'AND " + coloumn2 + " ='" + second + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newDayTable(Date date) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            dbm.insert("CREATE TABLE " + date.toString() + "(name varchar(25),"
                    + "employee_code varchar(10),"
                    + "workCode varchar(10));");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getStringArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count + 1];
            array[0] = null;
            count = 1;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public String[] getStringArray2(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public int[] getArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public int[] getValuesArray(String table, String coloumn, String neededColoumn, String match) {
        try {
            int count = 0;
            DatabaseManager dbm = DatabaseManager.getDbCon();
            ResultSet rs1 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + match + "'");
            while (rs1.next()) {
                count++;
            }
            int[] valuesArray = new int[count];
            count = 0;
            ResultSet rs2 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + match + "'");
            while (rs2.next()) {
                valuesArray[count] = rs2.getInt(neededColoumn);
                count++;
            }
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
            DatabaseManager dbm = DatabaseManager.getDbCon();
            ResultSet rs1 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " NOT LIKE '" + match + "'");
            while (rs1.next()) {
                count++;
            }
            int[] valuesArray = new int[count];
            count = 0;
            ResultSet rs2 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " NOT LIKE '" + match + "'");
            while (rs2.next()) {
                valuesArray[count] = rs2.getInt(neededColoumn);
                count++;
            }
            return valuesArray;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error");
        }
        return null;
    }

    public String[] getStringArray1(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count + 2];
            array[0] = null;
            array[1] = "All";
            count = 2;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getString(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;
    }

    public String[] PRCRWorkerSearch(String table, String coloumn1, String coloumn2, String coloumn3, Object element1, Object element2, Object element3, String target) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String workcodes[];
        int i = 0;
        try {
            ResultSet rs1 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn1 + " ='" + element1 + " 'AND " + coloumn2 + " ='" + element2 + " 'AND " + coloumn3 + " ='" + element3 + "'");
            while (rs1.next()) {
                i++;
            }
            workcodes = new String[i];
            i = 0;
            ResultSet rs2 = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn1 + " ='" + element1 + " 'AND " + coloumn2 + " ='" + element2 + " 'AND " + coloumn3 + " ='" + element3 + "'");
            while (rs2.next()) {
                workcodes[i] = rs2.getString(target);
                i++;
            }
            return workcodes;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String[] search_PRCR(String table_name, String column_1, String column_2, Object element_1, Object element_2, String needed_column) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        //String workcodes[] = null;
        String workcodes[];
        int i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + column_1 + " ='" + element_1 + " 'AND " + column_2 + " ='" + element_2 + "'");
            //int Fetchsize = query.getFetchSize();
            //workcodes = new String[Fetchsize]; 
            while (query.next()) {
                //workcodes[i] = query.getString(needed_column);
                i++;
            }
            workcodes = new String[i];
            i = 0;
            ResultSet query_1 = dbm.query("SELECT * FROM " + table_name + " WHERE " + column_1 + " ='" + element_1 + " 'AND " + column_2 + " ='" + element_2 + "'");
            while (query_1.next()) {
                workcodes[i] = query_1.getString(needed_column);
                i++;
            }
            return workcodes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();
        }
        return null;
    }

    public double checknReturnDataForCashAdvances(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, Object row_element3, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double value = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");

            while (query.next()) {
                value = value + query.getDouble(table_column_need);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return value;
    }

    public String[] checknReturnDataForCashAdvances_onebyone(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, Object row_element3, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();

        int i = 0;
        int j = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");

            while (query.next()) {
                i++;

            }

            String[] Arr = new String[i];
            ResultSet query2 = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " BETWEEN'" + row_element2 + "' AND '" + row_element3 + "'");
            while (query2.next()) {
                Arr[j] = query2.getString(table_column_need);
                j++;
            }
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " ORDER BY " + table_column + " ASC");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT * FROM " + table_name + " ORDER BY " + table_column + " ASC");
            while (query2.next()) {
                array[count] = query2.getString(table_column);
                count++;
            }
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
        DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + table_name + "");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " order by  " + Order_by + "  ");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + table_name + "  order by  " + Order_by + "");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + column_filtering + " LIKE '" + element + "'");
            while (query.next()) {
                num_of_rows_in_the_database++;

            }
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + column_filtering + " LIKE '" + element + "'");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
        } catch (SQLException ex) {

        }

        if (num_of_rows_in_the_database >= bottom) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
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
        DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                num_of_rows_in_the_database++;
            }
        } catch (SQLException ex) {

        }

        return num_of_rows_in_the_database;
    }

    public void CheckNDeleteFromDataBase(String table_name, String column_name, Object element) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("DELETE FROM " + table_name + " WHERE " + column_name + " = '" + element + "' ");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void DeleteTable(String table_name) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("DELETE FROM " + table_name + " ");
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

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table_column2 + ") VALUES(" + query.getString(table_column1) + ")");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }
    }

    public void CopyTable2Columns(String table_name1, String table1_column1, String table1_column2, String table_name2, String table2_column1, String table2_column2) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + ") VALUES(" + query.getString(table1_column1) + "," + query.getString(table1_column2) + ")");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }
    }

    public double checknReturnTotalForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        double value = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                value = value + query.getDouble(table_column_need);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return value;
    }

    public int checknReturnNumberOfEntriesForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return count;
    }

    public int[] checknReturnIntArrayForNoteAnalysis(String table_name, String table_column_giving1, Object row_element1, String table_column_giving2, Object row_element2, String table_column_need) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2, table_column_need);
        int[] arr = new int[num];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 + " LIKE'" + row_element2 + "'");

            while (query.next()) {
                arr[count] = query.getInt(table_column_need);
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return arr;
    }

    public String checknReturnDataBetweenTwoDays(String table_name, String date_column, Object date1, Object date2, String column_other_data, Object other_data, String column_return_data) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + column_other_data + " LIKE '" + other_data + " 'AND " + date_column + " BETWEEN'" + date1 + "' AND '" + date2 + "'");

            while (query.next()) {
                return query.getString(column_return_data);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //return ""+ex.getErrorCode();            
        }
        return null;
    }

    public int checkWhetherDataExists(String table_name, String table_column_giving, Object row_element) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving + " LIKE '" + row_element + "'");
            if (!query.next()) {
                return 0;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return 0;
    }

    // Copy data from one table and append to another
    public void copy_and_append(String old_table, String new_table) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("INSERT INTO " + new_table + " SELECT * FROM " + old_table + "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String copy_and_ADD(String old_table, String new_table) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("INSERT INTO " + new_table + " SELECT * FROM " + old_table + "");
            return old_table;
        } catch (SQLException ex) {
            String abcd = "The system returned with an unexpected error" + "\n" + "error code: 291343Fa" + "\n" + "please contact Admin";
            return abcd;
        }
    }

    // copy data to a new table
    public void copy_to_new_table(String old_table, String new_table) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            dbm.query("SELECT * INTO aa FROM account_journal_creditside WHERE 1=0");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String apply() {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int count = 0;
        String a;
        //int num = checknReturnNumberOfEntriesForNoteAnalysis(table_name, table_column_giving1, row_element1, table_column_giving2, row_element2, table_column_need);
        int[] arr = new int[10];
        try {
            //     ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_column_giving1 + " ='" + row_element1 + " 'AND " + table_column_giving2 +" <'" + row_element2 + "'");
            ResultSet query = dbm.query("SELECT * FROM " + count + " WHERE " + count + " ='" + count + " 'AND " + count + " LIKE'" + count + "'");
            a = null;
            while (query.next()) {
                arr[count] = query.getInt(count);
                count++;
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            a = "Windows";
            //return ""+ex.getErrorCode();            
        }
        return a;

    }

    public double prcr_loan(int emp_code, String year, String month) {

        DatabaseManager dbm = new DatabaseManager();
        double tot = 0;
        String year_month = year + "-" + month;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_loans WHERE emp_id LIKE'" + emp_code + "' AND month_year LIKE '" + year_month + "'");
            while (query.next()) {
                tot = tot + query.getDouble("monthly_amount");
            }
            return tot;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int[] prcr_loan_emp_code_array(String year, String month) {
        DatabaseManager dbm = new DatabaseManager();

        String year_month = year + "-" + month;
        int i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_loans WHERE month_year LIKE '" + year_month + "'");
            while (query.next()) {
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] arr = new int[i];
        i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_loans WHERE month_year LIKE '" + year_month + "'");
            while (query.next()) {
                arr[i] = query.getInt("emp_id");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    public double prcr_welfare(int emp_code, String year, String month) {

        String mnth = "" + Integer.parseInt(month);
        String year_month = year + "-" + mnth;

        DatabaseManager dbm = new DatabaseManager();

        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_welfare WHERE emp_id LIKE'" + emp_code + "' AND month LIKE '" + year_month + "'");
            while (query.next()) {
                return query.getDouble("amount");
            }
            return 0;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int[] prcr_welfare_emp_code_array(String year, String month) {
        DatabaseManager dbm = new DatabaseManager();

        int i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_welfare_members");
            while (query.next()) {
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] arr = new int[i];
        i = 0;
        try {
            ResultSet query = dbm.query("SELECT * FROM prcr_welfare_members");
            while (query.next()) {
                arr[i] = query.getInt("emp_id");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    public double[] prcr_emp_code_month_totals(String table_name, String year_month, String table_date_column, String table_emp_code_column, int emp_code, String[] required_columns) {

        DatabaseManager dbm = new DatabaseManager();
        int num_required = required_columns.length;

        double[] arr = new double[num_required];

        String start = year_month + "-01";
        String end = year_month + "-31";
        int i = 0;

        for (i = 0; i < num_required; i++) {
            arr[i] = 0;
        }
        i = 0;

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE " + table_emp_code_column + " LIKE '" + emp_code + "' AND " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                for (i = 0; i < num_required; i++) {
                    arr[i] = arr[i] + query.getDouble(required_columns[i]);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return arr;

    }

    public int[] prcr_active_emp_codes_for_month(String table_name, String year_month, String table_date_column, String table_emp_code_column) {

        DatabaseManager dbm = new DatabaseManager();
        int i = 0;
        int k = 0;
        int chk=1;

        int[] arr=new int[8000];
        
        for(i=0;i<5000;i++){
            arr[i]=0;
        }

        String start = year_month + "-01";
        String end = year_month + "-31";
        

        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name + " WHERE  " + table_date_column + " BETWEEN '" + start + "' AND '" + end + "'");
            while (query.next()) {
                chk=1;
                for (i = 0; i <= k; i++) {
                    if(query.getInt(table_emp_code_column)==arr[i]){
                        chk=0;
                        break;
                    }
                }
                if(chk==1){
                    arr[k]=query.getInt(table_emp_code_column);
                    k++;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        i=0;
        int num=0;
        
        for(i=0;i<8000;i++){
            if(arr[i]==0){
                break;
            }
            num++;
        }
        int[] return_arr = new int[num];
        
        for(i=0;i<num;i++){
            return_arr[i]=arr[i];
        }

        return return_arr;

    }

}

// category gategory id int------> String

