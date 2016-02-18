package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author sander
 */
public class SimpleGUI extends JFrame {

    public SimpleGUI() {
        initUI();
    }

    private void initUI() {
        setTitle("Simple Title");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SimpleGUI ex = new SimpleGUI();
            ex.setVisible(true);
        });
    }
}
