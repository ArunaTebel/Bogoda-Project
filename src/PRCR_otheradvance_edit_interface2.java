
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dhananjaya
 */
public class PRCR_otheradvance_edit_interface2 extends javax.swing.JFrame {
Interface_Events interface_events=new Interface_Events();
    Date_Handler datehandler = new Date_Handler();
    DateChooser_text datechooser = new DateChooser_text();
    DatabaseManager dbm = DatabaseManager.getDbCon();
    private int rows = 0;
    
    
    String date1;
    String day;
    String month;
    String year;
    String cash;
    String workCodee;
    int entry;//SHOULD SET THE ENTRY WHEN THIS IS CALLED FROM OUT SIDE
    int empCode;
    
     public void setEntry(int entry){
        this.entry=entry;
    }
    
    public void fill_data(int entry) {//get the data from workentry for given entry number,will be called where the object is created
        date1 = dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "date");
        dayfield1.setText(Integer.parseInt(date1.substring(8,10))+"");
        monthfield1.setText(datehandler.Return_month(Integer.parseInt(date1.substring(5,7))));
        yearfield1.setText(date1.substring(0,4));
        
        empCode=Integer.parseInt(dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "code"));
        empCode_JC.setSelectedItem(empCode);
        
        if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("TEA")){
                advance_type.setSelectedItem("TEA");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FESTIVAL")){
                advance_type.setSelectedItem("FESTIVAL");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FOODSTUFFS")){
                advance_type.setSelectedItem("FOODSTUFFS");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("LOAN")){
               advance_type.setSelectedItem("LOAN");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("CEB")){
                advance_type.setSelectedItem("CEB");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("TEACHER")){
                advance_type.setSelectedItem("TEACHER");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("CHEMICAL")){
                advance_type.setSelectedItem("CHEMICAL");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("PAYSLIP")){
                advance_type.setSelectedItem("PAYSLIP");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("FINE")){
                advance_type.setSelectedItem("FINE");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("MEALS")){
                advance_type.setSelectedItem("MEALS");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("PENSION")){
               advance_type.setSelectedItem("PENSION");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("WELFARE")){
                advance_type.setSelectedItem("WELFARE");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("KOVIL")){
                advance_type.setSelectedItem("KOVIL");
            }
            else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("OTHER_1")){
                advance_type.setSelectedItem("OTHER_1");
            }
              else if(dbm.checknReturnData("prcr_other_advance_book", "entry",entry ,"type").toString().equals("OTHER_2")){
                advance_type.setSelectedItem("OTHER_2");
            }
        
        cash=dbm.checknReturnData("prcr_other_advance_book", "entry", entry, "amount");
        advance_amount.setText(cash);
    }
    /**
     * Creates new form PRCR_otheradvance_edit_interface2
     */
    public PRCR_otheradvance_edit_interface2() {
        initComponents();
        try{
         this.setIconImage(new ImageIcon(getClass().getResource("Iconpng.png")).getImage());
        }catch(Exception e){
            
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

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        empCode_JC = new javax.swing.JComboBox();
        empName2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        division_jc = new javax.swing.JComboBox();
        datepanel = new javax.swing.JPanel();
        monthfield1 = new javax.swing.JTextField();
        yearfield1 = new javax.swing.JTextField();
        dayfield1 = new javax.swing.JTextField();
        datePick1 = new com.michaelbaranov.microba.calendar.DatePicker();
        advance_type = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Advance_type = new javax.swing.JTextField();
        division_lb = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        advance_amount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        work_code = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton3.setText("Clear selected row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Division");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Employee Code");

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

        jLabel5.setText("Employee Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(empCode_JC, 0, 59, Short.MAX_VALUE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(empName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(empCode_JC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel6.setText("Advance Type");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Advance Type", "Amount", "division"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(table);

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

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield1.setText(datehandler.get_today_month());
        monthfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfield1KeyPressed(evt);
            }
        });

        yearfield1.setText(datehandler.get_today_year());
        yearfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfield1KeyPressed(evt);
            }
        });

        dayfield1.setText(Integer.parseInt(datehandler.get_today_day())+"");
        dayfield1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dayfield1KeyPressed(evt);
            }
        });

        datePick1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePick1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanelLayout = new javax.swing.GroupLayout(datepanel);
        datepanel.setLayout(datepanelLayout);
        datepanelLayout.setHorizontalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(monthfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePick1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanelLayout.setVerticalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePick1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        advance_type.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        advance_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TEA", "FESTIVAL", "FOODSTUFFS", "LOAN", "CEB", "TEACHER", "CHEMICAL", "PAYSLIP", "FINE", "MEALS", "PENSION", "WELFARE", "KOVIL", "OTHER_1", "OTHER_2" }));
        advance_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                advance_typeItemStateChanged(evt);
            }
        });
        advance_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advance_typeActionPerformed(evt);
            }
        });
        advance_type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                advance_typeKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        jButton6.setText("EDIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancel");

        jButton8.setText("Clear Table");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8))
                    .addComponent(jButton6))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel7.setText("Date");

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });

        Advance_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Advance_typeActionPerformed(evt);
            }
        });
        Advance_type.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Advance_typeKeyPressed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        advance_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advance_amountActionPerformed(evt);
            }
        });
        advance_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                advance_amountKeyPressed(evt);
            }
        });

        jLabel10.setText("Amount");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(advance_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advance_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Advance_type, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(advance_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel7)
                    .addGap(8, 8, 8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel3)
                            .addGap(34, 34, 34)
                            .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(advance_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Advance_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jButton1))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(42, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jLabel7))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3)
                            .addGap(30, 30, 30)
                            .addComponent(division_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(work_code, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(division_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(410, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void ClearTable(){
         int rowsh = 0;
         int column=0;
         int k =table.getRowCount();
            while(table.getValueAt(rowsh,0)!=null) {
                for(column=0;column<5;column++){
                    table.setValueAt(null, rowsh, column);
                }
                rowsh++;
            
            }
            this.rows=0;//must set rows=0 because rows is incremented by send button
            
            System.out.println("table deleted");
    }
    
    public void ClearSelectedRow(){
           int selectedIndex=table.getSelectedRow();
        int filledRows=0;
        
        while(table.getValueAt(filledRows,0)!=null){
            filledRows++;
        }
        
        int i;
       for(i=selectedIndex;i<filledRows;i++){
           table.setValueAt( table.getValueAt(i+1, 0) ,i  ,0  );
            table.setValueAt( table.getValueAt(i+1, 1) ,i  ,1  );
        table.setValueAt( table.getValueAt(i+1, 2) ,i  ,2  );
         table.setValueAt( table.getValueAt(i+1, 3) ,i  ,3  );
          table.setValueAt( table.getValueAt(i+1, 4) ,i  ,4  );
           //table.setValueAt( table.getValueAt(i+1, 5) ,i  ,5  );
       }
        System.out.println(filledRows);
        rows=filledRows-1;
        
    }

    
    private void saveDataToAdvanceBook(Date date){
        
        
                
                  dbm.updateDatabase("prcr_other_advance_book", "entry", entry,"date",date);
                   dbm.updateDatabase("prcr_other_advance_book", "entry", entry,"code",table.getValueAt(0, 0));
                   dbm.updateDatabase("prcr_other_advance_book", "entry", entry,"type",table.getValueAt(0, 2));
                   dbm.updateDatabase("prcr_other_advance_book", "entry", entry,"amount",table.getValueAt(0, 3));

    }
    
    private void saveData(String tablename, Date tdate) {
      //  String division = null;
      //  String work_code = null;
       // division = (String) division_jc.getSelectedItem();
      // work_code = (String) workCode.getSelectedItem();
    
    
       
            int rowsd = 0;
            System.out.println("s="+table.getValueAt(rowsd, 0));
            
            
          
                while(table.getValueAt(rowsd, 0) != null) {
                                            System.out.println("in="+table.getValueAt(rowsd, 0));               //get the current advance amount from data base,add the new amount to it,update total amount to the workdata table
                    if(table.getValueAt(rowsd, 2).toString().equals("TEA")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"tea" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("FESTIVAL")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"fest_adv" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("FOODSTUFFS")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"food" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("LOAN")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"loan" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("CEB")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"ceb" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("TEACHER")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"teacher" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("CHEMICAL")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"chemical" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("PAYSLIP")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"pay_slip" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("FINE")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"fine" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("MEALS")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"meals" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("PENSION")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"pension" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("WELFARE")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"welfare" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("KOVIL")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"kovil" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("OTHER_1")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"new_1" , (String) table.getValueAt(rowsd, 3));}
                    if(table.getValueAt(rowsd, 2).toString().equals("OTHER_2")){Update_pr_workData(tablename, table.getValueAt(rowsd,0),"new_2" , (String) table.getValueAt(rowsd, 3));}
                    rowsd++;
                }
            
           
                  
    }
                                      //Function for the savedata()//column-column that need to be updated,amount entered  
public void Update_pr_workData(String tablename,Object code,String column,String new_amount){

     double amount_u = 0;

                    if (dbm.checknReturnData("pr_workdata_" + tablename, "code", code, column) != null) {
                        amount_u= Double.parseDouble(dbm.checknReturnData("pr_workdata_" + tablename, "code", code, column));
                    } else {
                        amount_u= 0;
                    }
                    if (new_amount != null) {
                        amount_u = amount_u + Double.parseDouble(new_amount);
                    } else {
                        amount_u=amount_u;
                    }
                    dbm.updateDatabase("pr_workdata_" + tablename, "code", code, column, amount_u);


}
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ClearSelectedRow(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            empName2.setText("" + Name);
        }

        advance_amount.requestFocus();
        //  W_code.selectAll();
    }//GEN-LAST:event_empCode_JCItemStateChanged

    private void empCode_JCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empCode_JCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCActionPerformed

    private void empCode_JCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empCode_JCKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            advance_amount.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_empCode_JCKeyPressed

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

            division_lb.setText("" + Name);
        }

        Advance_type.requestFocus();
    }//GEN-LAST:event_division_jcItemStateChanged

    private void division_jcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_division_jcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            Advance_type.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_division_jcKeyPressed

    private void monthfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfield1KeyPressed
        if (monthfield1.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Dec");
                int yr = Integer.parseInt(yearfield1.getText());

                yearfield1.setText("" + (yr - 1));
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Feb");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jan");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Mar");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Feb");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Apr");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Mar");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("May");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Apr");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                monthfield1.setText("Jun");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("May");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Jul");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jun");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Aug");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Jul");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Sep");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Aug");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Oct");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Sep");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Nov");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Oct");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Dec");
                monthfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield1.setText("Nov");
                int yr = Integer.parseInt(yearfield1.getText());
                monthfield1.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield1.setText("Jan");
                int yr = Integer.parseInt(yearfield1.getText());

                yearfield1.setText("" + (yr + 1));
                monthfield1.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            dayfield1.requestFocus();
            dayfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield1.requestFocus();
            yearfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //category_code.requestFocus();

        }
    }//GEN-LAST:event_monthfield1KeyPressed

    private void yearfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfield1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield1.setText("" + (Integer.parseInt(yearfield1.getText()) + 1));
            yearfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield1.setText("" + (Integer.parseInt(yearfield1.getText()) - 1));
            yearfield1.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield1.requestFocus();
            monthfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  category_code.requestFocus();

        }
    }//GEN-LAST:event_yearfield1KeyPressed

    private void dayfield1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dayfield1KeyPressed
        ///////////////////////////////////////////////////  Days Decrement/////////////////////////////////////////////////////////////////////////////

        if (dayfield1.getText().equals("1")) {           // Jumping to 31 and 30 from 1st
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                if (monthfield1.getText().equals("Feb") || monthfield1.getText().equals("Apr") || monthfield1.getText().equals("Jun") || monthfield1.getText().equals("Aug") || monthfield1.getText().equals("Sep") || monthfield1.getText().equals("Nov") || monthfield1.getText().equals("Feb")) {
                    dayfield1.setText("31");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("May") || monthfield1.getText().equals("Jul") || monthfield1.getText().equals("Oct") || monthfield1.getText().equals("Dec")) {
                    dayfield1.setText("30");
                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("Mar")) {     // from march 1st jump to 28th or 29th checking leap years
                    int yr = Integer.parseInt(yearfield1.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield1.setText("29"); // Leap Year
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield1.setText("28"); // not a leap year
                            }
                        }
                        dayfield1.setText("29");       // leap year

                    }
                    if (yr % 4 != 0) {
                        dayfield1.setText("28");       // not a leap year
                    }
                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth - 1));

                } else if (monthfield1.getText().equals("Jan")) {            // From jan 1st jump to december 31st decrementing year
                    dayfield1.setText("31");

                    int yr = Integer.parseInt(yearfield1.getText());
                    monthfield1.setText("Dec");
                    yearfield1.setText("" + (yr - 1));    // year
                }
                dayfield1.selectAll();
            }                                           // /// decrementing normal values
        } else if (dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
            || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
            || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
            || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
            || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
            || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
            || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) - 1));
                dayfield1.selectAll();
            }
        }
        /////////////////////////////////////////////////  Days Increment///////////////////////////////////////////////////////////////////////////////////////////////////
        if (dayfield1.getText().equals("30")) {               // from 30th to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield1.getText().equals("Apr") || monthfield1.getText().equals("Jun") || monthfield1.getText().equals("Sep") || monthfield1.getText().equals("Nov")) {
                    dayfield1.setText("0");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));

                }
                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));
                dayfield1.selectAll();
            }

        } else if (dayfield1.getText().equals("31")) {            // from 31st to 1st of next month
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                if (monthfield1.getText().equals("Jan") || monthfield1.getText().equals("Mar") || monthfield1.getText().equals("May") || monthfield1.getText().equals("Jul") || monthfield1.getText().equals("Aug") || monthfield1.getText().equals("Oct")) {
                    dayfield1.setText("1");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));

                } else if (monthfield1.getText().equals("Dec")) {      // December to january incrementing the year

                    dayfield1.setText("1");

                    int yr = Integer.parseInt(yearfield1.getText());
                    monthfield1.setText("Jan");
                    yearfield1.setText("" + (yr + 1));
                }
                dayfield1.selectAll();
            }

        } else if (monthfield1.getText().equals("Feb")) {                    // for february
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                if (dayfield1.getText().equals("28")) {                    // at 28 check for leap year
                    int yr = Integer.parseInt(yearfield1.getText());
                    if (yr % 4 == 0) {
                        if (yr % 100 == 0) {
                            if (yr % 400 == 0) {
                                dayfield1.setText("29"); // Leap Year       // increment to 29
                            }
                        }
                        if (yr % 100 == 0) {
                            if (yr % 400 != 0) {
                                dayfield1.setText("1");
                                int mnth = datechooser.return_index(monthfield1.getText());
                                monthfield1.setText(datechooser.Return_month(mnth + 1));

                                // not a leap year                             // jump to next month
                            }
                        }
                        dayfield1.setText("29");       // leap year             // increment to 29th

                    }
                    if (yr % 4 != 0) {
                        dayfield1.setText("1");
                        int mnth = datechooser.return_index(monthfield1.getText());
                        monthfield1.setText(datechooser.Return_month(mnth + 1));                  // not a leap year
                    }

                } else if (dayfield1.getText().equals("29")) {              // at 29 jump to next month normally
                    dayfield1.setText("1");

                    int mnth = datechooser.return_index(monthfield1.getText());
                    monthfield1.setText(datechooser.Return_month(mnth + 1));
                    // incrementing normal values/////////////////////// for february separately
                } else if (dayfield1.getText().equals("1") || dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
                    || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
                    || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
                    || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
                    || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
                    || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
                    || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {

                    dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));

                }
                dayfield1.selectAll();
            }
            // incrementing normal values
        } else if (dayfield1.getText().equals("1") || dayfield1.getText().equals("2") || dayfield1.getText().equals("3") || dayfield1.getText().equals("4") || dayfield1.getText().equals("5")
            || dayfield1.getText().equals("6") || dayfield1.getText().equals("7") || dayfield1.getText().equals("8") || dayfield1.getText().equals("9")
            || dayfield1.getText().equals("10") || dayfield1.getText().equals("11") || dayfield1.getText().equals("12") || dayfield1.getText().equals("13") || dayfield1.getText().equals("14")
            || dayfield1.getText().equals("15") || dayfield1.getText().equals("16") || dayfield1.getText().equals("17") || dayfield1.getText().equals("18")
            || dayfield1.getText().equals("19") || dayfield1.getText().equals("20") || dayfield1.getText().equals("21") || dayfield1.getText().equals("22")
            || dayfield1.getText().equals("23") || dayfield1.getText().equals("24") || dayfield1.getText().equals("25") || dayfield1.getText().equals("26")
            || dayfield1.getText().equals("27") || dayfield1.getText().equals("28") || dayfield1.getText().equals("29") || dayfield1.getText().equals("30") || dayfield1.getText().equals("31")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                dayfield1.setText("" + (Integer.parseInt(dayfield1.getText()) + 1));
                dayfield1.selectAll();

            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            monthfield1.requestFocus();
            monthfield1.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            division_jc.requestFocus();

        }
    }//GEN-LAST:event_dayfield1KeyPressed

    private void datePick1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePick1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePick1.getDate().getTime());

        dayfield1.setText(datehandler.get_day(datef));
        monthfield1.setText(datehandler.get_month(datef));
        yearfield1.setText(datehandler.get_year(datef));
        // category_code.requestFocus();
    }//GEN-LAST:event_datePick1ActionPerformed

    private void advance_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_advance_typeItemStateChanged
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
            work_code.setText("" + Name);
        }
        Advance_type.setText(advance_type.getSelectedItem().toString());
        // empCodeJC.requestFocus();
    }//GEN-LAST:event_advance_typeItemStateChanged

    private void advance_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advance_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_advance_typeActionPerformed

    private void advance_typeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advance_typeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //   empCodeJC.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_advance_typeKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //java.sql.Date tdate = new java.sql.Date(date.getDate().getTime());

        //addDateTable(tdate);//store data of workers worked in each division in each day   REMOVED INTENTIONALLY

        try{
        String month = null;
        String year = null;
        Date tdate=null;

        String ndate = null;
        try {
            tdate=datechooser.Return_date(yearfield1,monthfield1,dayfield1);
        } catch (ParseException ex) {
            Logger.getLogger(PRCR_Work_normal.class.getName()).log(Level.SEVERE, null, ex);
        }
        ndate = tdate.toString();
        StringBuilder ne_date = new StringBuilder(ndate);
        ne_date.setCharAt(4, '_');
        ne_date.setCharAt(7, '_');
        String new1_date = null;
        new1_date = ne_date.toString();
        String tablename = new1_date.substring(0, 7);

            //saveData(tablename, tdate);
            saveDataToAdvanceBook(tdate);
            ClearTable();
            JOptionPane.showMessageDialog(null, "Saved!\n", "Message", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Not saved!Error\n", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ClearTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            if(empCode_JC.getSelectedItem().toString().length()!=0){
                table.setValueAt(empCode_JC.getSelectedItem(), rows, 0);
                table.setValueAt(empName2.getText(), rows, 1);
                table.setValueAt(advance_type.getSelectedItem(), rows, 2);
                if (advance_amount.getText().length() == 0) {
                    table.setValueAt("0", rows, 3);
                    JOptionPane.showMessageDialog(null, "Enter amount\n", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    table.setValueAt(advance_amount.getText(), rows, 3);
                }

                rows++;
                // TODO add your handling code here:
                empCode_JC.setSelectedItem(null);
                advance_amount.setText(null);

                empCode_JC.requestFocus();
            }else{
                JOptionPane.showMessageDialog(null, "Enter the employ code\n", "Message", JOptionPane.INFORMATION_MESSAGE);
                empCode_JC.requestFocus();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter the employee code\n", "Message", JOptionPane.INFORMATION_MESSAGE);
            empCode_JC.requestFocus();
        }

        //Advance_type.requestFocus();
        //Advance_type.selectAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        interface_events.Respond_enter(jButton1, evt); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1FocusGained

    private void Advance_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Advance_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Advance_typeActionPerformed

    private void Advance_typeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Advance_typeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            advance_type.setSelectedItem(Advance_type.getText());
            System.out.println(Advance_type.getText());
            //   empCodeJC.requestFocus();
        }
    }//GEN-LAST:event_Advance_typeKeyPressed

    private void advance_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advance_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_advance_amountActionPerformed

    private void advance_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advance_amountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            jButton1.requestFocus();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_advance_amountKeyPressed

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
            java.util.logging.Logger.getLogger(PRCR_otheradvance_edit_interface2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PRCR_otheradvance_edit_interface2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PRCR_otheradvance_edit_interface2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PRCR_otheradvance_edit_interface2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PRCR_otheradvance_edit_interface2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Advance_type;
    private javax.swing.JTextField advance_amount;
    private javax.swing.JComboBox advance_type;
    private com.michaelbaranov.microba.calendar.DatePicker datePick1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JTextField dayfield1;
    private javax.swing.JComboBox division_jc;
    private javax.swing.JLabel division_lb;
    private javax.swing.JComboBox empCode_JC;
    private javax.swing.JLabel empName2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField monthfield1;
    private javax.swing.JTable table;
    private javax.swing.JLabel work_code;
    private javax.swing.JTextField yearfield1;
    // End of variables declaration//GEN-END:variables
}
