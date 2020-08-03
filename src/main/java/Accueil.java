import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Anas EL Massafi
 * @email anas.elmassafi@gmail.com
 */
public class Accueil extends javax.swing.JFrame {

    private final String name;

    // Variables declaration - do not modify
    private JButton vsPlayer;
    private JButton vsComputer;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public Accueil(String name) {
        this.name = name;
        addWindowListener(new WindowAdapter() {
        });
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Accueil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

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
            ClientImpl client = new ClientImpl(name, server);
            Context.setClient(client);
            server.registerClient(client);
            Accueil accueil = new Accueil(name);

            Common.logger.info("Salut " + name);
            java.awt.EventQueue.invokeLater(() -> accueil.setVisible(true));
        } catch (NotBoundException e) {
            System.out.println("NotBoundException " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException " + e.getMessage());
        } catch (RemoteException e) {
            System.out.println("RemoteException " + e.getMessage());
        }
        /*AccueilHolder.accueil = accueil;*/

        /* Create and display the form */

    }

    // End of variables declaration//GEN-END:variables
    private static String getUserName() {
        return JOptionPane.showInputDialog("Entrez votre nom");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vsPlayer = new javax.swing.JButton();
        vsComputer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(Configuration.Background_Color);

        jLabel1.setText("Bienvenue " + name);
        //   jLabel1.setSize(new Dimension(100, 100));
        jLabel1.setFont(new Font("Sans Serif", Font.PLAIN, 14));

        //    jLabel1.setForeground(Configuration.Writing_Color);


        vsPlayer.setText("Jouez contre un joueur");
        vsPlayer.addActionListener(this::jouezContreUnJoueur);
        vsPlayer.setBackground(Configuration.Btn_Color);

        vsComputer.setText("Jouez contre l'ordinateur");
        vsComputer.addActionListener(this::jouezContreOrdinateur);
        vsComputer.setBackground(Configuration.Btn_Color);

        //jPanel1.setPreferredSize(new Dimension(500,500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(89, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                                .addComponent(vsPlayer)
                                                .addGap(58, 58, 58)
                                                .addComponent(vsComputer)
                                                .addGap(77, 77, 77))
                                        .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(131, 131, 131))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(vsPlayer)
                                        .addComponent(vsComputer))
                                .addContainerGap(114, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(jPanel1);
        pack();
    }// </editor-fold>

    private void jouezContreUnJoueur(ActionEvent event) {

        try {
            Common.logger.info(name + " choisit de jouer contre un autre joueur");
            ServerDistant server = Context.getServer();
            String mark = server.joinRoom(Context.getClient());
            Dashboard dashboard = new Dashboard(mark);
            Context.setDashboard(dashboard);
            //server.broadcastMessage(name + " Has Connected");
            //new Thread(Context.getClient()).start();
            //  welcomeUser(name);
            this.setVisible(false);
            Context.getDashboard().setVisible(true);
            int id = Context.getServer().getSessionId(name);
            System.out.println(id);
            while (id==0){
                id = Context.getServer().getSessionId(name);
                System.out.println(id);
            }
            System.out.println(id);
            GameDistant game = (GameDistant) Naming.lookup(Configuration.getServerURL() + "/" + id);
            Context.setGame(game);
        } catch (Exception e) {
            Context.getDashboard().setVisible(false);
            JOptionPane.showMessageDialog(this, "nous ne pouvons pas nous connecter au serveur,\n Assurez-vous que le serveur fonctionne sur le port 1099", "Échec de connexion au serveur", JOptionPane.ERROR_MESSAGE);
            Common.logger.warning("nous ne pouvons pas nous connecter au serveur, assurez-vous que le serveur fonctionne sur le port 1099");
            Common.logger.warning(e.getMessage());
        }


    }

    private void jouezContreOrdinateur(ActionEvent event) {
    }

}
