
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class GL_Over_Advance {
    GL_Billsummerycls billsum = new GL_Billsummerycls();
    Date_Handler datehandler = new Date_Handler();
    int sup_id;
    String sup_name;
    double advance_taken;
    String category_code;
    Date issued_date;

    DatabaseManager dbm = DatabaseManager.getDbCon();

    public GL_Over_Advance() {

        sup_id = 0;
        sup_name = null;
        advance_taken = 0;
        category_code = null;
        issued_date=null;
    }

    //  Setters
    public void Set_Sup_ID(int sup_id) {
        this.sup_id = sup_id;
    }

    public void Set_Sup_Name() {
        sup_name = dbm.checknReturnStringDataReceipts("suppliers", "sup_id", sup_id, "sup_name");
    }

    public void Set_Advance_Taken(double advance_taken) {
        this.advance_taken = advance_taken;
    }

    public void Set_Category_Code() {
        category_code = dbm.checknReturnStringDataReceipts("suppliers", "sup_id", sup_id, "cat_id");
    }
    public void Set_Issued_Date(Date issued_date){
        this.issued_date=issued_date;
    }

    public void AddToOverAdvanceDatabase() {
        if (dbm.checkWhetherDataExists("gl_over_advance", "sup_id", sup_id) == 0) {
            try {
                dbm.insert("INSERT INTO gl_over_advance(sup_id,sup_name,category_code,advance_taken,issued_date) VALUES('" + sup_id + "','" + sup_name + "','" + category_code + "','" + advance_taken + "','" + issued_date + "')");
            } catch (SQLException ex) {
                MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            }
        }
        else{
            dbm.updateDatabase("gl_over_advance","sup_id", sup_id,"sup_name", sup_name);
            dbm.updateDatabase("gl_over_advance","sup_id", sup_id,"category_code", category_code);
            dbm.updateDatabase("gl_over_advance","sup_id", sup_id,"advance_taken", advance_taken);
            dbm.updateDatabase("gl_over_advance","sup_id", sup_id,"issued_date", issued_date);
        }

    }
    
      public double[] calculate(int sup, String year, String month){
    
       
       
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
        
        
        
        
        
        
        values = billsum.GL_table(sup, year, month);
       
       
        int i = 0;
        int j = 0;
        int day;
        double[] day_values = new double[31];
        
        double total = 0;
        while (i < 1000 && values[i][0]!=null) {
            j=0;
            
           

           
            //chek = values[i][0];
          
             total+=Double.parseDouble(values[i][4]); 
                 i++;
        }
         
         
        
         //////////////////////////////////////////////////////////////////loans/////////////////////////////////////////////////////
         // ((DefaultTableModel) jTable4.getModel()).setNumRows(0);
           p = 0;
                q = 0;
               while (p < 1000) {
                   while (q < 5) {

                       values[p][q] = null;
                       q++;
                   }
                p++;
               }
        double loans_tot = 0;
        i = 0;
             
        // if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
      //   {      
               
       //      values = billsum.advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
      //   else{
             values= billsum.loans_table(sup, year, month);//}
          while (i < 1000 && values[i][0]!=null) {
            j=0;
            
           

           
            //chek = values[i][0];
          
             loans_tot+=Double.parseDouble(values[i][4]); 
                 i++;
        }
       
         
         
         
         
         
         
         
        /////////////////////////////////////////////////////////cash advance/////////////////////////////////////////////////////////
       
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
         double Other_Ad_total= 0;
         
        i = 0;
             
        // if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
      //   {      
               
       //      values = billsum.advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
      //   else{
             values= billsum.advance_table(sup, year,month);//}
          while (i < 1000 && values[i][0]!=null) {
            j=0;
            
           

          
           
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
           i =0;
           int x =0;
         //if(Integer.parseInt(dayfield.getText())<(Integer.parseInt(datehandler.get_advance_month_split_day())+1))
        // {values = billsum.other_advance_table(sup, year, datehandler.get_prev_month(datehandler.return_month_as_num(month)));}
        // else{ 
           values= billsum.other_advance_table(sup, year, month);//}
          while (i < 1000 && values[x][0]!=null) {
            j=0;
            //System.out.println(values[i][0]);
           

            
            Other_Ad_total+=Double.parseDouble(values[x][4]);
             // System.out.println(Ad_total);
                 i++;
                 x++;
        }
          double[] output = new double[5];
          output[0]= Double.parseDouble(billsum.two_dec_places(""+total));
          output[1]= Double.parseDouble(billsum.two_dec_places(""+Ad_total));
          output[2]=Double.parseDouble(billsum.two_dec_places(""+Other_Ad_total));
          output[3]=Double.parseDouble(billsum.two_dec_places(""+loans_tot));
        output[4]=Double.parseDouble(billsum.two_dec_places(""+dbm.checknReturnDoubleData("supplier_pre_debt_coins", "entry", year+ month+sup, "pre_debts")));
         return output;
        
//Advance_tot.setText(billsum.two_dec_places(""+Ad_total));
          // System.out.println(billsum.two_dec_places(""+Ad_total));
          // TotalKG.setText(billsum.two_dec_places(""+total));
          // System.out.println(billsum.two_dec_places(""+total));
            //loantot.setText(billsum.two_dec_places(""+loans_tot));
           // System.out.println(billsum.two_dec_places(""+loans_tot));
          //  total_ad.setText(billsum.two_dec_places(""+Ad_total));
          //  System.out.println(billsum.two_dec_places(""+Other_Ad_total));
           // pedet.setText(billsum.two_dec_places(""+dbm.checknReturnDoubleData("supplier_pre_debt_coins", "entry", year+ datehandler.return_month_as_num(month)+sup, "pre_debts")));
           //System.out.println(billsum.two_dec_places(""+dbm.checknReturnDoubleData("supplier_pre_debt_coins", "entry", year+ month+sup, "pre_debts")));
      
           // loans.setText(billsum.two_dec_places(""+loans_tot));
         //   System.out.println(month);
       // if(datehandler.return_month_as_num(month).equals("01")){year = (Integer.parseInt(year)-1)+"";}
        //System.out.println(year);
//             Advance_tot.setText(""+Ad_total);
//              TotalKG.setText(""+total);
//               loantot.setText(""+loans_tot);
//                 total_ad.setText(""+Ad_total);
//            pedet.setText(dbm.checknReturnData("supplier_pre_debt_coins", "entry", year+ datehandler.return_month_as_num(month)+sup, "pre_debts"));
//            loans.setText(""+loans_tot);
    }
    
    

}
