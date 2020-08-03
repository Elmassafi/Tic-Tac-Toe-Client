import java.awt.*;

/**
 * @author Anas EL MASSAFI
 */
public class Configuration {

    private static final String ServerURL = "rmi://localhost/tictactoe";

    public static String getServerURL() {
        return ServerURL;
    }

    public final static Color Btn_Color = Color.decode("#FAF9F9");
    public final static Color Background_Color = Color.decode("#BEE3DB");
    public final static Color Border_Color = Color.decode("#FFD6BA");
    public final static Color Writing_Color=Color.decode("#555B6E");
}
