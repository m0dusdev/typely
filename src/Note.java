import org.fife.ui.rsyntaxtextarea.CodeTemplateManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;
import org.fife.ui.rsyntaxtextarea.templates.StaticCodeTemplate;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Class extending JInternalframe, instances of this class are added to the JTabbedPane.
 * This class contains an instance of RSSyntaxarea witch is the editor interface the
 * user interacts with.
 *
 * @author William March -
 */

public class Note extends JInternalFrame {

    // additional item for usefull functions
    private JMenuItem closeTabitem;
    private JMenuItem clearTabitem;
    private JMenuItem saveTabitem;



    private String path;
    private String textFromIo;
    private String syntaxFromIo;
    public boolean hasSaved = false;


    // the editor the user types in ...
    private JEditorPane textArea;

    public Note(String tfi, String sfi) {

        textFromIo = tfi;
        syntaxFromIo = sfi;




        // remove internal frame border
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).
                setNorthPane(null);
        this.setBorder(null);


        // internal JPanel holding RSSyntaxarea instance
        JPanel cp = new JPanel(new BorderLayout());

        this.setVisible(true);
        textArea = new JEditorPane();

        Document doc = textArea.getDocument();
        if (doc instanceof PlainDocument) {
            doc.putProperty(PlainDocument.tabSizeAttribute, 4);
        }


        cp.add(new JScrollPane(textArea));


        textArea.setVisible(true);








        setContentPane(cp);

        textArea.setText(tfi);

        pack();
    }


}



