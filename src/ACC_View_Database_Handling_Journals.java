
import java.sql.ResultSet;
import java.sql.SQLException;

public class ACC_View_Database_Handling_Journals {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    /*
     This will do the searches in the debit database.. you have to give the column you have to filter and the element you
     want to search... bottom and top are the table starting and ending values... unique for this..
     key=1 if we want to enter to a column data which is fetched by debit database
     key=0 if we want to enter to a column data which is fetched by credit database
     */
    public boolean Inserting_To_The_Table_Filtered_Journal_Full(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, int key) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int rows_in_debit = 0;
        int rows_in_credit = 0;
        int count = 0;
        int countd = 0;
        int countc = 0;
        int numd = 0;
        int numc = 0;
        int tr_no = 0;
        int num = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_main ");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query1.next()) {
                    rows_in_debit++;
                }
            }
        } catch (SQLException ex) {

        }
        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_main ");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query1.next()) {
                    rows_in_credit++;
                }
            }
        } catch (SQLException ex) {

        }

        num_of_rows_in_the_database = Math.max(rows_in_credit, rows_in_debit);
        

        // key 1 for main, 2 for debit , 3 for credit
        if (key == 1) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main");
                    while (query.next()) {
                        numd = 0;
                        numc=0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = count+Math.max(countc, countd);
                        num = Math.max(numc, numd);

                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                        } else {
                            break;
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 2) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main");
                    while (query.next()) {
                        //  num = 0;
                        countc=0;
                        countd=0;
                        numc=0;
                        numd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num= Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query3.next()) {
                            
                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table++;
                            } else {
                                break;
                            }
                        }
                        num_of_rows_filled_in_table=num_of_rows_filled_in_table-numd+num;

                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }

            return true;
        } else if (key == 3) {
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main");
                    while (query.next()) {
                       //  num = 0;
                        countc=0;
                        countd=0;
                        numc=0;
                        numd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = count+Math.max(countc, countd);
                        num= Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query3.next()) {
                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table++;
                            } else {
                                break;
                            }
                        }
                        num_of_rows_filled_in_table=num_of_rows_filled_in_table-numc+num;

                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }

            return true;
        } else {
            return false;

        }

    }
    
    // edited upto this point

    public boolean Inserting_To_The_Table_Filtered_Journal_Main_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element, int key) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int rows_in_credit = 0;
        int rows_in_debit = 0;
        int countc = 0;
        int countd = 0;
        int count = 0;
        int num = 0;
        int numc = 0;
        int numd = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " LIKE '" + element + "'");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query1.next()) {
                    rows_in_debit++;
                }
                ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query2.next()) {
                    rows_in_credit++;
                }
            }
            num_of_rows_in_the_database = Math.max(rows_in_credit, rows_in_debit);
        } catch (SQLException ex) {

        }

        if (key == 1) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        numc = 0;
                        numd = 0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num = Math.max(numc, numd);

                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                        } else {
                            break;
                        }

                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 2) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        countc=0;
                        countd=0;
                        numc=0;
                        numd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num=Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while(query3.next()){
                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table++;
                        } else {
                            break;
                        }
                    }
                        num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numd;
                        
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 3) {
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        numc = 0;
                        numd=0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num = Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while(query3.next()){
                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table++;
                        } else {
                            break;
                        }
                    }
                        num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numc;
                       // System.out.println(num_of_rows_filled_in_table);
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        }
        return false;

    }

    public boolean Inserting_To_The_Table_Filtered_Journal_Debit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element, int key) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int rows_in_credit = 0;
        int rows_in_debit = 0;
        int countc = 0;
        int countd = 0;
        int count = 0;
        int tr_no = 0;
        int num = 0;
        int numc = 0;
        int numd = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_debitside where " + column_filtering + " LIKE '" + element + "'");

            while (query.next()) {
                if (query.getInt("tr_no") != tr_no) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        rows_in_debit++;
                    }
                    ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                    while (query2.next()) {
                        rows_in_credit++;
                    }
                    tr_no = query.getInt("tr_no");
                }

            }

            num_of_rows_in_the_database = Math.max(rows_in_credit, rows_in_debit);

        } catch (SQLException ex) {

        }

        if (key == 1) {

            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_debitside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        if (tr_no != query.getInt("tr_no")) {
                            
                            numc=0;
                            numd=0;
                            countc=0;
                            countd=0;

                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            count=Math.max(countc,countd)+count;
                            num=Math.max(numc, numd);
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_main where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                } else {
                                    break;
                                }
                            }
                            tr_no = query.getInt("tr_no");
              
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 2) {
            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_debitside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {

                        
                        if (tr_no != query.getInt("tr_no")) {
                            
                            numc=0;
                            numd=0;
                            countc=0;
                            countd=0;

                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            
                            count=Math.max(countc,countd)+count;
                            num=Math.max(numc, numd);
                            
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                  
                                } else {
                                    break;
                                }
                            }
                            num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numd;
                            tr_no = query.getInt("tr_no");
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 3) {
            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_debitside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        if (tr_no != query.getInt("tr_no")) {
                            
                            numc=0;
                            numd=0;
                            countc=0;
                            countd=0;

                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            count=Math.max(countc,countd)+count;
                            num=Math.max(numc, numd);
                            
                            
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            num_of_rows_filled_in_table=num_of_rows_filled_in_table-numc+num;
                            tr_no = query.getInt("tr_no");
                            num = 0;
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        }
        return false;
    }

    public boolean Inserting_To_The_Table_Filtered_Journal_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element, int key) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int rows_in_credit = 0;
        int rows_in_debit = 0;
        int countc = 0;
        int countd = 0;
        int count = 0;
        int tr_no = 0;
        int num = 0;
        int numc = 0;
        int numd = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_creditside where " + column_filtering + " LIKE '" + element + "'");

            while (query.next()) {
                if (query.getInt("tr_no") != tr_no) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        rows_in_debit++;
                    }
                    ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                    while (query2.next()) {
                        rows_in_credit++;
                    }
                    tr_no = query.getInt("tr_no");
                }

            }

            num_of_rows_in_the_database = Math.max(rows_in_credit, rows_in_debit);

        } catch (SQLException ex) {

        }

        if (key == 1) {

            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_creditside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        
                        if (tr_no != query.getInt("tr_no")) {
                            
                            numd=0;
                            numc=0;
                            countc=0;
                            countd=0;

                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            num= Math.max(numc, numd);
                            count= Math.max(countc,countd)+count;
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_main where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    num = 0;
                                } else {
                                    break;
                                }
                            }
                            tr_no = query.getInt("tr_no");
                            num = 0;
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 2) {
            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_creditside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {

                        //count++;
                        if (tr_no != query.getInt("tr_no")) {
                            
                            numd=0;
                            numc=0;
                            countc=0;
                            countd=0;

                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            num= Math.max(numc, numd);
                            count= Math.max(countc,countd)+count;
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numd;
                            tr_no = query.getInt("tr_no");
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 3) {
            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_creditside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {

                        //count++;
                        if (tr_no != query.getInt("tr_no")) {

                            numc=0;
                            numd=0;
                            countc=0;
                            countd=0;
                            
                            ResultSet query2 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query2.next()) {
                                numd++;
                                countd++;
                            }
                            ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query3.next()) {
                                numc++;
                                countc++;
                            }
                            num= Math.max(numc, numd);
                            count= Math.max(countc,countd)+count;
                            
                            ResultSet query1 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {

                                
                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                 
                                } else {
                                    break;
                                }
                            }
                            tr_no = query.getInt("tr_no");
                            num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numc;
                        }
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        }
        return false;

    }
    
    
    public boolean Inserting_To_The_Table_Filtered_Journal_Date_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element1,Object element2, int key) {

        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int rows_in_credit = 0;
        int rows_in_debit = 0;
        int countc = 0;
        int countd = 0;
        int count = 0;
        int tr_no = 0;
        int num = 0;
        int numc = 0;
        int numd = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        try {
            ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " BETWEEN '" + element1 + "' AND '"+element2+"'");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query1.next()) {
                    rows_in_debit++;
                }
                ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                while (query2.next()) {
                    rows_in_credit++;
                }
            }
            num_of_rows_in_the_database = Math.max(rows_in_credit, rows_in_debit);
        } catch (SQLException ex) {

        }

        if (key == 1) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " BETWEEN '" + element1 + "' AND '"+element2+"'");
                    while (query.next()) {
                        numc=0;
                        numd=0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num = Math.max(numc, numd);

                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                        } else {
                            break;
                        }

                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 2) {

            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " BETWEEN '" + element1 + "' AND '"+element2+"'");
                    while (query.next()) {
                        numc=0;
                        numd=0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num = Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while(query3.next()){
                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table++;
                        } else {
                            break;
                        }
                    }
                        num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numd;
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } else if (key == 3) {
          
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_journal_main where " + column_filtering + " BETWEEN '" + element1 + "' AND '"+element2+"'");
                    while (query.next()) {
                        numc=0;
                        numd=0;
                        countc=0;
                        countd=0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_journal_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            countd++;
                            numd++;
                        }
                        ResultSet query2 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while (query2.next()) {
                            countc++;
                            numc++;
                        }

                        count = Math.max(countc, countd)+count;
                        num = Math.max(numc, numd);

                        ResultSet query3 = dbm.query("SELECT * FROM account_journal_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                        while(query3.next()){
                        if (count < bottom) {

                        } else if (count >= bottom && count <= top) {
                            table.setValueAt(query3.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                            num_of_rows_filled_in_table++;
                        } else {
                            break;
                        }
                    }
                       num_of_rows_filled_in_table=num_of_rows_filled_in_table+num-numc; 
                    }
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        }
        return false;

    }
    
    
    
    
    
     public void Fill_table_without_filtering(javax.swing.JTable table, int bottom, int top){
         Inserting_To_The_Table_Filtered_Journal_Full(table, "tr_no", 0, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "journal_no", 1, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "ref_no", 2, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "date", 3, bottom, top,  1);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "pay_type", 4, bottom, top,  1);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "debit_account_id", 5, bottom, top,  2);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "debit_account_name", 6, bottom, top, 2);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "debit_description", 7, bottom, top,  2);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "debit_amount", 8, bottom, top, 2);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "credit_account_id", 9, bottom, top,  3);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "credit_account_name", 10, bottom, top, 3);
        Inserting_To_The_Table_Filtered_Journal_Full(table, "credit_description", 11, bottom, top, 3);
       Inserting_To_The_Table_Filtered_Journal_Full(table, "credit_amount", 12, bottom, top, 3);
    }

    public void Filtered_table_For_Journal_Main_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top) {

        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "tr_no", 0, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "journal_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "ref_no", 2, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "date", 3, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "pay_type", 4, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "debit_account_id", 5, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "debit_account_name", 6, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "debit_description", 7, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "debit_amount", 8, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "credit_account_id", 9, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "credit_account_name", 10, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "credit_description", 11, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Main_Search(table, "credit_amount", 12, bottom, top, filtering_column, element, 3);
    }

    public void Filtered_table_For_Journal_Debit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top) {

        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "journal_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "ref_no", 2, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "date", 3, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "pay_type", 4, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "debit_account_id", 5, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "debit_account_name", 6, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "debit_description", 7, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "debit_amount", 8, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "credit_account_id", 9, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "credit_account_name", 10, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "credit_description", 11, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Debit_Search(table, "credit_amount", 12, bottom, top, filtering_column, element, 3);
    }

    public void Filtered_table_For_Journal_Credit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top) {

        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "journal_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "ref_no", 2, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "date", 3, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "pay_type", 4, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "debit_account_id", 5, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "debit_account_name", 6, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "debit_description", 7, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "debit_amount", 8, bottom, top, filtering_column, element, 2);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "credit_account_id", 9, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "credit_account_name", 10, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "credit_description", 11, bottom, top, filtering_column, element, 3);
        Inserting_To_The_Table_Filtered_Journal_Credit_Search(table, "credit_amount", 12, bottom, top, filtering_column, element, 3);
    }
    
    
    public void Filtered_table_For_Journal_Date_Search(javax.swing.JTable table, String filtering_column, Object element1, Object element2, int bottom, int top) {
        
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "tr_no", 0, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "journal_no", 1, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "ref_no", 2, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "date", 3, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "pay_type", 4, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "debit_account_id", 5, bottom, top, filtering_column, element1, element2, 2);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "debit_account_name", 6, bottom, top, filtering_column, element1, element2, 2);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "debit_description", 7, bottom, top, filtering_column, element1, element2, 2);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "debit_amount", 8, bottom, top, filtering_column, element1, element2, 2);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "credit_account_id", 9, bottom, top, filtering_column, element1, element2, 3);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "credit_account_name", 10, bottom, top, filtering_column, element1, element2, 3);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "credit_description", 11, bottom, top, filtering_column, element1, element2, 3);
        Inserting_To_The_Table_Filtered_Journal_Date_Search(table, "credit_amount", 12, bottom, top, filtering_column, element1, element2, 3);
    }
}
