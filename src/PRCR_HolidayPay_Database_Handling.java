
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PRCR_HolidayPay_Database_Handling {

    public Connection conn;
    private Statement statement;
    public static DatabaseManager db;

    PRCR_HolidayPay_Database_Handling() {

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

    public int Num_of_days_for_employee_for_year(int emp_code, int year) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int i = 1;
        int tot_days = 0;
        String s;
        String normal_days = "normal_days";
        String sundays = "sundays";
        for (i = 1; i <= 12; i++) {

            if (i < 10) {
                s = "pr_workdata_" + year + "_0" + i;
            } else {
                s = "pr_workdata_" + year + "_" + i;
            }
            if (dbm.TableExistence(s) == true) {
                try {
                    ResultSet query = dbm.query("SELECT * FROM " + s + " WHERE code =" + emp_code + "");
                    while (query.next()) {
                        if (query.getString(normal_days) != null) {
                            tot_days = tot_days + Integer.parseInt(query.getString(normal_days));
                        }
                        if (query.getString(sundays) != null) {
                            tot_days = tot_days + Integer.parseInt(query.getString(sundays));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return tot_days;
    }

    public int Num_of_days_for_employee_for_month(int emp_code, int year, int month) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int i = 1;
        int tot_days = 0;
        String s;
        String normal_days = "normal_days";
        String sundays = "sundays";

        if (month < 10) {
            s = "pr_workdata_" + year + "_0" + month;
        } else {
            s = "pr_workdata_" + year + "_" + month;
        }
        if (dbm.TableExistence(s) == true) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + s + " WHERE code =" + emp_code + "");
                while (query.next()) {
                    if (query.getString(normal_days) != null) {
                        tot_days = tot_days + Integer.parseInt(query.getString(normal_days));
                    }
                    if (query.getString(sundays) != null) {
                        tot_days = tot_days + Integer.parseInt(query.getString(sundays));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return tot_days;

    }

    public int Num_of_days_for_employee_from_to(int emp_code, int from_year, int from_month, int to_year, int to_month) {

        int i, j;
        int tot_days = 0;
        for (i = from_year; i <= to_year; i++) {
            j = 1;
            for (j = from_month; j <= 12; j++) {

                tot_days = tot_days + Num_of_days_for_employee_for_month(emp_code, i, j);

                if (i == to_year && j == to_month) {
                    return tot_days;
                }

            }
        }
        return tot_days;
    }

    public int Num_of_holidays_male(int days) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        String ndays = "days";

        try {
            ResultSet query = dbm.query("SELECT * FROM  holiday_pay_rate_details_male WHERE lower <= '" + days + "' AND upper >= '" + days + "' ");
            while (query.next()) {
                System.out.println("Hello");
                return Integer.parseInt(query.getString(ndays));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;

    }

    public int Num_of_holidays_female(int days) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        String ndays = "days";

        try {
            ResultSet query = dbm.query("SELECT * FROM  holiday_pay_rate_details_female WHERE lower <= '" + days + "' AND upper >= '" + days + "' ");
            while (query.next()) {
                System.out.println("Hello");
                return Integer.parseInt(query.getString(ndays));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;

    }

    public int Sallary_for_employee_for_year(int emp_code, int year) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int i = 1;
        int tot_salary = 0;
        String s;
        String full_salary = "full_salary";
        for (i = 1; i <= 12; i++) {

            if (i < 10) {
                s = "pr_workdata_" + year + "_0" + i;
            } else {
                s = "pr_workdata_" + year + "_" + i;
            }
            if (dbm.TableExistence(s) == true) {
                try {
                    ResultSet query = dbm.query("SELECT * FROM " + s + " WHERE code =" + emp_code + "");
                    while (query.next()) {
                        if (query.getString(full_salary) != null) {
                            tot_salary = tot_salary + Integer.parseInt(query.getString(full_salary));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return tot_salary;
    }

    public int Salary_for_employee_for_month(int emp_code, int year, int month) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        int i = 1;
        int tot_salary = 0;
        String s;
        String full_salary = "full_salary";

        if (month < 10) {
            s = "pr_workdata_" + year + "_0" + month;
        } else {
            s = "pr_workdata_" + year + "_" + month;
        }
        if (dbm.TableExistence(s) == true) {
            try {
                ResultSet query = dbm.query("SELECT * FROM " + s + " WHERE code =" + emp_code + "");
                while (query.next()) {
                    if (query.getString(full_salary) != null) {
                        tot_salary = tot_salary + Integer.parseInt(query.getString(full_salary));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return tot_salary;

    }

    public int Salary_for_employee_from_to(int emp_code, int from_year, int from_month, int to_year, int to_month) {

        int i, j;
        int tot_salary = 0;
        for (i = from_year; i <= to_year; i++) {
            j = 1;
            for (j = from_month; j <= 12; j++) {

                tot_salary = tot_salary + Num_of_days_for_employee_for_month(emp_code, i, j);

                if (i == to_year && j == to_month) {
                    return tot_salary;
                }

            }
        }
        return tot_salary;
    }

    public int num_of_holidays_for(int this_year, int emp_code) {

        DatabaseManager dbm = new DatabaseManager();

        if (Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", emp_code, "gender")) == 1) {

            return Num_of_holidays_male(Num_of_days_for_employee_for_year(emp_code, this_year - 1));
        } else {
            return Num_of_holidays_female(Num_of_days_for_employee_for_year(emp_code, this_year - 1));

        }

    }
}
