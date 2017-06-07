import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * NotePad class - New instance created on each new tabbedpane Tab
 * @author William March - s4916313
 */

public class Note extends JInternalFrame {

    private String textFromIo = "";
    private String syntaxFromIo = "SYNTAX_STLYE_PLANE";


    public RSyntaxTextArea textArea;

    public Note(String tfi, String sfi) {

        textArea = new RSyntaxTextArea(20,60);
        this.textFromIo = tfi;
        this.syntaxFromIo = sfi;

        System.out.println("text "+ tfi + " syntax " + sfi);

        JPanel cp = new JPanel(new BorderLayout());
        this.setVisible(true);

        // remove internall frame border
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);

        textArea = new RSyntaxTextArea(20, 60);

        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);
        textArea.setVisible(true);
        setContentPane(cp);

        pack();

        create();


    }

    public void create(){
        // set text area to contents of file from Io
        textArea.setText(this.textFromIo);

        if (syntaxFromIo.contains("JAVA")){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        } else if( syntaxFromIo.contains("PYTHON")){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);

        } else if( syntaxFromIo.contains("HTML")){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);

        } else if( syntaxFromIo.contains("CSS")){
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);

        }
    }

    private void getText(){

        String text = textArea.getText();
    }


}



