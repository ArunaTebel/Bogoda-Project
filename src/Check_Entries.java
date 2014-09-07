
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
    
    public int string_to_int(String s){
        if(isInteger(s)){
            return Integer.parseInt(s);
        }
        else{
            return 0;
        }
    }
    
     public double string_to_double(String s){
        if(isDouble(s)){
            return Double.parseDouble(s);
        }
        else{
            return 0;
        }
    }
}
