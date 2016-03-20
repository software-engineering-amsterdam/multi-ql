package nl.uva.ql.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.gui.panel.Panel;

public class QLFrame extends JFrame{
	
	private Panel panel;
	
	public QLFrame(){
		setMinimumSize(new Dimension(400, 400));
		setLayout(new MigLayout("", "[push, grow, fill]", ""));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void update(Evaluator evaluator, Identifier identifier) {
		panel.update(evaluator, identifier);
		this.pack();
	}
	
	public void addToFrame(Panel panel) {
		this.panel = panel;
		this.add(panel);
		this.pack();
	}

}
