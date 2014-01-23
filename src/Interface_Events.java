
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pramo
 */
public class Interface_Events {

    public Interface_Events() {

    }

    public void Change_focus_Enterkey_t_t(javax.swing.JTextField text1, javax.swing.JTextField text2, java.awt.event.KeyEvent evt) {

        String text = new String();
        text = text1.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            text2.requestFocusInWindow();

        }

    }

    public void Change_focus_Enterkey_t_b(javax.swing.JTextField text1, javax.swing.JButton button, java.awt.event.KeyEvent evt) {

        String text = new String();
        text = text1.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            button.requestFocusInWindow();

        }

    }

    public void Respond_enter(javax.swing.JButton button, java.awt.event.FocusEvent evt) {
        button.registerKeyboardAction(button.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);

        button.registerKeyboardAction(button.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
    }

    public void Change_focus_Up_b_b(javax.swing.JButton button2, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            button2.requestFocusInWindow();

        }
    }

    public void Change_focus_down_b_b(javax.swing.JButton button2, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            button2.requestFocusInWindow();

        }

    }

    public void Change_focus_right_b_b(javax.swing.JButton button2, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            button2.requestFocusInWindow();

        }

    }

    public void Change_focus_left_b_b(javax.swing.JButton button2, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            button2.requestFocusInWindow();

        }
        
        
        
    }
        
        
        
    public void Change_focus_Up_c(javax.swing.JComboBox combo, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            combo.requestFocusInWindow();

        }
    }

    public void Change_focus_down_c(javax.swing.JComboBox combo, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            combo.requestFocusInWindow();

        }

    }

    public void Change_focus_right_c(javax.swing.JComboBox combo, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            combo.requestFocusInWindow();

        }

    }

    public void Change_focus_left_c(javax.swing.JComboBox combo, java.awt.event.KeyEvent evt) {

        int key;
        key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            combo.requestFocusInWindow();

        }

    }
    
    
    
    
    
    
    
}
