import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.Naming;

/**
 * @author Anas EL Massafi
 * @email anas.elmassafi@gmail.com
 */
public class Home extends javax.swing.JFrame {

    private final String name;

    public Home(String name) {
        this.name = name;
        this.addWindowListener(new WindowClosing());
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        this.setTitle("Tic Tac Toe : " + Context.getClient().getName() + " ");
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        // Variables declaration - do not modify
        JButton vsPlayer = new JButton();
        JButton vsComputer = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(Configuration.Background_Color);

        jLabel1.setText("Bienvenue " + name);
        jLabel1.setPreferredSize(new Dimension(200, 100));
        jLabel1.setFont(new Font("Segoe Print", Font.PLAIN, 20));

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
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                                .addComponent(vsPlayer)
                                                .addGap(80, 80, 80)
                                                .addComponent(vsComputer)
                                                .addGap(80, 80, 80))
                                        .addGroup(GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(131, 131, 131))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
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
            System.out.println("dashboard " + dashboard);
            Context.setDashboard(dashboard);
            Context.loading.setVisible(true);
            this.setVisible(false);
            if (mark.equals("O")) {
                Context.getClient().meetingRoomRespond(1);
            }
        } catch (Exception e) {
            //Context.getDashboard().setVisible(false);
            JOptionPane.showMessageDialog(this, "nous ne pouvons pas nous connecter au serveur,\n Assurez-vous que le serveur fonctionne sur le port 1099", "Échec de connexion au serveur", JOptionPane.ERROR_MESSAGE);
            Common.logger.warning("nous ne pouvons pas nous connecter au serveur, assurez-vous que le serveur fonctionne sur le port 1099");
            Common.logger.warning(e.getMessage());
            e.printStackTrace();
        }


    }

    private void jouezContreOrdinateur(ActionEvent event) {
        try {

            String[] values = {"Random", "Minimax ", "AlphaBeta"};
            final Object dialog = JOptionPane.showInputDialog(this, "Quel est le niveau cible?", "Selection", JOptionPane.PLAIN_MESSAGE, null, values, "Random");
            if (dialog != null) {//null if the user cancels.
                String selectedString = dialog.toString();
                System.out.println(selectedString);
                Common.logger.info(name + " choisit de jouer contre un autre joueur");
                ServerDistant server = Context.getServer();
                String mark = server.playVsComputer(Context.getClient(), selectedString);
                System.out.println(mark);
                if (mark.equals("NO")) {
                    Common.logger.warning("We cannot create a Vs Computer Game");
                } else {
                    Dashboard dashboard = new Dashboard(mark);
                    Context.setDashboard(dashboard);
                    this.setVisible(false);
                    Context.getDashboard().setVisible(true);
                    int id = 0;
                    GameDistant game = (GameDistant) Naming.lookup(Configuration.getServerURL() + "/" + id);
                    Context.setGame(game);
                }
            } else {
                Common.logger.warning("commande annulée par l'utilisateur");
            }

        } catch (Exception e) {
//            Context.getDashboard().setVisible(false);
            JOptionPane.showMessageDialog(this, "nous ne pouvons pas nous connecter au serveur,\n Assurez-vous que le serveur fonctionne sur le port 1099", "Échec de connexion au serveur", JOptionPane.ERROR_MESSAGE);
            Common.logger.warning("nous ne pouvons pas nous connecter au serveur, assurez-vous que le serveur fonctionne sur le port 1099");
            Common.logger.warning(e.getMessage());
        }
    }

    private void checkDashboard(String mark) {
        if (Context.getDashboard() == null) {
            Dashboard dashboard = new Dashboard(mark);
            Context.setDashboard(dashboard);
        } else if (!Context.getDashboard().getMark().equals(mark)) {
            Context.getDashboard().setMark("X");
        }
    }
}
