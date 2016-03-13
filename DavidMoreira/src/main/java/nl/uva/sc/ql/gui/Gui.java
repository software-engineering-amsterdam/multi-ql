package nl.uva.sc.ql.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import nl.uva.sc.ql.gui.form.Form;
import nl.uva.sc.ql.gui.state.State;
import nl.uva.sc.ql.parser.ast.Node;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;

	private Node ast;
	private State state;
		
	public Gui(Node ast) {
		this.ast = ast;
		this.state = new State();
	}
	
	public void start(){
    	CreateQL createQL = new CreateQL(state);
    	Form form = createQL.getForm(ast);
    	form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
    	
    	form.createGui();
    	form.updateGui();
    	     
    	JScrollPane scrollPanel = new JScrollPane(form);
    	add(scrollPanel);
    	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(form.getName());
		setSize(560, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}