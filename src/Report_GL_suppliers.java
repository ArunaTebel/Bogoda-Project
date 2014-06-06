

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class Report_GL_suppliers extends javax.swing.JPanel {
UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    
    
     public  class Background implements Runnable{
        @Override
        public void run(){
           //  jProgressBar1.setIndeterminate(true);
            view.setEnabled(false);
            jProgressBar1.setVisible(true);
            int a = (int) (Math.random()*500);
            //System.out.println(a);
            for(int i=0;i<=3000+a;i++){
                jProgressBar1.setValue(100*i/4000);
                jProgressBar1.repaint();
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }     
    }
     
     public  class report implements Runnable{
        @Override
        public void run(){
           //  jProgressBar1.setIndeterminate(true);
           
                Thread a = new Thread(new Background());
               
                       if ( route.isSelected()) {
                           
                           
                           if( Cat_code.getSelectedItem()==null || Cat_code.getSelectedItem().toString()==""){ 
                               JOptionPane.showMessageDialog(datechooser, "Select Category");
                           
                           }
                           
                           else {
                          
                           HashMap param = new HashMap();
                    param.put("USER",new UserAccountControl().get_current_user());
                    param.put("cat_id", Cat_code.getSelectedItem().toString());
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("GL_SUPP", "D:\\", param, location, "SupplierAll_cat.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
        }}
                        if(welfare.isSelected()){
                            HashMap param = new HashMap();
                    param.put("USER",new UserAccountControl().get_current_user());
                    param.put("welfare", "YES") ;
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("GL_SUPP", "D:\\", param, location, "SupplierAll_welfare.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
        }  if(active.isSelected()){
            HashMap param = new HashMap();
                 //   param.put("route", Cat_code.getSelectedItem().toString());
                    param.put("USER",new UserAccountControl().get_current_user());
                    param.put("active", "YES");
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("GL_SUPP", "D:\\", param, location, "SupplierAll_Active.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
        }
         if(!active.isSelected()&& !welfare.isSelected()&& !route.isSelected()){
               
            HashMap param = new HashMap();
                    //param.put("route", Cat_code.getSelectedItem().toString());
                    param.put("USER",new UserAccountControl().get_current_user());
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("GL_SUPP", "D:\\", param, location, "SupplierAll.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
        }
         
               
            view.setEnabled(true);
            
        }     
     }
    /**
     *
     */
    public Report_GL_suppliers() {
          defaults.put("nimbusOrange", defaults.get("nimbusBase"));
        UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(51, 153, 0)));
      
        initComponents();
        //Cat_code.setEnabled(false);
       // supplier_id.setEnabled(false);
           jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    DatabaseManager dbm = new DatabaseManager();

    public void focus() {
  //      dayfield.requestFocus();
    //    dayfield.selectAll();
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
        jLabel3 = new javax.swing.JLabel();
        route = new javax.swing.JCheckBox();
        Cat_code = new javax.swing.JComboBox();
        active = new javax.swing.JCheckBox();
        welfare = new javax.swing.JCheckBox();
        view = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Group by");

        route.setText("Route");
        route.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                routeItemStateChanged(evt);
            }
        });

        Cat_code.setEditable(true);
        Cat_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("category", "category_id")));
        Cat_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cat_codeItemStateChanged(evt);
            }
        });

        active.setText("Active");
        active.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                activeItemStateChanged(evt);
            }
        });
        active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeActionPerformed(evt);
            }
        });

        welfare.setText("Welfare");
        welfare.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                welfareItemStateChanged(evt);
            }
        });
        welfare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welfareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welfare)
                    .addComponent(active)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(route)
                        .addGap(26, 26, 26)
                        .addComponent(Cat_code, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(route)
                    .addComponent(Cat_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(active)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welfare)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        view.setText("Veiw");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Suppliers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        //Thread a  = new Thread(new Background());
        Thread b = new Thread(new report());
        
       // a.start();
        b.start();
       // a.stop();
        
      
    }//GEN-LAST:event_viewActionPerformed

    private void Cat_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cat_codeItemStateChanged
       // if(Cat_code.getSelectedItem()!=null){
        //   cat_name.setText(dbm.checknReturnData("category", "category_id", Cat_code.getSelectedItem(), "category_name"));

           // trans_rate.requestFocusInWindow();
        //  }
    }//GEN-LAST:event_Cat_codeItemStateChanged

    private void routeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_routeItemStateChanged
        if (route.isEnabled()) {
            welfare.setSelected(false);
            active.setSelected(false);
            //route.setSelected(true);
        }
    }//GEN-LAST:event_routeItemStateChanged

    private void activeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_activeItemStateChanged
           if (active.isEnabled()) {
            welfare.setSelected(false);
           // active.setSelected(true);
            route.setSelected(false);
        }
    }//GEN-LAST:event_activeItemStateChanged

    private void activeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activeActionPerformed

    private void welfareItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_welfareItemStateChanged
           if (welfare.isEnabled()) {
           // welfare.setSelected(true);
            active.setSelected(false);
            route.setSelected(false);
        }
    }//GEN-LAST:event_welfareItemStateChanged

    private void welfareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welfareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_welfareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Cat_code;
    private javax.swing.JCheckBox active;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JCheckBox route;
    private javax.swing.JButton view;
    private javax.swing.JCheckBox welfare;
    // End of variables declaration//GEN-END:variables
}
