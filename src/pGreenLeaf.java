
import java.awt.Container;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class pGreenLeaf extends javax.swing.JPanel {

     Interface_Events interface_events = new Interface_Events();
    
    /**
     * Creates new form pGreenLeaf
     */
    public pGreenLeaf() {
        initComponents();
       
         
    }
    
    public void focus(){
    this.requestFocus();
    jButton1.requestFocus();
    
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        Add_combo = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        GL_content = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 3));

        jButton1.setText("Manual Entry");
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

        jButton2.setText("Cash Advances");
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

        jButton3.setText("Other Advances");
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

        jButton4.setText("Welfare");
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

        Add_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "Supplier", "Category", "Leaf category", "Transport rate", "AdvanceType", "----------" }));
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
        jButton6.setBorderPainted(false);
        jButton6.setEnabled(false);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---------", "PreDebits", "Extra Amount", "----------" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox3KeyReleased(evt);
            }
        });

        jButton7.setText("Alter");
        jButton7.setBorderPainted(false);
        jButton7.setEnabled(false);

        javax.swing.GroupLayout GL_contentLayout = new javax.swing.GroupLayout(GL_content);
        GL_content.setLayout(GL_contentLayout);
        GL_contentLayout.setHorizontalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        GL_contentLayout.setVerticalGroup(
            GL_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Green leaf.png"))); // NOI18N
        jLabel1.setRequestFocusEnabled(false);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Add_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Add_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, Short.MAX_VALUE))
            .addComponent(GL_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        
        GL_CashAdvance_plus_book cashadvance = new GL_CashAdvance_plus_book();
       
       interface_events.insertpanel(cashadvance, GL_content);
       
       
        cashadvance.focus();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Add_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_comboActionPerformed
        String selection = (String) Add_combo.getSelectedItem();
        if (selection.equalsIgnoreCase("Supplier")) {
            
          GL_Add_Supplier Addsupp = new GL_Add_Supplier();

       GL_content.removeAll();

       Addsupp.setSize(GL_content.getSize());

       GL_content.add(Addsupp);
        validate();
        repaint();
        }
        if (selection.equalsIgnoreCase("Category")) {
            
          Add_category Addcat = new Add_category();

       GL_content.removeAll();

       Addcat.setSize(GL_content.getSize());

       GL_content.add(Addcat);
        validate();
        repaint();
        }
        
        if (selection.equalsIgnoreCase("Leaf category")) {
            
          GL_Add_leafcategory Addcat = new GL_Add_leafcategory();

       GL_content.removeAll();

       Addcat.setSize(GL_content.getSize());

       GL_content.add(Addcat);
        validate();
        repaint();
        }
        
        if (selection.equalsIgnoreCase("Transport rate")) {
            
          GL_Add_transport_rate Addtrate = new GL_Add_transport_rate();

       GL_content.removeAll();

       Addtrate.setSize(GL_content.getSize());

       GL_content.add(Addtrate);
        validate();
        repaint();
        Addtrate.focus();
        }
         if (selection.equalsIgnoreCase("AdvanceType")) {
            
       GL_Add_advance_types Addtrate = new GL_Add_advance_types();

       GL_content.removeAll();

       Addtrate.setSize(GL_content.getSize());

       GL_content.add(Addtrate);
        validate();
        repaint();
        Addtrate.focus();
        }
    }//GEN-LAST:event_Add_comboActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
      String selection = jComboBox3.getSelectedItem().toString();
        
        if (selection.equalsIgnoreCase("Extra Amount")) {
            
          GL_extrapay Addsupp = new GL_extrapay();

       GL_content.removeAll();

       Addsupp.setSize(GL_content.getSize());

       GL_content.add(Addsupp);
        validate();
        repaint();
        }
        if (selection.equalsIgnoreCase("PreDebits")) {
            
//          Add_category Addcat = new Add_category();
//
//       GL_content.removeAll();
//
//       Addcat.setSize(GL_content.getSize());
//
//       GL_content.add(Addcat);
//        validate();
//        repaint();
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GLmanual_entry glmnEntry = new GLmanual_entry();

        GL_content.removeAll();

        glmnEntry.setSize(GL_content.getSize());

        GL_content.add(glmnEntry);
        validate();
        repaint();  
        glmnEntry.focus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GL_other_Advance_plus_book otheradd = new GL_other_Advance_plus_book();

        GL_content.removeAll();

        otheradd.setSize(GL_content.getSize());

        GL_content.add(otheradd);
        validate();
        repaint(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        GL_Welfare wel = new GL_Welfare();

        GL_content.removeAll();

        wel.setSize(GL_content.getSize());

        GL_content.add(wel);
        validate();
        repaint(); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GL_Loans loans = new GL_Loans();

        GL_content.removeAll();

        loans.setSize(GL_content.getSize());

        GL_content.add(loans);
        validate();
        repaint(); 
    }//GEN-LAST:event_jButton5ActionPerformed

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
         interface_events.Change_focus_right_c(jComboBox3, evt);
         interface_events.Change_focus_left_b_b(jButton5, evt);
        
         
    }//GEN-LAST:event_Add_comboKeyReleased

    private void jComboBox3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyReleased
        interface_events.Change_focus_left_c(Add_combo, evt);
    }//GEN-LAST:event_jComboBox3KeyReleased

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
