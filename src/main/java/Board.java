import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anas EL MASSAFI
 */
public final class Board extends JPanel {

    private final String mark;
    public List<JButton> buttons = new ArrayList<>();

    /**
     * Create the panel.
     *
     * @param mark accepter deux valeurs X ou O
     */
    public Board(String mark) {
        this.mark = mark;
        setSize(200, 200);
        initComponents();
    }

    void initComponents() {
        this.setBackground(Configuration.Background_Color);
        System.out.println("We are giong good");
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton btn0 = createButton("");
            add(btn0);
            buttons.add(btn0);

        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Configuration.Writing_Color);
        button.setBackground(Configuration.Btn_Color);
        button.addActionListener(this::actionPerformed);
        Border line = new LineBorder(Configuration.Border_Color);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setPreferredSize(new Dimension(100, 100));
        button.setBorder(compound);
        return button;
    }

    private void waitForTurn() {

       /* for (JButton b : buttons) {
            b.addActionListener(a -> {
            });
        }*/
    }

    private void yourTurn() {
       /* for (JButton b : buttons) {
            b.addActionListener(this::actionPerformed);
        }*/
    }

    private void actionPerformed(ActionEvent e)  {
        System.out.println("this : "+mark);
        try {
            JButton b = (JButton) e.getSource();
            Context.getGame().playMove(mark, buttons.indexOf(b));
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }

    }

    public void newMove(String mark,int move) {
        JButton b = buttons.get(move);
        b.setText(mark);
        b.setFont(new Font("Arial", Font.PLAIN, 40));
        b.setEnabled(false);
        b.setBackground(Configuration.Btn_Color);
        yourTurn();
        System.out.println(buttons.indexOf(b));
    }

}
