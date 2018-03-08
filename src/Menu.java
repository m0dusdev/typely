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


    Menu() {
        // file menu icons



        JMenu fMenu;         // file
        JMenu tMenu;        //  tab
        JMenu hMenu;       //   help
        JMenu tsMenu;       //      tab spaces




        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // Preferences
        JMenuItem prefItem = new JMenuItem("Preferences");
        fMenu.add(new JSeparator());
        fMenu.add(prefItem);
        prefItem.addActionListener((e -> {

            // try to get existing preferences file from user
            try {
                java.util.List<String> lines = Files.readAllLines(Paths.get(MainScreen.prefPath),
                        Charset.defaultCharset());
                StringBuilder styleBuffer = new StringBuilder();

                // add lines from file to new buffer
                for (String temp : lines) {
                    styleBuffer.append(temp);
                    styleBuffer.append("\n");
                }

                // add buffer to new tab
                MainScreen.newTab("Edit-Preferences.txt", "SYNTAX_STYLE_CSS",
                        styleBuffer.toString());
                MainScreen.getRSSyntaxarea().setCaretPosition(0);

                // if no preferences file was found, open a dialog and create a new preferences
                // file if the user chooses yes
            } catch (IOException ioe) {
                SwingUtilities.invokeLater(()-> {
                    CloseDialog cl = new CloseDialog("No preferences file found, would you like " +
                            "to create one ?",
                            "Error");

                    if (cl.show() == 0) {
                        try{
                            PrintWriter writer = new PrintWriter(MainScreen.prefPath, "UTF-8");
                            writer.println(
                                    "/*LOOK AND FEEL*/\n" +
                                            "Theme = dark\n\n"+
                                    "/*PREFERENCES*/\n"
                                    );
                            writer.flush();
                            writer.close();


                            // inform user of new file creation and its location
                            SwingUtilities.invokeLater(()-> new CloseDialog("File was created at "+
                                    MainScreen.prefPath,"New preferences file created" , true));


                        } catch (IOException ioex) {
                            // do something
                        }
                    }

                });
            }
        }));

        // Tab menu
        tMenu = new JMenu("  Tab  ");
        tMenu.setMnemonic(KeyEvent.VK_T);
        tMenu.setToolTipText("Apply operations to the currently selected tab");

        // clear tab menu item
        JMenuItem clMenui = new JMenuItem("Clear");
        clMenui.setToolTipText("Clear tabs contents");
        // listen
        clMenui.addActionListener((e ->
            MainScreen.getRSSyntaxarea().setText("")));

        // add
        tMenu.add(clMenui);



        // close tab without saving
        JMenuItem fcMenui = new JMenuItem("Close", KeyEvent.VK_T);
        KeyStroke ctrlXKeyStrokeforce = KeyStroke.getKeyStroke("control f");
        fcMenui.setAccelerator(ctrlXKeyStrokeforce);
        fcMenui.setToolTipText("Close tab without saving");

        // listen
        fcMenui.addActionListener((e -> MainScreen.tabbedPane.remove(MainScreen.current)));

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
            MainScreen.tabbedPane.removeTabAt(MainScreen.current);
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




    }
}
