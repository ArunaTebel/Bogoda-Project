
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acer
 */
public class PRCR_NoteAnalysis {

    private int n_5000;
    private int n_2000;
    private int n_1000;
    private int n_500;
    private int n_100;
    private int n_50;
    private int n_20;
    private int n_10;
    private int N5000;//total number of notes
    private int N2000;
    private int N1000;
    private int N500;
    private int N100;
    private int N50;
    private int N20;
    private int N10;
    private double pettyCash;
    String month;
    String year;
    String st;
    // private int employCode;
    DatabaseManager dbm = DatabaseManager.getDbCon();
    Date_Handler month_num = new Date_Handler();

    public PRCR_NoteAnalysis() {
        this.n_5000 = 0;
        this.n_2000 = 0;
        this.n_1000 = 0;
        this.n_500 = 0;
        this.n_100 = 0;
        this.n_50 = 0;
        this.n_20 = 0;
        this.n_10 = 0;
        this.N5000 = 0;
        this.N2000 = 0;
        this.N1000 = 0;
        this.N500 = 0;
        this.N100 = 0;
        this.N50 = 0;
        this.N20 = 0;
        this.N10 = 0;
        this.pettyCash = 0;
         this.month = null;
        this.year = null;
        this.st = null;
        //this.employCode = 0;
    }

     public void Set_month(String month) {
        this.month = month;
        if (month_num.return_index(month) < 10) {
            st = year + "_0" + month_num.return_index(month);
        } else {
            st = year + "_" + month_num.return_index(month);
        }
        System.out.println("st-"+st);
    }

    public void Set_year(String year) {
        this.year = year;
    }

    public void setEmpCode() {
    }

    public void setStaffNotes() {
        Payroll_Salary abc1 = new Payroll_Salary();
        int columnSize = 0;
        columnSize = getColumnsize("prcr_staffworkdata_"+st, "code");
        System.out.println(columnSize);

        int array[] = new int[columnSize];
        double arraySal[] = new double[columnSize];
        array = getIntArray("prcr_staffworkdata_"+st, "code");

        for (int i = 0; i < columnSize; i++) {
            System.out.println(array[i]);
            abc1.setEmployCode(array[i]);
            arraySal[i] = abc1.getFullPay(st);
            System.out.println(arraySal[i]);

        }

        int arrayN5000[] = new int[columnSize];
        arrayN5000 = getIntArray("prcr_staffworkdata_"+st, "n_5000");
        //int N5000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N5000 = N5000 + arrayN5000[i];
        }
        //N5000T.setText(Integer.toString(N5000));

