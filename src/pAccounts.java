
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class pAccounts extends javax.swing.JPanel {

    Interface_Events interface_events = new Interface_Events();

    public pAccounts() {
        initComponents();
    }

    public void focus() {
        this.requestFocus();
        jButton1.requestFocus();

    }

    public void quit() {
        ACC_Add_account acnt = new ACC_Add_account();
        pAccounts AddAcc = new pAccounts();
        GL_content.removeAll();
        GL_content.add(AddAcc);

        validate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GL_content = new javax.swing.JPanel();
        view = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Add_combo = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153), 3));

        javax.swing.GroupLayout GL_contentLayout = new javax.swing.GroupLayout(GL_content);
        GL_content.setLayout(GL_contentLayout);
        GL_contentLayout.setHorizontalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        GL_contentLayout.setVerticalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        view.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Opening Balances", "Error Detection", "Control Panel", "----------" }));
        view.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewItemStateChanged(evt);
            }
        });
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        view.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                viewKeyReleased(evt);
            }
        });

        jButton7.setText("Veiw");
        jButton7.setEnabled(false);

        jButton6.setText("Add");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Add_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Accounts", "Main Accounts", "Sub Accounts", "----------" }));
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

        jButton4.setText("Reports");
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

        jButton3.setText("Journals");
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

        jButton2.setText("Payments");
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

        jButton1.setText("Receipts");
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Accounts.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setRequestFocusEnabled(false);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("jButton5");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("jButton5");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton8.setText("Update");
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

        jButton11.setText("Change Acc. Period");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Cost of Pay");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton12FocusGained(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton12KeyReleased(evt);
            }
        });

        jButton13.setText("Edit Receipts");
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

        jButton14.setText("Edit Payments");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton14FocusGained(evt);
            }
        });
        jButton14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton14KeyReleased(evt);
            }
        });

        jButton15.setText("Edit Journals");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jButton15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton15FocusGained(evt);
            }
        });
        jButton15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton15KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Add_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(view, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Add_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        String selection = (String) view.getSelectedItem();
        if (selection.equalsIgnoreCase("Journals")) {

            ACC_Journal_View jview = new ACC_Journal_View();

            GL_content.removeAll();

            jview.setSize(GL_content.getSize());

            GL_content.add(jview);
            validate();
            repaint();
            // jview.focus();
        } else if (selection.equalsIgnoreCase("Receipts")) {

            ACC_Reciept_View rview = new ACC_Reciept_View();

            GL_content.removeAll();

            rview.setSize(GL_content.getSize());

            GL_content.add(rview);
            validate();
            repaint();
            // jview.focus();
        }
        if (selection.equalsIgnoreCase("Payments")) {

            ACC_Payment_View pview = new ACC_Payment_View();

            GL_content.removeAll();

            pview.setSize(GL_content.getSize());

            GL_content.add(pview);
            validate();
            repaint();
            // jview.focus();
        }
        if (selection.equalsIgnoreCase("Error Detection")) {
            Acc_Error_Detection error = new Acc_Error_Detection();
            error.setVisible(true);
        }

        if (selection.equalsIgnoreCase("Control Panel")) {
            Acc_Control_Panel ctrl = new Acc_Control_Panel();
            ctrl.setVisible(true);
        }
    }//GEN-LAST:event_viewActionPerformed

    private void Add_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_comboActionPerformed
        String selection = (String) Add_combo.getSelectedItem();
        if (selection.equalsIgnoreCase("Accounts")) {

            ACC_Add_account AddAcc = new ACC_Add_account();

            GL_content.removeAll();

            AddAcc.setSize(GL_content.getSize());

            GL_content.add(AddAcc);
            validate();
            repaint();
            AddAcc.focus();
        }
        if (selection.equalsIgnoreCase("Main Accounts")) {

            ACC_Add_main_account AddAcc = new ACC_Add_main_account();

            GL_content.removeAll();

            AddAcc.setSize(GL_content.getSize());

            GL_content.add(AddAcc);
            validate();
            repaint();
            AddAcc.focus();
        }
        if (selection.equalsIgnoreCase("Sub Accounts")) {

            ACC_Add_sub_account AddAcc = new ACC_Add_sub_account();

            GL_content.removeAll();

            AddAcc.setSize(GL_content.getSize());

            GL_content.add(AddAcc);
            validate();
            repaint();
            AddAcc.focus();
        }
    }//GEN-LAST:event_Add_comboActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        //System.out.println(" ok ");
        ACC_payments payments = new ACC_payments();

        // System.out.println(" ok 1");
        GL_content.removeAll();

        payments.setSize(GL_content.getSize());

        GL_content.add(payments);
        validate();
        repaint();

        payments.focus();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ACC_recepts recepts = new ACC_recepts();

        GL_content.removeAll();

        recepts.setSize(GL_content.getSize());

        GL_content.add(recepts);
        validate();
        repaint();
        recepts.focus();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        interface_events.Change_focus_right_c(Add_combo, evt);
        interface_events.Change_focus_Up_b_b(jButton3, evt);
    }//GEN-LAST:event_jButton4KeyReleased

    private void Add_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_comboKeyReleased
        interface_events.Change_focus_right_c(view, evt);
        interface_events.Change_focus_left_b_b(jButton4, evt);
    }//GEN-LAST:event_Add_comboKeyReleased

    private void viewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewKeyReleased
        interface_events.Change_focus_left_c(Add_combo, evt);
    }//GEN-LAST:event_viewKeyReleased

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ACC_journals journals = new ACC_journals();

        GL_content.removeAll();

        journals.setSize(GL_content.getSize());

        GL_content.add(journals);
        validate();
        repaint();

        journals.foucs();
    }//GEN-LAST:event_jButton3ActionPerformed


    private void viewItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_viewItemStateChanged

        if (view.getSelectedItem().toString() == "Journals") {
            ACC_Journal_View jview = new ACC_Journal_View();
            jview.setVisible(true);
            jview.Start();
            jview.focus();
        } else if (view.getSelectedItem().toString() == "Receipts") {
            ACC_Reciept_View rview = new ACC_Reciept_View();
            rview.setVisible(true);
            rview.focus();
        }
        if (view.getSelectedItem().toString() == "Payments") {
            ACC_Payment_View pview = new ACC_Payment_View();
            pview.setVisible(true);
            pview.focus();
        }
        if (view.getSelectedItem().toString() == "Opening Balances") {
            Acc_Change_Op_Bal AddAcc = new Acc_Change_Op_Bal();
            GL_content.removeAll();
            AddAcc.setSize(GL_content.getSize());
            GL_content.add(AddAcc);
            validate();
            repaint();
            AddAcc.focus();
        }
        /* if(view.getSelectedItem().toString() == "Error Detection"){
         Acc_Error_Detection error = new Acc_Error_Detection();
         error.setVisible(true);
         }*/

    }//GEN-LAST:event_viewItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        New_window NW = new New_window();
        NW.setVisible(true);
        ACC_recepts home = new ACC_recepts();
        NW.paint(home, "Receipts");
        NW.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        New_window NW = new New_window();
        NW.setVisible(true);
        ACC_payments home = new ACC_payments();
        NW.paint(home, "Payments");
        NW.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        New_window NW = new New_window();
        NW.setVisible(true);
        ACC_journals home = new ACC_journals();
        NW.paint(home, "Journals");
        NW.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Reports_Acc rep = new Reports_Acc();

        GL_content.removeAll();

        rep.setSize(GL_content.getSize());

        GL_content.add(rep);
        validate();
        repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ACC_Update updt = new ACC_Update();

        GL_content.removeAll();

        updt.setSize(GL_content.getSize());

        GL_content.add(updt);
        validate();
        repaint();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8FocusGained

    private void jButton8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8KeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Acc_Change_Period change = new Acc_Change_Period();
        change.start_set();
        change.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        ACC_COP cop = new ACC_COP();
        cop.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton12FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12FocusGained

    private void jButton12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12KeyReleased

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DatabaseManager dbm = DatabaseManager.getDbCon();

        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "editInterface") == 0) {
            try {
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "editInterface" + "','" + 1 + "')");
            } catch (SQLException ex) {
                Logger.getLogger(pAccounts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control", "editInterface", "value")) == 1) {
         //   ACC_Edit_Receipts_new_database receiptEdit = new ACC_Edit_Receipts_new_database();
         //   receiptEdit.setVisible(true);

            New_window NW = new New_window();
            NW.setVisible(true);
            ACC_Edit_Receipts_new_database receiptEdit = new ACC_Edit_Receipts_new_database();
            NW.paint(receiptEdit, "Edit Receipts");
            NW.setVisible(true);
            
        } else {
            ACC_Reciept_View rview = new ACC_Reciept_View();

            GL_content.removeAll();

            rview.setSize(GL_content.getSize());

            GL_content.add(rview);
            validate();
            repaint();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13FocusGained

    private void jButton13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13KeyReleased

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        DatabaseManager dbm = DatabaseManager.getDbCon();

        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "editInterface") == 0) {
            try {
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "editInterface" + "','" + 1 + "')");
            } catch (SQLException ex) {
                Logger.getLogger(pAccounts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control", "editInterface", "value")) == 1) {
          //  ACC_Edit_Payments_new_database paymentEdit = new ACC_Edit_Payments_new_database();
          //  paymentEdit.setVisible(true);
            
            New_window NW = new New_window();
            NW.setVisible(true);
            ACC_Edit_Payments_new_database paymentEdit = new ACC_Edit_Payments_new_database();
            NW.paint(paymentEdit, "Edit Paymnets");
            NW.setVisible(true);
            
        } else {
            ACC_Payment_View pview = new ACC_Payment_View();

            GL_content.removeAll();

            pview.setSize(GL_content.getSize());

            GL_content.add(pview);
            validate();
            repaint();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton14FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14FocusGained

    private void jButton14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14KeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        DatabaseManager dbm = DatabaseManager.getDbCon();

        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "editInterface") == 0) {
            try {
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "editInterface" + "','" + 1 + "')");
            } catch (SQLException ex) {
                Logger.getLogger(pAccounts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Integer.parseInt(dbm.checknReturnData("account_control_panel_details", "control", "editInterface", "value")) == 1) {
          //  ACC_Edit_Journals_new_database journalEdit = new ACC_Edit_Journals_new_database();
          //  journalEdit.setVisible(true);
            
            New_window NW = new New_window();
            NW.setVisible(true);
            ACC_Edit_Journals_new_database journalEdit = new ACC_Edit_Journals_new_database();
            NW.paint(journalEdit, "Edit Journals");
            NW.setVisible(true);
            
        } else {
            ACC_Journal_View jview = new ACC_Journal_View();

            GL_content.removeAll();

            jview.setSize(GL_content.getSize());

            GL_content.add(jview);
            validate();
            repaint();
        }

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton15FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15FocusGained

    private void jButton15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton15KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Add_combo;
    private javax.swing.JPanel GL_content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox view;
    // End of variables declaration//GEN-END:variables
}
