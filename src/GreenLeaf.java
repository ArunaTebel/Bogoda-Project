
import java.sql.Date;
import java.sql.SQLException;


public class GreenLeaf {

    private Date date;
    private int transaction_id;
    private int supplierCode;
    private String categoryCode;
    private int noOfSacks;
    private double totalWeight;
    private double sacksWeight;
    private double water;
    private double coarseLeaf;
    private double other;
    private double netQuantity;
    private double selfTransport; // Yes/No button   new thing we added :)
    private String leafCategory;
    private double intselfTransport;

    GreenLeaf(Date date,int transaction_id, int supplierCode, String categoryCode, int noOfSacks, double totalWeight, double sacksWeight, double water, double coarseLeaf, double other, double selfTransport,double netQuantity,String leafCategory) {
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
        categoryCode = null;
        noOfSacks = 0;
        totalWeight = 0;
        sacksWeight = 0;
        water = 0;
        coarseLeaf = 0;
        other = 0;
        selfTransport = 0;
        netQuantity=0;
        leafCategory=null;
        intselfTransport=0;

    }

    public double calcNetQuantity() {
        netQuantity = totalWeight - sacksWeight - water - coarseLeaf - other;
        return netQuantity;
    }

    // Setters
    
    public void setDate(Date date) {
        this.date = date;
    }
    public void setTransactionId(int transaction_id){
        this.transaction_id=transaction_id;
    }

    public void setSupplierCode(int supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setCategoryCode(String categoryCode) {
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

    public void setSelfTransport(double selfTransport) {
        this.selfTransport = selfTransport;
    }

    public void setNetQuantity(double netQuantity) {   // This method may not be needed.
        this.netQuantity = netQuantity;
    }
    public void setLeafCategory(String leafCategory){
        this.leafCategory=leafCategory;
    }
    public void setIntselfTransport(boolean sTransport ){
        
    }
    
    // Getters
    
    public Date getDate() {
        return date;
    }
    public int getTransactionId(){
        return transaction_id;
    }
    public int getSupplierCode() {
        return supplierCode;
    }

    public String getCategoryCode() {
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

    public double getSelfTransport() {
        return selfTransport;
    }
    
    public String getLeafCategory(){
        return leafCategory;
    }

    public void addToDataBase() {
        DatabaseManager dbCon = DatabaseManager.getDbCon();
        try {
            dbCon.insert("INSERT INTO green_leaf_transactions(tr_date,sup_id,category_code,no_of_sacks,total_kg,sack_kg,water_kg,coarse_leaf_kg,other,transport,leaf_category,net_qty) VALUES('" + date + "','" + supplierCode + "','" + categoryCode + "','" + noOfSacks + "','" + totalWeight +"','" + sacksWeight + "','" + water +"','" + coarseLeaf + "','" + other+"','" + selfTransport+"','" +leafCategory+"','"+netQuantity+"')");
             

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {
          DatabaseManager dbCon = DatabaseManager.getDbCon();
            try {
             //   System.out.println("UPDATE  green_leaf_transactions SET tr_date= '"+date+",sup_id='"+supplierCode+"',category_code= '"+categoryCode+"',no_of_sacks= '"+noOfSacks+"',total_kg= '"+totalWeight+"',sack_kg= '"+sacksWeight+"',water_kg= '"+water+"',coarse_leaf_kg= '"+coarseLeaf+"',other= '"+other+"',transport= '"+selfTransport+"',leaf_category= '"+leafCategory+"',net_qty='"+netQuantity+"' WHERE tr_id = '"+transaction_id+"'");
            dbCon.insert("UPDATE  green_leaf_transactions SET tr_date= '"+date+"',sup_id='"+supplierCode+"',category_code= '"+categoryCode+"',no_of_sacks= '"+noOfSacks+"',total_kg= '"+totalWeight+"',sack_kg= '"+sacksWeight+"',water_kg= '"+water+"',coarse_leaf_kg= '"+coarseLeaf+"',other= '"+other+"',transport= '"+selfTransport+"',leaf_category= '"+leafCategory+"',net_qty='"+netQuantity+"' WHERE tr_id = '"+transaction_id+"'");
             

        } catch (SQLException ex) {
            MessageBox.showMessage(ex.getMessage(), "SQL Error", "error");
        }

    }
}
