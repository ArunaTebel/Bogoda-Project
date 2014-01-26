
public class Check_Entries {

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
