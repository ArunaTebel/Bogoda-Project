
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GL_other_advances_class {

    UserAccountControl UserAC = new UserAccountControl();
    Date_Handler date_handler = new Date_Handler();
    DatabaseManager dbm = new DatabaseManager();
    String user = UserAC.get_current_user();
    int sup_id;
    String sup_name;
    double max_allowable;
    double amount, month_total;
    Date date;
    String item_name, date_time;
    String item_type;
    double item_rate;
    double quantity;
    int inst;

    public GL_other_advances_class() {
        sup_id = 0;
        inst = 0;
        sup_name = null;
        max_allowable = 0;
        amount = 0;
        date = null;
        item_name = null;
        item_type = null;
        item_rate = 0;
        quantity = 0;
    }

    // Setters
    public void set_sup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public void set_installments(int INST) {
        this.inst = INST;
    }

    public void set_sup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public void set_max_allowable(double max_allowable) {
        this.max_allowable = max_allowable;
    }

    public void set_date(Date date) {
        this.date = date;
    }

    public void set_amount(double amount) {
        this.amount = amount;
    }

    public void set_item_rate(double amount) {
        this.item_rate = amount;
    }

    public void set_quantity(double amount) {
        this.quantity = amount;
    }

    public void set_item_name(String sup_name) {
        this.item_name = sup_name;
    }

    public void set_item_type(String sup_name) {
        this.item_type = sup_name;
    }

    // Getters
    public int get_sup_id() {
        return sup_id;
    }

    public String get_sup_name() {
        return sup_name;
    }

    public double get_max_allowable() {
        return max_allowable;
    }

    public Date get_date() {
        return date;
    }

    public double get_amount() {
        return amount;
    }

    // Add to database
    public boolean addToDataBase() {

        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO gl_other_advance_book(Date,id,name,max_allow,item_name,item_type,item_rate,item_quantity,total_amount,installments) VALUES('" + date + "','" + sup_id + "','" + sup_name + "','" + max_allowable + "','" + item_name + "','" + item_type + "','" + item_rate + "','" + quantity + "','" + amount + "','" + inst + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean tranfer() {
        date_time = date_handler.get_today_date_time();
        int index = dbm.Checking_Length_Of_The_Table("gl_other_advances", "tr_no");
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbCon.query("SELECT * FROM gl_other_advance_book");

            while (query.next()) {
                if (query.getInt(11) > 0) {
                    month_total = query.getDouble(10) / query.getInt(11);
                }

                if (query.getInt(11) > 1) {
                    // System.out.println(index);
                    //System.out.println("writing" + dbm.Checking_Length_Of_The_Table("suppliers", "sup_id"));
                    dbCon.insert("INSERT INTO gl_other_advance_installments(entry,date,id,item_name,inst_amount,inst_left,pay_status)"
                            + " VALUES('" + index + "','" + query.getDate(2) + "','" + query.getInt(3) + "','" + query.getString(6) + "','" + month_total + "','" + (query.getInt(11) - 1) + "','" + "NOT_PAYED" + "')");

                }

                // System.out.println("writing");
                dbCon.insert("INSERT INTO gl_other_advances(Date,id,item_name,item_type,item_rate,item_quantity,installments,amount,total_amount,date_time,user)"
                        + " VALUES('" + query.getDate("date") + "','" + query.getInt("id") + "','" + query.getString("item_name") + "','" + query.getString("item_type") + "','" + query.getDouble("item_rate") + "','" + query.getInt("item_quantity") + "','" + query.getInt("installments") + "','" + query.getDouble("total_amount") + "','" + month_total + "','" + date_time + "','" + user + "')");
                index++;
                // dbCon.insert("INSERT INTO gl_cash_advance(month_tr_no,sup_id,pay_type,ordered_date,issued_date,emergency,special_permission,amount,ref_no,bank_code,cheque_no,cheque_date,date_time,user)"
                //   + " VALUES('" + query.getInt("entry_no") + "','" +query.getInt("sup_id")+"','" + query.getString("pay_type") + "','" + query.getDate("date") + "','" + issue_date + "','"+ "NO" + "','"+ query.getString("special_permission") + "','"+query.getDouble("amount") + "','"+ ref_no + "','"+ bank + "','"+ cheque+ "','"+ cheque_date+"','"+ date_time + "','"+user+ "')");}
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public void enter() throws SQLException {
        date_time = date_handler.get_today_date_time();
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        double rate, monthlyPay, amount;
        int installments, supId;
        String Nmonth, year, day, month;
        Date date;
        ResultSet query = dbCon.query("SELECT * FROM gl_other_advance_book");

        while (query.next()) {
            try {

                date = query.getDate("Date");
                installments = query.getInt("installments");
                Nmonth = date_handler.get_month(date);
                month = date_handler.return_month_as_num(Nmonth);
                year = date_handler.get_year(date);
                day = date_handler.get_day(date);
                sup_id = query.getInt("id");
                item_name = query.getString("item_name");
                item_type = query.getString("item_type");
                item_rate = query.getInt("item_rate");
                quantity = query.getDouble("item_quantity");
                amount = query.getDouble("total_amount");
                double total_amount = amount / installments;

                String[] allMonths = new String[installments];
                int i;
                int monthNum = Integer.parseInt(month);
                int newMonth;
                if (Integer.parseInt(day) < 8) {
                    for (i = 0; i < allMonths.length; i++) {
                        newMonth = monthNum + i - 1;
                        if (newMonth > 12) {
                            newMonth = newMonth - 12;
                        }
                        allMonths[i] = String.valueOf(newMonth);
                    }
                } else {
                    for (i = 0; i < allMonths.length; i++) {
                        newMonth = monthNum + i;
                        if (newMonth > 12) {
                            newMonth = newMonth - 12;
                        }
                        allMonths[i] = String.valueOf(newMonth);
                    }
                }
                Date loanDate1;
                if (Integer.parseInt(day) < 8) {
                    loanDate1 = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 2, 8);
                } else {
                    loanDate1 = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, 8);
                }
                dbCon.insert("INSERT INTO gl_other_advances(advance_id,type,Date,issue_date,id,item_name,item_type,item_rate,item_quantity,installments,amount,total_amount,date_time,user)"
                        + " VALUES('" + 0 + "','Issued This month','" + loanDate1 + "','" + date + "','" + sup_id + "','" + item_name + "','" + item_type + "','" + item_rate + "','" + quantity + "','" + installments + "','" + amount + "','" + total_amount + "','" + date_time + "','" + user + "')");
                int transaction = dbm.readLastRow("gl_other_advances", "tr_no");
                dbCon.updateDatabase("gl_other_advances", "tr_no", transaction, "advance_id", transaction);
                for (i = 1; i < allMonths.length; i++) {
                    if (allMonths[i - 1].equals("12")) {
                        year = String.valueOf(Integer.parseInt(year) + 1);
                    }
                    Date date1 = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(allMonths[i]) - 1, 8);
                    dbCon.insert("INSERT INTO gl_other_advances(advance_id,type,Date,issue_date,id,item_name,item_type,item_rate,item_quantity,installments,amount,total_amount,date_time,user)"
                            + " VALUES('" + transaction + "','Previous','" + date1 + "','" + date + "','" + sup_id + "','" + item_name + "','" + item_type + "','" + item_rate + "','" + quantity + "','" + installments + "','" + amount + "','" + total_amount + "','" + date_time + "','" + user + "')");
                }
            } catch (Exception ex) {
                Logger.getLogger(GL_Loans.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
