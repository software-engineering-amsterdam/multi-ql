package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.exceptions.CompilerException;
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
import nl.uva.sc.ql.parser.ast.Visitor;


public class Evaluator implements Visitor {
    
	// local variable to check when a If block was evaluated
	private boolean evaluatedIfBlock;
	

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
	}

	@Override
	public void visit(MathExpressionNode node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
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
	}

	@Override
	public void visit(LogicNode node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		
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
		
		if ((boolean) left.getValue()){
			evaluatedIfBlock = true;
			node.getRight().accept(this);
		}
	}

	@Override
	public void visit(BooleanNode node) {}

	@Override
	public void visit(StringNode node) {}

	@Override
	public void visit(MoneyNode node) {}

}
