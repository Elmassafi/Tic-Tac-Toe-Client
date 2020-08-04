import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Anas EL MASSAFI
 * @email anas.elmasssafi@gmail.com
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String serverURL = Configuration.getServerURL();
        try {
            ServerDistant server = (ServerDistant) Naming.lookup(serverURL);
            Context.setServer(server);
            String name = getUserName();
            boolean allowed = server.checkValidName(name);
            System.out.println(allowed);
            while (!allowed) {
                name = JOptionPane.showInputDialog("le nom " + name + " est pris veuillez entrer un nouveau nom");
                allowed = server.checkValidName(name);
            }
            HumanPlayer client = new HumanPlayer(name, server);
            Context.setClient(client);
            server.registerPlayer(client);
            Home home = new Home(name);
            Context.setHome(home);
            Common.logger.info("Salut " + name);

            java.awt.EventQueue.invokeLater(() -> home.setVisible(true));

        } catch (NotBoundException e) {
            System.out.println("NotBoundException " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException " + e.getMessage());
        } catch (RemoteException e) {
            System.out.println("RemoteException " + e.getMessage());
        }
    }


    // End of variables declaration//GEN-END:variables
    private static String getUserName() {
        return JOptionPane.showInputDialog("Entrez votre nom");
    }
}
