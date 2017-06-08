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


    public RSyntaxTextArea textArea;

    public Note(String tfi, String sfi) {

        // remove internall frame border
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);


        this.textFromIo = tfi;
        this.syntaxFromIo = sfi;

        System.out.println("text "+ tfi + " syntax " + sfi);

        JPanel cp = new JPanel(new BorderLayout());
        this.setVisible(true);



        textArea = new RSyntaxTextArea(20, 60);

        textArea.setCodeFoldingEnabled(true);
        textArea.setAnimateBracketMatching(true);
        textArea.setCloseCurlyBraces(true);
        textArea.setCloseMarkupTags(true);
        textArea.setAutoIndentEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setViewportView(textArea);
        cp.add(sp);
        textArea.setVisible(true);
        setContentPane(cp);

        pack();

        create();



    }

    public void create(){
        // set text area to contents of file from Io
        textArea.setText(this.textFromIo);
        System.out.print("null\n\n\n" +textArea.getText());
        //textArea.setSyntaxEditingStyle(syntaxFromIo);




}}



