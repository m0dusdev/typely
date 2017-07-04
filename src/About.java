import javax.swing.*;
import java.awt.*;

/**
 *      DIALOG FOR ""
 */

public class About extends JDialog   {

    private JLabel jt;

    About() {


        //ADD AN ICON

        //ImageIcon img = new ImageIcon(MainScreen.class.getResource("icon.jpg"));
        //this.setIconImage(img.getImage());


        setLayout(new BorderLayout());
        setTitle("About");
        setSize(170, 90);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        jt = new JLabel();

        this.setDefaultCloseOperation(1); // Hide on close
        jt.setFont(new Font("Futura", Font.ITALIC, 18));
        jt.setBackground(Color.decode("#EEEEEE"));
        jt.setForeground(Color.decode("#212121"));
        jt.setText("          edit - v0.5.5");
        add(jt, SwingConstants.CENTER);
    }
}


