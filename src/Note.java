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

    private String textFromIo;
    private String syntaxFromIo;
    public boolean hasSaved = false;


    public RSyntaxTextArea textArea;

    public Note(String tfi, String sfi, boolean save) {

        // remove internall frame border
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);

        hasSaved = save;
        textFromIo = tfi;
        syntaxFromIo = sfi;

        System.out.println("text "+ tfi + " syntax " + sfi);

        JPanel cp = new JPanel(new BorderLayout());
        this.setVisible(true);


        textArea = new RSyntaxTextArea(20, 300);
        textArea.add(new JButton("me"), SwingConstants.CENTER);

        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setViewportView(textArea);
        cp.add(sp);
        textArea.setVisible(true);
        setContentPane(cp);

        if (sfi.contains("JAVA")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        } else if (sfi.contains("PYTHON")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        } else if (sfi.contains("HTML")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        } else if (sfi.contains("CSS")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        } else if (sfi.contains("JAVASCRIPT")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        } else if (sfi.contains("LATEX")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
        } else if (sfi.contains("C")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        } else {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        }

        textArea.setText(tfi);

        textArea.setCodeFoldingEnabled(true);
        textArea.setAnimateBracketMatching(true);
        textArea.setCloseCurlyBraces(true);
        textArea.setCloseMarkupTags(true);
        textArea.setAutoIndentEnabled(true);
        textArea.setBackground(Uicolor.LIGHT_PRIMARY);
        textArea.setCurrentLineHighlightColor(Uicolor.DEFAULT_PRIMARY);
        //textArea.setFont(Uicolor.plain);
        textArea.setAntiAliasingEnabled(true);
        textArea.setRoundedSelectionEdges(false);
        textArea.setForeground(Uicolor.TEXT_PRIMARY);
        textArea.setBracketMatchingEnabled(true);
        textArea.setDragEnabled(true);

        pack();


    }

    public void setSyntax(String syntax) {
        if (syntax.contains("JAVA")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            System.out.print(syntax);

        } else if (syntax.contains("PYTHON")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
            System.out.print(syntax);
        } else if (syntax.contains("XML")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
            System.out.print(syntax);
        } else if (syntax.contains("LATEX")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            System.out.print(syntax);
        } else if (syntax.contains("CSS")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
            System.out.print(syntax);
        } else if (syntax.contains("HTML")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
            System.out.print(syntax);
        }
    }

    private static void setStyles() {
        // set styles

    }


    private boolean getSaved() {
        return this.hasSaved;
    }

    private void setSaved(Boolean saved) {
        this.hasSaved = saved;
    }


}



