
public class BankBranch {

    private String branchCode;
    private String branchName;

    BankBranch(String branchName, String branchCode) {
        this.branchName = branchName;
        this.branchCode = branchCode;
    }

    BankBranch() {
        branchCode = null;
        branchName = null;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void addToDataBase() {

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }

}
