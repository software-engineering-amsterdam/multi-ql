package nl.uva.sc.ql.gui;

import java.util.function.Function;

import nl.uva.sc.ql.gui.form.Form;
import nl.uva.sc.ql.gui.form.IfCondition;
import nl.uva.sc.ql.gui.form.IfElse;
import nl.uva.sc.ql.gui.form.Question;
import nl.uva.sc.ql.gui.form.QuestionBoolean;
import nl.uva.sc.ql.gui.form.QuestionInteger;
import nl.uva.sc.ql.gui.form.QuestionMoney;
import nl.uva.sc.ql.gui.form.QuestionString;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.ast.AssignVariableNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.FormNode;
import nl.uva.sc.ql.parser.ast.IfElseNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.LogicNode;
import nl.uva.sc.ql.parser.ast.MathExpressionNode;
import nl.uva.sc.ql.parser.ast.Node;
import nl.uva.sc.ql.parser.ast.RelationalExpressionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.VariableNode;

public class CreateForm implements Visitor {

	private Form form = null;
	private IfElse ifElse = null;
	private IfCondition ifCondition = null;
	
	public Form getForm(Node ast){
		ast.accept(this);
		return form;
	}

	@Override
	public void visit(FormNode node) {
		form = new Form(node.getName());	
		
		Node left = node.getLeft();
		if (left != null){ left.accept(this); }
	}

	@Override
	public void visit(StatementNode node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
		if (left != null){ left.accept(this); }
		if (right != null){ right.accept(this); }		
	}

	@Override
	public void visit(RelationalExpressionNode node) {		
	}

	@Override
	public void visit(MathExpressionNode node) {		
	}

	@Override
	public void visit(LogicNode node) {		
	}

	@Override
	public void visit(IfNode node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);
	}

	@Override
	public void visit(IfElseNode node) {
		ifElse = new IfElse();
		
		node.getLeft().accept(this);
		
		Node right = node.getRight();
		if (right != null){
			right.accept(this);
		}
		
		form.addIfElse(ifElse);
		ifElse = null;
	}

	@Override
	public void visit(ConditionBlockNode node) {
		ifCondition = new IfCondition(node.getLeft());
		
		node.getRight().accept(this);
		
		ifElse.addIfCondition(ifCondition);
		
		ifCondition = null;
	}

	@Override
	public void visit(AssignVariableNode node) {
		VariableNode variableNode = (VariableNode) node.getLeft();
		Node valueNode = node.getRight();

		auxVisitVariableNode(variableNode, valueNode, false);
	}

	@Override
	public void visit(VariableNode node) {
		auxVisitVariableNode(node, node, true);
	}
	
	private void auxVisitVariableNode(VariableNode variableNode, Node valueNode, boolean editable){
		String questionText = variableNode.getQuestion();
		String type = variableNode.getType();
		
		Function<String, Question> chooseNode = s -> { 
			Question question = null;
			switch(s) {
				case "String":
					question = new QuestionString(questionText, valueNode, editable);
					break;
				case "boolean":
					question = new QuestionBoolean(questionText, valueNode, editable);
					break;
				case "money":
					question = new QuestionMoney(questionText, valueNode, editable);
					break;
				case "int":
					question = new QuestionInteger(questionText, valueNode, editable);
					break;
			}
			return question;
			};
		
		Question question = chooseNode.apply(type);
		
		if(ifCondition != null){
			ifCondition.addQuestion(question);
		} else if(ifElse != null) {
			ifElse.addQuestion(question);
		} else {
			form.addQuestion(question);
		}
	}
}
