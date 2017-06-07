import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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


    static JInternalFrame c;
    static int tabCount = 1;
    static int current;
    static JTabbedPane tabbedPane = new JTabbedPane();

    public static java.util.List<RSyntaxTextArea> frameList = new ArrayList<>();



    public MainScreen() {

        super("NotePad");

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

                // if no tabs are left then reset tab count
                if(current == -1){
                    current = 0;
                }
            });
        setContentPane(tabbedPane);
        pack();
    }

    /**
     * Creates a new instance of the Note class - updates tab count set current editorpane
     * @param title set title for new tab
     */

    static void handle (String title, String syntax, String send, int flag) {

        switch (flag) {

            // new
            case 1: // New Tab operation
                Note n = new Note(send, syntax);
                tabbedPane.addTab(title, n);
                tabCount++;

                c = (JInternalFrame) tabbedPane.getComponentAt(current);

                RSyntaxTextArea rsyntaxRef;

                rsyntaxRef = (RSyntaxTextArea)c.getMostRecentFocusOwner();

                rsyntaxRef.setText("hello");











            case 2:
                break;


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
           handle("C:\\Users\\Public\\Documents\\Shared Virtual Machines" + tabCount, "SYNTAX_STYLE_PYTHON", null, 1);
       });

    }
}

