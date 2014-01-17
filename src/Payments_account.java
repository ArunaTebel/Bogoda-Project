
public class Payments_account {
    int refNo;
    int recieptNo; // auto
    String date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and credit.. Deafault should be set to cash
    String credit_accountCode; // set default the account code of cash..
    String credit_accountName;  // this should be automatically filled.. 
    String credit_description;
    String bankCode;
    String bankName; // this should be automatically filled..
    String branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    String chequeDate;
    Double amount;
    String[] debit_accountCode;// there can be several credit entries
    String[] debit_accountName;
    String[] debit_description;
    double difference; // allow save only if this is zero
    
}
