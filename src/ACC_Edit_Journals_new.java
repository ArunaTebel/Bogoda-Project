/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Pramo
 */
public class ACC_Edit_Journals_new extends javax.swing.JPanel {

    Journals_account jaobject = new Journals_account();

    MessageBox msg = new MessageBox();

    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();

    DatabaseManager dbm = DatabaseManager.getDbCon();

    int chkd = 1;
    int chkr = 0;
    int up = 0;
    int chkr1 = 0;
    int up1 = 0;

    Interface_Events interface_events = new Interface_Events();

    public int stringToIntNum(String s) {
        if (s.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    public double stringToDoubleNum(String s) {
        if (s.length() == 0) {
            return 0;
        } else {
            return Double.parseDouble(s);
        }
    }

    public ACC_Edit_Journals_new() {
        initComponents();
        String selection = (String) pay_type.getSelectedItem();

        if (selection.equalsIgnoreCase("Other")) {

            Cheque_pay.setVisible(false);

        }
        if (selection.equalsIgnoreCase("Cash")) {

            Cheque_pay.setVisible(false);

        }
    }

    public void foucs() {
        this.requestFocus();
        save.setEnabled(false);
        ref_no.requestFocus();
    }
    int tr_no = 0;

    public void Set_Tr_No(int tr_no) {
        this.tr_no = tr_no;
    }

    ACC_View_Database_Handling_Journals db = new ACC_View_Database_Handling_Journals();

    public void Fill_Edit_Form(int tr_no) {

        editLabel.setText("" + tr_no);
        ref_no.setText(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "ref_no"));
        // journal_no.setText(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "journal_no"));
        pay_type.setSelectedItem(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "pay_type"));
        description.setText(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "description"));
        if ("Cheque".equals(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "pay_type"))) {
            bank_code.setSelectedItem(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "bank_id"));
            branch_code.setSelectedItem(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "branch_id"));
            chequeNo.setText(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "cheque_no"));
            try {
                chequeDate.setDate(java.sql.Date.valueOf(dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "cheque_date")));
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ACC_Edit_Journals_new.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
        String[] s = new String[3];
        s = dbm.checknReturnStringDataReceipts("account_journal_main", "tr_no", tr_no, "date").split("-");
        yearfield.setText(s[0]);
        monthfield.setText(datehandler.Return_month(Integer.parseInt(s[1])));
        dayfield.setText("" + Integer.parseInt(s[2]));

        /// edited upto here
        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(debit_table, "debit_account_id", 0, 1, 50, "tr_no", tr_no, 2);
        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(debit_table, "debit_description", 1, 1, 50, "tr_no", tr_no, 2);
        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(debit_table, "debit_amount", 2, 1, 50, "tr_no", tr_no, 2);

        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(credit_table, "credit_account_id", 0, 1, 50, "tr_no", tr_no, 3);
        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(credit_table, "credit_description", 1, 1, 50, "tr_no", tr_no, 3);
        db.Inserting_To_The_Table_Filtered_Journal_Main_Search(credit_table, "credit_amount", 2, 1, 50, "tr_no", tr_no, 3);

        // Setting total and difference texts
        double totD = 0;
        int i = 0;

        while (debit_table.getValueAt(i, 0) != null) {
            totD = totD + Double.parseDouble((String) debit_table.getValueAt(i, 2));
            debit_table.setValueAt(format.modify_number(debit_table.getValueAt(i, 2).toString()), i, 2);
            i++;
        }

        NumberFormat formatter = new DecimalFormat("#0.00");
        debit_total.setText(format.modify_number(format.set_comma(formatter.format(totD))));

        double totC = 0;
        i = 0;

        while (credit_table.getValueAt(i, 0) != null) {
            totC = totC + Double.parseDouble((String) credit_table.getValueAt(i, 2));
            credit_table.setValueAt(format.modify_number(credit_table.getValueAt(i, 2).toString()), i, 2);
            i++;
        }

        credit_total.setText(format.modify_number(format.set_comma(formatter.format(totC))));

        double dif = Math.round(((totD - totC) * 100.0)) / 100.0;

        difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pay_type = new javax.swing.JComboBox();
        ref_no = new javax.swing.JTextField();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        dayfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel4 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Cheque_pay = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        chequeNo = new javax.swing.JTextField();
        bank_name = new javax.swing.JLabel();
        branch_name = new javax.swing.JLabel();
        bank_code = new javax.swing.JComboBox();
        branch_code = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        chequeDate = new com.michaelbaranov.microba.calendar.DatePicker();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        credit_table = new javax.swing.JTable();
        credit_account_code = new javax.swing.JComboBox();
        credit_description = new javax.swing.JTextField();
        credit_amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        credit_total = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        send2 = new javax.swing.JButton();
        credit_account_name = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        debit_table = new javax.swing.JTable();
        debit_account_code = new javax.swing.JComboBox();
        debit_description = new javax.swing.JTextField();
        debit_amount = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        debit_total = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        debit_account_name = new javax.swing.JLabel();
        difference = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        credit_account_code_table = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        editLabel = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Reference No:");

        jLabel2.setText("Date");

        jLabel4.setText("Pay Type");

        pay_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Other", "Cash", "Cheque" }));
        pay_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pay_typeItemStateChanged(evt);
            }
        });
        pay_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_typeActionPerformed(evt);
            }
        });
        pay_type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pay_typeKeyPressed(evt);
            }
        });

        ref_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ref_noKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ref_no, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(pay_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(ref_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pay_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 0, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        save.setText("Edit");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        save.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                saveFocusGained(evt);
            }
        });

        jButton7.setText("Cancel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Quit");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton7))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        bank_name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        branch_name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        //bank_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        DatabaseManager dbm = DatabaseManager.getDbCon();
        bank_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("bank","bank_id")));
        bank_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bank_codeItemStateChanged(evt);
            }
        });
        bank_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bank_codeActionPerformed(evt);
            }
        });
        bank_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bank_codeKeyPressed(evt);
            }
        });

        //branch_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        branch_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray2("bank_branch","branch_id")));
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
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Cheque_payLayout.createSequentialGroup()
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Cheque_payLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(branch_code, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bank_code, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bank_name, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(branch_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addGroup(Cheque_payLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
            .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Cheque_payLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addContainerGap(403, Short.MAX_VALUE)))
        );
        Cheque_payLayout.setVerticalGroup(
            Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cheque_payLayout.createSequentialGroup()
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bank_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(bank_code, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(branch_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(branch_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(chequeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(Cheque_payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Cheque_payLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel7)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "CREDIT"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
        credit_table.setModel(new javax.swing.table.DefaultTableModel(
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
                "Account Code", "                                Description", "          Amount"
            }
        ));
        credit_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        credit_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_tableKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(credit_table);
        centerRenderer.setFont(new java.awt.Font("Tahoma", 1, 14));
        if (credit_table.getColumnModel().getColumnCount() > 0) {
            credit_table.getColumnModel().getColumn(0).setMinWidth(3);
            credit_table.getColumnModel().getColumn(0).setPreferredWidth(65);
            credit_table.getColumnModel().getColumn(0).setMaxWidth(400);
            credit_table.getColumnModel().getColumn(1).setMinWidth(3);
            credit_table.getColumnModel().getColumn(1).setPreferredWidth(270);
            credit_table.getColumnModel().getColumn(1).setMaxWidth(400);
            credit_table.getColumnModel().getColumn(2).setMinWidth(3);
            credit_table.getColumnModel().getColumn(2).setPreferredWidth(70);
            credit_table.getColumnModel().getColumn(2).setMaxWidth(400);
            credit_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        }
        credit_account_code_table.setAutoResizeMode(credit_account_code_table.AUTO_RESIZE_OFF);

        credit_account_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        credit_account_code.setEditable(true);
        credit_account_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names","account_id")));
        credit_account_code.getEditor () .getEditorComponent().addKeyListener(new KeyAdapter()
            {

                public void keyReleased(KeyEvent evt) {

                    // if(evt.getKeyCode() != KeyEvent.VK_ENTER)
                    {
                        DatabaseManager dbm = DatabaseManager.getDbCon();

                        String code = ((JTextField) credit_account_code.getEditor().getEditorComponent()).getText();

                        if (dbm.checkWhetherDataExists("account_names", "account_id", code) == 1) {
                            credit_account_name.setText(dbm.checknReturnData("account_names", "account_id", code, "account_name"));

                        } else {
                            credit_account_name.setText("Invalid Account Code");
                        }

                    }
                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        chkr1 = 0;
                        System.out.println(chkr1);
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        debit_account_code.requestFocus();
                    }

                }

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        chkr1 = 0;
                        up1 = 1;
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (credit_account_name.getText() != "Invalid Account Code" && up1 == 0) {
                            credit_description.requestFocus();

                        } else if (chkr1 == 1 && credit_account_name.getText() == "Invalid Account Code") {
                            msg.showMessage("Invalid Account Code", "Receipt", "info");
                            //credit_account_code.setSelectedIndex(0);
                        } else if (chkr1 == 0) {
                            chkr1 = 1;
                            up1 = 0;
                        } else {
                            up1 = 0;
                        }
                        /* else{
                            chkr1 =0;
                        }*/
                        up1 = 0;

                    }

                }
            }
        );
        credit_account_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                credit_account_codeItemStateChanged(evt);
            }
        });
        credit_account_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_account_codeKeyPressed(evt);
            }
        });

        credit_description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_descriptionKeyPressed(evt);
            }
        });

        credit_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        credit_amount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        credit_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_amountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                credit_amountKeyReleased(evt);
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

        credit_total.setBackground(new java.awt.Color(204, 255, 204));
        credit_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel14.setText("Total");

        send2.setText("Send");
        send2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send2ActionPerformed(evt);
            }
        });
        send2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                send2FocusGained(evt);
            }
        });

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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(credit_account_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send2)
                        .addGap(57, 57, 57))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(credit_account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(credit_description, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(credit_amount))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(16, 16, 16)
                                .addComponent(jButton2)
                                .addGap(123, 123, 123)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(credit_total, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGap(34, 34, 34))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(send2)
                    .addComponent(credit_account_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(credit_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credit_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(credit_account_code))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(credit_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGap(12, 12, 12))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "DEBIT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        debit_table.setModel(new javax.swing.table.DefaultTableModel(
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
                "Account Code", "                         Description", "           Amount"
            }
        ));
        debit_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        debit_table.setMinimumSize(new java.awt.Dimension(2, 400));
        debit_table.getTableHeader().setReorderingAllowed(false);
        debit_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_tableKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(debit_table);
        if (debit_table.getColumnModel().getColumnCount() > 0) {
            debit_table.getColumnModel().getColumn(0).setMinWidth(3);
            debit_table.getColumnModel().getColumn(0).setPreferredWidth(85);
            debit_table.getColumnModel().getColumn(0).setMaxWidth(100);
            debit_table.getColumnModel().getColumn(1).setMinWidth(3);
            debit_table.getColumnModel().getColumn(1).setPreferredWidth(270);
            debit_table.getColumnModel().getColumn(1).setMaxWidth(400);
            debit_table.getColumnModel().getColumn(2).setMinWidth(3);
            debit_table.getColumnModel().getColumn(2).setPreferredWidth(70);
            debit_table.getColumnModel().getColumn(2).setMaxWidth(400);
            debit_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        }
        credit_account_code_table.setAutoResizeMode(credit_account_code_table.AUTO_RESIZE_OFF);

        debit_account_code.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        debit_account_code.setEditable(true);
        debit_account_code.getEditor () .getEditorComponent().addKeyListener(new KeyAdapter()
            {

                public void keyReleased(KeyEvent evt) {

                    // if(evt.getKeyCode() != KeyEvent.VK_ENTER)
                    {
                        DatabaseManager dbm = DatabaseManager.getDbCon();

                        String code = ((JTextField) debit_account_code.getEditor().getEditorComponent()).getText();

                        if (dbm.checkWhetherDataExists("account_names", "account_id", code) == 1) {
                            debit_account_name.setText(dbm.checknReturnData("account_names", "account_id", code, "account_name"));

                        } else {
                            debit_account_name.setText("Invalid Account Code");
                        }

                    }
                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        chkr = 0;
                        System.out.println(chkr);
                    }

                }

                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        chkr = 0;
                        up = 1;
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        credit_account_code.requestFocus();
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (debit_account_name.getText() != "Invalid Account Code" && up == 0) {
                            debit_description.requestFocus();

                        } else if (chkr == 1 && debit_account_name.getText() == "Invalid Account Code") {
                            msg.showMessage("Invalid Account Code", "Receipt", "info");
                            //debit_account_code.setSelectedIndex(0);
                        } else if (chkr == 0) {
                            chkr = 1;
                            up = 0;
                        } else {
                            up = 0;
                        }
                        /* else{
                            chkr =0;
                        }*/
                        up = 0;

                    }

                }
            }
        );
        debit_account_code.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("account_names","account_id")));
        debit_account_code.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                debit_account_codeItemStateChanged(evt);
            }
        });
        debit_account_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_account_codeKeyPressed(evt);
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

        debit_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debit_amount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        debit_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debit_amountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                debit_amountKeyReleased(evt);
            }
        });

        jButton5.setText("Clear all");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("Clear Last ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        debit_total.setBackground(new java.awt.Color(255, 204, 204));
        debit_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setText("Total");

        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        send.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sendFocusGained(evt);
            }
        });

        debit_account_name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        debit_account_name.setForeground(new java.awt.Color(255, 0, 102));
        debit_account_name.setText("Account name here");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debit_total, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(debit_account_code, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debit_description)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debit_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(debit_account_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send)
                        .addGap(24, 24, 24))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(send)
                    .addComponent(debit_account_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debit_account_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debit_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debit_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(debit_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9)
                        .addComponent(jButton5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        difference.setBackground(new java.awt.Color(255, 204, 204));
        difference.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        difference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                differenceActionPerformed(evt);
            }
        });

        jLabel13.setText("Difference");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("JOURNALS");

        description.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionFocusLost(evt);
            }
        });
        description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descriptionKeyPressed(evt);
            }
        });

        jLabel5.setText("Description");

        credit_account_code_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(credit_account_code_table);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Transaction No.");

        editLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editLabel.setText("                    ");

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 51, 51));
        jButton10.setText("DELETE ENTRY");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Cheque_pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(360, 360, 360)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(difference, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editLabel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Cheque_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(difference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pay_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_typeActionPerformed

        String selection = (String) pay_type.getSelectedItem();

        if (selection.equalsIgnoreCase("Cash")) {
            Cheque_pay.setVisible(false);

        }

        if (selection.equalsIgnoreCase("Other")) {
            Cheque_pay.setVisible(false);

        }

        if (selection.equalsIgnoreCase("Cheque")) {
            Cheque_pay.setVisible(true);
            try {
                chequeDate.setDate(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (Exception ex) {
                Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_pay_typeActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        int t = 0;
        double cred_tot = 0, diff = 0, deb_tot = 0;
        while (credit_table.getValueAt(t, 0) != null) {
            cred_tot = cred_tot + Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(t, 2)));
            t++;
        }
        t = 0;
        while (debit_table.getValueAt(t, 0) != null) {
            deb_tot = deb_tot + Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(t, 2)));
            t++;
        }
        diff = deb_tot - cred_tot;
        diff = Math.round(diff * 100.0) / 100.0;

        if (diff == 0) {
            try {
                if (datechooser.Return_date(yearfield, monthfield, dayfield).before(dbm.checknReturnData()) || datechooser.Return_date(yearfield, monthfield, dayfield).after(dbm.checkNreturnlastDate())) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not in this Accounting Period", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else {
                    boolean addToMainJournalDataBase = false;
                    jaobject.setRefNo(ref_no.getText());
                    jaobject.setTrNo(tr_no);
                    //jaobject.setJournalNo(journal_no.getText());
                    jaobject.setDate(datechooser.Return_date(yearfield, monthfield, dayfield));
                    jaobject.setPayType(pay_type.getSelectedItem().toString());
                    jaobject.setDescription(description.getText());
                    DatabaseManager dbm = DatabaseManager.getDbCon();
                    
                    dbm.CheckNDeleteFromDataBase("account_journal", "tr_no", tr_no);

                    // Adding main common parts of the interface
                    if ("Cheque".equals(jaobject.getPayType())) {

                        jaobject.setBankCode(Integer.parseInt(bank_code.getSelectedItem().toString()));

                        jaobject.setBankName(dbm.checknReturnData("bank", "bank_id", jaobject.getBankCode(), "bank_name"));
                        jaobject.setBranchCode(Integer.parseInt(branch_code.getSelectedItem().toString()));

                        jaobject.setBranchName(dbm.checknReturnData("bank_branch", "branch_id", jaobject.getBranchCode(), "branch_name"));
                        jaobject.setChequeNo(chequeNo.getText());

                        java.sql.Date date2 = new java.sql.Date(chequeDate.getDate().getTime());
                        jaobject.setChequeDate(date2);

                        dbm.CheckNDeleteFromDataBase("account_journal_main", "tr_no", tr_no);

                        

                        addToMainJournalDataBase = jaobject.addToMainJournalDataBaseBankEdit();
                    } else {
                        jaobject.setBankCode(0);
                        jaobject.setBankName(null);
                        jaobject.setBranchCode(0);
                        jaobject.setBranchName(null);
                        jaobject.setChequeDate(null);
                        jaobject.setChequeNo(null);
                        dbm.CheckNDeleteFromDataBase("account_journal_main", "tr_no", tr_no);
                        addToMainJournalDataBase = jaobject.addToMainJournalDataBaseCashEdit();
                    }

                    if (addToMainJournalDataBase == true) {

                        // Debit Side of the interface
                        int i = 0;
                        while (debit_table.getValueAt(i, 0) != null) {
                            i++;
                        }
                        String debit_acnt_name;
                        dbm.CheckNDeleteFromDataBase("account_journal_debitside", "tr_no", tr_no);
                        for (int j = 0; j <= i - 1; j++) {
                            debit_acnt_name = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) debit_table.getValueAt(j, 0)), "account_name");
                            jaobject.addToDebitDataBase(Integer.parseInt((String) debit_table.getValueAt(j, 0)), debit_acnt_name, (String) debit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(j, 2))));

                            if ("Cheque".equals(jaobject.getPayType())) {
                                jaobject.newAddToDebitDataBaseBank(Integer.parseInt((String) debit_table.getValueAt(j, 0)), debit_acnt_name, (String) debit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(j, 2))));
                            } else {
                                jaobject.newAddToDebitDataBaseCash(Integer.parseInt((String) debit_table.getValueAt(j, 0)), debit_acnt_name, (String) debit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(j, 2))));
                            }
                        }

                        // Credit Side of the interface
                        i = 0;
                        while (credit_table.getValueAt(i, 0) != null) {
                            i++;
                        }

                        String credit_acnt_name;
                        dbm.CheckNDeleteFromDataBase("account_journal_creditside", "tr_no", tr_no);
                        for (int j = 0; j <= i - 1; j++) {
                            credit_acnt_name = dbm.checknReturnData("account_names", "account_id", Integer.parseInt((String) credit_table.getValueAt(j, 0)), "account_name");
                            jaobject.addToCreditDataBase(Integer.parseInt((String) credit_table.getValueAt(j, 0)), credit_acnt_name, (String) credit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(j, 2))));

                            if ("Cheque".equals(jaobject.getPayType())) {
                                jaobject.newAddToCreditDataBaseBank(Integer.parseInt((String) credit_table.getValueAt(j, 0)), credit_acnt_name, (String) credit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(j, 2))));
                            } else {
                                jaobject.newAddToCreditDataBaseCash(Integer.parseInt((String) credit_table.getValueAt(j, 0)), credit_acnt_name, (String) credit_table.getValueAt(j, 1), Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(j, 2))));
                            }
                        }

                        msg.showMessage("Journal Entry is updated to Transaction no-" + jaobject.Get_Tr_no(), "Receipt", "info");

                        ref_no.setText(null);
                        // journal_no.setText(null);
                        pay_type.setSelectedIndex(0);
                        bank_code.setSelectedIndex(0);
                        branch_code.setSelectedIndex(0);
                        bank_name.setText(null);
                        branch_name.setText(null);
                        chequeNo.setText(null);
                        debit_account_code.setSelectedIndex(0);
                        debit_description.setText(null);
                        debit_amount.setText(null);
                        debit_account_name.setText(null);
                        credit_account_code.setSelectedIndex(0);
                        credit_account_name.setText(null);
                        credit_description.setText(null);
                        credit_amount.setText(null);
                        debit_total.setText(null);
                        credit_total.setText(null);
                        difference.setText(null);
                        description.setText(null);

                        // clear credit table all
                        {
                            int j = 0;
                            while (credit_table.getValueAt(j, 0) != null) {
                                credit_table.setValueAt(null, j, 0);
                                credit_table.setValueAt(null, j, 1);
                                credit_table.setValueAt(null, j, 2);
                                j++;
                            }
                        }

                        // clear debit table all
                        {
                            int j = 0;
                            while (debit_table.getValueAt(j, 0) != null) {
                                debit_table.setValueAt(null, j, 0);
                                debit_table.setValueAt(null, j, 1);
                                debit_table.setValueAt(null, j, 2);
                                j++;
                            }
                        }

                        ref_no.requestFocus();

                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(ACC_journals.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            debit_total.setText(format.modify_number(format.set_comma("" + deb_tot)));
            credit_total.setText(format.modify_number(format.set_comma("" + cred_tot)));
            difference.setText(format.modify_number(format.set_comma("" + diff)));
            msg.showMessage("There is a difference", "Please Check Again", "info");
            debit_account_code.requestFocusInWindow();
        }

    }//GEN-LAST:event_saveActionPerformed

    private void bank_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bank_codeItemStateChanged
        /*  DatabaseManager dbm = DatabaseManager.getDbCon();
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
         bank_name.setText("" + Name);
         }
         interface_events.Change_focus_Enterkey_c(branch_code, null);*/

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
            bank_name.setText("" + Name);
        }

        // branch_code.requestFocusInWindow();
    }//GEN-LAST:event_bank_codeItemStateChanged

    private void bank_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bank_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bank_codeActionPerformed

    private void branch_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branch_codeItemStateChanged
        /*           DatabaseManager dbm = DatabaseManager.getDbCon();
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
         branch_name.setText("" + Name);
         }
         interface_events.Change_focus_Enterkey_t(chequeNo, null); */

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
            branch_name.setText("" + Name);
        }

        // chequeNo.requestFocusInWindow();
    }//GEN-LAST:event_branch_codeItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int i = 0;
        while (credit_table.getValueAt(i, 0) != null) {
            i++;
        }
        i--;
        credit_table.setValueAt(null, i, 0);
        credit_table.setValueAt(null, i, 1);
        credit_table.setValueAt(null, i, 2);

        double tot = 0;
        i = 0;
        while (credit_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(i, 2)));
            tot = Math.round(tot * 100.0) / 100.0;
            i++;
        }
           // credit_total.setText(String.format("%.2f", tot));

        // Difference Calculation
        //  difference.setText(String.format("%.2f", (stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText())))));
        ////  credit_total.setText(format.modify_number(format.set_comma("" + tot)));
        NumberFormat formatter = new DecimalFormat("#0.00");
        credit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));

        // difference.setText("" + (Double.parseDouble(debitAmount.getText()) - tot));
        double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
        //// difference.setText(format.modify_number(format.set_comma("" + dif)));

        difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));

    }//GEN-LAST:event_jButton2ActionPerformed

    private void send2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send2ActionPerformed

        if (chk.isDouble(format.getNumberWithoutCommas(credit_amount.getText())) && credit_amount.getText().length() != 0) {
            int i = 0;
            while (credit_table.getValueAt(i, 0) != null) {
                i++;
            }

            credit_table.setValueAt(credit_account_code.getSelectedItem().toString(), i, 0);
            credit_table.setValueAt(credit_description.getText(), i, 1);
            credit_table.setValueAt(credit_amount.getText(), i, 2);
            credit_account_code.setSelectedIndex(0);
            credit_amount.setText(null);

            // Total calculation
            double tot = 0;
            i = 0;
            while (credit_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(i, 2)));
                tot = Math.round(tot * 100.0) / 100.0;
                i++;
            }
           // credit_total.setText(String.format("%.2f", tot));

            // Difference Calculation
            //  difference.setText(String.format("%.2f", (stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText())))));
            NumberFormat formatter = new DecimalFormat("#0.00");
            credit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));
            ////  credit_total.setText(format.modify_number(format.set_comma("" + tot)));

            // difference.setText("" + (Double.parseDouble(debitAmount.getText()) - tot));
            double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
            //// difference.setText(format.modify_number(format.set_comma("" + dif)));

            difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));

            if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) == 0) {

                if (debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
                    if (chkd == 1) {
                        save.setEnabled(true);
                        save.requestFocus();
                    }

                }
            } else {

                try {
                    if (Integer.parseInt(dbm.checknReturnData("rate_details", "Code_name", "ACJMSG", "rate")) == 1) {
                        msg.showMessage("There is a Difference", "Journal Entry", "info");
                    }
                } catch (Exception e) {

                }
                if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) > 0) {
                    credit_account_code.requestFocus();
                } else {
                    debit_account_code.requestFocus();
                }
            }
        } else {

            msg.showMessage("Please enter a valid amount", "Journal", "info");
            credit_amount.requestFocus();

        }


    }//GEN-LAST:event_send2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = 0;
        while (credit_table.getValueAt(i, 0) != null) {
            credit_table.setValueAt(null, i, 0);
            credit_table.setValueAt(null, i, 1);
            credit_table.setValueAt(null, i, 2);
            i++;
        }
        double tot = 0;
        i = 0;
        while (credit_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) credit_table.getValueAt(i, 2));
            i++;
        }
        credit_total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void credit_account_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_credit_account_codeItemStateChanged
