import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jetbrains.annotations.Contract;

/**
 * Class that creates the form and tabbed pane
 * @author William March - s4916313
 * @version 1.0
 */

public class MainScreen extends JFrame  {

    private static HashMap<String, JInternalFrame> editorMap = new HashMap<>();

    private static final long serialVersionUID = 1L;

    static boolean justAddedTab = false;
    static int tabCount = 0;
    static int current;
    static JTabbedPane tabbedPane;
    public static RSyntaxTextArea currentR = new RSyntaxTextArea();

    public MainScreen() {

        super("ed-it");

        // map


        // listen for close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowOpened(e);
                CloseDialog cl = new CloseDialog("test", "test");
                int tempResult = cl.show();

                if (tempResult == 1) {
                    // yes option
                }
                dispose();
            }
        });

        //tabbedPane setup




        // set menu bar
        setJMenuBar(new Menu());


        // add
        add(createTab(), BorderLayout.CENTER);

        // gets currently selected tab as index
        tabbedPane.addChangeListener((e)-> {

                JTabbedPane pane = (JTabbedPane) e.getSource();
                current = pane.getSelectedIndex();

            JInternalFrame je;

            je = (JInternalFrame) tabbedPane.getComponentAt(current);

            RSyntaxTextArea rsc;

            rsc = (RSyntaxTextArea) je.getMostRecentFocusOwner();

            currentR = rsc;
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() != 'a' && e.getKeyCode() == 65) {
                    tabbedPane.setSelectedIndex(0);
                    System.out.print("\nalt and 1\n");

                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });


        setContentPane(tabbedPane);
        pack();
    }

    // get map of all editor areas k = tab title, v = RSyntaxarea
    @Contract(pure = true)
    public static HashMap getEditorMap() {
        return editorMap;
    }


    public static void setEditorMap(String index, JInternalFrame area) {
        editorMap.put(index, area);
    }


    private JTabbedPane createTab() {
        JTabbedPane temp = new JTabbedPane();
        temp.setBackground(Color.decode("#90A4AE"));
        temp.setForeground(Color.decode("#212121"));
        temp.setFont(Uicolor.plain);

        // needed
        temp.setFocusable(false);
        temp.setVisible(true);


        // TO DO : set style from file
        temp.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = Uicolor.BOLD_GREY;
                lightHighlight = Uicolor.LIGHT_GREY;
                focus = Uicolor.GREY;
            }
        });
        tabbedPane = temp;

        return temp;
    }
    /**
     * Creates a new instance of the Note class - updates tab count set current editorpane
     * @param title set title for new tab
     */

    public static void handle(String title, String syntax, String send) {

        Note n = new Note(send, syntax, false);
        tabbedPane.addTab(title, n);
        System.out.println("text from file -    " + send + "   Syntax is -   " + syntax + " " +
                "  Title is -  " + title);
                tabCount++;
        String temp = tabCount + title;

        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        setEditorMap(temp, n);
        System.out.print(editorMap.toString());


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

           UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(100,
                   100, 100, 100));
           JFrame frame = new MainScreen();
           frame.setSize(1280, 800);
           frame.setDefaultCloseOperation(3);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);

           // kick off with a new tab
           handle("temp.txt " + tabCount, "PLAIN", null);
       });

    }
}

