import java.awt.*;

/**
 * holds ui elements
 * @author William March
 */

public @interface Uicolor {

    // GREY GUI colors
    Color LIGHT_GREY = Color.decode("#FAFAFA");
    Color GREY = Color.decode("#EEEEEE");
    Color BOLD_GREY = Color.decode("#616161");
    Color DARK_GREY = Color.decode("#9E9E9E");

    // BLUE GUI colors
    Color LIGHT_BLUE = Color.decode("#E1F5FE");
    Color BLUE = Color.decode("#81D4FA");
    Color BOLD_BLUE = Color.decode("#4FC3F7");
    Color DARK_BLUE = Color.decode("#29B6F6");

    // TEAL GUI colors
    Color LIGHT_TEAL = Color.decode("#E0F2F1");
    Color TEAL = Color.decode("#B2DFDB");
    Color BOLD_TEAL = Color.decode("#80CBC4");
    Color DARK_TEAL = Color.decode("#4DB6AC");

    // GREEN GUI colors
    Color LIGHT_GREEN = Color.decode("#E0F2F1");
    Color GREEN = Color.decode("#C8E6C9");
    Color BOLD_GREEN = Color.decode("#81C784");
    Color DARK_GREEN = Color.decode("#66BB6A");


    // Text colors
    Color TEXT_PRIMARY = Color.decode("#212121");

    // fonts
    Font plain = new Font(Font.SANS_SERIF, Font.ITALIC, 13);


}
