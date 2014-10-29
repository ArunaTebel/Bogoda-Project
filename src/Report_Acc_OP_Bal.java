
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author IDDAMALGODA
 */
public class Report_Acc_OP_Bal extends javax.swing.JPanel {

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Report_gen generate = new Report_gen();
    Date_Handler dt = new Date_Handler();

    /**
     * Creates new form Report_Acc_Reciepts
     */
    public Report_Acc_OP_Bal() {
        initComponents();

    }

    public class Background implements Runnable {

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
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public class report implements Runnable {

        Thread a = new Thread(new Background());

        ACC_ledger ledg = new ACC_ledger();

        ACC_Update updt = new ACC_Update();

        Date_Handler dt = new Date_Handler();

        DateChooser_text date_chooser = new DateChooser_text();

        DatabaseManager dbm = DatabaseManager.getDbCon();

        @Override
        public void run() {
            try {
                //  jProgressBar1.setIndeterminate(true);

                int accountCode;
                String column;
                String year;
                String name;
                String db_name=null;

                double op_bal;

                jProgressBar1.setValue(45);
                jProgressBar1.repaint();

                a.start();
                ResultSet query = dbm.query("SELECT * FROM accounting_period");
                while (query.next()) {
                    db_name = query.getString("period");
                }
                query.close();
                year = db_name.substring(0, 4);
                db_name = db_name.substring(0, 4) + "_balances";

                if (all.isSelected()) {
                    column = "1";
                    name = "1";
                } else {
                    column = "account_code";
                    name = account_code.getSelectedItem().toString();
                }

                HashMap param = new HashMap();

                param.put("USER", new UserAccountControl().get_current_user());
                param.put("year", year);
                param.put("db_name", db_name);
                param.put("column", column);
                param.put("name", name);

                        // jProgressBar1.setValue(45);
                // jProgressBar1.repaint();
                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                //  a.start();
                generate.create("Account Ledgers", "D:\\", param, location, "Report_Acc_Opp_Bal.jrxml");
                a.stop();
                ;
                jProgressBar1.setValue(100);
            } catch (SQLException ex) {
                Logger.getLogger(Report_Acc_OP_Bal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    DateChooser_text datechooser = new DateChooser_text();

    Date_Handler datehandler = new Date_Handler();

    String[] combo = new String[10];

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        debit_accountCode = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        account_code = new javax.swing.JComboBox();
        all = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        account_name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("ALL");

        debit_accountCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        debit_accountCode.setEditable(true);
        debit_accountCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names", "account_id")));
        debit_accountCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                debit_accountCodeItemStateChanged(evt);
            }
        });
        debit_accountCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debit_accountCodeActionPerformed(evt);
            }
        });
        debit_accountCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_accountCodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jButton1.setText("Veiw");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        debit_accountCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        account_code.setEditable(true);
        account_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names", "account_id")));
        account_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                account_codeItemStateChanged(evt);
            }
        });
        account_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                account_codeActionPerformed(evt);
            }
        });
        account_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                account_codeKeyPressed(evt);
            }
        });

        all.setText("ALL");
        all.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                allItemStateChanged(evt);
            }
        });
        all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Accounts Opening Balances");

        account_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setText("Account Code");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(all, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(account_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    ACC_Reciept_View_Table tbl = new ACC_Reciept_View_Table();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Thread s = new Thread(new report());
        s.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void debit_accountCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_debit_accountCodeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        /* String Name = null;
         if (evt.getStateChange() == ItemEvent.SELECTED) {
         int item = Integer.parseInt(evt.getItem().toString());
         try {
         ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id =" + item + "");
         while (query.next()) {
         Name = query.getString("account_name");
         }
         } catch (SQLException ex) {
         }
         debit_accountName.setText("" + Name);
         }
         debit_description.requestFocusInWindow(); */

        Check_Entries chk = new Check_Entries();

        MessageBox msg = new MessageBox();

        try {
            if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(debit_accountCode.getSelectedItem().toString())) == 1 || debit_accountCode.getSelectedIndex() == 0 || debit_accountCode.getSelectedItem().toString() == null) {
                String Name = null;
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    int item = Integer.parseInt(evt.getItem().toString());
                    try {
                        ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id =" + item + "");
                        while (query.next()) {
                            Name = query.getString("account_name");
                        }
                    } catch (SQLException ex) {
                    }

                }

            } else {
                msg.showMessage("Invalid Account Code", "Receipt", "info");
                debit_accountCode.setSelectedIndex(0);
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_debit_accountCodeItemStateChanged

    private void debit_accountCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debit_accountCodeActionPerformed

    }//GEN-LAST:event_debit_accountCodeActionPerformed

    private void debit_accountCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_accountCodeKeyPressed

    }//GEN-LAST:event_debit_accountCodeKeyPressed

    private void account_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_account_codeItemStateChanged

        DatabaseManager dbm = DatabaseManager.getDbCon();
        MessageBox msg = new MessageBox();

        try {
            if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(account_code.getSelectedItem().toString())) == 1 || debit_accountCode.getSelectedIndex() == 0 || debit_accountCode.getSelectedItem().toString() == null) {
                String Name = null;
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    int item = Integer.parseInt(evt.getItem().toString());
                    try {
                        ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id =" + item + "");
                        while (query.next()) {
                            Name = query.getString("account_name");
                        }
                    } catch (SQLException ex) {
                    }
                    account_name.setText("" + Name);
                }

            } else {
                msg.showMessage("Invalid Account Code", "Receipt", "info");
                debit_accountCode.setSelectedIndex(0);
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_account_codeItemStateChanged

    private void account_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_account_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_codeActionPerformed

    private void account_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_codeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_codeKeyPressed

    private void allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allActionPerformed

    }//GEN-LAST:event_allActionPerformed

    private void allItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_allItemStateChanged
        if (all.isSelected()) {
            account_code.setEnabled(false);
        } else {
            account_code.setEnabled(true);
        }
    }//GEN-LAST:event_allItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox account_code;
    private javax.swing.JLabel account_name;
    private javax.swing.JCheckBox all;
    private javax.swing.JComboBox debit_accountCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}