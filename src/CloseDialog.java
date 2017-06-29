import javax.swing.*;
import java.awt.*;

/**
 * Created by pc on 09/06/2017.
 */
public class CloseDialog {

    private String title;
    private String message;

    CloseDialog(String msg, String ttl) {
        message = msg;
        title = ttl;
        show();

    }

    void close() {
        this.close();
    }

    int show() {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return result;
    }

}
