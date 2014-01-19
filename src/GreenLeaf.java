
import java.sql.SQLException;


public class GreenLeaf {

    private String date;
    private int transaction_id;
    private int supplierCode;
    private int categoryCode;
    private int noOfSacks;
    private double totalWeight;
    private double sacksWeight;
    private double water;
    private double coarseLeaf;
    private double other;
    private double netQuantity;
    private boolean selfTransport; // Yes/No button   new thing we added :)
    private int leafCategory;
    private int intselfTransport;

    GreenLeaf(String date,int transaction_id, int supplierCode, int categoryCode, int noOfSacks, double totalWeight, double sacksWeight, double water, double coarseLeaf, double other, boolean selfTransport,double netQuantity,int leafCategory) {
        this.date = date;
        this.transaction_id=transaction_id;
        this.supplierCode = supplierCode;
        this.categoryCode = categoryCode;
        this.noOfSacks = noOfSacks;
        this.totalWeight = totalWeight;
        this.sacksWeight = sacksWeight;
        this.water = water;
        this.coarseLeaf = coarseLeaf;
        this.other = other;
        this.selfTransport = selfTransport;
        this.netQuantity=netQuantity;
        this.leafCategory=leafCategory;
        
    }

    GreenLeaf() {
        date = null;
        transaction_id=0;
        supplierCode = 0;
        categoryCode = 0;
        noOfSacks = 0;
        totalWeight = 0;
        sacksWeight = 0;
        water = 0;
        coarseLeaf = 0;
        other = 0;
        selfTransport = false;
        netQuantity=0;
        leafCategory=0;
        intselfTransport=0;

    }

    public double calcNetQuantity() {
        netQuantity = totalWeight - sacksWeight - water - coarseLeaf - other;
        return netQuantity;
    }

    // Setters
    
    public void setDate(String date) {
        this.date = date;
    }
    public void setTransactionId(int transaction_id){
        this.transaction_id=transaction_id;
    }

    public void setSupplierCode(int supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setNoOfSacks(int noOfSacks) {
        this.noOfSacks = noOfSacks;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setSacksWeight(double sacksWeight) {
        this.sacksWeight = sacksWeight;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setCoarseLeaf(double coarseLeaf) {
        this.coarseLeaf = coarseLeaf;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public void setSelfTransport(boolean selfTransport) {
        this.selfTransport = selfTransport;
    }

    public void setNetQuantity(double netQuantity) {   // This method may not be needed.
        this.netQuantity = netQuantity;
    }
    public void setLeafCategory(int leafCategory){
        this.leafCategory=leafCategory;
    }
    public void setIntselfTransport(boolean sTransport ){
        if(sTransport==false){
            intselfTransport=0;
        }
        else{
            intselfTransport=1;
        }
    }
    
    // Getters
    
    public String getDate() {
        return date;
    }
    public int getTransactionId(){
        return transaction_id;
    }
    public int getSupplierCode() {
        return supplierCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public int getNoOfSacks() {
        return noOfSacks;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public double getSacksWeight() {
        return sacksWeight;
    }

    public double getWater() {
        return water;
    }

    public double getCoarseLeaf() {
        return coarseLeaf;
    }

    public double getOther() {
        return other;
    }

    public double getNetQuantity() {
        return netQuantity;
    }

    public boolean getSelfTransport() {
        return selfTransport;
    }
    
    public int getLeafCategory(){
        return leafCategory;
    }

    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO green_leaf_transactions(tr_date,tr_id,sup_id,category_code,no_of_sacks,total_kg,sack_kg,water_kg,coarse_leaf_kg,other,self_transport,leaf_category,net_qty) VALUES('" + date + "','" + transaction_id + "','" + supplierCode + "','" + categoryCode + "','" + noOfSacks + "','" + totalWeight +"','" + sacksWeight + "','" + water +"','" + coarseLeaf + "','" + other+"','" + intselfTransport+"','" +leafCategory+"','"+netQuantity+"')");
             

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
