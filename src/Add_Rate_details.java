

/**
 *
 * @author Pramo
 */
public class Add_Rate_details extends javax.swing.JPanel {
Rate_Details aobject = new Rate_Details();
DatabaseManager dbm = new DatabaseManager();
Interface_Events interface_events = new Interface_Events();
    /**
     * Creates new form Add_Rate_details
     */
    public Add_Rate_details() {
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

        jLabel5 = new javax.swing.JLabel();
        account_class = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Rate = new javax.swing.JTextField();
        account_name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Code_name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Description = new javax.swing.JTextField();
        Code = new javax.swing.JComboBox();

        jLabel5.setText("Rs");

        account_class.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manuar", "Tea", "Chemicals", "BogodaStores", " " }));
        account_class.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                account_classKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        Save.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaveKeyPressed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Quit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Type");

        jLabel1.setText(" Name");

        jLabel2.setText("Account code");

        jLabel4.setText("Rate");

        Rate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                RateInputMethodTextChanged(evt);
            }
        });
        Rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RateKeyReleased(evt);
            }
        });

        account_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                account_nameActionPerformed(evt);
            }
        });
        account_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                account_nameKeyPressed(evt);
            }
        });

        jLabel8.setText("Code Name");

        Code_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Code_nameKeyPressed(evt);
            }
        });

        jLabel6.setText("Description");

        Description.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                DescriptionInputMethodTextChanged(evt);
            }
        });
        Description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DescriptionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DescriptionKeyReleased(evt);
            }
        });

        Code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        Code.setEditable(true);
        Code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("rate_details", "Rate_code")));
        Code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CodeItemStateChanged(evt);
            }
        });
        Code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(53, 53, 53))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(account_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Code_name, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(451, 451, 451))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(account_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Code_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        aobject.setName(account_name.getText());
       aobject.setCode(Integer.parseInt(Code.getSelectedItem().toString()));
        aobject.settype(account_class.getSelectedItem().toString());
        aobject.setRate(Double.parseDouble(Rate.getText()));
        aobject.setCodename(Code_name.getText());
       
        
        aobject.setDiscription(Description.getText());
        aobject.addToDataBase();
        
        account_name.setText(null);
        Code.setSelectedIndex(0);
        account_class.setSelectedIndex(0);
        Rate.setText(null);
        Code_name.setText(null);
        Description.setText(null);
        
        Code.requestFocus();
        
        
    }//GEN-LAST:event_SaveActionPerformed

    private void RateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_RateInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_RateInputMethodTextChanged

    private void RateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RateKeyPressed
        interface_events.Change_focus_Enterkey_t(Description, evt);
    }//GEN-LAST:event_RateKeyPressed

    private void RateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RateKeyReleased
        //current_balance.setText(opening_balance.getText());
    }//GEN-LAST:event_RateKeyReleased

    private void account_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_account_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_nameActionPerformed

    private void DescriptionInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_DescriptionInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DescriptionInputMethodTextChanged

    private void DescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DescriptionKeyPressed
        interface_events.Change_focus_Enterkey_t_b(Rate, Save, evt);
    }//GEN-LAST:event_DescriptionKeyPressed

    private void DescriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DescriptionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DescriptionKeyReleased

    private void CodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CodeItemStateChanged
      
        
        if(Code.getSelectedItem()!=null){
            account_name.setText(dbm.checknReturnData("rate_details", "Rate_code", Integer.parseInt(Code.getSelectedItem().toString()), "Name"));
              Code_name.setText(dbm.checknReturnData("rate_details", "Rate_code", Integer.parseInt(Code.getSelectedItem().toString()), "Code_name"));
           Rate.setText(dbm.checknReturnData("rate_details", "Rate_code", Integer.parseInt(Code.getSelectedItem().toString()), "rate"));
              Description.setText(dbm.checknReturnData("rate_details", "Rate_code", Integer.parseInt(Code.getSelectedItem().toString()), "Rate_Description"));
              account_class.setSelectedItem(dbm.checknReturnData("rate_details", "Rate_code", Integer.parseInt(Code.getSelectedItem().toString()), "type"));
        account_name.requestFocusInWindow();}
       
    }//GEN-LAST:event_CodeItemStateChanged

    private void CodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       account_name.setText(null);
        Code.setSelectedIndex(0);
        account_class.setSelectedIndex(0);
        Rate.setText(null);
        Code_name.setText(null);
        Description.setText(null);
        
        Code.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void account_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_nameKeyPressed
      interface_events.Change_focus_Enterkey_c(account_class, evt);
    }//GEN-LAST:event_account_nameKeyPressed

    private void account_classKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_classKeyPressed
        interface_events.Change_focus_Enterkey_t(Code_name, evt);
    }//GEN-LAST:event_account_classKeyPressed

    private void Code_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Code_nameKeyPressed
       interface_events.Change_focus_Enterkey_t(Rate, evt);
    }//GEN-LAST:event_Code_nameKeyPressed

    private void SaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaveKeyPressed
        interface_events.Respond_enter(Save, null);
    }//GEN-LAST:event_SaveKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Code;
    private javax.swing.JTextField Code_name;
    private javax.swing.JTextField Description;
    private javax.swing.JTextField Rate;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox account_class;
    private javax.swing.JTextField account_name;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
