import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Anas EL MASSAFI
 * @email anas.elmassafi@gmail.com
 */
public class WindowClosing extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent event) {
        try {
            Context.getServer().disconnectPlayer(Context.getClient());
        } catch (Exception e) {
            super.windowClosing(event);
            e.printStackTrace();
        }
        System.exit(1);
    }
}
