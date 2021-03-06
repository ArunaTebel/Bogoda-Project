
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
public class PRCRHolidayPayGround {

    DatabaseManager dbm;
    double epfRate;
    int[][] maleRanges;
    int[][] femaleRanges;
    int[] factoryWorkerCodes;
    int[] genderCodes;
    int[][] workDaysPerMonth;
    double[][] paymentPerMonth;
    int[] lastYearHolidays;

    public PRCRHolidayPayGround() {
        dbm = DatabaseManager.getDbCon();

        String coloumn = "epf";
        String table = "checkroll_pay_info";
        try {
            epfRate = dbm.readFirstRow(table, coloumn);
        } catch (SQLException ex) {
            Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*   maleRanges = new int[4][2];
         femaleRanges = new int[4][2];
         int i = 0;
         table = "holiday_pay_rates";
         coloumn = "range_start";
         try {
         ResultSet rs1 = dbm.query("SELECT " + coloumn + " FROM " + table);
         while(rs1.next()){
         maleRanges[i][0] = rs1.getInt(coloumn);
         femaleRanges[i][0] = maleRanges[i][0];
         i++;
         }
         } catch (SQLException ex) {
         Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
         i = 0;
         coloumn = "male";
         try {
         ResultSet rs2 = dbm.query("SELECT " + coloumn + " FROM " + table);
         while(rs2.next()){
         maleRanges[i][1] = rs2.getInt(coloumn);
         i++;
         }
         } catch (SQLException ex) {
         Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         coloumn = "female";
         i = 0;
         try {
         ResultSet rs4 = dbm.query("SELECT " + coloumn + " FROM " + table);
         while(rs4.next()){
         femaleRanges[i][1] = rs4.getInt(coloumn);
         i++;
         }
         } catch (SQLException ex) {
         Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        maleRanges = new int[4][2];
        femaleRanges = new int[4][2];
        int i = 0;
        table = "holiday_pay_rate_details_male";
        coloumn = "lower";
        try {
            ResultSet rs1 = dbm.query("SELECT " + coloumn + " FROM " + table);
            while (rs1.next()) {
                maleRanges[i][0] = rs1.getInt(coloumn);
                i++;
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        i = 0;
        coloumn = "ndays";
        try {
            ResultSet rs2 = dbm.query("SELECT " + coloumn + " FROM " + table);
            while (rs2.next()) {
                maleRanges[i][1] = rs2.getInt(coloumn);
                i++;
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        i = 0;

        table = "holiday_pay_rate_details_female";
        coloumn = "lower";
        try {
            ResultSet rs1 = dbm.query("SELECT " + coloumn + " FROM " + table);
            while (rs1.next()) {
                femaleRanges[i][0] = rs1.getInt(coloumn);
                i++;
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        i = 0;
        coloumn = "ndays";
        try {
            ResultSet rs2 = dbm.query("SELECT " + coloumn + " FROM " + table);
            while (rs2.next()) {
                femaleRanges[i][1] = rs2.getInt(coloumn);
                i++;
            }
            rs2.close();
        } catch (SQLException ex) {
            Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getFactoryWorkerCodes() {
        String factoryCode = "FAC";
        String table = "checkroll_personalinfo";
        String coloumn = "division";
        String neededColoumn = "code";
        factoryWorkerCodes = dbm.getValuesArrayN(table, coloumn, neededColoumn, factoryCode);
        neededColoumn = "gender";
        genderCodes = dbm.getValuesArrayN(table, coloumn, neededColoumn, factoryCode);
    }

    MessageBox msg = new MessageBox();
    
    public void getWorkDaysNPayment(int inputYear) {
        /*  Calendar currentDate = Calendar.getInstance();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
         int year = Integer.parseInt(formatter.format(currentDate.getTime()));*/

        int year = inputYear;

        int i, j, workerCode;
        int length = factoryWorkerCodes.length;
        workDaysPerMonth = new int[length][12];
        paymentPerMonth = new double[length][12];

        String table;
        String coloumn = "code";
        int workDays = 0;
        int sundays = 0;
        for (i = 0; i < length; i++) {
            workerCode = factoryWorkerCodes[i];
            for (j = 0; j < 12; j++) {
                if (j < 8) {
                    table = "pr_workdata_" + year + "_0" + (j + 1);
                } else if (j == 8) {
                    table = "pr_workdata_" + (year - 1) + "_0" + (j + 1);
                } else {
                    table = "pr_workdata_" + (year - 1) + "_" + (j + 1);
                }

                try {
                    ResultSet rs = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + workerCode + "'");
                    while (rs.next()) {
                        if (rs.getString("normal_days") != null) {
                            workDays = rs.getInt("normal_days");
                        }
                        if (rs.getString("sundays") != null) {
                            sundays = rs.getInt("sundays");
                        }
                        workDaysPerMonth[i][j] = workDays + sundays;
                        workDays = 0;
                        sundays = 0;

                        if (rs.getString("paid_amount") != null) {
                            paymentPerMonth[i][j] = rs.getDouble("paid_amount");
                        }
                    }
                    rs.close();
                } catch (SQLException ex) {
                  //  msg.showMessage("Details for " + table.split("_")[2] + " doesn't exxist", "Data Incomplete", "info");
                    Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void getLastYearHolidays(int inputYear) {
      /*  Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(formatter.format(currentDate.getTime())) - 1; */
        
        int year = inputYear-1;

        String table = "holiday_pay_ground";
        String coloumn = "entry";
        String neededColoumn = "holiday_days";
        String match;
        lastYearHolidays = new int[factoryWorkerCodes.length];
        int i;
        for (i = 0; i < factoryWorkerCodes.length; i++) {
            match = year + "" + factoryWorkerCodes[i];
            try {
                ResultSet rs = dbm.query("SELECT * FROM " + table + " WHERE " + coloumn + " ='" + match + "'");
                while (rs.next()) {
                    lastYearHolidays[i] = rs.getInt(neededColoumn);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateTable(int inputYear) {
       /* Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(formatter.format(currentDate.getTime()));*/
        
        int year = inputYear;
        
        int entry, total1, total2, payDueDates = 0, i, j;
        double totalPay, averagePay, payDue, epf, amount;

        for (i = 0; i < factoryWorkerCodes.length; i++) {
            entry = Integer.parseInt(year + "" + factoryWorkerCodes[i]);

            total1 = 0;
            for (j = 0; j < 12; j++) {
                total1 = total1 + workDaysPerMonth[i][j];
            }
            total2 = total1 + lastYearHolidays[i];

            totalPay = 0;
            for (j = 0; j < 12; j++) {
                totalPay = totalPay + paymentPerMonth[i][j];
            }

            if (total1 != 0) {
                averagePay = totalPay / total1;
            } else {
                averagePay = 0;
            }

            if (genderCodes[i] == 1) {
                if (total2 < maleRanges[0][0]) {
                    payDueDates = 0;
                } else {
                    for (j = 0; j < 4; j++) {
                        if (total2 >= maleRanges[j][0]) {
                            payDueDates = maleRanges[j][1];
                        }
                    }
                }
            } else {
                if (total2 < femaleRanges[0][0]) {
                    payDueDates = 0;
                } else {
                    for (j = 0; j < 4; j++) {
                        if (total2 >= femaleRanges[j][0]) {
                            payDueDates = femaleRanges[j][1];
                        }
                    }
                }
            }

            payDue = averagePay * payDueDates;
            epf = payDue * epfRate / 100;
            amount = payDue - epf;

            try {
                dbm.insert("INSERT INTO holiday_pay_ground(entry,workcode,9_days,10_days,11_days,12_days,1_days,2_days,3_days,4_days,5_days,6_days,7_days,8_days) VALUES('" + entry + "','" + factoryWorkerCodes[i] + "','" + workDaysPerMonth[i][8] + "','" + workDaysPerMonth[i][9] + "','" + workDaysPerMonth[i][10] + "','" + workDaysPerMonth[i][11] + "','" + workDaysPerMonth[i][0] + "','" + workDaysPerMonth[i][1] + "','" + workDaysPerMonth[i][2] + "','" + workDaysPerMonth[i][3] + "','" + workDaysPerMonth[i][4] + "','" + workDaysPerMonth[i][5] + "','" + workDaysPerMonth[i][6] + "','" + workDaysPerMonth[i][7] + "')");
            } catch (SQLException ex) {
                Logger.getLogger(PRCRHolidayPayFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "year_total", total1);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "from_last_year", lastYearHolidays[i]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "total_days", total2);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "9_salary", paymentPerMonth[i][8]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "10_salary", paymentPerMonth[i][9]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "11_salary", paymentPerMonth[i][10]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "12_salary", paymentPerMonth[i][11]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "1_salary", paymentPerMonth[i][0]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "2_salary", paymentPerMonth[i][1]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "3_salary", paymentPerMonth[i][2]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "4_salary", paymentPerMonth[i][3]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "5_salary", paymentPerMonth[i][4]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "6_salary", paymentPerMonth[i][5]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "7_salary", paymentPerMonth[i][6]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "8_salary", paymentPerMonth[i][7]);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "total_salary", totalPay);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "average_salary", averagePay);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "holiday_days", payDueDates);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "holiday_pay", payDue);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "epf", epf);
            dbm.updateDatabase("holiday_pay_ground", "entry", entry, "amount", amount);
        }
    }

    public void doAllSteps(int inputYear) {
        PRCRHolidayPayGround ex = new PRCRHolidayPayGround();
        ex.getFactoryWorkerCodes();
        int i, j;
        /*for(i=0;i<ex.factoryWorkerCodes.length;i++)
         System.out.println(ex.factoryWorkerCodes[i]);*/
        ex.getWorkDaysNPayment(inputYear);
        for (i = 0; i < ex.factoryWorkerCodes.length; i++) {
            for (j = 0; j < 12; j++) {
                System.out.println(ex.factoryWorkerCodes[i] + " - " + ex.workDaysPerMonth[i][j] + " - " + ex.paymentPerMonth[i][j]);
            }
            System.out.println("-----------------------");
        }
        ex.getLastYearHolidays(inputYear);
        /*for(i=0;i<ex.factoryWorkerCodes.length;i++)
         System.out.println(ex.lastYearHolidays[i]);*/
        ex.updateTable(inputYear);
    }

}
