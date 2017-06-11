import javax.swing.*;
import java.awt.*;

/**
 *      DIALOG FOR ""
 */

public class About extends JDialog   {

    private JLabel jt;

    About() {
        setLayout(new BorderLayout());
        setTitle("About");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        jt= new JLabel();
        this.setDefaultCloseOperation(1); // Hide on close
        jt.setFont(new Font("Futura", Font.ITALIC, 25));
        jt.setBackground(Color.decode("#EEEEEE"));
        jt.setForeground(Color.decode("#212121"));
        jt.setText("          edit - v0.4");
        add(jt, SwingConstants.CENTER);
    }
}

