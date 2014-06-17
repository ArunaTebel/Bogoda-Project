
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Supplier {

    /*Supplier's identification code. This code is used to access details of the
     supplier..*/
    private final int code, bank, branch,welf_num;
    private final String transRate;
    //Supplier details
    private final String name, sinhala_name, address, tel_no,
            pay_type, acc_no, cat_code, leaf , active, welfare, wel_d, wel_d_n;
    private final Date doc;
    //retreiving the database connection
    DatabaseManager dbCon = DatabaseManager.getDbCon();

    //This constructor will be called when the customer is registered
    public Supplier(int code, String name, String sinhala_name,
            String address, String tel_no, String pay_type,
            int bank, int branch, String acc_no, String cat_code,
            Date doc, String transRate,String LEAF,String ACT,String WELF,int WELFN,String WELF_D,String WELF_DN) {

        this.code = code;
        this.name = name;
        this.sinhala_name = sinhala_name;
       
        this.address = address;
        this.tel_no = tel_no;
        this.pay_type = pay_type;
        this.bank = bank;
        this.branch = branch;
        this.acc_no = acc_no;
        this.cat_code = cat_code;
        this.doc = doc;
        this.transRate = transRate;
        this.leaf = LEAF;
        this.active = ACT;
        this.welfare= WELF;
        this.welf_num = WELFN;
        this.wel_d= WELF_D;
        this.wel_d_n = WELF_DN;
    }

    /*This constructor will be created when the supplier details have to be edited 
     or supplier has to be deleted*/
    public Supplier() {
        this.code = 0;
        this.name = null;
        this.sinhala_name = null;
       
        this.address = null;
        this.tel_no = null;
        this.pay_type = null;
        this.bank = 0;
        this.branch = 0;
        this.acc_no = null;
        this.cat_code = null;
        this.doc = null;
        this.transRate = null;
        this.leaf = null;
        this.active = null;
        this.welfare = null;
        this.welf_num = 0;
        this.wel_d= null;
        this.wel_d_n = null;
    }

    //Editing details of an existing entry of a customer
    public void editDetails(int code) {
        /*
         UPDATE THE RELEVANT ENTRY ACCORDING TO THE 'code' IN THE DATABASE
         */
        try {
            ResultSet rs = dbCon.query("SELECT * FROM suppliers WHERE `sup_id`=" + code);
            while (rs.next()) {
                System.out.println(rs.getString("sup_name"));
            }
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }

    //Deletion of a supplier entry
    public void removeSupplier(int code) {
        /*
         GET THE RELEVANT ENTRY ACCORDING TO THE 'code' FROM THE DATABASE, AND DELETE THE ROW
         */
    }

    public void addToDatabase() {
        try {
            dbCon.insert("INSERT INTO suppliers (sup_id, sup_name, sup_sin_name, sup_estate_name, sup_address, sup_tel, sup_pay_type, bank_id, branch_id, sup_acc_no, cat_id, sup_doc,                                                                                                                                                                                                trans_rate,leaf_rate_code,active, welfare,welf_num,wel_due,wel_due_new) "
                    + "VALUES (" + code + "," + "'" + name + "'" + "," + "'" + sinhala_name + "'" + "," + "'" + "0" + "'" + "," + "'" + address + "'" + "," + "'" + tel_no + "'" + "," + "'" + pay_type + "'" + "," + bank + "," + branch + "," + "'" + acc_no + "'" + "," + "'" + cat_code + "'" + "," + "'" + doc + "'" +  "," + "'" + transRate + "'" + "" + "'" + leaf + "'" + "," + "'" + active + "'" + "," + "'" + welfare + "'" + "," + "'" + welf_num + "'" + "," + "'" + wel_d + "'" + "," + "'" + wel_d_n + ")");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
     public void update(){
        try {
            dbCon.insert("UPDATE  suppliers  SET sup_name ='" + name + "',sup_sin_name ='" + sinhala_name + "',sup_estate_name ='" + "0" + "',sup_address ='" + address + "',sup_tel ='" + tel_no + "',sup_pay_type ='" + pay_type + "',bank_id ='" + bank + "',branch_id ='" + branch + "',sup_acc_no ='" + acc_no + "',cat_id ='" + cat_code + "',sup_doc ='" + doc + "',trans_rate ='" + transRate + "',leaf_rate_code ='" + leaf + "',active ='" + active + "',welfare ='" + welfare + "',welf_num ='" + welf_num + "',wel_due ='" + wel_d + "',wel_due_new ='" + wel_d_n + "' WHERE sup_id='" + code + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

     
     
     }
}
