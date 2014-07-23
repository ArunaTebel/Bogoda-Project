
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
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
 * @author Pramo
 */
public class Task_manager extends javax.swing.JPanel {
 UIDefaults defaults = UIManager.getLookAndFeelDefaults();
 
    Date_Handler datehandler = new Date_Handler();
    DatabaseManager dbm = new DatabaseManager();
    GL_report_generator report_gen = new GL_report_generator();
    Update update = new Update();

    /**
     * Creates new form Task_manager
     */
    public Task_manager() {
        initComponents();
        defaults.put("nimbusOrange", defaults.get("nimbusBase"));
        UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(51, 153, 0)));
        
        String year = yearfield.getText();
        String month = datehandler.return_month_as_num(monthfield.getText());
        set_task_man(year, month);
    }

    public void set_task_man(String year, String month) {

  // String[] Status = new String[3];
        set_values(check(year, month, "1"), GLLabel1, GLdate1, GL1);
        set_values(check(year, month, "2"), GLLabel2, GLdate2, GL2);
        set_values(check(year, month, "3"), GLLabel3, GLdate3, GL3);
        set_values(check(year, month, "4"), GLLabel4, GLdate4, GL4);
        set_values(check(year, month, "5"), GLLabel5, GLdate5, GL5);
        set_values(check(year, month, "6"), GLLabel6, GLdate6, GL6);
      // set_values(check(year, month, "1"), GLdate7, GLdate1, GL1);

    }

    public String[] check(String year, String month, String task) {
        String[] Status = new String[3];
        Status[0] = "";
        Status[1] = "";
        Status[2] = "NO";

        try {
            Status[0] = dbm.checknReturnStringData("task_manager", "entry", "" + year + month + task, "status");
            Status[1] = dbm.checknReturnStringData("task_manager", "entry", "" + year + month + task, "date");
            if (Status[0].equals("DONE")) {
                Status[2] = "DONE";
            }

            return Status;
        } catch (Exception e) {

            return Status;
        }

    }

    public void set_values(String[] Status, javax.swing.JLabel status, javax.swing.JLabel date, javax.swing.JCheckBox check) {
        if (Status[2].equals("DONE")) {
            status.setText(Status[0]);
            date.setText(Status[1]);
            check.setSelected(false);
        } else {
            status.setText("INCOMPLETE");
            status.setForeground(new java.awt.Color(0, 153, 0));
            date.setText("N/A");
            check.setSelected(true);

        }

    }
    
    
    public class daily_trans implements Runnable{
    public void run(){
    Thread a = new Thread(new Background(35));
    
    a.start();
        System.out.println("THRED STARTED");
        try {
            report_gen.daily_transaction_calc(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Task_manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    a.stop();
    progress_task.setValue(100);
    }
    }
    
    
     public class weekly_advance implements Runnable{
    public void run(){
    Thread a = new Thread(new Background(35));
    a.start();
    report_gen.weekly_advance_calc(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()));
    a.stop();
    progress_task.setValue(100);
    }
     }
      public class predebts_coins implements Runnable{
    public void run(){
    Thread a = new Thread(new Background(35));
    a.start();
    report_gen.pre_debt_and_coin_Update(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()));
    a.stop();
    progress_task.setValue(100);
    }
     }
    
     public class monthly_ledger implements Runnable{
    public void run(){
    Thread a = new Thread(new Background(35));
    a.start();
        try {
            report_gen.monthly_ledger_calc(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Task_manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    a.stop();
    progress_task.setValue(100);
    }  
    }

    public class Background implements Runnable {

        int delay;

        public Background(int Delay) {

            delay = Delay;

        }

        @Override
        public void run() {
            progress_task.setVisible(true);
           
            int a = (int) (Math.random() * 500);
            for (int i = 0; i <= 3000 + a; i++) {
               //  System.out.println("THRED RUNNING");
                progress_task.setValue(100 * i / 4000);
                //info.setText(100 * i / 4000+"");
                progress_task.repaint();
               // info.repaint();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    
    public class task_green_leaf implements Runnable{
    
    
    public void run(){
     String year = yearfield.getText();
        String month = datehandler.return_month_as_num(monthfield.getText());
       
     Thread DT = new Thread(new daily_trans());
        Thread WA = new Thread(new weekly_advance());
        Thread ML = new Thread(new monthly_ledger());
        Thread PDC = new Thread(new predebts_coins());
        info.setText("Daily transactions processing....");
        if(GL1.isSelected()){ DT.run(); set_task_man(year, month);}
        
          info.setText("Weekly advances list processing.....");
         if(GL2.isSelected()){WA.run();set_task_man(year, month);}
          
          info.setText("Monthly ledger processing.....");
          if(GL3.isSelected()){ML.run(); set_task_man(year, month);}
    
     info.setText("Bought Forward Balances and Coins Balances Updating.....");
     if(GL4.isSelected()){PDC.run(); set_task_man(year, month);}
    
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        GLdate1 = new javax.swing.JLabel();
        GLLabel1 = new javax.swing.JLabel();
        GL1 = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        GL2 = new javax.swing.JCheckBox();
        GLLabel2 = new javax.swing.JLabel();
        GLdate2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        GLLabel3 = new javax.swing.JLabel();
        GLdate3 = new javax.swing.JLabel();
        GL3 = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        GLdate4 = new javax.swing.JLabel();
        GLLabel4 = new javax.swing.JLabel();
        GL4 = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        GL5 = new javax.swing.JCheckBox();
        GLLabel5 = new javax.swing.JLabel();
        GLdate5 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        GLLabel6 = new javax.swing.JLabel();
        GLdate6 = new javax.swing.JLabel();
        GL6 = new javax.swing.JCheckBox();
        newmonthP6 = new javax.swing.JProgressBar();
        dailyTprog = new javax.swing.JProgressBar();
        newmonthP2 = new javax.swing.JProgressBar();
        newmonthP3 = new javax.swing.JProgressBar();
        newmonthP4 = new javax.swing.JProgressBar();
        newmonthP5 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        salarycaloverallL = new javax.swing.JLabel();
        prvdebtsP = new javax.swing.JProgressBar();
        jPanel8 = new javax.swing.JPanel();
        month = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        newmonthC = new javax.swing.JCheckBox();
        salaryCalP = new javax.swing.JProgressBar();
        salarycaloverallC = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        salarycaloverallP = new javax.swing.JProgressBar();
        newmonthP = new javax.swing.JProgressBar();
        salarycalL = new javax.swing.JLabel();
        prvdebtsC = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        workdetailsC = new javax.swing.JCheckBox();
        prvdebtsL = new javax.swing.JLabel();
        workdetailsP = new javax.swing.JProgressBar();
        newmonthL = new javax.swing.JLabel();
        workdetailsL = new javax.swing.JLabel();
        advanceP = new javax.swing.JProgressBar();
        advanceL = new javax.swing.JLabel();
        advanceC = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        MessageTex = new javax.swing.JTextArea();
        extrapayL = new javax.swing.JLabel();
        extrapayP = new javax.swing.JProgressBar();
        extrapayC = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        info = new javax.swing.JLabel();
        progress_task = new javax.swing.JProgressBar();

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("End Month Update");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        GLdate1.setText("jLabel2");

        GLLabel1.setText("jLabel2");

        GL1.setText("Daily Transactions ");
        GL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GL1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(GLLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate1)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL1)
                    .addComponent(GLLabel1)
                    .addComponent(GLdate1))
                .addContainerGap())
        );

        GL2.setText("Weekly Advances");
        GL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GL2ActionPerformed(evt);
            }
        });

        GLLabel2.setText("jLabel2");

        GLdate2.setText("jLabel2");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(GLLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate2)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL2)
                    .addComponent(GLLabel2)
                    .addComponent(GLdate2))
                .addContainerGap())
        );

        GLLabel3.setText("jLabel2");

        GLdate3.setText("jLabel2");

        GL3.setText("Monthly Ledger");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(GLLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate3)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL3)
                    .addComponent(GLLabel3)
                    .addComponent(GLdate3))
                .addContainerGap())
        );

        GLdate4.setText("jLabel2");

        GLLabel4.setText("jLabel2");

        GL4.setText("Update Bal_CF and Coins");
        GL4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GL4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL4)
                .addGap(32, 32, 32)
                .addComponent(GLLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate4)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL4)
                    .addComponent(GLLabel4)
                    .addComponent(GLdate4))
                .addContainerGap())
        );

        GL5.setText("Bank Payment List");
        GL5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GL5ActionPerformed(evt);
            }
        });

        GLLabel5.setText("jLabel2");

        GLdate5.setText("jLabel2");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(GLLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate5)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL5)
                    .addComponent(GLLabel5)
                    .addComponent(GLdate5))
                .addContainerGap())
        );

        GLLabel6.setText("jLabel2");

        GLdate6.setText("jLabel2");

        GL6.setText("Cheque Payment List");
        GL6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GL6ActionPerformed(evt);
            }
        });

        newmonthP6.setStringPainted(true);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GL6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(GLLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GLdate6)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(newmonthP6, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GL6)
                    .addComponent(GLLabel6)
                    .addComponent(GLdate6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newmonthP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dailyTprog.setStringPainted(true);

        newmonthP2.setStringPainted(true);

        newmonthP3.setStringPainted(true);

        newmonthP4.setStringPainted(true);

        newmonthP5.setStringPainted(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dailyTprog, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newmonthP2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newmonthP3, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newmonthP4, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newmonthP5, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(dailyTprog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(newmonthP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newmonthP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newmonthP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newmonthP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton1.setText("Process");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Green Leaf", jPanel4);

        salarycaloverallL.setText("Salary Calculation");

        prvdebtsP.setStringPainted(true);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        month.setText(datehandler.get_today_month());
        month.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthKeyPressed(evt);
            }
        });

        year.setText(datehandler.get_today_year());
        year.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        salaryCalP.setStringPainted(true);

        jButton5.setText("Stop");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        salarycaloverallP.setStringPainted(true);

        newmonthP.setStringPainted(true);

        salarycalL.setText("Division");

        jButton6.setText("Start");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        prvdebtsL.setText("Previous Debts");

        workdetailsP.setStringPainted(true);

        newmonthL.setText("Month Table");

        workdetailsL.setText("Work Details");

        advanceP.setStringPainted(true);

        advanceL.setText("Advance Details");

        MessageTex.setColumns(20);
        MessageTex.setRows(5);
        jScrollPane1.setViewportView(MessageTex);

        extrapayL.setText("Extra Pay Details");

        extrapayP.setStringPainted(true);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(newmonthL, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addComponent(newmonthC))
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                    .addComponent(prvdebtsL, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(prvdebtsC))
                                .addComponent(newmonthP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                    .addComponent(workdetailsL, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(workdetailsC))
                                .addComponent(prvdebtsP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(salarycaloverallP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                    .addComponent(salarycaloverallL, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(salarycaloverallC))
                                .addComponent(extrapayP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                    .addComponent(extrapayL, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(extrapayC))
                                .addComponent(advanceP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane2Layout.createSequentialGroup()
                                    .addComponent(advanceL, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(advanceC))
                                .addComponent(workdetailsP, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(salarycalL, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(salaryCalP, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newmonthL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newmonthC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newmonthP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prvdebtsL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prvdebtsC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prvdebtsP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workdetailsL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(workdetailsC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workdetailsP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(advanceL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(advanceC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(advanceP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(extrapayL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(extrapayC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(extrapayP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salarycaloverallL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salarycaloverallC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salarycaloverallP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salarycalL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salaryCalP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(salarycaloverallL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(prvdebtsP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(newmonthC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(salaryCalP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(salarycaloverallC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(salarycaloverallP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(newmonthP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(salarycalL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(prvdebtsC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(workdetailsC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(prvdebtsL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(workdetailsP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(newmonthL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(workdetailsL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(advanceP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(advanceL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(advanceC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(extrapayL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(extrapayP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(extrapayC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );

        jTabbedPane1.addTab("Payrll-Checkroll", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Accounts", jPanel6);

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

        progress_task.setStringPainted(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(info)))
                .addContainerGap(701, Short.MAX_VALUE))
            .addComponent(progress_task, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress_task, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield.requestFocus();
            yearfield.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            String year = yearfield.getText();
            String month = datehandler.return_month_as_num(monthfield.getText());
            set_task_man(year, month);

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
            String year = yearfield.getText();
            String month = datehandler.return_month_as_num(monthfield.getText());
            set_task_man(year, month);
        }
    }//GEN-LAST:event_yearfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        // dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        //  dayfield2.requestFocus();
        // dayfield2.selectAll();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void GL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GL1ActionPerformed

    private void GL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GL2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GL2ActionPerformed

    private void GL4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GL4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GL4ActionPerformed

    private void GL5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GL5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GL5ActionPerformed

    private void GL6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GL6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GL6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(update.check_update(yearfield.getText(), datehandler.return_month_as_num(monthfield.getText()))){
 
    System.out.println("Updated");
         

}
        
//       Thread a = new Thread(new task_green_leaf());
//        a.start();
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void monthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyPressed
        if (month.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Dec");
                int yr = Integer.parseInt(year.getText());

                year.setText("" + (yr - 1));
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Feb");
                month.selectAll();
            }

        } else if (month.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Jan");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Mar");
                month.selectAll();
            }

        } else if (month.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Feb");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Apr");
                month.selectAll();
            }

        } else if (month.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Mar");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("May");
                month.selectAll();
            }

        } else if (month.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Apr");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                month.setText("Jun");
                month.selectAll();
            }

        } else if (month.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("May");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Jul");
                month.selectAll();
            }

        } else if (month.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Jun");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Aug");
                month.selectAll();
            }

        } else if (month.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Jul");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Sep");
                month.selectAll();
            }

        } else if (month.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Aug");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Oct");
                month.selectAll();
            }

        } else if (month.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Sep");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Nov");
                month.selectAll();
            }

        } else if (month.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Oct");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Dec");
                month.selectAll();
            }

        } else if (month.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                month.setText("Nov");
                int yr = Integer.parseInt(year.getText());
                month.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                month.setText("Jan");
                int yr = Integer.parseInt(year.getText());

                year.setText("" + (yr + 1));
                month.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            //change.requestFocus();
            //change.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            year.requestFocus();
            year.selectAll();
        }
    }//GEN-LAST:event_monthKeyPressed

    private void yearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            year.setText("" + (Integer.parseInt(year.getText()) + 1));
            year.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            year.setText("" + (Integer.parseInt(year.getText()) - 1));
            year.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            month.requestFocus();
            month.selectAll();
        }
    }//GEN-LAST:event_yearKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       // b.stop();      // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        String st;
        if (datehandler.return_index(month.getText()) < 10) {
            st = year.getText() + "_0" + datehandler.return_index(month.getText());
        } else {
            st = year.getText() + "_" + datehandler.return_index(month.getText());
        }
        System.out.println(st);
      //  b = new Thread(new PRCR_Checkroll_Monthly_workdata_database_update_class(st));
     //   b.start();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox GL1;
    private javax.swing.JCheckBox GL2;
    private javax.swing.JCheckBox GL3;
    private javax.swing.JCheckBox GL4;
    private javax.swing.JCheckBox GL5;
    private javax.swing.JCheckBox GL6;
    private javax.swing.JLabel GLLabel1;
    private javax.swing.JLabel GLLabel2;
    private javax.swing.JLabel GLLabel3;
    private javax.swing.JLabel GLLabel4;
    private javax.swing.JLabel GLLabel5;
    private javax.swing.JLabel GLLabel6;
    private javax.swing.JLabel GLdate1;
    private javax.swing.JLabel GLdate2;
    private javax.swing.JLabel GLdate3;
    private javax.swing.JLabel GLdate4;
    private javax.swing.JLabel GLdate5;
    private javax.swing.JLabel GLdate6;
    public static javax.swing.JTextArea MessageTex;
    public static javax.swing.JCheckBox advanceC;
    public static javax.swing.JLabel advanceL;
    public static javax.swing.JProgressBar advanceP;
    public static javax.swing.JProgressBar dailyTprog;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    public static javax.swing.JCheckBox extrapayC;
    public static javax.swing.JLabel extrapayL;
    public static javax.swing.JProgressBar extrapayP;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextField month;
    private javax.swing.JTextField monthfield;
    public static javax.swing.JCheckBox newmonthC;
    public static javax.swing.JLabel newmonthL;
    public static javax.swing.JProgressBar newmonthP;
    public static javax.swing.JProgressBar newmonthP2;
    public static javax.swing.JProgressBar newmonthP3;
    public static javax.swing.JProgressBar newmonthP4;
    public static javax.swing.JProgressBar newmonthP5;
    public static javax.swing.JProgressBar newmonthP6;
    public static javax.swing.JProgressBar progress_task;
    public static javax.swing.JCheckBox prvdebtsC;
    public static javax.swing.JLabel prvdebtsL;
    public static javax.swing.JProgressBar prvdebtsP;
    public static javax.swing.JProgressBar salaryCalP;
    public static javax.swing.JLabel salarycalL;
    public static javax.swing.JCheckBox salarycaloverallC;
    public static javax.swing.JLabel salarycaloverallL;
    public static javax.swing.JProgressBar salarycaloverallP;
    public static javax.swing.JCheckBox workdetailsC;
    public static javax.swing.JLabel workdetailsL;
    public static javax.swing.JProgressBar workdetailsP;
    public static javax.swing.JTextField year;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
