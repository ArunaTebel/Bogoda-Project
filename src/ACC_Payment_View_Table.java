
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Iddamalgoda
 */
public class ACC_Payment_View_Table extends javax.swing.JFrame {

    /**
     * Creates new form ACC_Payment_View
     */
    public ACC_Payment_View_Table() {
        initComponents();
    }
    public void Table_Fill_All(){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Fill_table_without_filtering(table,1,50);
    }
    
    ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
    public void Table_Fill_Credit_Search(String search_column,Object search_element){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_Credit_Search(table,search_column,search_element,1,50);
    }
    public void Table_Fill_Debit_Search(String search_column,Object search_element){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_Debit_Search(table,search_column,search_element,1,50);
    }
    public void Table_Fill_Date_Search(String search_column,Object search_element1,Object search_element2){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_For_Date_Credit_Search(table,search_column,search_element1,search_element2,1,50);
    }
    
    public void Table_Fill_Credit_Credit_Search(String search_column1,Object search_element1,String search_column2,Object search_element2){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_Credit_Credit_Search(table, search_column1, search_element1,1,50, search_column2, search_element2);
    }
    
    public void Table_Fill_Debit_Debit_Search(String search_column1,Object search_element1,String search_column2,Object search_element2){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         System.out.println("colombo");
         db.Filtered_table_For_Payments_Debit_Debit_Search(table, search_column1, search_element1,1,50, search_column2, search_element2);
    }
    
    public void Table_Fill_Credit_Debit_Search(String search_column1,Object search_element1,String search_column2,Object search_element2){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_Credit_Debit_Search(table, search_column1, search_element1,1,50, search_column2, search_element2);
    }
    
    public void Table_Fill_Date_Credit_Search(String search_column1,Object search_element1,Object search_element2,String search_column2,Object search_element3){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_For_Date_Credit_Two_Search(table, search_column1, search_element1,search_element2,1,50, search_column2, search_element3);
    }
    
    public void Table_Fill_Date_Debit_Search(String search_column1,Object search_element1,Object search_element2,String search_column2,Object search_element3){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_For_Date_Debit_Two_Search(table, search_column1, search_element1,search_element2,1,50, search_column2, search_element3);
    }
    
   ////////
    public void Table_Fill_Alln(int a, int b){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Fill_table_without_filtering(table,a,b);
    }
    
   
    public void Table_Fill_Credit_Searchn(String search_column,Object search_element,int a, int b){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_Credit_Search(table,search_column,search_element,a,b);
    }
    public void Table_Fill_Debit_Searchn(String search_column,Object search_element,int a, int b){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_Debit_Search(table,search_column,search_element,a,b);
    }
    public void Table_Fill_Date_Searchn(String search_column,Object search_element1,Object search_element2,int a, int b){
        ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
        db.Filtered_table_For_Payments_For_Date_Credit_Search(table,search_column,search_element1,search_element2,a,b);
    }
    
    public void Table_Fill_Credit_Credit_Searchn(String search_column1,Object search_element1,String search_column2,Object search_element2,int a, int b){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_Credit_Credit_Search(table, search_column1, search_element1,a,b, search_column2, search_element2);
    }
    
    public void Table_Fill_Debit_Debit_Searchn(String search_column1,Object search_element1,String search_column2,Object search_element2,int a, int b){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         System.out.println("colombo");
         db.Filtered_table_For_Payments_Debit_Debit_Search(table, search_column1, search_element1,a,b, search_column2, search_element2);
    }
    
    public void Table_Fill_Credit_Debit_Searchn(String search_column1,Object search_element1,String search_column2,Object search_element2,int a, int b){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_Credit_Debit_Search(table, search_column1, search_element1,a,b, search_column2, search_element2);
    }
    
    public void Table_Fill_Date_Credit_Searchn(String search_column1,Object search_element1,Object search_element2,String search_column2,Object search_element3,int a, int b){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_For_Date_Credit_Two_Search(table, search_column1, search_element1,search_element2,a,b, search_column2, search_element3);
    }
    
    public void Table_Fill_Date_Debit_Searchn(String search_column1,Object search_element1,Object search_element2,String search_column2,Object search_element3,int a, int b){
         ACC_View_Database_Handling_Payment db = new ACC_View_Database_Handling_Payment();
         db.Filtered_table_For_Payments_For_Date_Debit_Two_Search(table, search_column1, search_element1,search_element2,a,b, search_column2, search_element3);
    }
    
    
    //////////////////
    
    
    
   
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        page_info = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tr No.", "Ref No.", "Date", "Pay Type", "Credit ID ", "Credit Account Name", "Credit Description", "Credit Amount", "Debit ID", "Debit Account Name", "Debit Description", "Debit Amount"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);
        table.setRowMargin(2);
        table.setSelectionBackground(new java.awt.Color(51, 204, 0));
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(50);
            table.getColumnModel().getColumn(2).setPreferredWidth(60);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setPreferredWidth(80);
            table.getColumnModel().getColumn(7).setPreferredWidth(55);
            table.getColumnModel().getColumn(8).setPreferredWidth(57);
            table.getColumnModel().getColumn(11).setPreferredWidth(50);
        }

        jButton3.setText("<");
        jButton3.setToolTipText("Previous Page");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.setToolTipText("Clear Selected Row");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText(">");
        jButton5.setToolTipText("Next page");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        page_info.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        page_info.setText("Page X of XX");

        jButton6.setText("Edit This Transaction");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton4))
                                .addGap(337, 337, 337))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jButton6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(page_info)
                        .addGap(456, 456, 456))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(page_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       /* if(i>0){ i=i-50;
            int m = i/50;

            if(m>=0){
                int k = 0;
                int j = 0;
                while (k<=49) {
                    j = 0;

                    while (j < 6) {

                        table.setValueAt(null, k, j);
                        j++;
                    }
                    k++;
                }

                set_table(i+1, i+50);
                page_info.setText("Page "  + (m+1) +" of"+" "+no_of_pages);
            }*/
        

    }//GEN-LAST:event_jButton3ActionPerformed

    public void Clear_Table(){
        int i,j;
        i=0;
        j=0;
        
        for(i=0;i<12;i++){
            for(j=0;j<50;j++){
                table.setValueAt(null, j, i);
            }
        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Clear_Table();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      /*  if(i<a-50){     i=i+50;
            int m = i/50;
            if(m<=no_of_pages-1){
                int k = 0;
                int j = 0;

                while (k<=49) {
                    j = 0;
                    while (j < 6) {

                        table.setValueAt(null, k, j);
                        j++;
                    }
                    k++;
                }

                set_table(i+1, i+50);
                page_info.setText("Page "  + (m+1) +" of"+" "+no_of_pages);
            }

        } */
    }//GEN-LAST:event_jButton5ActionPerformed

    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        ACC_Edit_Payments edit = new ACC_Edit_Payments();
        edit.setVisible(true);
        int tr_no,check,row;
        tr_no=0;
        check=0;
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        row=rows[0];
        while(check==0){
            if(table.getValueAt(row,0)==null){
                row--;
            }
            else{
                tr_no=Integer.parseInt(table.getValueAt(row,0).toString());
                check=1;
            }
        }
        
        edit.Set_Tr_No(tr_no);
       
        edit.Fill_Edit_Form(tr_no);
           
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(ACC_Payment_View_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACC_Payment_View_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACC_Payment_View_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACC_Payment_View_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                ACC_Payment_View_Table jf = new ACC_Payment_View_Table();
                jf.setVisible(true);
                jf.setExtendedState(MainWindow.MAXIMIZED_BOTH);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel page_info;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
