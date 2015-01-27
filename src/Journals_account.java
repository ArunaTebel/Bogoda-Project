
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Journals_account {

    int tr_no;
    String refNo;
    //String journalNo; // auto
    Date date; // this should be automatically diplayed
    String payType; // this is selected from a combo box... it should contain cash/cheque and credit.. Deafault should be set to cash
    int bankCode;
    String bankName; // this should be automatically filled..
    int branchCode;
    String branchName; // this should be automatically filled..
    String chequeNo; // check whether we can use int here..
    Date chequeDate;
    String description;

    public Journals_account() {
        refNo = null;
//        journalNo = null;
        date = null;
        payType = null;
        bankCode = 0;
        bankName = null;
        branchCode = 0;
        branchName = null;
        chequeNo = null;
        chequeDate = null;
        description = null;
    }

    // Setters
    public void setTrNo(int tr_no) {
        this.tr_no = tr_no;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    // Getters
    public String getRefNo() {
        return refNo;
    }

    public Date getDate() {
        return date;
    }

    public String getPayType() {
        return payType;
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

    public int Get_Tr_no() {
        return tr_no;
    }

    public boolean addToMainJournalDataBaseBank() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(ref_no,date,pay_type,description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date) VALUES('" + refNo + "','" + date + "','" + payType + "','" + description + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        try {
            ResultSet rs = dbCon.query("SELECT LAST_INSERT_ID()");
            while (rs.next()) {
                tr_no = rs.getInt(1);
            }

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }
    
     public boolean addToMainJournalDataBaseBankEdit() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(tr_no,ref_no,date,pay_type,description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date) VALUES('" + tr_no + "','" + refNo + "','" + date + "','" + payType + "','" + description + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }

    public boolean addToMainJournalDataBaseBankall() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main_all(ref_no,date,pay_type,description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date) VALUES('" + refNo + "','" + date + "','" + payType + "','" + description + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        try {
            ResultSet rs = dbCon.query("SELECT LAST_INSERT_ID()");
            while (rs.next()) {
                tr_no = rs.getInt(1);
            }

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }

    public boolean addToMainJournalDataBaseCash() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(ref_no,date,pay_type,description) VALUES('" + refNo + "','" + date + "','" + payType + "','" + description + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        try {
            ResultSet rs = dbCon.query("SELECT LAST_INSERT_ID()");
            while (rs.next()) {
                tr_no = rs.getInt(1);
            }

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }
    
     public boolean addToMainJournalDataBaseCashEdit() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main(tr_no,ref_no,date,pay_type,description) VALUES('" + tr_no + "','" + refNo + "','" + date + "','" + payType + "','" + description + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }

    public boolean addToMainJournalDataBaseCashall() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_main_all(ref_no,date,pay_type,description) VALUES('" + refNo + "','" + date + "','" + payType + "','" + description + "')");
        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        try {
            ResultSet rs = dbCon.query("SELECT LAST_INSERT_ID()");
            while (rs.next()) {
                tr_no = rs.getInt(1);
            }

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }

        return true;
    }

    public boolean addToDebitDataBase(int debit_acnt_code, String debit_acnt_name, String debit_descriptn, double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_debitside(tr_no,debit_account_id,debit_account_name,debit_description,debit_amount) VALUES('" + tr_no + "','" + debit_acnt_code + "','" + debit_acnt_name + "','" + debit_descriptn + "','" + debit_amount + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
    
    public boolean newAddToDebitDataBaseBank(int debit_acnt_code, String debit_acnt_name, String debit_descriptn, double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        
        try {
            dbCon.insert("INSERT INTO account_journal(tr_no,debit_credit,ref_no,date,pay_type,account_id,account_name,description_long,description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,amount) VALUES('" + tr_no + "','" + "debit" + "','" + refNo + "','" + date + "','" + payType + "','" + debit_acnt_code + "','" + debit_acnt_name + "','"+description+"','" + debit_descriptn + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "','" + debit_amount + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Reciepts_accounts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true; 
    }
    
    
    public boolean newAddToDebitDataBaseCash(int debit_acnt_code, String debit_acnt_name, String debit_descriptn, double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal(tr_no,debit_credit,ref_no,date,pay_type,account_id,account_name,description_long,description,amount) VALUES('" + tr_no + "','" + "debit" + "','" + refNo + "','" + date + "','" + payType + "','" + debit_acnt_code + "','" + debit_acnt_name + "','"+description+"','" + debit_descriptn + "','" + debit_amount + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Reciepts_accounts.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return true; 
    }

    public boolean addToDebitDataBaseall(int debit_acnt_code, String debit_acnt_name, String debit_descriptn, double debit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_debitside_all(tr_no,debit_account_id,debit_account_name,debit_description,debit_amount) VALUES('" + tr_no + "','" + debit_acnt_code + "','" + debit_acnt_name + "','" + debit_descriptn + "','" + debit_amount + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean addToCreditDataBase(int credit_acnt_code, String credit_acnt_name, String credit_descriptn, double credit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_creditside(tr_no,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + tr_no + "','" + credit_acnt_code + "','" + credit_acnt_name + "','" + credit_descriptn + "','" + credit_amount + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }
    
    public boolean newAddToCreditDataBaseBank(int credit_acnt_code, String credit_acnt_name, String credit_descriptn, double credit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        
        try {
            dbCon.insert("INSERT INTO account_journal(tr_no,debit_credit,ref_no,date,pay_type,account_id,account_name,description_long,description,bank_id,bank_name,branch_id,branch_name,cheque_no,cheque_date,amount) VALUES('" + tr_no + "','" + "credit" + "','" + refNo + "','" + date + "','" + payType + "','" + credit_acnt_code + "','" + credit_acnt_name + "','"+description+"','" + credit_descriptn + "','" + bankCode + "','" + bankName + "','" + branchCode + "','" + branchName + "','" + chequeNo + "','" + chequeDate + "','" + credit_amount + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Reciepts_accounts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public boolean newAddToCreditDataBaseCash(int credit_acnt_code, String credit_acnt_name, String credit_descriptn, double credit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal(tr_no,debit_credit,ref_no,date,pay_type,account_id,account_name,description_long,description,amount) VALUES('" + tr_no + "','" + "credit" + "','" + refNo + "','" + date + "','" + payType + "','" + credit_acnt_code + "','" + credit_acnt_name + "','"+description+"','" + credit_descriptn + "','" + credit_amount + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Reciepts_accounts.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return true;  
    }

    public boolean addToCreditDataBaseall(int credit_acnt_code, String credit_acnt_name, String credit_descriptn, double credit_amount) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO account_journal_creditside_all(tr_no,credit_account_id,credit_account_name,credit_description,credit_amount) VALUES('" + tr_no + "','" + credit_acnt_code + "','" + credit_acnt_name + "','" + credit_descriptn + "','" + credit_amount + "')");

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
            return false;
        }
        return true;
    }

    public boolean UpdateMainDatabaseBank(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "ref_no", refNo);
        //dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "journal_no",journalNo);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "date", date);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "pay_type", payType);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "bank_id", bankCode);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "bank_name", bankName);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "branch_id", branchCode);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "branch_name", branchName);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "cheque_no", chequeNo);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "cheque_date", chequeDate);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "description", description);
        return true;
    }

    public boolean UpdateMainDatabaseBankall(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "ref_no", refNo);
//        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "journal_no",journalNo);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "date", date);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "pay_type", payType);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "bank_id", bankCode);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "bank_name", bankName);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "branch_id", branchCode);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "branch_name", branchName);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "cheque_no", chequeNo);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "cheque_date", chequeDate);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "description", description);

        return true;
    }

    public boolean UpdateMainDatabaseCash(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "ref_no", refNo);
//        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "journal_no",journalNo);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "date", date);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "pay_type", payType);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "description", description);

        return true;
    }

    public boolean UpdateMainDatabaseCashall(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "ref_no", refNo);
//        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "journal_no",journalNo);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "date", date);
        dbCon.updateDatabase("account_journal_main_all", "tr_no", tr_no, "pay_type", payType);
        dbCon.updateDatabase("account_journal_main", "tr_no", tr_no, "description", description);
        return true;
    }

    public void DeleteDebitEntries(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.CheckNDeleteFromDataBase("account_journal_debitside", "tr_no", tr_no);
    }

    public void DeleteDebitEntriesall(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.CheckNDeleteFromDataBase("account_journal_debitside_all", "tr_no", tr_no);
    }

    public void DeleteCreditEntries(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.CheckNDeleteFromDataBase("account_journal_creditside", "tr_no", tr_no);
    }

    public void DeleteCreditEntriesall(int tr_no) {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        dbCon.CheckNDeleteFromDataBase("account_journal_creditside_all", "tr_no", tr_no);
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
