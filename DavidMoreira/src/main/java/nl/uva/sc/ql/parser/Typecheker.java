package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.exceptions.CompilerException;
import nl.uva.sc.ql.exceptions.ErrorHandling;
import nl.uva.sc.ql.parser.ast.AssignVariableNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.IfElseNode;
import nl.uva.sc.ql.parser.ast.MoneyNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.Visitor;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.LogicNode;
import nl.uva.sc.ql.parser.ast.MathExpressionNode;
import nl.uva.sc.ql.parser.ast.Node;
import nl.uva.sc.ql.parser.ast.RelationalExpressionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;


public class Typecheker implements Visitor {

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
				if(left.getType() != "boolean"){
		        	ErrorHandling.getInstance().addError("Line "+node.getLine()+": bad operand type "+left.getType()+" for unitary operator '!'");
				}
				break;
			case "<":
			case ">":
			case "<=":
			case ">=":
				if(left.getType() != right.getType() || !left.getType().equals("money")){
		        	ErrorHandling.getInstance().addError("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
				}
				break;
			case "==":
			case "!=":
				if(left.getType() != right.getType()){
		        	ErrorHandling.getInstance().addError("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
				}
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
			case "-":
			case "*":
			case "/":
			case "%":
				if(left.getType() != right.getType() || !left.getType().equals("money")){
		        	ErrorHandling.getInstance().addError("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
				}
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
			case "||":
				if(left.getType() != right.getType() || !left.getType().equals("boolean")){
		        	ErrorHandling.getInstance().addError("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
				}
				break;
			default:
				throw new CompilerException("missing logic symbol: "+symbol);
		}		
	}

	@Override
	public void visit(IfElseNode node) {
		node.getLeft().accept(this);
		
		Node right = node.getRight();
		if (right != null){
			right.accept(this);
		}		
	}
	
	@Override
	public void visit(IfNode node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);
	}

	@Override
	public void visit(ConditionBlockNode node) {
		Node left = node.getLeft();
		left.accept(this);
		if(left.getType() != "boolean"){
        	ErrorHandling.getInstance().addError("Line "+left.getLine()+": incompatible types: "+left.getType()+" cannot be converted to boolean");
		}
		
		node.getRight().accept(this);
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
	
}
