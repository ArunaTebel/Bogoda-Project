
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
public class Report_PRCR_Employees extends javax.swing.JPanel {

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();

    public class Background implements Runnable {

        @Override
        public void run() {
            //  jProgressBar1.setIndeterminate(true);
            view.setEnabled(false);
            jProgressBar1.setVisible(true);
            int a = (int) (Math.random() * 500);
            //System.out.println(a);
            for (int i = 0; i <= 3000 + a; i++) {
                jProgressBar1.setValue(100 * i / 4000);
                jProgressBar1.repaint();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public class report implements Runnable {

        @Override
        public void run() {
           //  jProgressBar1.setIndeterminate(true);

            Thread a = new Thread(new Background());

            if (division.isSelected() && register.isSelected()) {
                HashMap param = new HashMap();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("DIVISION", division_jc.getSelectedItem().toString());
                    param.put("col", "division");
                    String register="0";
                    if(register_or_casual_combo.getSelectedItem().toString().equals("Register")){register="1";}
                    param.put("b", register);
                param.put("a", "register_or_casual");
                    
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("PRCR_Checkroll_EMP_Details_", "D:\\", param, location, "PRCR_Employee_Details_All.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
            } else if (division.isSelected()) {

                if (division_jc.getSelectedItem() == null || division_jc.getSelectedItem().toString() == "") {
                    JOptionPane.showMessageDialog(datechooser, "Select Category");

                } else {

                    HashMap param = new HashMap();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("DIVISION", division_jc.getSelectedItem().toString());
                    param.put("col", "division");
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("PRCR_Checkroll_EMP_Details_", "D:\\", param, location, "PRCR_Employee_Details.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
                }
            } //                        if(welfare.isSelected()){
            //                            HashMap param = new HashMap();
            //                    param.put("USER",new UserAccountControl().get_current_user());
            //                    param.put("welfare", "YES") ;
            //                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
            //                    a.start();
            //                    generate.create("GL_SUPP", "D:\\", param, location, "SupplierAll_welfare.jrxml");
            //                    a.stop();
            //                    ;
            //                    jProgressBar1.setValue(100);
            //        }  
            else if (register.isSelected()) {
                HashMap param = new HashMap();
                //   param.put("route", Cat_code.getSelectedItem().toString());
                param.put("USER", new UserAccountControl().get_current_user());
                String register="0";
               if(register_or_casual_combo.getSelectedItem().toString().equals("Register")){register="1";}
                    
                param.put("DIVISION", register);
                param.put("col", "register_or_casual");
                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                a.start();
                generate.create("PRCR_Checkroll_EMP_Details_", "D:\\", param, location, "PRCR_Employee_Details.jrxml");
                a.stop();
                ;
                jProgressBar1.setValue(100);
            }
            else if (!register.isSelected()  && !division.isSelected()) {

//                HashMap param = new HashMap();
//                //param.put("route", Cat_code.getSelectedItem().toString());
//                param.put("USER", new UserAccountControl().get_current_user());
//                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
//                a.start();
//                generate.create("PRCR_Checkroll_EMP_Details", "D:\\", param, location, "PRCR_Employee_Details.jrxml");
//                a.stop();
//                ;
//                jProgressBar1.setValue(100);
            }

            view.setEnabled(true);

        }
    }

    /**
     *
     */
    public Report_PRCR_Employees() {
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
        division = new javax.swing.JCheckBox();
        register = new javax.swing.JCheckBox();
        division_jc = new javax.swing.JComboBox();
        register_or_casual_combo = new javax.swing.JComboBox();
        view = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Group by");

        division.setText("Division");
        division.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                divisionItemStateChanged(evt);
            }
        });
        division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionActionPerformed(evt);
            }
        });

        register.setText("Register");
        register.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                registerItemStateChanged(evt);
            }
        });
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        division_jc.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        division_jc.setEditable(true);
        division_jc.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("division_details", "code")));

        register_or_casual_combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Register", "Casual" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(register)
                                .addGap(38, 38, 38)
                                .addComponent(register_or_casual_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(division)
                                .addGap(42, 42, 42)
                                .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(division)
                    .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(register)
                    .addComponent(register_or_casual_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        view.setText("Veiw");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Checkroll Employees");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        //Thread a  = new Thread(new Background());
        Thread b = new Thread(new report());

        // a.start();
        b.start();
       // a.stop();


    }//GEN-LAST:event_viewActionPerformed

    private void divisionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_divisionItemStateChanged
       
    }//GEN-LAST:event_divisionItemStateChanged

    private void registerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_registerItemStateChanged
        
    }//GEN-LAST:event_registerItemStateChanged

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerActionPerformed

    private void divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_divisionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox division;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JCheckBox register;
    private javax.swing.JComboBox register_or_casual_combo;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
