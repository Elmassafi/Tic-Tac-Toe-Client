import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Anas EL MASSAFI
 */
public class ClientImpl extends UnicastRemoteObject implements Client,Runnable {

    private final ServerDistant Server;
    private final String name;
    private String msg = "";

    ClientImpl(String name, ServerDistant Server) throws RemoteException {
        this.name = name;
        this.Server = Server;
    }

    @Override
    public void addMessage(String message) {
        System.out.println(message);
        Dashboard.area.append("\n\n " + message);
        msg = message;
    }

    public void doMove(String move) {
        System.out.println("this move " + move);
        if (msg.contains("b1")) {
            Dashboard.b1.setText(move);
            Dashboard.b1.setEnabled(false);
        } else if (msg.contains("b2")) {
            Dashboard.b2.setText(move);
            Dashboard.b2.setEnabled(false);
        } else if (msg.contains("b3")) {
            Dashboard.b3.setText(move);
            Dashboard.b3.setEnabled(false);
        } else if (msg.contains("b4")) {
            Dashboard.b4.setText(move);
            Dashboard.b4.setEnabled(false);
        } else if (msg.contains("b5")) {
            Dashboard.b5.setText(move);
            Dashboard.b5.setEnabled(false);
        } else if (msg.contains("b6")) {
            Dashboard.b6.setText(move);
            Dashboard.b6.setEnabled(false);
        } else if (msg.contains("b7")) {
            Dashboard.b7.setText(move);
            Dashboard.b7.setEnabled(false);
        } else if (msg.contains("b8")) {
            Dashboard.b8.setText(move);
            Dashboard.b8.setEnabled(false);
        } else if (msg.contains("b9")) {
            Dashboard.b9.setText(move);
            Dashboard.b9.setEnabled(false);
        } else if (move.contains("Result")) {
            System.out.println("FIN" + msg);
            Dashboard.area.append("\n\n " + msg);
            Dashboard.win(msg);
        }
    }


    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            check();
            try {
                checkDisconnect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void check() {
        String text = Dashboard.field.getText();
        if (Dashboard.posted) {
            try {
                Server.broadcastMessage(name + " said: " + text);
                Dashboard.posted = false;
                Dashboard.field.setText("");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (Dashboard.clicked) {
            try {
                Server.broadcastMessage(name + " Clicked: " + text);
                Server.broadcastMove(text);
                Dashboard.clicked = false;
                Dashboard.field.setText("");
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    private void checkDisconnect() throws RemoteException {
        if (Dashboard.disconnected) {
            Server.broadcastMessage(name + " Has Disconnected");
            Dashboard.disconnected = false;
            Server.disconnectClient(this.name);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
