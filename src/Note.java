import org.fife.ui.rsyntaxtextarea.CodeTemplateManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;
import org.fife.ui.rsyntaxtextarea.templates.StaticCodeTemplate;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * NotePad class - New instance created on each new tabbedpane Tab
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


    private RSyntaxTextArea textArea;

    public Note(String tfi, String sfi) {

        textFromIo = tfi;
        syntaxFromIo = sfi;


        // remove internal frame border
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).
                setNorthPane(null);
        this.setBorder(null);

        JPanel cp = new JPanel(new BorderLayout());
        this.setVisible(true);
        textArea = new RSyntaxTextArea(20, 150);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setViewportView(textArea);
        cp.add(sp);
        textArea.setVisible(true);
        textArea.setTemplatesEnabled(true);
        textArea.setHyperlinksEnabled(true);
        CodeTemplateManager ctm = textArea.getCodeTemplateManager();

        textArea.setTabLineColor(Color.WHITE);

        // close the current tab
        closeTabitem = new JMenuItem("Close tab");
        closeTabitem.addActionListener((e)-> MainScreen.tabbedPane.remove(MainScreen.current));
        JPopupMenu popup = textArea.getPopupMenu();
        popup.addSeparator();
        popup.add(closeTabitem);

        // clear the current tabs contents
        clearTabitem = new JMenuItem("Clear");
        clearTabitem.addActionListener((e)-> MainScreen.currentR.setText(""));
        popup.add(clearTabitem);

        //open the save dialog for the current tab
        saveTabitem = new JMenuItem("Save");
        saveTabitem.addActionListener((e)-> Io.saveAs());
        popup.add(saveTabitem);


        // add more
        CodeTemplate forLoop = new StaticCodeTemplate("fl",
                "for (int i=", "; i<; i++) {\n\t\n}\n");

        CodeTemplate psvm = new StaticCodeTemplate("psvm",
                "public static void main (String[] args){\n\t", "\n}");

        CodeTemplate aaa = new StaticCodeTemplate("aal",
                "addActionListener((e)-> {\n\t", "\n" + "});\n");



        setContentPane(cp);

        ctm.addTemplate(forLoop);
        ctm.addTemplate(psvm);
        ctm.addTemplate(aaa);

        // apply syntax automatically when opening a new file
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


        pack();

        getPrefs();

        setText(tfi);
    }

    // take key from preferences file and apply apropriate styling
    private void getXMLStyle(String type) throws IOException{
        if (type == "dark") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(textArea);
        } else if (type == "default") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/default.xml"));
            theme.apply(textArea);
        } else if (type == "default-alt") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/default-alt.xml"));
            theme.apply(textArea);
        } else if (type == "eclipse") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/eclipse.xml"));
            theme.apply(textArea);
        } else if (type == "idea") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/idea.xml"));
            theme.apply(textArea);
        }else if (type == "monokai") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(textArea);
        }else if (type == "vs") {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/vs.xml"));
            theme.apply(textArea);
        }




    }

    // set text from the constructor
    private void setText(String text) {
        textArea.setText(text);
    }

    // unused
    private String getText() {
        return textArea.getText();
    }

    // read and apply style from prefs file
    private void getPrefs() {

        try {
            // new string list from $stylePath
            java.util.List<String> lines = Files.readAllLines(Paths.get(MainScreen.prefPath),
                    Charset.defaultCharset());

            int i = 0;
            for (String temp : lines) {
                // search each line in file for a config match
                // - this way line order of preferences file does not impact on outcome
                temp = lines.get(i);
                i++;
                System.out.print(temp + "\n");

                /**
                 * EDITOR PREFERENCES
                 */
                if (temp.contains("setCodeFoldingEnabled") && temp.contains("true")) {
                    // setCodeFoldingEnabled true
                    textArea.setCodeFoldingEnabled(true);

                } else if (temp.contains("setCodeFoldingEnabled") && temp.contains("false")) {
                    // setCodeFoldingEnabled false
                    textArea.setCodeFoldingEnabled(false);

                } else if (temp.contains("setAnimateBracketMatching") && temp.contains("true")) {
                    // setAnimateBracketMatching true
                    textArea.setAnimateBracketMatching(true);

                } else if (temp.contains("setAnimateBracketMatching") && temp.contains("false")) {
                    // setAnimateBracketMatching false
                    textArea.setAnimateBracketMatching(false);
                } else if (temp.contains("setCloseCurlyBraces") && temp.contains("true")) {
                    // setCloseCurlyBraces true
                    textArea.setCloseCurlyBraces(true);
                } else if (temp.contains("setCloseCurlyBraces") && temp.contains("false")) {
                    // setCloseCurlyBraces false
                    textArea.setCloseCurlyBraces(false);
                } else if (temp.contains("setAutoIndentEnabled") && temp.contains("true")) {
                    // setAutoIndentEnabled true
                    textArea.setAutoIndentEnabled(true);
                } else if (temp.contains("setAutoIndentEnabled") && temp.contains("false")) {
                    // setAutoIndentEnabled false
                    textArea.setAutoIndentEnabled(false);
                } else if (temp.contains("setAntiAliasingEnabled") && temp.contains("true")) {
                    // setAntiAliasingEnabled true
                    textArea.setAntiAliasingEnabled(true);
                } else if (temp.contains("setAntiAliasingEnabled") && temp.contains("false")) {
                    // setAntiAliasingEnabled false
                    textArea.setAntiAliasingEnabled(false);
                } else if (temp.contains("setDragEnabled") && temp.contains("true")) {
                    // setDragEnabled true
                    textArea.setDragEnabled(true);
                } else if (temp.contains("setDragEnabled") && temp.contains("false")) {
                    // setDragEnabled false
                    textArea.setDragEnabled(false);

                } else if (temp.contains("setRoundedSelectionEdges") && temp.contains("true")) {
                    // set rounded selection edges to enabled
                    textArea.setRoundedSelectionEdges(true);
                } else if (temp.contains("setRoundedSelectionEdges") && temp.contains("false")) {
                    // set rounded selection edges to disabled
                    textArea.setRoundedSelectionEdges(false);

                } else if (temp.contains("setBracketMatchingEnabled") && temp.contains("true")) {
                    // set bracket matching to enabled
                    textArea.setBracketMatchingEnabled(true);
                } else if (temp.contains("setBracketMatchingEnabled") && temp.contains("false")) {
                    // set bracket matching to disabled
                    textArea.setBracketMatchingEnabled(false);
                } else if (temp.contains("setAnimateBracketMatching") && temp.contains("true")) {
                    // set animated bracket matching to enabled
                    textArea.setAnimateBracketMatching(true);
                } else if (temp.contains("setAnimateBracketMatchingEnabled") && temp.contains("false")) {
                    // set animated bracket matching to disabled
                    textArea.setAnimateBracketMatching(false);
                } else if (temp.contains("setCloseMarkupTags") && temp.contains("true")) {
                    // set close markup tags to enabled
                    textArea.setCloseMarkupTags(true);
                } else if (temp.contains("setCloseMarkupTags") && temp.contains("false")) {
                    // set close markup tags to disabled
                    textArea.setCloseMarkupTags(false);
                } else if (temp.contains("setEOLMarkersVisible") && temp.contains("true")) {
                    // set EOL markers visible to enabled
                    textArea.setEOLMarkersVisible(true);
                } else if (temp.contains("setEOLMarkersVisible") && temp.contains("false")) {
                    // set EOL Markers visible to disabled
                    textArea.setEOLMarkersVisible(false);
                } else if (temp.contains("setHighlightSecondaryLanguages") && temp.contains("true")) {
                    // set set highlight secondary languages
                    textArea.setHighlightSecondaryLanguages(true);
                } else if (temp.contains("setHighlightSecondaryLanguages") && temp.contains("false")) {
                    // set bracket matching to disabled
                    textArea.setHighlightSecondaryLanguages(false);
                } else if (temp.contains("setMarkOccurrences") && temp.contains("true")) {
                    // set mark occurrences to enabled
                    textArea.setMarkOccurrences(true);
                } else if (temp.contains("setMarkOccurences") && temp.contains("false")) {
                    // set bracket matching to disabled
                    textArea.setBracketMatchingEnabled(false);

                    // get user theme choice
                } else if (temp.contains("Theme")) {
                    if (temp.contains("dark")){
                        getXMLStyle("dark");

                    } else if (temp.contains("default")){
                        getXMLStyle("default");
                    } else if (temp.contains("default-alt")){
                        getXMLStyle("default-alt");
                    } else if (temp.contains("eclipse")){
                        getXMLStyle("eclipse");
                    } else if (temp.contains("idea")){
                        getXMLStyle("idea");
                    } else if (temp.contains("monokai")){
                        getXMLStyle("monokai");
                    } else if (temp.contains("vs")){
                        getXMLStyle("vs");
                    }




                }


            }


        } catch (IOException fileFucked) {
            fileFucked.printStackTrace();

        }

    }

}



