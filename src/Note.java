import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

/**
 * NotePad class - New instance created on each new tabbedpane Tab
 * @author William March - s4916313
 */

public class Note extends JEditorPane {

    private String ts;

    public Note(String text) {
        this.setBorder(new BorderUIResource.MatteBorderUIResource(5, 8, 5, 2, Uicolor.tabBack));
        this.setFont(Uicolor.plain);

        this.ts = text;
        this.setVisible(true);

        // set text from constructor
        this.setText(ts);
        this.setToolTipText("Enter notes here");

        // bind to RightClick menu
        this.addMouseListener(new RightClick());
    }
}