//        DatabaseManager dbm = DatabaseManager.getDbCon();
        /* if (credit_account_code.getSelectedItem() != null) {
         if (credit_account_code.getSelectedIndex() != 0) {
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
         credit_description.requestFocus();
         }
         } */

        /*    try {
         if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(credit_account_code.getSelectedItem().toString())) == 1 || credit_account_code.getSelectedIndex() == 0 || credit_account_code.getSelectedItem().toString() == null) {
         if (credit_account_code.getSelectedItem() != null) {
         if (credit_account_code.getSelectedIndex() != 0) {
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
         // credit_description.requestFocus();
         }
         }
         } else {
         msg.showMessage("Invalid Account Code", "Receipt", "info");
         credit_account_code.setSelectedIndex(0);
         }
         } catch (Exception e) {

         }*/
    }//GEN-LAST:event_credit_account_codeItemStateChanged

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
            // journal_no.requestFocus();

            try {
                if (Integer.parseInt(dayfield.getText()) > datehandler.return_enddate(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()))) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not a valid Date", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else if (datechooser.Return_date(yearfield, monthfield, dayfield).before(dbm.checknReturnData()) || datechooser.Return_date(yearfield, monthfield, dayfield).after(dbm.checkNreturnlastDate())) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not in this Accounting Period", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else {
                    chkd = 1;
                    pay_type.requestFocus();
                }

            } catch (ParseException ex) {
                Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            //journal_no.requestFocus();

            try {
                if (Integer.parseInt(dayfield.getText()) > datehandler.return_enddate(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()))) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not a valid Date", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else if (datechooser.Return_date(yearfield, monthfield, dayfield).before(dbm.checknReturnData()) || datechooser.Return_date(yearfield, monthfield, dayfield).after(dbm.checkNreturnlastDate())) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not in this Accounting Period", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else {
                    chkd = 1;
                    pay_type.requestFocus();
                }

            } catch (ParseException ex) {
                Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            // journal_no.requestFocus();

            try {
                if (Integer.parseInt(dayfield.getText()) > datehandler.return_enddate(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()))) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not a valid Date", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else if (datechooser.Return_date(yearfield, monthfield, dayfield).before(dbm.checknReturnData()) || datechooser.Return_date(yearfield, monthfield, dayfield).after(dbm.checkNreturnlastDate())) {
                    chkd = 0;
                    msg.showMessage("Date You Entered is not in this Accounting Period", "Please Check Again", "info");
                    dayfield.requestFocus();
                } else {
                    chkd = 1;
                    pay_type.requestFocus();
                }

            } catch (ParseException ex) {
                Logger.getLogger(ACC_recepts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dayfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        dayfield.setText("" + Integer.parseInt(datehandler.get_day(datef)));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        pay_type.requestFocus();

    }//GEN-LAST:event_datePicker1ActionPerformed

    private void ref_noKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ref_noKeyPressed
        interface_events.Change_focus_Enterkey_t(dayfield, evt);
        dayfield.selectAll();
    }//GEN-LAST:event_ref_noKeyPressed

    private void pay_typeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pay_typeKeyPressed
        if (pay_type.getSelectedItem() == "Cash" || pay_type.getSelectedItem() == "Other") {
            interface_events.Change_focus_Enterkey_t(description, evt);
        } else {
            interface_events.Change_focus_Enterkey_c(bank_code, evt);
        }
    }//GEN-LAST:event_pay_typeKeyPressed

    Check_Entries chk = new Check_Entries();

    private void pay_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pay_typeItemStateChanged

    }//GEN-LAST:event_pay_typeItemStateChanged

    private void credit_descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_descriptionKeyPressed
        interface_events.Change_focus_Enterkey_t(credit_amount, evt);
    }//GEN-LAST:event_credit_descriptionKeyPressed

    private void credit_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_amountKeyPressed

        /*   if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (chk.isDouble(credit_amount.getText())) {
         NumberFormat formatter = new DecimalFormat("0.00");
         credit_amount.setText(formatter.format(Double.parseDouble(credit_amount.getText())));
         interface_events.Change_focus_Enterkey_t_b(ref_no, send2, evt);
         } else {

         msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
         credit_amount.requestFocus();
         }
         }*/
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chk.isDouble(format.getNumberWithoutCommas(credit_amount.getText()))) {
                credit_amount.setText(format.modify_number(credit_amount.getText()));
                //interface_events.Change_focus_Enterkey_t_b(ref_no, send2, evt);
                send2.doClick();

            } else {
                msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                credit_amount.requestFocus();
            }
        }

    }//GEN-LAST:event_credit_amountKeyPressed

    private void send2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_send2FocusGained
        interface_events.Respond_enter(send2, evt);
    }//GEN-LAST:event_send2FocusGained

    private void chequeNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequeNoKeyPressed
        interface_events.Change_focus_Enterkey_t(description, evt);

        /*  if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         jPanel6.setBackground(new java.awt.Color(255, 0, 153));
         }*/

    }//GEN-LAST:event_chequeNoKeyPressed

    private void saveFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_saveFocusGained
        interface_events.Respond_enter(save, evt);
    }//GEN-LAST:event_saveFocusGained

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ref_no.setText(null);
        //journal_no.setText(null);
        description.setText(null);
        pay_type.setSelectedIndex(0);
        bank_code.setSelectedIndex(0);
        branch_code.setSelectedIndex(0);
        bank_name.setText(null);
        branch_name.setText(null);
        chequeNo.setText(null);
        debit_account_code.setSelectedIndex(0);
        debit_description.setText(null);
        debit_amount.setText(null);
        debit_account_name.setText(null);
        credit_account_code.setSelectedIndex(0);
        credit_account_name.setText(null);
        credit_description.setText(null);
        credit_amount.setText(null);
        debit_total.setText(null);
        credit_total.setText(null);
        difference.setText(null);

        // clear credit table all
        {
            int j = 0;
            while (credit_table.getValueAt(j, 0) != null) {
                credit_table.setValueAt(null, j, 0);
                credit_table.setValueAt(null, j, 1);
                credit_table.setValueAt(null, j, 2);
                j++;
            }
        }

        // clear debit table all
        {
            int j = 0;
            while (debit_table.getValueAt(j, 0) != null) {
                debit_table.setValueAt(null, j, 0);
                debit_table.setValueAt(null, j, 1);
                debit_table.setValueAt(null, j, 2);
                j++;
            }
        }
        ref_no.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void bank_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bank_codeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            branch_code.requestFocusInWindow();
        }
    }//GEN-LAST:event_bank_codeKeyPressed

    private void branch_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_branch_codeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chequeNo.requestFocusInWindow();
        }
    }//GEN-LAST:event_branch_codeKeyPressed

    private void chequeDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chequeDateKeyPressed
        jPanel6.setBackground(new java.awt.Color(240, 240, 240));
        description.requestFocusInWindow();
    }//GEN-LAST:event_chequeDateKeyPressed

    private void credit_tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_tableKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            save.setEnabled(false);
            /*   double tot = 0;
             int i = 0;
             while (credit_table.getValueAt(i, 0) != null) {
             tot = tot + Double.parseDouble((String) credit_table.getValueAt(i, 2));
             i++;
             }
             credit_total.setText(String.format("%.2f", tot));

             // Difference Calculation
             difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));

             if (Double.parseDouble(difference.getText()) == 0) {

             if (ref_no.getText().length() != 0 && debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
             if (chkd == 1) {
             save.setEnabled(true);
             save.requestFocus();
             }

             }
             } else {
             msg.showMessage("There is a Difference", "Journal Entry", "info");
             credit_account_code.requestFocus();
             }*/

            double tot = 0;
            int i = 0;
            while (credit_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) credit_table.getValueAt(i, 2)));
                tot = Math.round(tot * 100.0) / 100.0;
                i++;
            }
           // credit_total.setText(String.format("%.2f", tot));

            // Difference Calculation
            //  difference.setText(String.format("%.2f", (stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText())))));
            NumberFormat formatter = new DecimalFormat("#0.00");
            credit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));
            ////  credit_total.setText(format.modify_number(format.set_comma("" + tot)));

            // difference.setText("" + (Double.parseDouble(debitAmount.getText()) - tot));
            double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
            //// difference.setText(format.modify_number(format.set_comma("" + dif)));

            difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));

            if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) == 0) {

                if (debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
                    if (chkd == 1) {
                        save.setEnabled(true);
                        save.requestFocus();
                    }

                }
            } else {

                try {
                    if (Integer.parseInt(dbm.checknReturnData("rate_details", "Code_name", "ACJMSG", "rate")) == 1) {
                        msg.showMessage("There is a Difference", "Journal Entry", "info");
                    }
                } catch (Exception e) {

                }
                if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) > 0) {
                    credit_account_code.requestFocus();
                } else {
                    debit_account_code.requestFocus();
                }
            }

        }

    }//GEN-LAST:event_credit_tableKeyPressed

    private void sendFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sendFocusGained
        interface_events.Respond_enter(send, evt);
    }//GEN-LAST:event_sendFocusGained
    ACC_Number_Formats format = new ACC_Number_Formats();
    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed

        if (chk.isDouble(format.getNumberWithoutCommas(debit_amount.getText())) && debit_amount.getText().length() != 0) {
            int i = 0;
            while (debit_table.getValueAt(i, 0) != null) {
                i++;
            }

            debit_table.setValueAt(debit_account_code.getSelectedItem().toString(), i, 0);
            debit_table.setValueAt(debit_description.getText(), i, 1);
            debit_table.setValueAt(debit_amount.getText(), i, 2);
            debit_account_code.setSelectedIndex(0);
            debit_amount.setText(null);
            double tot = 0;
            i = 0;
            while (debit_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(i, 2)));
                tot = Math.round(tot * 100.0) / 100.0;
                i++;
            }
            //   debit_total.setText(String.format("%.2f", tot));
            //   difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));
            NumberFormat formatter = new DecimalFormat("#0.00");
            debit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));
            //// debit_total.setText(format.modify_number(format.set_comma("" + tot)));
            double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
            ////  difference.setText(format.modify_number(format.set_comma("" +dif )));

            difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));
            ///////
            if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) == 0) {

                if (debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
                    if (chkd == 1) {
                        save.setEnabled(true);
                        save.requestFocus();
                    }

                }
            } else {
                if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) > 0) {
                    credit_account_code.requestFocus();
                } else {
                    debit_account_code.requestFocus();
                }
            }
            //////
        } else {

            msg.showMessage("Please enter a valid amount", "Journal", "info");
            debit_amount.requestFocus();

        }

    }//GEN-LAST:event_sendActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int i = 0;
        while (debit_table.getValueAt(i, 0) != null) {
            i++;
        }
        i--;
        debit_table.setValueAt(null, i, 0);
        debit_table.setValueAt(null, i, 1);
        debit_table.setValueAt(null, i, 2);

        double tot = 0;
        i = 0;
        while (debit_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(i, 2)));
            tot = Math.round(tot * 100.0) / 100.0;
            i++;
        }
        //   debit_total.setText(String.format("%.2f", tot));
        //   difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));
        NumberFormat formatter = new DecimalFormat("#0.00");
        debit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));
        //// debit_total.setText(format.modify_number(format.set_comma("" + tot)));
        double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
        //// difference.setText(format.modify_number(format.set_comma("" +dif )));
        difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int i = 0;
        while (debit_table.getValueAt(i, 0) != null) {
            debit_table.setValueAt(null, i, 0);
            debit_table.setValueAt(null, i, 1);
            debit_table.setValueAt(null, i, 2);
            i++;
        }
        double tot = 0;
        i = 0;
        while (debit_table.getValueAt(i, 0) != null) {
            tot = tot + Double.parseDouble((String) debit_table.getValueAt(i, 2));
            i++;
        }
        debit_total.setText(String.format("%.2f", tot));
        difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void debit_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_amountKeyPressed
        /*  interface_events.Change_focus_Enterkey_t_b(ref_no, send, evt);
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (chk.isDouble(debit_amount.getText())) {
         NumberFormat formatter = new DecimalFormat("0.00");
         debit_amount.setText(formatter.format(Double.parseDouble(debit_amount.getText())));
         interface_events.Change_focus_Enterkey_t_b(ref_no, send, evt);
         } else {

         msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
         debit_amount.requestFocus();
         }
         }*/

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chk.isDouble(format.getNumberWithoutCommas(debit_amount.getText()))) {
                debit_amount.setText(format.modify_number(debit_amount.getText()));
                //interface_events.Change_focus_Enterkey_t_b(ref_no, send, evt);
                send.doClick();

            } else {
                msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                debit_amount.requestFocus();
            }
        }
    }//GEN-LAST:event_debit_amountKeyPressed

    private void debit_descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_descriptionKeyPressed
        interface_events.Change_focus_Enterkey_t(debit_amount, evt);
    }//GEN-LAST:event_debit_descriptionKeyPressed

    private void debit_descriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_debit_descriptionFocusLost

    }//GEN-LAST:event_debit_descriptionFocusLost

    private void debit_account_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_account_codeKeyPressed
        /*if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         debit_description.requestFocusInWindow();
         }*/
    }//GEN-LAST:event_debit_account_codeKeyPressed

    private void debit_account_codeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_debit_account_codeItemStateChanged
