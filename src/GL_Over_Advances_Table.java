
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class GL_Over_Advances_Table extends javax.swing.JPanel {

    // GL_other_advances_class oadvance = new GL_other_advances_class();
    GL_Over_Advance overadvance = new GL_Over_Advance();
    Report_gen gen = new Report_gen();
    int i = 0;

    int no_of_pages;

    DatabaseManager dbm = DatabaseManager.getDbCon();
    int a = dbm.Checking_Length_Of_The_Table("gl_over_advance", "sup_id");
    int a_2 = 0;
    Interface_Events interface_events = new Interface_Events();
    Common_Other_Advance_Database_Handling filter = new Common_Other_Advance_Database_Handling();
    Table_handler table_handler = new Table_handler();
    Date_Handler datehandler = new Date_Handler();
    UserAccountControl user = new UserAccountControl();

    public void focus() {
        this.requestFocusInWindow();

    }

    public void set_table(int bottom, int top) {
        a = dbm.Checking_Length_Of_The_Table("gl_over_advance", "sup_id");
        
        //System.out.println(a+"-this is entries---");
        if (a % 50 == 0) {
            no_of_pages = a / 50;

        } else {
            no_of_pages = a / 50 + 1;

        }
        // System.out.println("num of pages"+no_of_pages);
        page_info.setText("Page 1 of" + " " + no_of_pages);
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "sup_id", 0, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "sup_name", 1, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "category_code", 2, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "cash_ad", 3, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "other_ad", 4, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "loans", 5, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "bal_bf", 6, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "set_val", 7, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "total_kg", 8, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "recovered", 9, bottom, top, "remain");
        dbm.Inserting_To_The_Table_ordered(table, "gl_over_advance", "remain", 10, bottom, top, "remain");

// System.out.println("sheeeeeeeeeeet");
    }

    /**
     * Creates new form GL_other_advance_save
     */
    public GL_Over_Advances_Table() {
        initComponents();
        set_val.setText(dbm.checknReturnStringData("rate_details", "Code_name", "GLSET", "rate") + "");
        margin.setText("0");
                
        table.setAutoCreateRowSorter(true);
       // supplier_id.setSelectedItem("All");
    }
   
    
    public class Cal implements Runnable{
    
     public void run(){
     table_handler.clear_table(table, 11, 50);
     progress.setMaximum(200);
     progress.setValue(2);
         try {
            try {
                dbm.insert("Truncate table gl_over_advance");
                 
            } catch (SQLException ex) {
                Logger.getLogger(GL_other_advance_save.class.getName()).log(Level.SEVERE, null, ex);

            }

            String year = yearfield3.getText();
            String month = datehandler.return_month_as_num(monthfield3.getText());
            String cyear = yearfield2.getText();
            String cmonth = datehandler.return_month_as_num(monthfield2.getText());

            Date date4;                      //INPUT MONTH AS NUMBER EX: 03

            Date date1 = java.sql.Date.valueOf(year + "-" + month + "-" + "08");

            if (month.equals("12")) {
                date4 = java.sql.Date.valueOf(String.valueOf(Integer.parseInt(year) + 1) + "-" + datehandler.get_next_month(month) + "-" + "07");
            } else {
                date4 = java.sql.Date.valueOf(year + "-" + datehandler.get_next_month(month) + "-" + "07");
            }
          //  System.out.println(date1 + "----->" + date4);

            ResultSet query = dbm.query("SELECT * FROM gl_cash_advance where  " + "ordered_date" + " BETWEEN'" + date1 + "' AND '" + date4 + "'");
            double[] out = new double[5];
            double[] out1 = new double[5];
            double[] out2 = new double[5];
            double[] out3 = new double[5];
            double[] outcurr = new double[5];
            int sup;
            double set = Double.parseDouble(set_val.getText());
            double remain = 0, remain2 = 0, remain3 = 0, remain4 = 0, remcurrent = 0;
            String name, cat;
            int kl = 0;
            while (query.next()&& kl<200) {
                sup = query.getInt("sup_id");
                outlable.setText("Processing "+sup);
                // name = query.getString("sup_name");
                // cat = query.getString("cat_id");
                
                // System.out.println(temp1[0]+"--"+temp1[1]);
                
                 outcurr = overadvance.calculate(sup, cyear, cmonth);
                 remcurrent = outcurr[0] * set - (outcurr[1] + outcurr[2] + outcurr[3] + outcurr[4]);
                 
                 if (remcurrent < Integer.parseInt(margin.getText())) {
                     
                    // outlable.setText("Processing "+sup);
                     name = dbm.checknReturnData("suppliers", "sup_id", sup, "sup_name");
                cat = dbm.checknReturnData("suppliers", "sup_id", sup, "cat_id");
                String[] temp1 = new String[2];
                String[] temp2 = new String[2];
                String[] temp3 = new String[2];
                temp1 = datehandler.forwad_months(year, month, 1).split("-");
                temp2 = datehandler.forwad_months(year, month, 2).split("-");
                temp3 = datehandler.forwad_months(year, month, 3).split("-");
                if(Integer.parseInt(cyear+cmonth)==Integer.parseInt(year+month)){out =outcurr;  remain = remcurrent;}
                
               if(Integer.parseInt(cyear+cmonth)>Integer.parseInt(year+month)){
           try{      //out = overadvance.calculate(sup, year, month);
                  remain = dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", year+month+sup, "bal_cf");
                  
                  out[1]= dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", year+month+sup, "cash_advances");
                  out[0]=dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", year+month+sup, "total_kg");} catch(Exception e){}
                 if(Integer.parseInt(cyear+cmonth)>Integer.parseInt(temp1[0]+temp1[1])){
                 try{     //  out1 = overadvance.calculate(sup, temp1[0], temp1[1]);
                        remain2 = dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp1[0]+temp1[1]+sup, "bal_cf");
                        out1[0]= dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp1[0]+temp1[1]+sup, "total_kg");} catch(Exception e){}
                       if(Integer.parseInt(cyear+cmonth)>Integer.parseInt(temp2[0]+temp2[1])){
                    try{      // out2 = overadvance.calculate(sup, temp2[0], temp2[1]);
                          remain3 = dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp2[0]+temp2[1]+sup, "bal_cf");
                          out2[0]= dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp2[0]+temp2[1]+sup, "total_kg");}catch(Exception e){}
                          if(Integer.parseInt(cyear+cmonth)>Integer.parseInt(temp3[0]+temp3[1])){
                       try{      //out3 = overadvance.calculate(sup, temp3[0], temp3[1]); 
                            remain4 = dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp3[0]+temp3[1]+sup, "bal_cf");
                            out3[0]= dbm.checknReturnDoubleData("gl_monthly_ledger_current", "entry", temp3[0]+temp3[1]+sup, "total_kg");}catch(Exception e){}
               }}}}
                
                
                
                
               

                ////////////////////////////////////////// table header change////////////////////////////////
                JTableHeader th = table.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn current = tcm.getColumn(4);
                TableColumn next = tcm.getColumn(5);
                TableColumn next1 = tcm.getColumn(6);
                TableColumn next2 = tcm.getColumn(7);

                next.setHeaderValue(datehandler.Return_month(Integer.parseInt(temp1[1])));
                next1.setHeaderValue(datehandler.Return_month(Integer.parseInt(temp2[1])));
                next2.setHeaderValue(datehandler.Return_month(Integer.parseInt(temp3[1])));
                current.setHeaderValue(datehandler.Return_month(Integer.parseInt(month)));
                th.repaint();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                /*remain = out[0] * set - (out[1] + out[2] + out[3] + out[4]);
               
                remain2 = out1[0] * set - (out1[1] + out1[2] + out1[3] + out1[4]);
               
                remain3 = out2[0] * set - (out2[1] + out2[2] + out2[3] + out2[4]);
                 
                remain4 = out3[0] * set - (out3[1] + out3[2] + out3[3] + out3[4]); */
                
                 remain = 0-remain;
               
                remain2 = 0-remain2;
               
                remain3 = 0-remain3;
                 
                remain4 = 0-remain4;
                
                
               // if(remain<0){
                    //System.out.println("INSERT INTO gl_over_advance(sup_id,sup_name,category_code,cash_ad,other_ad,loans,bal_bf,set,total_kg,recovered,remain) VALUES('" + sup + "','" + name + "','" + cat + "','" + out[1] + "','" + out[2] + "','" + out[3] + "','" + out[4] + "','" + set + "','" + out[0] + "','" + set*out[0] + "','" + remain + "')");
                    try {
                        dbm.insert("INSERT INTO gl_over_advance(sup_id,sup_name,category_code,cash_ad,other_ad,loans,bal_bf,set_val,total_kg,recovered,remain,m1,m2,m3,m4) VALUES('" + sup + "','" + name + "','" + cat + "','" + out[1] + "','" + remain + "','" + remain2 + "','" + remain3 + "','" + remain4 + "','" + outcurr[0] + "','" + (set) * (outcurr[0]) + "','" + remcurrent + "','" + out[0] + "','" + out1[0] + "','" + out2[0] + "','" + out3[0] + "')");
                        progress.setValue(kl);
                        kl++;
                      
                    
                    } catch (Exception ee) {
                        System.out.println(ee.getMessage());

                    }
            //    }
                }
               
            }
            
            if(kl==200){
                JOptionPane.showMessageDialog(table, "Entry Limit exceeded"+"\n"+"Showing first 200 results"+"\n"+"Try adjusting margin");
            
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(GL_Over_Advances_Table.class.getName()).log(Level.SEVERE, null, ex);
        }
       
 set_table(1, 50);
     
     
     
     
     progress.setValue(200);
     outlable.setText("Done");
     
     
     
     
     
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        page_info = new javax.swing.JLabel();
        supplier_id = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        datepanel2 = new javax.swing.JPanel();
        monthfield2 = new javax.swing.JTextField();
        yearfield2 = new javax.swing.JTextField();
        datePicker3 = new com.michaelbaranov.microba.calendar.DatePicker();
        datepanel3 = new javax.swing.JPanel();
        monthfield3 = new javax.swing.JTextField();
        yearfield3 = new javax.swing.JTextField();
        datePicker4 = new com.michaelbaranov.microba.calendar.DatePicker();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        outlable = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        set_val = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        margin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Sup Name", "Category", "Cash:Ad", "month1", "month2", "month3", "month4", "TotalKG", "Recovered", "Remain"
            }
        ));
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(51, 153, 0));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(90);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(80);
            table.getColumnModel().getColumn(8).setPreferredWidth(50);
            table.getColumnModel().getColumn(10).setPreferredWidth(80);
        }

        jButton2.setText("<");
        jButton2.setToolTipText("Previous Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.setToolTipText("Clear Selected Row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(">");
        jButton4.setToolTipText("Next page");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        page_info.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        page_info.setText("Page X of XX");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id.setEditable(true);
        supplier_id.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray1("category", "category_id")));
        supplier_id.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplier_idItemStateChanged(evt);
            }
        });
        supplier_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplier_idActionPerformed(evt);
            }
        });

        jLabel7.setText("Sort by");

        jLabel8.setText("Current");

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
                .addComponent(monthfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
                        .addComponent(monthfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datepanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield3.setText(datehandler.get_today_month());
        monthfield3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfield3KeyPressed(evt);
            }
        });

        yearfield3.setText(datehandler.get_today_year());
        yearfield3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfield3KeyPressed(evt);
            }
        });

        datePicker4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanel3Layout = new javax.swing.GroupLayout(datepanel3);
        datepanel3.setLayout(datepanel3Layout);
        datepanel3Layout.setHorizontalGroup(
            datepanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(monthfield3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(datePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanel3Layout.setVerticalGroup(
            datepanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(monthfield3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Starting");

        jButton5.setText("Supplier Bill Summery");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        progress.setStringPainted(true);

        outlable.setText("Set Value");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8)
                        .addComponent(datepanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(page_info))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(outlable, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datepanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))))
                    .addComponent(page_info, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(progress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(outlable))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        set_val.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                set_valKeyPressed(evt);
            }
        });

        jButton6.setText("Save Set Value");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setText("Set Value");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(set_val, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(set_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Margin");

        margin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marginActionPerformed(evt);
            }
        });

        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("View Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(margin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(margin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (supplier_id.getSelectedItem().toString().equals("All")) {
            if (i > 0) {
                i = i - 50;
                int m = i / 50;

                if (m >= 0) {
                    int k = 0;
                    int j = 0;
                    while (k <= 49) {
                        j = 0;

                        while (j < 11) {

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    set_table(i + 1, i + 50);
                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }
            }
        }

        if (!supplier_id.getSelectedItem().toString().equals("All")) {
            if (i > 0) {

                i = i - 50;
                int m = i / 50;

                if (m >= 0) {
                    int k = 0;
                    int j = 0;
                    while (k <= 49) {
                        j = 0;

                        while (j < 11) {
                            // System.out.println("Minus clearing");

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    // set_table(i + 1, i + 50);
                    filter.Filtered_table_over_ad(table, "gl_over_advance", supplier_id.getSelectedItem().toString(), i + 1, i + 50, "category_code");

                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // removeSelectedRows(table);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (supplier_id.getSelectedItem().equals("All")) {

            if (i < a - 50) {
                i = i + 50;
                int m = i / 50;
                if (m <= no_of_pages - 1) {
                    int k = 0;
                    int j = 0;

                    while (k <= 49) {
                        j = 0;
                        while (j < 11) {

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    set_table(i + 1, i + 50);
                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }

            }
    }//GEN-LAST:event_jButton4ActionPerformed

        if (!supplier_id.getSelectedItem().equals("All")) {
            if (i < a_2 - 50) {
                i = i + 50;
                int m = i / 50;
                if (m <= no_of_pages - 1) {
                    int k = 0;
                    int j = 0;

                    while (k <= 49) {
                        j = 0;
                        while (j < 11) {
                            //  System.out.println("clearing");

                            table.setValueAt(null, k, j);
                            j++;
                        }
                        k++;
                    }

                    // set_table(i+1, i+50);
                    filter.Filtered_table_over_ad(table, "gl_over_advance", supplier_id.getSelectedItem().toString(), i + 1, i + 50, "category_code");

                    page_info.setText("Page " + (m + 1) + " of" + " " + no_of_pages);
                }

            }

        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       GL_Billsummery bill = new GL_Billsummery("SupID");
         bill.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_idItemStateChanged
        if (supplier_id.getSelectedItem().toString().equals("All")) {
            // table_handler.clear_table(table, 10, 50);
            // System.out.println("ok");
            set_table(1, 50);
            int i = 0;
        }
        if (!supplier_id.getSelectedItem().equals("All")) {

            table_handler.clear_table(table, 11, 50);
            a_2 = filter.Filtered_table_over_ad(table, "gl_over_advance", supplier_id.getSelectedItem().toString(), 1, 50, "category_code");
            if (a % 50 == 0) {
                no_of_pages = a_2 / 50;

            } else {
                no_of_pages = a_2 / 50 + 1;

            }
            page_info.setText("Page 1 of" + " " + no_of_pages);
            int i = 0;
        }
    }//GEN-LAST:event_supplier_idItemStateChanged

    private void supplier_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplier_idActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Thread a = new Thread(new Cal());
    a.start();
    supplier_id.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void set_valKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_set_valKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // calculate();

        }
    }//GEN-LAST:event_set_valKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dbm.updateDatabase("rate_details", "Code_name", "GLSET", "rate", Double.parseDouble(set_val.getText()));
        //calculate();
        //Red_message.setText("Set value saved");
        // double a =  bill_sum.bill_sum_cal(Integer.parseInt(supplier_id.getSelectedItem().toString()), yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), Double.parseDouble(Set_val.getText()));
        // max_allowable.setText(""+a);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield2.requestFocus();
            yearfield2.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
             monthfield3.requestFocus();
            monthfield3.selectAll();

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
            //   dayfield2.requestFocus();
            //  dayfield2.selectAll();

        }
    }//GEN-LAST:event_yearfield2KeyPressed

    private void datePicker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker3ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker3.getDate().getTime());

        // dayfield.setText(datehandler.get_day(datef));
        monthfield2.setText(datehandler.get_month(datef));
        yearfield2.setText(datehandler.get_year(datef));
        //  dayfield2.requestFocus();
        // dayfield2.selectAll();
    }//GEN-LAST:event_datePicker3ActionPerformed

    private void monthfield3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfield3KeyPressed

        if (monthfield3.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Dec");
                int yr = Integer.parseInt(yearfield3.getText());

                yearfield3.setText("" + (yr - 1));
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Feb");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Jan");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Mar");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Feb");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Apr");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Mar");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("May");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Apr");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                monthfield3.setText("Jun");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("May");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Jul");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Jun");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Aug");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Jul");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Sep");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Aug");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Oct");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Sep");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Nov");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Oct");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Dec");
                monthfield3.selectAll();
            }

        } else if (monthfield3.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield3.setText("Nov");
                int yr = Integer.parseInt(yearfield3.getText());
                monthfield3.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield3.setText("Jan");
                int yr = Integer.parseInt(yearfield3.getText());

                yearfield3.setText("" + (yr + 1));
                monthfield3.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield3.requestFocus();
            yearfield3.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  dayfield2.requestFocus();
            // dayfield2.selectAll();

        }


    }//GEN-LAST:event_monthfield3KeyPressed

    private void yearfield3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfield3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield3.setText("" + (Integer.parseInt(yearfield3.getText()) + 1));
            yearfield3.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield3.setText("" + (Integer.parseInt(yearfield3.getText()) - 1));
            yearfield3.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield3.requestFocus();
            monthfield3.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //   dayfield2.requestFocus();
            //  dayfield2.selectAll();

        }


    }//GEN-LAST:event_yearfield3KeyPressed

    private void datePicker4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker4ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker3.getDate().getTime());
        monthfield3.setText(datehandler.get_month(datef));
        yearfield3.setText(datehandler.get_year(datef));
    }//GEN-LAST:event_datePicker4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String year = yearfield3.getText();
            String month = datehandler.return_month_as_num(monthfield3.getText());
            String cyear = yearfield2.getText();
            String cmonth = datehandler.return_month_as_num(monthfield2.getText());
        String[] temp1 = new String[2];
                String[] temp2 = new String[2];
                String[] temp3 = new String[2];
                temp1 = datehandler.forwad_months(year, month, 1).split("-");
                temp2 = datehandler.forwad_months(year, month, 2).split("-");
                temp3 = datehandler.forwad_months(year, month, 3).split("-");
        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
         String saveloc = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
        HashMap param = new HashMap();
        param.put("month1",datehandler.Return_month(Integer.parseInt(month)));
        param.put("month2",datehandler.Return_month(Integer.parseInt(temp1[1])));
        param.put("month3",datehandler.Return_month(Integer.parseInt(temp2[1])));
        param.put("month4",datehandler.Return_month(Integer.parseInt(temp3[1])));
        param.put("Currentmonth",datehandler.Return_month_full(Integer.parseInt(cmonth)));
        param.put("USER", user.get_current_user());
        
        gen.create("OverAdvance", saveloc, param, location, "GL_over_advance.jrxml");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void marginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker4;
    private javax.swing.JPanel datepanel2;
    private javax.swing.JPanel datepanel3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField margin;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JTextField monthfield3;
    private javax.swing.JLabel outlable;
    private javax.swing.JLabel page_info;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTextField set_val;
    private javax.swing.JComboBox supplier_id;
    private javax.swing.JTable table;
    private javax.swing.JTextField yearfield2;
    private javax.swing.JTextField yearfield3;
    // End of variables declaration//GEN-END:variables
}
