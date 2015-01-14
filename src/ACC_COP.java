
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Iddamalgoda
 */
public class ACC_COP extends javax.swing.JFrame {

    /**
     * Creates new form ACC_COP
     */
    public ACC_COP() {
        initComponents();
        dailyPanel.setVisible(true);
        monthlyPanel.setVisible(false);
        method.requestFocus();
        try {
            fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
        } catch (ParseException ex) {
            System.out.println(ex);
            Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    DatabaseManager dbm = DatabaseManager.getDbCon();

    DateChooser_text datechooser = new DateChooser_text();

    Date_Handler datehandler = new Date_Handler();

    public void save(String date, double glq, double nmtq) {
        try {
            dbm.insert("INSERT INTO account_cost_of_pay(date,green_leaf_qty,net_made_tea_qty) VALUES('" + date + "','" + glq + "','" + nmtq + "')");
        } catch (SQLException ex) {
            Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete() {
        String date = null;
        int reply = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete Entry", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if ("Daily".equals(method.getSelectedItem().toString())) {

                try {
                    date = dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield));
                } catch (ParseException ex) {
                    msg.showMessage("Invalid Date", "Cost of Pay", "info");
                    dayfield.requestFocus();
                    Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    date = dt.get_year(datechooser.Return_date(yearfield, monthfield, dayfield)) + "-" + dt.get_month_as_num(datechooser.Return_date(yearfield, monthfield, dayfield));
                } catch (ParseException ex) {
                    msg.showMessage("Invalid Date", "Cost of Pay", "info");
                    dayfield.requestFocus();
                    Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                dbm.insert("DELETE FROM account_cost_of_pay WHERE date LIKE '" + date + "'");
            } catch (SQLException ex) {
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void fillGLBars(Date date) {

        if (veiw.getSelectedItem() == "Daily") {
            String month = datehandler.get_month_as_num(date);
            String year = datehandler.get_year(date);

            String startDate = year + "-" + month;

            String endDate = year + "-" + month + "-32";

            System.out.println("Start-" + startDate);
            System.out.println("End-" + endDate);
            int arrPosition;
            double maxD = 0;
            double tot = 0;
            double value = 0;
            int days[] = new int[31];
            try {
                ResultSet query = dbm.query("SELECT * FROM account_cost_of_pay WHERE date >= '" + startDate + "' and date < '" + endDate + "' ");
                while (query.next()) {
                    value = query.getDouble("green_leaf_qty");
                    tot = tot + value;
                    if (value > maxD) {
                        maxD = value;
                    }
                    if (query.getString("date").split("-").length == 2) {
                        if (Integer.parseInt(query.getString("date").split("-")[1]) == Integer.parseInt(month)) {
                            tot = value;
                            maxD = 0;
                            Arrays.fill(days, 0);
                            break;
                        }
                    } else if (query.getString("date").split("-").length == 3) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[2]) - 1;
                        System.out.println(arrPosition);
                        days[arrPosition] = (int) value;
                        value = 0;
                    }
                }
                query.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            gl_total_daily.setText("" + tot);
            int max = (int) Math.round(maxD);

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

            // for net made tea
            arrPosition = 0;
            maxD = 0;
            tot = 0;
            value = 0;
            Arrays.fill(days, 0);
            try {
                ResultSet query = dbm.query("SELECT * FROM account_cost_of_pay WHERE date >= '" + startDate + "' and date < '" + endDate + "' ");
                while (query.next()) {
                    value = query.getDouble("net_made_tea_qty");
                    tot = tot + value;
                    if (value > maxD) {
                        maxD = value;
                    }
                    if (query.getString("date").split("-").length == 2) {
                        if (Integer.parseInt(query.getString("date").split("-")[1]) == Integer.parseInt(month)) {
                            tot = value;
                            maxD = 0;
                            Arrays.fill(days, 0);
                            break;
                        }
                    } else if (query.getString("date").split("-").length == 3) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[2]) - 1;
                        System.out.println(arrPosition);
                        days[arrPosition] = (int) value;
                        value = 0;
                    }
                }
                query.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            netMadeTeaTotal.setText("" + tot);
            max = (int) Math.round(maxD);

            jProgressBarA1.setMaximum(max);
            jProgressBarA2.setMaximum(max);
            jProgressBarA3.setMaximum(max);
            jProgressBarA4.setMaximum(max);
            jProgressBarA5.setMaximum(max);
            jProgressBarA6.setMaximum(max);
            jProgressBarA7.setMaximum(max);
            jProgressBarA8.setMaximum(max);
            jProgressBarA9.setMaximum(max);
            jProgressBarA10.setMaximum(max);
            jProgressBarA11.setMaximum(max);
            jProgressBarA12.setMaximum(max);
            jProgressBarA13.setMaximum(max);
            jProgressBarA14.setMaximum(max);
            jProgressBarA15.setMaximum(max);
            jProgressBarA16.setMaximum(max);
            jProgressBarA17.setMaximum(max);
            jProgressBarA18.setMaximum(max);
            jProgressBarA19.setMaximum(max);
            jProgressBarA20.setMaximum(max);
            jProgressBarA21.setMaximum(max);
            jProgressBarA22.setMaximum(max);
            jProgressBarA23.setMaximum(max);
            jProgressBarA24.setMaximum(max);
            jProgressBarA25.setMaximum(max);
            jProgressBarA26.setMaximum(max);
            jProgressBarA27.setMaximum(max);
            jProgressBarA28.setMaximum(max);
            jProgressBarA29.setMaximum(max);
            jProgressBarA30.setMaximum(max);
            jProgressBarA31.setMaximum(max);

            jProgressBarA1.setValue(days[0]);
            jProgressBarA1.setString(Integer.toString(days[0]));
            jProgressBarA2.setValue(days[1]);
            jProgressBarA2.setString(Integer.toString(days[1]));
            jProgressBarA3.setValue(days[2]);
            jProgressBarA3.setString(Integer.toString(days[2]));
            jProgressBarA4.setValue(days[3]);
            jProgressBarA4.setString(Integer.toString(days[3]));
            jProgressBarA5.setValue(days[4]);
            jProgressBarA5.setString(Integer.toString(days[4]));
            jProgressBarA6.setValue(days[5]);
            jProgressBarA6.setString(Integer.toString(days[5]));
            jProgressBarA7.setValue(days[6]);
            jProgressBarA7.setString(Integer.toString(days[6]));
            jProgressBarA8.setValue(days[7]);
            jProgressBarA8.setString(Integer.toString(days[7]));
            jProgressBarA9.setValue(days[8]);
            jProgressBarA9.setString(Integer.toString(days[8]));
            jProgressBarA10.setValue(days[9]);
            jProgressBarA10.setString(Integer.toString(days[9]));
            jProgressBarA11.setValue(days[10]);
            jProgressBarA11.setString(Integer.toString(days[10]));
            jProgressBarA12.setValue(days[11]);
            jProgressBarA12.setString(Integer.toString(days[11]));
            jProgressBarA13.setValue(days[12]);
            jProgressBarA13.setString(Integer.toString(days[12]));
            jProgressBarA14.setValue(days[13]);
            jProgressBarA14.setString(Integer.toString(days[13]));
            jProgressBarA15.setValue(days[14]);
            jProgressBarA15.setString(Integer.toString(days[14]));
            jProgressBarA16.setValue(days[15]);
            jProgressBarA16.setString(Integer.toString(days[15]));
            jProgressBarA17.setValue(days[16]);
            jProgressBarA17.setString(Integer.toString(days[16]));
            jProgressBarA18.setValue(days[17]);
            jProgressBarA18.setString(Integer.toString(days[17]));
            jProgressBarA19.setValue(days[18]);
            jProgressBarA19.setString(Integer.toString(days[18]));
            jProgressBarA20.setValue(days[19]);
            jProgressBarA20.setString(Integer.toString(days[19]));
            jProgressBarA21.setValue(days[20]);
            jProgressBarA21.setString(Integer.toString(days[20]));
            jProgressBarA22.setValue(days[21]);
            jProgressBarA22.setString(Integer.toString(days[21]));
            jProgressBarA23.setValue(days[22]);
            jProgressBarA23.setString(Integer.toString(days[22]));
            jProgressBarA24.setValue(days[23]);
            jProgressBarA24.setString(Integer.toString(days[23]));
            jProgressBarA25.setValue(days[24]);
            jProgressBarA25.setString(Integer.toString(days[24]));
            jProgressBarA26.setValue(days[25]);
            jProgressBarA26.setString(Integer.toString(days[25]));
            jProgressBarA27.setValue(days[26]);
            jProgressBarA27.setString(Integer.toString(days[26]));
            jProgressBarA28.setValue(days[27]);
            jProgressBarA28.setString(Integer.toString(days[27]));
            jProgressBarA29.setValue(days[28]);
            jProgressBarA29.setString(Integer.toString(days[28]));
            jProgressBarA30.setValue(days[29]);
            jProgressBarA30.setString(Integer.toString(days[29]));
            jProgressBarA31.setValue(days[30]);
            jProgressBarA31.setString(Integer.toString(days[30]));

        } else {

            String month = datehandler.get_month_as_num(date);
            String year = datehandler.get_year(date);

            String startDate = year + "-01";

            String endDate = year + "-12-32";

            System.out.println("Start-" + startDate);
            System.out.println("End-" + endDate);
            int arrPosition;
            double maxD = 0;
            double tot = 0;
            double value = 0;
            int days[] = new int[12];
            int chk[] = new int[12];
            Arrays.fill(chk, 0);
            Arrays.fill(days, 0);
            try {
                ResultSet query = dbm.query("SELECT * FROM account_cost_of_pay WHERE date >= '" + startDate + "' and date < '" + endDate + "' ");
                while (query.next()) {
                    value = query.getDouble("green_leaf_qty");

                    if (query.getString("date").split("-").length == 2) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[1]) - 1;
                        days[arrPosition] = (int) value;
                        chk[arrPosition] = 1;
                        value = 0;
                    } else if (query.getString("date").split("-").length == 3) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[1]) - 1;
                        if (chk[arrPosition] == 0) {
                            days[arrPosition] = days[arrPosition] + (int) value;
                            value = 0;
                        }
                    }
                }
                query.close();
                for (int i = 0; i < 12; i++) {
                    tot = tot + days[i];
                    if (maxD < days[i]) {
                        maxD = days[i];
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            glDailyMonthly.setText("" + tot);
            int max = (int) Math.round(maxD);

            jProgressBarM1.setMaximum(max);
            jProgressBarM2.setMaximum(max);
            jProgressBarM3.setMaximum(max);
            jProgressBarM4.setMaximum(max);
            jProgressBarM5.setMaximum(max);
            jProgressBarM6.setMaximum(max);
            jProgressBarM7.setMaximum(max);
            jProgressBarM8.setMaximum(max);
            jProgressBarM9.setMaximum(max);
            jProgressBarM10.setMaximum(max);
            jProgressBarM11.setMaximum(max);
            jProgressBarM12.setMaximum(max);

            jProgressBarM1.setValue(days[0]);
            jProgressBarM1.setString(Integer.toString(days[0]));
            jProgressBarM2.setValue(days[1]);
            jProgressBarM2.setString(Integer.toString(days[1]));
            jProgressBarM3.setValue(days[2]);
            jProgressBarM3.setString(Integer.toString(days[2]));
            jProgressBarM4.setValue(days[3]);
            jProgressBarM4.setString(Integer.toString(days[3]));
            jProgressBarM5.setValue(days[4]);
            jProgressBarM5.setString(Integer.toString(days[4]));
            jProgressBarM6.setValue(days[5]);
            jProgressBarM6.setString(Integer.toString(days[5]));
            jProgressBarM7.setValue(days[6]);
            jProgressBarM7.setString(Integer.toString(days[6]));
            jProgressBarM8.setValue(days[7]);
            jProgressBarM8.setString(Integer.toString(days[7]));
            jProgressBarM9.setValue(days[8]);
            jProgressBarM9.setString(Integer.toString(days[8]));
            jProgressBarM10.setValue(days[9]);
            jProgressBarM10.setString(Integer.toString(days[9]));
            jProgressBarM11.setValue(days[10]);
            jProgressBarM11.setString(Integer.toString(days[10]));
            jProgressBarM12.setValue(days[11]);
            jProgressBarM12.setString(Integer.toString(days[11]));

            // for net made tea
            arrPosition = 0;
            maxD = 0;
            tot = 0;
            value = 0;
            Arrays.fill(chk, 0);
            Arrays.fill(days, 0);
            try {
                ResultSet query = dbm.query("SELECT * FROM account_cost_of_pay WHERE date >= '" + startDate + "' and date < '" + endDate + "' ");
                while (query.next()) {
                    value = query.getDouble("net_made_tea_qty");

                    if (query.getString("date").split("-").length == 2) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[1]) - 1;
                        days[arrPosition] = (int) value;
                        chk[arrPosition] = 1;
                        value = 0;
                    } else if (query.getString("date").split("-").length == 3) {
                        arrPosition = Integer.parseInt(query.getString("date").split("-")[1]) - 1;
                        if (chk[arrPosition] == 0) {
                            days[arrPosition] = days[arrPosition] + (int) value;
                            value = 0;
                        }
                    }
                }
                query.close();
                for (int i = 0; i < 12; i++) {
                    tot = tot + days[i];
                    if (maxD < days[i]) {
                        maxD = days[i];
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            netMadeTeaMonthlyTotal.setText("" + tot);
            max = (int) Math.round(maxD);

            jProgressBarN1.setMaximum(max);
            jProgressBarN2.setMaximum(max);
            jProgressBarN3.setMaximum(max);
            jProgressBarN4.setMaximum(max);
            jProgressBarN5.setMaximum(max);
            jProgressBarN6.setMaximum(max);
            jProgressBarN7.setMaximum(max);
            jProgressBarN8.setMaximum(max);
            jProgressBarN9.setMaximum(max);
            jProgressBarN10.setMaximum(max);
            jProgressBarN11.setMaximum(max);
            jProgressBarN12.setMaximum(max);

            jProgressBarN1.setValue(days[0]);
            jProgressBarN1.setString(Integer.toString(days[0]));
            jProgressBarN2.setValue(days[1]);
            jProgressBarN2.setString(Integer.toString(days[1]));
            jProgressBarN3.setValue(days[2]);
            jProgressBarN3.setString(Integer.toString(days[2]));
            jProgressBarN4.setValue(days[3]);
            jProgressBarN4.setString(Integer.toString(days[3]));
            jProgressBarN5.setValue(days[4]);
            jProgressBarN5.setString(Integer.toString(days[4]));
            jProgressBarN6.setValue(days[5]);
            jProgressBarN6.setString(Integer.toString(days[5]));
            jProgressBarN7.setValue(days[6]);
            jProgressBarN7.setString(Integer.toString(days[6]));
            jProgressBarN8.setValue(days[7]);
            jProgressBarN8.setString(Integer.toString(days[7]));
            jProgressBarN9.setValue(days[8]);
            jProgressBarN9.setString(Integer.toString(days[8]));
            jProgressBarN10.setValue(days[9]);
            jProgressBarN10.setString(Integer.toString(days[9]));
            jProgressBarN11.setValue(days[10]);
            jProgressBarN11.setString(Integer.toString(days[10]));
            jProgressBarN12.setValue(days[11]);
            jProgressBarN12.setString(Integer.toString(days[11]));
        }

    }
    MessageBox msg = new MessageBox();

    public void fill_data(String date) {

        String arr[] = date.split("-");
        String monthYear = arr[0] + "-" + arr[1];
        if (dbm.checkWhetherDataExists("account_cost_of_pay", "date", monthYear) == 1 && method.getSelectedItem().toString() == "Daily") {
            msg.showMessage("Data for this month has already been entered under monthly method. If you want to re-enter,please delete the previous monthly entry", "Cost of Pay", "info");
            greenLeafQty.setEnabled(false);
            netMadeTeaQty.setEnabled(false);
        } else if (dbm.checkWhetherDataExists("account_cost_of_pay", "date", date) == 1 && method.getSelectedItem().toString() == "Daily") {
            greenLeafQty.setText(dbm.checknReturnData("account_cost_of_pay", "date", date, "green_leaf_qty"));
            netMadeTeaQty.setText(dbm.checknReturnData("account_cost_of_pay", "date", date, "net_made_tea_qty"));
            greenLeafQty.setEnabled(true);
            netMadeTeaQty.setEnabled(true);
            saveButton.setEnabled(false);
        } else if (dbm.checkWhetherDataExists("account_cost_of_pay", "date", monthYear) == 1 && method.getSelectedItem().toString() == "Monthly") {
            greenLeafQty.setText(dbm.checknReturnData("account_cost_of_pay", "date", monthYear, "green_leaf_qty"));
            netMadeTeaQty.setText(dbm.checknReturnData("account_cost_of_pay", "date", monthYear, "net_made_tea_qty"));
            greenLeafQty.setEnabled(true);
            netMadeTeaQty.setEnabled(true);
            saveButton.setEnabled(false);
        } else {
            greenLeafQty.setEnabled(true);
            netMadeTeaQty.setEnabled(true);
            saveButton.setEnabled(true);
            greenLeafQty.setText(null);
            netMadeTeaQty.setText(null);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        method = new javax.swing.JComboBox();
        datepanel = new javax.swing.JPanel();
        monthfield = new javax.swing.JTextField();
        yearfield = new javax.swing.JTextField();
        dayfield = new javax.swing.JTextField();
        datePicker1 = new com.michaelbaranov.microba.calendar.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        netMadeTeaQty = new javax.swing.JTextField();
        greenLeafQty = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        monthlyPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jProgressBarM2 = new javax.swing.JProgressBar();
        jProgressBarM10 = new javax.swing.JProgressBar();
        jProgressBarM11 = new javax.swing.JProgressBar();
        jProgressBarM12 = new javax.swing.JProgressBar();
        jProgressBarM1 = new javax.swing.JProgressBar();
        jProgressBarM8 = new javax.swing.JProgressBar();
        jProgressBarM3 = new javax.swing.JProgressBar();
        jProgressBarM4 = new javax.swing.JProgressBar();
        jProgressBarM5 = new javax.swing.JProgressBar();
        jProgressBarM7 = new javax.swing.JProgressBar();
        jProgressBarM9 = new javax.swing.JProgressBar();
        jProgressBarM6 = new javax.swing.JProgressBar();
        day109 = new javax.swing.JLabel();
        day110 = new javax.swing.JLabel();
        day111 = new javax.swing.JLabel();
        day112 = new javax.swing.JLabel();
        day113 = new javax.swing.JLabel();
        day114 = new javax.swing.JLabel();
        day115 = new javax.swing.JLabel();
        day116 = new javax.swing.JLabel();
        day117 = new javax.swing.JLabel();
        day118 = new javax.swing.JLabel();
        day119 = new javax.swing.JLabel();
        day120 = new javax.swing.JLabel();
        glDailyMonthly = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jProgressBarN2 = new javax.swing.JProgressBar();
        jProgressBarN10 = new javax.swing.JProgressBar();
        jProgressBarN11 = new javax.swing.JProgressBar();
        jProgressBarN12 = new javax.swing.JProgressBar();
        jProgressBarN1 = new javax.swing.JProgressBar();
        jProgressBarN8 = new javax.swing.JProgressBar();
        jProgressBarN3 = new javax.swing.JProgressBar();
        jProgressBarN4 = new javax.swing.JProgressBar();
        jProgressBarN5 = new javax.swing.JProgressBar();
        jProgressBarN7 = new javax.swing.JProgressBar();
        jProgressBarN9 = new javax.swing.JProgressBar();
        jProgressBarN6 = new javax.swing.JProgressBar();
        day121 = new javax.swing.JLabel();
        day122 = new javax.swing.JLabel();
        day123 = new javax.swing.JLabel();
        day124 = new javax.swing.JLabel();
        day125 = new javax.swing.JLabel();
        day126 = new javax.swing.JLabel();
        day127 = new javax.swing.JLabel();
        day128 = new javax.swing.JLabel();
        day129 = new javax.swing.JLabel();
        day130 = new javax.swing.JLabel();
        day131 = new javax.swing.JLabel();
        day132 = new javax.swing.JLabel();
        netMadeTeaMonthlyTotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        dailyPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jProgressBarA18 = new javax.swing.JProgressBar();
        jProgressBarA23 = new javax.swing.JProgressBar();
        jProgressBarA21 = new javax.swing.JProgressBar();
        jProgressBarA17 = new javax.swing.JProgressBar();
        jProgressBarA20 = new javax.swing.JProgressBar();
        jProgressBarA19 = new javax.swing.JProgressBar();
        jProgressBarA25 = new javax.swing.JProgressBar();
        jProgressBarA24 = new javax.swing.JProgressBar();
        jProgressBarA28 = new javax.swing.JProgressBar();
        jProgressBarA27 = new javax.swing.JProgressBar();
        jProgressBarA26 = new javax.swing.JProgressBar();
        jProgressBarA22 = new javax.swing.JProgressBar();
        jProgressBarA31 = new javax.swing.JProgressBar();
        jProgressBarA30 = new javax.swing.JProgressBar();
        jProgressBarA29 = new javax.swing.JProgressBar();
        day32 = new javax.swing.JLabel();
        day33 = new javax.swing.JLabel();
        day34 = new javax.swing.JLabel();
        day35 = new javax.swing.JLabel();
        day36 = new javax.swing.JLabel();
        day37 = new javax.swing.JLabel();
        day38 = new javax.swing.JLabel();
        day39 = new javax.swing.JLabel();
        day40 = new javax.swing.JLabel();
        day41 = new javax.swing.JLabel();
        day42 = new javax.swing.JLabel();
        day43 = new javax.swing.JLabel();
        day44 = new javax.swing.JLabel();
        day45 = new javax.swing.JLabel();
        day46 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        netMadeTeaTotal = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jProgressBarA16 = new javax.swing.JProgressBar();
        jProgressBarA2 = new javax.swing.JProgressBar();
        jProgressBarA10 = new javax.swing.JProgressBar();
        jProgressBarA11 = new javax.swing.JProgressBar();
        jProgressBarA15 = new javax.swing.JProgressBar();
        jProgressBarA12 = new javax.swing.JProgressBar();
        jProgressBarA1 = new javax.swing.JProgressBar();
        jProgressBarA8 = new javax.swing.JProgressBar();
        jProgressBarA13 = new javax.swing.JProgressBar();
        jProgressBarA3 = new javax.swing.JProgressBar();
        jProgressBarA4 = new javax.swing.JProgressBar();
        jProgressBarA5 = new javax.swing.JProgressBar();
        jProgressBarA7 = new javax.swing.JProgressBar();
        jProgressBarA14 = new javax.swing.JProgressBar();
        jProgressBarA9 = new javax.swing.JProgressBar();
        jProgressBarA6 = new javax.swing.JProgressBar();
        day47 = new javax.swing.JLabel();
        day48 = new javax.swing.JLabel();
        day49 = new javax.swing.JLabel();
        day50 = new javax.swing.JLabel();
        day51 = new javax.swing.JLabel();
        day52 = new javax.swing.JLabel();
        day53 = new javax.swing.JLabel();
        day54 = new javax.swing.JLabel();
        day55 = new javax.swing.JLabel();
        day56 = new javax.swing.JLabel();
        day57 = new javax.swing.JLabel();
        day58 = new javax.swing.JLabel();
        day59 = new javax.swing.JLabel();
        day60 = new javax.swing.JLabel();
        day61 = new javax.swing.JLabel();
        day62 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        jLabel15 = new javax.swing.JLabel();
        gl_total_daily = new javax.swing.JTextField();
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
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        veiw = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 1647483647));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cost Of Pay");

        jLabel2.setText("Enteriing Method ");

        method.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Monthly" }));
        method.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                methodItemStateChanged(evt);
            }
        });
        method.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                methodActionPerformed(evt);
            }
        });
        method.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                methodKeyPressed(evt);
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

        jLabel3.setText("Date");

        jLabel4.setText("Green Leaf Quantity");

        jLabel5.setText("Net made tea Quantity");

        netMadeTeaQty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        netMadeTeaQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        netMadeTeaQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netMadeTeaQtyActionPerformed(evt);
            }
        });
        netMadeTeaQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                netMadeTeaQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                netMadeTeaQtyKeyReleased(evt);
            }
        });

        greenLeafQty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        greenLeafQty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        greenLeafQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenLeafQtyActionPerformed(evt);
            }
        });
        greenLeafQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                greenLeafQtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                greenLeafQtyKeyReleased(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        saveButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                saveButtonFocusGained(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBarM2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM2.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM2.setValue(50);
        jProgressBarM2.setStringPainted(true);

        jProgressBarM10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM10.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM10.setValue(50);
        jProgressBarM10.setStringPainted(true);

        jProgressBarM11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM11.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM11.setValue(50);
        jProgressBarM11.setStringPainted(true);

        jProgressBarM12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM12.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM12.setValue(50);
        jProgressBarM12.setStringPainted(true);

        jProgressBar1.setString("12");
        jProgressBarM1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM1.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM1.setValue(50);
        jProgressBarM1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jProgressBarM1.setStringPainted(true);

        jProgressBarM8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM8.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM8.setValue(50);
        jProgressBarM8.setStringPainted(true);

        jProgressBarM3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM3.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM3.setValue(50);
        jProgressBarM3.setStringPainted(true);

        jProgressBarM4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM4.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM4.setValue(50);
        jProgressBarM4.setStringPainted(true);

        jProgressBarM5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM5.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM5.setValue(50);
        jProgressBarM5.setStringPainted(true);

        jProgressBarM7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM7.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM7.setValue(50);
        jProgressBarM7.setStringPainted(true);

        jProgressBarM9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM9.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM9.setValue(50);
        jProgressBarM9.setStringPainted(true);

        jProgressBarM6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarM6.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarM6.setValue(50);
        jProgressBarM6.setStringPainted(true);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarM12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarM1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jProgressBarM1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarM12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        day109.setText("Jan");

        day110.setText("Feb");

        day111.setText("March");

        day112.setText("April");

        day113.setText("May");

        day114.setText("June");

        day115.setText("July");

        day116.setText("Aug");

        day117.setText("Sep");

        day118.setText("Oct");

        day119.setText("Nov");

        day120.setText("Dec");

        jLabel18.setText("Total");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(day111, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day113, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day112, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day114, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day118, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day120, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day119, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day110, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day109, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day115, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day116, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day117, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(glDailyMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(day109)
                        .addGap(18, 18, 18)
                        .addComponent(day110)
                        .addGap(18, 18, 18)
                        .addComponent(day111)
                        .addGap(18, 18, 18)
                        .addComponent(day112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day113)
                        .addGap(18, 18, 18)
                        .addComponent(day114)
                        .addGap(18, 18, 18)
                        .addComponent(day115)
                        .addGap(18, 18, 18)
                        .addComponent(day116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day117)
                        .addGap(11, 11, 11)
                        .addComponent(day118)
                        .addGap(18, 18, 18)
                        .addComponent(day119)
                        .addGap(18, 18, 18)
                        .addComponent(day120))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(glDailyMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Green Leaf");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Made Tea");

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBarN2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN2.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN2.setValue(50);
        jProgressBarN2.setStringPainted(true);

        jProgressBarN10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN10.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN10.setValue(50);
        jProgressBarN10.setStringPainted(true);

        jProgressBarN11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN11.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN11.setValue(50);
        jProgressBarN11.setStringPainted(true);

        jProgressBarN12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN12.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN12.setValue(50);
        jProgressBarN12.setStringPainted(true);

        jProgressBar1.setString("12");
        jProgressBarN1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN1.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN1.setValue(50);
        jProgressBarN1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jProgressBarN1.setStringPainted(true);

        jProgressBarN8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN8.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN8.setValue(50);
        jProgressBarN8.setStringPainted(true);

        jProgressBarN3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN3.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN3.setValue(50);
        jProgressBarN3.setStringPainted(true);

        jProgressBarN4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN4.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN4.setValue(50);
        jProgressBarN4.setStringPainted(true);

        jProgressBarN5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN5.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN5.setValue(50);
        jProgressBarN5.setStringPainted(true);

        jProgressBarN7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN7.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN7.setValue(50);
        jProgressBarN7.setStringPainted(true);

        jProgressBarN9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN9.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN9.setValue(50);
        jProgressBarN9.setStringPainted(true);

        jProgressBarN6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarN6.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarN6.setValue(50);
        jProgressBarN6.setStringPainted(true);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarN12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarN1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jProgressBarN1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarN12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        day121.setText("Jan");

        day122.setText("Feb");

        day123.setText("March");

        day124.setText("April");

        day125.setText("May");

        day126.setText("June");

        day127.setText("July");

        day128.setText("Aug");

        day129.setText("Sep");

        day130.setText("Oct");

        day131.setText("Nov");

        day132.setText("Dec");

        jLabel19.setText("Total");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(day123, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day125, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day124, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day126, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day130, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day132, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day131, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day122, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day121, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day127, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day128, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day129, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(netMadeTeaMonthlyTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(day121)
                        .addGap(18, 18, 18)
                        .addComponent(day122)
                        .addGap(18, 18, 18)
                        .addComponent(day123)
                        .addGap(18, 18, 18)
                        .addComponent(day124)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day125)
                        .addGap(18, 18, 18)
                        .addComponent(day126)
                        .addGap(18, 18, 18)
                        .addComponent(day127)
                        .addGap(18, 18, 18)
                        .addComponent(day128)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day129)
                        .addGap(11, 11, 11)
                        .addComponent(day130)
                        .addGap(18, 18, 18)
                        .addComponent(day131)
                        .addGap(18, 18, 18)
                        .addComponent(day132))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(netMadeTeaMonthlyTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout monthlyPanelLayout = new javax.swing.GroupLayout(monthlyPanel);
        monthlyPanel.setLayout(monthlyPanelLayout);
        monthlyPanelLayout.setHorizontalGroup(
            monthlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthlyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(monthlyPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(51, 51, 51))
        );
        monthlyPanelLayout.setVerticalGroup(
            monthlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, monthlyPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(monthlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(monthlyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBarA18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA18.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA18.setValue(50);
        jProgressBarA18.setStringPainted(true);

        jProgressBarA23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA23.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA23.setValue(50);
        jProgressBarA23.setStringPainted(true);

        jProgressBarA21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA21.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA21.setValue(50);
        jProgressBarA21.setStringPainted(true);

        jProgressBarA17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA17.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA17.setValue(50);
        jProgressBarA17.setStringPainted(true);

        jProgressBarA20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA20.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA20.setValue(50);
        jProgressBarA20.setStringPainted(true);

        jProgressBarA19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA19.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA19.setValue(50);
        jProgressBarA19.setStringPainted(true);

        jProgressBarA25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA25.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA25.setValue(50);
        jProgressBarA25.setStringPainted(true);

        jProgressBarA24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA24.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA24.setValue(50);
        jProgressBarA24.setStringPainted(true);

        jProgressBarA28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA28.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA28.setValue(50);
        jProgressBarA28.setStringPainted(true);

        jProgressBarA27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA27.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA27.setValue(50);
        jProgressBarA27.setStringPainted(true);

        jProgressBarA26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA26.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA26.setValue(50);
        jProgressBarA26.setStringPainted(true);

        jProgressBarA22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA22.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA22.setValue(50);
        jProgressBarA22.setStringPainted(true);

        jProgressBarA31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA31.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA31.setValue(50);
        jProgressBarA31.setStringPainted(true);

        jProgressBarA30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA30.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA30.setValue(50);
        jProgressBarA30.setStringPainted(true);

        jProgressBarA29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA29.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA29.setValue(50);
        jProgressBarA29.setStringPainted(true);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarA30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA31, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jProgressBarA17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA25, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA31, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        day32.setText("17");

        day33.setText("18");

        day34.setText("19");

        day35.setText("20");

        day36.setText("21");

        day37.setText("22");

        day38.setText("23");

        day39.setText("24");

        day40.setText("25");

        day41.setText("26");

        day42.setText("27");

        day43.setText("28");

        day44.setText("29");

        day45.setText("30");

        day46.setText("31");

        jLabel16.setText("Total");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(day32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day34, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day35, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day36, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day38, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day39, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day40, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day41, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day42, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day44, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day45, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(day46, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(netMadeTeaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(day32)
                        .addGap(18, 18, 18)
                        .addComponent(day33)
                        .addGap(18, 18, 18)
                        .addComponent(day34)
                        .addGap(18, 18, 18)
                        .addComponent(day35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day36)
                        .addGap(18, 18, 18)
                        .addComponent(day37)
                        .addGap(18, 18, 18)
                        .addComponent(day38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day39)
                        .addGap(18, 18, 18)
                        .addComponent(day40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day41)
                        .addGap(18, 18, 18)
                        .addComponent(day42)
                        .addGap(18, 18, 18)
                        .addComponent(day43)
                        .addGap(18, 18, 18)
                        .addComponent(day44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day46)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netMadeTeaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        jProgressBarA16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA16.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA16.setValue(50);
        jProgressBarA16.setStringPainted(true);

        jProgressBarA2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA2.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA2.setValue(50);
        jProgressBarA2.setStringPainted(true);

        jProgressBarA10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA10.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA10.setValue(50);
        jProgressBarA10.setStringPainted(true);

        jProgressBarA11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA11.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA11.setValue(50);
        jProgressBarA11.setStringPainted(true);

        jProgressBarA15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA15.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA15.setValue(50);
        jProgressBarA15.setStringPainted(true);

        jProgressBarA12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA12.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA12.setValue(50);
        jProgressBarA12.setStringPainted(true);

        jProgressBar1.setString("12");
        jProgressBarA1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA1.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA1.setValue(50);
        jProgressBarA1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jProgressBarA1.setStringPainted(true);

        jProgressBarA8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA8.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA8.setValue(50);
        jProgressBarA8.setStringPainted(true);

        jProgressBarA13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA13.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA13.setValue(50);
        jProgressBarA13.setStringPainted(true);

        jProgressBarA3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA3.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA3.setValue(50);
        jProgressBarA3.setStringPainted(true);

        jProgressBarA4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA4.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA4.setValue(50);
        jProgressBarA4.setStringPainted(true);

        jProgressBarA5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA5.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA5.setValue(50);
        jProgressBarA5.setStringPainted(true);

        jProgressBarA7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA7.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA7.setValue(50);
        jProgressBarA7.setStringPainted(true);

        jProgressBarA14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA14.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA14.setValue(50);
        jProgressBarA14.setStringPainted(true);

        jProgressBarA9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA9.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA9.setValue(50);
        jProgressBarA9.setStringPainted(true);

        jProgressBarA6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBarA6.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBarA6.setValue(50);
        jProgressBarA6.setStringPainted(true);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBarA15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jProgressBarA1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jProgressBarA1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarA16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        day47.setText("1");

        day48.setText("2");

        day49.setText("3");

        day50.setText("4");

        day51.setText("5");

        day52.setText("6");

        day53.setText("7");

        day54.setText("8");

        day55.setText("9");

        day56.setText("10");

        day57.setText("11");

        day58.setText("12");

        day59.setText("13");

        day60.setText("14");

        day61.setText("15");

        day62.setText("16");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day47, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(day55)
                        .addGap(15, 15, 15)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(day47)
                .addGap(18, 18, 18)
                .addComponent(day48)
                .addGap(18, 18, 18)
                .addComponent(day49)
                .addGap(18, 18, 18)
                .addComponent(day50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day51)
                .addGap(18, 18, 18)
                .addComponent(day52)
                .addGap(18, 18, 18)
                .addComponent(day53)
                .addGap(18, 18, 18)
                .addComponent(day54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day55)
                .addGap(11, 11, 11)
                .addComponent(day56)
                .addGap(18, 18, 18)
                .addComponent(day57)
                .addGap(18, 18, 18)
                .addComponent(day58)
                .addGap(18, 18, 18)
                .addComponent(day59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day61)
                .addGap(18, 18, 18)
                .addComponent(day62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Made Tea");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBar18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar18.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar18.setValue(50);
        jProgressBar18.setStringPainted(true);

        jProgressBar23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar23.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar23.setValue(50);
        jProgressBar23.setStringPainted(true);

        jProgressBar21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar21.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar21.setValue(50);
        jProgressBar21.setStringPainted(true);

        jProgressBar17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar17.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar17.setValue(50);
        jProgressBar17.setStringPainted(true);

        jProgressBar20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar20.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar20.setValue(50);
        jProgressBar20.setStringPainted(true);

        jProgressBar19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar19.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar19.setValue(50);
        jProgressBar19.setStringPainted(true);

        jProgressBar25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar25.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar25.setValue(50);
        jProgressBar25.setStringPainted(true);

        jProgressBar24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar24.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar24.setValue(50);
        jProgressBar24.setStringPainted(true);

        jProgressBar28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar28.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar28.setValue(50);
        jProgressBar28.setStringPainted(true);

        jProgressBar27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar27.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar27.setValue(50);
        jProgressBar27.setStringPainted(true);

        jProgressBar26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar26.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar26.setValue(50);
        jProgressBar26.setStringPainted(true);

        jProgressBar22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar22.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar22.setValue(50);
        jProgressBar22.setStringPainted(true);

        jProgressBar31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar31.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar31.setValue(50);
        jProgressBar31.setStringPainted(true);

        jProgressBar30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar30.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar30.setValue(50);
        jProgressBar30.setStringPainted(true);

        jProgressBar29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                .addComponent(jProgressBar17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar25, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar31, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel15.setText("Total");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
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
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gl_total_daily, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(day17)
                        .addGap(18, 18, 18)
                        .addComponent(day18)
                        .addGap(18, 18, 18)
                        .addComponent(day19)
                        .addGap(18, 18, 18)
                        .addComponent(day20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day21)
                        .addGap(18, 18, 18)
                        .addComponent(day22)
                        .addGap(18, 18, 18)
                        .addComponent(day23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day24)
                        .addGap(18, 18, 18)
                        .addComponent(day25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day26)
                        .addGap(18, 18, 18)
                        .addComponent(day27)
                        .addGap(18, 18, 18)
                        .addComponent(day28)
                        .addGap(18, 18, 18)
                        .addComponent(day29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(day31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gl_total_daily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        jProgressBar16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar16.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar16.setValue(50);
        jProgressBar16.setStringPainted(true);

        jProgressBar2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar2.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar2.setValue(50);
        jProgressBar2.setStringPainted(true);

        jProgressBar10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar10.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar10.setValue(50);
        jProgressBar10.setStringPainted(true);

        jProgressBar11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar11.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar11.setValue(50);
        jProgressBar11.setStringPainted(true);

        jProgressBar15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar15.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar15.setValue(50);
        jProgressBar15.setStringPainted(true);

        jProgressBar12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar12.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar12.setValue(50);
        jProgressBar12.setStringPainted(true);

        jProgressBar1.setString("12");
        jProgressBar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar1.setValue(50);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jProgressBar1.setStringPainted(true);

        jProgressBar8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar8.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar8.setValue(50);
        jProgressBar8.setStringPainted(true);

        jProgressBar13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar13.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar13.setValue(50);
        jProgressBar13.setStringPainted(true);

        jProgressBar3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar3.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar3.setValue(50);
        jProgressBar3.setStringPainted(true);

        jProgressBar4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar4.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar4.setValue(50);
        jProgressBar4.setStringPainted(true);

        jProgressBar5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar5.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar5.setValue(50);
        jProgressBar5.setStringPainted(true);

        jProgressBar7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar7.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar7.setValue(50);
        jProgressBar7.setStringPainted(true);

        jProgressBar14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar14.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar14.setValue(50);
        jProgressBar14.setStringPainted(true);

        jProgressBar9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jProgressBar9.setForeground(new java.awt.Color(0, 0, 0));
        jProgressBar9.setValue(50);
        jProgressBar9.setStringPainted(true);

        jProgressBar6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(day10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(day3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(day8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(day9)
                        .addGap(15, 15, 15)))
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
                .addGap(18, 18, 18)
                .addComponent(day2)
                .addGap(18, 18, 18)
                .addComponent(day3)
                .addGap(18, 18, 18)
                .addComponent(day4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day5)
                .addGap(18, 18, 18)
                .addComponent(day6)
                .addGap(18, 18, 18)
                .addComponent(day7)
                .addGap(18, 18, 18)
                .addComponent(day8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day9)
                .addGap(11, 11, 11)
                .addComponent(day10)
                .addGap(18, 18, 18)
                .addComponent(day11)
                .addGap(18, 18, 18)
                .addComponent(day12)
                .addGap(18, 18, 18)
                .addComponent(day13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(day15)
                .addGap(18, 18, 18)
                .addComponent(day16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Green Leaf");

        javax.swing.GroupLayout dailyPanelLayout = new javax.swing.GroupLayout(dailyPanel);
        dailyPanel.setLayout(dailyPanelLayout);
        dailyPanelLayout.setHorizontalGroup(
            dailyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dailyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dailyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dailyPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dailyPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(113, 113, 113)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dailyPanelLayout.setVerticalGroup(
            dailyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dailyPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(dailyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(dailyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(monthlyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dailyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dailyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthlyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel10.setText("View Details");

        veiw.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Monthly" }));
        veiw.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                veiwItemStateChanged(evt);
            }
        });
        veiw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veiwActionPerformed(evt);
            }
        });
        veiw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                veiwKeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel10))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(greenLeafQty, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(veiw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(netMadeTeaQty, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(55, 55, 55))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(datepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel3)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(greenLeafQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(netMadeTeaQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(veiw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(132, 132, 132))))
        );

        pack();
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

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ////// ChaNGE  focus on enter////////////////
            try {
                fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
                fill_data(dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield)));
            } catch (ParseException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            greenLeafQty.requestFocus();
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
            try {
                fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
                fill_data(dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield)));
            } catch (ParseException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            greenLeafQty.requestFocus();
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
            try {
                fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
                fill_data(dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield)));
            } catch (ParseException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            greenLeafQty.requestFocus();
        }
    }//GEN-LAST:event_dayfieldKeyPressed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed
        java.sql.Date datef = new java.sql.Date(datePicker1.getDate().getTime());

        dayfield.setText(datehandler.get_day(datef));
        monthfield.setText(datehandler.get_month(datef));
        yearfield.setText(datehandler.get_year(datef));
        greenLeafQty.requestFocus();
    }//GEN-LAST:event_datePicker1ActionPerformed

    private void netMadeTeaQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netMadeTeaQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_netMadeTeaQtyActionPerformed

    private void greenLeafQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenLeafQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_greenLeafQtyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        delete();

        // saving data again
        double greenLqty = 0;
        double madeTeaQty = 0;
        String date = null;

        if ("Daily".equals(method.getSelectedItem().toString())) {

            try {
                date = dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                msg.showMessage("Invalid Date", "Cost of Pay", "info");
                dayfield.requestFocus();
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                date = dt.get_year(datechooser.Return_date(yearfield, monthfield, dayfield)) + "-" + dt.get_month_as_num(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                msg.showMessage("Invalid Date", "Cost of Pay", "info");
                dayfield.requestFocus();
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            greenLqty = Double.parseDouble(format.getNumberWithoutCommas(greenLeafQty.getText()));
        } catch (Exception e) {
            msg.showMessage("Invalid Amount for Green Leaf Quantity", "Cost of Pay", "info");
            greenLeafQty.setText(null);
            greenLeafQty.requestFocus();
        }

        try {
            madeTeaQty = Double.parseDouble(format.getNumberWithoutCommas(netMadeTeaQty.getText()));
        } catch (Exception e) {
            msg.showMessage("Invalid Amount for Net Made Tea Quantity", "Cost of Pay", "info");
            netMadeTeaQty.setText(null);
            netMadeTeaQty.requestFocus();
        }

        try {
            save(date, greenLqty, madeTeaQty);
            msg.showMessage("Data has been Saved", "Cost of Pay", "info");
            try {
                fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            greenLeafQty.setText("");
            netMadeTeaQty.setText("");
            dayfield.requestFocus();
        } catch (Exception e) {
            msg.showMessage("Problem with data entered.. Data not Saved..", "Cost of Pay", "info");
        }

    }//GEN-LAST:event_jButton2ActionPerformed
    Date_Handler dt = new Date_Handler();

    //  MessageBox msg = new MessageBox();
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        double greenLqty = 0;
        double madeTeaQty = 0;
        String date = null;

        if ("Daily".equals(method.getSelectedItem().toString())) {

            try {
                date = dt.get_date_as_a_String(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                msg.showMessage("Invalid Date", "Cost of Pay", "info");
                dayfield.requestFocus();
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                date = dt.get_year(datechooser.Return_date(yearfield, monthfield, dayfield)) + "-" + dt.get_month_as_num(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                msg.showMessage("Invalid Date", "Cost of Pay", "info");
                dayfield.requestFocus();
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            greenLqty = Double.parseDouble(format.getNumberWithoutCommas(greenLeafQty.getText()));
        } catch (Exception e) {
            msg.showMessage("Invalid Amount for Green Leaf Quantity", "Cost of Pay", "info");
            greenLeafQty.setText(null);
            greenLeafQty.requestFocus();
        }

        try {
            madeTeaQty = Double.parseDouble(format.getNumberWithoutCommas(netMadeTeaQty.getText()));
        } catch (Exception e) {
            msg.showMessage("Invalid Amount for Net Made Tea Quantity", "Cost of Pay", "info");
            netMadeTeaQty.setText(null);
            netMadeTeaQty.requestFocus();
        }

        try {
            save(date, greenLqty, madeTeaQty);
            msg.showMessage("Data has been Saved", "Cost of Pay", "info");
            try {
                fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
            } catch (ParseException ex) {
                System.out.println(ex);
                Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
            }
            greenLeafQty.setText("");
            netMadeTeaQty.setText("");
            dayfield.requestFocus();
        } catch (Exception e) {
            msg.showMessage("Problem with data entered.. Data not Saved..", "Cost of Pay", "info");
        }

    }//GEN-LAST:event_saveButtonActionPerformed

    private void methodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_methodItemStateChanged

    }//GEN-LAST:event_methodItemStateChanged

    private void methodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_methodKeyPressed

        if ("Daily".equals(method.getSelectedItem().toString())) {
            interface_events.Change_focus_Enterkey_t(dayfield, evt);
            dayfield.selectAll();
        } else {
            interface_events.Change_focus_Enterkey_t(monthfield, evt);
        }

    }//GEN-LAST:event_methodKeyPressed
    Interface_Events interface_events = new Interface_Events();
    Check_Entries chk = new Check_Entries();
    private void greenLeafQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greenLeafQtyKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chk.isDouble(format.getNumberWithoutCommas(greenLeafQty.getText()))) {
                greenLeafQty.setText(format.modify_number(greenLeafQty.getText()));
                interface_events.Change_focus_Enterkey_t(netMadeTeaQty, evt);
            } else {
                if (greenLeafQty.getText().length() == 0) {
                    greenLeafQty.setText("0.00");
                    interface_events.Change_focus_Enterkey_t(netMadeTeaQty, evt);
                } else {
                    msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                    greenLeafQty.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_greenLeafQtyKeyPressed

    private void netMadeTeaQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_netMadeTeaQtyKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chk.isDouble(format.getNumberWithoutCommas(netMadeTeaQty.getText()))) {
                netMadeTeaQty.setText(format.modify_number(netMadeTeaQty.getText()));
                interface_events.Change_focus_Enterkey_t_b(netMadeTeaQty, saveButton, evt);
            } else {
                if (netMadeTeaQty.getText().length() == 0) {
                    netMadeTeaQty.setText("0.00");
                    interface_events.Change_focus_Enterkey_t_b(netMadeTeaQty, saveButton, evt);
                } else {
                    msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                    netMadeTeaQty.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_netMadeTeaQtyKeyPressed

    private void methodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_methodActionPerformed
        if ("Daily".equals(method.getSelectedItem().toString())) {
            dayfield.setVisible(true);
        } else {
            dayfield.setVisible(false);
        }
    }//GEN-LAST:event_methodActionPerformed

    private void saveButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_saveButtonFocusGained
        interface_events.Respond_enter(saveButton, evt);
    }//GEN-LAST:event_saveButtonFocusGained
    ACC_Number_Formats format = new ACC_Number_Formats();
    private void greenLeafQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greenLeafQtyKeyReleased
        if (chk.isDouble(format.getNumberWithoutCommas(greenLeafQty.getText())) || greenLeafQty.getText().length() == 0) {
            String num = format.getNumberWithoutCommas(greenLeafQty.getText());
            if (num.length() != 0) {
                greenLeafQty.setText(format.set_comma(num));
            }
        } else {
            if (greenLeafQty.getText().length() == 0) {
                greenLeafQty.setText("0.00");
                interface_events.Change_focus_Enterkey_t(netMadeTeaQty, evt);
            } else {
                msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                greenLeafQty.setText(format.remove_last_char(greenLeafQty.getText()));
                greenLeafQty.requestFocus();
            }
        }
    }//GEN-LAST:event_greenLeafQtyKeyReleased

    private void netMadeTeaQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_netMadeTeaQtyKeyReleased
        if (chk.isDouble(format.getNumberWithoutCommas(netMadeTeaQty.getText())) || netMadeTeaQty.getText().length() == 0) {
            String num = format.getNumberWithoutCommas(netMadeTeaQty.getText());
            if (num.length() != 0) {
                netMadeTeaQty.setText(format.set_comma(num));
            }
        } else {
            if (netMadeTeaQty.getText().length() == 0) {
                netMadeTeaQty.setText("0.00");
                interface_events.Change_focus_Enterkey_t_b(netMadeTeaQty, saveButton, evt);
            } else {
                msg.showMessage("Enter A Valid Amount Here", "Please Check Again", "info");
                netMadeTeaQty.setText(format.remove_last_char(netMadeTeaQty.getText()));
                netMadeTeaQty.requestFocus();
            }
        }
    }//GEN-LAST:event_netMadeTeaQtyKeyReleased

    private void veiwItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_veiwItemStateChanged
        try {
            fillGLBars(datechooser.Return_date(yearfield, monthfield, dayfield));
        } catch (ParseException ex) {
            System.out.println(ex);
            Logger.getLogger(ACC_COP.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("Daily".equals(veiw.getSelectedItem().toString())) {
            dailyPanel.setVisible(true);
            monthlyPanel.setVisible(false);
        } else {
            dailyPanel.setVisible(false);
            monthlyPanel.setVisible(true);
        }

    }//GEN-LAST:event_veiwItemStateChanged

    private void veiwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veiwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_veiwActionPerformed

    private void veiwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_veiwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_veiwKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
        greenLeafQty.setText(null);
        netMadeTeaQty.setText(null);
        dayfield.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ACC_COP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACC_COP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACC_COP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACC_COP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACC_COP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dailyPanel;
    private com.michaelbaranov.microba.calendar.DatePicker datePicker1;
    private javax.swing.JPanel datepanel;
    private javax.swing.JLabel day1;
    private javax.swing.JLabel day10;
    private javax.swing.JLabel day109;
    private javax.swing.JLabel day11;
    private javax.swing.JLabel day110;
    private javax.swing.JLabel day111;
    private javax.swing.JLabel day112;
    private javax.swing.JLabel day113;
    private javax.swing.JLabel day114;
    private javax.swing.JLabel day115;
    private javax.swing.JLabel day116;
    private javax.swing.JLabel day117;
    private javax.swing.JLabel day118;
    private javax.swing.JLabel day119;
    private javax.swing.JLabel day12;
    private javax.swing.JLabel day120;
    private javax.swing.JLabel day121;
    private javax.swing.JLabel day122;
    private javax.swing.JLabel day123;
    private javax.swing.JLabel day124;
    private javax.swing.JLabel day125;
    private javax.swing.JLabel day126;
    private javax.swing.JLabel day127;
    private javax.swing.JLabel day128;
    private javax.swing.JLabel day129;
    private javax.swing.JLabel day13;
    private javax.swing.JLabel day130;
    private javax.swing.JLabel day131;
    private javax.swing.JLabel day132;
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
    private javax.swing.JLabel day32;
    private javax.swing.JLabel day33;
    private javax.swing.JLabel day34;
    private javax.swing.JLabel day35;
    private javax.swing.JLabel day36;
    private javax.swing.JLabel day37;
    private javax.swing.JLabel day38;
    private javax.swing.JLabel day39;
    private javax.swing.JLabel day4;
    private javax.swing.JLabel day40;
    private javax.swing.JLabel day41;
    private javax.swing.JLabel day42;
    private javax.swing.JLabel day43;
    private javax.swing.JLabel day44;
    private javax.swing.JLabel day45;
    private javax.swing.JLabel day46;
    private javax.swing.JLabel day47;
    private javax.swing.JLabel day48;
    private javax.swing.JLabel day49;
    private javax.swing.JLabel day5;
    private javax.swing.JLabel day50;
    private javax.swing.JLabel day51;
    private javax.swing.JLabel day52;
    private javax.swing.JLabel day53;
    private javax.swing.JLabel day54;
    private javax.swing.JLabel day55;
    private javax.swing.JLabel day56;
    private javax.swing.JLabel day57;
    private javax.swing.JLabel day58;
    private javax.swing.JLabel day59;
    private javax.swing.JLabel day6;
    private javax.swing.JLabel day60;
    private javax.swing.JLabel day61;
    private javax.swing.JLabel day62;
    private javax.swing.JLabel day7;
    private javax.swing.JLabel day8;
    private javax.swing.JLabel day9;
    private javax.swing.JTextField dayfield;
    private javax.swing.JTextField glDailyMonthly;
    private javax.swing.JTextField gl_total_daily;
    private javax.swing.JTextField greenLeafQty;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
    private javax.swing.JProgressBar jProgressBarA1;
    private javax.swing.JProgressBar jProgressBarA10;
    private javax.swing.JProgressBar jProgressBarA11;
    private javax.swing.JProgressBar jProgressBarA12;
    private javax.swing.JProgressBar jProgressBarA13;
    private javax.swing.JProgressBar jProgressBarA14;
    private javax.swing.JProgressBar jProgressBarA15;
    private javax.swing.JProgressBar jProgressBarA16;
    private javax.swing.JProgressBar jProgressBarA17;
    private javax.swing.JProgressBar jProgressBarA18;
    private javax.swing.JProgressBar jProgressBarA19;
    private javax.swing.JProgressBar jProgressBarA2;
    private javax.swing.JProgressBar jProgressBarA20;
    private javax.swing.JProgressBar jProgressBarA21;
    private javax.swing.JProgressBar jProgressBarA22;
    private javax.swing.JProgressBar jProgressBarA23;
    private javax.swing.JProgressBar jProgressBarA24;
    private javax.swing.JProgressBar jProgressBarA25;
    private javax.swing.JProgressBar jProgressBarA26;
    private javax.swing.JProgressBar jProgressBarA27;
    private javax.swing.JProgressBar jProgressBarA28;
    private javax.swing.JProgressBar jProgressBarA29;
    private javax.swing.JProgressBar jProgressBarA3;
    private javax.swing.JProgressBar jProgressBarA30;
    private javax.swing.JProgressBar jProgressBarA31;
    private javax.swing.JProgressBar jProgressBarA4;
    private javax.swing.JProgressBar jProgressBarA5;
    private javax.swing.JProgressBar jProgressBarA6;
    private javax.swing.JProgressBar jProgressBarA7;
    private javax.swing.JProgressBar jProgressBarA8;
    private javax.swing.JProgressBar jProgressBarA9;
    private javax.swing.JProgressBar jProgressBarM1;
    private javax.swing.JProgressBar jProgressBarM10;
    private javax.swing.JProgressBar jProgressBarM11;
    private javax.swing.JProgressBar jProgressBarM12;
    private javax.swing.JProgressBar jProgressBarM2;
    private javax.swing.JProgressBar jProgressBarM3;
    private javax.swing.JProgressBar jProgressBarM4;
    private javax.swing.JProgressBar jProgressBarM5;
    private javax.swing.JProgressBar jProgressBarM6;
    private javax.swing.JProgressBar jProgressBarM7;
    private javax.swing.JProgressBar jProgressBarM8;
    private javax.swing.JProgressBar jProgressBarM9;
    private javax.swing.JProgressBar jProgressBarN1;
    private javax.swing.JProgressBar jProgressBarN10;
    private javax.swing.JProgressBar jProgressBarN11;
    private javax.swing.JProgressBar jProgressBarN12;
    private javax.swing.JProgressBar jProgressBarN2;
    private javax.swing.JProgressBar jProgressBarN3;
    private javax.swing.JProgressBar jProgressBarN4;
    private javax.swing.JProgressBar jProgressBarN5;
    private javax.swing.JProgressBar jProgressBarN6;
    private javax.swing.JProgressBar jProgressBarN7;
    private javax.swing.JProgressBar jProgressBarN8;
    private javax.swing.JProgressBar jProgressBarN9;
    private javax.swing.JComboBox method;
    private javax.swing.JTextField monthfield;
    private javax.swing.JPanel monthlyPanel;
    private javax.swing.JTextField netMadeTeaMonthlyTotal;
    private javax.swing.JTextField netMadeTeaQty;
    private javax.swing.JTextField netMadeTeaTotal;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox veiw;
    private javax.swing.JTextField yearfield;
    // End of variables declaration//GEN-END:variables
}
