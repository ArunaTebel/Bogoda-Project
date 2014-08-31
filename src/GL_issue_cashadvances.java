
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GL_issue_cashadvances extends javax.swing.JPanel {

    int i = 0;
    int no_of_pages;
    double total;
    double page_total;
    int deleted;

    DatabaseManager dbm = DatabaseManager.getDbCon();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
    Cash_Advance_Common_Class cadvance = new Cash_Advance_Common_Class();
    int a = dbm.Checking_Length_Of_The_Table("gl_cash_advance_book", "entry_no");
    Interface_Events interface_events = new Interface_Events();

    public GL_issue_cashadvances() {
        initComponents();

        {           // Total_calculation
            int j = 0;
            int length = dbm.getStringArray("gl_cash_advance_book", "amount").length;

            while (j < length - 1) {
                total = total + Double.parseDouble(dbm.getStringArray("gl_cash_advance_book", "amount")[j + 1]);

                if (j < 50) {
                    pageTotal.setText("" + total);
                }
                j++;
            }

        }

        Grandtotal.setText("" + total);
    }

    public void focus() {
        this.requestFocusInWindow();

    }

    public double Gtotal() {
      total =0;
        int j = 0;
        int length = dbm.getStringArray("gl_cash_advance_book", "amount").length;

        while (j < length - 1) {
            total = total + Integer.parseInt(dbm.getStringArray("gl_cash_advance_book", "amount")[j + 1]);

            j++;
        }
        return total;

    }

    public void set_table(int bottom, int top) {
        //System.out.println("in");
        if (a % 50 == 0) {
            no_of_pages = a / 50;

        } else {
            no_of_pages = a / 50 + 1;

        }
        page_info.setText("Page 1 of" + " " + no_of_pages);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "entry_no", 0, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "issue_date", 1, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_id", 2, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_name", 3, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "max_allowable", 4, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "amount", 5, bottom, top);
        dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "special_permission", 6, bottom, top);

    }
    
    public void print(){
    
     HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
          

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
String adloc = dbm.checknReturnStringData("file_locations", "description", "CashAdvance", "location");
           String dateNow= generate.create_custom("Cash_Advance_Book", adloc, param, location, "Cash_advance_book.jrxml");
    
    
    }

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
        jLabel4 = new javax.swing.JLabel();
        Del = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Grandtotal = new javax.swing.JTextField();
        pageTotal = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No:", "Date", "Supp:ID", "Name", "Max:Allow", "Amount", "SP"
            }
        ));
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(51, 204, 0));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(1).setPreferredWidth(30);
            table.getColumnModel().getColumn(2).setPreferredWidth(15);
            table.getColumnModel().getColumn(3).setPreferredWidth(75);
            table.getColumnModel().getColumn(4).setPreferredWidth(30);
            table.getColumnModel().getColumn(5).setPreferredWidth(30);
            table.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        jButton2.setText("<");
        jButton2.setToolTipText("Previous Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear Selected");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(page_info)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(page_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel4.setText("Deleted ");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));

        jLabel3.setText("Total ");

        jLabel2.setText("Page Total");

        Grandtotal.setBackground(new java.awt.Color(204, 255, 204));

        pageTotal.setBackground(new java.awt.Color(204, 255, 204));
        pageTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pageTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(Grandtotal))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pageTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton5.setText("Supplier Bill Summery");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));

        jButton6.setText("Save Book");
        jButton6.setToolTipText("Close The book for the month and save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("Open Saved Books");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Del, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Del, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        Del.setText("" + removeSelectedRows(table));
        Grandtotal.setText(""+Gtotal());
         int l = 0;
            double tot = 0;
            while (l <= 49) {
                if (table.getValueAt(l, 5) != null) {
                    tot = tot + Double.parseDouble(table.getValueAt(l, 5).toString());
                   // System.out.println(tot);
                }

                l++;
            }
            pageTotal.setText("" + tot);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        HashMap param = new HashMap();
//            param.put("USER", user.get_current_user());
//          
//
//            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
//String adloc = dbm.checknReturnStringData("file_locations", "description", "CashAdvance", "location");
//           String dateNow= generate.create("Cash_Advance_Book", adloc, param, location, "Cash_advance_book.jrxml");
        
        print();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (i < a - 50) {
            i = i + 50;
            int m = i / 50;
            if (m <= no_of_pages - 1) {
                int k = 0;
                int j = 0;

                while (k <= 49) {
                    j = 0;
                    while (j < 7) {

                        table.setValueAt(null, k, j);
                        j++;
                    }
                    k++;
                }

                set_table(i + 1, i + 50);
                page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
            }

            int l = 0;
            double tot = 0;
            while (l <= 49) {
                if (table.getValueAt(l, 5) != null) {
                    tot = tot + Double.parseDouble(table.getValueAt(l, 5).toString());
                    //System.out.println(tot);
                }

                l++;
            }
            pageTotal.setText("" + tot);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (i > 0) {
            i = i - 50;
            int m = i / 50;

            if (m >= 0) {
                int k = 0;
                int j = 0;
                while (k <= 49) {
                    j = 0;

                    while (j < 7) {

                        table.setValueAt(null, k, j);
                        j++;
                    }
                    k++;
                }

                set_table(i + 1, i + 50);
                page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
            }

            int l = 0;
            double tot = 0;
            while (l <= 49) {
                if (table.getValueAt(l, 5) != null) {
                    tot = tot + Double.parseDouble(table.getValueAt(l, 5).toString());
                   // System.out.println(tot);
                }

                l++;
            }
            pageTotal.setText("" + tot);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       
        int reply = JOptionPane.showConfirmDialog(jButton4,
                        "Are you Sure?"+"\n"+"This will reset the book"+"\n"+"make sure you have printed the book", "Attention!", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION){
            print();
            cadvance.tranfer();
        cadvance.truncate();
         int k = 0;
        int j = 0;
        while (k <= 49) {
            j = 0;

            while (j < 7) {

                table.setValueAt(null, k, j);
                j++;
            }
            k++;
        }
        }
        else{}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void pageTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageTotalActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         New_windows_theme NW = new New_windows_theme();
       // NW.setVisible(true);
       File_chooser home = new File_chooser();
       

        NW.paint(home);
        NW.setLocation(150, 150);
        NW.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
         GL_Billsummery bill = new GL_Billsummery("SupID");
         bill.setVisible(true);
       
    }//GEN-LAST:event_jButton5ActionPerformed
    public int removeSelectedRows(JTable table) {
        int m = i / 50;
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        deleted += rows.length;
        for (int n = 0; n < rows.length; n++) {
            
            
            cadvance.set_month_tr(Integer.parseInt(table.getValueAt(rows[n] - n,0).toString()));
            
            cadvance.set_amount(Double.parseDouble(table.getValueAt(rows[n] - n,5).toString()));
       
            cadvance.set_sup_id(Integer.parseInt(table.getValueAt(rows[n] - n,2).toString()));
            
            cadvance.set_sup_name(table.getValueAt(rows[n] - n,5).toString());
            
            cadvance.AddToDeleteDataBase();
            dbm.CheckNDeleteFromDataBase("gl_cash_advance_book", "entry_no", table.getValueAt(rows[n] - n, 0));
            set_table(i + 1, i + 50);

          //  System.out.println(table.getValueAt(rows[i] - i, 0));
            // model.removeRow(rows[i] - i);
        }
        int k = 0;
        int j = 0;
        while (k <= 49) {
            j = 0;

            while (j < 7) {

                table.setValueAt(null, k, j);
                j++;
            }
            k++;
            set_table(i + 1, i + 50);

            page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);

            // set_table();
            a = dbm.Checking_Length_Of_The_Table("gl_cash_advance_book", "entry_no");
        }
        return deleted;
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Del;
    private javax.swing.JTextField Grandtotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pageTotal;
    private javax.swing.JLabel page_info;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
