
public class Reciepts_accounts {

    int refNo;
    int recieptNo; // auto
    String date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and credit.. Deafault should be set to cash
    String debit_accountCode; // set default the account code of cash..
    String debit_accountName;  // this should be automatically filled.. 
    String debit_description;
    String bankCode;
    String bankName; // this should be automatically filled..
    String branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    String chequeDate;
    double debitAmount;
    String[] credit_accountCode = new String[5];// there can be several credit entries
    String[] credit_accountName = new String[5];
    String[] credit_description = new String[5];
    double[] creditAmount = new double[5];
    double difference; // allow save only if this is zero

    public Reciepts_accounts() {
        refNo = 0;
        recieptNo = 0;
        date = null;
        payType = null;
        debit_accountCode = null;
        debit_accountName = null;
        debit_description = null;
        bankCode = null;
        bankName = null;
        branchCode = null;
        branchName = null;
        chequeDate = null;
        debitAmount = 0;
        for (int i = 0; i < 5; i++) {
            credit_accountCode[i] = null;
            credit_accountName[i] = null;
            credit_description[i] = null;
        }
        for (int i = 0; i < 5; i++) {
            creditAmount[i] = 0;
        }

    }

    public void setRefNo(int refNo) {
        this.refNo = refNo;
    }
    public void setRecieptNo(int recieptNo){
        this.recieptNo=recieptNo;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setPayType(String  payType){
        this.payType=payType;
    }
    public void setDebit_accountCode(String debit_accountCode){
        this.debit_accountCode=debit_accountCode;
    }
    

}
