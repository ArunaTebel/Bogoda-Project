/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */
public class pPRCR extends javax.swing.JPanel {

    Interface_Events interface_events = new Interface_Events();

    /**
     * Creates new form pPRCR
     */
    public pPRCR() {
        initComponents();
    }
    
    
    
    public void focus(){
    this.requestFocus();
    jButton1.requestFocus();
    
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GL_content = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Add_combo = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        View_Combo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153), 3));

        javax.swing.GroupLayout GL_contentLayout = new javax.swing.GroupLayout(GL_content);
        GL_content.setLayout(GL_contentLayout);
        GL_contentLayout.setHorizontalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        GL_contentLayout.setVerticalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("Work Entry");
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
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        jButton2.setText("Salary");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton2KeyReleased(evt);
            }
        });

        jButton3.setText(" Advances");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton3FocusGained(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton3KeyReleased(evt);
            }
        });

        jButton4.setText("Installments");
        jButton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton4FocusGained(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton4KeyReleased(evt);
            }
        });

        jButton5.setText("Loans");
        jButton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton5FocusGained(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton5KeyReleased(evt);
            }
        });

        Add_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Employee", "Staff pay info", "Chkroll pay info", "Work code details", "Division details", "Leaf Rate", "----------" }));
        Add_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_comboActionPerformed(evt);
            }
        });
        Add_combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Add_comboKeyReleased(evt);
            }
        });

        jButton6.setText("Add");

        jButton7.setText("Veiw");

        View_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Work Code Details", "Note Analysis", "Transport Code", "Leaf Rate", "----------" }));
        View_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_ComboActionPerformed(evt);
            }
        });
        View_Combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                View_ComboKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll.png"))); // NOI18N
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Add_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(View_Combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(View_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE))
            .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PRCR_work_entry workEntry = new PRCR_work_entry();

        GL_content.removeAll();

        workEntry.setSize(GL_content.getSize());

        GL_content.add(workEntry);
        validate();
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        PRCR_salary sal = new PRCR_salary();

        GL_content.removeAll();

        sal.setSize(GL_content.getSize());

        GL_content.add(sal);
        validate();
        repaint();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GL_Other_Advances otheradd = new GL_Other_Advances();

        GL_content.removeAll();

        otheradd.setSize(GL_content.getSize());

        GL_content.add(otheradd);
        validate();
        repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Add_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_comboActionPerformed
        String selection = (String) Add_combo.getSelectedItem();
        if (selection.equalsIgnoreCase("Staff pay info")) {

            PRCR_Add_staffpay Addstfpay = new PRCR_Add_staffpay();

            GL_content.removeAll();

            Addstfpay.setSize(GL_content.getSize());

            GL_content.add(Addstfpay);
            validate();
            repaint();
        }
        if (selection.equalsIgnoreCase("Chkroll pay info")) {

            PRCR_Add_Chekrlpay Addcheck = new PRCR_Add_Chekrlpay();

            GL_content.removeAll();

            Addcheck.setSize(GL_content.getSize());

            GL_content.add(Addcheck);
            validate();
            repaint();
        }

        if (selection.equalsIgnoreCase("Employee")) {

            PRCR_Add_Employee Addemploye = new PRCR_Add_Employee();

            GL_content.removeAll();

            Addemploye.setSize(GL_content.getSize());

            GL_content.add(Addemploye);
            validate();
            repaint();
        }
        if (selection.equalsIgnoreCase("Work code details")) {

            PRCR_Add_Workcode Addwrkcd = new PRCR_Add_Workcode();

            GL_content.removeAll();

            Addwrkcd.setSize(GL_content.getSize());

            GL_content.add(Addwrkcd);
            validate();
            repaint();
        }
        if (selection.equalsIgnoreCase("Division details")) {

            PRCR_Add_Division Adddv = new PRCR_Add_Division();

            GL_content.removeAll();

            Adddv.setSize(GL_content.getSize());

            GL_content.add(Adddv);
            validate();
            repaint();
        }
    }//GEN-LAST:event_Add_comboActionPerformed

    private void View_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_ComboActionPerformed
        String selection = (String) View_Combo.getSelectedItem();
        if (selection.equalsIgnoreCase("Work Code Details")) {

            PRCR_view_workCodeDetails workcode = new PRCR_view_workCodeDetails();

            GL_content.removeAll();

            workcode.setSize(GL_content.getSize());

            GL_content.add(workcode);
            validate();
            repaint();
    }//GEN-LAST:event_View_ComboActionPerformed

    }

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        interface_events.Change_focus_down_b_b(jButton2, evt);
    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyReleased
        interface_events.Change_focus_down_b_b(jButton3, evt);
        interface_events.Change_focus_Up_b_b(jButton1, evt);
    }//GEN-LAST:event_jButton2KeyReleased

    private void jButton3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyReleased
        interface_events.Change_focus_down_b_b(jButton4, evt);
        interface_events.Change_focus_Up_b_b(jButton2, evt);
    }//GEN-LAST:event_jButton3KeyReleased

    private void jButton4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyReleased
        interface_events.Change_focus_down_b_b(jButton5, evt);
        interface_events.Change_focus_Up_b_b(jButton3, evt);
    }//GEN-LAST:event_jButton4KeyReleased

    private void jButton5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyReleased
        interface_events.Change_focus_right_c(Add_combo, evt);
        interface_events.Change_focus_Up_b_b(jButton4, evt);
    }//GEN-LAST:event_jButton5KeyReleased

    private void Add_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_comboKeyReleased
        interface_events.Change_focus_right_c(View_Combo, evt);
        interface_events.Change_focus_left_b_b(jButton5, evt);
    }//GEN-LAST:event_Add_comboKeyReleased

    private void View_ComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_View_ComboKeyReleased
        interface_events.Change_focus_left_c(Add_combo, evt);
    }//GEN-LAST:event_View_ComboKeyReleased

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        interface_events.Respond_enter(jButton1, evt);
    }//GEN-LAST:event_jButton1FocusGained

    private void jButton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusGained
        interface_events.Respond_enter(jButton2, evt);
    }//GEN-LAST:event_jButton2FocusGained

    private void jButton3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton3FocusGained
        interface_events.Respond_enter(jButton3, evt);
    }//GEN-LAST:event_jButton3FocusGained

    private void jButton4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton4FocusGained
       interface_events.Respond_enter(jButton4, evt);
    }//GEN-LAST:event_jButton4FocusGained

    private void jButton5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusGained
        interface_events.Respond_enter(jButton5, evt);
    }//GEN-LAST:event_jButton5FocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Add_combo;
    private javax.swing.JPanel GL_content;
    private javax.swing.JComboBox View_Combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
