import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;

/**
 * @author William March - s4916313
 */

public class RightClick extends MouseAdapter {

    private JPopupMenu popup = new JPopupMenu();

    // actions
    private Action cutAction;
    private Action copyAction;
    private Action pasteAction;
    private Action undoAction;
    private Action selectAllAction;
    private Action closeTabAction;
    private Action clearAction;
    private Action newTabAction;

    private JTextComponent textComponent;
    private String savedString = "";
    private Actions lastActionSelected;

    // system actions
    private enum Actions {UNDO, CUT, COPY, PASTE, SELECT_ALL}



    public RightClick() {
        // undo action
        undoAction = new AbstractAction("Undo") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                textComponent.setText("");
                textComponent.replaceSelection(savedString);

                lastActionSelected = Actions.UNDO;
            }
        };



        // add
        popup.add(undoAction);
        popup.addSeparator();


        // cut action
        cutAction = new AbstractAction("Cut") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.CUT;
                savedString = textComponent.getText();
                textComponent.cut();
            }
        };

        // add
        popup.add(cutAction);


        // copy action
        copyAction = new AbstractAction("Copy") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.COPY;
                textComponent.copy();
            }
        };

        // add
        popup.add(copyAction);

        // paste action
        pasteAction = new AbstractAction("Paste") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.PASTE;
                savedString = textComponent.getText();
                textComponent.paste();
            }
        };

        // add
        popup.add(pasteAction);
        popup.addSeparator();

        // action to clear the current tabs text area
        clearAction = new AbstractAction("Clear") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MainScreen.je.get
            }
        };

        // add
        popup.add(clearAction);

        // close tab action
        closeTabAction = new AbstractAction("Close Tab") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen.tabbedPane.remove(MainScreen.current);
            }
        };

        // add
        popup.add(closeTabAction);
        popup.addSeparator();

        newTabAction = new AbstractAction("New Tab") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen.newTab("Note"+ MainScreen.tabCount, null, null);
            }
        };

        // add
        popup.add(newTabAction);



        // select all action
        selectAllAction = new AbstractAction("Select All") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                lastActionSelected = Actions.SELECT_ALL;
                textComponent.selectAll();
            }
        };

        popup.add(selectAllAction);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {


            if (!(e.getSource() instanceof JTextComponent)) {
                return;
            }

            textComponent = (JTextComponent) e.getSource();
            textComponent.requestFocus();

            boolean enabled = textComponent.isEnabled();
            boolean editable = textComponent.isEditable();


            boolean nonempty = !(textComponent.getText() == null || textComponent.getText().equals(""));
            boolean marked = textComponent.getSelectedText() != null;

            // check for clipboard
            boolean pasteAvailable =
                    Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.stringFlavor);

            // Menu logic
            undoAction.setEnabled(enabled && editable && (lastActionSelected == Actions.CUT || lastActionSelected == Actions.PASTE));
            cutAction.setEnabled(enabled && editable && marked);
            copyAction.setEnabled(enabled && marked);
            pasteAction.setEnabled(enabled && editable && pasteAvailable);
            selectAllAction.setEnabled(enabled && nonempty);

            int nx = e.getX();

            // format for smaller windows
            if (nx > 500) {
                nx = nx - popup.getSize().width;
            }

            popup.show(e.getComponent(), nx, e.getY() - popup.getSize().height);
        }
    }
}

