
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    DatabaseManager dbm = DatabaseManager.getDbCon();
    private int rows = 0;
    Date_Handler datehandler = new Date_Handler();

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

            dbm.insert("CREATE TABLE pr_workdata_" + yr_mnth + "(code INT,"
                    + "division VARCHAR(15),"
                    + "normal_days INT," + "sundays INT," + "ot_before_hours INT," + "ot_after_hours INT," + "extra_pay DOUBLE," + "full_salary DOUBLE," + "n_5000 INT," + "n_2000 INT," + "n_1000 INT," + "n_500 INT," + "n_100 INT," + "n_50 INT," + "n_20 INT," + "n_10 INT);");
        } catch (SQLException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("in ex");
        }
        ///DONT DELETE checkroll_personalinfo SQL table
        //copying worker's codes to newly created table
        CopyTable2Columns("checkroll_personalinfo", "code","division", "pr_workdata_" + yr_mnth, "code","division");
        System.out.println("table copied");

    }
    
    public void CopyTable2Columns(String table_name1, String table1_column1,String table1_column2, String table_name2, String table2_column1,String table2_column2) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT * FROM " + table_name1 + "");//ORDER BY " + table_column1 + " ASC");
            while (query.next()) {

                try {
                   // dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
                    dbm.insert("INSERT INTO " + table_name2 + "(" + table2_column1 + ","+table2_column2+") VALUES('" + query.getString(table1_column1) + "','"+query.getString(table1_column2)+"')");
                } catch (SQLException ex) {
                    MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
                }
            }
        } catch (SQLException ex) {

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
                    if (table.getValueAt(rows, 2) != null) {
                        dhours = dhours + Double.parseDouble((String) table.getValueAt(rows, 2));
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
                    if (table.getValueAt(rows, 3) != null) {
                        ahours = ahours + Double.parseDouble((String) table.getValueAt(rows, 3));
                    } else {
                        ahours = ahours;
                    }

                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours", ahours);

                    //updating newly created tables
                    try {
                        dbm.insert("INSERT INTO d_" + ne_date + "(name,emp_code,work_code,division) VALUES('" + rows + "','" + table.getValueAt(rows, 0) + "','" + work_code + "','" + division + "')");
                    } catch (SQLException ex) {
                        Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
                    }

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
                    if (table.getValueAt(rows, 2) != null) {
                        dhours = dhours + Double.parseDouble((String) table.getValueAt(rows, 2));
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
                    if (table.getValueAt(rows, 3) != null) {
                        ahours = ahours + Double.parseDouble((String) table.getValueAt(rows, 3));
                    } else {
                        ahours = ahours;
                    }

                    dbm.updateDatabase("pr_workdata_" + tablename, "code", table.getValueAt(rows, 0), "ot_after_hours", ahours);

                    //update date tables for work code details
                    try {
                        dbm.insert("INSERT INTO d_" + ne_date + "(name,emp_code,work_code,division) VALUES('" + rows + "','" + table.getValueAt(rows, 0) + "','" + work_code + "','" + division + "')");
                    } catch (SQLException ex) {
                        Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            JOptionPane.showMessageDialog(null, "Details are saved for the \n" + tdate, "Message", JOptionPane.INFORMATION_MESSAGE);

        }
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
        date = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        empCodeJC = new javax.swing.JComboBox();
        empName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
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

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        sunday.setText("Sunday");

        workCode.setEditable(true);
        workCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("workcode_details","code")));
        workCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                workCodeItemStateChanged(evt);
            }
        });

        jLabel3.setText("Date");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Day", "Night"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(40);
            table.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jLabel2.setText("Division");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Employee Code");

        empCodeJC.setEditable(true);
        DatabaseManager dbm=DatabaseManager.getDbCon();
        empCodeJC.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("checkroll_personalinfo","code")));
        empCodeJC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                empCodeJCItemStateChanged(evt);
            }
        });
        empCodeJC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empCodeJCActionPerformed(evt);
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
                        .addComponent(empCodeJC, 0, 59, Short.MAX_VALUE)
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
                    .addComponent(empCodeJC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jButton8.setText("Quit");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Work Code");

        division_jc.setEditable(true);
        division_jc.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("division_details", "code")));
        division_jc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                division_jcItemStateChanged(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        otnight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otnightActionPerformed(evt);
            }
        });

        jLabel7.setText("Night ");

        otday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otdayActionPerformed(evt);
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

        jLabel8.setText("Over Time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sunday))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sunday))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addGap(75, 75, 75)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        java.sql.Date tdate = new java.sql.Date(date.getDate().getTime());

        addDateTable(tdate);//store data of workers worked in each division in each day

        String month = null;
        String year = null;

        String ndate = null;
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


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        table.setValueAt(empCodeJC.getSelectedItem(), rows, 0);
        table.setValueAt(empName.getText(), rows, 1);

        if (otday.getText().length() == 0) {
            table.setValueAt("0", rows, 2);

        } else {
            table.setValueAt(otday.getText(), rows, 2);
        }

        if (otnight.getText().length() == 0) {
            table.setValueAt("0", rows, 3);

        } else {
            table.setValueAt(otnight.getText(), rows, 3);
        }
        rows++;
        // TODO add your handling code here:
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
    }//GEN-LAST:event_workCodeItemStateChanged

    private void empCodeJCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empCodeJCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empCodeJCActionPerformed

    private void empCodeJCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_empCodeJCItemStateChanged
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
    }//GEN-LAST:event_empCodeJCItemStateChanged

    private void otdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otdayActionPerformed

    private void otnightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otnightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otnightActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker date;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel division_lb;
    private javax.swing.JComboBox empCodeJC;
    private javax.swing.JLabel empName;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField otday;
    private javax.swing.JTextField otnight;
    private javax.swing.JCheckBox sunday;
    private javax.swing.JTable table;
    private javax.swing.JComboBox workCode;
    private javax.swing.JLabel work_code;
    // End of variables declaration//GEN-END:variables
}
