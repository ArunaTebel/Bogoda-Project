
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class PRCR_Extrapayment_cashwork extends javax.swing.JPanel {
    DatabaseManager dbm = new DatabaseManager();
    Date_Handler datehandler = new Date_Handler();
    /**
     * Creates new form PRCR_Extrapayment_cashwork
     */
    public PRCR_Extrapayment_cashwork() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        empCodeJC = new javax.swing.JComboBox();
        workCode = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        empName = new javax.swing.JLabel();
        work_code = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jLabel1.setText("Month -Year");

        jLabel2.setText("Employee Code");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField2.setText(datehandler.get_today_month());
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setText(datehandler.get_today_year());
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        empCodeJC.setEditable(true);
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

        workCode.setEditable(true);
        workCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("workcode_details","code")));
        workCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                workCodeItemStateChanged(evt);
            }
        });

        jLabel3.setText("Work Code");

        jLabel4.setText("Rate");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Default");

        empName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        empName.setText("Default");

        work_code.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        work_code.setText("Default");

        jLabel8.setText("Change");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Number Of Days");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel10.setText("Amount");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancel");

        jButton8.setText("Quit");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(workCode, 0, 1, Short.MAX_VALUE)
                                                .addComponent(empCodeJC, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel5))
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(empName)
                                            .addComponent(work_code)))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(empCodeJC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(work_code))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (jTextField2.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Dec");
                int yr = Integer.parseInt(jTextField3.getText());

                jTextField3.setText("" + (yr - 1));
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Feb");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Jan");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Mar");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Feb");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Apr");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Mar");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("May");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Apr");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                jTextField2.setText("Jun");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("May");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Jul");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Jun");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Aug");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Jul");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Sep");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Aug");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Oct");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Sep");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Nov");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Oct");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Dec");
                jTextField2.selectAll();
            }

        } else if (jTextField2.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                jTextField2.setText("Nov");
                int yr = Integer.parseInt(jTextField3.getText());
                jTextField2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                jTextField2.setText("Jan");
                int yr = Integer.parseInt(jTextField3.getText());

                jTextField3.setText("" + (yr + 1));
                jTextField2.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jTextField1.requestFocus();
            jTextField1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jTextField3.requestFocus();
            jTextField3.selectAll();
        }

    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jTextField3.setText("" + (Integer.parseInt(jTextField3.getText()) + 1));
            jTextField3.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jTextField3.setText("" + (Integer.parseInt(jTextField3.getText()) - 1));
            jTextField3.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jTextField2.requestFocus();
            jTextField2.selectAll();
        }

    }//GEN-LAST:event_jTextField3KeyPressed

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

    private void empCodeJCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empCodeJCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empCodeJCActionPerformed

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      /*  java.sql.Date tdate = new java.sql.Date(date.getDate().getTime());

        addDateTable(tdate);
        String work_code=null;
        String division=null;
        division=(String) division_jc.getSelectedItem();
        work_code=(String) workCode.getSelectedItem();
        String ndate=null;
        ndate=tdate.toString();
        StringBuilder ne_date = new StringBuilder(ndate);
        ne_date.setCharAt(4, '_');
        ne_date.setCharAt(7, '_');

        if (sunday.isSelected() == false) {
            int rows = 0;
            for (; rows < table.getRowCount(); rows++) {
                if (table.getValueAt(rows, 0) != null) {
                    int normaldays = 0;
                    System.out.println(table.getValueAt(rows, 0));
                    normaldays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", table.getValueAt(rows, 0), "normal_days"));
                    normaldays++;
                    dbm.updateDatabase("checkroll_personalinfo", "code", table.getValueAt(rows, 0), "normal_days", normaldays);
                    //updating newly created tables
                    try {
                        dbm.insert("INSERT INTO d_"+ne_date+"(name,emp_code,work_code,division) VALUES('"+rows+"','"+table.getValueAt(rows, 0)+"','"+work_code+"','"+division+"')");
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
                    sundays = Integer.parseInt(dbm.checknReturnData("checkroll_personalinfo", "code", table.getValueAt(rows, 0), "sundays"));
                    sundays++;
                    dbm.updateDatabase("checkroll_personalinfo", "code", table.getValueAt(rows, 0), "sundays", sundays);

                    try {
                        dbm.insert("INSERT INTO d_"+ne_date+"(name,emp_code,work_code) VALUES('"+rows+"','"+table.getValueAt(rows, 0)+"','s')");
                    } catch (SQLException ex) {
                        Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            JOptionPane.showMessageDialog(null, "Details are saved for the \n" + tdate, "Message", JOptionPane.INFORMATION_MESSAGE);

        }*/
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox empCodeJC;
    private javax.swing.JLabel empName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox workCode;
    private javax.swing.JLabel work_code;
    // End of variables declaration//GEN-END:variables
}
