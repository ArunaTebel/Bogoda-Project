
import java.sql.ResultSet;
import java.sql.SQLException;

public class Supplier {

    /*Supplier's identification code. This code is used to access details of the
    supplier..*/
    private final int code, bank, branch;
    //Supplier details
    private final String name, sinhala_name, estate_name, address, tel_no,
            pay_type, acc_no, cat_code,
            doc;
    //retreiving the database connection
    DatabaseManager dbCon = DatabaseManager.getDbCon();

    //This constructor will be called when the customer is registered
    public Supplier(int code, String name, String sinhala_name, String estate_name,
            String address, String tel_no, String pay_type,
            int bank, int branch, String acc_no, String cat_code,
            String doc) {

        this.code = code;
        this.name = name;
        this.sinhala_name = sinhala_name;
        this.estate_name = estate_name;
        this.address = address;
        this.tel_no = tel_no;
        this.pay_type = pay_type;
        this.bank = bank;
        this.branch = branch;
        this.acc_no = acc_no;
        this.cat_code = cat_code;
        this.doc = doc;
    }

    /*This constructor will be created when the supplier details have to be edited 
    or supplier has to be deleted*/
    public Supplier() {
        this.code = 0;
        this.name = null;
        this.sinhala_name = null;
        this.estate_name = null;
        this.address = null;
        this.tel_no = null;
        this.pay_type = null;
        this.bank = 0;
        this.branch = 0;
        this.acc_no = null;
        this.cat_code = null;
        this.doc = null;
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
            dbCon.insert("INSERT INTO suppliers (sup_id, sup_name, sup_sin_name, sup_estate_name, sup_address, sup_tel, sup_pay_type, bank_id, branch_id, sup_acc_no, cat_id, sup_doc) "
                    + "VALUES (" + code + "," + name + "," + sinhala_name + "," + estate_name + "," + address + "," + tel_no + "," + pay_type + "," + bank + "," + branch + "," + acc_no + "," + cat_code + "," + doc + ")");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }
    }
}
