package nl.uva.sc.ql.gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import nl.uva.sc.ql.parser.ast.Node;

public class QLGui extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Node ast;
	
	public QLGui(Node ast) {
		this.ast = ast;
	}
	
	public void start(){
	    Container cp = getContentPane();
	    cp.setLayout(new GridLayout(10, 2, 5, 5));
	    
        System.out.println("Starting evaluator...");
        Evaluator evaluator = new Evaluator(this);
        
        evaluator.test();
        ast.accept(evaluator);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("QL");
		setSize(1050, 500);  // sets initial size frame
		setVisible(true);
	}
}
