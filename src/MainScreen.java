import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.util.*;
import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * Class that creates the form and tabbed pane
 * @author William March - s4916313
 * @version 1.0
 */

public class MainScreen extends JFrame  {



    static int tabCount = 1;
    static int current;
    static JTabbedPane tabbedPane = new JTabbedPane();
    public static RSyntaxTextArea currentR = new RSyntaxTextArea();

    public static java.util.List<RSyntaxTextArea> frameList = new ArrayList<>();



    public MainScreen() {

        super("ed-it");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowOpened(e);
                CloseDialog cl = new CloseDialog();
                int tempResult = cl.show();

                if (tempResult == 1) {
                    // yes option
                } else if (tempResult == 2) {
                    dispose(); // no option
                } else if (tempResult == 3) {

                }
            }
        });

        //tabbedPane setup

        tabbedPane.setBackground(Uicolor.ACCENT_COLOR);
        tabbedPane.setForeground(Uicolor.LIGHT_PRIMARY);
        tabbedPane.setFont(Uicolor.plain);
        tabbedPane.setFocusable(false);
        tabbedPane.setVisible(true);

        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = Uicolor.ACCENT_COLOR;
                lightHighlight = Uicolor.LIGHT_PRIMARY;
                focus = Uicolor.DEFAULT_PRIMARY;
            }
        });

        // set menu bar
        setJMenuBar(new Menu());



        add(tabbedPane, BorderLayout.CENTER);

        // gets currently selected tab as index
        tabbedPane.addChangeListener((e)-> {

                JTabbedPane pane = (JTabbedPane) e.getSource();

                current = pane.getSelectedIndex();
            currentR = getRarea();

            if (current == -1) current = current + 1;

            });
        setContentPane(tabbedPane);
        pack();
    }


    /**
     * Creates a new instance of the Note class - updates tab count set current editorpane
     * @param title set title for new tab
     */

    static void handle (String title, String syntax, String send) {
        //Note n = new Note(send, syntax);
        tabbedPane.addTab(title, new Note(send, syntax, false));
        System.out.println("text from file -    " + send + "   Syntax is -   " + syntax + "   Title is -  " + title);
                tabCount++;

    }

    // return reference to the current syntax pane in focus
    static RSyntaxTextArea getRarea() {
        JInternalFrame je;
        je = (JInternalFrame) MainScreen.tabbedPane.getComponentAt(MainScreen.current);
        RSyntaxTextArea rsc;
        rsc = (RSyntaxTextArea) je.getMostRecentFocusOwner();
        return rsc;
    }

    public static void setSyntax(String syntax) {
        RSyntaxTextArea tempset = getRarea();

        if (syntax.contains("JAVA")) {
            tempset.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        }
    }



    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           // set appropriate look and feel
           try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (ClassNotFoundException ex) {
           } catch (InstantiationException ex) {
           } catch (IllegalAccessException ex) {
           } catch (UnsupportedLookAndFeelException ex) {
           }
           JFrame frame = new MainScreen();
           frame.setSize(1280, 800);
           frame.setDefaultCloseOperation(3);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);

           // kick off with a new frame
           handle("temp.txt " + tabCount, "PLAIN", null);
       });

    }
}

