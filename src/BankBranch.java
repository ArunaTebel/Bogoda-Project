public class BankBranch {
    private int branchCode; 
    private String branchName;
    BankBranch(String branchName,int branchCode){
        this.branchName=branchName;
        this.branchCode=branchCode;
    }
    BankBranch(){
        branchCode=0;
        branchName=null;
    }
    public void setBranchName(String branchName){
        this.branchName=branchName;
    }
    public void setBranchCode(int branchCode){
        this.branchCode=branchCode;
    }
    public String getBranchName(){
        return branchName;
    }
    public int getBranchCode(){
        return branchCode;
    }
    public void addToDataBase(){
        
    }
    public void removeFromDataBase(){
        
    }
    public void editDataBase(){
        
    }
    
}
