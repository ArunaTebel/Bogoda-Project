
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class Report_PRCR_Checkroll_work_details extends javax.swing.JPanel {

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
            if(employee.isSelected() && divisionbox.isSelected() && workCodebox.isSelected()){
                
                if (empCode_JC.getSelectedItem() == null || empCode_JC.getSelectedItem().toString() == ""||
                        division_jc.getSelectedItem() == null || division_jc.getSelectedItem().toString() == ""||
                            workCode.getSelectedItem() == null || workCode.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","emp_code");
                    param.put("a",empCode_JC.getSelectedItem().toString());
                    param.put("col2","division");
                    param.put("b",division_jc.getSelectedItem().toString());
                    param.put("col3","work_code");
                    param.put("c",workCode.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
            else if(employee.isSelected() && divisionbox.isSelected() && !workCodebox.isSelected()){
                
                if (empCode_JC.getSelectedItem() == null || empCode_JC.getSelectedItem().toString() == ""||
                        division_jc.getSelectedItem() == null || division_jc.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","emp_code");
                    param.put("a",empCode_JC.getSelectedItem().toString());
                    param.put("col2","emp_code");
                    param.put("b",empCode_JC.getSelectedItem().toString());
                    param.put("col3","emp_code");
                    param.put("c",empCode_JC.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
            else if(employee.isSelected() && !divisionbox.isSelected() && workCodebox.isSelected()){
                
                if (empCode_JC.getSelectedItem() == null || empCode_JC.getSelectedItem().toString() == ""||
                        
                            workCode.getSelectedItem() == null || workCode.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","emp_code");
                    param.put("a",empCode_JC.getSelectedItem().toString());
                    param.put("col2","emp_code");
                    param.put("b",empCode_JC.getSelectedItem().toString());
                    param.put("col3","work_code");
                    param.put("c",workCode.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
              
            else if(employee.isSelected() && !divisionbox.isSelected() && !workCodebox.isSelected()){
                
                if (empCode_JC.getSelectedItem() == null || empCode_JC.getSelectedItem().toString() == ""
                       
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","emp_code");
                    param.put("a",empCode_JC.getSelectedItem().toString());
                    param.put("col2","emp_code");
                    param.put("b",empCode_JC.getSelectedItem().toString());
                    param.put("col3","emp_code");
                    param.put("c",empCode_JC.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
            
                       else if(!employee.isSelected() && divisionbox.isSelected() && workCodebox.isSelected()){
                
                if (
                        division_jc.getSelectedItem() == null || division_jc.getSelectedItem().toString() == ""||
                            workCode.getSelectedItem() == null || workCode.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","division");
                    param.put("a",division_jc.getSelectedItem().toString());
                    param.put("col2","division");
                    param.put("b",division_jc.getSelectedItem().toString());
                    param.put("col3","work_code");
                    param.put("c",workCode.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
            
                       else if(!employee.isSelected() && divisionbox.isSelected() && !workCodebox.isSelected()){
                
                if (
                        division_jc.getSelectedItem() == null || division_jc.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","division");
                    param.put("a",division_jc.getSelectedItem().toString());
                    param.put("col2","division");
                    param.put("b",division_jc.getSelectedItem().toString());
                    param.put("col3","division");
                    param.put("c",division_jc.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
            
                       else if(!employee.isSelected() && !divisionbox.isSelected() && workCodebox.isSelected()){
                
                if (
                            workCode.getSelectedItem() == null || workCode.getSelectedItem().toString() == ""
                        
                        ) {
                    JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code And WorkCode");
                } else {
                
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());
                    param.put("col1","work_code");
                    param.put("a",workCode.getSelectedItem().toString());
                    param.put("col2","work_code");
                    param.put("b",workCode.getSelectedItem().toString());
                    param.put("col3","work_code");
                    param.put("c",workCode.getSelectedItem().toString());
                    

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("PRCR_Checkroll_Work_Details", "D:\\", param, location, "PRCR_Checkroll_Work_Details.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                } }
            
            }
                       else{
                           JOptionPane.showMessageDialog(datechooser, "Select Employee Code,Division Code or WorkCode");
                       }
            
            
            
        }

        private void viewMouseClicked(java.awt.event.MouseEvent evt) {
            // jLabel4.setText("please wait..");
        }

        private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {
            

        }
    }

    /**
     *
     */
    public Report_PRCR_Checkroll_work_details() {
        initComponents();
        jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
    DatabaseManager dbm = new DatabaseManager();

    public void focus() {
        dayfield.requestFocus();
        dayfield.selectAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        employee = new javax.swing.JCheckBox();
        empCode_JC = new javax.swing.JComboBox();
        EmpName = new javax.swing.JLabel();
        divisionbox = new javax.swing.JCheckBox();
        division_jc = new javax.swing.JComboBox();
        workCode = new javax.swing.JComboBox();
        workCodebox = new javax.swing.JCheckBox();
        view = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("From");

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

        dayfield.setText(Integer.parseInt(datehandler.get_today_day())+"");
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

        dayfield2.setText(Integer.parseInt(datehandler.get_today_day())+"");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setText("To");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Group by");

        employee.setText("Employee");
        employee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                employeeItemStateChanged(evt);
            }
        });
        employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeActionPerformed(evt);
            }
        });

        empCode_JC.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        empCode_JC.setEditable(true);
        empCode_JC.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("checkroll_personalinfo","code")));
        empCode_JC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                empCode_JCItemStateChanged(evt);
            }
        });
        empCode_JC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empCode_JCActionPerformed(evt);
            }
        });
        empCode_JC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                empCode_JCKeyPressed(evt);
            }
        });

        divisionbox.setText("Division");
        divisionbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                divisionboxItemStateChanged(evt);
            }
        });
        divisionbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionboxActionPerformed(evt);
            }
        });

        division_jc.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        division_jc.setEditable(true);
        division_jc.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("division_details", "code")));
        division_jc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                division_jcItemStateChanged(evt);
            }
        });
        division_jc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                division_jcKeyPressed(evt);
            }
        });

        workCode.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        workCode.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("workcode_details","code")));
        workCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                workCodeItemStateChanged(evt);
            }
        });
        workCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workCodeActionPerformed(evt);
            }
        });
        workCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                workCodeKeyPressed(evt);
            }
        });

        workCodebox.setText("Work Code");
        workCodebox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                workCodeboxItemStateChanged(evt);
            }
        });
        workCodebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workCodeboxActionPerformed(evt);
            }
        });

        view.setText("Veiw");
        view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewMouseClicked(evt);
            }
        });
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(divisionbox))
                                    .addGap(36, 36, 36))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(workCodebox)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(employee)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(workCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(67, 67, 67)))
                                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(empCode_JC, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(employee)
                        .addComponent(empCode_JC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(EmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(divisionbox)
                    .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workCodebox)
                    .addComponent(workCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Work Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
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
            // jButton1.requestFocus();

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
            //jButton1.requestFocus();

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
            // jButton1.requestFocus();

        }
    }//GEN-LAST:event_dayfield2KeyPressed

    private void datePicker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker3ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker3.getDate().getTime());

        dayfield2.setText(datehandler.get_day(datef));
        monthfield2.setText(datehandler.get_month(datef));
        yearfield2.setText(datehandler.get_year(datef));
        //jButton1.requestFocus();
    }//GEN-LAST:event_datePicker3ActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
       Thread b = new Thread(new report());
       b.start();
    }//GEN-LAST:event_viewActionPerformed

    private void viewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewMouseClicked
        // jLabel4.setText("please wait..");
    }//GEN-LAST:event_viewMouseClicked

    private void employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeActionPerformed

    private void employeeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_employeeItemStateChanged
        
    }//GEN-LAST:event_employeeItemStateChanged

    private void empCode_JCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_empCode_JCItemStateChanged
        DatabaseManager dbm = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int item = Integer.parseInt(evt.getItem().toString());
            try {
                ResultSet query = dbm.query("SELECT * FROM personal_info WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("name");
                }
            } catch (SQLException ex) {
            }
            EmpName.setText("" + Name);
        }

       
    }//GEN-LAST:event_empCode_JCItemStateChanged

    private void empCode_JCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empCode_JCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCActionPerformed

    private void empCode_JCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empCode_JCKeyPressed
              // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCKeyPressed

    private void divisionboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_divisionboxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_divisionboxItemStateChanged

    private void divisionboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_divisionboxActionPerformed

    private void division_jcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_division_jcItemStateChanged
        DatabaseManager dbma = DatabaseManager.getDbCon();
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = evt.getItem().toString();

            try {
                ResultSet query = dbma.query("SELECT * FROM division_details WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("division");
                    System.out.println(Name);
                }
            } catch (SQLException ex) {
                System.out.println("error");
            }

           
        }

        empCode_JC.requestFocus();

    }//GEN-LAST:event_division_jcItemStateChanged

    private void division_jcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_division_jcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            empCode_JC.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_division_jcKeyPressed

    private void workCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_workCodeItemStateChanged
        String Name = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String item = evt.getItem().toString();
            try {
                ResultSet query = dbm.query("SELECT * FROM workcode_details WHERE code =" + item + "");
                while (query.next()) {
                    Name = query.getString("work");
                }
            } catch (SQLException ex) {
            }
            
        }
       
    }//GEN-LAST:event_workCodeItemStateChanged

    private void workCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workCodeActionPerformed

    private void workCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workCodeKeyPressed
             // TODO add your handling code here:
    }//GEN-LAST:event_workCodeKeyPressed

    private void workCodeboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_workCodeboxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_workCodeboxItemStateChanged

    private void workCodeboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workCodeboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workCodeboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmpName;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private javax.swing.JPanel datepanel;
    private javax.swing.JPanel datepanel2;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField dayfield2;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JCheckBox divisionbox;
    private javax.swing.JComboBox empCode_JC;
    private javax.swing.JCheckBox employee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JButton view;
    private javax.swing.JComboBox workCode;
    private javax.swing.JCheckBox workCodebox;
    private javax.swing.JTextField yearfield;
    private javax.swing.JTextField yearfield2;
    // End of variables declaration//GEN-END:variables
}
