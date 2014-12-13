
import java.text.DecimalFormat;

public class ACC_Number_Formats {

    public String getNumberWithoutCommas(String num) {
        num = num.replace(",", "");
        return num;
    }

    public String set_comma(String num) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        String output = null;
        if (num.contains(".")) {
            String[] arr = num.split("\\.");
            if (arr.length > 1) {
                if (arr[1].length() > 2) {
                    output = myFormatter.format(Double.parseDouble(arr[0])) + "." + arr[1].substring(0, 2);
                } else {
                    output = myFormatter.format(Double.parseDouble(arr[0])) + "." + arr[1];
                }
            } else {
                output = myFormatter.format(Double.parseDouble(num)) + ".";
            }
        } else {
            output = myFormatter.format(Double.parseDouble(num));
        }
        return output;
    }

    public String add_dot_for_a_number_with_commas(String num) {

        String numWithoutCommas = getNumberWithoutCommas(num);
        String numWithdot = numWithoutCommas + ".00";
        return set_comma(numWithdot);
    }

    public String add_dot_for_a_number_with_commas_and_one_decimalPlace(String num) {
        String numWithoutCommas = getNumberWithoutCommas(num);
        String numWithdot = numWithoutCommas + "0";
        return set_comma(numWithdot);
    }

    public String modify_number(String num) {
        if (!num.contains(".")) {
            return add_dot_for_a_number_with_commas(num);
        } else {
            String[] arr = num.split("\\.");
            if (arr.length > 1) {
                if (arr[1].length() > 1) {
                    return num;
                } else {
                    return add_dot_for_a_number_with_commas_and_one_decimalPlace(num);
                }
            }
            else{
                return add_dot_for_a_number_with_commas(arr[0]);
            }
        }
    }

    public String remove_last_char(String num) {
        num = num.replace(num.substring(num.length() - 1), "");
        return num;
    }

}
