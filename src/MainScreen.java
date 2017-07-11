
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Class that creates the form and tabbed pane
 * @author William March
 * @version 0.5.1
 */

public class MainScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    public static String prefPath = "";


    // booleans for setting user system os
    static boolean isOsx = false;
    static boolean isWin = false;
    static boolean isLinux = false;

    // K = tab title - String
    // V = tab saved status - boolean
    public static HashMap<String, Boolean> saveMap = new HashMap<>();

    // K = tab title - String
    // V = Instance of Note class representing each editor area - JInternalFrame
    private static HashMap<String, JInternalFrame> editorMap = new HashMap<>();

    static boolean justAddedTab = false;
    static int current;
    static JTabbedPane tabbedPane;
    public static RSyntaxTextArea currentR = new RSyntaxTextArea();

    public JComponent makeUI() {
        UIManager.put("TabbedPane.tabInsets", new Insets(2, 2, 2, 50));
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("aaaaaaaaaaaaaaaa", new JPanel());
        tabbedPane.addTab("bbbbbbbb", new JPanel());
        tabbedPane.addTab("ccc", new JPanel());

        JPanel p = new JPanel(new BorderLayout());
        //p.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
        p.add(tabbedPane);
        p.add(new JButton(new AbstractAction("add tab") {
            @Override public void actionPerformed(ActionEvent e) {
                tabbedPane.addTab("test", new JScrollPane(new JTree()));
            }
        }), BorderLayout.SOUTH);

        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                JPanel gp = new CloseableTabbedPaneGlassPane(tabbedPane);
                tabbedPane.getRootPane().setGlassPane(gp);
                gp.setOpaque(false);
                gp.setVisible(true);
            }
        });

        return p;
    }

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

        // add menu bar
        setJMenuBar(new Menu());


        // add
        add(createTab(), BorderLayout.CENTER);

        // gets currently selected tab as index
        tabbedPane.addChangeListener((e)-> {

                JTabbedPane pane = (JTabbedPane) e.getSource();
                current = pane.getSelectedIndex();

            JInternalFrame je;

            // if tabs are left then obtain reference of new tab
            if (current > -1) {
                je = (JInternalFrame) tabbedPane.getComponentAt(current);
                RSyntaxTextArea rsc;
                rsc = (RSyntaxTextArea) je.getMostRecentFocusOwner();
                currentR = rsc;


                // set title
                this.setTitle(tabbedPane.getTitleAt(current).replace("-", "") +
                        " |  typely - ALPHA  V0.5.5 - Unlicensed  ");

            } else {
                System.err.print("NO TABS");

            }
        });

        setContentPane(tabbedPane);
        pack();
    }


    // keyboard shortcuts
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                // newTab up
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

    // get os and set appropriate path for preferences file
    private void getOs(){
        // get user OS
        switch (Util.getOS()) {
            case WINDOWS:
                isWin = true;
                MainScreen.prefPath = System.getProperty("user.home") + "/Desktop/Edit-Preferences.txt";
                break;
            case LINUX:
                isLinux = true;
                MainScreen.prefPath = System.getProperty("user.home") + "/Edit-Preferences.txt";
                break;
            case MAC:
                isOsx = true;
                prefPath = System.getProperty("user.home") + "/Desktop/Edit-Preferences.txt";
                break;

    }}



    // return hash map with tab number key and RSSyntaxarea value
    public static HashMap getEditorMap() {
        return editorMap;
    }

    //
    public static void addEditorMap(String index, JInternalFrame area) {
        editorMap.put(index, area);
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
                tabAreaInsets = new Insets(0,0,0,0);
            }
        });
        tabbedPane = temp;

        return temp;
    }


    /**
     * Creates a new instance of the Note class - updates tab count set currentR to the newly created
     * RSSyntaxArea
     *
     * @param title - set title for new tab
     * @param syntax - the syntax for the new tab
     * @param send - text to send to the new tab
     */
    public static void newTab(String title, String syntax, String send) {

        Note n = new Note(send, syntax);
        if (title.length() < 20){
            tabbedPane.addTab("-----"+title +"-----",  n);
        }else if (title.length() < 10){
            tabbedPane.addTab("-------------"+title+"-------------",  n);

        }else {
            tabbedPane.addTab(title,  n);
        }
        System.out.println("text from file -    " + send + "   Syntax is -   " + syntax + " " +
                "  Title is -  " + title);




        // change currently selected tab to new tab
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() -1);


        saveMap.put(title, false);


        // add new tab  to editor hashmap to keep track of it possibly in a later build
        addEditorMap(title, n);
        System.out.print(editorMap.toString());
    }


    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {

               try {
                   System.setProperty("apple.laf.useScreenMenuBar", "true");
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
           newTab("temp.txt ", "PLAIN", null);
       });

    }
}

