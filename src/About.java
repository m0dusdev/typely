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
        jEditorPane.setFont(new Font("Futura", Font.ITALIC, 18));
        jEditorPane.setBackground(Color.decode("#EEEEEE"));
        jEditorPane.setForeground(Color.decode("#212121"));
        jEditorPane.setFont(Uicolor.plain);
        jEditorPane.setText("      typely - v0.6.2\n <a href=\\\"http://www.typely.net/About:C\\\"> " +
                "   typely.net</a>");
        add(jEditorPane, SwingConstants.CENTER);

        // hyper link listener for website
        jEditorPane.addHyperlinkListener((e)-> {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if(Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        }catch (URISyntaxException URIex) {
                           URIex.getCause();
                        } catch (IOException aboutIo){
                            aboutIo.getCause();
                        }

                    }
                }

        });
    }
}


