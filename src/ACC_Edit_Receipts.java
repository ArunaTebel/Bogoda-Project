
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ACC_Edit_Receipts extends javax.swing.JFrame {

    public ACC_Edit_Receipts() {
        initComponents();
        try{
         this.setIconImage(new ImageIcon(getClass().getResource("Iconpng.png")).getImage());
        }catch(Exception e){
            
        }
    }

    int tr_no = 0;

    Double before_edited_debit_amount;

    int before_edited_debit_acc_id;

    public void Set_Tr_No(int tr_no) {
        this.tr_no = tr_no;
    }
    @SuppressWarnings("unchecked")

    Reciepts_accounts raobject = new Reciepts_accounts();

    Interface_Events interface_events = new Interface_Events();

    MessageBox msg = new MessageBox();

    DateChooser_text datechooser = new DateChooser_text();

    Date_Handler datehandler = new Date_Handler();

    DatabaseManager dbm = DatabaseManager.getDbCon();

    ACC_View_Database_Handling db = new ACC_View_Database_Handling();

    int count1 = 0;

    int c_acc_id[];
    double c_acc_amts[];

    public void Fill_Edit_Form(int tr_no) {
        try {
            String current = null;

            String given_period = null;

            try {
                ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
                while (rs.next()) {
                    current = rs.getString("period");
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
                while (rs1.next()) {
                    given_period = rs1.getString("period");
                }
                rs1.close();
            } catch (SQLException ex) {
                Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (current.equals(given_period)) {

                refNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "ref_no"));
                //recieptNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "reciept_no"));
                payType.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "pay_type"));
                if ("Cheque".equals(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "pay_type"))) {
                    bankCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "bank_id"));
                    branchCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "branch_id"));
                    chequeNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "cheque_no"));
                    chequeDate.setDate(java.sql.Date.valueOf(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "cheque_date")));
                }

                String[] s = new String[3];
                s = dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "date").split("-");
                yearfield.setText(s[0]);
                monthfield.setText(datehandler.Return_month(Integer.parseInt(s[1])));
                dayfield.setText("" + Integer.parseInt(s[2]));

                debit_accountCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_account_id"));
                debit_description.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_description"));
                debitAmount.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_amount"));
                description.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "description"));
                //before_edited_debit_amount = Double.parseDouble(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_amount"));
                before_edited_debit_acc_id = Integer.parseInt(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_account_id"));

                String acnt_classf = dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "account_class");
                if ("Current Asset".equals(acnt_classf) || "Fixed Asset".equals(acnt_classf) || "Expense".equals(acnt_classf)) {
                    before_edited_debit_amount = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "current_balance")) - Double.parseDouble(debitAmount.getText());
                } else {
                    before_edited_debit_amount = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "current_balance")) + Double.parseDouble(debitAmount.getText());
                }

            } else {
                
                refNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "ref_no"));
                //recieptNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "reciept_no"));
                payType.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "pay_type"));
                if ("Cheque".equals(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "pay_type"))) {
                    bankCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "bank_id"));
                    branchCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "branch_id"));
                    chequeNo.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "cheque_no"));
                    chequeDate.setDate(java.sql.Date.valueOf(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "cheque_date")));
                }

                String[] s = new String[3];
                s = dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "date").split("-");
                yearfield.setText(s[0]);
                monthfield.setText(datehandler.Return_month(Integer.parseInt(s[1])));
                dayfield.setText("" + Integer.parseInt(s[2]));

                debit_accountCode.setSelectedItem(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "debit_account_id"));
                debit_description.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "debit_description"));
                debitAmount.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "debit_amount"));
                description.setText(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "description"));

                //before_edited_debit_amount = Double.parseDouble(dbm.checknReturnStringDataReceipts("account_reciept_debitside", "tr_no", tr_no, "debit_amount"));
                before_edited_debit_acc_id = Integer.parseInt(dbm.checknReturnStringDataReceipts("account_reciept_debitside_all", "tr_no", tr_no, "debit_account_id"));

                String acnt_classf = dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "account_class");
                if ("Current Asset".equals(acnt_classf) || "Fixed Asset".equals(acnt_classf) || "Expense".equals(acnt_classf)) {
                    before_edited_debit_amount = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "current_balance")) - Double.parseDouble(debitAmount.getText());
                } else {
                    before_edited_debit_amount = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", before_edited_debit_acc_id, "current_balance")) + Double.parseDouble(debitAmount.getText());
                }


            }

            db.Inserting_To_The_Table_Filtered_Reciept_Debit_Search(credit_account_code_table, "credit_account_id", 0, 1, 50, "tr_no", tr_no, 0);
            db.Inserting_To_The_Table_Filtered_Reciept_Debit_Search(credit_description_table, "credit_description", 0, 1, 50, "tr_no", tr_no, 0);
            db.Inserting_To_The_Table_Filtered_Reciept_Debit_Search(credit_amount_table, "credit_amount", 0, 1, 50, "tr_no", tr_no, 0);

            //
            int i = 0;
            String acnt_class;
            double credit_value;
            double credit_updated_value;
            /*  while (credit_account_code_table.getValueAt(i, 0) != null) {
             credit_value = Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
             acnt_class = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "account_class");
             if ("Current Asset".equals(acnt_class) || "Fixed Asset".equals(acnt_class) || "Expense".equals(acnt_class)) {
             credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) + credit_value;
             } else {
             credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) - credit_value;
             }
             dbm.updateDatabase("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance", credit_updated_value);
             i++;
             }*/
            ///

            count1 = 0;
            i = 0;
            while (credit_account_code_table.getValueAt(i, 0) != null) {
                count1++;
                i++;
            }

            int[] acc_ids = new int[count1];
            double[] acc_amts = new double[count1];

            i = 0;
            while (credit_account_code_table.getValueAt(i, 0) != null) {
                credit_value = Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                acc_ids[i] = Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0));
                acnt_class = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "account_class");
                if ("Current Asset".equals(acnt_class) || "Fixed Asset".equals(acnt_class) || "Expense".equals(acnt_class)) {
                    acc_amts[i] = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) + credit_value;
                } else {
                    acc_amts[i] = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) - credit_value;
                }
                // dbm.updateDatabase("account_names", "account_id", Integer.parseInt((String) debit_account_code_table.getValueAt(i, 0)), "current_balance", debit_updated_value);
                i++;
            }

            c_acc_amts = acc_amts;
            c_acc_id = acc_ids;

            // Setting total and difference texts
            double tot = 0;
            i = 0;
            while (credit_account_code_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                i++;
            }
            total.setText(String.format("%.2f", tot));
            difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

        } catch (PropertyVetoException ex) {
            Logger.getLogger(ACC_Edit_Receipts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        credit_account_code_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        credit_description_table = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        credit_amount_table = new javax.swing.JTable();
        credit_account_code = new javax.swing.JComboBox();
        credit_description = new javax.swing.JTextField();
        credit_amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        difference = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        credit_account_name = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        payType = new javax.swing.JComboBox();
        refNo = new javax.swing.JTextField();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        dayfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        Cheque_pay = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        chequeNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bankName = new javax.swing.JLabel();
        branchName = new javax.swing.JLabel();
        bankCode = new javax.swing.JComboBox();
        branchCode = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        chequeDate = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel5 = new javax.swing.JPanel();
        debit_accountCode = new javax.swing.JComboBox();
        debit_description = new javax.swing.JTextField();
        debitAmount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        debit_accountName = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "CREDIT"));

        credit_account_code_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Account Code"
            }
        ));
        credit_account_code_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(credit_account_code_table);
        credit_account_code_table.setAutoResizeMode(credit_account_code_table.AUTO_RESIZE_OFF);

        credit_description_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Discription"
            }
        ));
        credit_description_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(credit_description_table);
        credit_account_code_table.setAutoResizeMode(credit_account_code_table.AUTO_RESIZE_OFF);

        credit_amount_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Amount"
            }
        ));
        credit_amount_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        credit_amount_table.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                credit_amount_tableComponentAdded(evt);
            }
        });
        credit_amount_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                credit_amount_tableKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(credit_amount_table);
        credit_account_code_table.setAutoResizeMode(credit_account_code_table.AUTO_RESIZE_OFF);

        credit_account_code.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        credit_account_code.setEditable(true);
        credit_account_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names", "account_id")));
        credit_account_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                credit_account_codeItemStateChanged(evt);
            }
        });

        credit_description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_descriptionKeyPressed(evt);
            }
        });

        credit_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_amountKeyPressed(evt);
            }
        });

        jButton1.setText("Clear all");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear Last ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
        });

        jLabel13.setText("Difference");

        total.setBackground(new java.awt.Color(255, 204, 204));

        jLabel14.setText("Total");

        credit_account_name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        credit_account_name.setForeground(new java.awt.Color(255, 0, 102));
        credit_account_name.setText("Account name here");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(credit_account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(credit_description)
                            .addComponent(credit_account_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(credit_amount, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 75, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(difference, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(total))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(credit_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(credit_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(credit_account_name)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(credit_account_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton1))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(difference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Reference No:");

        jLabel2.setText("Date");

        jLabel4.setText("Pay Type");

        payType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Cheque" }));
        payType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                payTypeItemStateChanged(evt);
            }
        });
        payType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payTypeActionPerformed(evt);
            }
        });
        payType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                payTypeKeyPressed(evt);
            }
        });

        refNo.setOpaque(false);
        refNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refNoActionPerformed(evt);
            }
        });
        refNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                refNoKeyPressed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(refNo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(payType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(refNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(payType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Cheque_pay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Branch");

        jLabel7.setText("Bank");

        jButton3.setText("View Codes");

        jButton4.setText("View Codes");

        jLabel9.setText("Cheque No:");

        chequeNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chequeNoKeyPressed(evt);
            }
        });

        jLabel5.setText("Date");

        bankName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bankName.setForeground(new java.awt.Color(51, 51, 51));

        branchName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        branchName.setForeground(new java.awt.Color(51, 51, 51));

        bankCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        bankCode.setEditable(true);
        bankCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("bank","bank_id")));
        bankCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bankCodeItemStateChanged(evt);
            }
        });
        bankCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankCodeActionPerformed(evt);
            }
        });

        branchCode.putClientProperty("JComboBox.isTableCellEditor",Boolean.TRUE);
        branchCode.setEditable(true);
        branchCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("bank_branch","branch_id")));
        branchCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                branchCodeItemStateChanged(evt);
            }
        });

        chequeDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chequeDateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chequeDate, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chequeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout Cheque_payLayout = new javax.swing.GroupLayout(Cheque_pay);
        Cheque_pay.setLayout(Cheque_payLayout);
        Cheque_payLayout.setHorizontalGroup(
            Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cheque_payLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Cheque_payLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Cheque_payLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(29, 29, 29)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bankCode, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(branchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bankName, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(branchName, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Cheque_payLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addContainerGap(393, Short.MAX_VALUE)))
        );
        Cheque_payLayout.setVerticalGroup(
            Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cheque_payLayout.createSequentialGroup()
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Cheque_payLayout.createSequentialGroup()
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Cheque_payLayout.createSequentialGroup()
                                .addComponent(bankCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(branchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Cheque_payLayout.createSequentialGroup()
                                .addComponent(bankName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(branchName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)))
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(chequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addContainerGap())
            .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Cheque_payLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel7)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "DEBIT"));

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

        debit_description.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                debit_descriptionFocusLost(evt);
            }
        });
        debit_description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_descriptionKeyPressed(evt);
            }
        });

        debitAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debitAmountKeyPressed(evt);
            }
        });

        jLabel10.setText("Account Code");

        jLabel11.setText("Description");

        jLabel12.setText("Amount");

        debit_accountName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        debit_accountName.setForeground(new java.awt.Color(255, 0, 102));
        debit_accountName.setText("Account name here");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debit_accountName, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(debit_description, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(debitAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debit_accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debit_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debitAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debit_accountName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 0, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton6FocusGained(evt);
            }
        });

        jButton7.setText("Cancel");

        jButton8.setText("Quit");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton7))
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton5.setText("Send");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton5FocusGained(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 51, 51));
        jButton9.setText("DELETE ENTRY");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel3.setText("Description");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cheque_pay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(description)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jSeparator1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cheque_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void credit_account_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_credit_account_codeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
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
            credit_account_name.setText("" + Name);
        }
        credit_description.requestFocusInWindow();
    }//GEN-LAST:event_credit_account_codeItemStateChanged

    private void credit_descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_descriptionKeyPressed
        interface_events.Change_focus_Enterkey_t(credit_amount, evt);
    }//GEN-LAST:event_credit_descriptionKeyPressed

    private void credit_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_amountKeyPressed
        interface_events.Change_focus_Enterkey_t_b(refNo, jButton5, evt);
    }//GEN-LAST:event_credit_amountKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            credit_account_code_table.setValueAt(null, i, 0);
            credit_description_table.setValueAt(null, i, 0);
            credit_amount_table.setValueAt(null, i, 0);
            i++;
        }
        double tot = 0;
        i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
            i++;
        }
        total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            i++;
        }
        i--;
        credit_account_code_table.setValueAt(null, i, 0);
        credit_description_table.setValueAt(null, i, 0);
        credit_amount_table.setValueAt(null, i, 0);

        double tot = 0;
        i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
            i++;
        }
        total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

        credit_account_code.requestFocusInWindow();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusGained
        interface_events.Respond_enter(jButton2, evt);
    }//GEN-LAST:event_jButton2FocusGained

    private void payTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_payTypeItemStateChanged
        if ("Cash".equals(payType.getSelectedItem().toString())) {
            debit_accountCode.requestFocusInWindow();
        } else {
            bankCode.requestFocusInWindow();
        }
    }//GEN-LAST:event_payTypeItemStateChanged

    private void payTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payTypeActionPerformed

        String selection = (String) payType.getSelectedItem();

        if (selection.equalsIgnoreCase("Cash")) {
            Cheque_pay.setVisible(false);

        }

        if (selection.equalsIgnoreCase("Cheque")) {
            Cheque_pay.setVisible(true);
            bankCode.requestFocusInWindow();

        }
    }//GEN-LAST:event_payTypeActionPerformed

    private void payTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_payTypeKeyPressed
        interface_events.Change_focus_Enterkey_c(debit_accountCode, evt);
    }//GEN-LAST:event_payTypeKeyPressed

    private void refNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refNoActionPerformed

    }//GEN-LAST:event_refNoActionPerformed

    private void refNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_refNoKeyPressed

        interface_events.Change_focus_Enterkey_t(dayfield, evt);
        dayfield.selectAll();
        // if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        // recieptNo.setBackground(new java.awt.Color(255, 0, 153));
        //}
    }//GEN-LAST:event_refNoKeyPressed

    private void monthfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfieldKeyPressed
        if (monthfield.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Dec");
                int yr = Integer.parseInt(yearfield.getText());

                yearfield.setText("" + (yr - 1));
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Feb");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jan");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Mar");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Feb");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Apr");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Mar");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("May");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Apr");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                monthfield.setText("Jun");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("May");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Jul");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jun");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Aug");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Jul");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Sep");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Aug");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Oct");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Sep");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Nov");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Oct");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield.setText("Dec");
                monthfield.selectAll();
            }

        } else if (monthfield.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield.setText("Nov");
                int yr = Integer.parseInt(yearfield.getText());
                monthfield.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
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
           payType.requestFocus();

        }
    }//GEN-LAST:event_monthfieldKeyPressed

    private void yearfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) + 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield.setText("" + (Integer.parseInt(yearfield.getText()) - 1));
            yearfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield.requestFocus();
            monthfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            payType.requestFocus();

        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void dayfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayfieldKeyPressed
        ///////////////////////////////////////////////////  Days Decrement/////////////////////////////////////////////////////////////////////////////

        if (dayfield.getText().equals("1")) {           // Jumping to 31 and 30 from 1st
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

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
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) - 1));
                dayfield.selectAll();
            }
        }
        /////////////////////////////////////////////////  Days Increment///////////////////////////////////////////////////////////////////////////////////////////////////
        if (dayfield.getText().equals("30")) {               // from 30th to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield.getText().equals("Apr") || monthfield.getText().equals("Jun") || monthfield.getText().equals("Sep") || monthfield.getText().equals("Nov")) {
                    dayfield.setText("0");

                    int mnth = datechooser.return_index(monthfield.getText());
                    monthfield.setText(datechooser.Return_month(mnth + 1));

                }
                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) + 1));
                dayfield.selectAll();
            }

        } else if (dayfield.getText().equals("31")) {            // from 31st to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

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
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
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
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                dayfield.setText("" + (Integer.parseInt(dayfield.getText()) + 1));
                dayfield.selectAll();

            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            monthfield.requestFocus();
            monthfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            payType.requestFocus();

        }
    }//GEN-LAST:event_dayfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        dayfield.setText("" + Integer.parseInt(datehandler.get_day(datef)));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        payType.requestFocus();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void chequeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequeNoKeyPressed
        interface_events.Change_focus_Enterkey_Cal(chequeDate, evt);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jPanel6.setBackground(new java.awt.Color(255, 0, 153));
        }
    }//GEN-LAST:event_chequeNoKeyPressed

    private void bankCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bankCodeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int item = Integer.parseInt(evt.getItem().toString());
            try {
                ResultSet query = dbm.query("SELECT * FROM bank WHERE bank_id =" + item + "");
                while (query.next()) {
                    Name = query.getString("bank_name");
                }
            } catch (SQLException ex) {
            }
            bankName.setText("" + Name);
        }

        branchCode.requestFocusInWindow();
    }//GEN-LAST:event_bankCodeItemStateChanged

    private void bankCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bankCodeActionPerformed

    private void branchCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branchCodeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int item = Integer.parseInt(evt.getItem().toString());
            try {
                ResultSet query = dbm.query("SELECT * FROM bank_branch WHERE branch_id =" + item + "");
                while (query.next()) {
                    Name = query.getString("branch_name");
                }
            } catch (SQLException ex) {
            }
            branchName.setText("" + Name);
        }

        chequeNo.requestFocusInWindow();
    }//GEN-LAST:event_branchCodeItemStateChanged

    private void chequeDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequeDateKeyPressed
        jPanel6.setBackground(new java.awt.Color(240, 240, 240));
        debit_accountCode.requestFocusInWindow();
    }//GEN-LAST:event_chequeDateKeyPressed

    private void debit_accountCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_debit_accountCodeItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
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
            debit_accountName.setText("" + Name);
        }
        debit_description.requestFocusInWindow();
    }//GEN-LAST:event_debit_accountCodeItemStateChanged

    private void debit_accountCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debit_accountCodeActionPerformed

    }//GEN-LAST:event_debit_accountCodeActionPerformed

    private void debit_accountCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_accountCodeKeyPressed

    }//GEN-LAST:event_debit_accountCodeKeyPressed

    private void debit_descriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_debit_descriptionFocusLost
        credit_description.setText(debit_description.getText());
    }//GEN-LAST:event_debit_descriptionFocusLost

    private void debit_descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_descriptionKeyPressed
        interface_events.Change_focus_Enterkey_t(debitAmount, evt);
    }//GEN-LAST:event_debit_descriptionKeyPressed

    private void debitAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debitAmountKeyPressed
        interface_events.Change_focus_Enterkey_c(credit_account_code, evt);
    }//GEN-LAST:event_debitAmountKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            double tot = 0;
            int i = 0;
            while (credit_account_code_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                i++;
            }
            total.setText(String.format("%.2f", tot));
            difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

            if (Double.parseDouble(difference.getText()) == 0) {
                try {
                    boolean addToDebitDataBase;

                    raobject.setRefNo(refNo.getText());
                    //raobject.setRecieptNo(recieptNo.getText());
                    raobject.setDate(datechooser.Return_date(yearfield, monthfield, dayfield));
                    raobject.setPayType(payType.getSelectedItem().toString());
                    raobject.setDebit_accountCode(Integer.parseInt(debit_accountCode.getSelectedItem().toString()));

                    raobject.setDebit_accountName(dbm.checknReturnStringDataReceipts("account_names", "account_id", Integer.parseInt(debit_accountCode.getSelectedItem().toString()), "account_name"));
                    //  raobject.setDebit_accountName(debit_accountName.getText());

                    raobject.setDebit_description(debit_description.getText());

                    raobject.setDebitAmount(Double.parseDouble(debitAmount.getText()));
                    raobject.setDescription(description.getText());
                    if ("Cheque".equals(raobject.getPayType())) {

                        raobject.setBankCode(Integer.parseInt(bankCode.getSelectedItem().toString()));

                        raobject.setBankName(dbm.checknReturnData("bank", "bank_id", raobject.getBankCode(), "bank_name"));
                        raobject.setBranchCode(Integer.parseInt(branchCode.getSelectedItem().toString()));

                        raobject.setBranchName(dbm.checknReturnData("bank_branch", "branch_id", raobject.getBranchCode(), "branch_name"));
                        raobject.setChequeNo(chequeNo.getText());

                        java.sql.Date date2 = new java.sql.Date(chequeDate.getDate().getTime());
                        raobject.setChequeDate(date2);

                        addToDebitDataBase = raobject.UpdateDebitDatabaseBank(tr_no);

                    } else {

                        raobject.setBankCode(0);
                        raobject.setBankName(null);
                        raobject.setBranchCode(0);
                        raobject.setBranchName(null);
                        raobject.setChequeDate(null);
                        raobject.setChequeNo(null);
                        addToDebitDataBase = raobject.UpdateDebitDatabaseCash(tr_no);

                    }
                    // adding the relevant value to the current balance of the debit account

                    if (addToDebitDataBase == true) {

                        msg.showMessage("Receipt is UPDATED to Transaction no-" + tr_no, "Receipt", "info");
               // double updated_current_balance = Double.parseDouble(dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) - before_edited_debit_amount + raobject.getDebitAmount();

                        // Delete the effect of previous entry
                        dbm.updateDatabase("account_names", "account_id", before_edited_debit_acc_id, "current_balance", before_edited_debit_amount);

                        // Add the corrected effect
                        String acnt_classn = dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "account_class");
                        double new_value = 0;
                        if ("Current Asset".equals(acnt_classn) || "Fixed Asset".equals(acnt_classn) || "Expense".equals(acnt_classn)) {
                            new_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) + raobject.getDebitAmount();
                        } else {
                            new_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) - raobject.getDebitAmount();
                        }
                        dbm.updateDatabase("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance", new_value);

                    }

                    // Credit Side of the interface
                    raobject.DeleteCreditEntries(tr_no);
                    i = 0;

                    for (i = 0; i < count1; i++) {
                        dbm.updateDatabase("account_names", "account_id", c_acc_id[i], "current_balance", c_acc_amts[i]);
                    }

                    i = 0;
                    while (credit_account_code_table.getValueAt(i, 0) != null) {
                        i++;
                    }
                    String credit_acnt_name;

                    for (int j = 0; j <= i - 1; j++) {
                        credit_acnt_name = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(j, 0)), "account_name");
                        raobject.addToCreditDataBaseEdit(tr_no, Integer.parseInt((String) credit_account_code_table.getValueAt(j, 0)), credit_acnt_name, (String) credit_description_table.getValueAt(j, 0), Double.parseDouble((String) credit_amount_table.getValueAt(j, 0)));
                    }

                    // adding the relevant value to the current balance of the credit account
                    i = 0;
                    String acnt_class;
                    double credit_value;
                    double credit_updated_value;
                    while (credit_account_code_table.getValueAt(i, 0) != null) {
                        credit_value = Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                        acnt_class = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "account_class");
                        if ("Current Asset".equals(acnt_class) || "Fixed Asset".equals(acnt_class) || "Expense".equals(acnt_class)) {
                            credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) - credit_value;
                        } else {
                            credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) + credit_value;
                        }
                        dbm.updateDatabase("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance", credit_updated_value);
                        i++;
                    }

                    // clear all
                    {
                        int j = 0;
                        while (credit_account_code_table.getValueAt(j, 0) != null) {
                            credit_account_code_table.setValueAt(null, j, 0);
                            credit_description_table.setValueAt(null, j, 0);
                            credit_amount_table.setValueAt(null, j, 0);
                            j++;
                        }

//                        recieptNo.setText(null);
                        refNo.setText(null);
                        payType.setSelectedIndex(0);
                        bankCode.setSelectedIndex(0);
                        branchCode.setSelectedIndex(0);
                        bankName.setText(null);
                        branchName.setText(null);
                        chequeNo.setText(null);
                        debit_accountCode.setSelectedIndex(0);
                        debit_description.setText(null);
                        debitAmount.setText(null);
                        debit_accountName.setText(null);
                        credit_account_code.setSelectedIndex(0);
                        credit_account_name.setText(null);
                        credit_description.setText(null);
                        credit_amount.setText(null);
                        total.setText(null);
                        difference.setText(null);
                        description.setText(null);

                    }

                    refNo.requestFocus();
                } catch (ParseException ex) {
                    Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageBox msg = new MessageBox();
                msg.showMessage("There is a Difference.. Please Check Again", "Receipt", "info");
            }
        } else {

            double tot = 0;
            int i = 0;
            while (credit_account_code_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                i++;
            }
            total.setText(String.format("%.2f", tot));
            difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

            if (Double.parseDouble(difference.getText()) == 0) {
                try {
                    boolean addToDebitDataBaseall;

                    raobject.setRefNo(refNo.getText());
//                    raobject.setRecieptNo(recieptNo.getText());
                    raobject.setDate(datechooser.Return_date(yearfield, monthfield, dayfield));
                    raobject.setPayType(payType.getSelectedItem().toString());
                    raobject.setDebit_accountCode(Integer.parseInt(debit_accountCode.getSelectedItem().toString()));

                    raobject.setDebit_accountName(dbm.checknReturnStringDataReceipts("account_names", "account_id", Integer.parseInt(debit_accountCode.getSelectedItem().toString()), "account_name"));
                    //  raobject.setDebit_accountName(debit_accountName.getText());

                    raobject.setDebit_description(debit_description.getText());

                    raobject.setDebitAmount(Double.parseDouble(debitAmount.getText()));

                    if ("Cheque".equals(raobject.getPayType())) {

                        raobject.setBankCode(Integer.parseInt(bankCode.getSelectedItem().toString()));

                        raobject.setBankName(dbm.checknReturnData("bank", "bank_id", raobject.getBankCode(), "bank_name"));
                        raobject.setBranchCode(Integer.parseInt(branchCode.getSelectedItem().toString()));

                        raobject.setBranchName(dbm.checknReturnData("bank_branch", "branch_id", raobject.getBranchCode(), "branch_name"));
                        raobject.setChequeNo(chequeNo.getText());

                        java.sql.Date date2 = new java.sql.Date(chequeDate.getDate().getTime());
                        raobject.setChequeDate(date2);

                        addToDebitDataBaseall = raobject.UpdateDebitDatabaseBankall(tr_no);

                    } else {

                        raobject.setBankCode(0);
                        raobject.setBankName(null);
                        raobject.setBranchCode(0);
                        raobject.setBranchName(null);
                        raobject.setChequeDate(null);
                        raobject.setChequeNo(null);
                        addToDebitDataBaseall = raobject.UpdateDebitDatabaseCashall(tr_no);

                    }
                    // adding the relevant value to the current balance of the debit account

                    if (addToDebitDataBaseall == true) {

                        msg.showMessage("Receipt is UPDATED to Transaction no-" + tr_no, "Receipt", "info");
               // double updated_current_balance = Double.parseDouble(dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) - before_edited_debit_amount + raobject.getDebitAmount();

                        // Delete the effect of previous entry
                 /*   dbm.updateDatabase("account_names", "account_id", before_edited_debit_acc_id, "current_balance", before_edited_debit_amount);

                         // Add the corrected effect
                         String acnt_classn = dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "account_class");
                         double new_value = 0;
                         if ("Current Asset".equals(acnt_classn) || "Fixed Asset".equals(acnt_classn) || "Expense".equals(acnt_classn)) {
                         new_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) + raobject.getDebitAmount();
                         } else {
                         new_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance")) - raobject.getDebitAmount();
                         }
                         dbm.updateDatabase("account_names", "account_id", raobject.getDebit_accountCode(), "current_balance", new_value);
                         */
                    }

                    // Credit Side of the interface
                    raobject.DeleteCreditEntriesall(tr_no);
                    /*    i = 0;

                     for (i = 0; i < count1; i++) {
                     dbm.updateDatabase("account_names", "account_id", c_acc_id[i], "current_balance", c_acc_amts[i]);
                     }
                     */
                    i = 0;
                    while (credit_account_code_table.getValueAt(i, 0) != null) {
                        i++;
                    }
                    String credit_acnt_name;

                    for (int j = 0; j <= i - 1; j++) {
                        credit_acnt_name = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(j, 0)), "account_name");
                        raobject.addToCreditDataBaseEditall(tr_no, Integer.parseInt((String) credit_account_code_table.getValueAt(j, 0)), credit_acnt_name, (String) credit_description_table.getValueAt(j, 0), Double.parseDouble((String) credit_amount_table.getValueAt(j, 0)));
                    }

                // adding the relevant value to the current balance of the credit account
            /*    i = 0;
                     String acnt_class;
                     double credit_value;
                     double credit_updated_value;
                     while (credit_account_code_table.getValueAt(i, 0) != null) {
                     credit_value = Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
                     acnt_class = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "account_class");
                     if ("Current Asset".equals(acnt_class) || "Fixed Asset".equals(acnt_class) || "Expense".equals(acnt_class)) {
                     credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) - credit_value;
                     } else {
                     credit_updated_value = Double.parseDouble((String) dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance")) + credit_value;
                     }
                     dbm.updateDatabase("account_names", "account_id", Integer.parseInt((String) credit_account_code_table.getValueAt(i, 0)), "current_balance", credit_updated_value);
                     i++;
                     }*/
                    // clear all
                    {
                        int j = 0;
                        while (credit_account_code_table.getValueAt(j, 0) != null) {
                            credit_account_code_table.setValueAt(null, j, 0);
                            credit_description_table.setValueAt(null, j, 0);
                            credit_amount_table.setValueAt(null, j, 0);
                            j++;
                        }

//                        recieptNo.setText(null);
                        refNo.setText(null);
                        payType.setSelectedIndex(0);
                        bankCode.setSelectedIndex(0);
                        branchCode.setSelectedIndex(0);
                        bankName.setText(null);
                        branchName.setText(null);
                        chequeNo.setText(null);
                        debit_accountCode.setSelectedIndex(0);
                        debit_description.setText(null);
                        debitAmount.setText(null);
                        debit_accountName.setText(null);
                        credit_account_code.setSelectedIndex(0);
                        credit_account_name.setText(null);
                        credit_description.setText(null);
                        credit_amount.setText(null);
                        total.setText(null);
                        difference.setText(null);

                    }

                    refNo.requestFocus();
                } catch (ParseException ex) {
                    Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MessageBox msg = new MessageBox();
                msg.showMessage("There is a Difference.. Please Check Again", "Receipt", "info");
            }

        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusGained
        interface_events.Respond_enter(jButton6, evt);
    }//GEN-LAST:event_jButton6FocusGained

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int i = 0;

        while (credit_account_code_table.getValueAt(i, 0) != null) {
            i++;
        }

        credit_account_code_table.setValueAt(credit_account_code.getSelectedItem().toString(), i, 0);
        credit_description_table.setValueAt(credit_description.getText(), i, 0);
        credit_amount_table.setValueAt(credit_amount.getText(), i, 0);
        credit_account_code.setSelectedIndex(0);
        credit_amount.setText(null);
        double tot = 0;
        i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
            i++;
        }
        total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));

        if (Double.parseDouble(difference.getText()) < 0) {
            msg.showMessage("Credit balance is higher than Debit balance", "Please Check Again", "info");
            jButton2.requestFocusInWindow();
        } else if (Double.parseDouble(difference.getText()) != 0) {
            msg.showMessage("There is a difference", "Please Check Again", "info");
            credit_account_code.requestFocusInWindow();
        } else {
            jButton6.requestFocusInWindow();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusGained
        interface_events.Respond_enter(jButton5, evt);
    }//GEN-LAST:event_jButton5FocusGained

    private void credit_amount_tableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_credit_amount_tableComponentAdded
        double tot = 0;
        int i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
            i++;
        }
        total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));
    }//GEN-LAST:event_credit_amount_tableComponentAdded

    private void credit_amount_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_amount_tableKeyReleased
        double tot = 0;
        int i = 0;
        while (credit_account_code_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_amount_table.getValueAt(i, 0));
            i++;
        }
        total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f",(Double.parseDouble(debitAmount.getText()) - tot)));
    }//GEN-LAST:event_credit_amount_tableKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int reply = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete Entry", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                dbm.CheckNDeleteFromDataBase("account_reciept_debitside", "tr_no", tr_no);
                dbm.CheckNDeleteFromDataBase("account_reciept_creditside", "tr_no", tr_no);

                dbm.updateDatabase("account_names", "account_id", before_edited_debit_acc_id, "current_balance", before_edited_debit_amount);

                int i = 0;

                for (i = 0; i < count1; i++) {
                    dbm.updateDatabase("account_names", "account_id", c_acc_id[i], "current_balance", c_acc_amts[i]);
                }

                i = 0;
                while (credit_account_code_table.getValueAt(i, 0) != null) {
                    i++;
                }

                int j = 0;
                while (credit_account_code_table.getValueAt(j, 0) != null) {
                    credit_account_code_table.setValueAt(null, j, 0);
                    credit_description_table.setValueAt(null, j, 0);
                    credit_amount_table.setValueAt(null, j, 0);
                    j++;
                }

