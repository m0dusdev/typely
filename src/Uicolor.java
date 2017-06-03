import java.awt.*;

/**
 * holds ui elements
 * @author William March
 */
public @interface Uicolor {
    // colors
    Color tabFront = new Color(127,140,141);  // grey
    Color tabBack = new Color(241, 229, 238); // pink
    Color dialogC = new Color(133, 133, 173); // shadow for dialog

    // fonts
    Font plain = new Font(Font.SANS_SERIF, Font.ITALIC, 13);
}
