
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
public class Combo implements Runnable {

    javax.swing.JComboBox Combo;
    int count = 0;
    String temp = "";

    public Combo(javax.swing.JComboBox combo) {
        Combo = combo;
    }

    @Override
    public void run() {
        count = 0;

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {

                        if (GL_Loans.k == 5) {
                            char a = e.getKeyChar();
                            int b = e.getKeyCode();
                            int id = e.getID();
                            Object obj = new Object();
                            obj = Combo.getModel();
                            if (b != KeyEvent.VK_BACK_SPACE) {
                                if (id == KeyEvent.KEY_PRESSED && b != KeyEvent.VK_ENTER && b != 0 && b != 40 && b != 38) {
                                    temp = (String) Combo.getEditor().getItem() + a;
                                    count = temp.length();
                                    Combo.setModel(new javax.swing.DefaultComboBoxModel(getStringArrayfilter("rate_details", "Code_name", "" + temp, count)));
                                    Combo.getEditor().setItem(temp.substring(0, count - 1));
                                }
                            } else if (b == KeyEvent.VK_BACK_SPACE) {
                                temp = (String) Combo.getEditor().getItem();
                                int ad = temp.length();

                                Combo.setModel(new javax.swing.DefaultComboBoxModel(getStringArrayfilter("rate_details", "Code_name", "" + temp, ad)));
                                if (temp.length() > 0) {
                                    Combo.getEditor().setItem(temp);
                                } else {
                                    Combo.getEditor().setItem(null);
                                }
                            }
                        }
                        return false;
                    }
                });
    }

    public String[] getStringArrayfilter(String table_name, String column_name, String letter, int COUNT) {
        int count = 0;
        DatabaseManager dbm = DatabaseManager.getDbCon();
        try {
            ResultSet query = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query.next()) {
                count++;
            }
            String[] array = new String[count + 1];
            array[0] = null;
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                try {
                    if (query2.getObject(column_name).toString().length() >= COUNT) {
                        if (query2.getObject(column_name).toString().substring(0, COUNT).equalsIgnoreCase(letter)) {
                            array[count] = query2.getString(column_name);
                            count++;
                        } else if (query2.getObject(column_name).toString().equalsIgnoreCase(letter)) {
                            array[count] = query2.getString(column_name);
                            count++;
                        }
                    }
                } catch (SQLException e) {
                }
            }
            return array;
        } catch (SQLException ex) {
        }
        return null;
    }
}
