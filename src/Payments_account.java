
import java.sql.Date;
import java.sql.SQLException;

public class Payments_account {

    int refNo;
    int recieptNo; // auto
    Date date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and debit.. Deafault should be set to cash
    int credit_accountCode; // set default the account code of cash..
    String credit_accountName;  // this should be automatically filled.. 
    String credit_description;
    int bankCode;
    String bankName; // this should be automatically filled..
    int branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    Date chequeDate;
    double creditAmount;
    int[] debit_accountCode = new int[5];// there can be several debit entries
    String[] debit_accountName = new String[5];
    String[] debit_description = new String[5];
    double[] debitAmount = new double[5];
    double difference; // allow save only if this is zero
    double debitTotal; // total of all debit entries

    public Payments_account() {
        refNo = 0;
        recieptNo = 0;
        date = null;
        payType = null;
        credit_accountCode = 0;
        credit_accountName = null;
        credit_description = null;
        bankCode = 0;
        bankName = null;
        branchCode = 0;
        branchName = null;
        chequeDate = null;
        creditAmount = 0;
        for (int i = 0; i < 5; i++) {
            debit_accountCode[i] = 0;
            debit_accountName[i] = null;
            debit_description[i] = null;
        }
        for (int i = 0; i < 5; i++) {
            debitAmount[i] = 0;
        }
        debitTotal = 0;

    }

    // Setters
    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }

    public void setRecieptNo(int recieptNo) {
        this.recieptNo = recieptNo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public void setCredit_accountCode(int credit_accountCode) {
        this.credit_accountCode = credit_accountCode;
    }

    public void setCredit_accountName(String credit_accountName) {
        this.credit_accountName = credit_accountName;
    }

    public void setCredit_description(String credit_description) {
        this.credit_description = credit_description;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setDebit_accountCode1(int debitAccountCode) {
        debit_accountCode[0] = debitAccountCode;
    }

    public void setDebit_accountName1(String debitAccountName) {
        debit_accountName[0] = debitAccountName;
    }

    public void setDebit_description1(String debitDescription) {
        debit_description[0] = debitDescription;
    }

    public void setDebitAmount1(double debitAmnt) {
        debitAmount[0] = debitAmnt;
    }

    public void setDebit_accountCode2(int debitAccountCode) {
        debit_accountCode[1] = debitAccountCode;
    }

    public void setDebit_accountName2(String debitAccountName) {
        debit_accountName[1] = debitAccountName;
    }

    public void setDebit_description2(String debitDescription) {
        debit_description[1] = debitDescription;
    }

    public void setDebitAmount2(double debitAmnt) {
        debitAmount[1] = debitAmnt;
    }

    public void setDebit_accountCode3(int debitAccountCode) {
        debit_accountCode[2] = debitAccountCode;
    }

    public void setDebit_accountName3(String debitAccountName) {
        debit_accountName[2] = debitAccountName;
    }

    public void setDebit_description3(String debitDescription) {
        debit_description[2] = debitDescription;
    }

    public void setDebitAmount3(double debitAmnt) {
        debitAmount[2] = debitAmnt;
    }

    public void setDebit_accountCode4(int debitAccountCode) {
        debit_accountCode[3] = debitAccountCode;
    }

    public void setDebit_accountName4(String debitAccountName) {
        debit_accountName[3] = debitAccountName;
    }

    public void setDebit_description4(String debitDescription) {
        debit_description[3] = debitDescription;
    }

    public void setDebitAmount4(double debitAmnt) {
        debitAmount[3] = debitAmnt;
    }

    public void setDebit_accountCode5(int debitAccountCode) {
        debit_accountCode[4] = debitAccountCode;
    }

    public void setDebit_accountName5(String debitAccountName) {
        debit_accountName[4] = debitAccountName;
    }

    public void setDebit_description5(String debitDescription) {
        debit_description[4] = debitDescription;
    }

    public void setDebitAmount5(double debitAmnt) {
        debitAmount[4] = debitAmnt;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public void setDebitTotal() {
        for (int i = 0; i < 5; i++) {
            debitTotal = debitAmount[0];
        }
    }

    public void setDifference() {
        difference = creditAmount - debitTotal;
    }

    // Getters
    public int getRefNo() {
        return refNo;
    }

    public int getRecieptNo() {
        return recieptNo;
    }

    public Date getDate() {
        return date;
    }

    public String getPayType() {
        return payType;
    }

    public int getCredit_accountCode() {
        return credit_accountCode;
    }

    public String getCredit_accountName() {
        return credit_accountName;
    }

    public String getCredit_description() {
        return credit_description;
    }

    public int getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public int getDebit_accountCode1() {
        return debit_accountCode[0];
    }

    public String getDebit_accountName1() {
        return debit_accountName[0];
    }

    public String getDebit_description1() {
        return debit_description[0];
    }

    public double getDebitAmount1() {
        return debitAmount[0];
    }

    public int getDebit_accountCode2() {
        return debit_accountCode[1];
    }

    public String getDebit_accountName2() {
        return debit_accountName[1];
    }

    public String getDebit_description2() {
        return debit_description[1];
    }

    public double getDebitAmount2() {
        return debitAmount[1];
    }

    public int getDebit_accountCode3() {
        return debit_accountCode[2];
    }

    public String getDebit_accountName3() {
        return debit_accountName[2];
    }

    public String getDebit_description3() {
        return debit_description[2];
    }

    public double getDebitAmount3() {
        return debitAmount[2];
    }

    public int getDebit_accountCode4() {
        return debit_accountCode[3];
    }

    public String getDebit_accountName4() {
        return debit_accountName[3];
    }

    public String getDebit_description4() {
        return debit_description[3];
    }

    public double getDebitAmount4() {
        return debitAmount[3];
    }

    public int getDebit_accountCode5() {
        return debit_accountCode[4];
    }

    public String getDebit_accountName5() {
        return debit_accountName[4];
    }

    public String getDebit_description5() {
        return debit_description[4];
    }

    public double setDebitAmount5() {
        return debitAmount[4];
    }

    public double getDifference() {
        return difference;
    }

    public double getDebitTotal() {
        return debitTotal;
    }

    public boolean addToCreditDataBaseBank() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_payment_creditside(ref_no,reciept_no,date,pay_type,credit_account_id,credit_account_name,credit_description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,credit_amount) VALUES('" + refNo + "','" + recieptNo + "','" + date + "','" + payType + "','" + credit_accountCode + "','" + credit_accountName + "','" + credit_description + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "','" + creditAmount + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean addToCreditDataBaseCash() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_payment_creditside(ref_no,reciept_no,date,pay_type,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + refNo + "','" + recieptNo + "','" + date + "','" + payType + "','" + credit_accountCode + "','" + credit_accountName + "','" + credit_description + "','" + creditAmount + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean addToDebitDataBase(int ref_num, int debit_acnt_code, String debit_acnt_name, String debit_descriptn,double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_payment_debitside(reciept_no,debit_account_id,debit_account_name,debit_description,debit_amount) VALUES('" + ref_num + "','" + debit_acnt_code + "','" + debit_acnt_name + "','" + debit_descriptn + "','"+ debit_amount+"')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    /*   // this has to be coded later
     public void addToDebitDataBase() {
     DatabaseManager dbCon = DatabaseManager.getDbCon();
     try {
     dbCon.insert("INSERT INTO bank(bank_id,bank_name) VALUES('" + bankCode + "','" + bankName + "')");
     } catch (SQLException ex) {
     MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
     }

     } */

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
