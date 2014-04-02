
import java.sql.Date;
import java.sql.SQLException;

public class GL_Over_Advance {

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

}
