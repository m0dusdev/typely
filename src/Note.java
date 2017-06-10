import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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


        pack();

        getStyle();


    }

    private void getStyle() {
        String stylePath = "src/styles";

        try {
            Map<String, String> map = new HashMap<>();
            java.util.List<String> lines = Files.readAllLines(Paths.get(stylePath), Charset.defaultCharset());
            StringBuilder styleBuffer = new StringBuilder();
            System.out.print(lines.get(0) + "\n");

            for (String temp : lines) {
                styleBuffer.append(temp + "\n");
            }


            System.err.print(styleBuffer.toString());

            String fromStyle = styleBuffer.toString();


            MainScreen.tabbedPane.setBackground(Uicolor.DARK_GREY);
            MainScreen.tabbedPane.setForeground(Uicolor.LIGHT_GREY);
            MainScreen.tabbedPane.setFont(Uicolor.plain);
            MainScreen.tabbedPane.setFocusable(false);
            MainScreen.tabbedPane.setVisible(true);


            MainScreen.tabbedPane.setUI(new BasicTabbedPaneUI() {
                @Override
                protected void installDefaults() {
                    super.installDefaults();
                    highlight = Uicolor.BOLD_GREY;
                    lightHighlight = Uicolor.LIGHT_GREY;
                    focus = Uicolor.GREY;
                }
            });

        } catch (IOException fileFucked) {
            fileFucked.printStackTrace();
        }

    }

}



