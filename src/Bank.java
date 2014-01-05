
public class Bank {

    private String bankCode;
    private String bankName;

    Bank(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    Bank() {
        bankCode = null;
        bankName = null;
    }

    public void setBankName(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void addToDataBase() {

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
