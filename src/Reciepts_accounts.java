
import java.sql.Date;
import java.sql.SQLException;


public class Reciepts_accounts {

    int refNo;
    int recieptNo; // auto
    Date date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and credit.. Deafault should be set to cash
    int debit_accountCode; // set default the account code of cash..
    String debit_accountName;  // this should be automatically filled.. 
    String debit_description;
    int bankCode;
    String bankName; // this should be automatically filled..
    int branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    Date chequeDate;
    double debitAmount;
    int[] credit_accountCode = new int[5];// there can be several credit entries
    String[] credit_accountName = new String[5];
    String[] credit_description = new String[5];
    double[] creditAmount = new double[5];
    double difference; // allow save only if this is zero
    double creditTotal; // total of all credit entries

    public Reciepts_accounts() {
        refNo = 0;
        recieptNo = 0;
        date = null;
        payType = null;
        debit_accountCode = 0;
        debit_accountName = null;
        debit_description = null;
        bankCode = 0;
        bankName = null;
        branchCode = 0;
        branchName = null;
        chequeDate = null;
        debitAmount = 0;
        for (int i = 0; i < 5; i++) {
            credit_accountCode[i] = 0;
            credit_accountName[i] = null;
            credit_description[i] = null;
        }
        for (int i = 0; i < 5; i++) {
            creditAmount[i] = 0;
        }
        creditTotal=0;

    }
    
    // Setters

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }
    public void setRecieptNo(int recieptNo){
        this.recieptNo=recieptNo;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setPayType(String  payType){
        this.payType=payType;
    }
    public void setDebit_accountCode(int debit_accountCode){
        this.debit_accountCode=debit_accountCode;
    }
    public void setDebit_accountName(String debit_accountName){
        this.debit_accountName=debit_accountName;
    }
    public void setDebit_description(String debit_description){
        this.debit_description=debit_description;
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
    public void setDebitAmount(double debitAmount){
        this.debitAmount=debitAmount;
    }
    public void setCredit_accountCode1(int creditAccountCode){
        credit_accountCode[0]=creditAccountCode;
    }
    public void setCredit_accountName1(String creditAccountName){
        credit_accountName[0]=creditAccountName;
    }
    public void setCredit_description1(String creditDescription){
        credit_description[0]=creditDescription;
    }
    public void setCreditAmount1(double creditAmnt){
        creditAmount[0]=creditAmnt;
    }
     public void setCredit_accountCode2(int creditAccountCode){
        credit_accountCode[1]=creditAccountCode;
    }
    public void setCredit_accountName2(String creditAccountName){
        credit_accountName[1]=creditAccountName;
    }
    public void setCredit_description2(String creditDescription){
        credit_description[1]=creditDescription;
    }
    public void setCreditAmount2(double creditAmnt){
        creditAmount[1]=creditAmnt;
    }
     public void setCredit_accountCode3(int creditAccountCode){
        credit_accountCode[2]=creditAccountCode;
    }
    public void setCredit_accountName3(String creditAccountName){
        credit_accountName[2]=creditAccountName;
    }
    public void setCredit_description3(String creditDescription){
        credit_description[2]=creditDescription;
    }
    public void setCreditAmount3(double creditAmnt){
        creditAmount[2]=creditAmnt;
    }
     public void setCredit_accountCode4(int creditAccountCode){
        credit_accountCode[3]=creditAccountCode;
    }
    public void setCredit_accountName4(String creditAccountName){
        credit_accountName[3]=creditAccountName;
    }
    public void setCredit_description4(String creditDescription){
        credit_description[3]=creditDescription;
    }
    public void setCreditAmount4(double creditAmnt){
        creditAmount[3]=creditAmnt;
    }
     public void setCredit_accountCode5(int creditAccountCode){
        credit_accountCode[4]=creditAccountCode;
    }
    public void setCredit_accountName5(String creditAccountName){
        credit_accountName[4]=creditAccountName;
    }
    public void setCredit_description5(String creditDescription){
        credit_description[4]=creditDescription;
    }
    public void setCreditAmount5(double creditAmnt){
        creditAmount[4]=creditAmnt;
    }
    public void setDifference(double difference){
        this.difference=difference;
    }
    public void setCreditTotal(){
        for(int i=0;i<5;i++){
            creditTotal=creditAmount[0];
        }
    }
    public void setDifference(){
        difference= debitAmount-creditTotal;
    }
    
    // Getters
    
    
    public int getRefNo() {
        return refNo;
    }
    public int getRecieptNo(){
        return recieptNo;
    }
    public Date getDate(){
        return date;
    }
    public String getPayType(){
        return payType;
    }
    public int getDebit_accountCode(){
        return debit_accountCode;
    }
    public String getDebit_accountName(){
        return debit_accountName;
    }
    public String getDebit_description(){
        return debit_description;
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
    public double getDebitAmount(){
        return debitAmount;
    }
    public int getCredit_accountCode1(){
        return credit_accountCode[0];
    }
    public String getCredit_accountName1(){
        return credit_accountName[0];
    }
    public String getCredit_description1(){
        return credit_description[0];
    }
    public double getCreditAmount1(){
        return creditAmount[0];
    }
     public int getCredit_accountCode2(){
        return credit_accountCode[1];
    }
    public String getCredit_accountName2(){
        return credit_accountName[1];
    }
    public String getCredit_description2(){
        return credit_description[1];
    }
    public double getCreditAmount2(){
        return creditAmount[1];
    }
     public int getCredit_accountCode3(){
        return credit_accountCode[2];
    }
    public String getCredit_accountName3(){
        return credit_accountName[2];
    }
    public String getCredit_description3(){
        return credit_description[2];
    }
    public double getCreditAmount3(){
        return creditAmount[2];
    }
     public int getCredit_accountCode4(){
        return credit_accountCode[3];
    }
    public String getCredit_accountName4(){
        return credit_accountName[3];
    }
    public String getCredit_description4(){
        return credit_description[3];
    }
    public double getCreditAmount4(){
        return creditAmount[3];
    }
     public int getCredit_accountCode5(){
        return credit_accountCode[4];
    }
    public String getCredit_accountName5(){
        return credit_accountName[4];
    }
    public String getCredit_description5(){
        return credit_description[4];
    }
    public double setCreditAmount5(){
        return creditAmount[4];
    }
    public double getDifference(){
        return difference;
    }
    public double getCreditTotal(){
        return creditTotal;
    }  
   
    public boolean addToDebitDataBaseBank() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_reciept_debitside(ref_no,reciept_no,date,pay_type,debit_account_id,debit_account_name,debit_description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,debit_amount) VALUES('" + refNo + "','" + recieptNo + "','" +date+"','" +payType+"','" +debit_accountCode+"','" +debit_accountName+"','" +debit_description+"','" +bankCode+"','" +bankName+"','" +branchCode+"','" +branchName+"','" +chequeNo+"','" +chequeDate+"','" +debitAmount+"')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
     public boolean addToDebitDataBaseCash() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_reciept_debitside(ref_no,reciept_no,date,pay_type,debit_account_id,debit_account_name,debit_description,debit_amount) VALUES('" + refNo + "','" + recieptNo + "','" +date+"','" +payType+"','" +debit_accountCode+"','" +debit_accountName+"','" +debit_description+"','" + debitAmount+"')");
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
   

