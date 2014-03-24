
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MessageBox {

   

    public static int showMessage(String message, String title, String type) {
        int showConfirmDialog = 0;
        switch (type) {
            case "error":
                JOptionPane.showMessageDialog(null, message, title, ERROR_MESSAGE);
                break;
            case "info":
                JOptionPane.showMessageDialog(null, message, title, INFORMATION_MESSAGE);
                break;
            case "warning":
                JOptionPane.showMessageDialog(null, message, title, WARNING_MESSAGE);
                break;
            case "question":
                JOptionPane.showMessageDialog(null, message, title, QUESTION_MESSAGE);
                break;
            case "plain":
                JOptionPane.showMessageDialog(null, message, title, PLAIN_MESSAGE);
                break;
            case "yes_no":
                showConfirmDialog = JOptionPane.showConfirmDialog(null, message, title, YES_NO_OPTION);
                break;
            case "yes_no_cancel":
                showConfirmDialog = JOptionPane.showConfirmDialog(null, message, title, YES_NO_CANCEL_OPTION);
                break;
        }
        return showConfirmDialog;
    }
}
