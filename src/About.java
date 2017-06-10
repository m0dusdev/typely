import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A class used to display Information about the program
 * @author William March - s4916313
 */

public class About extends JDialog   {

    // shadow grey

    private JLabel jt;

    About() {


        this.setLayout(new BorderLayout());
        this.setTitle("About ed-it");


        this.setSize(500,150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        // hide on close
        this.setDefaultCloseOperation(1);
        jt= new JLabel();

        jt.setFont(Uicolor.plain);
        jt.setBackground(Uicolor.GREY);
        jt.setForeground(Uicolor.TEXT_PRIMARY);



        jt.setText("         Ed-it text editor beta V0.1  : Aurthor William March - moduspc@gmail.com");

        jt.setBackground(Uicolor.LIGHT_GREY);

        this.add(jt, SwingConstants.CENTER);
    }

}

