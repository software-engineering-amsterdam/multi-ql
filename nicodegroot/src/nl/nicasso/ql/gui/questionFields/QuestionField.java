package nl.nicasso.ql.gui.questionFields;

import java.awt.Component;

import javax.swing.JLabel;

import nl.nicasso.ql.gui.evaluator.values.Value;

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
	
	public void setFeedbackField(JLabel feedback) {
		//throw new AssertionError("setFeedbackField");
	}
	
}