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

    static boolean isOsx = false;
    static boolean isWin = false;
    static boolean isLinux = false;

    public Float version = 0.5F;

    private static HashMap<String, JInternalFrame> editorMap = new HashMap<>();

    private static final long serialVersionUID = 1L;

    static boolean justAddedTab = false;
    static int tabCount = 0;
    static int current;
    static JTabbedPane tabbedPane;
    public static RSyntaxTextArea currentR = new RSyntaxTextArea();

    public MainScreen() {


        super("ed-it");

        // get os
        getOs();

        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(170, 0, 0, 0));



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

            if (current > -1) {
                je = (JInternalFrame) tabbedPane.getComponentAt(current);

                RSyntaxTextArea rsc;

                rsc = (RSyntaxTextArea) je.getMostRecentFocusOwner();

                currentR = rsc;
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
                tabCount++;
        String temp = tabCount + title;

        // change currently selected tab to new tab
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

        // add new tab  to editor hashmap to keep track of it possibly in a later build
        addEditorMap(temp, n);
        System.out.print(editorMap.toString());
    }



    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           // set appropriate look and feel for each system type
           if (isWin){ // windows look and feel
               try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               } catch (ClassNotFoundException ex) {
               } catch (InstantiationException ex) {
               } catch (IllegalAccessException ex) {
               } catch (UnsupportedLookAndFeelException ex) {
               }

           }else if (isLinux){ // linux look and feel
               try {
                   for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                       if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                           javax.swing.UIManager.setLookAndFeel(info.getClassName());
                           break;
                       }
                   }
               } catch (ClassNotFoundException ex) {
               } catch (InstantiationException ex) {
               } catch (IllegalAccessException ex) {
               } catch (UnsupportedLookAndFeelException ex) {
               }

           }else if (isOsx){
               try { // osx look and feel
                   System.setProperty("com.apple.mrj.application.apple.menu.about.name", "typley");
                   System.setProperty("apple.laf.useScreenMenuBar", "true");
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               } catch (ClassNotFoundException ex) {
               } catch (InstantiationException ex) {
               } catch (IllegalAccessException ex) {
               } catch (UnsupportedLookAndFeelException ex) {
               }
           }


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

