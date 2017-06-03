import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * This class builds a menu bar and handles the actions associated with it.
 * @author William March - s4916313
 */

public class Menu extends JMenuBar{
    // Create menu;
    public  Menu() {
        JMenu fMenu; // file
        JMenu tMenu; // tab
        JMenu hMenu; // help

        // file menu
        fMenu = new JMenu(" File ");
        fMenu.setMnemonic(KeyEvent.VK_F);

        // new item
        JMenuItem nMenui = new JMenuItem(" New note ");
        nMenui.setMnemonic(KeyEvent.VK_N);

        // new item listen
        nMenui.addActionListener((e) -> MainScreen.newTab("Note" + MainScreen.tabCount));

        // add
        fMenu.add(nMenui);
        fMenu.add(new JSeparator());

        // exit menu item
        JMenuItem eMenuI = new JMenuItem(" Exit ");
        fMenu.add(eMenuI);

        // exit menu listen
        eMenuI.addActionListener((e)-> System.exit(0));

        // save  item
        JMenuItem sMenui = new JMenuItem(" Save ");
        fMenu.add(sMenui);

        // save listen
        sMenui.addActionListener(event -> {
                Io.save();
        });

        // open item
        JMenuItem oMenui = new JMenuItem(" Open ");
        fMenu.add(oMenui);

        // open listen
        oMenui.addActionListener(event -> {
            try{
                Io.open();
            } catch(IOException ie){

            }

        });


        // Tab menu
        tMenu = new JMenu("Tab");
        tMenu.setMnemonic(KeyEvent.VK_T);

        // clear tab menu item
        JMenuItem clMenui = new JMenuItem("Clear Tab");

        // listen
        clMenui.addActionListener((e -> MainScreen.je.setText("")));

        // add
        tMenu.add(clMenui);

        // Close tab item
        JMenuItem cMenui = new JMenuItem("Close Tab ");
        // listen
        cMenui.addActionListener((e -> MainScreen.tabbedPane.remove(MainScreen.current)));

        // add
        tMenu.add(cMenui);

        hMenu = new JMenu("Help");

        //about item
       JMenuItem aMenui = new JMenuItem(" About ");

       // listen
       aMenui.addActionListener((e)-> new About());

       // add
       hMenu.add(aMenui);

       // add menus to bar
        add(fMenu);

        add(tMenu);

        add(hMenu);
    }
}