        int arrayN2000[] = new int[columnSize];
        arrayN2000 = getIntArray("prcr_staffworkdata_"+st, "n_2000");
        //int N2000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N2000 = N2000 + arrayN2000[i];
        }
        // N2000T.setText(Integer.toString(N2000));

        int arrayN1000[] = new int[columnSize];
        arrayN1000 = getIntArray("prcr_staffworkdata_"+st, "n_1000");
        //int N1000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N1000 = N1000 + arrayN1000[i];
        }
        // N1000T.setText(Integer.toString(N1000));

        int arrayN500[] = new int[columnSize];
        arrayN500 = getIntArray("prcr_staffworkdata_"+st, "n_500");
        //int N500 = 0;
        for (int i = 0; i < columnSize; i++) {
            N500 = N500 + arrayN500[i];
        }
        //N500T.setText(Integer.toString(N500));

        int arrayN100[] = new int[columnSize];
        arrayN100 = getIntArray("prcr_staffworkdata_"+st, "n_100");
        //int N100 = 0;
        for (int i = 0; i < columnSize; i++) {
            N100 = N100 + arrayN100[i];
        }
        //N100T.setText(Integer.toString(N100));

        int arrayN50[] = new int[columnSize];
        arrayN50 = getIntArray("prcr_staffworkdata_"+st, "n_50");
        //int N50 = 0;
        for (int i = 0; i < columnSize; i++) {
            N50 = N50 + arrayN50[i];
        }
        //N50T.setText(Integer.toString(N50));

        int arrayN20[] = new int[columnSize];
        arrayN20 = getIntArray("prcr_staffworkdata_"+st, "n_20");
        //int N20 = 0;
        for (int i = 0; i < columnSize; i++) {
            N20 = N20 + arrayN20[i];
        }
        //N20T.setText(Integer.toString(N20));

        int arrayN10[] = new int[columnSize];
        arrayN10 = getIntArray("prcr_staffworkdata_"+st, "n_10");
        //int N10 = 0;
        for (int i = 0; i < columnSize; i++) {
            N10 = N10 + arrayN10[i];
        }
        //N10T.setText(Integer.toString(N10));

    }

    public void setCheckrollNotes() {
        CheckrollSallaryCal abc = new CheckrollSallaryCal();
        int columnSize = 0;
        columnSize = getColumnsize("pr_workdata_"+st, "code");
        System.out.println("colmnsiz"+columnSize);

        int array[] = new int[columnSize];
        double arraySal[] = new double[columnSize];
        array = getIntArray("pr_workdata_"+st, "code");

        for (int i = 0; i < columnSize; i++) {
            System.out.println("arrayindex-"+array[i]);
            abc.setEmployCode(array[i]);
            arraySal[i] = abc.getFinalSalary(st);
            System.out.println("salary i-"+arraySal[i]);

        }

        int arrayN5000[] = new int[columnSize];
        arrayN5000 = getIntArray("pr_workdata_"+st, "n_5000");
        //int N5000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N5000 = N5000 + arrayN5000[i];
        }
        //N5000T.setText(Integer.toString(N5000));

        int arrayN2000[] = new int[columnSize];
        arrayN2000 = getIntArray("pr_workdata_"+st, "n_2000");
        //int N2000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N2000 = N2000 + arrayN2000[i];
        }
        //N2000T.setText(Integer.toString(N2000));

        int arrayN1000[] = new int[columnSize];
        arrayN1000 = getIntArray("pr_workdata_"+st, "n_1000");
        //int N1000 = 0;
        for (int i = 0; i < columnSize; i++) {
            N1000 = N1000 + arrayN1000[i];
        }
        //N1000T.setText(Integer.toString(N1000));

        int arrayN500[] = new int[columnSize];
        arrayN500 = getIntArray("pr_workdata_"+st, "n_500");
        //int N500 = 0;
        for (int i = 0; i < columnSize; i++) {
            N500 = N500 + arrayN500[i];
        }
        //N500T.setText(Integer.toString(N500));

        int arrayN100[] = new int[columnSize];
        arrayN100 = getIntArray("pr_workdata_"+st, "n_100");
        //int N100 = 0;
        for (int i = 0; i < columnSize; i++) {
            N100 = N100 + arrayN100[i];
        }
        //N100T.setText(Integer.toString(N100));

        int arrayN50[] = new int[columnSize];
        arrayN50 = getIntArray("pr_workdata_"+st, "n_50");
        //int N50 = 0;
        for (int i = 0; i < columnSize; i++) {
            N50 = N50 + arrayN50[i];
        }
        //N50T.setText(Integer.toString(N50));

        int arrayN20[] = new int[columnSize];
        arrayN20 = getIntArray("pr_workdata_"+st, "n_20");
        //int N20 = 0;
        for (int i = 0; i < columnSize; i++) {
            N20 = N20 + arrayN20[i];
        }
        //N20T.setText(Integer.toString(N20));

        int arrayN10[] = new int[columnSize];
        arrayN10 = getIntArray("pr_workdata_"+st, "n_10");
        //int N10 = 0;
        for (int i = 0; i < columnSize; i++) {
            N10 = N10 + arrayN10[i];
        }
        //N10T.setText(Integer.toString(N10));

    }

    public void ChNoteAnalysis(double salary, int employCode,String s) {

        n_5000 = (int) (salary / 5000);
        double rem = salary % 5000;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_5000", n_5000);

        n_2000 = (int) (rem / 2000);
        rem = rem % 2000;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_2000", n_2000);

        n_1000 = (int) (rem / 1000);
        rem = rem % 1000;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_1000", n_1000);

        n_500 = (int) (rem / 500);
        rem = rem % 500;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "N_500", n_500);

        n_100 = (int) (rem / 100);
        rem = salary % 100;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_100", n_100);

        n_50 = (int) (rem / 50);
        rem = rem % 50;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_50", n_50);

        n_20 = (int) (rem / 20);
        rem = rem % 20;
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_20", n_20);

        n_10 = (int) (rem / 10);
        dbm.updateDatabase("pr_workdata_"+s, "code", employCode, "n_10", n_10);

        pettyCash = rem % 10;
        System.out.println("5000-" + n_5000 + "-2000-" + n_2000 + "-1000-" + n_1000 + "," + n_500 + "," + n_100);

    }

    public void StNoteAnalysis(double salary, int employCode,String s) {//when we input salary of any employer number of notes that needed to give him will be updated in database

        n_5000 = (int) (salary / 5000);
        double rem = salary % 5000;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_5000", n_5000);

        n_2000 = (int) (rem / 2000);
        rem = rem % 2000;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_2000", n_2000);

        n_1000 = (int) (rem / 1000);
        rem = rem % 1000;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_1000", n_1000);

        n_500 = (int) (rem / 500);
        rem = rem % 500;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "N_500", n_500);

        n_100 = (int) (rem / 100);
        rem = salary % 100;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_100", n_100);

        n_50 = (int) (rem / 50);
        rem = rem % 50;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_50", n_50);

        n_20 = (int) (rem / 20);
        rem = rem % 20;
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_20", n_20);

        n_10 = (int) (rem / 10);
        dbm.updateDatabase("prcr_staffworkdata_"+s, "code", employCode, "n_10", n_10);

        pettyCash = rem % 10;
        // System.out.println("5000-" + n_5000 + "-2000-" + n_2000 + "-1000-" + n_1000 + "," + n_500 + "," + n_100);

    }

    public int getN5000() {
        return this.N5000;
    }

    public int getN2000() {
        return this.N2000;
    }

    public int getN1000() {
        return this.N1000;
    }

    public int getN500() {
        return this.N500;
    }

    public int getN100() {
        return this.N100;
    }

    public int getN50() {
        return this.N50;
    }

    public int getN20() {
        return this.N20;
    }

    public int getN10() {
        return this.N10;
    }
    public String getString(){
        return st;
    }

    public int[] getIntArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            int[] array = new int[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getInt(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }
      public double[] getDoubleArray(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            double[] array = new double[count];
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                array[count] = query2.getDouble(column_name);
                count++;
            }
            return array;
        } catch (SQLException ex) {

        }
        return null;

    }

    public int getColumnsize(String table_name, String column_name) {

        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }

        } catch (SQLException ex) {

        }
        return count;
        //return null;

    }

}
