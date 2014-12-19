
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
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
public class Report_PRCR_Debit_N_Coin_Report extends javax.swing.JPanel {

    PRCR_Checkroll_Summary_Report rep = new PRCR_Checkroll_Summary_Report();

    public class Background implements Runnable {

        int delay;

        public Background(int Delay) {
            delay = Delay;

        }

        @Override
        public void run() {
            //  jProgressBar1.setIndeterminate(true);
            // view.setEnabled(false);
            jProgressBar1.setVisible(true);
            int a = (int) (Math.random() * 500);
            //System.out.println(a);
            for (int i = 0; i <= 3000 + a; i++) {
                jProgressBar1.setValue(100 * i / 4000);
                jProgressBar1.repaint();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public class report_old implements Runnable {

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

            // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
            param.put("division_column", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`division`");
            param.put("division", division_jc.getSelectedItem().toString());

            param.put("column1", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`code`");
            param.put("column2", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`register_or_casual`");
            param.put("table_name", "`pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + "` pr_workdata_" + getST(monthfield.getText(), yearfield.getText()));

            if (report_type.getSelectedItem().toString().equals("Debit List")) {
                if (registerC.getSelectedItem().toString().equals("Register")) {
                    param.put("report_name", "Debit List - "+division_jc.getSelectedItem().toString()+" (Register) - " + monthfield.getText() + "-" + yearfield.getText());
                    param.put("register_column", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`register_or_casual`");
                    param.put("register", 1);

                } else {
                    param.put("report_name", "Debit List "+division_jc.getSelectedItem().toString()+" (Casual) - " + monthfield.getText() + "-" + yearfield.getText());
                    param.put("register_column", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`register_or_casual`");
                    param.put("register", 0);
                }
                param.put("column3", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`next_month`");
                param.put("lowerthan", 0);
                param.put("greaterthan", -1000000000.0);
            } else {
                
                 if (registerC.getSelectedItem().toString().equals("Register")) {
                    param.put("report_name", "Coins List "+division_jc.getSelectedItem().toString()+" (Register) - " + monthfield.getText() + "-" + yearfield.getText());
                    param.put("register_column", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`register_or_casual`");
                    param.put("register", 1);

                } else {
                    param.put("report_name", "Coins List "+division_jc.getSelectedItem().toString()+" (Casual) - " + monthfield.getText() + "-" + yearfield.getText());
                    param.put("register_column", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`register_or_casual`");
                    param.put("register", 0);
                }
                
                param.put("column3", "pr_workdata_" + getST(monthfield.getText(), yearfield.getText()) + ".`coins`");
                param.put("lowerthan", 10);
                param.put("greaterthan", 0.0);
            }

//         param.put("Month", datehandler.return_month_as_num(monthfield.getText()));
            a.start();
            // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
            String dateNow;
            if (division_jc.getSelectedItem().toString().equals("STAFF")) {
                dateNow = generate.create("PRCR_debit_report_", "D:\\", param, location, "PRCR_debit_report.jrxml");
            } else {

                dateNow = generate.create("PRCR_debit_report_", "D:\\", param, location, "PRCR_debit_report.jrxml");

            }

            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "PPRCR_debit_report_", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public class update implements Runnable {

        public GL_report_generator report_gen;
        String YEAR;
        String MONTH;

        public update(String year, String month) {
            report_gen = new GL_report_generator();
            YEAR = year;
            MONTH = month;
        }

        @Override
        public void run() {
            Date_Handler date_handler = new Date_Handler();
            report_gen.weekly_advance_calc(YEAR, date_handler.return_month_as_num(MONTH));

        }
    }

    /**
     *
     */
    public Report_PRCR_Debit_N_Coin_Report() {
        initComponents();
        jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
    DatabaseManager dbm = DatabaseManager.getDbCon();

    public void focus() {
        //dayfield.requestFocus();
        //dayfield.selectAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        view1 = new javax.swing.JButton();
        division_jc = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        report_type = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        registerC = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield.setText(datehandler.get_today_month());
        monthfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfieldKeyPressed(evt);
            }
        });

        yearfield.setText(datehandler.get_today_year());
        yearfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfieldKeyPressed(evt);
            }
        });

        datePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanelLayout = new javax.swing.GroupLayout(datepanel);
        datepanel.setLayout(datepanelLayout);
        datepanelLayout.setHorizontalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanelLayout.setVerticalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Debit And Coin List");

        view1.setText("View");
        view1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view1MouseClicked(evt);
            }
        });
        view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view1ActionPerformed(evt);
            }
        });

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

        jLabel2.setText("Division");

        jLabel5.setText("Report");

        report_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Debit List", "Coin List" }));

        jLabel6.setText("Register Or Casual");

        registerC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Register", "Casual" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerC, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(report_type, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {division_jc, registerC, report_type});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void monthfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfieldKeyPressed
        if (monthfield.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Dec");
                int yr = Integer.parseInt(yearfield.getText());

                yearfield.setText("" + (yr - 1));
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Feb");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jan");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Mar");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Feb");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Apr");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Mar");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("May");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Apr");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                monthfield.setText("Jun");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("May");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jul");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jun");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Aug");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jul");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Sep");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Aug");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Oct");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Sep");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Nov");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Oct");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Dec");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Nov");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jan");
                int yr = Integer.parseInt(yearfield.getText());

                yearfield.setText("" + (yr + 1));
                monthfield.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield.requestFocus();
            yearfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  dayfield2.requestFocus();
            // dayfield2.selectAll();

        }
    }//GEN-LAST:event_monthfieldKeyPressed

    private void yearfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) + 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) - 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield.requestFocus();
            monthfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //   dayfield2.requestFocus();
            //  dayfield2.selectAll();

        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        // dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        //  dayfield2.requestFocus();
        // dayfield2.selectAll();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void view1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view1MouseClicked

    private void view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1ActionPerformed
        //  duplicateTable("pr_workdata_" + getST(monthfield.getText(), yearfield.getText()), "prcr_checkroll_ledger_report");

        Thread b = new Thread(new report_old());
        b.start();
    }//GEN-LAST:event_view1ActionPerformed

    public void duplicateTable(String original_table_name, String table_copy_name) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            if (dbCon.TableExistence(table_copy_name)) {
                dbCon.insert("DROP TABLE " + table_copy_name + "");
            }
            dbCon.insert("CREATE TABLE " + table_copy_name + " LIKE " + original_table_name + "");
            dbCon.insert("INSERT " + table_copy_name + " SELECT * FROM " + original_table_name + "");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }

    public String getST(String month, String year) {
        String st;
        if (datehandler.return_index(month) < 10) {
            st = year + "_0" + datehandler.return_index(month);
        } else {
            st = year + "_" + datehandler.return_index(month);
        }

        return st;
    }
    private void division_jcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_division_jcItemStateChanged
        DatabaseManager dbma = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = evt.getItem().toString();

            try {
                ResultSet query = dbma.query("SELECT * FROM division_details WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("division");

                }
            } catch (SQLException ex) {
                System.out.println("error");
            }

            // division_lb.setText("" + Name);
        }

        //empCode_JC.requestFocus();
    }//GEN-LAST:event_division_jcItemStateChanged

    private void division_jcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_division_jcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  empCode_JC.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_division_jcKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JComboBox registerC;
    private javax.swing.JComboBox report_type;
    private javax.swing.JButton view1;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
