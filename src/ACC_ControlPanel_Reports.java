
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author siddamalgoda
 */
public class ACC_ControlPanel_Reports extends javax.swing.JPanel {

    /**
     * Creates new form ACC_ControlPanel_Reports
     */
    public ACC_ControlPanel_Reports() {
        
        initComponents();
        start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        black1 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        color1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        black = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        color = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        black2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        color2 = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        black3 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        color3 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        black4 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        color4 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        oldMode = new javax.swing.JRadioButton();
        newMode = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 51), 1, true));

        black1.setBackground(new java.awt.Color(153, 153, 153));
        black1.setText("Black");
        black1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                black1ItemStateChanged(evt);
            }
        });

        jLabel4.setText("Payments");

        color1.setBackground(new java.awt.Color(0, 204, 0));
        color1.setText("Color");
        color1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                color1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(color1)
                .addGap(35, 35, 35)
                .addComponent(black1)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color1)
                    .addComponent(black1)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 51), 1, true));

        black.setBackground(new java.awt.Color(153, 153, 153));
        black.setText("Black");
        black.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                blackItemStateChanged(evt);
            }
        });

        jLabel3.setText("Receipts");

        color.setBackground(new java.awt.Color(0, 204, 0));
        color.setText("Color");
        color.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                colorItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(color)
                .addGap(34, 34, 34)
                .addComponent(black)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color)
                    .addComponent(black)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 51), 1, true));

        black2.setBackground(new java.awt.Color(153, 153, 153));
        black2.setText("Black");
        black2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                black2ItemStateChanged(evt);
            }
        });

        jLabel5.setText("Journals");

        color2.setBackground(new java.awt.Color(0, 204, 0));
        color2.setText("Color");
        color2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                color2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(color2)
                .addGap(36, 36, 36)
                .addComponent(black2)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color2)
                    .addComponent(black2)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        jButton2.setBackground(new java.awt.Color(166, 122, 240));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jButton2)
                .addGap(121, 121, 121))
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 51), 1, true));

        black3.setBackground(new java.awt.Color(153, 153, 153));
        black3.setText("Black");
        black3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                black3ItemStateChanged(evt);
            }
        });

        jLabel6.setText("Ledger Accounts");

        color3.setBackground(new java.awt.Color(0, 204, 0));
        color3.setText("Color");
        color3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                color3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(color3)
                .addGap(36, 36, 36)
                .addComponent(black3)
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color3)
                    .addComponent(black3)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 51), 1, true));

        black4.setBackground(new java.awt.Color(153, 153, 153));
        black4.setText("Black");
        black4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                black4ItemStateChanged(evt);
            }
        });

        jLabel7.setText("Trail Balance");

        color4.setBackground(new java.awt.Color(0, 204, 0));
        color4.setText("Color");
        color4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                color4ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(color4)
                .addGap(36, 36, 36)
                .addComponent(black4)
                .addGap(14, 14, 14))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(color4)
                    .addComponent(black4)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        oldMode.setText("Old");
        oldMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                oldModeItemStateChanged(evt);
            }
        });

        newMode.setText("New");
        newMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                newModeItemStateChanged(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        jButton1.setBackground(new java.awt.Color(166, 122, 240));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButton1)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(newMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(oldMode)
                .addGap(52, 52, 52)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newMode)
                    .addComponent(oldMode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        jLabel1.setText("Report Mode");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 51)));

        jLabel2.setText("Report Font Color");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel2)
                .addGap(141, 141, 141))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Report Handling");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(211, 211, 211))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    public void start(){
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_new", "value"))==0){
            newMode.setSelected(false);
            oldMode.setSelected(true);
        }
        else{
            newMode.setSelected(true);
            oldMode.setSelected(false);
        }
        
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_receipt_color", "value"))==0){
            color.setSelected(false);
            black.setSelected(true);
        }
        else{
            color.setSelected(true);
            black.setSelected(false);
        }
        
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_payment_color", "value"))==0){
            color1.setSelected(false);
            black1.setSelected(true);
        }
        else{
            color1.setSelected(true);
            black1.setSelected(false);
        }
        
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_journal_color", "value"))==0){
            color2.setSelected(false);
            black2.setSelected(true);
        }
        else{
            color2.setSelected(true);
            black2.setSelected(false);
        }
        
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_ledger_color", "value"))==0){
            color3.setSelected(false);
            black3.setSelected(true);
        }
        else{
            color3.setSelected(true);
            black3.setSelected(false);
        }
        
        if(Integer.parseInt(dbm.checknReturnData("account_control_panel_details","control", "report_trailbalance_color", "value"))==0){
            color4.setSelected(false);
            black4.setSelected(true);
        }
        else{
            color4.setSelected(true);
            black4.setSelected(false);
        }
    }
    private void colorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_colorItemStateChanged

        if(color.isSelected()){
            black.setSelected(false);
        }
    }//GEN-LAST:event_colorItemStateChanged

    private void blackItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_blackItemStateChanged

            if(black.isSelected()){
                color.setSelected(false);
            }
  
    }//GEN-LAST:event_blackItemStateChanged

    private void black1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_black1ItemStateChanged
        if(black1.isSelected()){
                color1.setSelected(false);
            }
    }//GEN-LAST:event_black1ItemStateChanged

    private void color1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_color1ItemStateChanged
        if(color1.isSelected()){
            black1.setSelected(false);
        }
    }//GEN-LAST:event_color1ItemStateChanged

    private void black2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_black2ItemStateChanged
        if(black2.isSelected()){
                color2.setSelected(false);
            }
    }//GEN-LAST:event_black2ItemStateChanged

    private void color2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_color2ItemStateChanged
       if(color2.isSelected()){
            black2.setSelected(false);
        }
    }//GEN-LAST:event_color2ItemStateChanged
    DatabaseManager dbm = DatabaseManager.getDbCon();
    MessageBox msg = new MessageBox();
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int newOld = 0;
        if(newMode.isSelected()){
            newOld=1;
        }
        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_new") == 0) {
            try {
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_new" + "','" + newOld + "')");
            } catch (SQLException ex) {
                Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_new" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_new" + "','" + newOld + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        msg.showMessage("Data has been Saved", "Saved", "info");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int colorReceipt = 0;
        int colorPayment =0;
        int colorJournal =0;
        int colorLedger =0;
        int colorTrailBalance =0;
        if(color.isSelected()){
            colorReceipt=1;
        }
        if(color1.isSelected()){
            colorPayment=1;
        }
        if(color2.isSelected()){
            colorJournal=1;
        }
        if(color3.isSelected()){
            colorLedger=1;
        }
        if(color4.isSelected()){
            colorTrailBalance=1;
        }
        
        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_receipt_color") == 0) {
                try {
                    dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_receipt_color" + "','" + colorReceipt + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_receipt_color" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_receipt_color" + "','" + colorReceipt + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_payment_color") == 0) {
                try {
                    dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_payment_color" + "','" + colorPayment + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_payment_color" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_payment_color" + "','" + colorPayment + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_journal_color") == 0) {
                try {
                    dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_journal_color" + "','" + colorJournal + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_journal_color" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_journal_color" + "','" + colorJournal + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_ledger_color") == 0) {
                try {
                    dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_ledger_color" + "','" + colorLedger + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_ledger_color" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_ledger_color" + "','" + colorLedger + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
          if (dbm.checkWhetherDataExists("account_control_panel_details", "control", "report_trailbalance_color") == 0) {
                try {
                    dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_trailbalance_color" + "','" + colorTrailBalance + "')");
                } catch (SQLException ex) {
                    Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        else{
            try {
                dbm.insert("DELETE FROM account_control_panel_details WHERE control LIKE '" + "report_trailbalance_color" + "' ");
                dbm.insert("INSERT INTO account_control_panel_details(control,value) VALUES('" + "report_trailbalance_color" + "','" + colorTrailBalance + "')");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_ControlPanel_Reports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        msg.showMessage("Data has been Saved", "Saved", "info");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void newModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_newModeItemStateChanged
        if(newMode.isSelected()){
            oldMode.setSelected(false);
        }
    }//GEN-LAST:event_newModeItemStateChanged

    private void oldModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_oldModeItemStateChanged
        if(oldMode.isSelected()){
            newMode.setSelected(false);
        }
    }//GEN-LAST:event_oldModeItemStateChanged

    private void black3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_black3ItemStateChanged
        if(black3.isSelected()){
            color3.setSelected(false);
        }
    }//GEN-LAST:event_black3ItemStateChanged

    private void color3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_color3ItemStateChanged
        if(color3.isSelected()){
            black3.setSelected(false);
        }
    }//GEN-LAST:event_color3ItemStateChanged

    private void black4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_black4ItemStateChanged
        if(black4.isSelected()){
            color4.setSelected(false);
        }
    }//GEN-LAST:event_black4ItemStateChanged

    private void color4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_color4ItemStateChanged
        if(color4.isSelected()){
            black4.setSelected(false);
        }
    }//GEN-LAST:event_color4ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton black;
    private javax.swing.JRadioButton black1;
    private javax.swing.JRadioButton black2;
    private javax.swing.JRadioButton black3;
    private javax.swing.JRadioButton black4;
    private javax.swing.JRadioButton color;
    private javax.swing.JRadioButton color1;
    private javax.swing.JRadioButton color2;
    private javax.swing.JRadioButton color3;
    private javax.swing.JRadioButton color4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton newMode;
    private javax.swing.JRadioButton oldMode;
    // End of variables declaration//GEN-END:variables
}
