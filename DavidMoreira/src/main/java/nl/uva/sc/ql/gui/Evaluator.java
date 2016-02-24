package nl.uva.sc.ql.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import nl.uva.sc.ql.exceptions.CompilerException;
import nl.uva.sc.ql.exceptions.NoValueException;
import nl.uva.sc.ql.parser.Visitor;
import nl.uva.sc.ql.parser.ast.AssignVariableNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.IfElseNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.LogicNode;
import nl.uva.sc.ql.parser.ast.MathExpressionNode;
import nl.uva.sc.ql.parser.ast.MoneyNode;
import nl.uva.sc.ql.parser.ast.Node;
import nl.uva.sc.ql.parser.ast.RelationalExpressionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.VariableNode;


public class Evaluator implements Visitor {
	
	// local variable to check when an if block was evaluated
	private boolean evaluatedIfBlock;
	private JFrame jFrame;
	private JTextField tfInput, tfOutput;

	public Evaluator(JFrame jframe){
		this.jFrame = jframe;
	}
	
	public void test() {
		jFrame.add(new JLabel("Enter an Integer: "));
		tfInput = new JTextField(10);
		jFrame.add(tfInput);
		jFrame.add(new JLabel("The Accumulated Sum is: "));
		tfOutput = new JTextField(10);
		tfOutput.setEditable(false);  // read-only
		jFrame.add(tfOutput);
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
		Node left = node.getLeft();
		Node right = node.getRight();
		
		// update node values
		left.accept(this);
		right.accept(this);
		
		try {
			String symbol = node.getSymbol();
			switch(symbol){
				case "!":
					node.setValue(!(boolean) left.getValue());
					break;
				case "<":
					node.setValue((double) left.getValue() < (double) right.getValue());
					break;
				case ">":
					node.setValue((double) left.getValue() > (double) right.getValue());
					break;
				case "<=":
					node.setValue((double) left.getValue() <= (double) right.getValue());
					break;
				case ">=":
					node.setValue((double) left.getValue() >= (double) right.getValue());
					break;
				case "==":
					node.setValue(left.getValue().equals(right.getValue()));
					break;
				case "!=":
					node.setValue(!left.getValue().equals(right.getValue()));
					break;
				default:
					throw new CompilerException("missing relational symbol: "+symbol);
			}
		} catch (NoValueException e) {}
	}

	@Override
	public void visit(MathExpressionNode node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
		// update node values
		left.accept(this);
		right.accept(this);
		
		try {
			String symbol = node.getSymbol();
			switch(symbol){
				case "+":
					node.setValue((double) left.getValue() + (double) right.getValue());
					break;
				case "-":
					node.setValue((double) left.getValue() - (double) right.getValue());
					break;
				case "*":
					node.setValue((double) left.getValue() * (double) right.getValue());
					break;
				case "/":
					node.setValue((double) left.getValue() / (double) right.getValue());
					break;
				case "%":
					node.setValue((double) left.getValue() % (double) right.getValue());
					break;
				default:
					throw new CompilerException("missing math symbol: "+symbol);
			}
		} catch (NoValueException e) {}
	}

	@Override
	public void visit(LogicNode node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
		// update node values
		left.accept(this);
		right.accept(this);
		
		try {
			String symbol = node.getSymbol();
			switch(symbol){
				case "&&":
					node.setValue((boolean) left.getValue() && (boolean) right.getValue());
					break;
				case "||":
					node.setValue((boolean) left.getValue() || (boolean) right.getValue());
					break;
				default:
					throw new CompilerException("missing logic symbol: "+symbol);
			}
		} catch (NoValueException e) {}
	}
	
	@Override
	public void visit(IfElseNode node) {
		evaluatedIfBlock = false;
		
		node.getLeft().accept(this);
		
		Node right = node.getRight();
		if (!evaluatedIfBlock && right != null){
			right.accept(this);
		}		
	}

	@Override
	public void visit(IfNode node) {		
		node.getLeft().accept(this);
		if(evaluatedIfBlock){
			return;
		}
		
		node.getRight().accept(this);
		if(evaluatedIfBlock){
			return;
		}
	}

	@Override
	public void visit(ConditionBlockNode node) {
		Node left = node.getLeft();
		left.accept(this);
		
		try {
			if ((boolean) left.getValue()){
				evaluatedIfBlock = true;
				node.getRight().accept(this);
			}
		} catch (NoValueException e) {}
	}

	@Override
	public void visit(BooleanNode node) {}

	@Override
	public void visit(StringNode node) {}

	@Override
	public void visit(MoneyNode node) {}

	@Override
	public void visit(AssignVariableNode node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);		
	}

	@Override
	public void visit(VariableNode node) {
		if (!node.hasDoneQuestion()){
			JComponent component;
			jFrame.add(new JLabel(node.getQuestion()));
			component = node.getComponent();
			jFrame.add(component);
			node.doneQuestion();
		}
	}

}