//                recieptNo.setText(null);
                refNo.setText(null);
                payType.setSelectedIndex(0);
                bankCode.setSelectedIndex(0);
                branchCode.setSelectedIndex(0);
                bankName.setText(null);
                branchName.setText(null);
                chequeNo.setText(null);
                debit_accountCode.setSelectedIndex(0);
                debit_description.setText(null);
                debitAmount.setText(null);
                debit_accountName.setText(null);
                credit_account_code.setSelectedIndex(0);
                credit_account_name.setText(null);
                credit_description.setText(null);
                credit_amount.setText(null);
                total.setText(null);
                difference.setText(null);
                description.setText(null);
                refNo.requestFocus();
            }
        } else {

            int reply = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete Entry", JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                dbm.CheckNDeleteFromDataBase("account_reciept_debitside_all", "tr_no", tr_no);
                dbm.CheckNDeleteFromDataBase("account_reciept_creditside_all", "tr_no", tr_no);

                int j = 0;
                while (credit_account_code_table.getValueAt(j, 0) != null) {
                    credit_account_code_table.setValueAt(null, j, 0);
                    credit_description_table.setValueAt(null, j, 0);
                    credit_amount_table.setValueAt(null, j, 0);
                    j++;
                }

//                recieptNo.setText(null);
                refNo.setText(null);
                payType.setSelectedIndex(0);
                bankCode.setSelectedIndex(0);
                branchCode.setSelectedIndex(0);
                bankName.setText(null);
                branchName.setText(null);
                chequeNo.setText(null);
                debit_accountCode.setSelectedIndex(0);
                debit_description.setText(null);
                debitAmount.setText(null);
                debit_accountName.setText(null);
                credit_account_code.setSelectedIndex(0);
                credit_account_name.setText(null);
                credit_description.setText(null);
                credit_amount.setText(null);
                total.setText(null);
                difference.setText(null);
                description.setText(null);
                refNo.requestFocus();
            }

        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(ACC_Edit_Receipts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACC_Edit_Receipts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACC_Edit_Receipts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACC_Edit_Receipts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACC_Edit_Receipts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cheque_pay;
    private javax.swing.JComboBox bankCode;
    private javax.swing.JLabel bankName;
    private javax.swing.JComboBox branchCode;
    private javax.swing.JLabel branchName;
    private com.michaelbaranov.microba.calendar.DatePicker chequeDate;
    private javax.swing.JTextField chequeNo;
    private javax.swing.JComboBox credit_account_code;
    private javax.swing.JTable credit_account_code_table;
    private javax.swing.JLabel credit_account_name;
    private javax.swing.JTextField credit_amount;
    private javax.swing.JTable credit_amount_table;
    private javax.swing.JTextField credit_description;
    private javax.swing.JTable credit_description_table;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField debitAmount;
    private javax.swing.JComboBox debit_accountCode;
    private javax.swing.JLabel debit_accountName;
    private javax.swing.JTextField debit_description;
    private javax.swing.JTextField description;
    private javax.swing.JTextField difference;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JComboBox payType;
    private javax.swing.JTextField refNo;
    private javax.swing.JTextField total;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
