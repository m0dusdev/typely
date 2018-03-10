import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *      DIALOG FOR ""
 */
 final class About extends JDialog  {

    private final JEditorPane jEditorPane;

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
        jEditorPane = new JEditorPane();
        jEditorPane.setContentType("text/html");
        jEditorPane.setEditable(false);

        this.setDefaultCloseOperation(1); // Hide on close
        jEditorPane.setFont(new Font("Futura", Font.ITALIC, 25));
        jEditorPane.setBackground(Color.decode("#EEEEEE"));
        jEditorPane.setForeground(Color.decode("#212121"));
        jEditorPane.setFont(Uicolor.plain);
        jEditorPane.setText("      typely - v0.6.2");
        add(jEditorPane, SwingConstants.CENTER);


    }
}


