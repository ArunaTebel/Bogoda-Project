
/**
 *
 * @author Pramo
 */
public class Add_Management_rates extends javax.swing.JPanel {

    Rate_Details aobject = new Rate_Details();
    DatabaseManager dbm = new DatabaseManager();
    Interface_Events interface_events = new Interface_Events();

    /**
     * Creates new form Add_Rate_details
     */
    public Add_Management_rates() {
        initComponents();
    }

    
   public void focus(){
this.requestFocus();
Code.requestFocus();

}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Save1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        account_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Rate = new javax.swing.JTextField();
        Code = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

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
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Save1.setBackground(new java.awt.Color(0, 51, 255));
        Save1.setForeground(new java.awt.Color(255, 255, 255));
        Save1.setText("Update");
        Save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save1ActionPerformed(evt);
            }
        });
        Save1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Save1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3))
                            .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Code Name");

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

        jLabel1.setText("Description");

        Rate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                RateInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
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

        Code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        Code.setEditable(true);
        Code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("rate_manegement_details", "Code_name")));
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

        jLabel4.setText("Rate");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(account_name)
                    .addComponent(Code, 0, 129, Short.MAX_VALUE)
                    .addComponent(Rate, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(518, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        aobject.setName(account_name.getText());
        aobject.setCode(Code.getSelectedItem().toString());
       // aobject.settype(account_class.getSelectedItem().toString());
        aobject.setRate(Double.parseDouble(Rate.getText()));

       // aobject.setDiscription(Description.getText());
        //aobject.setInstallments(jComboBox1.getSelectedIndex()+1);
        aobject.addToDataBase2();

        account_name.setText(null);
        Code.setSelectedIndex(0);
       // jComboBox1.setSelectedIndex(0);
      //  account_class.setSelectedIndex(0);
        Rate.setText(null);

        //Description.setText(null);

        Code.requestFocus();


    }//GEN-LAST:event_SaveActionPerformed

    private void RateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_RateInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_RateInputMethodTextChanged

    private void RateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RateKeyPressed
        interface_events.Change_focus_Enterkey_t_b(Rate, Save, evt);
    }//GEN-LAST:event_RateKeyPressed

    private void RateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RateKeyReleased
        //current_balance.setText(opening_balance.getText());
    }//GEN-LAST:event_RateKeyReleased

    private void account_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_account_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_nameActionPerformed

    private void CodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CodeItemStateChanged

        if (Code.getSelectedItem() != null) {
            account_name.setText(dbm.checknReturnStringData("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "Name"));
            Rate.setText(dbm.checknReturnStringData("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "rate"));
          //  Description.setText(dbm.checknReturnStringData("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "Rate_Description"));
          //  account_class.setSelectedItem(dbm.checknReturnStringData("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "type"));
            account_name.requestFocusInWindow();
        }

    }//GEN-LAST:event_CodeItemStateChanged

    private void CodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        account_name.setText(null);
        Code.setSelectedIndex(0);
      //  account_class.setSelectedIndex(0);
        Rate.setText(null);

        //Description.setText(null);

        Code.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void account_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_nameKeyPressed
      //  interface_events.Change_focus_Enterkey_c(account_class, evt);
    }//GEN-LAST:event_account_nameKeyPressed

    private void SaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaveKeyPressed
        interface_events.Respond_enter(Save, null);
    }//GEN-LAST:event_SaveKeyPressed

    private void Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save1ActionPerformed
       dbm.updateDatabase("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "Name", account_name.getText());
      // dbm.updateDatabase("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "type", account_class.getSelectedItem().toString());
       dbm.updateDatabase("rate_manegement_details", "Code_name", Code.getSelectedItem().toString(), "rate", Double.parseDouble(Rate.getText()));
      // dbm.updateDatabase("rate_detils", "Code_name", Code.getSelectedItem().toString(), "Name", account_name.getText());
    }//GEN-LAST:event_Save1ActionPerformed

    private void Save1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Save1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Save1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Code;
    private javax.swing.JTextField Rate;
    private javax.swing.JButton Save;
    private javax.swing.JButton Save1;
    private javax.swing.JTextField account_name;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
