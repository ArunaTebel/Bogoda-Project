
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
public class Report_Acc_Payments extends javax.swing.JPanel {

    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    DatabaseManager dbm =  DatabaseManager.getDbCon();
    Report_gen generate = new Report_gen();

    /**
     * Creates new form Report_Acc_Payments
     */
    public Report_Acc_Payments() {
        initComponents();
        jPanel5.setVisible(false);
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

                Thread a = new Thread(new Background());

                if (!andbutton.isSelected()) {

                    if (field_choice1.getSelectedItem().toString() == "All") {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());

                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                        generate.create("Account Payments", "D:\\", param, location, "Account Payment All.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (field_choice1.getSelectedItem().toString() == "Date") {
                        try {
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                            a.start();
                            HashMap param = new HashMap();
                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                            param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));

                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                            generate.create("Account Payments", "D:\\", param, location, "Account Payment Date.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (field_choice1.getSelectedItem().toString() == "Transaction No.") {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", field.getText());
                        // jProgressBar1.setValue(45);
                        //jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Tr.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", Return_String_Field(search.getText()));
                        param.put("b", field.getText());
                        //jProgressBar1.setValue(45);
                        //jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        //a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    }
                } else {
                    //////// When andbutton is not activated

                    if (field_choice1.getSelectedItem().toString() == "Date") {
                        if (field_choice2.getSelectedItem().toString() == "Transaction No.") {
                            try {
                                jProgressBar1.setValue(45);
                                jProgressBar1.repaint();
                                a.start();
                                HashMap param = new HashMap();
                                param.put("USER", new UserAccountControl().get_current_user());
                                param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                                param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                                param.put("a", field1.getText());
                               // jProgressBar1.setValue(45);
                                // jProgressBar1.repaint();
                                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                                // a.start();
                                generate.create("Account Payments", "D:\\", param, location, "Account Payment Date And Tr.jrxml");
                                a.stop();
                                ;
                                jProgressBar1.setValue(100);
                            } catch (ParseException ex) {
                                Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                jProgressBar1.setValue(45);
                                jProgressBar1.repaint();
                                a.start();
                                HashMap param = new HashMap();
                                param.put("USER", new UserAccountControl().get_current_user());
                                param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                                param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                                param.put("a", Return_String_Field(search1.getText()));
                                param.put("b", field1.getText());
                               // jProgressBar1.setValue(45);
                                // jProgressBar1.repaint();
                                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                                // a.start();
                                generate.create("Account Payments", "D:\\", param, location, "Account Payment Date And Normal.jrxml");
                                a.stop();
                                ;
                                jProgressBar1.setValue(100);
                            } catch (ParseException ex) {
                                Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else if (field_choice1.getSelectedItem().toString() == "Transaction No.") {

                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", field.getText());
                        param.put("b", Return_String_Field(search1.getText()));
                        param.put("c", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Tr And Normal.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (field_choice2.getSelectedItem().toString() == "Transaction No.") {

                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("c", field.getText());
                        param.put("b", Return_String_Field(search.getText()));
                        param.put("a", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        //a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Tr And Normal.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();

                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", Return_String_Field(search.getText()));
                        param.put("b", field.getText());
                        param.put("c", Return_String_Field(search1.getText()));
                        param.put("d", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment AndNormal.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);

                    }

                }
            } else {

                Thread a = new Thread(new Background());

                if (!andbutton.isSelected()) {

                    if (field_choice1.getSelectedItem().toString() == "All") {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Allall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (field_choice1.getSelectedItem().toString() == "Date") {
                        try {
                            jProgressBar1.setValue(45);
                            jProgressBar1.repaint();
                            a.start();
                            HashMap param = new HashMap();
                            param.put("USER", new UserAccountControl().get_current_user());
                            param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                            param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                           // jProgressBar1.setValue(45);
                            // jProgressBar1.repaint();
                            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                            // a.start();
                            generate.create("Account Payments", "D:\\", param, location, "Account Payment Dateall.jrxml");
                            a.stop();
                            ;
                            jProgressBar1.setValue(100);
                        } catch (ParseException ex) {
                            Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (field_choice1.getSelectedItem().toString() == "Transaction No.") {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", field.getText());
                        //jProgressBar1.setValue(45);
                        //jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        //a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Trall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else {
                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", Return_String_Field(search.getText()));
                        param.put("b", field.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Paymentall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    }
                } else {
                    //////// When andbutton is not activated

                    if (field_choice1.getSelectedItem().toString() == "Date") {
                        if (field_choice2.getSelectedItem().toString() == "Transaction No.") {
                            try {
                                jProgressBar1.setValue(45);
                                jProgressBar1.repaint();
                                a.start();
                                HashMap param = new HashMap();
                                param.put("USER", new UserAccountControl().get_current_user());
                                param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                                param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                                param.put("a", field1.getText());
                               // jProgressBar1.setValue(45);
                                // jProgressBar1.repaint();
                                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                                //  a.start();
                                generate.create("Account Payments", "D:\\", param, location, "Account Payment Date And Trall.jrxml");
                                a.stop();
                                ;
                                jProgressBar1.setValue(100);
                            } catch (ParseException ex) {
                                Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                jProgressBar1.setValue(45);
                                jProgressBar1.repaint();
                                a.start();
                                HashMap param = new HashMap();
                                param.put("USER", new UserAccountControl().get_current_user());
                                param.put("Date1", datechooser.Return_date(yearfield, monthfield, dayfield));
                                param.put("Date2", datechooser.Return_date(yearfield2, monthfield2, dayfield2));
                                param.put("a", Return_String_Field(search1.getText()));
                                param.put("b", field1.getText());
                             //   jProgressBar1.setValue(45);
                                //  jProgressBar1.repaint();
                                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                                //  a.start();
                                generate.create("Account Payments", "D:\\", param, location, "Account Payment Date And Normalall.jrxml");
                                a.stop();
                                ;
                                jProgressBar1.setValue(100);
                            } catch (ParseException ex) {
                                Logger.getLogger(Report_Acc_Payments.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else if (field_choice1.getSelectedItem().toString() == "Transaction No.") {

                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", field.getText());
                        param.put("b", Return_String_Field(search1.getText()));
                        param.put("c", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Tr And Normalall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else if (field_choice2.getSelectedItem().toString() == "Transaction No.") {

                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("c", field.getText());
                        param.put("b", Return_String_Field(search.getText()));
                        param.put("a", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment Tr And Normalall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);
                    } else {

                        jProgressBar1.setValue(45);
                        jProgressBar1.repaint();
                        a.start();
                        HashMap param = new HashMap();
                        param.put("USER", new UserAccountControl().get_current_user());
                        param.put("a", Return_String_Field(search.getText()));
                        param.put("b", field.getText());
                        param.put("c", Return_String_Field(search1.getText()));
                        param.put("d", field1.getText());
                       // jProgressBar1.setValue(45);
                        // jProgressBar1.repaint();
                        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                        // a.start();
                        generate.create("Account Payments", "D:\\", param, location, "Account Payment AndNormalall.jrxml");
                        a.stop();
                        ;
                        jProgressBar1.setValue(100);

                    }

                }

            }
        }
    }
    DateChooser_text datechooser = new DateChooser_text();

    Date_Handler datehandler = new Date_Handler();

    String[] combo = new String[10];

    public void Set_Combo() {

        String s;
        String[] arr2 = new String[10];

        String[] arr = new String[11];
        arr[0] = null;
        arr[1] = "Transaction No.";
        arr[2] = "Date";
        arr[3] = "Payment No.";
        arr[4] = "Reference No.";
        arr[5] = "Debit Account ID";
        arr[6] = "Debit Description";
        arr[7] = "Debit Amount";
        arr[8] = "Credit Account ID";
        arr[9] = "Credit Account Description";
        arr[10] = "Credit Amount";

        if (field_choice1.getSelectedIndex() != 0) {
            int i = 0;
            int j = field_choice1.getSelectedIndex() - 1;
            for (i = 0; i < j; i++) {
                arr2[i] = arr[i];
            }
            for (i = j + 1; i < 11; i++) {
                arr2[i - 1] = arr[i];
            }
            combo = arr2;
        } else {
            System.out.println("aaaa");

        }

    }

    public String Return_String_Field(String s) {
        switch (s) {
            case "Transaction No.":
                return "tr_no";
            case "Date":
                return "date";
            case "Payment No.":
                return "payment_no";
            case "Reference No.":
                return "ref_no";
            case "Debit Account ID":
                return "debit_account_id";
            case "Debit Description":
                return "debit_description";
            case "Debit Amount":
                return "debit_amount";
            case "Credit Account ID":
                return "credit_account_id";
            case "Credit Description":
                return "credit_description";
            case "Credit Amount":
                return "credit_amount";
            default:
                return null;
        }

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
        field_choice1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        field = new javax.swing.JTextField();
        search = new javax.swing.JLabel();
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
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        field_choice2 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        field1 = new javax.swing.JTextField();
        search1 = new javax.swing.JLabel();
        andbutton = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        field_choice1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "All", "Transaction No.", "Date", "Payment No.", "Reference No.", "Debit Account ID", "Debit Description", "Debit Amount", "Credit Account ID", "Credit Description", "Credit Amount" }));
        field_choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                field_choice1ItemStateChanged(evt);
            }
        });
        field_choice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_choice1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Select Search filters");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(field_choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        field_choice2.setModel(new javax.swing.DefaultComboBoxModel(combo));
        field_choice2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                field_choice2ItemStateChanged(evt);
            }
        });
        field_choice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_choice2ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(field_choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        andbutton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        andbutton.setText("And");
        andbutton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                andbuttonItemStateChanged(evt);
            }
        });
        andbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andbuttonActionPerformed(evt);
            }
        });

        jButton1.setText("Veiw");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Payments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(andbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(andbutton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(205, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void field_choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_field_choice1ItemStateChanged
        if ("Date".equals(field_choice1.getSelectedItem().toString())) {
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
        } else {
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
        }
        search.setText(field_choice1.getSelectedItem().toString());

        dayfield.requestFocusInWindow();
        dayfield.selectAll();

        Set_Combo();

    }//GEN-LAST:event_field_choice1ItemStateChanged

    private void field_choice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_choice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_choice1ActionPerformed

    private void fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldActionPerformed

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

    private void field_choice2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_field_choice2ItemStateChanged

        search1.setText(field_choice2.getSelectedItem().toString());

    }//GEN-LAST:event_field_choice2ItemStateChanged

    private void field_choice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_choice2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_choice2ActionPerformed

    private void field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field1ActionPerformed

    private void andbuttonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_andbuttonItemStateChanged
        if (andbutton.isSelected()) {

            jPanel5.setVisible(true);
            Set_Combo();
        } else {
            jPanel5.setVisible(false);
        }
    }//GEN-LAST:event_andbuttonItemStateChanged

    private void andbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andbuttonActionPerformed
        field_choice2.setModel(new javax.swing.DefaultComboBoxModel(combo));
    }//GEN-LAST:event_andbuttonActionPerformed

