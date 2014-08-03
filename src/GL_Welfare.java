
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class GL_Welfare extends javax.swing.JPanel {
    
    DatabaseManager dbm = new DatabaseManager();
    GL_report_generator reportgen = new GL_report_generator();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    int count = 1;

    /**
     * Creates new form GL_InstAdvances
     */
    public GL_Welfare() {
        initComponents();
        
        Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
        // ((DefaultTableModel) jTable1.getModel()).setNumRows(2);
        
    }
    
    public void clear(){
    jTextArea1.setText("");
         jTextArea2.setText("");
         jTextArea3.setText("");
         suspendButton.setSelected(false);
         regsterbt.setSelected(false);
         newOldButton.setSelected(false);
         beforeAfterButton.setSelected(false);
         supplier_id.getEditor().setItem("");
         supname.setText("");
         welf_num.setSelectedIndex(0);
    
    }
    public class calc implements Runnable{
        javax.swing.JLabel Reg,Nreg,Sus,att;
        String month, year;
        
        
        public calc(javax.swing.JLabel RReg, javax.swing.JLabel NNreg, javax.swing.JLabel SSus, javax.swing.JLabel Att, String Year, String Month){
        month = Month;
        year = Year;
        Reg = RReg;
        Nreg = NNreg;
        Sus= SSus;
        att= Att;
        
        
        }
        
        
    public void run() {
        clear();
        int reg = 0;
        int nreg = 0;
        int sus = 0;
        int Att = 0;
        String stat;
        
        int regNum = 0;
        int newRegNum = 0;
        int susNum = 0;
        String table = "welfare";
        String coloumnG = "month2";
      //  Calendar currentDate = Calendar.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String day = formatter.format(currentDate.getTime());
//        int monthNow = Integer.parseInt(day.substring(5, 7));
        //String thisMonth = day.substring(0, 5) + monthNow;
        String thisMonth = year+month;
        int supId = 0;
        String table1 = "suppliers";
        String coloumnG1 = "sup_id";
        
        try {
            ResultSet rs1 = dbm.query("SELECT * FROM " + table + " where " + coloumnG + " = '" + thisMonth + "'");
            while (rs1.next()) {
                
                if (rs1.getInt("new_old") == 1) {
                    
                    newRegNum++;
                }
               if( rs1.getInt("new_old")==0){
            
              regNum++;
            }
                
                if (rs1.getInt("suspended_months") > 0) {
                    susNum++;
               }
                supId = rs1.getInt("sup_id");
                    double[] total = new double[33];
                    //String year1 = day.substring(0, 5);
                    //String month1 = day.substring(5, 7);
                    total = reportgen.get_day_totals(supId, year, month);
                    int i = 0;
                    double Total = 0;
                    while (i < 32) {
                        Total += total[i];
                        i++;
                    }
                    if (Total == 0) {
                        Att++;
                    
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        att.setText(Att + " Need Attention");
        Reg.setText(regNum + " Registered");
        Nreg.setText(newRegNum + " New Registered");
        Sus.setText(susNum + " Suspended");
        
    } }

    
    public void check_last_two(String[] a,String year, String month){
    int i = 0;
    int sup;
    double[] total = new double[33];
    double[] total1 = new double[33];
    String[] temp1 = datehandler.Reverse_months(year, month, 1).split("-");
    String[] temp2 = datehandler.Reverse_months(year, month, 2).split("-");
    while(i<a.length){
      sup = Integer.parseInt(a[i]);
     double Total= 0,Total1 = 0;
                    //String year1 = day.substring(0, 5);
                    //String month1 = day.substring(5, 7);
                 /*   total = reportgen.get_day_totals(sup, temp1[0], temp1[1]);
                    total1 = reportgen.get_day_totals(sup, temp2[0], temp2[1]);
                    
                    int j = 0;
                   
                    while (j < 32) {
                        Total += total[j];
                        Total1 += total1[j];
                        j++;
                    }*/
                    
                    Total = dbm.checknReturnDoubleData("daily_transactions_current", "entry", temp1[0]+temp1[1]+sup, "Total");
                    Total1 = dbm.checknReturnDoubleData("daily_transactions_current", "entry", temp2[0]+temp2[1]+sup, "Total");
                    if (Total == 0 && Total1==0) {
                        
                  
    jTextArea2.setText(jTextArea2.getText()+sup+"\n");
    
    
    }
    
    
    
    i++;
    
    }
    }
    
     public void check_last_month_given(String[] a,String year, String month){
         jTextArea1.setText("");
    int i = 0;
    int sup;
    double[] total = new double[33];
    //double[] total1 = new double[33];
    String[] temp1 = datehandler.Reverse_months(year, month, 1).split("-");
    //String[] temp2 = datehandler.Reverse_months(year, month, 2).split("-");
    while(i<a.length){
      sup = Integer.parseInt(a[i]);
     double Total= 0;                    
                    
                    Total = dbm.checknReturnDoubleData("daily_transactions_current", "entry", temp1[0]+temp1[1]+sup, "Total");
                   // Total1 = dbm.checknReturnDoubleData("daily_transactions_current", "entry", temp2[0]+temp2[1]+sup, "Total");
                    if (Total != 0 ) {
                        
                  
    jTextArea1.setText(jTextArea1.getText()+sup+"\n");
    
    
    }
    
    
    
    i++;
    
    }
    }
    public void get_array(String year, String month){
        
        try {
            String table = "welfare";
            String coloumnG = "month2";
            String thisMonth = year+month;
            String[] temp1 = datehandler.Reverse_months(year, month, 1).split("-");
            int   supId=0;
            int j =0;
            double Total_l=0;
            ResultSet rs1 = dbm.query("SELECT * FROM  welfare  where " + coloumnG + " = '" + thisMonth + "'");
            while (rs1.next()) {
               
              supId = rs1.getInt("sup_id");
                double[] total = new double[33];
                //String year1 = day.substring(0, 5);
                //String month1 = day.substring(5, 7);
                total = reportgen.get_day_totals(supId, year, month);
                int i = 0;
                double Total = 0;
                while (i < 32) {
                    Total += total[i];
                    i++;
                }
                if (Total == 0) {
                    jTextArea1.setText(jTextArea1.getText()+supId+"\n");
                  j++;  
                }
                
                 if (Total != 0) {
                     Total_l = dbm.checknReturnDoubleData("daily_transactions_current", "entry", temp1[0]+temp1[1]+supId, "Total");
                        if(Total_l==0){
                        
                        jTextArea3.setText(jTextArea3.getText()+supId+"\n");
                        }
                     
                     
                  j++;  
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
        }
            
   
    
    }
    
    public void Update(String supId){
    
      DatabaseManager dbm = new DatabaseManager();
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            int entry = 0;
            /*try {
             entry = dbm.readLastRow("welfare", "entry") + 1;
             } catch (SQLException ex) {
             Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
             }*/
            
            String day = formatter.format(currentDate.getTime());
          //  int month = Integer.parseInt(day.substring(5, 7));
            //
            String thisMonth = yearfield.getText() + "-"+Integer.parseInt(datehandler.return_month_as_num(monthfield.getText()));
            String thisMonth2 = yearfield.getText() +datehandler.return_month_as_num(monthfield.getText());
            
            int newMonths = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_M", "rate")-1;
            
           
           try { 
            entry = Integer.parseInt(thisMonth2 + supId);}
           catch (Exception e){
                 JOptionPane.showMessageDialog(attention, "Sup id error!");
           }
            
            int suspended = -1;
            int remain = -1;
            int before = 0;
            int multi = 1;
            if (suspendButton.isSelected()) {
                String temp = welf_num.getSelectedItem().toString();
                if (temp.equals("-")) {
                    remain = 5000;
                     int reply = JOptionPane.showConfirmDialog(attention,
                        "Remove this supplier from welfare?" + "\n" + "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                     dbm.CheckNDeleteFromDataBase("welfare","entry", thisMonth2+supId);
                
                }
                       
                    
                } else {
                   // System.out.println(temp);
                    
                    remain = Integer.parseInt(temp);
                    suspended = Integer.parseInt(temp);
                }
                if (beforeAfterButton.isSelected()) {
                    before = 1;
                }
            } else {
                before = -1;
            }
              int newRegisterd = 0;
            int newOld = 0;
            if (regsterbt.isSelected()) {
                 String temp = welf_num.getSelectedItem().toString();
                if (temp.equals("-")) {
                    //System.out.println("IN normal");
                   // JOptionPane.showMessageDialog(attention, "Invalid state. Please select months");
                    newRegisterd = 0;
                    newMonths = -1;
                    multi= 0;
                    
                    try {
                    dbm.insert("INSERT INTO welfare(entry,month,month2,sup_id,months_on_welfare,new_old,suspended_months,suspended_remain,before_after) VALUES('" + entry + "','" + thisMonth + "','" + thisMonth2 + "','" + supId + "','" + newMonths + "','" + newRegisterd + "','" + suspended + "','" + remain + "','" + before + "')");
                } catch (SQLException ex) {
                    try {
                        dbm.insert("UPDATE  welfare SET month='"+thisMonth+"',month2='"+thisMonth2+"',sup_id='"+supId+"',months_on_welfare='"+newMonths+"',new_old='"+1+"',suspended_months='"+suspended+"',suspended_remain='"+remain+"',before_after='"+before+"' WHERE entry = '"+entry+"'");
                    } catch (SQLException ex1) {
                        Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex1);
                    }
              
                }
                } else {
                    remain = -1;
                    multi = Integer.parseInt(temp);
                }
                if (beforeAfterButton.isSelected()) {
                    before = 0;
                }
            }
            
          
            if (newOldButton.isSelected()) {
                newRegisterd = 1;
                 newOld = 1;
                try {
                    dbm.insert("INSERT INTO welfare(entry,month,month2,sup_id,months_on_welfare,new_old,suspended_months,suspended_remain,before_after) VALUES('" + entry + "','" + thisMonth + "','" + thisMonth2 + "','" + supId + "','" + newMonths + "','" + newRegisterd + "','" + suspended + "','" + remain + "','" + before + "')");
                } catch (SQLException ex) {
                    try {
                        dbm.insert("UPDATE  welfare SET month='"+thisMonth+"',month2='"+thisMonth2+"',sup_id='"+supId+"',months_on_welfare='"+newMonths+"',new_old='"+1+"',suspended_months='"+suspended+"',suspended_remain='"+remain+"',before_after='"+before+"' WHERE entry = '"+entry+"'");
                    } catch (SQLException ex1) {
                        Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex1);
                    }
              
                }
            } else {
                String temp = welf_num.getSelectedItem().toString();
                if(temp.equals("-" )&& suspendButton.isSelected()){}
                else{
               // System.out.println("Printing");
                dbm.update("welfare", "month", "sup_id", thisMonth, supId, "suspended_months", suspended);
                dbm.update("welfare", "month", "sup_id", thisMonth, supId, "suspended_remain", remain);
                dbm.update("welfare", "month", "sup_id", thisMonth, supId, "before_after", before);
                }
            }
            

         //String thisMonthsave = yearfield.getText() + "-" + Integer.parseInt(datehandler.return_month_as_num(monthfield.getText()));
       // DatabaseManager dbm = new DatabaseManager();
       ////////////////////////////////////////////////////////////// Update code/////////////////////////////////////////////////////////
            
        //ADD NEW OLD VALUE
            try{
            newOld = Integer.parseInt(dbm.checknReturnData("welfare", "entry", thisMonth2+supId, "new_old"));
            }catch(Exception ee){System.out.println(ee.getMessage());}
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
    
    
    
    
    
    }
    
    public void execute(javax.swing.JTextArea area){
        if(area.getText()==""){}
        else{
    String arr[] = area.getText().split("\n");
    int i = 0;
    while(i<arr.length){
        Update(arr[i]);
        System.out.println(arr[i]);
    i++;
    }
    
    }
    
    }
    
    
     public void execute_resume(javax.swing.JTextArea area){
          int oldRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_RATE", "rate");
        int newRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_NEW", "rate");
       
         String year = yearfield.getText();
         String month =datehandler.return_month_as_num( monthfield.getText());
        // regsterbt.setSelected(true);
         
        if(area.getText()==""){}
        else{
    String arr[] = area.getText().split("\n");
    int i = 0;
    int j;
    int k;
    String a;
    
    while(i<arr.length){
        Update(arr[i]);
      j =0;
      k= 0;
      
        while(j<2){
        String[] temp =datehandler.Reverse_months(year, month, j+1).split("-");
         a=dbm.checknReturnData("welfare","entry",temp[0]+temp[1]+arr[i],"suspended_months");
         if(Integer.parseInt(a)>0){
         
         k++;
         
         
         
         }
         
         
        }
        dbm.updateDatabase("welfare", "entry", year+month+arr[i], "amount", oldRate*(1+k));
        
        System.out.println(arr[i]);
    i++;
    }
    
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

        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        Nreg = new javax.swing.JLabel();
        reg = new javax.swing.JLabel();
        sus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        welf_num = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        newOldButton = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        suspendButton = new javax.swing.JRadioButton();
        regsterbt = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        beforeAfterButton = new javax.swing.JRadioButton();
        supplier_id = new javax.swing.JComboBox();
        supname = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        supplier_id1 = new javax.swing.JComboBox();
        attention = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton14 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        jButton6.setText("Supplier Status");

        jButton7.setText("Supplies");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Nreg.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        Nreg.setText("XX New registered");

        reg.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        reg.setText("XX Registered");

        sus.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        sus.setText("XX Suspended");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Change"));

        welf_num.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "" }));
        welf_num.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                welf_numItemStateChanged(evt);
            }
        });
        welf_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welf_numActionPerformed(evt);
            }
        });

        jLabel6.setText("Months");

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Supplier");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        newOldButton.setText("New Register");
        newOldButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                newOldButtonItemStateChanged(evt);
            }
        });
        newOldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOldButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newOldButton)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newOldButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        suspendButton.setText("Suspend");
        suspendButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                suspendButtonItemStateChanged(evt);
            }
        });
        suspendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspendButtonActionPerformed(evt);
            }
        });

        regsterbt.setText("Resume");
        regsterbt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                regsterbtItemStateChanged(evt);
            }
        });
        regsterbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regsterbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(suspendButton)
                    .addComponent(regsterbt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regsterbt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(suspendButton)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        beforeAfterButton.setText("Pay Before");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beforeAfterButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beforeAfterButton)
                .addContainerGap())
        );

        DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id.setEditable(true);
        supplier_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supplier_id.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("suppliers", "sup_id")));
        supplier_id.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplier_idItemStateChanged(evt);
            }
        });

        supname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        //DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id1.setEditable(true);
        supplier_id1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supplier_id1.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("suppliers", "sup_id")));
        supplier_id1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                supplier_id1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(supplier_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplier_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(welf_num, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supname, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supname, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addComponent(welf_num, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        attention.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        attention.setForeground(new java.awt.Color(153, 0, 0));
        attention.setText("XX Need Attention");

        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText("Load");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText("View");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton10.setText("View All");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setText("Update Welfare");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
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
                .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
                        .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)), "Not Supplied", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(153, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("This month");

        jButton13.setText("Excecute for all");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTextArea2.setBackground(new java.awt.Color(233, 233, 233));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea1.setBackground(new java.awt.Color(233, 233, 233));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton14.setText("Excecute for all");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Past 3 months");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)), "Supplied", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 0)));

        jTextArea3.setBackground(new java.awt.Color(233, 233, 233));
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("This month");

        jButton15.setText("Excecute for all");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attention)
                            .addComponent(reg)
                            .addComponent(sus)
                            .addComponent(Nreg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(Nreg)
                        .addGap(27, 27, 27)
                        .addComponent(reg)
                        .addGap(27, 27, 27)
                        .addComponent(sus)
                        .addGap(27, 27, 27)
                        .addComponent(attention)
                        .addGap(38, 38, 38)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
       // String[] temp = datehandler.Reverse_months(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), 12).split("-");
       // System.out.println(temp[0]+temp[1]);
        param.put("month", yearfield.getText() + "-"+Integer.parseInt(datehandler.return_month_as_num(monthfield.getText())));
        // param.put("to_date", Return_date2);
        param.put("USER", new UserAccountControl().get_current_user());
        
       // param.put("12month", temp[0]+temp[1]);
        

        // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
         String Slocation = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
        
        generate.create("GL_welfare", Slocation, param, location, "welfare_new.jrxml");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
        String[] temp = datehandler.Reverse_months(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), 12).split("-");
        System.out.println(temp[0]+temp[1]);
        param.put("month", yearfield.getText() + "-"+Integer.parseInt(datehandler.return_month_as_num(monthfield.getText())));
        // param.put("to_date", Return_date2);
        param.put("USER", new UserAccountControl().get_current_user());
        
        param.put("12month", temp[0]+temp[1]);
        

        // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
         String Slocation = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
        
        generate.create("GL_welfare", Slocation, param, location, "welfareCross.jrxml");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String supId = null;
            if (supplier_id.getSelectedIndex() == 0 || supplier_id.getEditor().getItem().equals("") || supplier_id.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Supplier Id not Given!");
            } else {
                supId = supplier_id.getSelectedItem().toString();
            }
        
        
        if ((newOldButton.isSelected() ) && welf_num.getSelectedIndex() != 0) {
            
            JOptionPane.showMessageDialog(attention, "Unsupported Function! Check The selections");
            
        } else {
            Update(supId);
           Thread as = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        as.start();

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       
//        String thisMonth = yearfield.getText() + "-" + Integer.parseInt(datehandler.return_month_as_num(monthfield.getText()));
//        
//        DatabaseManager dbm = new DatabaseManager();
//       
//        String table = "welfare";
//        String coloumnG = "month";
//        String coloumnN = null;
//        int supId = 0;
//        int newOld = 0;
//        int suspended = 0;
//        int remain = 0;
//        int before = 0;
//        double amount = 0;
//            System.out.println("Running qwelf");
//        int oldRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_RATE", "rate");
//        int newRate = (int) dbm.checknReturnDoubleData("rate_details", "Code_name", "WELF_NEW", "rate");
//        
//        try {
//            ResultSet query = dbm.query("SELECT * FROM " + table + " where " + coloumnG + " = '" + thisMonth + "'");
//            while (query.next()) {
//                coloumnN = "sup_id";
//                supId = query.getInt(coloumnN);
//                 String examount = dbm.filterReturn2StringData("welfare", "sup_id", supId+"","month",thisMonth, "amount");
//        if(examount==null){
//                coloumnN = "new_old";
//                newOld = query.getInt(coloumnN);
//                coloumnN = "suspended_months";
//                suspended = query.getInt(coloumnN);
//                coloumnN = "suspended_remain";
//                remain = query.getInt(coloumnN);
//                coloumnN = "before_after";
//                before = query.getInt(coloumnN);
//                
//                if (remain >= 0) {
//                    if ((remain == 0 && before == 0) || (remain == suspended && before == 1)) {
//                        if (newOld == 0) {
//                            amount = suspended * oldRate;
//                        } else {
//                            amount = suspended * newRate;
//                        }
//                    } else {
//                        amount = 0;
//                    }
//                } else {
//                    if (newOld == 0) {
//                        amount = oldRate;
//                    } else {
//                        amount = newRate;
//                    }                    
//                }
//                dbm.update("welfare", "month", "sup_id", thisMonth, supId, "amount", amount);                
//            }}
//        } catch (SQLException ex) {
//            Logger.getLogger(GL_Welfare.class.getName()).log(Level.SEVERE, null, ex);
//        }

        //reportgen.update_welfare(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()));
           //reportgen.update_taskmanager(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), "4");
    }//GEN-LAST:event_jButton12ActionPerformed

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
            // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield.requestFocus();
            yearfield.selectAll();
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
              Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
     
            // dayfield2.selectAll();

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
            Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
     
            //  dayfield2.selectAll();

        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        // dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
         Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
     
        // dayfield2.selectAll();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void suspendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suspendButtonActionPerformed

    private void newOldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOldButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newOldButtonActionPerformed

    private void regsterbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regsterbtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regsterbtActionPerformed

    private void welf_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welf_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_welf_numActionPerformed

    private void regsterbtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_regsterbtItemStateChanged
        if (regsterbt.isSelected()) {
            suspendButton.setSelected(false);
            welf_num.setSelectedIndex(0);
            beforeAfterButton.setSelected(false);
            
        }
    }//GEN-LAST:event_regsterbtItemStateChanged

    private void newOldButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_newOldButtonItemStateChanged
        
    }//GEN-LAST:event_newOldButtonItemStateChanged

    private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_idItemStateChanged
        if (supplier_id.getSelectedItem() != null) {
            //try {
            supname.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_name"));
//                sup_name_sinhala.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_sin_name"));
//                Cat_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "cat_id"));
//                //trans_rate.setSelectedItem(dbm.checknReturnStringData("suppliers", "sup_id", supplier_id.getSelectedItem().toString(), "trans_code"));
//                trans_rate.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "trans_rate"));
//                payment_method.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_pay_type"));
//                address.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_address"));
//                tel.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_tel"));
//                bank_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "bank_id"));
//                branch_code.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "branch_id"));
//                welf.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "welfare"));
//                trans_rate1.setSelectedItem(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "leaf_rate_code"));
//                sup_name.requestFocusInWindow();
//                //date.setDate(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_doc"));
//                String date1 = dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_doc");
//                java.sql.Date Datef = null;
//                Datef=java.sql.Date.valueOf(date1);
//
//                date.setDate(Datef);
            //  } catch (PropertyVetoException ex) {
            //    Logger.getLogger(GL_Add_Supplier.class.getName()).log(Level.SEVERE, null, ex);
            // }
        }

    }//GEN-LAST:event_supplier_idItemStateChanged

    private void suspendButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_suspendButtonItemStateChanged
        if (suspendButton.isSelected()) {
            regsterbt.setSelected(false);
        }
    }//GEN-LAST:event_suspendButtonItemStateChanged

    private void welf_numItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_welf_numItemStateChanged
        if (welf_num.getSelectedIndex() != 0 && suspendButton.isSelected()) {
            beforeAfterButton.setSelected(true);
            
        }
    }//GEN-LAST:event_welf_numItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
       // String[] temp = datehandler.Reverse_months(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), 12).split("-");
       // System.out.println(temp[0]+temp[1]);
        param.put("month", yearfield.getText() + "-"+Integer.parseInt(datehandler.return_month_as_num(monthfield.getText())));
        // param.put("to_date", Return_date2);
        param.put("USER", new UserAccountControl().get_current_user());
        
       // param.put("12month", temp[0]+temp[1]);
        

        // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
         String Slocation = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
        
        generate.create("GL_welfare", Slocation, param, location, "welfare_old.jrxml");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
       // String[] temp = datehandler.Reverse_months(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()), 12).split("-");
       // System.out.println(temp[0]+temp[1]);
        param.put("month", yearfield.getText() + "-"+Integer.parseInt(datehandler.return_month_as_num(monthfield.getText())));
        // param.put("to_date", Return_date2);
        param.put("USER", new UserAccountControl().get_current_user());
        
       // param.put("12month", temp[0]+temp[1]);
        

        // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
        String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
         String Slocation = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
        
        generate.create("GL_welfare", Slocation, param, location, "welfare_sus.jrxml");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      clear();
         String year = yearfield.getText();
         String month =datehandler.return_month_as_num(monthfield.getText());
        get_array(year,month );
        String[] arr= jTextArea1.getText().split("\n");
        
        check_last_two(arr, year, month);
        check_last_month_given(arr, year, month);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       welf_num.setSelectedIndex(11);
        suspendButton.setSelected(true);
        beforeAfterButton.setSelected(false);
        supname.setText("aaaaaaaaaaaaaaa");
        execute(jTextArea1);     
      Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        welf_num.setSelectedIndex(0);
        suspendButton.setSelected(true);
        beforeAfterButton.setSelected(false);
        execute(jTextArea2); 
        Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       welf_num.setSelectedIndex(0);
        regsterbt.setSelected(true);
        //beforeAfterButton.setSelected(false);
        
        execute(jTextArea3); 
        Thread a = new Thread(new calc(reg, Nreg, sus, attention, yearfield.getText(), datehandler.return_month_as_num(monthfield.getText())));
        a.start();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void supplier_id1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_id1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_supplier_id1ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       Acc_Update_And_Additional_Data tst = new Acc_Update_And_Additional_Data();
       
       tst.complete_op_bal_temp();
    }//GEN-LAST:event_jButton4ActionPerformed

   
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nreg;
    private javax.swing.JLabel attention;
    private javax.swing.JRadioButton beforeAfterButton;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField monthfield;
    private javax.swing.JRadioButton newOldButton;
    private javax.swing.JLabel reg;
    private javax.swing.JRadioButton regsterbt;
    private javax.swing.JLabel supname;
    private javax.swing.JComboBox supplier_id;
    private javax.swing.JComboBox supplier_id1;
    private javax.swing.JLabel sus;
    private javax.swing.JRadioButton suspendButton;
    private javax.swing.JComboBox welf_num;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
