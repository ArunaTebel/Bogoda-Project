
public class Supplier {

    private final int code;
    private final String name, sinhala_name, other_name, estate_name, address, tel_no,
            fax_no, e_mail, pay_type, bank, branch, acc_no, cat_code, trans_code,
            dob, doc, t_con_no, full_land, tea_land;

    Supplier(int code, String name, String sinhala_name, String other_name, String estate_name,
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

    public void editDetails() {

    }
}
