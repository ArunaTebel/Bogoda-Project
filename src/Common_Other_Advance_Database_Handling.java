public class Common_Other_Advance_Database_Handling {
    
    DatabaseManager dbm = DatabaseManager.getDbCon();
    
    public void Filtered_table(javax.swing.JTable table,String table_name,String element,int bottom,int top){
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
        
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"tr_no", 0, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"Date", 1, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"id", 2, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"name", 3, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"max_allow", 4, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"item_name", 5, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"item_type", 6, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"item_rate", 7, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"item_quantity", 8, bottom, top,"item_type", element);
        dbm.Inserting_To_The_Table_Filtered(table, table_name,"total_amount", 9, bottom, top,"item_type", element);
    }
    
}
