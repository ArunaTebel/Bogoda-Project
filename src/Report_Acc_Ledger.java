
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
   DatabaseManager dbm =  DatabaseManager.getDbCon();
    Report_gen generate = new Report_gen();
    Date_Handler dt = new Date_Handler();

    /**
     * Creates new form Report_Acc_Reciepts
     */
    public Report_Acc_Ledger() {
        initComponents();
        jPanel2.setVisible(false);

    }

    public void start(){
        date_check.setSelected(true);
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

        Thread a = new Thread(new Background());

        ACC_ledger ledg = new ACC_ledger();

        ACC_Update updt = new ACC_Update();

        Date_Handler dt = new Date_Handler();

        DateChooser_text date_chooser = new DateChooser_text();

        DatabaseManager dbm =  DatabaseManager.getDbCon();

        @Override
        public void run() {
            //  jProgressBar1.setIndeterminate(true);

            String from_date;
            String to_date;
            String account_name;
            int accountCode;

            double op_bal;

            if (!date_range.isSelected()) {

                if (!all.isSelected()) {

                    if (date_check.isSelected()) {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        
                        a.start();
                        
                        accountCode = Integer.parseInt(account_code.getSelectedItem().toString());

                        account_name = dbm.checknReturnData("account_names", "account_id", accountCode, "account_name");

                        ledg.fill_account_code(accountCode);

                        HashMap param = new HashMap();

                        from_date = updt.checknReturnDataN();

                        to_date = dt.get_today_date();

                        //  op_bal = Double.parseDouble(dbm.checknReturnData("account_names", "account_id", accountCode, "opening_balance"));
                        String d = dt.get_today_date().substring(0, 4);

                        String dated = d + "-04-01";

                        if (op_bal_tick.isSelected()) {
                            op_bal = 0;
                        } else {
                            op_bal = ledg.opening_balance_calc(accountCode, dated);
                        }

                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("Account_Code", accountCode);
                        param.put("Account_Name", account_name);
                        param.put("From_Date", from_date);
                        param.put("To_Date", to_date);
                        param.put("Op_Bal", op_bal);

                       // jProgressBar1.setValue(45);
                       // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                       // a.start();
                        generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (trans_check.isSelected()) {
                        
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        
                        a.start();
                        accountCode = Integer.parseInt(account_code.getSelectedItem().toString());

                        account_name = dbm.checknReturnData("account_names", "account_id", accountCode, "account_name");

                        ledg.fill_account_code(accountCode);

                        HashMap param = new HashMap();

                        from_date = updt.checknReturnDataN();

                        to_date = dt.get_today_date();

                        //  op_bal = Double.parseDouble(dbm.checknReturnData("account_names", "account_id", accountCode, "opening_balance"));
                        String d = dt.get_today_date().substring(0, 4);

                        String dated = d + "-04-01";

                        if (op_bal_tick.isSelected()) {
                            op_bal = 0;
                        } else {
                            op_bal = ledg.opening_balance_calc(accountCode, dated);
                        }

                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("Account_Code", accountCode);
                        param.put("Account_Name", account_name);
                        param.put("From_Date", from_date);
                        param.put("To_Date", to_date);
                        param.put("Op_Bal", op_bal);

                      //  jProgressBar1.setValue(45);
                      //  jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                      //  a.start();
                        generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_Sort_TR.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    }
                } else {

                    if (date_check.isSelected()) {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                         a.start();
                        HashMap param = new HashMap();
                        from_date = updt.checknReturnDataN();
                        to_date = dt.get_today_date();
                       
                        ledg.fill_all();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("From_Date", from_date);
                        param.put("To_Date", to_date);
                        
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                        generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_All.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (trans_check.isSelected()) {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        from_date = updt.checknReturnDataN();
                        to_date = dt.get_today_date();
                        
                        ledg.fill_all();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("From_Date", from_date);
                        param.put("To_Date", to_date);
                        
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                        generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_All_Sort_Tr.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    }
                }

            } else {

                if (!all.isSelected()) {

                    if (date_check.isSelected()) {
                        try {
                             jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                           
                            a.start();
                          
                            
                            
                            accountCode = Integer.parseInt(account_code.getSelectedItem().toString());

                            account_name = dbm.checknReturnData("account_names", "account_id", accountCode, "account_name");

                            HashMap param = new HashMap();

                            to_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield2, monthfield2, dayfield2));
                            from_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield, monthfield, dayfield));

                            if(op_bal_tick.isSelected()){
                                op_bal=0;
                            }
                            else{
                            op_bal = ledg.opening_balance_calc(accountCode, from_date);
                            }

                            ledg.fill_account_code_filtered_date(accountCode, from_date, to_date);

                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Account_Code", accountCode);
                            param.put("Account_Name", account_name);
                            param.put("From_Date", from_date);
                            param.put("To_Date", to_date);
                            param.put("Op_Bal", op_bal);

                           // jProgressBar1.setValue(45);
                           // jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                          //  a.start();
                            generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Ledger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (trans_check.isSelected()) {
                        try {
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                           
                            a.start();
                            accountCode = Integer.parseInt(account_code.getSelectedItem().toString());

                            account_name = dbm.checknReturnData("account_names", "account_id", accountCode, "account_name");

                            HashMap param = new HashMap();

                            to_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield2, monthfield2, dayfield2));
                            from_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield, monthfield, dayfield));

                            if(op_bal_tick.isSelected()){
                                op_bal=0;
                            }
                            else{
                            op_bal = ledg.opening_balance_calc(accountCode, from_date);
                            }

                            ledg.fill_account_code_filtered_date(accountCode, from_date, to_date);

                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Account_Code", accountCode);
                            param.put("Account_Name", account_name);
                            param.put("From_Date", from_date);
                            param.put("To_Date", to_date);
                            param.put("Op_Bal", op_bal);

                          //  jProgressBar1.setValue(45);
                          //  jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                          //  a.start();
                            generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_Sort_TR.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Ledger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {

                    if (date_check.isSelected()) {
                        try {
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                           
                            a.start();
                            HashMap param = new HashMap();
                            to_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield2, monthfield2, dayfield2));
                            from_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield, monthfield, dayfield));

                            ledg.fill_all_filtered_date(from_date, to_date);

                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("From_Date", from_date);
                            param.put("To_Date", to_date);
                          //  jProgressBar1.setValue(45);
                          //  jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                          //  a.start();
                            generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_All.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Ledger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(trans_check.isSelected()){
                        try {
                           
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                           
                            a.start();
                            HashMap param = new HashMap();
                            to_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield2, monthfield2, dayfield2));
                            from_date = dt.get_date_as_a_String(date_chooser.Return_date(yearfield, monthfield, dayfield));

                            ledg.fill_all_filtered_date(from_date, to_date);

                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("From_Date", from_date);
                            param.put("To_Date", to_date);
                            //jProgressBar1.setValue(45);
                            //jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                            //a.start();
                            generate.create("Account Ledgers", "D:\\", param, location, "Report_ACC_LedgerAccounts_All_Sort_Tr.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Ledger.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

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
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
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
        jSeparator3 = new javax.swing.JSeparator();
        account_code = new javax.swing.JComboBox();
        all = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        account_name = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        date_check = new javax.swing.JRadioButton();
        trans_check = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        op_bal_tick = new javax.swing.JCheckBox();
        date_range = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

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

        jButton1.setText("Veiw");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        dayfield.setText(""+Integer.parseInt(datehandler.get_today_day()));
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

        dayfield2.setText(""+Integer.parseInt(datehandler.get_today_day()));
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
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
        all.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                allItemStateChanged(evt);
            }
        });
        all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Accounts Ledger");

        account_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Ordered By ");

        date_check.setText("Date");
        date_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                date_checkItemStateChanged(evt);
            }
        });

        trans_check.setText("Transaction No.");
        trans_check.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trans_checkItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date_check)
                .addGap(18, 18, 18)
                .addComponent(trans_check)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_check)
                    .addComponent(trans_check)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        op_bal_tick.setText("No Opening Balance");

        date_range.setText("Date Range");
        date_range.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                date_rangeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date_range)
                .addGap(10, 10, 10)
                .addComponent(op_bal_tick)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_range)
                    .addComponent(op_bal_tick))
                .addContainerGap())
        );

        jLabel5.setText("Account Code");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(all, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(89, 89, 89))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(account_name, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(account_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(all)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
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

        DatabaseManager dbm =  DatabaseManager.getDbCon();
        MessageBox msg = new MessageBox();

        try {
            if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(account_code.getSelectedItem().toString())) == 1 || debit_accountCode.getSelectedIndex() == 0 || debit_accountCode.getSelectedItem().toString() == null) {
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
                    account_name.setText("" + Name);
                }

            } else {
                msg.showMessage("Invalid Account Code", "Receipt", "info");
                debit_accountCode.setSelectedIndex(0);
            }
        } catch (Exception e) {

        }

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

    private void date_rangeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_date_rangeItemStateChanged
        if (date_range.isSelected()) {
            jPanel2.setVisible(true);
        } else {
            jPanel2.setVisible(false);
        }
    }//GEN-LAST:event_date_rangeItemStateChanged

    private void allItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_allItemStateChanged
        if (all.isSelected()) {
            account_code.setEnabled(false);
        } else {
            account_code.setEnabled(true);
        }
    }//GEN-LAST:event_allItemStateChanged

    private void date_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_date_checkItemStateChanged
        if (date_check.isSelected()) {
            trans_check.setSelected(false);
        }
        else{
            trans_check.setSelected(true);
        }
    }//GEN-LAST:event_date_checkItemStateChanged

    private void trans_checkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trans_checkItemStateChanged
        if (trans_check.isSelected()) {
            date_check.setSelected(false);
        }
        else{
            date_check.setSelected(true);
        }
    }//GEN-LAST:event_trans_checkItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox account_code;
    private javax.swing.JLabel account_name;
    private javax.swing.JCheckBox all;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private javax.swing.JRadioButton date_check;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField monthfield;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JCheckBox op_bal_tick;
    private javax.swing.JRadioButton trans_check;
    private javax.swing.JTextField yearfield;
    private javax.swing.JTextField yearfield2;
    // End of variables declaration//GEN-END:variables
}
