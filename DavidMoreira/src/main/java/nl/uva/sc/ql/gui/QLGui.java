package nl.uva.sc.ql.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
//import javax.swing.JScrollPane;

import nl.uva.sc.ql.gui.form.Form;
import nl.uva.sc.ql.parser.ast.Node;

public class QLGui {
	
	private Node ast;
	private State state;
	
	//private JScrollPane scrollPanel;
	
	public QLGui(Node ast) {
		this.ast = ast;
		this.state = new State();
		//this.scrollPanel = new JScrollPane();
	}
	
	public void start(){
    	CreateForm cf = new CreateForm(state);
    	Form form = cf.getForm(ast);
		
	    Container cp = form.getContentPane();
	    cp.setLayout(new GridLayout(10, 100, 10, 10));
	    
        System.out.println("Starting gui...");
        
    	form.createGui();
    	form.updateGui();
    	       
    	// form.add(scrollPanel);
    	
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setTitle(form.getName());
		form.setSize(1050, 500);  // sets initial size frame
		form.setLocationRelativeTo(null);
		form.setVisible(true);
	}
}