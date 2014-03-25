
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ACC_View_Database_Handling {

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public boolean Inserting_To_The_Table_Filtered_Reciept(javax.swing.JTable table, String column_name, int table_column_num, int bottom, int top, String column_filtering, String element, int key) {

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
                ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getString("tr_no") + "'");
                while (query1.next()) {
                    num_of_rows_in_the_database++;
                }
            }
        } catch (SQLException ex) {

        }

        if (key == 1) {

            if (num_of_rows_in_the_database > bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                        num = 0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getString("tr_no") + "'");
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
        } else if (key == 0) {

            if (num_of_rows_in_the_database > bottom) {
                try {

                    ResultSet query = dbm.query("SELECT * FROM account_reciept_debitside where " + column_filtering + " LIKE '" + element + "'");
                    while (query.next()) {
                      //  num = 0;
                        ResultSet query1 = dbm.query("SELECT * FROM account_reciept_creditside where tr_no = '" + query.getString("tr_no") + "'");
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

  
    public void Filtered_table_For_Reciepts(javax.swing.JTable table,String filtering_column, String element, int bottom, int top) {
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

        Inserting_To_The_Table_Filtered_Reciept(table,"tr_no", 0, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table, "reciept_no", 1, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table,  "ref_no", 2, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table, "date", 3, bottom, top,filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table,  "pay_type", 4, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table, "debit_account_id", 5, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table,  "debit_account_name", 6, bottom, top,filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table, "debit_description", 7, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table,  "debit_amount", 8, bottom, top, filtering_column, element,1);
        Inserting_To_The_Table_Filtered_Reciept(table, "credit_account_id", 9, bottom, top, filtering_column, element,0);
        Inserting_To_The_Table_Filtered_Reciept(table, "credit_account_name", 10, bottom, top, filtering_column, element,0);
        Inserting_To_The_Table_Filtered_Reciept(table, "credit_description", 11, bottom, top, filtering_column, element,0);
        Inserting_To_The_Table_Filtered_Reciept(table, "credit_amount", 12, bottom, top, filtering_column, element,0);
    }

}
