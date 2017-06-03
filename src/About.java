import javax.swing.*;

/**
 * A class used to display Information about the program
 * @author William March - s4916313
 */

public class About extends JDialog  {

    // shadow grey

    private JTextPane jt;

    About() {
        this.setSize(135,50);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        // hide on close
        this.setDefaultCloseOperation(1);

        jt = new JTextPane();
        jt.setEditable(false);
        jt.setContentType("text/html");
        jt.setText("<html><strong>Simple java notepad</strong></html>");

        jt.setBackground(Uicolor.dialogC);

        this.add(jt);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> new About());
    }
}

