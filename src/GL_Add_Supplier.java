

import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
 * @author Pramo
 */
public class GL_Add_Supplier extends javax.swing.JPanel {
DatabaseManager dbm = DatabaseManager.getDbCon();
Interface_Events interface_events = new Interface_Events();
GL_report_generator reportgen = new GL_report_generator();
DateChooser_text date_chooser= new DateChooser_text();
Date_Handler date_handler = new Date_Handler();
        
    /**
     * Creates new form GL_Add_Supplier
     */
    private Supplier supplier;

    public GL_Add_Supplier() {
        initComponents();
        trans_rate.setSelectedIndex(2);
        welf.setSelectedIndex(1);
        supplier_id.setSelectedItem(0);
        supplier_id.getEditor().setItem("");
        
    }
 
    public void add_welf(){
    
            
           
            
            DatabaseManager dbm = DatabaseManager.getDbCon();
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            int entry = 0;
            /*try {
             entry = dbm.readLastRow("welfare", "entry") + 1;
             } catch (SQLException ex) {
             Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
             }*/
            
            String day = formatter.format(currentDate.getTime());
            int month = Integer.parseInt(day.substring(5, 7));
            //
            String thisMonth = day.substring(0, 5) + month;
            String thisMonth2 = day.substring(0, 4) + day.substring(5, 7);
            
            int newMonths = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_M", "rate")-1;
            
            String supId = null;
            if (supplier_id.getSelectedIndex() == 0 || supplier_id.getEditor().getItem().equals("") || supplier_id.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Supplier Id not Given!");
            } else {
                supId = supplier_id.getSelectedItem().toString();
            }
           try { 
            entry = Integer.parseInt(thisMonth2 + supId);}
           catch (Exception e){
                 JOptionPane.showMessageDialog(null, "Sup id error!");
           }
            
            int suspended = -1;
            int remain = -1;
            int before = 0;
            int multi = 1;
            
            
            
            
            int newRegisterd = 0;
            int newOld = 0;
           
                newRegisterd = 1;
                 newOld = 1;
                try {
                    dbm.insert("INSERT INTO welfare(entry,month,month2,sup_id,months_on_welfare,new_old,suspended_months,suspended_remain,before_after) VALUES('" + entry + "','" + thisMonth + "','" + thisMonth2 + "','" + supId + "','" + newMonths + "','" + 1 + "','" + suspended + "','" + remain + "','" + before + "')");
                } catch (SQLException ex) {
                    try {
                        dbm.insert("UPDATE  welfare SET month='"+thisMonth+"',month2='"+thisMonth2+"',sup_id='"+supId+"',months_on_welfare='"+newMonths+"',new_old='"+1+"',suspended_months='"+suspended+"',suspended_remain='"+remain+"',before_after='"+before+"' WHERE entry = '"+entry+"'");
                    } catch (SQLException ex1) {
                        Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex1);
                       
                    }
              
                }
           
           // calcfigures(reg, Nreg, sus, attention,yearfield.getText(),datehandler.return_month_as_num(monthfield.getText()));

         //String thisMonthsave = yearfield.getText() + "-" + Integer.parseInt(datehandler.return_month_as_num(monthfield.getText()));
       // DatabaseManager dbm = new DatabaseManager();
       ////////////////////////////////////////////////////////////// Update code/////////////////////////////////////////////////////////
            
        //ADD NEW OLD VALUE
            newOld = Integer.parseInt(dbm.checknReturnData("welfare", "sup_id", supId, "new_old"));
            double amount = 0;
            
            int oldRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_RATE", "rate");
            int newRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_NEW", "rate");
            
