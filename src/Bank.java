public class Bank {
    private int bankCode;
    private String bankName;
    Bank(String bankName,int bankCode){
        this.bankName=bankName;
        this.bankCode=bankCode;
    }
    Bank(){
        bankCode=0;
        bankName=null;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    } 
    public void setBankCode(int bankCode){
        this.bankCode=bankCode;
    }
    public String getBankName(){
        return bankName;
    }
    public int getBankCode(){
        return bankCode;
    }
    public void addToDataBase(){
        
    }
    public void removeFromDataBase(){
        
    }
    public void editDataBase(){
        
    }
}

