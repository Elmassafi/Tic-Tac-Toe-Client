import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerDistant extends Remote {
    void registerClient(Client Client) throws RemoteException;

    void broadcastMessage(String message) throws RemoteException;

    void broadcastMove(String message) throws RemoteException;

    void disconnectClient(String name) throws RemoteException;

    Boolean checkValidName(String name) throws RemoteException;

    int getSessionId(String name) throws RemoteException, MalformedURLException;
}