            if (remain >= 0) {
                if ((remain == 0 && before == 0) || (remain == suspended && before == 1)) {
                    if (newOld == 0) {
                        amount = (suspended+1) * oldRate;
                    } else {
                        amount = (suspended+1) * newRate;
                    }
                } else {
                    amount = 0;
                }
            } else {
                if (newOld == 0) {
                    amount = oldRate*(multi+1);
                } else {
                    amount = newRate;
                }                
            }
            dbm.update("welfare", "month", "sup_id", thisMonth, supId, "amount", amount);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
    
    
    }
    
    public void clear(){
 /*  supplier_id.setSelectedIndex(0);
    sup_name.setText("");
    sup_name_sinhala.setText("");
    Cat_code.setSelectedIndex(0);
    trans_rate.setSelectedIndex(0);
    trans_rate1.setSelectedIndex(0);
    bank_code.setSelectedIndex(0);  */
    branch_code.setSelectedIndex(0);
    acc_no.setText("");
    tel.setText("");
    address.setText("");
    cat_name.setText("");
    cat_name1.setText("");
    cat_name2.setText("");
    bank_name.setText("");
    branch_name.setText("");
        
        supplier_id.setSelectedItem(0);
        supplier_id.getEditor().setItem("");
        supplier_id.requestFocus();
    
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sup_name = new javax.swing.JTextField();
        sup_name_sinhala = new javax.swing.JTextField();
        cat_name = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        payment_method = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bank_name = new javax.swing.JTextField();
        branch_name = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        acc_no = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        quit = new javax.swing.JButton();
        cancel1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        supplier_id = new javax.swing.JComboBox();
        trans_rate = new javax.swing.JComboBox();
        bank_code = new javax.swing.JComboBox();
        branch_code = new javax.swing.JComboBox();
        Cat_code = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        date = new com.michaelbaranov.microba.calendar.DatePicker();
        cat_name1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        trans_rate1 = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        cat_name2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        welf = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        quit1 = new javax.swing.JButton();
        quit2 = new javax.swing.JButton();

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setText("Supplier Code");

        jLabel2.setText("Transport Rate");

        jLabel3.setText("Category");

        jLabel4.setText("Name");

        jLabel5.setText("Name in Sinhala");

        jLabel6.setText("Payment Method");

        jLabel7.setText("Bank");

        jLabel8.setText("Branch");

        jLabel9.setText("Account Number");

        jLabel11.setText("Telephone");

        jLabel12.setText("Address");

        jLabel13.setText("Date Commenced");

        sup_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sup_nameKeyPressed(evt);
            }
        });

        sup_name_sinhala.setFont(new java.awt.Font("Ds-Chamika", 0, 18)); // NOI18N
        sup_name_sinhala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sup_name_sinhalaKeyPressed(evt);
            }
        });

        cat_name.setBackground(new java.awt.Color(153, 255, 153));

        jLabel14.setText("Name");

        jLabel15.setText("Code");

        payment_method.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CASH", "CHEQUE", "BANK" }));
        payment_method.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                payment_methodKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                payment_methodKeyReleased(evt);
            }
        });

        jLabel16.setText("Name");

        jLabel17.setText("Code");

        bank_name.setBackground(new java.awt.Color(153, 255, 153));

        branch_name.setBackground(new java.awt.Color(153, 255, 153));

        jLabel18.setText("Name");

        jLabel19.setText("Code");

        acc_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acc_noKeyPressed(evt);
            }
        });

        tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telKeyPressed(evt);
            }
        });

        address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        save.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saveKeyPressed(evt);
            }
        });

        cancel.setText("Update");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        cancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cancelKeyPressed(evt);
            }
        });

        quit.setText("Cancel");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });
        quit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quitKeyPressed(evt);
            }
        });

        cancel1.setText("Delete");
        cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel1ActionPerformed(evt);
            }
        });
        cancel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cancel1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancel1)
                    .addComponent(quit))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quit))
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        jButton6.setText("Supplier Status");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id.setEditable(true);
        supplier_id.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        supplier_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supplier_id.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("suppliers", "sup_id")));
        supplier_id.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplier_idItemStateChanged(evt);
            }
        });

        //trans_rate.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        trans_rate.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("tranport_rates", "Trans_id"))
        );
        trans_rate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trans_rateItemStateChanged(evt);
            }
        });
        trans_rate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                trans_rateKeyPressed(evt);
            }
        });

        //bank_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        bank_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("bank", "bank_id")));
        bank_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bank_codeItemStateChanged(evt);
            }
        });
        bank_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bank_codeKeyPressed(evt);
            }
        });

        branch_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        branch_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("bank_branch", "branch_id")));
        branch_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                branch_codeItemStateChanged(evt);
            }
        });
        branch_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                branch_codeKeyPressed(evt);
            }
        });

        //Cat_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        //supplier_id.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        Cat_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("category", "category_id")));
        Cat_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cat_codeItemStateChanged(evt);
            }
        });
        Cat_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Cat_codeKeyPressed(evt);
            }
        });

        date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFocusLost(evt);
            }
        });
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        cat_name1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel20.setText("Rs");

        //supplier_id.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        trans_rate1.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("leaf_category", "category_name"))
        );
        trans_rate1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trans_rate1ItemStateChanged(evt);
            }
        });
        trans_rate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                trans_rate1KeyPressed(evt);
            }
        });

        jLabel21.setText("Leaf Rate");

        cat_name2.setBackground(new java.awt.Color(153, 255, 153));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        welf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New Register", "None", " " }));
        welf.setSelectedIndex(1);

        jLabel10.setText("Welfare");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welf, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        quit1.setText("Cancel");
        quit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quit1ActionPerformed(evt);
            }
        });
        quit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quit1KeyPressed(evt);
            }
        });

        quit2.setText("Cancel");
        quit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quit2ActionPerformed(evt);
            }
        });
        quit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quit2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel15))
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel17))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel19))
                            .addComponent(jLabel6)
                            .addComponent(jLabel21)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bank_code, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bank_name, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(quit1))
                            .addComponent(payment_method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 416, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sup_name_sinhala, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sup_name, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Cat_code, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(trans_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(trans_rate1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel14)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(cat_name, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel20)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cat_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(cat_name2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(91, 91, 91)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acc_no, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13)
                                    .addGap(10, 10, 10)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(branch_code, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(47, 47, 47)
                                    .addComponent(jLabel18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(branch_name, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(quit2)
                                    .addGap(13, 13, 13))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup_name_sinhala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cat_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(Cat_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trans_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cat_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cat_name2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trans_rate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(payment_method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(bank_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(branch_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bank_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(quit1))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(branch_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(quit2))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acc_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(86, 86, 86))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        //reading data from the textfields....
        try{
         String bankCode= "0";
        String branchCode= "0";
        String name = sup_name.getText();
        String nameSin = sup_name_sinhala.getText();
        String code = supplier_id.getSelectedItem().toString();
        String catCode = Cat_code.getSelectedItem().toString();
        String transRate = trans_rate.getSelectedItem().toString();
        String payMethod = payment_method.getSelectedItem().toString();
        try{
        bankCode = bank_code.getSelectedItem().toString();
        branchCode = branch_code.getSelectedItem().toString();
        }catch (Exception es){
     
        }
        String accNo = acc_no.getText();
        String leaf =trans_rate1.getSelectedItem().toString();
        String active = "YES";
        String welfare = "";
        int welf_num = 0;
        String welf_d = "";
        String welf_dn = "";
       
        String telephone = tel.getText();
        String addr = address.getText();
       
        Date Date;
        Date = date.getDate();
        java.sql.Date sqlDate = new java.sql.Date(Date.getTime());

        supplier = new Supplier(Integer.parseInt(code), name, nameSin,  addr, telephone, payMethod, Integer.parseInt(bankCode),
                Integer.parseInt(branchCode), accNo, catCode, sqlDate, transRate, leaf, active, welfare, welf_num, welf_d, welf_dn);
    
        if(welf.getSelectedItem().toString().equals("New Register")){
      add_welf();
        }
          try{
        supplier.addToDatabase();
         clear();
         JOptionPane.showMessageDialog(payment_method, "Saved");
        
      }
      catch(Exception ee){}
       
       } catch(Exception e){
            System.out.println(e);
         JOptionPane.showMessageDialog(date_chooser, "Empty Fields Detected");
        }
        // It will notify all the empty comboboxes other than bank and branch ids, and wont complain about text fields
    }//GEN-LAST:event_saveActionPerformed

    private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_idItemStateChanged
        if(supplier_id.getSelectedItem()!=null && supplier_id.getSelectedItem()!=""){
            try {
                sup_name.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_name"));
                sup_name_sinhala.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_sin_name"));
                Cat_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "cat_id"));
                trans_rate.setSelectedItem(dbm.checknReturnStringData("suppliers", "sup_id", supplier_id.getSelectedItem().toString(), "trans_rate"));
              //  trans_rate.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "trans_rate"));
                payment_method.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_pay_type"));
                address.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_address"));
                tel.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_tel"));
                bank_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "bank_id"));
                //System.out.println(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "bank_id"));
                
                branch_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "branch_id"));
             //   welf.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "welfare"));
                trans_rate1.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "leaf_rate_code"));
               
                //date.setDate(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_doc"));
                String date1 = dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_doc");
                java.sql.Date Datef = null;
                try{
                Datef=java.sql.Date.valueOf(date1);
                } catch (Exception e){
                    
                    Datef=java.sql.Date.valueOf(date_handler.get_today_date());
                }
                
                date.setDate(Datef);
                
            } catch (PropertyVetoException ex) {
                Logger.getLogger(GL_Add_Supplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
      sup_name.requestFocus();
       
       
        
    }//GEN-LAST:event_supplier_idItemStateChanged

    private void bank_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bank_codeItemStateChanged
        if(bank_code.getSelectedItem()!=null){
            bank_name.setText(dbm.checknReturnData("bank", "bank_id", Integer.parseInt(bank_code.getSelectedItem().toString()), "bank_name"));
            
           // branch_code.requestFocusInWindow();
        }
    }//GEN-LAST:event_bank_codeItemStateChanged

    private void branch_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branch_codeItemStateChanged
       if(branch_code.getSelectedItem()!=null){
            branch_name.setText(dbm.checknReturnData("bank_branch", "branch_id", Integer.parseInt(branch_code.getSelectedItem().toString()), "branch_name"));
            
           // acc_no.requestFocusInWindow();
        }
    }//GEN-LAST:event_branch_codeItemStateChanged

    private void Cat_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cat_codeItemStateChanged
         if(Cat_code.getSelectedItem()!=null){
            cat_name.setText(dbm.checknReturnStringData("category", "category_id", Cat_code.getSelectedItem().toString(), "category_name"));
             
          //  trans_rate.requestFocusInWindow();
        }
         
    }//GEN-LAST:event_Cat_codeItemStateChanged

    private void sup_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_nameKeyPressed
        interface_events.Change_focus_Enterkey_t(sup_name_sinhala, evt);
    }//GEN-LAST:event_sup_nameKeyPressed

    private void sup_name_sinhalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_name_sinhalaKeyPressed
        interface_events.Change_focus_Enterkey_c(Cat_code, evt);
    }//GEN-LAST:event_sup_name_sinhalaKeyPressed

    private void trans_rateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trans_rateItemStateChanged
   if(trans_rate.getSelectedItem()!=null){
            cat_name1.setText(dbm.checknReturnStringData("tranport_rates", "Trans_id", trans_rate.getSelectedItem().toString(), "Trans_rate"));
             
           //trans_rate1.requestFocusInWindow();  
        }
             
        
      
    }//GEN-LAST:event_trans_rateItemStateChanged

    private void trans_rateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trans_rateKeyPressed
        interface_events.Change_focus_Enterkey_c(trans_rate1, evt);
    }//GEN-LAST:event_trans_rateKeyPressed

    private void payment_methodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payment_methodKeyPressed
        interface_events.Change_focus_Enterkey_c(bank_code, evt);
    }//GEN-LAST:event_payment_methodKeyPressed

    private void payment_methodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payment_methodKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_payment_methodKeyReleased

    private void acc_noKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acc_noKeyPressed
        interface_events.Change_focus_Enterkey_t(tel, evt);
    }//GEN-LAST:event_acc_noKeyPressed

    private void telKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telKeyPressed
        interface_events.Change_focus_Enterkey_t(address, evt);
    }//GEN-LAST:event_telKeyPressed

    private void addressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressKeyPressed
        interface_events.Change_focus_Enterkey_Cal(date, evt);
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        jPanel3.setBackground(new java.awt.Color(0, 102, 0));
        }
    }//GEN-LAST:event_addressKeyPressed

    private void dateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFocusLost
        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_dateFocusLost

    private void dateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyPressed
        interface_events.Change_focus_Enterkey_t_b(tel, save, evt);
    }//GEN-LAST:event_dateKeyPressed

    private void saveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saveKeyPressed
       interface_events.Respond_enter(save, null);
       interface_events.Change_focus_right_b_b(cancel, evt);
    }//GEN-LAST:event_saveKeyPressed

    private void cancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelKeyPressed
       interface_events.Respond_enter(cancel, null);
        interface_events.Change_focus_right_b_b(quit, evt);
       interface_events.Change_focus_left_b_b(save, evt);
    }//GEN-LAST:event_cancelKeyPressed

    private void quitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quitKeyPressed
       interface_events.Respond_enter(quit, null);
        interface_events.Change_focus_left_b_b(cancel, evt);
    }//GEN-LAST:event_quitKeyPressed

    private void trans_rate1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trans_rate1ItemStateChanged
        if(trans_rate.getSelectedItem()!=null){
            cat_name2.setText(dbm.checknReturnStringData("leaf_category", "category_name", trans_rate1.getSelectedItem().toString(), "category_id"));
             
                // payment_method.requestFocus();
        }
  
    }//GEN-LAST:event_trans_rate1ItemStateChanged

    private void trans_rate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trans_rate1KeyPressed
        interface_events.Change_focus_Enterkey_c(payment_method, evt);
    }//GEN-LAST:event_trans_rate1KeyPressed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        clear();
    }//GEN-LAST:event_quitActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
 try{
         String bankCode= "0";
        String branchCode= "0";
        
        String name = sup_name.getText();
        String nameSin = sup_name_sinhala.getText();
        String code = supplier_id.getSelectedItem().toString();
        String catCode = Cat_code.getSelectedItem().toString();
        String transRate = trans_rate.getSelectedItem().toString();
        String payMethod = payment_method.getSelectedItem().toString();
        try{
        bankCode = bank_code.getSelectedItem().toString();
        branchCode = branch_code.getSelectedItem().toString();
        }catch (Exception es){
      }
        String accNo = acc_no.getText();
        String leaf =trans_rate1.getSelectedItem().toString();
        String active = "YES";
        String welfare = "";
        int welf_num = 0;
        String welf_d = "";
        String welf_dn = "";
       
        String telephone = tel.getText();
        String addr = address.getText();
       
        Date Date;
        Date = date.getDate();
        java.sql.Date sqlDate = new java.sql.Date(Date.getTime());

        supplier = new Supplier(Integer.parseInt(code), name, nameSin,  addr, telephone, payMethod, Integer.parseInt(bankCode),
                Integer.parseInt(branchCode), accNo, catCode, sqlDate, transRate, leaf, active, welfare, welf_num, welf_d, welf_dn);
      
        supplier.update();
        if(welf.getSelectedItem().toString().equals("New Register")){
     
      add_welf();
         
        }
         clear();
        JOptionPane.showMessageDialog(payment_method, "Updated");
         } catch(Exception e){
            System.out.println(e);
         JOptionPane.showMessageDialog(date_chooser, "Empty Fields Detected");
        }
        // It will notify all the empty comboboxes other than bank and branch ids, and wont complain about text fields
    }//GEN-LAST:event_cancelActionPerformed

    private void cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel1ActionPerformed
      int  reply = JOptionPane.showConfirmDialog(cat_name,
                        "Are you Sure?" + "\n" + "Delete Supplier "+ supplier_id.getSelectedItem()+"?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {}
                if (reply == JOptionPane.YES_OPTION) {
                
        
        dbm.CheckNDeleteFromDataBase("suppliers", "sup_id", supplier_id.getSelectedItem());
        dbm.CheckNDeleteFromDataBase("welfare", "sup_id", supplier_id.getSelectedItem());
        
        clear();}
    }//GEN-LAST:event_cancel1ActionPerformed

    private void cancel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancel1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancel1KeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         try{
        GL_Billsummery bill = new GL_Billsummery(supplier_id.getSelectedItem().toString());
         bill.setVisible(true);
        }catch (Exception e){
         GL_Billsummery bill = new GL_Billsummery("SupID");
         bill.setVisible(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Cat_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cat_codeKeyPressed
        interface_events.Change_focus_Enterkey_c(trans_rate, evt);
    }//GEN-LAST:event_Cat_codeKeyPressed

    private void bank_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bank_codeKeyPressed
       interface_events.Change_focus_Enterkey_c(branch_code, evt);
    }//GEN-LAST:event_bank_codeKeyPressed

    private void branch_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_branch_codeKeyPressed
       interface_events.Change_focus_Enterkey_t(acc_no, evt);
    }//GEN-LAST:event_branch_codeKeyPressed

    private void quit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quit1ActionPerformed
        bank_code.setSelectedItem(null);
    }//GEN-LAST:event_quit1ActionPerformed

    private void quit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_quit1KeyPressed

    private void quit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quit2ActionPerformed
        branch_code.setSelectedItem(null);
    }//GEN-LAST:event_quit2ActionPerformed

    private void quit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_quit2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Cat_code;
    private javax.swing.JTextField acc_no;
    private javax.swing.JTextField address;
    private javax.swing.JComboBox bank_code;
    private javax.swing.JTextField bank_name;
    private javax.swing.JComboBox branch_code;
    private javax.swing.JTextField branch_name;
    private javax.swing.JButton cancel;
    private javax.swing.JButton cancel1;
    private javax.swing.JTextField cat_name;
    private javax.swing.JTextField cat_name1;
    private javax.swing.JTextField cat_name2;
    private com.michaelbaranov.microba.calendar.DatePicker date;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox payment_method;
    private javax.swing.JButton quit;
    private javax.swing.JButton quit1;
    private javax.swing.JButton quit2;
    private javax.swing.JButton save;
    private javax.swing.JTextField sup_name;
    private javax.swing.JTextField sup_name_sinhala;
    private javax.swing.JComboBox supplier_id;
    private javax.swing.JTextField tel;
    private javax.swing.JComboBox trans_rate;
    private javax.swing.JComboBox trans_rate1;
    private javax.swing.JComboBox welf;
    // End of variables declaration//GEN-END:variables
}
