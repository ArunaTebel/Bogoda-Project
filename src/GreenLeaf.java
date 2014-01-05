
public class GreenLeaf {

    private String date;
    private String supplierCode;
    private String categoryCode;
    private int noOfSacks;
    private double totalWeight;
    private double sacksWeight;
    private double water;
    private double coarseLeaf;
    private double other;
    private double netQuantity;
    private boolean selfTransport; // Yes/No button   new thing we added :)

    GreenLeaf(String date, String supplierCode, String categoryCode, int noOfSacks, double totalWeight, double sacksWeight, double water, double coarseLeaf, double other, boolean selfTransport) {
        this.date = date;
        this.supplierCode = supplierCode;
        this.categoryCode = categoryCode;
        this.noOfSacks = noOfSacks;
        this.totalWeight = totalWeight;
        this.sacksWeight = sacksWeight;
        this.water = water;
        this.coarseLeaf = coarseLeaf;
        this.other = other;
        this.selfTransport = selfTransport;
    }

    GreenLeaf() {
        date = null;
        supplierCode = null;
        categoryCode = null;
        noOfSacks = 0;
        totalWeight = 0;
        sacksWeight = 0;
        water = 0;
        coarseLeaf = 0;
        other = 0;
        selfTransport = false;

    }

    public double calcNetQuantity() {
        netQuantity = totalWeight - sacksWeight - water - coarseLeaf - other;
        return netQuantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSupplierCode(String supplierCode) {
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

    public void setSelfTransport(boolean selfTransport) {
        this.selfTransport = selfTransport;
    }

    public void setNetQuantity(double netQuantity) {   // This method may not be needed.
        this.netQuantity = netQuantity;
    }

    public String getDate() {
        return date;
    }

    public String getSupplierCode() {
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

    public boolean getSelfTransport() {
        return selfTransport;
    }

    public void addToDataBase() {

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
