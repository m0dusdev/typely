import com.sun.tools.javac.Main;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March -
 */
 class Menu extends JMenuBar{
    // Create menu;

    private int tabCount = 0; // the amount of tabs

    Menu() {

        // file menu icons
        JMenu fMenu;         // file
        JMenu tMenu;        //  tab
        JMenu hMenu;       //   help


        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);


        // add new tab button
        JButton addBtn = new JButton(" + ");
        addBtn.setSize(40,50);
        addBtn.addActionListener((e -> {

            MainScreen.newTab("New note " ,"", false);
            updateTabCount();
        }));

        add(addBtn);


        // add new drawing button
        JButton addDrawingBtn = new JButton(" `~/ ");
        addDrawingBtn.setSize(40,50);
        addDrawingBtn.addActionListener((e -> {

            MainScreen.newTab("New Drawing " ,"", true);
            updateTabCount();
        }));

        add(addDrawingBtn);


        // open menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Exit");
        exitMenuItem.addActionListener((e)-> System.exit(0));

        // open menu item
        JMenuItem newDrawingMenuItem = new JMenuItem("New Drawing");
        newDrawingMenuItem.setToolTipText("New Drawing");
        newDrawingMenuItem.addActionListener((e)-> MainScreen.newTab("Drawing", "", true));

        fMenu.add(newDrawingMenuItem);

        // open menu item
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setToolTipText("Open a file");
        openMenuItem.addActionListener((e)-> {
            try{
                Io.open();
            }catch (IOException io) {
                io.printStackTrace();
            }
        });

        fMenu.add(openMenuItem);

        // saveAs Menu item
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        saveAsMenuItem.setToolTipText("Save a file in a location");
        saveAsMenuItem.addActionListener((e)-> Io.saveAs());

        fMenu.add(saveAsMenuItem);

        // Tab menu
        tMenu = new JMenu("  Tab  ");
        tMenu.setMnemonic(KeyEvent.VK_T);
        tMenu.setToolTipText("Apply operations to the currently selected tab");

        // new tab menu item
        JMenuItem newMenui = new JMenuItem("New Tab");
        newMenui.setToolTipText("New Tab");
        newMenui.addActionListener((e)-> MainScreen.newTab("Note",  "", true));
        tMenu.add(newMenui);

        // clear tab menu item
        JMenuItem clMenui = new JMenuItem("Clear");
        clMenui.setToolTipText("Clear tabs contents");
        // listen
        clMenui.addActionListener((e ->
            MainScreen.getRSSyntaxarea().setText("")));

        // add
        tMenu.add(clMenui);


        // close all  tab menu item
        JMenuItem clAllMenui = new JMenuItem("Close all");
        clAllMenui.setToolTipText("Close all tabs");
        // listen
        clAllMenui.addActionListener((e -> MainScreen.getTabPane().removeAll()));
        // add
        tMenu.add(clAllMenui);




        // close tab without saving
        JMenuItem fcMenui = new JMenuItem("Close", KeyEvent.VK_T);
        KeyStroke ctrlXKeyStrokeforce = KeyStroke.getKeyStroke("control f");
        fcMenui.setAccelerator(ctrlXKeyStrokeforce);
        fcMenui.setToolTipText("Close tab without saving");

        // listen
        fcMenui.addActionListener((e -> MainScreen.getTabPane().remove(MainScreen.current)));

        // add
        tMenu.add(fcMenui);

        // close and save current tab
        JMenuItem cMenui = new JMenuItem("Close and save", KeyEvent.VK_T);
        KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control c");
        cMenui.setAccelerator(ctrlXKeyStroke);
        cMenui.setToolTipText("Close and save tab");

        // listen
        cMenui.addActionListener((e -> {
            Io.saveAs();
            MainScreen.getTabPane().removeTabAt(MainScreen.current);
            System.out.print("\nyes\n");
        }));

        // add
        tMenu.add(cMenui);

        hMenu = new JMenu(" Help ");

        //about item
        JMenuItem aMenui = new JMenuItem(" About ");

        // listen
        aMenui.addActionListener((e)-> SwingUtilities.invokeLater(About::new));

        // add
        hMenu.add(aMenui);


        // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);


        this.setSize(this.getWidth(), 100);

        // Close current tab button
        JButton closeBtn = new JButton("Close Tab");
        closeBtn.addActionListener((e -> MainScreen.getTabPane().removeTabAt(MainScreen.current)));
        add(closeBtn);

        JMenu spaceMenu = new JMenu("  ");
        add(spaceMenu);






    }

    private void updateTabCount(){
        this.tabCount++;
    }
}
