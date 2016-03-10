package nl.uva.sc.ql.gui.form;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.uva.sc.ql.gui.State;
import nl.uva.sc.ql.parser.ast.ExpressionNode;

public abstract class Question extends JPanel implements GuiInterface, Observer {

	private static final long serialVersionUID = 1L;
		
	private State state;
	private String question;
	private String identifier;
	private ExpressionNode expression;
	private boolean editable;
	
	private JComponent component = null;
	
	public Question(State state, String question, String identifier, ExpressionNode expression, boolean editable){
		this.state = state;
		this.question = question;
		this.identifier = identifier;
		this.expression = expression;
		this.editable = editable;
		this.component = createComponentWithValue();
		
		setVisible(false);
		state.registerObserver(this);
	}

	public State getState(){
		return this.state;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public ExpressionNode getExpression(){
		return this.expression;
	}

	public boolean isEditable(){
		return this.editable;
	}
	
	public JComponent getComponent(){
		return this.component;
	}
	
	public abstract JComponent createComponentWithValue();
	
	@Override
	public abstract void update();
	
	@Override
	public void createGui(){
		add(new JLabel(question));
		add(component);
	}
	
	@Override
	public void updateGui() {}
		
	@Override
	public String toString(){
		return getClass().getSimpleName()+": "+getQuestion();
	}
}