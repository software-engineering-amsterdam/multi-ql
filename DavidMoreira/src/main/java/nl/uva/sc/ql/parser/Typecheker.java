package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.exceptions.CompilerException;
import nl.uva.sc.ql.exceptions.ExceptionHandling;
import nl.uva.sc.ql.exceptions.TypechecherException;
import nl.uva.sc.ql.parser.ast.AssignVariableNode;
import nl.uva.sc.ql.parser.ast.FormNode;
import nl.uva.sc.ql.parser.ast.IfElseNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.LogicNode;
import nl.uva.sc.ql.parser.ast.MathExpressionNode;
import nl.uva.sc.ql.parser.ast.Node;
import nl.uva.sc.ql.parser.ast.RelationalExpressionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.VariableNode;


public class Typecheker implements Visitor {

	@Override
	public void visit(FormNode node) {
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
		Node left = node.getLeft();
		Node right = node.getRight();
				
		String symbol = node.getSymbol();
		switch(symbol){
			case "!":
				if(left.getType().equals("boolean")){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand type "+left.getType()+" for unitary operator '!'");
		        	ExceptionHandling.getInstance().addError(exception);
				}
				break;
			case "<":
			case ">":
			case "<=":
			case ">=":
				if(left.getType() != right.getType() || (!left.getType().equals("money") && !left.getType().equals("int"))){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
				}
				break;
			case "==":
			case "!=":
				if(left.getType() != right.getType()){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
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
				if(left.getType() != right.getType() || (!left.getType().equals("money") && !left.getType().equals("int") && !left.getType().equals("String"))){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
				}
			case "-":
				if(left.getType() != right.getType() || (!left.getType().equals("money") && !left.getType().equals("int"))){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
				}
				break;
			case "*":
			case "/":
			case "%":
				if((!right.getType().equals("int") && !left.getType().equals("int")) || (left.getType().equals("int") && (!right.getType().equals("int") || !right.getType().equals("money"))) || (right.getType().equals("int") && (!left.getType().equals("int") || !left.getType().equals("money")))){
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
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
					TypechecherException exception = new TypechecherException("Line "+node.getLine()+": bad operand types for binary operator '"+symbol+"' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
		        	ExceptionHandling.getInstance().addError(exception);
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
		if(!left.getType().equals("boolean")){
			TypechecherException exception = new TypechecherException("Line "+left.getLine()+": incompatible types: "+left.getType()+" cannot be converted to boolean");
        	ExceptionHandling.getInstance().addError(exception);
		}
		
		node.getRight().accept(this);
	}

	@Override
	public void visit(AssignVariableNode node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);
	}

	@Override
	public void visit(VariableNode node) {}
	
}