    ACC_Payment_View_Table tbl = new ACC_Payment_View_Table();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*  tbl.Clear_Table();

         tbl.setVisible(true);
         tbl.setExtendedState(ACC_Payment_View_Table.MAXIMIZED_BOTH);
         if (search.getText() == "All") {
         tbl.Table_Fill_All();

         } else if (andbutton.isSelected()) {
         if ("Date".equals(search.getText())) {
         if ((search1.getText() == "Credit Account ID" || search1.getText() == "Credit Description" || search1.getText() == "Credit Amount")) {
         try {
         tbl.Table_Fill_Date_Credit_Search(Return_String_Field(search.getText()), datechooser.Return_date(yearfield, monthfield, dayfield), datechooser.Return_date(yearfield2, monthfield2, dayfield2), Return_String_Field(search1.getText()), field1.getText());
         } catch (ParseException ex) {
         Logger.getLogger(ACC_Payment_View.class.getName()).log(Level.SEVERE, null, ex);
         }
         } else {
         try {
         tbl.Table_Fill_Date_Debit_Search(Return_String_Field(search.getText()), datechooser.Return_date(yearfield, monthfield, dayfield), datechooser.Return_date(yearfield2, monthfield2, dayfield2), Return_String_Field(search1.getText()), field1.getText());
         } catch (ParseException ex) {
         Logger.getLogger(ACC_Payment_View.class.getName()).log(Level.SEVERE, null, ex);
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
         Logger.getLogger(ACC_Payment_View.class.getName()).log(Level.SEVERE, null, ex);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton andbutton;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private javax.swing.JPanel datepanel;
    private javax.swing.JPanel datepanel2;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField dayfield2;
    private javax.swing.JTextField field;
    private javax.swing.JTextField field1;
    private javax.swing.JComboBox field_choice1;
    private javax.swing.JComboBox field_choice2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JLabel search;
    private javax.swing.JLabel search1;
    private javax.swing.JTextField yearfield;
    private javax.swing.JTextField yearfield2;
    // End of variables declaration//GEN-END:variables
}
