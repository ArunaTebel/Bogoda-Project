
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_change_margin_dates extends javax.swing.JFrame {

    /**
     * Creates new form PRCR_change_margin_dates
     */
    private String month;
    private String division;
    private short margin;
    private short workingdays;
    short row;
    DatabaseManager dbm=DatabaseManager.getDbCon();
    public PRCR_change_margin_dates() {
        initComponents();
        
        
       
    }
    
    public void setInputParameters(String month){
    this.month=month;
    
       initialFill(month);
    }
    public void initialFill(String month){
       try {
            ResultSet query=dbm.query("SELECT * FROM prcr_margin_dates WHERE month LIKE '"+month+"'");
            row=0;
            while(query.next()){
              division=query.getString("division");
              margin=query.getShort("margin");
              workingdays=query.getShort("total");
              
              table.setValueAt(division, row, 0);
              table.setValueAt(margin, row, 2);
              table.setValueAt(workingdays, row, 1);
              row++;
              
            }
           
            
            if(row==0){
               query=dbm.query("SELECT * FROM division_details");
                while(query.next()){
                division=query.getString("code");
                table.setValueAt(division, row, 0);
                row++;
                }
               
            }
             query.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public PRCR_change_margin_dates(String s,String d) {
        initComponents();
        this.month=s;
        this.division=d;
      
        
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Change margin dates to");

        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Division", "Working Days", "Margin Days"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Short.class, java.lang.Short.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton2.setText("Start process");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        row=0;
        
        while(table.getValueAt(row,0)!=null){
            workingdays= Short.valueOf((table.getValueAt(row,1).toString()));
         if (checkDataAvailability("prcr_margin_dates", "month", month, "division", table.getValueAt(row,0).toString(), "margin")) {
                    updateDatabase("prcr_margin_dates", "month", month, "division",  table.getValueAt(row,0).toString(), "total", workingdays);
                    updateDatabase("prcr_margin_dates", "month", month, "division",  table.getValueAt(row,0).toString(), "margin", Math.floor(workingdays * 0.75));
                } else {
             try {
                 dbm.insert("INSERT INTO prcr_margin_dates(month,division,margin,total) VALUES('" + month + "','" +table.getValueAt(row,0).toString()+ "','" + Math.floor(workingdays * 0.75) + "','" + workingdays + "')");
             } catch (SQLException ex) {
                 Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(Level.SEVERE, null, ex);
             }
                }
         
         row++;
        
        }
         initialFill(month);
         JOptionPane.showMessageDialog(null, "Saved\n", "Message", JOptionPane.INFORMATION_MESSAGE);
         
        // System.out.println("month,row-"+month+row);
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   
        JOptionPane.showMessageDialog(null, "Update Number of Working dates for this month and click ok\n", "Message", JOptionPane.INFORMATION_MESSAGE);
            
        Task_manager.b = new Thread(new PRCR_Checkroll_Monthly_workdata_database_update_class(month));//month=201x_xx
        Task_manager.b.setPriority(10);
        Task_manager.b.start();        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

       public boolean checkDataAvailability(String table_name, String table_column_giving1, String row_element1, String table_column_giving2, String row_element2, String table_column_need) {
        //DatabaseManager dbm = DatabaseManager.getDbCon();
        
        try {
             ResultSet query = dbm.query("SELECT * FROM " + table_name + " where " + table_column_giving1 + " LIKE '" + row_element1 + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'");
            while (query.next()) {
               // s = query.getString(table_column_need);
                return true;
            }
            query.close();
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

    public boolean updateDatabase(String table_name, String table_column_giving, Object row_element, String table_column_giving2, Object row_element2, String table_column_need, Object update_element) {
       // DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            dbm.insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' WHERE " + table_column_giving + "='" + row_element + "' AND " + table_column_giving2 + "='" + row_element2 + "'");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }

    public boolean fillDatabase(String table_name, String table_column_giving1, Object row_element1,String table_column_giving2, Object row_element2, String table_column_need, Object update_element) {
        
//    if (checkDataAvailability("prcr_margin_dates", "month", st, "division", division, "margin")) {
//                    updateDatabase("prcr_margin_dates", "month", st, "division", division, "total", workingdays);
//                    updateDatabase("prcr_margin_dates", "month", st, "division", division, "margin", Math.floor(workingdays * 0.75));
//                } else {
//                    dbm.insert("INSERT INTO prcr_margin_dates(month,division,margin,total) VALUES('" + st + "','" + division + "','" + Math.floor(workingdays * 0.75) + "','" + workingdays + "')");
//                }
//
//    
//    
        
    DatabaseManager dbm = DatabaseManager.getDbCon();
        try {

            dbm.insert("UPDATE " + table_name + " SET " + table_column_need + " ='" + update_element + "' where " + table_column_giving1 + " LIKE '" + row_element1 + "' AND " + table_column_giving2 + " LIKE '" + row_element2 + "'");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL ERROR", "error");
            return false;
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PRCR_change_margin_dates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PRCR_change_margin_dates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
