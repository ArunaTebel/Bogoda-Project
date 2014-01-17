
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
    Double amount;
    String[] credit_accountCode;// there can be several credit entries
    String[] credit_accountName;
    String[] credit_description;
    double difference; // allow save only if this is zero
    
    
    
    
    
}
