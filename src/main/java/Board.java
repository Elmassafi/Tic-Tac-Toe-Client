import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anas EL MASSAFI
 */
public final class Board extends JPanel {

    private String mark ;
    private List<JButton> buttons = new ArrayList<>();

    /**
     * Create the panel.
     *
     * @param mark accepter deux valeurs X ou O
     */
    public Board(String mark) {
        this.mark = mark;
        initComponents();
    }

    void initComponents() {
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
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.addActionListener(this::actionPerformed);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    private void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        b.setText(mark);
        b.setFont(new Font("Arial", Font.PLAIN, 40));
        b.setEnabled(false);
        System.out.println(buttons.indexOf(b));
    }
}
