
import java.awt.Desktop;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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
public class Report_GL_Transport extends javax.swing.JPanel {

    public class Background implements Runnable {

        int delay;

        public Background(int Delay) {
            delay = Delay;

        }

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
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reports_GL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    public class report_cadvance implements Runnable {

        @Override
        public void run() {
            //  jProgressBar1.setIndeterminate(true);

            Thread a = new Thread(new Background(10));

           
                try {
                    HashMap param = new HashMap();
                    a.start();
                    Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
                    Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);

                    param.put("from_date", Return_date1);
                    param.put("to_date", Return_date2);
                    param.put("USER", user.get_current_user());

                    String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

                    generate.create("GL_CADVANCE", saveloc, param, location, "GL_TR_cadvance.jrxml");
                    a.stop();
                    jProgressBar1.setValue(100);
                } catch (ParseException ex) {
                    Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
        }

        private void viewMouseClicked(java.awt.event.MouseEvent evt) {
            // jLabel4.setText("please wait..");
        }

        
    }
    
    
     public  class report_otheradd implements Runnable{
        @Override
        public void run(){
           //  jProgressBar1.setIndeterminate(true);
           
                Thread a = new Thread(new Background(5));
               
                      
            try {
                HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
                Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
               // jProgressBar1.setValue(20);
                Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);
                param.put("from_date", Return_date1);
                //param.put("p1", "gl_other_advances.`item_type`");
               // param.put("p2",supplier_id.getSelectedItem().toString());
                param.put("to_date", Return_date2);
                param.put("USER",new UserAccountControl().get_current_user());
                jProgressBar1.setValue(45);
                jProgressBar1.repaint();
                // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                a.start();
                generate.create("GL_trans", saveloc, param, location, "GL_TR_otheradd.jrxml");
                a.stop();;
                jProgressBar1.setValue(100);
            } catch (ParseException ex) {
                Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
     }
     
     
     
     
     