//        DatabaseManager dbm = DatabaseManager.getDbCon();
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
         debit_account_name.setText("" + Name);
         }
         debit_description.requestFocus();*/

        /*   try {
         if (dbm.checkWhetherDataExists("account_names", "account_id", Integer.parseInt(debit_account_code.getSelectedItem().toString())) == 1 || debit_account_code.getSelectedIndex() == 0 || debit_account_code.getSelectedItem().toString() == null) {
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
         debit_account_name.setText("" + Name);
         }
         //  debit_description.requestFocus();
         } else {
         msg.showMessage("Invalid Account Code", "Receipt", "info");
         debit_account_code.setSelectedIndex(0);
         }
         } catch (Exception e) {

         }*/
    }//GEN-LAST:event_debit_account_codeItemStateChanged

    private void descriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFocusLost
        credit_description.setText(description.getText());
        debit_description.setText(description.getText());
    }//GEN-LAST:event_descriptionFocusLost

    private void descriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionKeyPressed
        interface_events.Change_focus_Enterkey_c(debit_account_code, evt);
    }//GEN-LAST:event_descriptionKeyPressed

    private void credit_account_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_account_codeKeyPressed
        /*  if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         credit_description.requestFocusInWindow();
         }*/
    }//GEN-LAST:event_credit_account_codeKeyPressed

    private void debit_tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_tableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            save.setEnabled(false);
            /*   double tot = 0;
             int i = 0;
             while (debit_table.getValueAt(i, 0) != null) {
             tot = tot + Double.parseDouble((String) debit_table.getValueAt(i, 2));
             i++;
             }
             debit_total.setText(String.format("%.2f", tot));
             difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));

             if (Double.parseDouble(difference.getText()) == 0) {

             if (ref_no.getText().length() != 0 && debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
             if (chkd == 1) {
             save.setEnabled(true);
             save.requestFocus();
             }

             }
             } else {
             msg.showMessage("There is a Difference", "Journal Entry", "info");
             credit_account_code.requestFocus();
             }*/

            double tot = 0;
            int i = 0;
            while (debit_table.getValueAt(i, 0) != null) {
                tot = tot + Double.parseDouble(format.getNumberWithoutCommas((String) debit_table.getValueAt(i, 2)));
                tot = Math.round(tot * 100.0) / 100.0;
                i++;
            }
            //   debit_total.setText(String.format("%.2f", tot));
            //   difference.setText(String.format("%.2f", (stringToDoubleNum(debit_total.getText()) - stringToDoubleNum(credit_total.getText()))));
            NumberFormat formatter = new DecimalFormat("#0.00");
            debit_total.setText(format.modify_number(format.set_comma(formatter.format(tot))));
            //// debit_total.setText(format.modify_number(format.set_comma("" + tot)));
            double dif = Math.round((stringToDoubleNum(format.getNumberWithoutCommas(debit_total.getText())) - stringToDoubleNum(format.getNumberWithoutCommas(credit_total.getText()))) * 100.0) / 100.0;
            ////  difference.setText(format.modify_number(format.set_comma("" +dif )));

            difference.setText(format.modify_number(format.set_comma(formatter.format(dif))));
            ///////
            if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) == 0) {

                if (debit_table.getValueAt(0, 0) != null && credit_table.getValueAt(0, 0) != null) {
                    if (chkd == 1) {
                        save.setEnabled(true);
                        save.requestFocus();
                    }

                }
            } else {
                if (Double.parseDouble(format.getNumberWithoutCommas(difference.getText())) > 0) {
                    credit_account_code.requestFocus();
                } else {
                    debit_account_code.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_debit_tableKeyPressed

    private void differenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_differenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_differenceActionPerformed

    private void debit_amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_debit_amountKeyReleased
        if (chk.isDouble(format.getNumberWithoutCommas(debit_amount.getText())) || debit_amount.getText().length() == 0) {

            String num = format.getNumberWithoutCommas(debit_amount.getText());
            if (num.length() != 0) {
                debit_amount.setText(format.set_comma(num));
            }
        } else {
            msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
            debit_amount.setText(format.remove_last_char(debit_amount.getText()));
            debit_amount.requestFocus();
        }
    }//GEN-LAST:event_debit_amountKeyReleased

    private void credit_amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_credit_amountKeyReleased
        if (chk.isDouble(format.getNumberWithoutCommas(credit_amount.getText())) || credit_amount.getText().length() == 0) {

            String num = format.getNumberWithoutCommas(credit_amount.getText());
            if (num.length() != 0) {
                credit_amount.setText(format.set_comma(num));
            }
        } else {
            msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
            credit_amount.setText(format.remove_last_char(credit_amount.getText()));
            credit_amount.requestFocus();
        }
    }//GEN-LAST:event_credit_amountKeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete Entry", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {

            dbm.CheckNDeleteFromDataBase("account_journal_main", "tr_no", tr_no);
            dbm.CheckNDeleteFromDataBase("account_journal_debitside", "tr_no", tr_no);
            dbm.CheckNDeleteFromDataBase("account_journal_creditside", "tr_no", tr_no);
            
            dbm.CheckNDeleteFromDataBase("account_journal", "tr_no", tr_no);

            ref_no.setText(null);
            //journal_no.setText(null);
            pay_type.setSelectedIndex(0);
            bank_code.setSelectedIndex(0);
            branch_code.setSelectedIndex(0);
            bank_name.setText(null);
            branch_name.setText(null);
            chequeNo.setText(null);
            debit_account_code.setSelectedIndex(0);
            debit_description.setText(null);
            debit_amount.setText(null);
            debit_account_name.setText(null);
            credit_account_code.setSelectedIndex(0);
            credit_account_name.setText(null);
            credit_description.setText(null);
            credit_amount.setText(null);
            debit_total.setText(null);
            credit_total.setText(null);
            difference.setText(null);
            description.setText(null);

            // clear credit table all
            {
                int j = 0;
                while (credit_table.getValueAt(j, 0) != null) {
                    credit_table.setValueAt(null, j, 0);
                    credit_table.setValueAt(null, j, 1);
                    credit_table.setValueAt(null, j, 2);
                    j++;
                }
            }

            // clear debit table all
            {
                int j = 0;
                while (debit_table.getValueAt(j, 0) != null) {
                    debit_table.setValueAt(null, j, 0);
                    debit_table.setValueAt(null, j, 1);
                    debit_table.setValueAt(null, j, 2);
                    j++;
                }
            }

        }
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cheque_pay;
    private javax.swing.JComboBox bank_code;
    private javax.swing.JLabel bank_name;
    private javax.swing.JComboBox branch_code;
    private javax.swing.JLabel branch_name;
    private com.michaelbaranov.microba.calendar.DatePicker chequeDate;
    private javax.swing.JTextField chequeNo;
    private javax.swing.JComboBox credit_account_code;
    private javax.swing.JTable credit_account_code_table;
    private javax.swing.JLabel credit_account_name;
    private javax.swing.JTextField credit_amount;
    private javax.swing.JTextField credit_description;
    private javax.swing.JTable credit_table;
    private javax.swing.JTextField credit_total;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JTextField dayfield;
    private javax.swing.JComboBox debit_account_code;
    private javax.swing.JLabel debit_account_name;
    private javax.swing.JTextField debit_amount;
    private javax.swing.JTextField debit_description;
    private javax.swing.JTable debit_table;
    private javax.swing.JTextField debit_total;
    private javax.swing.JTextField description;
    private javax.swing.JTextField difference;
    private javax.swing.JLabel editLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JComboBox pay_type;
    private javax.swing.JTextField ref_no;
    private javax.swing.JButton save;
    private javax.swing.JButton send;
    private javax.swing.JButton send2;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
