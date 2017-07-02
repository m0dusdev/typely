import com.sun.javafx.image.impl.IntArgb;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import org.jetbrains.annotations.Contract;

/**
 * Class that creates the form and tabbed pane
 * @author William March
 * @version 0.5.1
 */

public class MainScreen extends JFrame {

    private static final long serialVersionUID = 1L;



    // booleans for setting user system os
    static boolean isOsx = false;
    static boolean isWin = false;
    static boolean isLinux = false;

    public static HashMap<String, Boolean> saveMap = new HashMap<>();

    // K = tab title
    // V = all instances of RSSyntax areas
    private static HashMap<String, JInternalFrame> editorMap = new HashMap<>();

    static boolean justAddedTab = false;
    static int current;
    static JTabbedPane tabbedPane;
    public static RSyntaxTextArea currentR = new RSyntaxTextArea();

    public MainScreen() {

        // initial title
        super(" |  typely - ALPHA  V0.5.5 - Unlicensed  ");

        // get os
        getOs();



        // listen for close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowOpened(e);
                CloseDialog cl = new CloseDialog("test", "test", false);
                int tempResult = cl.show();

                if (tempResult == 1) {
                    // yes option
                }
                dispose();
            }
        });

        // add manu bar
        setJMenuBar(new Menu());


        // add
        add(createTab(), BorderLayout.CENTER);

        // gets currently selected tab as index
        tabbedPane.addChangeListener((e)-> {

                JTabbedPane pane = (JTabbedPane) e.getSource();
                current = pane.getSelectedIndex();

            JInternalFrame je;

            if (current > -1) {
                je = (JInternalFrame) tabbedPane.getComponentAt(current);

                RSyntaxTextArea rsc;

                rsc = (RSyntaxTextArea) je.getMostRecentFocusOwner();

                currentR = rsc;

                this.setTitle(tabbedPane.getTitleAt(current) +    " |  typely - ALPHA  V0.5.5 - Unlicensed  ");

            } else {
                System.err.print("NO TABS");

            }
        });

        setContentPane(tabbedPane);
        pack();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                // handle up
                break;
            case KeyEvent.VK_DOWN:
                // handle down
                break;
            case KeyEvent.VK_LEFT:
                // handle left
                break;
            case KeyEvent.VK_RIGHT :
                // handle right
                break;
        }
    }


    // helper function for Util.getOs - - add more later
    private void getOs(){
        // get user OS
        switch (Util.getOS()) {
            case WINDOWS:
                isWin = true;
                break;
            case LINUX:
                isLinux = true;
                break;
            case MAC:
                isOsx = true;
                break;

    }}



    // return hash map with tab number key and RSSyntaxarea value
    public static HashMap getEditorMap() {
        return editorMap;
    }

    // add to
    public static void addEditorMap(String index, JInternalFrame area) {
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
                tabAreaInsets = new Insets(5,5,5,2);
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

        Note n = new Note(send, syntax);
        tabbedPane.addTab(title, n);
        System.out.println("text from file -    " + send + "   Syntax is -   " + syntax + " " +
                "  Title is -  " + title);

        String temp = title;





        // change currently selected tab to new tab
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

        saveMap.put(title, false);


        // add new tab  to editor hashmap to keep track of it possibly in a later build
        addEditorMap(temp, n);
        System.out.print(editorMap.toString());
    }


    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {

           // set appropriate look and feel for each system type
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

           // kick off with a new tab
           handle("temp.txt ", "PLAIN", null);
       });

    }
}

