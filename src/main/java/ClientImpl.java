import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Anas EL MASSAFI
 */
public class ClientImpl extends UnicastRemoteObject implements Client {

    private final ServerDistant Server;
    private final String name;
    private String msg = "";

    ClientImpl(String name, ServerDistant Server) throws RemoteException {
        this.name = name;
        this.Server = Server;
    }

    @Override
    public void addMessage(String message) {
        Context.getDashboard().area.append("\n\n " + message);
    }
    @Override
    public void newMove(String mark,int move) {
        Context.getBoard().newMove(mark,move);
    }


    @Override
    public void winner() throws RemoteException {
        Context.getDashboard().abandon.setText("Victoire");
        Context.getDashboard().abandon.setBackground(Color.decode("#06d6a0"));
    }

    @Override
    public void loser() throws RemoteException {
        Context.getDashboard().abandon.setText("Défaite");
        Context.getDashboard().abandon.setBackground(Color.decode("#d62828"));
    }

    @Override
    public String getName() {
        return this.name;
    }
}
