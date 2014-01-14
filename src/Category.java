
public class Category {

    private String categoryName;
    private String categoryCode;
    private double extraRate;

    Category(String categoryName, String categoryCode, double extraRate) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.extraRate = extraRate;
    }

    Category() {
        categoryName = null;
        categoryCode = null;
        extraRate = 0;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setExtraRate(double extraRate) {
        this.extraRate = extraRate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public double getExtraRate() {
        return extraRate;
    }

    public void addToDataBase() {

    }

    public void removeFromDataBase() {

    }

    public void editDataBase() {

    }
}
