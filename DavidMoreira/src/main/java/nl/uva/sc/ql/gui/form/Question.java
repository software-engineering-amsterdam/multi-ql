package nl.uva.sc.ql.gui.form;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.ast.Node;

public abstract class Question extends JPanel implements GuiInterface, Observer {

	private static final long serialVersionUID = 1L;
		
	private String question;
	private boolean questionDone;
	private Node node;
	private boolean editable;
	private JComponent component = null;
	
	public Question(String question, Node node, boolean editable){
		this.question = question;
		this.node = node;
		this.questionDone = false;
		this.editable = editable;
		node.registerObserver(this);
	}

	public String getQuestion() {
		return this.question;
	}

	public boolean isQuestionDone() {
		return this.questionDone;
	}

	public void setQuestionDone(boolean doneQuestion) {
		this.questionDone = doneQuestion;
	}
	
	public String getValuetoString() {
		// try if the node has a value
		try {
			// update value of the node and check if succeeded
			if(this.node.eval()){
				return this.node.getValue().toString();
			}
		} catch(NoValueException e){}
		
		return null;
	}
	
	public void setValue(Object value){
		this.node.setValue(value);
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
	public boolean runGui(){
		if(!isQuestionDone()){
			add(new JLabel(this.question));
			component = createComponentWithValue();
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
