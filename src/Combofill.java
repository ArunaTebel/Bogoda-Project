

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramo
 */


public  class Combofill implements FocusListener {
     public static int k;   
     
        javax.swing.JComboBox com;
        String tab= null;
        String col= null;
        
        public Combofill(javax.swing.JComboBox jcombo,String table, String Coloum){
        com = jcombo;
        tab= table;
        col = Coloum;
      
        
        
        }
 private  Thread t;
        @Override
        public void focusGained(FocusEvent e) {
            k = 5;
            t = new Thread(new Comboa(com,tab,col));
            t.start();
          
            
            
        }

        @Override
        public void focusLost(FocusEvent e) {
            k = 0;
           
            t.stop();
             if(t.isAlive()){
                 
             }
        }
        
        
        public class Comboa implements Runnable {
  int k= 0;
    javax.swing.JComboBox Combo;
    int count = 0;
    String temp = "";
    String table_name= null;
    String col = null;

    public Comboa(javax.swing.JComboBox combo, String table, String coloum) {
        Combo = combo;
        table_name = table;
        col = coloum;
         DatabaseManager dbm = DatabaseManager.getDbCon();
        
    }

    @Override
    public void run() {
        count = 0;

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                    
                       if (Combofill.k == 5) {
                          
                            char a = e.getKeyChar();
                            int b = e.getKeyCode();
                            int id = e.getID();
                           
                            if (b != KeyEvent.VK_BACK_SPACE) {
                                if (id == KeyEvent.KEY_PRESSED  && b!= KeyEvent.VK_ENTER&& b != 0 && b != 40 && b != 38) {
                                     
                                    temp = (String) Combo.getEditor().getItem() + a;
                                    count = temp.length();
                                    
                                    Combo.setModel(new javax.swing.DefaultComboBoxModel(getStringArrayfilter(table_name, col, "" + temp, count)));
                                      try{Combo.showPopup();} catch(Exception en){}
                                    Combo.getEditor().setItem(temp.substring(0, count - 1));
                                }
                                if (id == KeyEvent.KEY_RELEASED){
                                if(b== KeyEvent.VK_ENTER){
                                
                              
                                  String pa=  Combo.getModel().getElementAt(1).toString();
                                    System.out.println(pa);
                                  try{
                                Combo.setSelectedIndex(1);
                                  } catch(Exception ed){}
                                
                                
                                }
                                
                                }
                            }  if (b == KeyEvent.VK_BACK_SPACE && id == KeyEvent.KEY_PRESSED ) {
                                
                               
                                temp = (String) Combo.getEditor().getItem();
                                int ad = temp.length();

                                Combo.setModel(new javax.swing.DefaultComboBoxModel(getStringArrayfilter(table_name, col, "" + temp, ad)));
                                if (temp.length() > 0) {
                                    Combo.getEditor().setItem(temp);
                                     try{Combo.showPopup();} catch(Exception en){}
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
            String[] array = new String[count + 2];
            array[0] = null;
            count = 0;
            ResultSet query2 = dbm.query("SELECT " + column_name + " FROM " + table_name + "");
            while (query2.next()) {
                try {
                    if (query2.getObject(column_name).toString().length() >= COUNT) {
                        if (query2.getObject(column_name).toString().substring(0, COUNT).equalsIgnoreCase(letter)) {
                            array[count+1] = query2.getString(column_name);
                            count++;
                        } else if (query2.getObject(column_name).toString().equalsIgnoreCase(letter)) {
                            array[count+1] = query2.getString(column_name);
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
    }