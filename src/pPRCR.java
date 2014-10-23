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
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153), 3));

        javax.swing.GroupLayout GL_contentLayout = new javax.swing.GroupLayout(GL_content);
        GL_content.setLayout(GL_contentLayout);
        GL_contentLayout.setHorizontalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
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

        jButton3.setText("Cash Advances");
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

        jButton4.setText("Other Advances");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
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
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
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

        Add_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Employee", "Staff pay info", "Chkroll pay info", "Work code details", "Division details", "Coins and Debits", "----------" }));
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
        jButton6.setEnabled(false);

        jButton7.setText("Veiw");
        jButton7.setEnabled(false);

        View_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Add Coins and Debits", "Note Analysis", "Transport Code", "Leaf Rate", "----------" }));
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
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton8.setText("Welfare");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton8FocusGained(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton8KeyReleased(evt);
            }
        });

        jButton9.setText("Holiday Pay");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("jButton10");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("jButton10");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Debits Pay");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jButton13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton13FocusGained(evt);
            }
        });
        jButton13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton13KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Add_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(View_Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Add_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(View_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, Short.MAX_VALUE))
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
        PRCR_cash_advances otheradd = new PRCR_cash_advances();

        GL_content.removeAll();

        otheradd.setSize(GL_content.getSize());

        GL_content.add(otheradd);
        validate();
        repaint();
        otheradd.focus();
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
        if (selection.equalsIgnoreCase("Add Coins and Debits")) {

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PRCR_other_advances otheradd = new PRCR_other_advances();

        GL_content.removeAll();

        otheradd.setSize(GL_content.getSize());

        GL_content.add(otheradd);
        validate();
        repaint();
        otheradd.focus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8FocusGained

    private void jButton8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8KeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        PRCR_Welfare wel = new PRCR_Welfare();

        GL_content.removeAll();

        wel.setSize(GL_content.getSize());

        GL_content.add(wel);
        validate();
        repaint();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     PRCR_Loans loan = new PRCR_Loans();

        GL_content.removeAll();

        loan.setSize(GL_content.getSize());

        GL_content.add(loan);
        validate();
        repaint();
        loan.focus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        PRCRHoliday newHoliday = new PRCRHoliday();

        GL_content.removeAll();

        newHoliday.setSize(GL_content.getSize());

        GL_content.add(newHoliday);
        validate();
        repaint();
        //newHoliday.focus();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
     New_window NW = new New_window();
        NW.setVisible(true);
            PRCR_work_entry workEntry = new PRCR_work_entry();
       
        NW.paint(workEntry,"Work Entry");
        NW.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      PRCR_cash_advances otheradd = new PRCR_cash_advances(); 
       New_window NW = new New_window();
        NW.setVisible(true);
           
       
        NW.paint(otheradd,"Checkroll Cash Advances");
        NW.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
PRCR_other_advances otheradd = new PRCR_other_advances();    New_window NW = new New_window();
        NW.setVisible(true);
           
       
        NW.paint(otheradd,"Checkroll Other Advances");
        NW.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      PRCR_debit_pay wel= new PRCR_debit_pay();    New_window NW = new New_window();
       

        GL_content.removeAll();

        wel.setSize(GL_content.getSize());

        GL_content.add(wel);
        validate();
        repaint();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13FocusGained

    private void jButton13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Add_combo;
    private javax.swing.JPanel GL_content;
    private javax.swing.JComboBox View_Combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
