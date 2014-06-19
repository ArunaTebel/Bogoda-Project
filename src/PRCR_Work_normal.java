
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class PRCR_Work_normal extends javax.swing.JPanel {

    /**
     * Creates new form PRCR_Work_normal
     */
    Interface_Events interface_events = new Interface_Events();
    Date_Handler datehandler = new Date_Handler();
    DateChooser_text datechooser = new DateChooser_text();
    DatabaseManager dbm = DatabaseManager.getDbCon();
    private int rows = 0;

    public PRCR_Work_normal() {
        initComponents();
    }

    //create a table to store work code details(workers work in each work code) for each day
    public void addDateTable(Date date) {
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String sdate = null;

        try {

            sdate = date.toString();
            StringBuilder new_date = new StringBuilder(sdate);
            new_date.setCharAt(4, '_');
            new_date.setCharAt(7, '_');
            System.out.println(sdate);

            dbm.insert("CREATE TABLE d_" + new_date + "(name VARCHAR(25),"
                    + "emp_code INT,"
                    + "work_code VARCHAR(15)," + "division VARCHAR(15));");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //create a table to store work entry data for each month
    public void CreateNewMonthTable(String yr_mnth) {
        DatabaseManager dbm = DatabaseManager.getDbCon();

        try {
            //use new_1 and new_2 if any other deduction type is needd to be added
            dbm.insert("CREATE TABLE pr_workdata_" + yr_mnth + "(code INT,"
                    + "division VARCHAR(15)," + "register_or_casual INT,"
                    + "normal_days INT," + "normal_pay DOUBLE," + "sundays INT,"
                    + "sunday_pay DOUBLE," + "total_pay DOUBLE," + "ot_before_hours INT,"
                    + "ot_before_amount DOUBLE," + "ot_after_hours INT," + "ot_after_amount DOUBLE,"
                    + "incentive1 DOUBLE," + "incentive2 DOUBLE," + "extra_pay DOUBLE,"
                    + "gross_pay DOUBLE," + "tea DOUBLE," + "salary_adv DOUBLE," + "fest_adv DOUBLE,"
                    + "food DOUBLE," + "loan DOUBLE," + "bank DOUBLE," + "epf10 DOUBLE," + "epf12 DOUBLE,"
                    + "total_epf DOUBLE," + "etf DOUBLE," + "ceb DOUBLE," + "teacher DOUBLE," + "chemical DOUBLE,"
                    + "pay_slip DOUBLE," + "fine DOUBLE," + "welfare DOUBLE," + "kovil DOUBLE," + "new_1 DOUBLE," + "new_2 DOUBLE," + "other_ded1 DOUBLE,"
                    + "meals DOUBLE," + "other_ded2 DOUBLE," + "pension DOUBLE,"
                    + "other_ded3 DOUBLE," + "stamp DOUBLE," + "pre_debt DOUBLE," + "total_ded DOUBLE,"
                    + "full_salary DOUBLE," + "coins DOUBLE," + "paid_amount DOUBLE," + "next_month DOUBLE," + "n_5000 INT,"
                    + "n_2000 INT," + "n_1000 INT," + "n_500 INT," + "n_100 INT," + "n_50 INT," + "n_20 INT," + "n_10 INT);");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in ex");
        }
        ///DONT DELETE checkroll_personalinfo SQL table
        //copying worker's codes to newly created table
        CopyTable3Columns("checkroll_personalinfo", "code", "division", "register_or_casual", "pr_workdata_" + yr_mnth, "code", "division", "register_or_casual");
        //copy the previous month's 'next_month' amount to 'pre_debt' column in this month

        GetPreDebts(yr_mnth);

        System.out.println("table copied");
        JOptionPane.showMessageDialog(null, "New checkroll table is created for this month\n", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    //method to copy the previous month's 'next_month' amount to 'pre_debt' column in this month
    public void GetPreDebts(String yrmnth) {
        String prv_yrmnth = ReturnPrvMnthTableName(yrmnth);
        int no_of_codes = getColumnsize("pr_workdata_" + yrmnth, "code");
        int codes[] = new int[no_of_codes];
        codes=getIntArray("pr_workdata_" + prv_yrmnth, "code");
        double pre_debt_amount = 0;
        for (int i = 0; i < no_of_codes; i++) {
            if (dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month") != null) {

                pre_debt_amount = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + prv_yrmnth, "code", codes[i], "next_month"));
                pre_debt_amount=-pre_debt_amount;
            }

            dbm.updateDatabase("pr_workdata_" + yrmnth, "code", codes[i], "pre_debt", pre_debt_amount);

        }
    }

    //method to return previous month table name(only yr_mnth) when this month's table name(only yr_mnth) is given
    //input 2014_01 output 2013_12
    public String ReturnPrvMnthTableName(String thisMnth) {
        String[] year_month = new String[2];
        int[] yr_mnth_int = new int[2];
        year_month = thisMnth.split("_");
        yr_mnth_int[0] = Integer.parseInt(year_month[0]);
        yr_mnth_int[1] = Integer.parseInt(year_month[1]);
        if (yr_mnth_int[1] == 1) {
            yr_mnth_int[0] = yr_mnth_int[0] - 1;
            yr_mnth_int[1] = 12;
        } else {
            yr_mnth_int[1] = yr_mnth_int[1] - 1;
        }
        String prvMnth;
        if (yr_mnth_int[1] > 9) {
            prvMnth = String.valueOf(yr_mnth_int[0]) + "_" + String.valueOf(yr_mnth_int[1]);
        } else {
            prvMnth = String.valueOf(yr_mnth_int[0]) + "_0" + String.valueOf(yr_mnth_int[1]);
        }
        return prvMnth;
    }

    ;
    
    public void CopyTable2Columns(String table_name1, String table1_column1, String table1_column2, String table_name2, String table2_column1, String table2_column2) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + ") VALUES('" + query.getString(table1_column1) + "','" + query.getString(table1_column2) + "')");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }

    }

    public void CopyTable3Columns(String table_name1, String table1_column1, String table1_column2, String table1_column3, String table_name2, String table2_column1, String table2_column2, String table2_column3) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                    // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + "," + table2_column2 + "," + table2_column3 + ") VALUES('" + query.getString(table1_column1) + "','" + query.getString(table1_column2) + "','" + query.getString(table1_column3) + "')");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

        }
    }

    public int[] getIntArray(String table_name, String column_name) {

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

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }

        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

    private void saveDataToWorkEntry(Date date) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        int rowd;
        rowd = 0;
        for (; rowd < table.getRowCount(); rowd++) {
            if (table.getValueAt(rowd, 0) != null) {
                try {
                    dbCon.insert("INSERT INTO prcr_checkroll_workentry(date,normalday_or_sunday,emp_code,work_code,ot_day,ot_night) VALUES('" + date + "','" + getNormalOrSun() + "','" + table.getValueAt(rowd, 0) + "','" + table.getValueAt(rowd, 2) + "','" + table.getValueAt(rowd, 3) + "','" + table.getValueAt(rowd, 4) + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(PRCR_Add_Employee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private String getNormalOrSun() {
        if (sunday.isSelected()) {
            return "s";
        } else {
            return "n";
        }
    }

    //store data to work entry tables(ex. table name-:pr_workdata_2014_03)
    //ex-:table name=2014_03
    //ne_date=2014_03_05
    //tdate=2014/03/05
    private void saveData(String tablename, StringBuilder ne_date, Date tdate) {
        String division = null;
        String work_code = null;
        division = (String) division_jc.getSelectedItem();
        work_code = (String) workCode.getSelectedItem();
        if (sunday.isSelected() == false) {
            int rows = 0;
            for (; rows < table.getRowCount(); rows++) {
                if (table.getValueAt(rows, 0) != null) {
                    int normaldays = 0;

                    System.out.println(table.getValueAt(rows, 0));
                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "normal_days") != null) {
                        normaldays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "normal_days"));
                    } else {
                        normaldays = 0;
                    }

                    normaldays++;
                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "normal_days", normaldays);
                    //overtime-day
                    double dhours = 0;

                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours") != null) {
                        dhours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours"));
                    } else {
                        dhours = 0;
                    }
                    if (table.getValueAt(rows, 3) != null) {
                        dhours = dhours + Double.parseDouble((String) table.getValueAt(rows, 3));
                    } else {
                        dhours = dhours;
                    }
                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours", dhours);

                    //overtime after
                    double ahours = 0;

                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours") != null) {
                        ahours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours"));
                    } else {
                        ahours = 0;
                    }
                    if (table.getValueAt(rows, 4) != null) {
                        ahours = ahours + Double.parseDouble((String) table.getValueAt(rows, 4));
                    } else {
                        ahours = ahours;
                    }

                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours", ahours);

                    //updating newly created tables
          /*          try {
                     dbm.insert("INSERT INTO d_" + ne_date + "(name,emp_code,work_code,division) VALUES('" + rows + "','" + table.getValueAt(rows, 0) + "','" + work_code + "','" + division + "')");
                     } catch (SQLException ex) {
                     Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     */
                }
            }
            JOptionPane.showMessageDialog(null, "Details are saved for the \n" + tdate, "Message", JOptionPane.INFORMATION_MESSAGE);

        } else {
            int rows = 0;
            for (; rows < table.getRowCount(); rows++) {
                if (table.getValueAt(rows, 0) != null) {
                    int sundays = 0;
                    System.out.println(table.getValueAt(rows, 0));
                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "sundays") != null) {

                        sundays = Integer.parseInt(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "sundays"));
                    } else {
                        sundays = 0;
                    }

                    sundays++;
                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "sundays", sundays);

                    //overtime-day
                    double dhours = 0;

                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours") != null) {
                        dhours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours"));
                    } else {
                        dhours = 0;
                    }
                    if (table.getValueAt(rows, 3) != null) {
                        dhours = dhours + Double.parseDouble((String) table.getValueAt(rows, 3));
                    } else {
                        dhours = dhours;
                    }
                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_before_hours", dhours);

                    //overtime after
                    double ahours = 0;

                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours") != null) {
                        ahours = Double.parseDouble(dbm.checknReturnData("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours"));
                    } else {
                        ahours = 0;
                    }
                    if (table.getValueAt(rows, 4) != null) {
                        ahours = ahours + Double.parseDouble((String) table.getValueAt(rows, 4));
                    } else {
                        ahours = ahours;
                    }

                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours", ahours);

                    //update date tables for work code details
                  /*  try {
                     dbm.insert("INSERT INTO d_" + ne_date + "(name,emp_code,work_code,division) VALUES('" + rows + "','" + table.getValueAt(rows, 0) + "','" + work_code + "','" + division + "')");
                     } catch (SQLException ex) {
                     Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     */
                }
            }
            JOptionPane.showMessageDialog(null, "Details are saved for the \n" + tdate, "Message", JOptionPane.INFORMATION_MESSAGE);
            ClearTable();
        }
    }

    public void ClearTable() {
        int rows = 0;
        int column = 0;
        int k = table.getRowCount();
        while (table.getValueAt(rows, 0) != null) {
            for (column = 0; column < 6; column++) {
                table.setValueAt(null, rows, column);
            }
            rows++;

        }
        this.rows = 0;
    }

    public void ClearSelectedRow() {
        int selectedIndex = table.getSelectedRow();
        int filledRows = 0;

        while (table.getValueAt(filledRows, 0) != null) {
            filledRows++;
        }

        int i;
        for (i = selectedIndex; i < filledRows; i++) {
            table.setValueAt(table.getValueAt(i + 1, 0), i, 0);
            table.setValueAt(table.getValueAt(i + 1, 1), i, 1);
            table.setValueAt(table.getValueAt(i + 1, 2), i, 2);
            table.setValueAt(table.getValueAt(i + 1, 3), i, 3);
            table.setValueAt(table.getValueAt(i + 1, 4), i, 4);
            table.setValueAt(table.getValueAt(i + 1, 5), i, 5);
        }
        System.out.println(filledRows);
        rows = filledRows - 1;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        sunday = new javax.swing.JCheckBox();
        workCode = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        empCode_JC = new javax.swing.JComboBox();
        empName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        division_jc = new javax.swing.JComboBox();
        division_lb = new javax.swing.JLabel();
        work_code = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        otnight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        otday = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        datepanel = new javax.swing.JPanel();
        monthfield1 = new javax.swing.JTextField();
        yearfield1 = new javax.swing.JTextField();
        dayfield1 = new javax.swing.JTextField();
        datePick1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jButton3 = new javax.swing.JButton();
        W_code = new javax.swing.JTextField();

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        sunday.setText("Sunday");

        workCode.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        workCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("workcode_details","code")));
        workCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                workCodeItemStateChanged(evt);
            }
        });
        workCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workCodeActionPerformed(evt);
            }
        });
        workCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                workCodeKeyPressed(evt);
            }
        });

        jLabel3.setText("Date");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Work code", "Day", "Night", "division"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(40);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jLabel2.setText("Division");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Employee Code");

        empCode_JC.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        empCode_JC.setEditable(true);
        DatabaseManager dbm=DatabaseManager.getDbCon();
        empCode_JC.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("checkroll_personalinfo","code")));
        empCode_JC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                empCode_JCItemStateChanged(evt);
            }
        });
        empCode_JC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empCode_JCActionPerformed(evt);
            }
        });
        empCode_JC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                empCode_JCKeyPressed(evt);
            }
        });

        jLabel5.setText("Employee Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empCode_JC, 0, 59, Short.MAX_VALUE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(empName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(empCode_JC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancel");

        jButton8.setText("Clear Table");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel4.setText("Work Code");

        division_jc.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        division_jc.setEditable(true);
        division_jc.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("division_details", "code")));
        division_jc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                division_jcItemStateChanged(evt);
            }
        });
        division_jc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                division_jcKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        otnight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otnightActionPerformed(evt);
            }
        });
        otnight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                otnightKeyPressed(evt);
            }
        });

        jLabel7.setText("Night ");

        otday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otdayActionPerformed(evt);
            }
        });
        otday.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                otdayKeyPressed(evt);
            }
        });

        jLabel6.setText("Day");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otday, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otnight, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otnight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });

        jLabel8.setText("Over Time");

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield1.setText(datehandler.get_today_month());
        monthfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfield1KeyPressed(evt);
            }
        });

        yearfield1.setText(datehandler.get_today_year());
        yearfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfield1KeyPressed(evt);
            }
        });

        dayfield1.setText(datehandler.get_today_day());
        dayfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayfield1KeyPressed(evt);
            }
        });

        datePick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePick1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanelLayout = new javax.swing.GroupLayout(datepanel);
        datepanel.setLayout(datepanelLayout);
        datepanelLayout.setHorizontalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(monthfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePick1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanelLayout.setVerticalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePick1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("Clear selected row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        W_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                W_codeActionPerformed(evt);
            }
        });
        W_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                W_codeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel2)
                                        .addGap(34, 34, 34)
                                        .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(W_code, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(sunday)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sunday)
                                    .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(357, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(W_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1)
                                        .addGap(73, 73, 73)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //java.sql.Date tdate = new java.sql.Date(date.getDate().getTime());

      //addDateTable(tdate);//store data of workers worked in each division in each day   REMOVED INTENTIONALLY
        String month = null;
        String year = null;
        Date tdate = null;

        String ndate = null;
        try {
            tdate = datechooser.Return_date(yearfield1, monthfield1, dayfield1);
        } catch (ParseException ex) {
            Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
        }
        ndate = tdate.toString();
        StringBuilder ne_date = new StringBuilder(ndate);
        ne_date.setCharAt(4, '_');
        ne_date.setCharAt(7, '_');
        String new1_date = null;
        new1_date = ne_date.toString();
        String tablename = new1_date.substring(0, 7);

        if (dbm.TableExistence("pr_workdata_" + tablename) == false) {
            CreateNewMonthTable(tablename);
        }

        saveData(tablename, ne_date, tdate);
        saveDataToWorkEntry(tdate);


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (empCode_JC.getSelectedItem().toString().length() != 0) {
                table.setValueAt(empCode_JC.getSelectedItem(), rows, 0);
                table.setValueAt(empName.getText(), rows, 1);
                table.setValueAt(workCode.getSelectedItem(), rows, 2);
                table.setValueAt(division_jc.getSelectedItem(), rows, 5);
                if (otday.getText().length() == 0) {
                    table.setValueAt("0", rows, 3);

                } else {
                    table.setValueAt(otday.getText(), rows, 3);
                }

                if (otnight.getText().length() == 0) {
                    table.setValueAt("0", rows, 4);

                } else {
                    table.setValueAt(otnight.getText(), rows, 4);
                }
                rows++;
                // TODO add your handling code here:
                empCode_JC.setSelectedItem(null);
                otday.setText(null);
                otnight.setText(null);
                empCode_JC.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Enter the employee code\n", "Message", JOptionPane.INFORMATION_MESSAGE);
                empCode_JC.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter the employee code\n", "Message", JOptionPane.INFORMATION_MESSAGE);
            empCode_JC.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void division_jcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_division_jcItemStateChanged
        DatabaseManager dbma = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = evt.getItem().toString();

            try {
                ResultSet query = dbma.query("SELECT * FROM division_details WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("division");
                    System.out.println(Name);
                }
            } catch (SQLException ex) {
                System.out.println("error");
            }

            division_lb.setText("" + Name);
        }

        empCode_JC.requestFocus();


    }//GEN-LAST:event_division_jcItemStateChanged

    private void workCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_workCodeItemStateChanged
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = evt.getItem().toString();
            try {
                ResultSet query = dbm.query("SELECT * FROM workcode_details WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("work");
                }
            } catch (SQLException ex) {
            }
            work_code.setText("" + Name);
        }
        W_code.setText(workCode.getSelectedItem().toString());
        otday.requestFocus();
    }//GEN-LAST:event_workCodeItemStateChanged

    private void empCode_JCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empCode_JCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCActionPerformed

    private void empCode_JCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_empCode_JCItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int item = Integer.parseInt(evt.getItem().toString());
            try {
                ResultSet query = dbm.query("SELECT * FROM personal_info WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("name");
                }
            } catch (SQLException ex) {
            }
            empName.setText("" + Name);
        }

        W_code.requestFocus();
        W_code.selectAll();
    }//GEN-LAST:event_empCode_JCItemStateChanged

    private void otdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otdayActionPerformed

    private void otnightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otnightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otnightActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PRCR_viewNedit_workentry jf = new PRCR_viewNedit_workentry();
        jf.setVisible(true);
        jf.setExtendedState(PRCR_viewNedit_workentry.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void monthfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfield1KeyPressed
        if (monthfield1.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Dec");
                int yr = Integer.parseInt(yearfield1.getText());

                yearfield1.setText("" + (yr - 1));
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Feb");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jan");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Mar");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Feb");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Apr");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Mar");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("May");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Apr");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                monthfield1.setText("Jun");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("May");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Jul");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jun");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Aug");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jul");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Sep");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Aug");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Oct");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Sep");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Nov");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Oct");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Dec");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Nov");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Jan");
                int yr = Integer.parseInt(yearfield1.getText());

                yearfield1.setText("" + (yr + 1));
                monthfield1.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            dayfield1.requestFocus();
            dayfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield1.requestFocus();
            yearfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //category_code.requestFocus();

        }
    }//GEN-LAST:event_monthfield1KeyPressed

    private void yearfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfield1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield1.setText("" + (Integer.parseInt(yearfield1.getText()) + 1));
            yearfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield1.setText("" + (Integer.parseInt(yearfield1.getText()) - 1));
            yearfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield1.requestFocus();
            monthfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  category_code.requestFocus();

        }
    }//GEN-LAST:event_yearfield1KeyPressed

    private void dayfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayfield1KeyPressed
        ///////////////////////////////////////////////////  Days Decrement/////////////////////////////////////////////////////////////////////////////

        if (dayfield1.getText().equals("1")) {           // Jumping to 31 and 30 from 1st
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield1.getText().equals("Feb") || monthfield1.getText().equals("Apr") || monthfield1.getText().equals("Jun") || monthfield1.getText().equals("Aug") || monthfield1.getText().equals("Sep") || monthfield1.getText().equals("Nov") || monthfield1.getText().equals("Feb")) {
                    dayfield1.setText("31");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("May") || monthfield1.getText().equals("Jul") || monthfield1.getText().equals("Oct") || monthfield1.getText().equals("Dec")) {
                    dayfield1.setText("30");
                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("Mar")) {     // from march 1st jump to 28th or 29th checking leap years
                    int yr = Integer.parseInt(yearfield1.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield1.setText("29"); // Leap Year
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield1.setText("28"); // not a leap year
                            }
                        }
                        dayfield1.setText("29");       // leap year

                    }
                    if (yr % 4 != 0) {
                        dayfield1.setText("28");       // not a leap year
                    }
                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("Jan")) {            // From jan 1st jump to december 31st decrementing year
                    dayfield1.setText("31");

                    int yr = Integer.parseInt(yearfield1.getText());
                    monthfield1.setText("Dec");
                    yearfield1.setText("" + (yr - 1));    // year
                }
                dayfield1.selectAll();
            }                                           // /// decrementing normal values
        } else if (dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
                || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
                || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
                || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
                || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
                || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
                || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) - 1));
                dayfield1.selectAll();
            }
        }
        /////////////////////////////////////////////////  Days Increment///////////////////////////////////////////////////////////////////////////////////////////////////
        if (dayfield1.getText().equals("30")) {               // from 30th to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield1.getText().equals("Apr") || monthfield1.getText().equals("Jun") || monthfield1.getText().equals("Sep") || monthfield1.getText().equals("Nov")) {
                    dayfield1.setText("0");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));

                }
                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));
                dayfield1.selectAll();
            }

        } else if (dayfield1.getText().equals("31")) {            // from 31st to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield1.getText().equals("Jan") || monthfield1.getText().equals("Mar") || monthfield1.getText().equals("May") || monthfield1.getText().equals("Jul") || monthfield1.getText().equals("Aug") || monthfield1.getText().equals("Oct")) {
                    dayfield1.setText("1");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));

                } else if (monthfield1.getText().equals("Dec")) {      // December to january incrementing the year

                    dayfield1.setText("1");

                    int yr = Integer.parseInt(yearfield1.getText());
                    monthfield1.setText("Jan");
                    yearfield1.setText("" + (yr + 1));
                }
                dayfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Feb")) {                    // for february
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                if (dayfield1.getText().equals("28")) {                    // at 28 check for leap year
                    int yr = Integer.parseInt(yearfield1.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield1.setText("29"); // Leap Year       // increment to 29
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield1.setText("1");
                                int mnth = datechooser.return_index(monthfield1.getText());
                                monthfield1.setText(datechooser.Return_month(mnth + 1));

                                // not a leap year                             // jump to next month
                            }
                        }
                        dayfield1.setText("29");       // leap year             // increment to 29th

                    }
                    if (yr % 4 != 0) {
                        dayfield1.setText("1");
                        int mnth = datechooser.return_index(monthfield1.getText());
                        monthfield1.setText(datechooser.Return_month(mnth + 1));                  // not a leap year
                    }

                } else if (dayfield1.getText().equals("29")) {              // at 29 jump to next month normally
                    dayfield1.setText("1");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));
                    // incrementing normal values/////////////////////// for february separately
                } else if (dayfield1.getText().equals("1") || dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
                        || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
                        || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
                        || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
                        || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
                        || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
                        || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {

                    dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));

                }
                dayfield1.selectAll();
            }
            // incrementing normal values
        } else if (dayfield1.getText().equals("1") || dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
                || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
                || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
                || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
                || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
                || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
                || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));
                dayfield1.selectAll();

            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            monthfield1.requestFocus();
            monthfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            division_jc.requestFocus();

        }
    }//GEN-LAST:event_dayfield1KeyPressed

    private void datePick1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePick1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePick1.getDate().getTime());

        dayfield1.setText(datehandler.get_day(datef));
        monthfield1.setText(datehandler.get_month(datef));
        yearfield1.setText(datehandler.get_year(datef));
        // category_code.requestFocus();
    }//GEN-LAST:event_datePick1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ClearTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ClearSelectedRow(); // TODO add your handling code here:

    }//GEN-LAST:event_jButton3ActionPerformed

    private void division_jcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_division_jcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            empCode_JC.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_division_jcKeyPressed

    private void empCode_JCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empCode_JCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            workCode.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCKeyPressed

    private void workCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            otday.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_workCodeKeyPressed

    private void otdayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otdayKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            otnight.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_otdayKeyPressed

    private void otnightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otnightKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            jButton1.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_otnightKeyPressed

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        interface_events.Respond_enter(jButton1, evt); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1FocusGained

    private void W_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_W_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_W_codeActionPerformed

    private void W_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_W_codeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            workCode.setSelectedItem(W_code.getText());
            otday.requestFocus();
        }
    }//GEN-LAST:event_W_codeKeyPressed

    private void workCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workCodeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField W_code;
    private com.michaelbaranov.microba.calendar.DatePicker datePick1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JTextField dayfield1;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel division_lb;
    private javax.swing.JComboBox empCode_JC;
    private javax.swing.JLabel empName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField monthfield1;
    private javax.swing.JTextField otday;
    private javax.swing.JTextField otnight;
    private javax.swing.JCheckBox sunday;
    private javax.swing.JTable table;
    private javax.swing.JComboBox workCode;
    private javax.swing.JLabel work_code;
    private javax.swing.JTextField yearfield1;
    // End of variables declaration//GEN-END:variables
}
