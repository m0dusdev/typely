
import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Class that represents the frame and tabbed pane
 * @author William March
 * @version 0.5.1
 */

final class MainScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    public static String prefPath = "";


    // booleans for setting user system os
    private static boolean isOsx = false;
    private static boolean isWin = false;
    private static boolean isLinux = false;

    // K = tab title - String
    // V = tab saved status - boolean
    public static HashMap<String, Boolean> saveMap = new HashMap<>();

    // K = tab title - String
    // V = Instance of Note class representing each editor area - JInternalFrame
    private static HashMap<String, JInternalFrame> editorMap = new HashMap<>();

    static boolean justAddedTab = false;
    static int current;
    private static JTabbedPane getTabPane;

    //
    private static JEditorPane rsc;


    private MainScreen() {

        // initial title
        super(" |  typely - ALPHA  V0.5.5 - Unlicensed  ");
        addKeyListener(new KeyListen());

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

        // add menu bar
        setJMenuBar(new Menu());


        // add
        add(createTab(), BorderLayout.CENTER);

        // gets currently selected tab as index
        getTabPane.addChangeListener((e)-> {

                JTabbedPane pane = (JTabbedPane) e.getSource();
                current = pane.getSelectedIndex();

            JInternalFrame je;


                this.setTitle(getTabPane.getTitleAt(current).replace("-", "") +
                        " |  typely - ALPHA  V0.5.5 - Unlicensed  ");

            } else {
                System.err.print("NO TABS");

            }
        });

        setContentPane(getTabPane);
        pack();
    }


    // HANDLE KEY PRESSES
    class KeyListen implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("Right key typed");
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("Left key typed");
            }

        }



        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("Right key Released");
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("Left key Released");
            }
        }



        // keyboard shortcuts
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch( keyCode ) {
                case KeyEvent.VK_1:
                    System.out.print("\njpressed\n");
                    break;
                case KeyEvent.VK_DOWN:
                    // newTab down
                    break;
                case KeyEvent.VK_LEFT:
                    // newTab left
                    break;
                case KeyEvent.VK_RIGHT :
                    // newTab right
                    break;
            }
        }
    }



    // get os and set appropriate path for preferences file
    private void getOs(){
        // get user OS
        switch (Util.getOS()) {
            case WINDOWS:
                isWin = true;
                MainScreen.prefPath = System.getProperty("user.home") +
                        "/Documents" + "/typely-Preferences.txt";
                break;
            case LINUX:
                isLinux = true;
                MainScreen.prefPath = System.getProperty("user.home") +
                        "/typely-Preferences.txt";
                break;
            case MAC:
                isOsx = true;
                prefPath = System.getProperty("user.home") +
                        "/Desktop/Edit-Preferences.txt";
                break;

    }}

    private static void setEditorPane(JEditorPane rs){
        rsc = rs;
    }

    // return refrence the currently selected RSSytanxarea
    @Contract(pure = true)
    public static JEditorPane getRSSyntaxarea(){
        return rsc;
    }

    // return hash map with tab number key and RSSyntaxarea value
    public static HashMap getEditorMap() {
        return editorMap;
    }

    //
    private static void addEditorMap(String index, JInternalFrame area) {
        editorMap.put(index, area);
    }


    public static JTabbedPane getTabPane(){
        return getTabPane;
    }


    /**
     * @return - a styled tabbed pane
     */
    private JTabbedPane createTab() {
        JTabbedPane temp = new JTabbedPane();
        temp.setBackground(Color.decode("#90A4AE"));
        temp.setForeground(Color.decode("#212121"));
        temp.setFont(Uicolor.plain);


        temp.setFocusable(false);
        temp.setVisible(true);


        // Ui hacking
        temp.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = Uicolor.BOLD_GREY;
                lightHighlight = Uicolor.LIGHT_GREY;
                focus = Uicolor.GREY;
                //tabAreaInsets = new Insets(20,10,20,10);
                tabInsets = new Insets(20,10,20,10);
            }
        });
        getTabPane = temp;
        return temp;
    }


    /**
     * Creates a new instance of the Note class - updates tab count set currentR to the newly created
     * RSSyntaxArea
     *
     * @param title - set title for new tab
     * @param send - text to send to the new tab
     * @param type - false = notepad, true = drawing
     */
    public static void newTab(String title, String send, boolean type) {

        if (!type) {
            Note n = new Note(send);
            getTabPane.addTab(title,  n);



            // change currently selected tab to new tab
            getTabPane.setSelectedIndex(getTabPane.getTabCount() -1);


            saveMap.put(title, false);


            // add new tab  to editor hashmap to keep track of it possibly in a later build
            addEditorMap(title, n);
            System.out.print(editorMap.toString());
        } else {
            Drawing temp = new Drawing();
            getTabPane().addTab("Drawing",temp);
        }

    }


    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {

               try {
                   //System.setProperty("apple.laf.useScreenMenuBar", "true");
                   //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                   UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                   //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.WindowsLookAndFeel");


               }catch (ClassNotFoundException cl) {

               }catch (InstantiationException in) {

               }catch (IllegalAccessException ile) {

               }catch (UnsupportedLookAndFeelException unsup) {

               }

           JFrame frame = new MainScreen();
           frame.setSize(1280, 800);
           frame.setDefaultCloseOperation(3);
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);

           // kick off with a new tab
           newTab("temp.txt ",  "", false);
       });

    }
}

