package org.uva.sea.ql.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.gui.panels.Panel;

public class QlGUI implements FormVisitor {
	
	private JFrame frame;
	private List<Panel> panels;
	
	public QlGUI() {
		panels = new ArrayList<Panel>();
		frame = new JFrame("Questionnaire");
		JLabel l=new JLabel("Questionnaire");
		frame.add(l);
		frame.setSize(400,400);
		//frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public List<Panel> getPanels() {
		return panels;
	}
	
	 public void addPanel(Panel panel) {
		 panels.add(panel);
	 }

	@Override
	public void visitForm(Form form) {
		form.getBlock().accept(this);
		frame.setVisible(true);
	}

	@Override
	public void visitBlock(Block block) {
		// TODO Auto-generated method stub
		System.out.println("Visiting block");
		
	}


}
