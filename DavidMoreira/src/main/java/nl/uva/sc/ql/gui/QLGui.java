package nl.uva.sc.ql.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
//import javax.swing.JScrollPane;


import nl.uva.sc.ql.gui.form.Form;
import nl.uva.sc.ql.parser.ast.Node;

public class QLGui {
	
	private Node ast;	
	
	public QLGui(Node ast) {
		this.ast = ast;
	}
	
	public void start(){
    	CreateForm cf = new CreateForm();
    	Form form = cf.getForm(ast);
		
	    Container cp = form.getContentPane();
	    cp.setLayout(new GridLayout(10, 2, 5, 5));
	    
        System.out.println("Starting gui...");
        
    	form.runGui();
    	
    	System.out.println(form.toString());
        
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setTitle(form.getName());
		form.setSize(1050, 500);  // sets initial size frame
		form.setVisible(true);
	}
}
