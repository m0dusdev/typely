import javax.swing.*;

/**
 * Created by pc on 09/06/2017.
 */
public class CloseDialog {

    private String title;
    private String message;
    private boolean mode;

    CloseDialog(String msg, String ttl, boolean m) {
        message = msg;
        title = ttl;
        mode = m;

        if (m) {
            warning();
        } else if (!m) {
            show();
        }

    }

    CloseDialog(String msg, String ttl) {
        message = msg;
        title = ttl;
    }


    int show() {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return result;
    }

    int warning() {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        return result;
    }

}
