package nl.uva.sc.ql.gui.form;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.ast.Node;

public abstract class Question extends JPanel implements QLGuiForm {

	private static final long serialVersionUID = 1L;
		
	private String question;
	private boolean questionDone;
	private Node node;
	private boolean editable;
	
	public Question(String question, Node node, boolean editable){
		this.question = question;
		this.node = node;
		this.questionDone = false;
		this.editable = editable;
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
	
	public abstract JComponent getComponent(boolean editable, String valueText);
	
	public boolean createGui(){
		if(!isQuestionDone()){
			JComponent component;
			add(new JLabel(this.question));
			component = getComponent(editable, getValuetoString());
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
