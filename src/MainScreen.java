import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class that creates the form and tabbed pane
 * @author William March - s4916313
 * @version 1.0
 */

public class MainScreen extends JFrame  {

    public static JEditorPane je; // current notepad in focus

    static int tabCount = 1;
    
    static int current;
    static JTabbedPane tabbedPane = new JTabbedPane();

    public MainScreen() {

        super("NotePad");
     
        
        // colors for pane
        tabbedPane.setBackground(Uicolor.tabBack);
        tabbedPane.setForeground(Uicolor.tabFront);

        // set menu bar
        setJMenuBar(new Menu());

        tabbedPane.setVisible(true);

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
    }

    /**
     * Creates a new instance of the Note class - updates tab count set current editorpane
     * @param title set title for new tab
     */
   public static void newTab(String title) {
       tabbedPane.addTab(title, new Note(null));
       tabCount++;
       je = (JEditorPane)tabbedPane.getComponentAt(current);
   }

    public static void main(String[] args)
    {
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
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(3);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                newTab("Note"+ tabCount);
        });
    }
}

