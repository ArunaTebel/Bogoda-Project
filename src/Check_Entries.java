
import java.text.DecimalFormat;

public class Check_Entries extends javax.swing.JTextField {

    public void setText(String s) {
        super.setText(s);
        double num = 0;
        if (s.contains(".")) {
            if (isDouble(s.split(".")[0])) {
                num = Double.parseDouble(s);
                super.setText(set_comma(num));
            } else {
                super.setText(s);
            }
        } else {
            super.setText(s);
        }
    }

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

    public int string_to_int(String s) {
        if (isInteger(s)) {
            return Integer.parseInt(s);
        } else {
            return 0;
        }
    }

    public double string_to_double(String s) {
        if (isDouble(s)) {
            return Double.parseDouble(s);
        } else {
            return 0;
        }
    }

    public String set_comma(double num) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        String output = myFormatter.format(num);
        return output;
    }

    public int verify_combo_box(javax.swing.JComboBox combo, String databaseTable, String tableColumn) {

        DatabaseManager dbm = DatabaseManager.getDbCon();
        MessageBox msg = new MessageBox();
        if (combo.getSelectedItem() == null) {
            msg.showMessage("Invalid Account Code", "Receipt", "info");
            combo.requestFocus();
            return 0;
        } else {
            String item = combo.getSelectedItem().toString();
            if (dbm.checkWhetherDataExists(databaseTable, tableColumn, item) == 0) {
                msg.showMessage("Invalid Account Code", "Receipt", "info");
                combo.setSelectedItem(null);
                combo.requestFocus();
                return 0;
            } else {
                return 1;
            }
        }
    }
}
