
import java.sql.Date;
import java.sql.SQLException;

public class Journals_account {
    int refNo;
    int journalNo; // auto
    Date date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and credit.. Deafault should be set to cash
    int bankCode;
    String bankName; // this should be automatically filled..
    int branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    Date chequeDate;
    
   public Journals_account() {
        refNo = 0;
        journalNo = 0;
        date = null;
        payType = null;
        bankCode = 0;
        bankName = null;
        branchCode = 0;
        branchName = null;
        chequeNo=null;
        chequeDate = null;
    }
    
    // Setters

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }
    public void setJournalNo(int recieptNo){
        this.journalNo=recieptNo;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setPayType(String  payType){
        this.payType=payType;
    }
    public void setBankCode(int bankCode){
        this.bankCode=bankCode;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    }
    public void setBranchCode(int branchCode){
        this.branchCode=branchCode;
    }
    public void setBranchName(String branchName){
        this.branchName=branchName;
    }
    public void setChequeNo(String chequeNo){
        this.chequeNo=chequeNo;
    }
    public void setChequeDate(Date chequeDate){
        this.chequeDate=chequeDate;
    }
    
    // Getters
    
    
    public int getRefNo() {
        return refNo;
    }
    public int getJournaltNo(){
        return journalNo;
    }
    public Date getDate(){
        return date;
    }
    public String getPayType(){
        return payType;
    }
    public int getBankCode(){
        return bankCode;
    }
    public String getBankName(){
        return bankName;
    }
    public int getBranchCode(){
        return branchCode;
    }
    public String getBranchName(){
        return branchName;
    }
    public String getChequeNo(){
        return chequeNo;
    }
    public Date getChequeDate(){
        return chequeDate;
    }
   
    public boolean addToMainJournalDataBaseBank() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(ref_no,journal_no,date,pay_type,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date) VALUES('" + refNo + "','" + journalNo + "','" +date+"','" +payType+"','"  +bankCode+"','" +bankName+"','" +branchCode+"','" +branchName+"','" +chequeNo+"','" +chequeDate+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
     public boolean addToMainJournalDataBaseCash() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(ref_no,journal_no,date,pay_type) VALUES('" + refNo + "','" + journalNo + "','" +date+"','" +payType+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
     public boolean addToDebitDataBase(int journal_num , int debit_acnt_code, String debit_acnt_name, String debit_descriptn,double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_debitside(journal_no,debit_account_id,debit_account_name,debit_description,debit_amount) VALUES('" + journal_num + "','" + debit_acnt_code + "','" + debit_acnt_name + "','" + debit_descriptn + "','"+ debit_amount+"')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
     }
     
     public boolean addToCreditDataBase(int journal_num , int credit_acnt_code, String credit_acnt_name, String credit_descriptn,double credit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_creditside(journal_no,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + journal_num + "','" + credit_acnt_code + "','" + credit_acnt_name + "','" + credit_descriptn + "','"+ credit_amount+"')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
     }
     
     
        
      
        
   /*   // this has to be coded later
    public void addToCreditDataBase() {
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
