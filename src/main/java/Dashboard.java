import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author Anas el massafi
 */
public class Dashboard extends JFrame implements ActionListener {

    static final JTextField field = new JTextField(22);
    static final JTextArea area = new JTextArea(7, 46);
    //Add game buttons*JPanel c = new JPanel();
    static final JPanel c = new JPanel();
    static final JButton b1 = new JButton();
    static final JButton b2 = new JButton();
    static final JButton b3 = new JButton();
    static final JButton b4 = new JButton();
    static final JButton b5 = new JButton();
    static final JButton b6 = new JButton();
    static final JButton b7 = new JButton();
    static final JButton b8 = new JButton();
    static final JButton b9 = new JButton();
    private static final JLabel message = new JLabel("Joined at: ");
    private static final JLabel welcome = new JLabel();
    private static final JButton post = new JButton("Envoyer");
    private static final JButton disconnect = new JButton("Deconnecter");
    static Boolean posted = false;
    static Boolean clicked = false;
    static Boolean disconnected = false;
    //Declare class variables
    private static final int count = 0;
    private static final boolean c1 = true;
    private static final boolean c2 = true;
    private static final boolean c3 = true;
    private static final boolean c4 = true;
    private static final boolean c5 = true;
    private static final boolean c6 = true;
    private static final boolean c7 = true;
    private static final boolean c8 = true;
    private static final boolean c9 = true;
    private static final char[] aMoves = new char[10];
    private static final boolean check = true;
    private Dashboard() {

        super(" Client");
        setUpPanel(this);
        setAddEventListeners();
        setLocation(10, 10);
        setSize(600, 600);
    }

    public static void win(String Result) {
        JOptionPane.showMessageDialog(c, new JLabel("<html><div style='text-align: center;'>" + "Congratulations!<br>The game is a tie!" +
                "</div></html>", JLabel.CENTER), (Result), JOptionPane.PLAIN_MESSAGE);

    }

    public static Dashboard getInstance() {
        return DashboardHolder.INSTANCE;
    }

    private void setUpPanel(Dashboard dashboard) {
        JPanel panel_north = new JPanel();
        JPanel panel_south = new JPanel(new GridLayout(2, 0));
        JPanel panel_chat = new JPanel(new FlowLayout());
        panel_chat.add(field);
        panel_chat.add(post);

        panel_north.add(welcome);
        panel_north.add(message);
        JPanel panel_middle = new JPanel(new GridLayout(3, 3));
        //panel_middle.add(field);
        //panel_middle.add(post);

        dashboard.setResizable(false);
        dashboard.setVisible(false);
        dashboard.showTime();

        area.setEditable(false);
        addScrollPanelToPanel(panel_south);

        dashboard.setUserTextColor();
        setComponentColors(panel_middle, panel_north);

        dashboard.setBounds(400, 400, 750, 500);
        addEverythingToMainPanel(dashboard, panel_south, panel_north, panel_middle, panel_chat);
        dashboard.setVisible(false);
        area.setBackground(Color.WHITE);
        dashboard.setVisible(true);
        //Add COlor
        b1.setBackground(Color.gray);
        b2.setBackground(Color.gray);
        b3.setBackground(Color.gray);
        b4.setBackground(Color.gray);
        b5.setBackground(Color.gray);
        b6.setBackground(Color.gray);
        b7.setBackground(Color.gray);
        b8.setBackground(Color.gray);
        b9.setBackground(Color.gray);
        //Add buttons to panels
        panel_middle.add(b1);
        panel_middle.add(b2);
        panel_middle.add(b3);
        panel_middle.add(b4);
        panel_middle.add(b5);
        panel_middle.add(b6);
        panel_middle.add(b7);
        panel_middle.add(b8);
        panel_middle.add(b9);
    }

    private void addScrollPanelToPanel(JPanel panel_south) {
        JScrollPane scrollPane = new JScrollPane(area);
        DefaultCaret caret = (DefaultCaret) area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel_south.add(scrollPane);
    }

    private void setComponentColors(JPanel panel_middle, JPanel panel_north) {
        post.setBackground(Color.black);
        post.setForeground(Color.WHITE);
        disconnect.setBackground(Color.WHITE);
        disconnect.setForeground(Color.GRAY);
        panel_north.setBackground(Color.white);
        panel_middle.setBackground(Color.WHITE);
    }

    private void addEverythingToMainPanel(Dashboard dashboard, JPanel panel_south, JPanel panel_north, JPanel panel_middle, JPanel panel_chat) {

        c.setBackground(Color.WHITE);
        c.setBorder(new EmptyBorder(5, 5, 5, 5));
        c.setLayout(new BorderLayout(1, 1));
        dashboard.setContentPane(c);
        panel_middle.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        panel_south.add(panel_chat);

        c.add(panel_north, BorderLayout.AFTER_LAST_LINE);
        c.add(panel_middle, BorderLayout.CENTER);
        c.add(panel_south, BorderLayout.BEFORE_FIRST_LINE);

        panel_north.add(disconnect);
    }

    private void setAddEventListeners() {
        field.addActionListener(this);
        post.addActionListener(this);
        disconnect.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
    }

    private int generateRandomRGBValue() {
        Random rand = new Random();
        return rand.nextInt(255);
    }

    private void setUserTextColor() {
        area.setForeground(new Color(generateRandomRGBValue(), generateRandomRGBValue(), generateRandomRGBValue()));
    }

    private void showTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        message.setText("Joined: " + dateFormat.format(cal.getTime()));
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        source.getName();
        if (source == post) {
            posted = true;
        } else if (source == disconnect) {
            disconnected = true;
            /*            Accueil.AccueilHolder.accueil.setVisible(true);*/
            dispose();
        } else {
            if (source == b1) {
                field.setText("b1");
            } else if (source == b2) {
                field.setText("b2");
            } else if (source == b3) {
                field.setText("b3");
            } else if (source == b4) {
                field.setText("b4");
            } else if (source == b5) {
                field.setText("b5");
            } else if (source == b6) {
                field.setText("b6");
            } else if (source == b7) {
                field.setText("b7");
            } else if (source == b8) {
                field.setText("b8");
            } else if (source == b9) {
                field.setText("b9");
            }
            clicked = true;
        }
    }

    public static class DashboardHolder {
        public static final Dashboard INSTANCE = new Dashboard();
    }
}
