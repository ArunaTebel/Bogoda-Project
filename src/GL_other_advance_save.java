
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class GL_other_advance_save extends javax.swing.JPanel {

    GL_other_advances_class oadvance = new GL_other_advances_class();
    int i = 0;

    int no_of_pages;

    DatabaseManager dbm = DatabaseManager.getDbCon();
    int a = dbm.Checking_Length_Of_The_Table("gl_other_advance_book", "tr_no");
    int a_2 = 0;
    Interface_Events interface_events = new Interface_Events();
    Common_Other_Advance_Database_Handling filter = new Common_Other_Advance_Database_Handling();
    Table_handler table_handler = new Table_handler();

    public void focus() {
        this.requestFocusInWindow();

    }

    public void set_table(int bottom, int top) {
        // System.out.println(a+"-this is rntries---");
        if (a % 50 == 0) {
            no_of_pages = a / 50;

        } else {
            no_of_pages = a / 50 + 1;

        }
        // System.out.println("num of pages"+no_of_pages);
        page_info.setText("Page 1 of" + " " + no_of_pages);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "tr_no", 0, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "Date", 1, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "id", 2, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "name", 3, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "order_num", 4, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "item_name", 5, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "item_type", 6, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "item_rate", 7, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "item_quantity", 8, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_other_advance_book", "total_amount", 9, bottom, top);
        //System.out.println("sheeeeeeeeeeet");
    }

    /**
     * Creates new form GL_other_advance_save
     */
    public GL_other_advance_save() {
        initComponents();
        table.setAutoCreateRowSorter(true);
        supplier_id.setSelectedItem("All");
        
             /// calculating quantity total
        double tot = 0;
        int k = 0;
        while (table.getValueAt(k, 0) != null) {
            tot = tot + Double.parseDouble(table.getValueAt(k, 8).toString());
            k++;
            if(k==49){break;}
        }
        qty_total.setText("" + tot);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        page_info = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        supplier_id = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        qty_total = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No:", "Date", "Supp:ID", "Name", "OrderNO", "Item", "Type", "Rate", "Qty", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(51, 153, 0));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(40);
            table.getColumnModel().getColumn(1).setPreferredWidth(90);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(120);
            table.getColumnModel().getColumn(4).setPreferredWidth(60);
            table.getColumnModel().getColumn(5).setPreferredWidth(80);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);
            table.getColumnModel().getColumn(8).setPreferredWidth(40);
        }

        jButton2.setText("<");
        jButton2.setToolTipText("Previous Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.setToolTipText("Clear Selected Row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(">");
        jButton4.setToolTipText("Next page");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        page_info.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        page_info.setText("Page X of XX");

        jButton5.setText("Supplier Bill Summery");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id.setEditable(true);
        supplier_id.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("gl_advance_types", "type")));
        supplier_id.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplier_idItemStateChanged(evt);
            }
        });
        supplier_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplier_idActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total Quantity");

        qty_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291)
                        .addComponent(page_info)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(qty_total, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))))
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(page_info))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qty_total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );

        jButton1.setText("Save Book");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(62, 62, 62))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (supplier_id.getSelectedItem().toString().equals("All")) {
            if (i > 0) {
                i = i - 50;
                int m = i / 50;

                if (m >= 0) {
                    int k = 0;
                    int j = 0;
                    while (k <= 49) {
                        j = 0;

                        while (j < 10) {

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    set_table(i + 1, i + 50);
                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }
            }
        }

        if (!supplier_id.getSelectedItem().toString().equals("All")) {
            if (i > 0) {

                i = i - 50;
                int m = i / 50;

                if (m >= 0) {
                    int k = 0;
                    int j = 0;
                    while (k <= 49) {
                        j = 0;

                        while (j < 10) {
                            // System.out.println("Minus clearing");

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    // set_table(i + 1, i + 50);
                    filter.Filtered_table(table, "gl_other_advance_book", supplier_id.getSelectedItem().toString(), i + 1, i + 50);

                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        removeSelectedRows(table);
         /// calculating quantity total
            
            int k = 0;
            tot=0;
            while (table.getValueAt(k, 0) != null) {
                tot = tot + Double.parseDouble(table.getValueAt(k, 8).toString());
                k++;
                if(k==49){break;}
            }
            qty_total.setText("" + tot);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (supplier_id.getSelectedItem().equals("All")) {

            if (i < a - 50) {
                i = i + 50;
                int m = i / 50;
                if (m <= no_of_pages - 1) {
                    int k = 0;
                    int j = 0;

                    while (k <= 49) {
                        j = 0;
                        while (j < 10) {

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    set_table(i + 1, i + 50);
                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }

            }
    }//GEN-LAST:event_jButton4ActionPerformed

        if (!supplier_id.getSelectedItem().equals("All")) {
            if (i < a_2 - 50) {
                i = i + 50;
                int m = i / 50;
                if (m <= no_of_pages - 1) {
                    int k = 0;
                    int j = 0;

                    while (k <= 49) {
                        j = 0;
                        while (j < 10) {
                            //  System.out.println("clearing");

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    // set_table(i+1, i+50);
                    filter.Filtered_table(table, "gl_other_advance_book", supplier_id.getSelectedItem().toString(), i + 1, i + 50);

                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }

            }

        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed
    double tot=0;
    private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_idItemStateChanged
        if (supplier_id.getSelectedItem().toString().equals("All")) {
            table_handler.clear_table(table, 10, 50);
            // System.out.println("ok");
            set_table(1, 50);
            int i = 0;
                 /// calculating quantity total
            
            int k = 0;
            tot=0;
            while ((table.getValueAt(k, 0) != null) ) {
                tot = tot + Double.parseDouble(table.getValueAt(k, 8).toString());
                k++;
                if(k==49){break;}
            }
            qty_total.setText("" + tot);
        }
        if (!supplier_id.getSelectedItem().equals("All")) {

            table_handler.clear_table(table, 10, 50);
            a_2 = filter.Filtered_table(table, "gl_other_advance_book", supplier_id.getSelectedItem().toString(), 1, 50);
            if (a % 50 == 0) {
                no_of_pages = a_2 / 50;

            } else {
                no_of_pages = a_2 / 50 + 1;

            }
            page_info.setText("Page 1 of" + " " + no_of_pages);
            int i = 0;
            
            /// calculating quantity total
            
            int k = 0;
            tot=0;
            while (table.getValueAt(k, 0) != null) {
                tot = tot + Double.parseDouble(table.getValueAt(k, 8).toString());
                k++;
                if(k==49){break;}
            }
            qty_total.setText("" + tot);
        }
    }//GEN-LAST:event_supplier_idItemStateChanged

    private void supplier_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplier_idActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int reply = JOptionPane.showConfirmDialog(jButton4,
                "Are you SURE?" + "\n" + "This will close the current book" + "\n" + "make sure you have finalized the book", "Attention!", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {

            try {
                oadvance.enter();
            } catch (SQLException ex) {
                Logger.getLogger(GL_other_advance_save.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                
               //  dbm.insert("CREATE TABLE gl_other_advance_book_backup LIKE gl_other_advance_book");
                 dbm.insert("INSERT INTO gl_other_advance_book_backup SELECT* FROM gl_other_advance_book");
                 dbm.insert("Truncate table gl_other_advance_book");
            } catch (SQLException ex) {
                Logger.getLogger(GL_other_advance_save.class.getName()).log(Level.SEVERE, null, ex);

            }
            int k = 0;
            int j = 0;
            while (k <= 49) {
                j = 0;

                while (j < 10) {

                    table.setValueAt(null, k, j);
                    j++;
                }
                k++;
            }

        } else {
        }
        
         /// calculating quantity total
            
            int k = 0;
            tot=0;
            while (table.getValueAt(k, 0) != null) {
                tot = tot + Double.parseDouble(table.getValueAt(k, 8).toString());
                k++;
                if(k==49){break;}
            }
            qty_total.setText("" + tot);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void removeSelectedRows(JTable table) {
        int m = i / 50;
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        //  deleted += rows.length;
        for (int n = 0; n < rows.length; n++) {

            dbm.CheckNDeleteFromDataBase("gl_other_advance_book", "tr_no", table.getValueAt(rows[n] - n, 0));
            set_table(i + 1, i + 50);

            //  System.out.println(table.getValueAt(rows[i] - i, 0));
            // model.removeRow(rows[i] - i);
        }
        int k = 0;
        int j = 0;
        while (k <= 49) {
            j = 0;

            while (j < 10) {

                table.setValueAt(null, k, j);
                j++;
            }
            k++;
            set_table(i + 1, i + 50);

            page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);

            // set_table();
            a = dbm.Checking_Length_Of_The_Table("gl_other_advance_book", "tr_no");
        }
       // return deleted;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel page_info;
    private javax.swing.JLabel qty_total;
    private javax.swing.JComboBox supplier_id;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
