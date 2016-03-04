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
	
	private boolean questionDone;
	private JComponent component = null;
	
	public Question(State state, String question, String identifier, ExpressionNode expression, boolean editable){
		this.state = state;
		this.question = question;
		this.identifier = identifier;
		this.expression = expression;
		this.editable = editable;
		this.questionDone = false;
		this.component = createComponentWithValue();
		
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
	
	public boolean isQuestionDone() {
		return this.questionDone;
	}

	public void setQuestionDone(boolean doneQuestion) {
		this.questionDone = doneQuestion;
	}
	
	public abstract JComponent createComponentWithValue();
	
	@Override
	public abstract void update();
	
	// TODO: maybe i don't need this
	@Override
	public boolean runGui(){
		if(!isQuestionDone()){
			add(new JLabel(question));
			add(component);
			setQuestionDone(true);
		}
		
		return true;
	}
		
	@Override
	public String toString(){
		return getClass().getSimpleName()+": "+getQuestion();
	}
}
