import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anas EL MASSAFI
 * @email anas.elmassafi@gmail.com
 */
public class HumanPlayer extends UnicastRemoteObject implements Player {

    private final ServerDistant Server;
    private final String name;
    private String msg = "";

    public HumanPlayer(String name, ServerDistant Server) throws RemoteException {
        this.name = name;
        this.Server = Server;
    }

    @Override
    public void addMessage(String message) {
        Context.getDashboard().area.append("\n\n " + message);
    }

    @Override
    public void newMove(String mark, int move) {
        Context.getBoard().newMove(mark, move);
    }


    @Override
    public void winner() {
        Context.getDashboard().abandon.setText("Victoire");
        Context.getDashboard().abandon.setBackground(Color.decode("#06d6a0"));
    }

    @Override
    public void loser() {
        Context.getDashboard().abandon.setText("Défaite");
        Context.getDashboard().abandon.setBackground(Color.decode("#d62828"));
    }

    @Override
    public void tie() {
        Context.getDashboard().abandon.setText("Match nul");
        Context.getDashboard().abandon.setBackground(Color.decode("#F5E960"));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void meetingRoomRespond(int res) throws RemoteException {
        try {
            if (res == 1) {
                AtomicInteger id;
                try {
                    id = new AtomicInteger(Context.getServer().getSessionId(name));
                    GameDistant game = (GameDistant) Naming.lookup(Configuration.getServerURL() + "/" + id.get());
                    Context.setGame(game);
                    Context.getDashboard().setVisible(true);
                    Context.loading.setVisible(false);
                } catch (NotBoundException | MalformedURLException e) {
                    e.printStackTrace();
                    Context.loading.jLabel1.setText("essayer plus tard");
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
