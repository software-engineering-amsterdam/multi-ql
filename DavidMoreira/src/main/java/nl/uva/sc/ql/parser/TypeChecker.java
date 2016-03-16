package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.errorwarning.CyclicError;
import nl.uva.sc.ql.errorwarning.MessagesHandler;
import nl.uva.sc.ql.errorwarning.QuestionWarning;
import nl.uva.sc.ql.errorwarning.TypeError;
import nl.uva.sc.ql.errorwarning.VariableError;
import nl.uva.sc.ql.parser.ast.AdditionNode;
import nl.uva.sc.ql.parser.ast.AndNode;
import nl.uva.sc.ql.parser.ast.AssignedQuestionNode;
import nl.uva.sc.ql.parser.ast.BlockNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.DifferentNode;
import nl.uva.sc.ql.parser.ast.DivisionNode;
import nl.uva.sc.ql.parser.ast.EqualsNode;
import nl.uva.sc.ql.parser.ast.ExpressionNode;
import nl.uva.sc.ql.parser.ast.FormNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.GreatEqualsThanNode;
import nl.uva.sc.ql.parser.ast.GreatThanNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.IntegerNode;
import nl.uva.sc.ql.parser.ast.LessEqualsThanNode;
import nl.uva.sc.ql.parser.ast.LessThanNode;
import nl.uva.sc.ql.parser.ast.ListStatementsNode;
import nl.uva.sc.ql.parser.ast.MultiplicationNode;
import nl.uva.sc.ql.parser.ast.NotNode;
import nl.uva.sc.ql.parser.ast.OrNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.SubtractionNode;
import nl.uva.sc.ql.parser.ast.QuestionNode;

public class TypeChecker implements Visitor {

	private SymbolTable symbolTable;
	private MessagesHandler handler;
	private CyclicDependency cyclic;
	
	public TypeChecker(SymbolTable symbolTable, MessagesHandler handler) {
		this.symbolTable = symbolTable;
		this.handler = handler;
		this.cyclic = new CyclicDependency();
	}

	@Override
	public void visit(AdditionNode additionNode) {
		ExpressionNode left = additionNode.getLeft();
		ExpressionNode right = additionNode.getRight();	
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+additionNode.getLine()+": bad operand types for binary operator '+' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}
	}

