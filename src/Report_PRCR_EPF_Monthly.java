
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
public class Report_PRCR_EPF_Monthly extends javax.swing.JPanel {

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

    public class report implements Runnable {

        public report() {
        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));
            
           
            Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
            param.put("year", yearfield.getText());
            param.put("month", datehandler.return_month_as_num(monthfield.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());

            // b.start();
            a.start();
            t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

            String dateNow = generate.create("Weekly_Advances_", "D:\\", param, location, "Weekly_Advances.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "Weekly_Advances_", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }

    }

    public class report_old implements Runnable {

        public report_old() {
        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

            // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            
            if(divisionCB.isSelected()){
            HashMap param = new HashMap();
            
//             double grandtotal=0;
//                try {
//                    ResultSet query=dbm.query("SELECT * FROM prcr_checkroll_ledger_report WHERE active LIKE 1 AND division LIKE'"+division_jc.getSelectedItem()+"'");
//                    while(query.next()){
//                        grandtotal=grandtotal+query.getDouble("total_epf");
//                    }
//                    param.put("grandtotalP",grandtotal);
//                } catch (SQLException ex) {
//                    Logger.getLogger(Report_PRCR_EPF_Monthly.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            
            
            param.put("USER", user.get_current_user());
            param.put("DIVISION", division_jc.getSelectedItem().toString());
//         param.put("Month", datehandler.return_month_as_num(monthfield.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());
//            if (register_or_casual_combo.getSelectedItem().toString().equals("Register")) {
//                param.put("REGISTER", "1");
//                param.put("RegisterLable","Registered");
//            } else {
//                param.put("REGISTER", "0");
//                param.put("RegisterLable","Casual");
//            }
            param.put("REGISTER", "1");
            a.start();
            // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

            String dateNow = generate.create("PRCR_Checkroll_Monthly_EPF_", "D:\\", param, location, "PRCR_Checkroll_Monthly_EPF.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "PRCR_Checkroll_Monthly_EPF_", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
    
                HashMap param = new HashMap();
                
                double grandtotal=0;
                try {
                    ResultSet query=dbm.query("SELECT * FROM prcr_checkroll_ledger_report WHERE active LIKE '"+1+"'");
                    while(query.next()){
                        grandtotal=grandtotal+query.getDouble("total_epf");
                    }
                    grandtotal=(Math.round(grandtotal*100.0))/100.0;
                    param.put("grandtotalP",grandtotal);
                } catch (SQLException ex) {
                    Logger.getLogger(Report_PRCR_EPF_Monthly.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            param.put("USER", user.get_current_user());
            //param.put("DIVISION", division_jc.getSelectedItem().toString());
//         param.put("Month", datehandler.return_month_as_num(monthfield.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());
//            if (register_or_casual_combo.getSelectedItem().toString().equals("Register")) {
//                param.put("REGISTER", "1");
//                param.put("RegisterLable","Registered");
//            } else {
//                param.put("REGISTER", "0");
//                param.put("RegisterLable","Casual");
//            }
            param.put("REGISTER", "1");
            a.start();
            // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

            String dateNow = generate.create("PRCR_Checkroll_Monthly_EPF_ALL", "D:\\", param, location, "PRCR_Checkroll_Monthly_EPF_all.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "PRCR_Checkroll_Monthly_EPF_ALL", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
                
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
    public Report_PRCR_EPF_Monthly() {
        initComponents();
        jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
    DatabaseManager dbm =  DatabaseManager.getDbCon();

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
        view = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        view2 = new javax.swing.JButton();
        view1 = new javax.swing.JButton();
        division_jc = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        divisionCB = new javax.swing.JCheckBox();

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

        view.setText("Update");
        view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewMouseClicked(evt);
            }
        });
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Monthly EPF");

        view2.setText("Open last");
        view2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view2MouseClicked(evt);
            }
        });
        view2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view2ActionPerformed(evt);
            }
        });

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

        divisionCB.setText("jCheckBox1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(view2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(divisionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(52, 52, 52)
                                .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divisionCB))
                .addGap(40, 40, 40)
                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
//     int a= JOptionPane.showConfirmDialog(datechooser, "This will update the Daily transactions list. it will take several minutes to complete.. Are you sure?");
//        System.out.println(a); 
//         Thread b = new Thread(new report());
//        if(a==0){  b.start();}

        //b.start();

    }//GEN-LAST:event_viewActionPerformed

    private void viewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewMouseClicked
        // jLabel4.setText("please wait..");
    }//GEN-LAST:event_viewMouseClicked

    private void view2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view2MouseClicked

    private void view2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view2ActionPerformed
//        String save_location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
//        
//        String file_name= dbm.checknReturnStringData("last_report", "type", "Weekly_Advances_", "filename");
//        File myFile = new File("D:\\"+file_name+".pdf");
//        try {
//            Desktop.getDesktop().open(myFile);
//        } catch (IOException ex) {
//            Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_view2ActionPerformed

    private void view1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view1MouseClicked

    private void view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1ActionPerformed
        duplicateTable("pr_workdata_" + getST(monthfield.getText(), yearfield.getText()), "prcr_checkroll_ledger_report");

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
    private javax.swing.JCheckBox divisionCB;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JButton view;
    private javax.swing.JButton view1;
    private javax.swing.JButton view2;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
