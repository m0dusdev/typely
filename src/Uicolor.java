import java.awt.*;

/**
 * holds ui elements
 * @author William March
 */

public @interface Uicolor {

    // GUI colors
    Color DARK_PRIMARY = Color.decode("#616161");
    Color DEFAULT_PRIMARY = Color.decode("#9E9E9E");
    Color LIGHT_PRIMARY = Color.decode("#F5F5F5");
    Color ACCENT_COLOR = Color.decode("#607D8B");

    // Text colors
    Color TEXT_PRIMARY = Color.decode("#212121");

    // fonts
    Font plain = new Font(Font.SANS_SERIF, Font.ITALIC, 13);

}
