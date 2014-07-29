
import javax.swing.JOptionPane;


public class GL_Add_leafcategory extends javax.swing.JPanel {
    DatabaseManager dbm = new DatabaseManager();
    
    LeafCategory lcobject = new LeafCategory();
    public GL_Add_leafcategory() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        Code = new javax.swing.JComboBox();
        Trans_rate = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Save1 = new javax.swing.JButton();

        jLabel1.setText("Category Name");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Code");

        Code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        Code.setEditable(true);
        Code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("leaf_category", "category_name")));
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

        Trans_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Trans_rateActionPerformed(evt);
            }
        });
        Trans_rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trans_rateKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        jPanel1.setForeground(new java.awt.Color(0, 102, 0));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        save.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                saveFocusGained(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Save1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Trans_rate)
                            .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(490, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Trans_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 334, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CodeItemStateChanged
        try {
            if (Code.getSelectedItem() != null) {
                Trans_rate.setText(dbm.checknReturnStringData("leaf_category", "category_name", Code.getSelectedItem().toString(), "category_id"));
                Trans_rate.requestFocusInWindow();
            }
        } catch (Exception e) {
            Code.setSelectedIndex(0);
        }
    }//GEN-LAST:event_CodeItemStateChanged

    private void CodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodeActionPerformed

    private void Trans_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Trans_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Trans_rateActionPerformed

    private void Trans_rateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trans_rateKeyPressed
        // interface_events.Change_focus_Enterkey_c(account_class, evt);
    }//GEN-LAST:event_Trans_rateKeyPressed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String Trans_id = Code.getSelectedItem().toString();
        double trans_rate =Double.parseDouble(Trans_rate.getText());

        lcobject = new LeafCategory(Trans_id, trans_rate);
        lcobject.addToDataBase();

        Trans_rate.setText(null);
        Code.setSelectedIndex(0);

        JOptionPane.showMessageDialog(Trans_rate, "Success!");
    }//GEN-LAST:event_saveActionPerformed

    private void saveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_saveFocusGained
        // interface_events.Respond_enter(save, evt);
    }//GEN-LAST:event_saveFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Trans_rate.setText(null);

        Code.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save1ActionPerformed
        try {
            
       
     dbm.updateDatabase("leaf_category", "category_name", Code.getSelectedItem().toString(), "category_id", Trans_rate.getText());
        Trans_rate.setText(null);
        Code.setSelectedIndex(0);
        JOptionPane.showMessageDialog(Trans_rate, "Entry updated!");
 } catch (Exception e) {
     JOptionPane.showMessageDialog(Trans_rate, "Failed!");
        }
        
    }//GEN-LAST:event_Save1ActionPerformed

    private void Save1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Save1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Save1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Code;
    private javax.swing.JButton Save1;
    private javax.swing.JTextField Trans_rate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
