import org.fife.ui.rsyntaxtextarea.CodeTemplateManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;
import org.fife.ui.rsyntaxtextarea.templates.StaticCodeTemplate;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static java.awt.event.KeyEvent.VK_0;

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
        textArea.setTemplatesEnabled(true);
        CodeTemplateManager ctm = textArea.getCodeTemplateManager();

        // add more
        CodeTemplate forLoop = new StaticCodeTemplate("fl", "for (int i=", "; i<; i++) {\n\t\n}\n");

        CodeTemplate psvm = new StaticCodeTemplate("psvm", "public static void main (String[] args){\n\t", "\n}");

        CodeTemplate aaa = new StaticCodeTemplate("aaa", "addActionListener((e)-> {\n\t", "\n" + "});\n");


        setContentPane(cp);

        ctm.addTemplate(forLoop);
        ctm.addTemplate(psvm);
        ctm.addTemplate(aaa);

        // apply syntax
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

        getStyle();


        setText(tfi);


    }

    private void setText(String text) {
        textArea.setText(text);
    }

    private String getText() {
        return textArea.getText();
    }



    private void getStyle() {
        String stylePath = "src/styles";

        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get(stylePath), Charset.defaultCharset());

            int i = 0;
            for (String temp : lines) {
                temp = lines.get(i);
                i++;
                System.out.print(temp + "\n");
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
                    // set code folding to disabled
                    textArea.setRoundedSelectionEdges(true);
                } else if (temp.contains("setRoundedSelectionEdges") && temp.contains("false")) {
                    // set code folding to disabled
                    textArea.setRoundedSelectionEdges(false);

                } else if (temp.contains("setBracketMatchingEnabled") && temp.contains("true")) {
                    // set code folding to disabled
                    textArea.setBracketMatchingEnabled(true);
                } else if (temp.contains("setBracketMatchingEnabled") && temp.contains("false")) {
                    // set code folding to disabled
                    textArea.setBracketMatchingEnabled(false);
                }


                /**
                 * SYNTAX PANEL BACKGROUND COLORS
                 */

                // Textarea background
                else if (temp.contains("setBackground")) {
                    if (temp.contains(("bold-grey"))) {
                        textArea.setBackground(Uicolor.BOLD_GREY);
                    } else if (temp.contains("grey")) {
                        textArea.setBackground(Uicolor.GREY);
                    } else if (temp.contains("light-grey")) {
                        textArea.setBackground(Uicolor.LIGHT_GREY);
                    } else if (temp.contains("dark-grey")) {
                        textArea.setBackground(Uicolor.DARK_GREY);
                    } else if (temp.contains("bold-blue")) {
                        textArea.setBackground(Uicolor.BOLD_BLUE);
                    } else if (temp.contains("blue")) {
                        textArea.setBackground(Uicolor.BLUE);
                    } else if (temp.contains("light-blue")) {
                        textArea.setBackground(Uicolor.LIGHT_BLUE);
                    } else if (temp.contains("dark-blue")) {
                        textArea.setBackground(Uicolor.DARK_BLUE);
                    } else if (temp.contains("bold-teal")) {
                        textArea.setBackground(Uicolor.BOLD_TEAL);
                    } else if (temp.contains("light-teal")) {
                        textArea.setBackground(Uicolor.LIGHT_TEAL);
                    } else if (temp.contains("teal")) {
                        textArea.setBackground(Uicolor.TEAL);
                    } else if (temp.contains("dark-green")) {
                        textArea.setBackground(Uicolor.DARK_GREEN);
                    } else if (temp.contains("green")) {
                        textArea.setBackground(Uicolor.GREEN);
                    } else if (temp.contains("light-green")) {
                        textArea.setBackground(Uicolor.LIGHT_GREEN);
                    } else if (temp.contains("dark-green")) {
                        textArea.setBackground(Uicolor.DARK_GREEN);
                    } else this.textArea.setForeground(Uicolor.LIGHT_GREY);

                    // TextArea Foreground
                } else if (temp.contains("setForeground")) {
                    if (temp.contains("bold-green")) {
                        textArea.setForeground(Uicolor.GREEN);
                    } else if (temp.contains("grey")) {
                        textArea.setForeground(Uicolor.GREY);
                    } else if (temp.contains("light-grey")) {
                        textArea.setForeground(Uicolor.LIGHT_GREY);
                    } else if (temp.contains("dark-grey")) {
                        textArea.setForeground(Uicolor.DARK_GREY);
                    } else if (temp.contains("bold-blue")) {
                        textArea.setForeground(Uicolor.BOLD_BLUE);
                    } else if (temp.contains("blue")) {
                        textArea.setForeground(Uicolor.BLUE);
                    } else if (temp.contains("light-blue")) {
                        textArea.setForeground(Uicolor.LIGHT_BLUE);
                    } else if (temp.contains("dark-blue")) {
                        textArea.setForeground(Uicolor.DARK_BLUE);
                    } else if (temp.contains("bold-teal")) {
                        textArea.setForeground(Uicolor.BOLD_TEAL);
                    } else if (temp.contains("light-teal")) {
                        textArea.setForeground(Uicolor.LIGHT_TEAL);
                    } else if (temp.contains("teal")) {
                        textArea.setForeground(Uicolor.TEAL);
                    } else if (temp.contains("dark-green")) {
                        textArea.setForeground(Uicolor.DARK_GREEN);
                    } else if (temp.contains("green")) {
                        textArea.setForeground(Uicolor.GREEN);
                    } else if (temp.contains("light-green")) {
                        textArea.setForeground(Uicolor.LIGHT_GREEN);
                    } else if (temp.contains("dark-green")) {
                        textArea.setForeground(Uicolor.DARK_GREEN);
                    } else textArea.setForeground(Uicolor.GREY);

                } else if (temp.contains("setHighlighter")) {

                    if (temp.contains("bold-green")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.GREEN);
                    } else if (temp.contains("grey")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.GREY);
                    } else if (temp.contains("light-grey")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.LIGHT_GREY);
                    } else if (temp.contains("dark-grey")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.DARK_GREY);
                    } else if (temp.contains("bold-blue")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.BOLD_BLUE);
                    } else if (temp.contains("blue")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.BLUE);
                    } else if (temp.contains("light-blue")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.LIGHT_BLUE);
                    } else if (temp.contains("dark-blue")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.DARK_BLUE);
                    } else if (temp.contains("bold-teal")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.BOLD_TEAL);
                    } else if (temp.contains("light-teal")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.LIGHT_TEAL);
                    } else if (temp.contains("teal")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.TEAL);
                    } else if (temp.contains("dark-green")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.DARK_GREEN);
                    } else if (temp.contains("green")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.GREEN);
                    } else if (temp.contains("light-green")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.LIGHT_GREEN);
                    } else if (temp.contains("dark-green")) {
                        textArea.setCurrentLineHighlightColor(Uicolor.DARK_GREEN);
                    } else textArea.setCurrentLineHighlightColor(Uicolor.GREY);
                }
            }


        } catch (IOException fileFucked) {
            fileFucked.printStackTrace();
            CloseDialog cl = new CloseDialog("Preference file is missing", "File ");

        }

    }

}



