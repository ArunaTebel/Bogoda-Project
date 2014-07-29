
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
public class Report_PRCR_EPF_6Month extends javax.swing.JPanel {

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

//    public class report implements Runnable {
//
//        public report() {
//        }
//
//        @Override
//        public void run() {
//
//            Thread a = new Thread(new Background(55));
//
//            Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
//            HashMap param = new HashMap();
//            param.put("USER", user.get_current_user());
//            param.put("year", yearfield.getText());
//            param.put("month", datehandler.return_month_as_num(monthfield.getText()));
//            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());
//
//            // b.start();
//            a.start();
//            t1.run();
//
//            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
//
//            String dateNow = generate.create("Weekly_Advances_", "D:\\", param, location, "Weekly_Advances.jrxml");
//            a.stop();
//            jProgressBar1.setValue(100);
//            try {
//                generate.savename(dateNow, "Weekly_Advances_", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
//            } catch (SQLException ex) {
//                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//
//    }

    public class report_old implements Runnable {

        public report_old() {
        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

            // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
            
            a.start();
            // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
               if(first6.isSelected()){
            String dateNow = generate.create("PRCR_Checkroll_6Monthl_EPF_", "D:\\", param, location, "PRCR_EPF_firsthalf.jrxml");
               }else if(last6.isSelected()){
               String dateNow = generate.create("PRCR_Checkroll_6Monthl_EPF_", "D:\\", param, location, "PRCR_EPF_secondhalf.jrxml");
            
               }
            
            a.stop();
            jProgressBar1.setValue(100);
//            try {
//                generate.savename(dateNow, "PRCR_Checkroll_6Monthl_EPF_", yearField.getText());
//            } catch (SQLException ex) {
//                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
//            }

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
    public Report_PRCR_EPF_6Month() {
        initComponents();
        jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
    DatabaseManager dbm = new DatabaseManager();

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

        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        yearField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        first6 = new javax.swing.JRadioButton();
        last6 = new javax.swing.JRadioButton();
        view = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        view2 = new javax.swing.JButton();
        view1 = new javax.swing.JButton();
        epfPrgrsbr = new javax.swing.JProgressBar();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        yearField.setText("YYYY");

        jLabel1.setText("Year");

        first6.setText("First 6 Months");
        first6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first6ActionPerformed(evt);
            }
        });

        last6.setText("Last 6 Months");
        last6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                last6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(first6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(last6, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(first6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(last6)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(yearField)
                    .addGap(57, 57, 57)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yearField))
                            .addGap(49, 49, 49)))
                    .addGap(24, 24, 24)))
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
        jLabel4.setText("EPF 6 Month");

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

        epfPrgrsbr.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(view2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(epfPrgrsbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(epfPrgrsbr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
int year = Integer.parseInt(yearField.getText());
        if(first6.isSelected()){
            PRCRLedgerFirstHalf ex = new PRCRLedgerFirstHalf(year);
   Thread b=new Thread(ex);
   b.start();
        }
        else if(last6.isSelected()){
            PRCRLedgerSecondHalf ex = new PRCRLedgerSecondHalf(year);
            Thread c=new Thread(ex);
            c.start();
        }
        else
            JOptionPane.showMessageDialog(null, "Select Period of the year!");
        

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
        int year = Integer.parseInt(yearField.getText());
        if(first6.isSelected()){
//            PRCRLedgerFirstHalf ex = new PRCRLedgerFirstHalf(year);
//            ex.getWorkCodes();
//            ex.updateTable();
        }
        else if(last6.isSelected()){
//            PRCRLedgerSecondHalf ex = new PRCRLedgerSecondHalf(year);
//            ex.getWorkCodes();
//            ex.updateTable();
        }
        else
            JOptionPane.showMessageDialog(null, "Select Period of the year!");
        
        
        Thread b = new Thread(new report_old());
        b.start();
    }//GEN-LAST:event_view1ActionPerformed

    private void first6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_first6ActionPerformed

    private void last6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_last6ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_last6ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar epfPrgrsbr;
    private javax.swing.JRadioButton first6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JRadioButton last6;
    private javax.swing.JButton view;
    private javax.swing.JButton view1;
    private javax.swing.JButton view2;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}
