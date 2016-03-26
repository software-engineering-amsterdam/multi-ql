package nl.nicasso.ql.gui.questionFields;

import java.awt.Component;

import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.Label;

// @TODO CHANGE TO INTERFACE
public abstract class QuestionField {
		
	public QuestionField() {
		//throw new AssertionError("QuestionField");
	}
	
	public void setValue(Value value) {
		throw new AssertionError("QuestionField setValue");
	}
	
	public Value getValue() {
		throw new AssertionError("QuestionField getValue");
	}
	
	public Component getField() {
		throw new AssertionError("QuestionField getField");
	}
	
	public boolean equalValues(Value value) {
		throw new AssertionError("QuestionField equalValues");
	}
	
	public void setFeedbackLabel(Label label) {
		throw new AssertionError("QuestionField setFeedbackLabel");
	}
	
}