	@Override
	public void visit(AndNode andNode) {
		ExpressionNode left = andNode.getLeft();
		ExpressionNode right = andNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) || !left.getType().equals("boolean")){
			TypeError typeError = new TypeError("Line "+andNode.getLine()+": bad operand types for binary operator '&&' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}
	}

	@Override
	public void visit(AssignedQuestionNode assignedQuestionNode) {
		QuestionNode questionNode = assignedQuestionNode.getVariableNode();
		ExpressionNode expression = assignedQuestionNode.getExpression();		
				
		// initialize the cyclicDependency before the expression is visited
		cyclic.init(questionNode.getIdentifier());
		
		questionNode.accept(this);
		expression.accept(this);
		
		// since it visited the expression previously, the result of if it's cyclic is known
		if (cyclic.isCyclic()){
			CyclicError cyclicError = new CyclicError("Line "+assignedQuestionNode.getLine()+": cyclic dependency: "+questionNode.getIdentifier());
			handler.addError(cyclicError);
		}
		
		if (!questionNode.getType().equals(expression.getType())){
			TypeError typeError = new TypeError("Line "+assignedQuestionNode.getLine()+": incompatible types: "+questionNode.getType()+" cannot be converted to "+expression.getType());
			handler.addError(typeError);
		}
	}

	@Override
	public void visit(BlockNode blockNode) {
		blockNode.getListStatements().accept(this);
	}

	@Override
	public void visit(BooleanNode booleanNode) {
	}

	@Override
	public void visit(ConditionBlockNode conditionBlockNode) {
		ExpressionNode expression = conditionBlockNode.getExpression();
		BlockNode block = conditionBlockNode.getBlock();
		
		expression.accept(this);
		block.accept(this);
		
		if(!expression.getType().equals("boolean")){
			TypeError typeError = new TypeError("Line "+expression.getLine()+": incompatible types: "+expression.getType()+" cannot be converted to boolean");
			handler.addError(typeError);
		}
	}

	@Override
	public void visit(DifferentNode differentNode) {
		ExpressionNode left = differentNode.getLeft();
		ExpressionNode right = differentNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType())){
			TypeError typeError = new TypeError("Line "+differentNode.getLine()+": bad operand types for binary operator '!=' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}	
	}

	@Override
	public void visit(DivisionNode divisionNode) {
		ExpressionNode left = divisionNode.getLeft();
		ExpressionNode right = divisionNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError exception = new TypeError("Line "+divisionNode.getLine()+": bad operand types for binary operator '/' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(exception);
		}	
	}

	@Override
	public void visit(EqualsNode equalsNode) {
		ExpressionNode left = equalsNode.getLeft();
		ExpressionNode right = equalsNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType())){
			TypeError typeError = new TypeError("Line "+equalsNode.getLine()+": bad operand types for binary operator '==' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}		
	}

	@Override
	public void visit(FormNode formNode) {
		formNode.getBlock().accept(this);
	}

	@Override
	public void visit(GreatEqualsThanNode greatEqualsThanNode) {
		ExpressionNode left = greatEqualsThanNode.getLeft();
		ExpressionNode right = greatEqualsThanNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+greatEqualsThanNode.getLine()+": bad operand types for binary operator '<=' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}		
	}

	@Override
	public void visit(GreatThanNode greatThanNode) {
		ExpressionNode left = greatThanNode.getLeft();
		ExpressionNode right = greatThanNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+greatThanNode.getLine()+": bad operand types for binary operator '<' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}	
	}

	@Override
	public void visit(IdentifierNode identifierNode) {
		String identifier = identifierNode.getName();
		QuestionNode questionNode = symbolTable.lookup(identifier);
		
		if (questionNode == null) {
			VariableError variableError = new VariableError("Assigning undefined variable "+identifier+". Line "+identifierNode.getLine());
	    	handler.addError(variableError);
	    } else {
	    	identifierNode.setType(questionNode.getType());
	    }
		
		cyclic.analyseIfCycle(identifier);
	}

	@Override
	public void visit(IfNode ifNode) {
		for (ConditionBlockNode cbn : ifNode.getConditions()){
			cbn.accept(this);
		}
		
		BlockNode bn = ifNode.getElseBlock();
		if(bn != null) { bn.accept(this); }
	}

	@Override
	public void visit(IntegerNode integerNode) {		
	}

	@Override
	public void visit(LessEqualsThanNode lessEqualsThanNode) {
		ExpressionNode left = lessEqualsThanNode.getLeft();
		ExpressionNode right = lessEqualsThanNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+lessEqualsThanNode.getLine()+": bad operand types for binary operator '>=' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}		
	}

	@Override
	public void visit(LessThanNode lessThanNode) {
		ExpressionNode left = lessThanNode.getLeft();
		ExpressionNode right = lessThanNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+lessThanNode.getLine()+": bad operand types for binary operator '>' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}		
	}

	@Override
	public void visit(ListStatementsNode listStatementsNode) {
		for (StatementNode s : listStatementsNode.getListStatements()){
			s.accept(this);
		}
	}

	@Override
	public void visit(MultiplicationNode multiplicationNode) {
		ExpressionNode left = multiplicationNode.getLeft();
		ExpressionNode right = multiplicationNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) && !left.getType().equals("int")){
			TypeError exception = new TypeError("Line "+multiplicationNode.getLine()+": bad operand types for binary operator '*' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(exception);
		}		
	}

	@Override
	public void visit(NotNode notNode) {
		ExpressionNode expression = notNode.getExpression();
		
		expression.accept(this);
		
		if(!notNode.getType().equals("boolean")){
			TypeError typeError = new TypeError("Line "+notNode.getLine()+": bad operand type "+notNode.getType()+" for unitary operator '!'");
        	handler.addError(typeError);
		}
	}

	@Override
	public void visit(OrNode orNode) {
		ExpressionNode left = orNode.getLeft();
		ExpressionNode right = orNode.getRight();
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) || !left.getType().equals("boolean")){
			TypeError typeError = new TypeError("Line "+orNode.getLine()+": bad operand types for binary operator '||' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}		
	}

	@Override
	public void visit(QuestionNode questionNode) {
	    String name = questionNode.getIdentifier();
	    String question = questionNode.getQuestion();
	    // String type = variableNode.getType();
		
	    // TODO: duplicate question declarations with different types
		// VariableNode otherVariableNode = symbolTable.lookup(name);
		if (symbolTable.lookup(name) != null) {
			VariableError variableError = new VariableError("Already defined variable: "+name+". Line "+questionNode.getLine());
	        handler.addError(variableError);
	    }
	    
	    if (symbolTable.lookup(question) != null) {
	    	QuestionWarning questionWarning = new QuestionWarning("Already defined question: "+question+". Line "+questionNode.getLine());
			handler.addWarning(questionWarning);
	    }
	    
	    questionNode.addThisToSymbolTable(symbolTable);
	}

	@Override
	public void visit(StringNode stringNode) {		
	}

	@Override
	public void visit(SubtractionNode subtractionNode) {
		ExpressionNode left = subtractionNode.getLeft();
		ExpressionNode right = subtractionNode.getRight();	
		
		left.accept(this);
		right.accept(this);
		
		if(!left.getType().equals(right.getType()) || !left.getType().equals("int")){
			TypeError typeError = new TypeError("Line "+subtractionNode.getLine()+": bad operand types for binary operator '-' \nfirst type: "+left.getType()+"\nsecond type: "+right.getType());
			handler.addError(typeError);
		}
	}
}
