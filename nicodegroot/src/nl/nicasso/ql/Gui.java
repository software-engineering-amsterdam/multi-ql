package nl.nicasso.ql;

import java.awt.GridLayout;

import javax.swing.*;

public class Gui {
	
	private JFrame frame;

	public Gui() {
		initGui();
	}
	
	private void initGui() {
		frame = new JFrame("Questionnaire");
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(0, 1));
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addQuestion("SCHIJT!");
        
        frame.setVisible(true);
	}
	
	public void addQuestion(String label) {
		JLabel l = new JLabel(label);
		frame.add(l);
		
		JTextField txt=new JTextField("KAKA");
		//txt.setText("Anurag jain(csanuragjain)");
		frame.add(txt);
	}
	
}
