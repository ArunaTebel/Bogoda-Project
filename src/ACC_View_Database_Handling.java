
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ACC_View_Database_Handling {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    /*
     This will do the searches in the debit database.. you have to give the column you have to filter and the element you
     want to search... bottom and top are the table starting and ending values... unique for this..
     key=1 if we want to enter to a column data which is fetched by debit database
     key=0 if we want to enter to a column data which is fetched by credit database
     */
    public boolean Inserting_To_The_Table_Filtered_Reciept_Full(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, int key) {
        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside ");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside ");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all ");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all ");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
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

    }

    public boolean Inserting_To_The_Table_Filtered_Reciept_Debit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " LIKE '" + element + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " LIKE '" + element + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
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
    }

    // Copied up to this point to the journal
    /*
     This will do the searches in the credit database.. you have to give the column you have to filter and the element you
     want to search... bottom and top are the table starting and ending values... unique for this..
     key=1 if we want to enter to a column data which is fetched by debit database
     key=0 if we want to enter to a column data which is fetched by credit database
     */
    public boolean Inserting_To_The_Table_Filtered_Reciept_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + " LIKE '" + element + "'");
                while (query.next()) {

                    if (query.getInt("tr_no") != tr_no) {
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            num_of_rows_in_the_database++;
                        }
                        query1.close();
                        tr_no = query.getInt("tr_no");
                    }

                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {

                            //count++;
                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {
                                    num++;
                                    count++;
                                }
                                query2.close();
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                        num = 0;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {

                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {

                                    //  num = 0;
                                    count++;
                                    //    num++;
                                    //  }

                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table++;
                                    } else {
                                        break;
                                    }
                                }
                                query2.close();
                                tr_no = query.getInt("tr_no");
                            }

                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }

        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + " LIKE '" + element + "'");
                while (query.next()) {

                    if (query.getInt("tr_no") != tr_no) {
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no LIKE '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            num_of_rows_in_the_database++;
                        }
                        query1.close();
                        tr_no = query.getInt("tr_no");
                    }

                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {

                            //count++;
                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {
                                    num++;
                                    count++;
                                }
                                query2.close();
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                        num = 0;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + " LIKE '" + element + "'");
                        while (query.next()) {

                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {

                                    //  num = 0;
                                    count++;
                                    //    num++;
                                    //  }

                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table++;
                                    } else {
                                        break;
                                    }
                                }
                                query2.close();
                                tr_no = query.getInt("tr_no");
                            }

                        }
                        query.close();
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
    }

    /*
     if the search is for an element which is in the debit database.. That is fields such as debit account id, debit amount..
     you have to use this method
     */
    public void Filtered_table_For_Reciepts_Debit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top) {

        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, 1);
        //Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "ref_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "date", 2, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "pay_type", 3, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "debit_account_name",5, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "debit_description",6, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "credit_description", 10, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element, 0);
    }

    /*
     if the search is for an element which is in the credit database.. That is fields such as credit account id, credit amount..
     you have to use this method
     */
    public void Filtered_table_For_Reciepts_Credit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top) {
        /*  if(a%50==0){
         no_of_pages= a/50;
        
         }
         else{
         no_of_pages=a/50+1;
           
         }
         page_info.setText("Page 1 of"+" "+no_of_pages);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "entry_no", 0,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "date", 1,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_id", 2,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_name", 3,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "max_allowable", 4,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "amount", 5,bottom,top); */

        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, 1);
       // Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "ref_no", 1, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "date", 2, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "pay_type", 3, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "debit_account_id",4, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "debit_description", 6, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "credit_description", 10, bottom, top, filtering_column, element, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element, 0);
    }

    /*
     This method is used for a search done in the debit datbase.. the only difference is here we search by
     two elements.. return all the entries which are between that to values.. all others are as previous method(1st)
     */
    public boolean Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element1, Object element2, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }

        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
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
    }
    /*
     This method is used for a search done in the credit datbase.. the only difference is here we search by
     two elements.. return all the entries which are between that to values.. all others are as previous method(1st)
     */

    public boolean Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, Object element1, Object element2, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + "BETWEEN '" + element1 + "' AND '" + element2 + "'");
                while (query.next()) {

                    num_of_rows_in_the_database++;

                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + "BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            num++;
                            count++;
                            if (tr_no != query.getInt("tr_no")) {
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;

                            count++;
                            //    num++;
                            //  }

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table++;
                            } else {
                                break;
                            }

                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + "BETWEEN '" + element1 + "' AND '" + element2 + "'");
                while (query.next()) {

                    num_of_rows_in_the_database++;

                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + "BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            num++;
                            count++;
                            if (tr_no != query.getInt("tr_no")) {
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering + " BETWEEN '" + element1 + "' AND '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;

                            count++;
                            //    num++;
                            //  }

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table++;
                            } else {
                                break;
                            }

                        }
                        query.close();
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

    }
    /*
     if the search is from a column in the debit database.. use this method.. the only difference to the above
     similar method is this search for values that are between given two values
     */

    public void Filtered_table_For_Reciepts_For_Date_Debit_Search(javax.swing.JTable table, String filtering_column, Object element1, Object element2, int bottom, int top) {
        /*  if(a%50==0){
         no_of_pages= a/50;
        
         }
         else{
         no_of_pages=a/50+1;
           
         }
         page_info.setText("Page 1 of"+" "+no_of_pages);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "entry_no", 0,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "date", 1,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_id", 2,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_name", 3,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "max_allowable", 4,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "amount", 5,bottom,top); */

        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "tr_no", 0, bottom, top, filtering_column, element1, element2, 1);
       // Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "ref_no", 1, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "date", 2, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "pay_type", 3, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "debit_description", 6, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "credit_description", 10, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Debit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element1, element2, 0);
    }

    /*
     if the search is from a column in the credit database.. use this method.. the only difference to the above
     similar method is this search for values that are between given two values
     */
    public void Filtered_table_For_Reciepts_For_Date_Credit_Search(javax.swing.JTable table, String filtering_column, Object element1, Object element2, int bottom, int top) {
        /*  if(a%50==0){
         no_of_pages= a/50;
        
         }
         else{
         no_of_pages=a/50+1;
           
         }
         page_info.setText("Page 1 of"+" "+no_of_pages);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "entry_no", 0,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "date", 1,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_id", 2,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "sup_name", 3,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "max_allowable", 4,bottom,top);
         dbm.Inserting_To_The_Table(table, "gl_cash_advance_book", "amount", 5,bottom,top); */

        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element1, element2, 1);
        //Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "ref_no", 1, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "date", 2, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "pay_type", 3, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "debit_description", 6, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element1, element2, 1);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "credit_description", 10, bottom, top, filtering_column, element1, element2, 0);
        Inserting_To_The_Table_Filtered_Between_Two_Reciept_Credit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element1, element2, 0);
    }

    /// Methods For AND search
    public boolean Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering1, Object element1, String column_filtering2, Object element2, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
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

    }

    /*
     This will do the searches in the credit database.. you have to give the column you have to filter and the element you
     want to search... bottom and top are the table starting and ending values... unique for this..
     key=1 if we want to enter to a column data which is fetched by debit database
     key=0 if we want to enter to a column data which is fetched by credit database
     */
    public boolean Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering1, Object element1, String column_filtering2, Object element2, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                while (query.next()) {

                    if (query.getInt("tr_no") != tr_no) {
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no LIKE '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            num_of_rows_in_the_database++;
                        }
                        query1.close();
                        tr_no = query.getInt("tr_no");
                    }

                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {

                            //count++;
                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {
                                    num++;
                                    count++;
                                }
                                query2.close();
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                        num = 0;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {

                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {

                                    //  num = 0;
                                    count++;
                                    //    num++;
                                    //  }

                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table++;
                                    } else {
                                        break;
                                    }
                                }
                                query2.close();
                                tr_no = query.getInt("tr_no");
                            }

                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                while (query.next()) {

                    if (query.getInt("tr_no") != tr_no) {
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no LIKE '" + query.getInt("tr_no") + "'");
                        while (query1.next()) {
                            num_of_rows_in_the_database++;
                        }
                        query1.close();
                        tr_no = query.getInt("tr_no");
                    }

                }
                query.close();
            } catch (SQLException ex) {

            }
            System.out.println(num_of_rows_in_the_database);

            if (key == 1) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {

                            //count++;
                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {
                                    num++;
                                    count++;
                                }
                                query2.close();
                                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_debitside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query1.next()) {

                                    //       num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                        num = 0;
                                    } else {
                                        break;
                                    }
                                }
                                query1.close();
                                tr_no = query.getInt("tr_no");
                                num = 0;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {
                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_creditside_all where " + column_filtering1 + " LIKE '" + element1 + "' AND " + column_filtering2 + " LIKE '" + element2 + "'");
                        while (query.next()) {

                            if (tr_no != query.getInt("tr_no")) {

                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                                while (query2.next()) {

                                    //  num = 0;
                                    count++;
                                    //    num++;
                                    //  }

                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table++;
                                    } else {
                                        break;
                                    }
                                }
                                query2.close();
                                tr_no = query.getInt("tr_no");
                            }

                        }
                        query.close();
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
    }

    public boolean Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering_debit, Object element_debit, String column_filtering_credit, Object element_credit, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            /*   try {
             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             num_of_rows_in_the_database++;
             }
             }
             } catch (SQLException ex) {

             }
             */
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                    while (query1.next()) {

                        if (tr_no != query1.getInt("tr_no")) {
                            ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");

                            while (query2.next()) {

                                num_of_rows_in_the_database++;

                            }
                            tr_no = query1.getInt("tr_no");
                        }
                    }
                    query1.close();
                }
                query.close();

            } catch (SQLException ex) {

            }


            /*  if (key == 1) {

             if (num_of_rows_in_the_database >= bottom) {
             try {

             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             num = 0;
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             count++;
             num++;
             }

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
             }  */
            if (key == 1) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                            while (query1.next()) {
                                //   count++;
                                //   num++;
                                // }

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;
                                        num++;
                                    }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    } else {
                                        break;
                                    }
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } //
            else if (key == 0) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                            while (query1.next()) {

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;

                                        //    num++;
                                        //  }
                                        if (count < bottom) {

                                        } else if (count >= bottom && count <= top) {
                                            table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                            num_of_rows_filled_in_table++;
                                        } else {
                                            break;
                                        }
                                    }
                                    query2.close();
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }

        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            /*   try {
             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             num_of_rows_in_the_database++;
             }
             }
             } catch (SQLException ex) {

             }
             */
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                    while (query1.next()) {

                        if (tr_no != query1.getInt("tr_no")) {
                            ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");

                            while (query2.next()) {

                                num_of_rows_in_the_database++;

                            }
                            tr_no = query1.getInt("tr_no");
                        }
                    }
                    query1.close();
                }
                query.close();

            } catch (SQLException ex) {

            }


            /*  if (key == 1) {

             if (num_of_rows_in_the_database >= bottom) {
             try {

             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             num = 0;
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             count++;
             num++;
             }

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
             }  */
            if (key == 1) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                            while (query1.next()) {
                                //   count++;
                                //   num++;
                                // }

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;
                                        num++;
                                    }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    } else {
                                        break;
                                    }
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } //
            else if (key == 0) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + element_debit + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + element_credit + "'");
                            while (query1.next()) {

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;

                                        //    num++;
                                        //  }
                                        if (count < bottom) {

                                        } else if (count >= bottom && count <= top) {
                                            table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                            num_of_rows_filled_in_table++;
                                        } else {
                                            break;
                                        }
                                    }
                                    query2.close();
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
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
    }
    ///

    public boolean Inserting_To_The_Table_Filtered_Date_Debit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering_date, Object date1, Object date2, String column_filtering_debit, Object debit_element, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + debit_element + "' AND " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + debit_element + "' AND " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_debit + " LIKE '" + debit_element + "'  AND  " + column_filtering_date + "  BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
            //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + debit_element + "' AND " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                    while (query1.next()) {
                        num_of_rows_in_the_database++;
                    }
                    query1.close();
                }
                query.close();
            } catch (SQLException ex) {

            }

            if (key == 1) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + debit_element + "' AND " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                                num++;
                            }
                            query1.close();

                            if (count < bottom) {

                            } else if (count >= bottom && count <= top) {
                                table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                            } else {
                                break;
                            }
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } else if (key == 0) {

                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_debit + " LIKE '" + debit_element + "'  AND  " + column_filtering_date + "  BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "'");
                            while (query1.next()) {
                                count++;
                            //    num++;
                                //  }

                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query1.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table++;
                                } else {
                                    break;
                                }
                            }
                            query1.close();
                        }
                        query.close();
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
    }

    /////
    public boolean Inserting_To_The_Table_Filtered_Date_Credit_Search(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering_date, Object date1, Object date2, String column_filtering_credit, Object credit_element, int key) {

        String current = null;

        String given_period = null;

        try {
            ResultSet rs = dbm.query("SELECT * FROM acc_current_period_act");
            while (rs.next()) {
                current = rs.getString("period");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rs1 = dbm.query("SELECT * FROM acc_current_period");
            while (rs1.next()) {
                given_period = rs1.getString("period");
            }
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(ACC_Edit_Payments.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (current.equals(given_period)) {

            int num_of_rows_filled_in_table = 0;
            int num_of_rows_in_the_database = 0;
            int count = 0;
            int tr_no = 0;
            int num = 0;
            /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
             num_of_columns_filled_in_table++;
             } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

            /*   try {
             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             num_of_rows_in_the_database++;
             }
             }
             } catch (SQLException ex) {

             }
             */
            try {
                ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                while (query.next()) {
                    ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                    while (query1.next()) {

                        if (tr_no != query1.getInt("tr_no")) {
                            ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");

                            while (query2.next()) {

                                num_of_rows_in_the_database++;

                            }
                            query2.close();
                            tr_no = query1.getInt("tr_no");
                        }
                    }
                    query1.close();
                }
                query.close();

            } catch (SQLException ex) {

            }


            /*  if (key == 1) {

             if (num_of_rows_in_the_database >= bottom) {
             try {

             ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
             while (query.next()) {
             num = 0;
             ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
             while (query1.next()) {
             count++;
             num++;
             }

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
             }  */
            if (key == 1) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                            while (query1.next()) {
                            //   count++;
                                //   num++;
                                // }

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;
                                        num++;
                                    }
                                    query2.close();
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                    } else {
                                        break;
                                    }
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }
                return true;
            } //
            else if (key == 0) {

                tr_no = 0;
                if (num_of_rows_in_the_database >= bottom) {
                    try {

                        ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                        while (query.next()) {
                            //  num = 0;
                            ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                            while (query1.next()) {

                                if (tr_no != query1.getInt("tr_no")) {
                                    ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query1.getInt("tr_no") + "' ");
                                    while (query2.next()) {
                                        count++;

                                    //    num++;
                                        //  }
                                        if (count < bottom) {

                                        } else if (count >= bottom && count <= top) {
                                            table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                            num_of_rows_filled_in_table++;
                                        } else {
                                            break;
                                        }
                                    }
                                    query2.close();
                                    tr_no = query1.getInt("tr_no");
                                }
                            }
                            query1.close();
                        }
                        query.close();
                    } catch (SQLException ex) {

                    }
                } else {
                    return false;

                }

                return true;
            } else {
                return false;
            }
        } else {
            
            
        int num_of_rows_filled_in_table = 0;
        int num_of_rows_in_the_database = 0;
        int count = 0;
        int tr_no = 0;
        int num = 0;
        /*  while (table.getValueAt(num_of_columns_filled_in_table, table_column_num) != null) {
         num_of_columns_filled_in_table++;
         } */
        //  "SELECT * FROM " + table_name + " WHERE " + table_column_giving + " =" + row_element + ""

        /*   try {
         ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
         while (query.next()) {
         ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
         while (query1.next()) {
         num_of_rows_in_the_database++;
         }
         }
         } catch (SQLException ex) {

         }
         */
        try {
            ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
            while (query.next()) {
                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                while (query1.next()) {

                    if (tr_no != query1.getInt("tr_no")) {
                        ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");

                        while (query2.next()) {

                            num_of_rows_in_the_database++;

                        }
                        query2.close();
                        tr_no = query1.getInt("tr_no");
                    }
                }
                query1.close();
            }
            query.close();

        } catch (SQLException ex) {

        }


        /*  if (key == 1) {

         if (num_of_rows_in_the_database >= bottom) {
         try {

         ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
         while (query.next()) {
         num = 0;
         ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
         while (query1.next()) {
         count++;
         num++;
         }

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
         }  */
        if (key == 1) {

            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                    while (query.next()) {
                        num = 0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                        while (query1.next()) {
                            //   count++;
                            //   num++;
                            // }

                            if (tr_no != query1.getInt("tr_no")) {
                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");
                                while (query2.next()) {
                                    count++;
                                    num++;
                                }
                                query2.close();
                                if (count < bottom) {

                                } else if (count >= bottom && count <= top) {
                                    table.setValueAt(query.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                    num_of_rows_filled_in_table = num_of_rows_filled_in_table + num;
                                } else {
                                    break;
                                }
                                tr_no = query1.getInt("tr_no");
                            }
                        }
                        query1.close();
                    }
                    query.close();
                } catch (SQLException ex) {

                }
            } else {
                return false;

            }
            return true;
        } //
        else if (key == 0) {

            tr_no = 0;
            if (num_of_rows_in_the_database >= bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside_all where " + column_filtering_date + " BETWEEN '" + date1 + "' AND '" + date2 + "'");
                    while (query.next()) {
                        //  num = 0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query.getInt("tr_no") + "' AND " + column_filtering_credit + "  LIKE '" + credit_element + "'");
                        while (query1.next()) {

                            if (tr_no != query1.getInt("tr_no")) {
                                ResultSet query2 = dbm.query("SELECT * FROM account_reciept_creditside_all where tr_no = '" + query1.getInt("tr_no") + "' ");
                                while (query2.next()) {
                                    count++;

                                    //    num++;
                                    //  }
                                    if (count < bottom) {

                                    } else if (count >= bottom && count <= top) {
                                        table.setValueAt(query2.getString(column_name), num_of_rows_filled_in_table, table_column_num);
                                        num_of_rows_filled_in_table++;
                                    } else {
                                        break;
                                    }
                                }
                                query2.close();
                                tr_no = query1.getInt("tr_no");
                            }
                        }
                        query1.close();
                    }
                    query.close();
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
    }

    ///
    public void Fill_table_without_filtering(javax.swing.JTable table, int bottom, int top) {
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "tr_no", 0, bottom, top, 1);
        //Inserting_To_The_Table_Filtered_Reciept_Full(table, "reciept_no", 1, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "ref_no", 1, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "date", 2, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "pay_type", 3, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "debit_account_id", 4, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "debit_account_name", 5, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "debit_description", 6, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "debit_amount", 7, bottom, top, 1);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "credit_account_id", 8, bottom, top, 0);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "credit_account_name", 9, bottom, top, 0);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "credit_description", 10, bottom, top, 0);
        Inserting_To_The_Table_Filtered_Reciept_Full(table, "credit_amount", 11, bottom, top, 0);
    }

    public void Filtered_table_For_Reciepts_For_Date_Debit_Two_Search(javax.swing.JTable table, String filtering_column, Object element1, Object element2, int bottom, int top, String filtering_column2, Object element3) {

        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "tr_no", 0, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
       // Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "ref_no", 1, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "date", 2, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "pay_type", 3, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "debit_description", 6, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "credit_description", 10, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Debit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
    }

    public void Filtered_table_For_Reciepts_For_Date_Credit_Two_Search(javax.swing.JTable table, String filtering_column, Object element1, Object element2, int bottom, int top, String filtering_column2, Object element3) {

        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        //Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "ref_no", 1, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "date", 2, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "pay_type", 3, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "debit_description", 6, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 1);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "credit_description", 10, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
        Inserting_To_The_Table_Filtered_Date_Credit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element1, element2, filtering_column2, element3, 0);
    }

    public void Filtered_table_For_Reciepts_Debit_Debit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top, String filtering_column2, Object element2) {

        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        //Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "ref_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "date", 2, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "pay_type", 3, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "debit_description", 6, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "credit_description", 10, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Debit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element, filtering_column2, element2, 0);
    }

    public void Filtered_table_For_Reciepts_Credit_Credit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top, String filtering_column2, Object element2) {

        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, filtering_column2, element2, 1);
       // Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "ref_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "date", 2, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "pay_type", 3, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "debit_description", 6, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "credit_description", 10, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Credit_Credit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element, filtering_column2, element2, 0);
    }

    public void Filtered_table_For_Reciepts_Debit_Credit_Search(javax.swing.JTable table, String filtering_column, Object element, int bottom, int top, String filtering_column2, Object element2) {

        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "tr_no", 0, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        //Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "reciept_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "ref_no", 1, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "date", 2, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "pay_type", 3, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "debit_account_id", 4, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "debit_account_name", 5, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "debit_description", 6, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "debit_amount", 7, bottom, top, filtering_column, element, filtering_column2, element2, 1);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "credit_account_id", 8, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "credit_account_name", 9, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "credit_description", 10, bottom, top, filtering_column, element, filtering_column2, element2, 0);
        Inserting_To_The_Table_Filtered_Reciept_Debit_Credit_Search(table, "credit_amount", 11, bottom, top, filtering_column, element, filtering_column2, element2, 0);
    }

}
