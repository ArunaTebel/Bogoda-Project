
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
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
public class GL_BillsummeryPanel extends javax.swing.JPanel {

    Interface_Events interface_events = new Interface_Events();
    DatabaseManager dbm = new DatabaseManager();
    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    DateChooser_text datechooser = new DateChooser_text();
    GL_Billsummerycls billsum = new GL_Billsummerycls();

    Date_Handler datehandler = new Date_Handler();

    /**
     * Creates new form GL_BillsummeryPanel
     */
    public GL_BillsummeryPanel() {
        defaults.put("nimbusOrange", defaults.get("nimbusBase"));
        UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(51, 153, 0)));
        initComponents();
        int[] day = new int[31];
        int n = 0;
        while(n<31){  day[n]=0;  n++;}
        set_day_values(day);
        set_max_Kg(10);
    }
   
    public void focus(){
    this.requestFocus();
    supplier_id.requestFocus();
    
    
    }
    public void Fill_tables(javax.swing.JTable Supplies, javax.swing.JTable jtable2){
    String month = monthfield.getText();
        String year = yearfield.getText();
        int sup = Integer.parseInt(supplier_id.getSelectedItem().toString());
        ((DefaultTableModel) Supplies.getModel()).setNumRows(0);
       // ((DefaultTableModel) Supplies.getModel()).setNumRows(i + 1);
       
        String[][] values = new String[1000][5];
               int p = 0;
               int q = 0;
               while (p < 1000) {
                   while (q < 5) {
                  
                       values[p][q] = null;
                       q++;
                   }
             p++;
               }
        
        
        
        
        
        
        values = billsum.GL_table(sup, year, datehandler.return_month_as_num(month));
        //System.out.println(values[1][0]);
        double Max_kg = 0;
        int i = 0;
        int j = 0;
        int day;
        double[] day_values = new double[31];
        int[] day_values_int = new int[31];
        double total = 0;
       // String chek = "not null";
        while (i < 1000 && values[i][0]!=null) {
            j=0;
            //System.out.println(values[i][0]);
            day = Integer.parseInt(values[i][0].substring(8));
            //System.out.println(day);
            day_values[ day - 1] = day_values[day - 1] + Double.parseDouble(values[i][4]);
            total+=Double.parseDouble(values[i][4]);
            if (Max_kg < day_values[day - 1]) {
                Max_kg = day_values[day - 1];
            }

            ((DefaultTableModel) Supplies.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                
                Supplies.setValueAt(values[i][j], i, j);
               // System.out.println("in loop");
                j++;
            }
                 i++;
        }
       //System.out.println("loop done");
         Supply_total.setText("" + total);
         set_max_Kg((int) Math.round(Max_kg));
         int k=0;
         while(k<31){
           day_values_int[k] = (int) Math.round(day_values[k]);
            k++;
         }
         set_day_values(day_values_int);
         
        /////////////////////////////////////////////////////////cash advance/////////////////////////////////////////////////////////
       ((DefaultTableModel) jTable2.getModel()).setNumRows(0);
           p = 0;
                q = 0;
               while (p < 1000) {
                   while (q < 5) {

                       values[p][q] = null;
                       q++;
                   }
                p++;
               }
         double Ad_total= 0;
        i = 0;
             
        // if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
      //   {      
               
       //      values = billsum.advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
      //   else{
             values= billsum.advance_table(sup, year, datehandler.return_month_as_num(month));//}
          while (i < 1000 && values[i][0]!=null) {
            j=0;
            
           

            ((DefaultTableModel) jTable2.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                
                jTable2.setValueAt(values[i][j], i, j);
                
             
               
                j++;
            }
             Ad_total+=Double.parseDouble(values[i][4]); 
                 i++;
        }
       
      ////////////////////////////////////other advance/////////////////////////////////////  
        
          
            p = 0;
                q = 0;
               while (p < 1000) {
                   while (q < 5) {

                       values[p][q] = null;
                       q++;
                   }
                p++;
               }
           
           int x =0;
         //if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
        // {values = billsum.other_advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
        // else{ 
           values= billsum.other_advance_table(sup, year, datehandler.return_month_as_num(month));//}
          while (i < 1000 && values[x][0]!=null) {
            j=0;
            //System.out.println(values[i][0]);
           

            ((DefaultTableModel) jTable2.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                //System.out.println("in loop");
                jTable2.setValueAt(values[x][j], i, j);
            
                j++;
            }
            Ad_total+=Double.parseDouble(values[x][4]);
             // System.out.println(Ad_total);
                 i++;
                 x++;
        }
            Advance_tot.setText(""+Ad_total);
            TotalKG.setText(""+total);
            total_ad.setText(""+Ad_total);
            balBF.setText("0.0");
            loans.setText("0.0");
            
    
    
    
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
        Supplies = new javax.swing.JTable();
        Supply_total = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        TotalKG = new javax.swing.JTextField();
        loans = new javax.swing.JTextField();
        Set = new javax.swing.JTextField();
        total_ad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        gross_amount = new javax.swing.JTextField();
        final_total = new javax.swing.JTextField();
        balBF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Advance_tot = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        supplier_id = new javax.swing.JComboBox();
        supplier_name = new javax.swing.JLabel();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jProgressBar18 = new javax.swing.JProgressBar();
        jProgressBar23 = new javax.swing.JProgressBar();
        jProgressBar21 = new javax.swing.JProgressBar();
        jProgressBar17 = new javax.swing.JProgressBar();
        jProgressBar20 = new javax.swing.JProgressBar();
        jProgressBar19 = new javax.swing.JProgressBar();
        jProgressBar25 = new javax.swing.JProgressBar();
        jProgressBar24 = new javax.swing.JProgressBar();
        jProgressBar28 = new javax.swing.JProgressBar();
        jProgressBar27 = new javax.swing.JProgressBar();
        jProgressBar26 = new javax.swing.JProgressBar();
        jProgressBar22 = new javax.swing.JProgressBar();
        jProgressBar31 = new javax.swing.JProgressBar();
        jProgressBar30 = new javax.swing.JProgressBar();
        jProgressBar29 = new javax.swing.JProgressBar();
        day17 = new javax.swing.JLabel();
        day18 = new javax.swing.JLabel();
        day19 = new javax.swing.JLabel();
        day20 = new javax.swing.JLabel();
        day21 = new javax.swing.JLabel();
        day22 = new javax.swing.JLabel();
        day23 = new javax.swing.JLabel();
        day24 = new javax.swing.JLabel();
        day25 = new javax.swing.JLabel();
        day26 = new javax.swing.JLabel();
        day27 = new javax.swing.JLabel();
        day28 = new javax.swing.JLabel();
        day29 = new javax.swing.JLabel();
        day30 = new javax.swing.JLabel();
        day31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jProgressBar16 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar10 = new javax.swing.JProgressBar();
        jProgressBar11 = new javax.swing.JProgressBar();
        jProgressBar15 = new javax.swing.JProgressBar();
        jProgressBar12 = new javax.swing.JProgressBar();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar8 = new javax.swing.JProgressBar();
        jProgressBar13 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar7 = new javax.swing.JProgressBar();
        jProgressBar14 = new javax.swing.JProgressBar();
        jProgressBar9 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();
        day1 = new javax.swing.JLabel();
        day2 = new javax.swing.JLabel();
        day3 = new javax.swing.JLabel();
        day4 = new javax.swing.JLabel();
        day5 = new javax.swing.JLabel();
        day6 = new javax.swing.JLabel();
        day7 = new javax.swing.JLabel();
        day8 = new javax.swing.JLabel();
        day9 = new javax.swing.JLabel();
        day10 = new javax.swing.JLabel();
        day11 = new javax.swing.JLabel();
        day12 = new javax.swing.JLabel();
        day13 = new javax.swing.JLabel();
        day14 = new javax.swing.JLabel();
        day15 = new javax.swing.JLabel();
        day16 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 2), "Supplies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 0)));

        Supplies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Sacks", "Total", "Deduct", "Net:Q"
            }
        ));
        Supplies.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(Supplies);

        jLabel12.setText("Total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Supply_total, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Supply_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0), 2), "Loans", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(204, 51, 0)));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Type", "Inst", "Amount", "Net:Amnt"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jLabel13.setText("Total");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(284, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Bal B/F");

        Set.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SetKeyPressed(evt);
            }
        });

        jLabel8.setText("Loans");

        jLabel5.setText("Total (KG)");

        final_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        final_total.setForeground(new java.awt.Color(204, 0, 0));

        balBF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                balBFKeyPressed(evt);
            }
        });

        jLabel7.setText("Total Advances");

        jLabel6.setText("Set Value");

        jLabel10.setText("=");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Set, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel5))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalKG, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loans, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(balBF, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_ad, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gross_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(final_total, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalKG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(Set, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(gross_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total_ad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(final_total, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 102, 0), 2), "Total Advances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(204, 102, 0)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Type", "Code", "Installments", "Amount"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel14.setText("Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(285, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Advance_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Advance_tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Name ");

        jLabel1.setText("User ID");

        DatabaseManager dbm = DatabaseManager.getDbCon();
        supplier_id.setEditable(true);
        supplier_id.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        supplier_id.setModel(new javax.swing.DefaultComboBoxModel(dbm.getStringArray("suppliers", "sup_id")));
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

        supplier_name.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplier_name.setForeground(new java.awt.Color(102, 102, 102));
        supplier_name.setText("Name Here");

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield.setText(datehandler.get_today_month());
        monthfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                monthfieldKeyReleased(evt);
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
                .addGap(10, 10, 10)
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
                        .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplier_name, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBar18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar18.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar18.setValue(50);
        jProgressBar18.setStringPainted(true);

        jProgressBar23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar23.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar23.setValue(50);
        jProgressBar23.setStringPainted(true);

        jProgressBar21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar21.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar21.setValue(50);
        jProgressBar21.setStringPainted(true);

        jProgressBar17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar17.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar17.setValue(50);
        jProgressBar17.setStringPainted(true);

        jProgressBar20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar20.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar20.setValue(50);
        jProgressBar20.setStringPainted(true);

        jProgressBar19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar19.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar19.setValue(50);
        jProgressBar19.setStringPainted(true);

        jProgressBar25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar25.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar25.setValue(50);
        jProgressBar25.setStringPainted(true);

        jProgressBar24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar24.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar24.setValue(50);
        jProgressBar24.setStringPainted(true);

        jProgressBar28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar28.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar28.setValue(50);
        jProgressBar28.setStringPainted(true);

        jProgressBar27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar27.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar27.setValue(50);
        jProgressBar27.setStringPainted(true);

        jProgressBar26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar26.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar26.setValue(50);
        jProgressBar26.setStringPainted(true);

        jProgressBar22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar22.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar22.setValue(50);
        jProgressBar22.setStringPainted(true);

        jProgressBar31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar31.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar31.setValue(50);
        jProgressBar31.setStringPainted(true);

        jProgressBar30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar30.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar30.setValue(50);
        jProgressBar30.setStringPainted(true);

        jProgressBar29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar29.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar29.setValue(50);
        jProgressBar29.setStringPainted(true);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBar30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar31, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        day17.setText("17");

        day18.setText("18");

        day19.setText("19");

        day20.setText("20");

        day21.setText("21");

        day22.setText("22");

        day23.setText("23");

        day24.setText("24");

        day25.setText("25");

        day26.setText("26");

        day27.setText("27");

        day28.setText("28");

        day29.setText("29");

        day30.setText("30");

        day31.setText("31");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(day17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day30, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(day31, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day17)
                .addGap(18, 18, 18)
                .addComponent(day18)
                .addGap(18, 18, 18)
                .addComponent(day19)
                .addGap(18, 18, 18)
                .addComponent(day20)
                .addGap(18, 18, 18)
                .addComponent(day21)
                .addGap(18, 18, 18)
                .addComponent(day22)
                .addGap(18, 18, 18)
                .addComponent(day23)
                .addGap(18, 18, 18)
                .addComponent(day24)
                .addGap(18, 18, 18)
                .addComponent(day25)
                .addGap(18, 18, 18)
                .addComponent(day26)
                .addGap(18, 18, 18)
                .addComponent(day27)
                .addGap(18, 18, 18)
                .addComponent(day28)
                .addGap(18, 18, 18)
                .addComponent(day29)
                .addGap(18, 18, 18)
                .addComponent(day30)
                .addGap(18, 18, 18)
                .addComponent(day31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jProgressBar16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar16.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar16.setValue(50);
        jProgressBar16.setStringPainted(true);

        jProgressBar2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar2.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar2.setValue(50);
        jProgressBar2.setStringPainted(true);

        jProgressBar10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar10.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar10.setValue(50);
        jProgressBar10.setStringPainted(true);

        jProgressBar11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar11.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar11.setValue(50);
        jProgressBar11.setStringPainted(true);

        jProgressBar15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar15.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar15.setValue(50);
        jProgressBar15.setStringPainted(true);

        jProgressBar12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar12.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar12.setValue(50);
        jProgressBar12.setStringPainted(true);

        jProgressBar1.setString("12");
        jProgressBar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setValue(50);
        jProgressBar1.setStringPainted(true);

        jProgressBar8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar8.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar8.setValue(50);
        jProgressBar8.setStringPainted(true);

        jProgressBar13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar13.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar13.setValue(50);
        jProgressBar13.setStringPainted(true);

        jProgressBar3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar3.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar3.setValue(50);
        jProgressBar3.setStringPainted(true);

        jProgressBar4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar4.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar4.setValue(50);
        jProgressBar4.setStringPainted(true);

        jProgressBar5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar5.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar5.setValue(50);
        jProgressBar5.setStringPainted(true);

        jProgressBar7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar7.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar7.setValue(50);
        jProgressBar7.setStringPainted(true);

        jProgressBar14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar14.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar14.setValue(50);
        jProgressBar14.setStringPainted(true);

        jProgressBar9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar9.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar9.setValue(50);
        jProgressBar9.setStringPainted(true);

        jProgressBar6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jProgressBar6.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar6.setValue(50);
        jProgressBar6.setStringPainted(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBar15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jProgressBar16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        day1.setText("1");

        day2.setText("2");

        day3.setText("3");

        day4.setText("4");

        day5.setText("5");

        day6.setText("6");

        day7.setText("7");

        day8.setText("8");

        day9.setText("9");

        day10.setText("10");

        day11.setText("11");

        day12.setText("12");

        day13.setText("13");

        day14.setText("14");

        day15.setText("15");

        day16.setText("16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(day10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(day8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(day9)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(day2)
                .addGap(18, 18, 18)
                .addComponent(day3)
                .addGap(18, 18, 18)
                .addComponent(day4)
                .addGap(18, 18, 18)
                .addComponent(day5)
                .addGap(18, 18, 18)
                .addComponent(day6)
                .addGap(18, 18, 18)
                .addComponent(day7)
                .addGap(18, 18, 18)
                .addComponent(day8)
                .addGap(18, 18, 18)
                .addComponent(day9)
                .addGap(18, 18, 18)
                .addComponent(day10)
                .addGap(18, 18, 18)
                .addComponent(day11)
                .addGap(18, 18, 18)
                .addComponent(day12)
                .addGap(18, 18, 18)
                .addComponent(day13)
                .addGap(18, 18, 18)
                .addComponent(day14)
                .addGap(18, 18, 18)
                .addComponent(day15)
                .addGap(18, 18, 18)
                .addComponent(day16)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void supplier_idItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_supplier_idItemStateChanged
        try {
            
      if(supplier_id.getSelectedIndex()!=0){
           if(supplier_id.getSelectedItem()!=""){
               supplier_name.setText(dbm.checknReturnData("suppliers", "sup_id", Integer.parseInt(supplier_id.getSelectedItem().toString()), "sup_name"));
    
               Fill_tables(Supplies, jTable2);
/*
        String month = monthfield.getText();
        String year = yearfield.getText();
        int sup = Integer.parseInt(supplier_id.getSelectedItem().toString());
        ((DefaultTableModel) Supplies.getModel()).setNumRows(0);
       // ((DefaultTableModel) Supplies.getModel()).setNumRows(i + 1);
       
        String[][] values = new String[1000][5];
               int p = 0;
               int q = 0;
               while (p < 1000) {
                   while (q < 5) {
                  
                       values[p][q] = null;
                       q++;
                   }
             p++;
               }
        
        
        
        
        
        
        values = billsum.GL_table(sup, year, datehandler.return_month_as_num(month));
        //System.out.println(values[1][0]);
        double Max_kg = 0;
        int i = 0;
        int j = 0;
        int day;
        double[] day_values = new double[31];
        int[] day_values_int = new int[31];
        double total = 0;
       // String chek = "not null";
        while (i < 1000 && values[i][0]!=null) {
            j=0;
            //System.out.println(values[i][0]);
            day = Integer.parseInt(values[i][0].substring(8));
            //System.out.println(day);
            day_values[ day - 1] = day_values[day - 1] + Double.parseDouble(values[i][4]);
            total+=Double.parseDouble(values[i][4]);
            if (Max_kg < day_values[day - 1]) {
                Max_kg = day_values[day - 1];
            }

            ((DefaultTableModel) Supplies.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                
                Supplies.setValueAt(values[i][j], i, j);
               // System.out.println("in loop");
                j++;
            }
                 i++;
        }
       //System.out.println("loop done");
         Supply_total.setText("" + total);
         set_max_Kg((int) Math.round(Max_kg));
         int k=0;
         while(k<31){
           day_values_int[k] = (int) Math.round(day_values[k]);
            k++;
         }
         set_day_values(day_values_int);
         
        /////////////////////////////////////////////////////////cash advance/////////////////////////////////////////////////////////
       ((DefaultTableModel) jTable2.getModel()).setNumRows(0);
           p = 0;
                q = 0;
               while (p < 1000) {
                   while (q < 5) {

                       values[p][q] = null;
                       q++;
                   }
                p++;
               }
         double Ad_total= 0;
        i = 0;
             
       //  if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
        // {      
               
          //   values = billsum.advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
        // else{
             values= billsum.advance_table(sup, year, datehandler.return_month_as_num(month));//}
          while (i < 1000 && values[i][0]!=null) {
            j=0;
            
           

            ((DefaultTableModel) jTable2.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                
                jTable2.setValueAt(values[i][j], i, j);
                
             
               
                j++;
            }
             Ad_total+=Double.parseDouble(values[i][4]); 
                 i++;
        }
       
      ////////////////////////////////////other advance/////////////////////////////////////  
        
          
            p = 0;
                q = 0;
               while (p < 1000) {
                   while (q < 5) {

                       values[p][q] = null;
                       q++;
                   }
                p++;
               }
           
           int x =0;
       //  if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
       //  {values = billsum.other_advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
     //   else{ 
                values= billsum.other_advance_table(sup, year, datehandler.return_month_as_num(month));
                //}//}
          while (i < 1000 && values[x][0]!=null) {
            j=0;
            //System.out.println(values[i][0]);
           

            ((DefaultTableModel) jTable2.getModel()).setNumRows(i + 1);
            //chek = values[i][0];
            while (j < 5) {
                System.out.println("in loop");
                jTable2.setValueAt(values[x][j], i, j);
            
                j++;
            }
            Ad_total+=Double.parseDouble(values[x][4]);
              System.out.println(Ad_total);
                 i++;
                 x++;
        }
            Advance_tot.setText(""+Ad_total);
            TotalKG.setText(""+total);
            total_ad.setText(""+Ad_total);
            balBF.setText("0.0");
            loans.setText("0.0");
           */
               monthfield.requestFocus();
               monthfield.selectAll();
         }
       }
 } catch (Exception e) {
            System.err.println(e.getMessage());  
        }
        
    }//GEN-LAST:event_supplier_idItemStateChanged

    private void supplier_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplier_idActionPerformed

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
            //dayfield.requestFocus();
           // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield.requestFocus();
            yearfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            Fill_tables(Supplies, jTable2);

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
            Fill_tables(Supplies, jTable2);

        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        //dayfield.setText(Integer.parseInt(datehandler.get_day(datef))+"");
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        //category_code.requestFocus();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void SetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SetKeyPressed
       if(TotalKG.getText()!= null && total_ad.getText() != null && loans.getText()!= null && balBF.getText()!= null){
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         gross_amount.setText(""+(Double.parseDouble(Set.getText())*Double.parseDouble(TotalKG.getText())));
        final_total.setText(""+(Double.parseDouble(gross_amount.getText())-Double.parseDouble(total_ad.getText())));
        if((Double.parseDouble(gross_amount.getText())-Double.parseDouble(total_ad.getText()))>0){
        
        final_total.setForeground(new java.awt.Color(0, 153, 0));}
        else{   final_total.setForeground(new java.awt.Color(204, 0, 0));                    }
        
        }}
    }//GEN-LAST:event_SetKeyPressed

    private void balBFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_balBFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_balBFKeyPressed

    private void monthfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_monthfieldKeyReleased

    public void set_max_Kg(int max) {
        jProgressBar1.setMaximum(max);
        jProgressBar2.setMaximum(max);
        jProgressBar3.setMaximum(max);
        jProgressBar4.setMaximum(max);
        jProgressBar5.setMaximum(max);
        jProgressBar6.setMaximum(max);
        jProgressBar7.setMaximum(max);
        jProgressBar8.setMaximum(max);
        jProgressBar9.setMaximum(max);
        jProgressBar10.setMaximum(max);
        jProgressBar11.setMaximum(max);
        jProgressBar12.setMaximum(max);
        jProgressBar13.setMaximum(max);
        jProgressBar14.setMaximum(max);
        jProgressBar15.setMaximum(max);
        jProgressBar16.setMaximum(max);
        jProgressBar17.setMaximum(max);
        jProgressBar18.setMaximum(max);
        jProgressBar19.setMaximum(max);
        jProgressBar20.setMaximum(max);
        jProgressBar21.setMaximum(max);
        jProgressBar22.setMaximum(max);
        jProgressBar23.setMaximum(max);
        jProgressBar24.setMaximum(max);
        jProgressBar25.setMaximum(max);
        jProgressBar26.setMaximum(max);
        jProgressBar27.setMaximum(max);
        jProgressBar28.setMaximum(max);
        jProgressBar29.setMaximum(max);
        jProgressBar30.setMaximum(max);
        jProgressBar31.setMaximum(max);

    }

    public void set_day_values(int days[]) {
        jProgressBar1.setValue(days[0]);
        jProgressBar1.setString(Integer.toString(days[0]));
        jProgressBar2.setValue(days[1]);
        jProgressBar2.setString(Integer.toString(days[1]));
        jProgressBar3.setValue(days[2]);
        jProgressBar3.setString(Integer.toString(days[2]));
        jProgressBar4.setValue(days[3]);
        jProgressBar4.setString(Integer.toString(days[3]));
        jProgressBar5.setValue(days[4]);
        jProgressBar5.setString(Integer.toString(days[4]));
        jProgressBar6.setValue(days[5]);
        jProgressBar6.setString(Integer.toString(days[5]));
        jProgressBar7.setValue(days[6]);
        jProgressBar7.setString(Integer.toString(days[6]));
        jProgressBar8.setValue(days[7]);
        jProgressBar8.setString(Integer.toString(days[7]));
        jProgressBar9.setValue(days[8]);
        jProgressBar9.setString(Integer.toString(days[8]));
        jProgressBar10.setValue(days[9]);
        jProgressBar10.setString(Integer.toString(days[9]));
        jProgressBar11.setValue(days[10]);
        jProgressBar11.setString(Integer.toString(days[10]));
        jProgressBar12.setValue(days[11]);
        jProgressBar12.setString(Integer.toString(days[11]));
        jProgressBar13.setValue(days[12]);
        jProgressBar13.setString(Integer.toString(days[12]));
        jProgressBar14.setValue(days[13]);
        jProgressBar14.setString(Integer.toString(days[13]));
        jProgressBar15.setValue(days[14]);
        jProgressBar15.setString(Integer.toString(days[14]));
        jProgressBar16.setValue(days[15]);
        jProgressBar16.setString(Integer.toString(days[15]));
        jProgressBar17.setValue(days[16]);
        jProgressBar17.setString(Integer.toString(days[16]));
        jProgressBar18.setValue(days[17]);
        jProgressBar18.setString(Integer.toString(days[17]));
        jProgressBar19.setValue(days[18]);
        jProgressBar19.setString(Integer.toString(days[18]));
        jProgressBar20.setValue(days[19]);
        jProgressBar20.setString(Integer.toString(days[19]));
        jProgressBar21.setValue(days[20]);
        jProgressBar21.setString(Integer.toString(days[20]));
        jProgressBar22.setValue(days[21]);
        jProgressBar22.setString(Integer.toString(days[21]));
        jProgressBar23.setValue(days[22]);
        jProgressBar23.setString(Integer.toString(days[22]));
        jProgressBar24.setValue(days[23]);
        jProgressBar24.setString(Integer.toString(days[23]));
        jProgressBar25.setValue(days[24]);
        jProgressBar25.setString(Integer.toString(days[24]));
        jProgressBar26.setValue(days[25]);
        jProgressBar26.setString(Integer.toString(days[25]));
        jProgressBar27.setValue(days[26]);
        jProgressBar27.setString(Integer.toString(days[26]));
        jProgressBar28.setValue(days[27]);
        jProgressBar28.setString(Integer.toString(days[27]));
        jProgressBar29.setValue(days[28]);
        jProgressBar29.setString(Integer.toString(days[28]));
        jProgressBar30.setValue(days[29]);
        jProgressBar30.setString(Integer.toString(days[29]));
        jProgressBar31.setValue(days[30]);
        jProgressBar31.setString(Integer.toString(days[30]));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Advance_tot;
    private javax.swing.JTextField Set;
    private javax.swing.JTable Supplies;
    private javax.swing.JTextField Supply_total;
    private javax.swing.JTextField TotalKG;
    private javax.swing.JTextField balBF;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JLabel day1;
    private javax.swing.JLabel day10;
    private javax.swing.JLabel day11;
    private javax.swing.JLabel day12;
    private javax.swing.JLabel day13;
    private javax.swing.JLabel day14;
    private javax.swing.JLabel day15;
    private javax.swing.JLabel day16;
    private javax.swing.JLabel day17;
    private javax.swing.JLabel day18;
    private javax.swing.JLabel day19;
    private javax.swing.JLabel day2;
    private javax.swing.JLabel day20;
    private javax.swing.JLabel day21;
    private javax.swing.JLabel day22;
    private javax.swing.JLabel day23;
    private javax.swing.JLabel day24;
    private javax.swing.JLabel day25;
    private javax.swing.JLabel day26;
    private javax.swing.JLabel day27;
    private javax.swing.JLabel day28;
    private javax.swing.JLabel day29;
    private javax.swing.JLabel day3;
    private javax.swing.JLabel day30;
    private javax.swing.JLabel day31;
    private javax.swing.JLabel day4;
    private javax.swing.JLabel day5;
    private javax.swing.JLabel day6;
    private javax.swing.JLabel day7;
    private javax.swing.JLabel day8;
    private javax.swing.JLabel day9;
    private javax.swing.JTextField final_total;
    private javax.swing.JTextField gross_amount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar10;
    private javax.swing.JProgressBar jProgressBar11;
    private javax.swing.JProgressBar jProgressBar12;
    private javax.swing.JProgressBar jProgressBar13;
    private javax.swing.JProgressBar jProgressBar14;
    private javax.swing.JProgressBar jProgressBar15;
    private javax.swing.JProgressBar jProgressBar16;
    private javax.swing.JProgressBar jProgressBar17;
    private javax.swing.JProgressBar jProgressBar18;
    private javax.swing.JProgressBar jProgressBar19;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar20;
    private javax.swing.JProgressBar jProgressBar21;
    private javax.swing.JProgressBar jProgressBar22;
    private javax.swing.JProgressBar jProgressBar23;
    private javax.swing.JProgressBar jProgressBar24;
    private javax.swing.JProgressBar jProgressBar25;
    private javax.swing.JProgressBar jProgressBar26;
    private javax.swing.JProgressBar jProgressBar27;
    private javax.swing.JProgressBar jProgressBar28;
    private javax.swing.JProgressBar jProgressBar29;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar30;
    private javax.swing.JProgressBar jProgressBar31;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JProgressBar jProgressBar9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField loans;
    private javax.swing.JTextField monthfield;
    private javax.swing.JComboBox supplier_id;
    private javax.swing.JLabel supplier_name;
    private javax.swing.JTextField total_ad;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
