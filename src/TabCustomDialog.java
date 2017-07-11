import javax.swing.*;
import java.awt.*;

/**
 * Class for setting a custom tab width via a JSpinner
 */

final class TabCustomDialog extends JDialog {

    private JButton okBtn;
    private JSpinner jt;
    private int selection;

    TabCustomDialog() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Tabs");
        this.setLayout(null);
        this.setPreferredSize(new Dimension(175,90));

        //spinner setup
        SpinnerModel model = new SpinnerNumberModel(4,0,50,1);
        jt = new JSpinner(model);
        jt.setBounds(20,10,50,30);
        jt.setVisible(true);
        jt.addChangeListener((e -> {
            try {
                jt.commitEdit(); // purge user input if any
            } catch ( java.text.ParseException pe ) {
                pe.getCause();
            }
            // get value and cast to class member "selection"
            selection = (Integer)jt.getValue();
        }));

        // ok button creation
        okBtn = new JButton("Set");
        okBtn.setBounds(92,13, 48,25);
        okBtn.setVisible(true);
        okBtn.addActionListener((e ->{

            // set current tabs tab width
            MainScreen.currentR.setTabSize(selection);

            // close dialog
            dispose();
        } ));

        // add components to dialog
        this.add(jt);
        this.add(okBtn);

        pack();

        // centre dialog
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);

    }

}
