
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author IDDAMALGODA
 */
public class Report_Acc_Ledger extends javax.swing.JPanel {

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    DatabaseManager dbm = new DatabaseManager();
    Report_gen generate = new Report_gen();

    /**
     * Creates new form Report_Acc_Reciepts
     */
    public Report_Acc_Ledger() {
        initComponents();
        jPanel2.setVisible(false);

    }

    public class Background implements Runnable {

        @Override
        public void run() {
            //  jProgressBar1.setIndeterminate(true);
            // view.setEnabled(false);
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

            ACC_ledger ledg = new ACC_ledger();

            ACC_Update updt = new ACC_Update();

            Date_Handler dt = new Date_Handler();

            DatabaseManager dbm = new DatabaseManager();

            String from_date;
            String to_date;
            String account_name;
            int accountCode;

            double op_bal;

            if (!date_range.isSelected()) {

                if (!all.isSelected()) {

                    accountCode = Integer.parseInt(account_code.getSelectedItem().toString());

                    account_name = dbm.checknReturnData("account_names", "account_id", accountCode, "account_name");

                    ledg.fill_account_code(accountCode);

                    HashMap param = new HashMap();

                    from_date = updt.checknReturnData();

                    to_date = dt.get_today_date();

                    op_bal = Double.parseDouble(dbm.checknReturnData("account_names", "account_id", accountCode, "opening_balance"));

                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("Account_Code", accountCode);
                    param.put("Account_Name", account_name);
                    param.put("From_Date", from_date);
                    param.put("To_Date", to_date);
                    param.put("Op_Bal", op_bal);

                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("Account Receipts", "D:\\", param, location, "Report_ACC_Ledger_Accounts.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
                } else {
                    ledg.fill_all();
                    HashMap param = new HashMap();
                    from_date = updt.checknReturnData();
                    to_date = dt.get_today_date();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("From_Date", from_date);
                    param.put("To_Date", to_date);
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("Account Receipts", "D:\\", param, location, "Reports_ACC_LedgerAccounts_All.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
                }
                
            } else {
                //////// When date button is activated

          /*      if (field_choice1.getSelectedItem().toString() == "Date") {
                    if (field_choice2.getSelectedItem().toString() == "Transaction No.") {
                        try {
                            HashMap param = new HashMap();
                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                            param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                            param.put("a", field1.getText());
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                            a.start();
                            generate.create("Account Receipts", "D:\\", param, location, "Account Receipt Date And Tr.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Reciepts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            HashMap param = new HashMap();
                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                            param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                            param.put("a", Return_String_Field(search1.getText()));
                            param.put("b", field1.getText());
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                            a.start();
                            generate.create("Account Receipts", "D:\\", param, location, "Account Receipt Date And Normal.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Reciepts.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else if (field_choice1.getSelectedItem().toString() == "Transaction No.") {

                    HashMap param = new HashMap();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("a", field.getText());
                    param.put("b", Return_String_Field(search1.getText()));
                    param.put("c", field1.getText());
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("Account Receipts", "D:\\", param, location, "Account Receipt Tr And Normal.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
                } else if (field_choice2.getSelectedItem().toString() == "Transaction No.") {

                    HashMap param = new HashMap();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("c", field.getText());
                    param.put("b", Return_String_Field(search.getText()));
                    param.put("a", field1.getText());
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("Account Receipts", "D:\\", param, location, "Account Receipt Tr And Normal.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);
                } else {

                    HashMap param = new HashMap();
                    param.put("USER", new UserAccountControl().get_current_user());
                    param.put("a", Return_String_Field(search.getText()));
                    param.put("b", field.getText());
                    param.put("c", Return_String_Field(search1.getText()));
                    param.put("d", field1.getText());
                    jProgressBar1.setValue(45);
                    jProgressBar1.repaint();
                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                    a.start();
                    generate.create("Account Receipts", "D:\\", param, location, "Account Receipt AndNormal.jrxml");
                    a.stop();
                    ;
                    jProgressBar1.setValue(100);

                }*/

            }
        }
    }
    DateChooser_text datechooser = new DateChooser_text();

    Date_Handler datehandler = new Date_Handler();

    String[] combo = new String[10];

    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        debit_accountCode = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        date_range = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        dayfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        datepanel2 = new javax.swing.JPanel();
        monthfield2 = new javax.swing.JTextField();
        yearfield2 = new javax.swing.JTextField();
        dayfield2 = new javax.swing.JTextField();
        datePicker3 = new com.michaelbaranov.microba.calendar.DatePicker();
        jSeparator1 = new javax.swing.JSeparator();
        account_code = new javax.swing.JComboBox();
        all = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("ALL");

        debit_accountCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        debit_accountCode.setEditable(true);
        debit_accountCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names", "account_id")));
        debit_accountCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                debit_accountCodeItemStateChanged(evt);
            }
        });
        debit_accountCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debit_accountCodeActionPerformed(evt);
            }
        });
        debit_accountCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_accountCodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        date_range.setText("Date Range");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("From");

        jLabel2.setText("To");

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield.setText(datehandler.get_today_month());
        monthfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfieldKeyPressed(evt);
            }
        });

        yearfield.setText(datehandler.get_today_year());
        yearfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfieldKeyPressed(evt);
            }
        });

        dayfield.setText(datehandler.get_today_day());
        dayfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayfieldKeyPressed(evt);
            }
        });

        datePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanelLayout = new javax.swing.GroupLayout(datepanel);
        datepanel.setLayout(datepanelLayout);
        datepanelLayout.setHorizontalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayfield, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanelLayout.setVerticalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datepanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield2.setText(datehandler.get_today_month());
        monthfield2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfield2KeyPressed(evt);
            }
        });

        yearfield2.setText(datehandler.get_today_year());
        yearfield2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfield2KeyPressed(evt);
            }
        });

        dayfield2.setText(datehandler.get_today_day());
        dayfield2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayfield2KeyPressed(evt);
            }
        });

        datePicker3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanel2Layout = new javax.swing.GroupLayout(datepanel2);
        datepanel2.setLayout(datepanel2Layout);
        datepanel2Layout.setHorizontalGroup(
            datepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(monthfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        datepanel2Layout.setVerticalGroup(
            datepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        debit_accountCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        account_code.setEditable(true);
        account_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names", "account_id")));
        account_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                account_codeItemStateChanged(evt);
            }
        });
        account_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                account_codeActionPerformed(evt);
            }
        });
        account_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                account_codeKeyPressed(evt);
            }
        });

        all.setText("ALL");
        all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allActionPerformed(evt);
            }
        });

        jLabel3.setText("Account Code");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_range)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(all, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(192, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(account_code, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(all))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(date_range)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void monthfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfieldKeyPressed
        if (monthfield.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Dec");
                int yr = Integer.parseInt(yearfield.getText());

                yearfield.setText("" + (yr - 1));
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Feb");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jan");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Mar");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Feb");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Apr");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Mar");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("May");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Apr");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                monthfield.setText("Jun");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("May");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jul");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jun");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Aug");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jul");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Sep");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Aug");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Oct");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Sep");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Nov");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Oct");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Dec");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Nov");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jan");
                int yr = Integer.parseInt(yearfield.getText());

                yearfield.setText("" + (yr + 1));
                monthfield.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            dayfield.requestFocus();
            dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield.requestFocus();
            yearfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            dayfield2.requestFocus();
            dayfield2.selectAll();

        }
    }//GEN-LAST:event_monthfieldKeyPressed

    private void yearfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) + 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) - 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield.requestFocus();
            monthfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            dayfield2.requestFocus();
            dayfield2.selectAll();

        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void dayfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayfieldKeyPressed
        ///////////////////////////////////////////////////  Days Decrement/////////////////////////////////////////////////////////////////////////////

        if (dayfield.getText().equals("1")) {           // Jumping to 31 and 30 from 1st
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield.getText().equals("Feb") || monthfield.getText().equals("Apr") || monthfield.getText().equals("Jun") || monthfield.getText().equals("Aug") || monthfield.getText().equals("Sep") || monthfield.getText().equals("Nov") || monthfield.getText().equals("Feb")) {
                    dayfield.setText("31");

                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield.getText().equals("May") || monthfield.getText().equals("Jul") || monthfield.getText().equals("Oct") || monthfield.getText().equals("Dec")) {
                    dayfield.setText("30");
                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield.getText().equals("Mar")) {     // from march 1st jump to 28th or 29th checking leap years
                    int yr = Integer.parseInt(yearfield.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield.setText("29"); // Leap Year
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield.setText("28"); // not a leap year
                            }
                        }
                        dayfield.setText("29");       // leap year

                    }
                    if (yr % 4 != 0) {
                        dayfield.setText("28");       // not a leap year
                    }
                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield.getText().equals("Jan")) {            // From jan 1st jump to december 31st decrementing year
                    dayfield.setText("31");

                    int yr = Integer.parseInt(yearfield.getText());
                    monthfield.setText("Dec");
                    yearfield.setText("" + (yr - 1));    // year
                }
                dayfield.selectAll();
            }                                           // /// decrementing normal values
        } else if (dayfield.getText().equals("2") || dayfield.getText().equals("3") || dayfield.getText().equals("4") || dayfield.getText().equals("5")
                || dayfield.getText().equals("6") || dayfield.getText().equals("7") || dayfield.getText().equals("8") || dayfield.getText().equals("9")
                || dayfield.getText().equals("10") || dayfield.getText().equals("11") || dayfield.getText().equals("12") || dayfield.getText().equals("13") || dayfield.getText().equals("14")
                || dayfield.getText().equals("15") || dayfield.getText().equals("16") || dayfield.getText().equals("17") || dayfield.getText().equals("18")
                || dayfield.getText().equals("19") || dayfield.getText().equals("20") || dayfield.getText().equals("21") || dayfield.getText().equals("22")
                || dayfield.getText().equals("23") || dayfield.getText().equals("24") || dayfield.getText().equals("25") || dayfield.getText().equals("26")
                || dayfield.getText().equals("27") || dayfield.getText().equals("28") || dayfield.getText().equals("29") || dayfield.getText().equals("30") || dayfield.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) - 1));
                dayfield.selectAll();
            }
        }
        /////////////////////////////////////////////////  Days Increment///////////////////////////////////////////////////////////////////////////////////////////////////
        if (dayfield.getText().equals("30")) {               // from 30th to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield.getText().equals("Apr") || monthfield.getText().equals("Jun") || monthfield.getText().equals("Sep") || monthfield.getText().equals("Nov")) {
                    dayfield.setText("0");

                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth + 1));

                }
                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) + 1));
                dayfield.selectAll();
            }

        } else if (dayfield.getText().equals("31")) {            // from 31st to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield.getText().equals("Jan") || monthfield.getText().equals("Mar") || monthfield.getText().equals("May") || monthfield.getText().equals("Jul") || monthfield.getText().equals("Aug") || monthfield.getText().equals("Oct")) {
                    dayfield.setText("1");

                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth + 1));

                } else if (monthfield.getText().equals("Dec")) {      // December to january incrementing the year

                    dayfield.setText("1");

                    int yr = Integer.parseInt(yearfield.getText());
                    monthfield.setText("Jan");
                    yearfield.setText("" + (yr + 1));
                }
                dayfield.selectAll();
            }

        } else if (monthfield.getText().equals("Feb")) {                    // for february
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                if (dayfield.getText().equals("28")) {                    // at 28 check for leap year
                    int yr = Integer.parseInt(yearfield.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield.setText("29"); // Leap Year       // increment to 29
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield.setText("1");
                                int mnth = datechooser.return_index(monthfield.getText());
                                monthfield.setText(datechooser.Return_month(mnth + 1));

                                // not a leap year                             // jump to next month
                            }
                        }
                        dayfield.setText("29");       // leap year             // increment to 29th

                    }
                    if (yr % 4 != 0) {
                        dayfield.setText("1");
                        int mnth = datechooser.return_index(monthfield.getText());
                        monthfield.setText(datechooser.Return_month(mnth + 1));                  // not a leap year
                    }

                } else if (dayfield.getText().equals("29")) {              // at 29 jump to next month normally
                    dayfield.setText("1");

                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth + 1));
                    // incrementing normal values/////////////////////// for february separately
                } else if (dayfield.getText().equals("1") || dayfield.getText().equals("2") || dayfield.getText().equals("3") || dayfield.getText().equals("4") || dayfield.getText().equals("5")
                        || dayfield.getText().equals("6") || dayfield.getText().equals("7") || dayfield.getText().equals("8") || dayfield.getText().equals("9")
                        || dayfield.getText().equals("10") || dayfield.getText().equals("11") || dayfield.getText().equals("12") || dayfield.getText().equals("13") || dayfield.getText().equals("14")
                        || dayfield.getText().equals("15") || dayfield.getText().equals("16") || dayfield.getText().equals("17") || dayfield.getText().equals("18")
                        || dayfield.getText().equals("19") || dayfield.getText().equals("20") || dayfield.getText().equals("21") || dayfield.getText().equals("22")
                        || dayfield.getText().equals("23") || dayfield.getText().equals("24") || dayfield.getText().equals("25") || dayfield.getText().equals("26")
                        || dayfield.getText().equals("27") || dayfield.getText().equals("28") || dayfield.getText().equals("29") || dayfield.getText().equals("30") || dayfield.getText().equals("31")) {

                    dayfield.setText("" + (Integer.parseInt(dayfield.getText()) + 1));

                }
                dayfield.selectAll();
            }
            // incrementing normal values
        } else if (dayfield.getText().equals("1") || dayfield.getText().equals("2") || dayfield.getText().equals("3") || dayfield.getText().equals("4") || dayfield.getText().equals("5")
                || dayfield.getText().equals("6") || dayfield.getText().equals("7") || dayfield.getText().equals("8") || dayfield.getText().equals("9")
                || dayfield.getText().equals("10") || dayfield.getText().equals("11") || dayfield.getText().equals("12") || dayfield.getText().equals("13") || dayfield.getText().equals("14")
                || dayfield.getText().equals("15") || dayfield.getText().equals("16") || dayfield.getText().equals("17") || dayfield.getText().equals("18")
                || dayfield.getText().equals("19") || dayfield.getText().equals("20") || dayfield.getText().equals("21") || dayfield.getText().equals("22")
                || dayfield.getText().equals("23") || dayfield.getText().equals("24") || dayfield.getText().equals("25") || dayfield.getText().equals("26")
                || dayfield.getText().equals("27") || dayfield.getText().equals("28") || dayfield.getText().equals("29") || dayfield.getText().equals("30") || dayfield.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) + 1));
                dayfield.selectAll();

            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            monthfield.requestFocus();
            monthfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            dayfield2.requestFocus();
            dayfield2.selectAll();

        }
    }//GEN-LAST:event_dayfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        dayfield2.requestFocus();
        dayfield2.selectAll();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void monthfield2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfield2KeyPressed

        if (monthfield2.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Dec");
                int yr = Integer.parseInt(yearfield2.getText());

                yearfield2.setText("" + (yr - 1));
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Feb");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Jan");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Mar");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Feb");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Apr");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Mar");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("May");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Apr");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                monthfield2.setText("Jun");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("May");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Jul");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Jun");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Aug");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Jul");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Sep");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Aug");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Oct");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Sep");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Nov");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Oct");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Dec");
                monthfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield2.setText("Nov");
                int yr = Integer.parseInt(yearfield2.getText());
                monthfield2.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield2.setText("Jan");
                int yr = Integer.parseInt(yearfield2.getText());

                yearfield2.setText("" + (yr + 1));
                monthfield2.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            dayfield2.requestFocus();
            dayfield2.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield2.requestFocus();
            yearfield2.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            jButton1.requestFocus();

        }
    }//GEN-LAST:event_monthfield2KeyPressed

    private void yearfield2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfield2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield2.setText("" + (Integer.parseInt(yearfield2.getText()) + 1));
            yearfield2.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield2.setText("" + (Integer.parseInt(yearfield2.getText()) - 1));
            yearfield2.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield2.requestFocus();
            monthfield2.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            jButton1.requestFocus();

        }
    }//GEN-LAST:event_yearfield2KeyPressed

    private void dayfield2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayfield2KeyPressed
        ///////////////////////////////////////////////////  Days Decrement/////////////////////////////////////////////////////////////////////////////

        if (dayfield2.getText().equals("1")) {           // Jumping to 31 and 30 from 1st
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield2.getText().equals("Feb") || monthfield2.getText().equals("Apr") || monthfield2.getText().equals("Jun") || monthfield2.getText().equals("Aug") || monthfield2.getText().equals("Sep") || monthfield2.getText().equals("Nov") || monthfield2.getText().equals("Feb")) {
                    dayfield2.setText("31");

                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield2.getText().equals("May") || monthfield2.getText().equals("Jul") || monthfield2.getText().equals("Oct") || monthfield2.getText().equals("Dec")) {
                    dayfield2.setText("30");
                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield2.getText().equals("Mar")) {     // from march 1st jump to 28th or 29th checking leap years
                    int yr = Integer.parseInt(yearfield2.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield2.setText("29"); // Leap Year
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield2.setText("28"); // not a leap year
                            }
                        }
                        dayfield2.setText("29");       // leap year

                    }
                    if (yr % 4 != 0) {
                        dayfield2.setText("28");       // not a leap year
                    }
                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield2.getText().equals("Jan")) {            // From jan 1st jump to december 31st decrementing year
                    dayfield2.setText("31");

                    int yr = Integer.parseInt(yearfield2.getText());
                    monthfield2.setText("Dec");
                    yearfield2.setText("" + (yr - 1));    // year
                }
                dayfield2.selectAll();
            }                                           // /// decrementing normal values
        } else if (dayfield2.getText().equals("2") || dayfield2.getText().equals("3") || dayfield2.getText().equals("4") || dayfield2.getText().equals("5")
                || dayfield2.getText().equals("6") || dayfield2.getText().equals("7") || dayfield2.getText().equals("8") || dayfield2.getText().equals("9")
                || dayfield2.getText().equals("10") || dayfield2.getText().equals("11") || dayfield2.getText().equals("12") || dayfield2.getText().equals("13") || dayfield2.getText().equals("14")
                || dayfield2.getText().equals("15") || dayfield2.getText().equals("16") || dayfield2.getText().equals("17") || dayfield2.getText().equals("18")
                || dayfield2.getText().equals("19") || dayfield2.getText().equals("20") || dayfield2.getText().equals("21") || dayfield2.getText().equals("22")
                || dayfield2.getText().equals("23") || dayfield2.getText().equals("24") || dayfield2.getText().equals("25") || dayfield2.getText().equals("26")
                || dayfield2.getText().equals("27") || dayfield2.getText().equals("28") || dayfield2.getText().equals("29") || dayfield2.getText().equals("30") || dayfield2.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                dayfield2.setText("" + (Integer.parseInt(dayfield2.getText()) - 1));
                dayfield2.selectAll();
            }
        }
        /////////////////////////////////////////////////  Days Increment///////////////////////////////////////////////////////////////////////////////////////////////////
        if (dayfield2.getText().equals("30")) {               // from 30th to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield2.getText().equals("Apr") || monthfield2.getText().equals("Jun") || monthfield2.getText().equals("Sep") || monthfield2.getText().equals("Nov")) {
                    dayfield2.setText("0");

                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth + 1));

                }
                dayfield2.setText("" + (Integer.parseInt(dayfield2.getText()) + 1));
                dayfield2.selectAll();
            }

        } else if (dayfield2.getText().equals("31")) {            // from 31st to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield2.getText().equals("Jan") || monthfield2.getText().equals("Mar") || monthfield2.getText().equals("May") || monthfield2.getText().equals("Jul") || monthfield2.getText().equals("Aug") || monthfield2.getText().equals("Oct")) {
                    dayfield2.setText("1");

                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth + 1));

                } else if (monthfield2.getText().equals("Dec")) {      // December to january incrementing the year

                    dayfield2.setText("1");

                    int yr = Integer.parseInt(yearfield2.getText());
                    monthfield2.setText("Jan");
                    yearfield2.setText("" + (yr + 1));
                }
                dayfield2.selectAll();
            }

        } else if (monthfield2.getText().equals("Feb")) {                    // for february
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                if (dayfield2.getText().equals("28")) {                    // at 28 check for leap year
                    int yr = Integer.parseInt(yearfield2.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield2.setText("29"); // Leap Year       // increment to 29
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield2.setText("1");
                                int mnth = datechooser.return_index(monthfield2.getText());
                                monthfield2.setText(datechooser.Return_month(mnth + 1));

                                // not a leap year                             // jump to next month
                            }
                        }
                        dayfield2.setText("29");       // leap year             // increment to 29th

                    }
                    if (yr % 4 != 0) {
                        dayfield2.setText("1");
                        int mnth = datechooser.return_index(monthfield2.getText());
                        monthfield2.setText(datechooser.Return_month(mnth + 1));                  // not a leap year
                    }

                } else if (dayfield2.getText().equals("29")) {              // at 29 jump to next month normally
                    dayfield2.setText("1");

                    int mnth = datechooser.return_index(monthfield2.getText());
                    monthfield2.setText(datechooser.Return_month(mnth + 1));
                    // incrementing normal values/////////////////////// for february separately
                } else if (dayfield2.getText().equals("1") || dayfield2.getText().equals("2") || dayfield2.getText().equals("3") || dayfield2.getText().equals("4") || dayfield2.getText().equals("5")
                        || dayfield2.getText().equals("6") || dayfield2.getText().equals("7") || dayfield2.getText().equals("8") || dayfield2.getText().equals("9")
                        || dayfield2.getText().equals("10") || dayfield2.getText().equals("11") || dayfield2.getText().equals("12") || dayfield2.getText().equals("13") || dayfield2.getText().equals("14")
                        || dayfield2.getText().equals("15") || dayfield2.getText().equals("16") || dayfield2.getText().equals("17") || dayfield2.getText().equals("18")
                        || dayfield2.getText().equals("19") || dayfield2.getText().equals("20") || dayfield2.getText().equals("21") || dayfield2.getText().equals("22")
                        || dayfield2.getText().equals("23") || dayfield2.getText().equals("24") || dayfield2.getText().equals("25") || dayfield2.getText().equals("26")
                        || dayfield2.getText().equals("27") || dayfield2.getText().equals("28") || dayfield2.getText().equals("29") || dayfield2.getText().equals("30") || dayfield2.getText().equals("31")) {

                    dayfield2.setText("" + (Integer.parseInt(dayfield2.getText()) + 1));

                }
                dayfield2.selectAll();
            }
            // incrementing normal values
        } else if (dayfield2.getText().equals("1") || dayfield2.getText().equals("2") || dayfield2.getText().equals("3") || dayfield2.getText().equals("4") || dayfield2.getText().equals("5")
                || dayfield2.getText().equals("6") || dayfield2.getText().equals("7") || dayfield2.getText().equals("8") || dayfield2.getText().equals("9")
                || dayfield2.getText().equals("10") || dayfield2.getText().equals("11") || dayfield2.getText().equals("12") || dayfield2.getText().equals("13") || dayfield2.getText().equals("14")
                || dayfield2.getText().equals("15") || dayfield2.getText().equals("16") || dayfield2.getText().equals("17") || dayfield2.getText().equals("18")
                || dayfield2.getText().equals("19") || dayfield2.getText().equals("20") || dayfield2.getText().equals("21") || dayfield2.getText().equals("22")
                || dayfield2.getText().equals("23") || dayfield2.getText().equals("24") || dayfield2.getText().equals("25") || dayfield2.getText().equals("26")
                || dayfield2.getText().equals("27") || dayfield2.getText().equals("28") || dayfield2.getText().equals("29") || dayfield2.getText().equals("30") || dayfield2.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                dayfield2.setText("" + (Integer.parseInt(dayfield2.getText()) + 1));
                dayfield2.selectAll();

            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            monthfield2.requestFocus();
            monthfield2.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            jButton1.requestFocus();

        }
    }//GEN-LAST:event_dayfield2KeyPressed

    private void datePicker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker3ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        jButton1.requestFocus();
    }//GEN-LAST:event_datePicker3ActionPerformed

    ACC_Reciept_View_Table tbl = new ACC_Reciept_View_Table();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*  tbl.Clear_Table();

         tbl.setVisible(true);
         tbl.setExtendedState(ACC_Reciept_View_Table.MAXIMIZED_BOTH);
         if (search.getText() == "All") {
         tbl.Table_Fill_All();

         } else if (andbutton.isSelected()) {
         if ("Date".equals(search.getText())) {
         if ((search1.getText() == "Credit Account ID" || search1.getText() == "Credit Description" || search1.getText() == "Credit Amount")) {
         try {
         tbl.Table_Fill_Date_Credit_Search(Return_String_Field(search.getText()), datechooser.Return_date(yearfield, monthfield, dayfield), datechooser.Return_date(yearfield2, monthfield2, dayfield2), Return_String_Field(search1.getText()), field1.getText());
         } catch (ParseException ex) {
         Logger.getLogger(ACC_Reciept_View.class.getName()).log(Level.SEVERE, null, ex);
         }
         } else {
         try {
         tbl.Table_Fill_Date_Debit_Search(Return_String_Field(search.getText()), datechooser.Return_date(yearfield, monthfield, dayfield), datechooser.Return_date(yearfield2, monthfield2, dayfield2), Return_String_Field(search1.getText()), field1.getText());
         } catch (ParseException ex) {
         Logger.getLogger(ACC_Reciept_View.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         } else if (search.getText() == "Credit Account ID" || search.getText() == "Credit Description" || search.getText() == "Credit Amount") {
         if (search1.getText() == "Credit Account ID" || search1.getText() == "Credit Description" || search1.getText() == "Credit Amount") {
         tbl.Table_Fill_Credit_Credit_Search(Return_String_Field(search.getText()), field.getText(), Return_String_Field(search1.getText()), field1.getText());
         } else {
         tbl.Table_Fill_Debit_Credit_Search(Return_String_Field(search1.getText()), field1.getText(), Return_String_Field(search.getText()), field.getText());
         }
         } else {
         if (search1.getText() == "Credit Account ID" || search1.getText() == "Credit Description" || search1.getText() == "Credit Amount") {
         tbl.Table_Fill_Debit_Credit_Search(Return_String_Field(search.getText()), field.getText(), Return_String_Field(search1.getText()), field1.getText());
         } else {
         tbl.Table_Fill_Debit_Debit_Search(Return_String_Field(search.getText()), field.getText(), Return_String_Field(search1.getText()), field1.getText());
         }
         }

         } else {

         if ("Date".equals(search.getText())) {
         try {
         tbl.Table_Fill_Date_Search(Return_String_Field(search.getText()), datechooser.Return_date(yearfield, monthfield, dayfield), datechooser.Return_date(yearfield2, monthfield2, dayfield2));
         } catch (ParseException ex) {
         Logger.getLogger(ACC_Reciept_View.class.getName()).log(Level.SEVERE, null, ex);
         }
         } else if (search.getText() == "Credit Account ID" || search.getText() == "Credit Description" || search.getText() == "Credit Amount") {

         tbl.Table_Fill_Credit_Search(Return_String_Field(search.getText()), field.getText());
         } else {
         tbl.Table_Fill_Debit_Search(Return_String_Field(search.getText()), field.getText());

         }
         }*/

        /* if(!andbutton.isSelected()){
         String fieldv = Return_String_Field(search.getText());
         String para =  field.getText();
            
         HashMap rep = new HashMap();
            
         } */
        Thread s = new Thread(new report());
        s.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void debit_accountCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_debit_accountCodeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        /* String Name = null;
         if (evt.getStateChange() == ItemEvent.SELECTED) {
         int item = Integer.parseInt(evt.getItem().toString());
         try {
         ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id =" + item + "");
         while (query.next()) {
         Name = query.getString("account_name");
         }
         } catch (SQLException ex) {
         }
         debit_accountName.setText("" + Name);
         }
         debit_description.requestFocusInWindow(); */

        Check_Entries chk = new Check_Entries();
        
        MessageBox msg = new MessageBox();

        try {
            if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(debit_accountCode.getSelectedItem().toString())) == 1 || debit_accountCode.getSelectedIndex() == 0 || debit_accountCode.getSelectedItem().toString() == null) {
                String Name = null;
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    int item = Integer.parseInt(evt.getItem().toString());
                    try {
                        ResultSet query = dbm.query("SELECT * FROM account_names WHERE account_id =" + item + "");
                        while (query.next()) {
                            Name = query.getString("account_name");
                        }
                    } catch (SQLException ex) {
                    }
                    
                }
                
            } else {
                msg.showMessage("Invalid Account Code", "Receipt", "info");
                debit_accountCode.setSelectedIndex(0);
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_debit_accountCodeItemStateChanged

    private void debit_accountCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debit_accountCodeActionPerformed

    }//GEN-LAST:event_debit_accountCodeActionPerformed

    private void debit_accountCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_accountCodeKeyPressed

    }//GEN-LAST:event_debit_accountCodeKeyPressed

    private void account_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_account_codeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_account_codeItemStateChanged

    private void account_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_account_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_codeActionPerformed

    private void account_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_account_codeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_codeKeyPressed

    private void allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox account_code;
    private javax.swing.JCheckBox all;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private javax.swing.JCheckBox date_range;
    private javax.swing.JPanel datepanel;
    private javax.swing.JPanel datepanel2;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField dayfield2;
    private javax.swing.JComboBox debit_accountCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField monthfield;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JTextField yearfield;
    private javax.swing.JTextField yearfield2;
    // End of variables declaration//GEN-END:variables
}