     public  class report_loans implements Runnable{
        @Override
        public void run(){
           //  jProgressBar1.setIndeterminate(true);
           
                Thread a = new Thread(new Background(5));
               
                      
            try {
                HashMap param = new HashMap();
                //jProgressBar1.setValue(10);
                Date Return_date1 = datechooser.Return_date(yearfield, monthfield, dayfield);
               // jProgressBar1.setValue(20);
                Date Return_date2 = datechooser.Return_date(yearfield2, monthfield2, dayfield2);
                param.put("from_date", Return_date1);
                param.put("to_date", Return_date2);
                param.put("USER",new UserAccountControl().get_current_user());
                jProgressBar1.setValue(45);
                jProgressBar1.repaint();
                // Date Return_date = datechooser.Return_date(yearfield, monthfield, dayfield);
                String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");
                a.start();
                generate.create("GL_trans", saveloc, param, location, "GL_TR_loans.jrxml");
                a.stop();;
                jProgressBar1.setValue(100);
            } catch (ParseException ex) {
                Logger.getLogger(Report_GL_trans.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        }}
     
     
     
     public class report_monthly_led implements Runnable{
      public report_monthly_led() {
             
        }

      @Override
        public void run() {

            Thread a = new Thread(new Background(55));

           // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
             double limit =dbm.checknReturnDoubleData("rate_details", "Code_name", "CASH_LIMIT", "rate");
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
          //  param.put("Cat",Cat_code.getEditor().getItem().toString());
            param.put("cash_limit", limit);
            param.put("year", yearfield.getText());
            param.put("month", datehandler.return_month_as_num(monthfield4.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield4.getText())) + " " + yearfield4.getText().toString());
            //param.put("p1", filter);
           // param.put("p2", value);
           // param.put("AscDec", asc);
            //param.put("porder", order1);
           // param.put("porder2", order2);
            // b.start();
            a.start();
           // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

           String dateNow= generate.create("Monthly_Ledger", saveloc, param, location, "tr_monthly_ledger.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "Monthly_Ledger", yearfield4.getText()+datehandler.return_month_as_num(monthfield4.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
     }
    
     
     public class report_pay implements Runnable {

       

        public report_pay() {
          

        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

            // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            //param.put("sup", Integer.parseInt(supplier_id.getSelectedItem().toString()));
            param.put("USER", user.get_current_user());
            param.put("year", yearfield.getText());
           // param.put("p1", filter);
           // param.put("p2", value);
            //param.put("AscDec", asc);
            //param.put("porder", order1);
           // param.put("porder2", order2);

            param.put("month", datehandler.return_month_as_num(monthfield4.getText()));
            param.put("ChekPay", dbm.checknReturnDoubleData("rate_details", "Code_name", "CASH_LIMIT", "rate"));

            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield4.getText())) + " " + yearfield4.getText().toString());

            // b.start();
            a.start();
            // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

            String dateNow = generate.create("PaySlip", saveloc, param, location, "GL_Play_slip_1.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "PaySlip", yearfield.getText() + datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    public class report implements Runnable {

        public report() {
        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

            Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
            param.put("year", yearfield.getText());
            param.put("month", datehandler.return_month_as_num(monthfield.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());

            // b.start();
            a.start();
            t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

           String dateNow= generate.create("Weekly_Advances_", saveloc, param, location, "Weekly_Advances.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "Weekly_Advances_", yearfield.getText()+datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
     public class report_old implements Runnable {

        public report_old() {
        }

        @Override
        public void run() {

            Thread a = new Thread(new Background(55));

           // Thread t1 = new Thread(new update(yearfield.getText(), monthfield.getText()));
            HashMap param = new HashMap();
            param.put("USER", user.get_current_user());
            param.put("year", yearfield.getText());
            param.put("month", datehandler.return_month_as_num(monthfield.getText()));
            param.put("Month", datehandler.Return_month_full(datehandler.return_index(monthfield.getText())) + " " + yearfield.getText().toString());

            // b.start();
            a.start();
           // t1.run();

            String location = dbm.checknReturnStringData("file_locations", "description", "Reports", "location");

           String dateNow= generate.create("Weekly_Advances_", saveloc, param, location, "Weekly_Advances.jrxml");
            a.stop();
            jProgressBar1.setValue(100);
            try {
                generate.savename(dateNow, "Weekly_Advances_", yearfield.getText()+datehandler.return_month_as_num(monthfield.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(Report_GL_daily_transactions.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public class update implements Runnable {

        public GL_report_generator report_gen;
        String YEAR;
        String MONTH;

        public update(String year, String month) {
            report_gen = new GL_report_generator();
            YEAR = year;
            MONTH = month;
        }

        @Override
        public void run() {
            Date_Handler date_handler = new Date_Handler();
            report_gen.weekly_advance_calc(YEAR, date_handler.return_month_as_num(MONTH));

        }
    }
    
    

    /**
     *
     */
    public Report_GL_Transport() {
        initComponents();
        jProgressBar1.setStringPainted(true);
    }
    DateChooser_text datechooser = new DateChooser_text();
    Date_Handler datehandler = new Date_Handler();
    Report_gen generate = new Report_gen();
    UserAccountControl user = new UserAccountControl();
   DatabaseManager dbm =  DatabaseManager.getDbCon();
    String saveloc = dbm.checknReturnStringData("file_locations", "description", "ReportSave", "location");
    Update update = new Update();

    public void focus() {
        //dayfield.requestFocus();
        //dayfield.selectAll();
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
        datepanel = new javax.swing.JPanel();
        monthfield4 = new javax.swing.JTextField();
        yearfield4 = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        view1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        datepanel1 = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        dayfield = new javax.swing.JTextField();
        datePicker2 = new com.michaelbaranov.microba.calendar.DatePicker();
        datepanel2 = new javax.swing.JPanel();
        monthfield2 = new javax.swing.JTextField();
        yearfield2 = new javax.swing.JTextField();
        dayfield2 = new javax.swing.JTextField();
        datePicker3 = new com.michaelbaranov.microba.calendar.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        view3 = new javax.swing.JButton();
        view4 = new javax.swing.JButton();
        view5 = new javax.swing.JButton();
        view6 = new javax.swing.JButton();
        view7 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        datepanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthfield4.setText(datehandler.get_today_month());
        monthfield4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthfield4KeyPressed(evt);
            }
        });

        yearfield4.setText(datehandler.get_today_year());
        yearfield4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearfield4KeyPressed(evt);
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
                .addComponent(monthfield4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(monthfield4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearfield4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Transport Reports");

        view1.setText("Monthly ledger");
        view1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view1MouseClicked(evt);
            }
        });
        view1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("From");

        datepanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        datePicker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanel1Layout = new javax.swing.GroupLayout(datepanel1);
        datepanel1.setLayout(datepanel1Layout);
        datepanel1Layout.setHorizontalGroup(
            datepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayfield, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(monthfield, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearfield, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datepanel1Layout.setVerticalGroup(
            datepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(datepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(datepanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datepanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        view3.setText("Pay Slip");
        view3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view3MouseClicked(evt);
            }
        });
        view3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view3ActionPerformed(evt);
            }
        });

        view4.setText("Cash Advances");
        view4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view4MouseClicked(evt);
            }
        });
        view4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view4ActionPerformed(evt);
            }
        });

        view5.setText("Other Advances");
        view5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view5MouseClicked(evt);
            }
        });
        view5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view5ActionPerformed(evt);
            }
        });

        view6.setText("Veiw");
        view6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view6MouseClicked(evt);
            }
        });
        view6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view6ActionPerformed(evt);
            }
        });

        view7.setText("Loans");
        view7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view7MouseClicked(evt);
            }
        });
        view7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(view4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(view5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(view7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(view3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(view6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void monthfield4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthfield4KeyPressed
        if (monthfield4.getText().equals("Jan")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Dec");
                int yr = Integer.parseInt(yearfield4.getText());

                yearfield4.setText("" + (yr - 1));
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Feb");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Feb")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Jan");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Mar");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Mar")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Feb");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Apr");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Apr")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Mar");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();
            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("May");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("May")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Apr");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {

                monthfield4.setText("Jun");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Jun")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("May");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Jul");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Jul")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Jun");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Aug");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Aug")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Jul");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Sep");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Sep")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Aug");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Oct");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Oct")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Sep");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Nov");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Nov")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Oct");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Dec");
                monthfield4.selectAll();
            }

        } else if (monthfield4.getText().equals("Dec")) {
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                monthfield4.setText("Nov");
                int yr = Integer.parseInt(yearfield4.getText());
                monthfield4.selectAll();

            }
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                monthfield4.setText("Jan");
                int yr = Integer.parseInt(yearfield4.getText());

                yearfield4.setText("" + (yr + 1));
                monthfield4.selectAll();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
           // dayfield.requestFocus();
            // dayfield.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            yearfield4.requestFocus();
            yearfield4.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //  dayfield2.requestFocus();
            // dayfield2.selectAll();

        }
        // update.update_month_check(view, yearfield4, monthfield4);
    }//GEN-LAST:event_monthfield4KeyPressed

    private void yearfield4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearfield4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            yearfield4.setText("" + (Integer.parseInt(yearfield4.getText()) + 1));
            yearfield4.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            yearfield4.setText("" + (Integer.parseInt(yearfield4.getText()) - 1));
            yearfield4.selectAll();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            monthfield4.requestFocus();
            monthfield4.selectAll();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  ////// ChaNGE  focus on enter////////////////
            //   dayfield2.requestFocus();
            //  dayfield2.selectAll();

        }
        // update.update_month_check(view, yearfield4, monthfield);
    }//GEN-LAST:event_yearfield4KeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        // dayfield.setText(datehandler.get_day(datef));
        monthfield4.setText(datehandler.get_month(datef));
        yearfield4.setText(datehandler.get_year(datef));
      //  dayfield2.requestFocus();
        // dayfield2.selectAll();
       //  update.update_month_check(view, yearfield, monthfield);
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void view1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view1MouseClicked

    private void view1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view1ActionPerformed
          Thread b = new Thread(new report_monthly_led());
        b.start();
    }//GEN-LAST:event_view1ActionPerformed

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

    private void datePicker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker2ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker2.getDate().getTime());

        dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        dayfield2.requestFocus();
        dayfield2.selectAll();
    }//GEN-LAST:event_datePicker2ActionPerformed

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

    private void view3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view3MouseClicked

    private void view3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view3ActionPerformed
        Thread a = new Thread(new report_pay());
        a.start();
    }//GEN-LAST:event_view3ActionPerformed

    private void view4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view4MouseClicked

    private void view4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view4ActionPerformed
         Thread a = new Thread(new report_cadvance());
        a.start();
    }//GEN-LAST:event_view4ActionPerformed

    private void view5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view5MouseClicked
       
    }//GEN-LAST:event_view5MouseClicked

    private void view5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view5ActionPerformed
         Thread c = new Thread(new report_otheradd());
        c.start();
    }//GEN-LAST:event_view5ActionPerformed

    private void view6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view6MouseClicked

    private void view6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_view6ActionPerformed

    private void view7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_view7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_view7MouseClicked

    private void view7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view7ActionPerformed
        Thread d = new Thread(new report_loans());
        d.start();
    }//GEN-LAST:event_view7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker2;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker3;
    private javax.swing.JPanel datepanel;
    private javax.swing.JPanel datepanel1;
    private javax.swing.JPanel datepanel2;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField dayfield2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField monthfield;
    private javax.swing.JTextField monthfield2;
    private javax.swing.JTextField monthfield4;
    private javax.swing.JButton view1;
    private javax.swing.JButton view3;
    private javax.swing.JButton view4;
    private javax.swing.JButton view5;
    private javax.swing.JButton view6;
    private javax.swing.JButton view7;
    private javax.swing.JTextField yearfield;
    private javax.swing.JTextField yearfield2;
    private javax.swing.JTextField yearfield4;
    // End of variables declaration//GEN-END:variables
}
