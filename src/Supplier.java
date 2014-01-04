
public class Supplier {

    //Supplier's identification code. This code is used to access details of the supplier.
    private final int code;
    //Supplier details
    private final String name, sinhala_name, other_name, estate_name, address, tel_no,
            fax_no, e_mail, pay_type, bank, branch, acc_no, cat_code, trans_code,
            dob, doc, t_con_no, full_land, tea_land;

    //This constructor will be called when the customer is registered
    public Supplier(int code, String name, String sinhala_name, String other_name, String estate_name,
            String address, String tel_no, String fax_no, String e_mail, String pay_type,
            String bank, String branch, String acc_no, String cat_code, String trans_code,
            String dob, String doc, String t_con_no, String full_land, String tea_land) {

        this.code = code;
        this.name = name;
        this.sinhala_name = sinhala_name;
        this.other_name = other_name;
        this.estate_name = estate_name;
        this.address = address;
        this.tel_no = tel_no;
        this.fax_no = fax_no;
        this.e_mail = e_mail;
        this.pay_type = pay_type;
        this.bank = bank;
        this.branch = branch;
        this.acc_no = acc_no;
        this.cat_code = cat_code;
        this.trans_code = trans_code;
        this.dob = dob;
        this.doc = doc;
        this.t_con_no = t_con_no;
        this.full_land = full_land;
        this.tea_land = tea_land;
    }

    //This constructor will be created when the supplier details have to be edited or supplier has to be deleted
    public Supplier() {
        this.code = 0;
        this.name = null;
        this.sinhala_name = null;
        this.other_name = null;
        this.estate_name = null;
        this.address = null;
        this.tel_no = null;
        this.fax_no = null;
        this.e_mail = null;
        this.pay_type = null;
        this.bank = null;
        this.branch = null;
        this.acc_no = null;
        this.cat_code = null;
        this.trans_code = null;
        this.dob = null;
        this.doc = null;
        this.t_con_no = null;
        this.full_land = null;
        this.tea_land = null;
    }

    //Editing details of an existing entry of a customer
    public void editDetails(int code) {
        /*
         UPDATE THE RELEVANT ENTRY ACCORDING TO THE 'code' IN THE DATABASE
         */
    }

    //Deletion of a supplier entry
    public void removeSupplier(int code) {
        /*
         GET THE RELEVANT ENTRY ACCORDING TO THE 'code' FROM THE DATABASE, AND DELETE THE ROW
         */
    }
}
