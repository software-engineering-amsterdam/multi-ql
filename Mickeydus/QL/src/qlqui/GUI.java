package qlqui;

/**
 *
 * @author Dominique
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GUI extends JFrame {

    public GUI() {

        initUI();
    }

    private void initUI() {
        
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                GUI ex = new GUI();
                ex.setVisible(true);
            }
        });
    }
}
