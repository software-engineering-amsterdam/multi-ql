package org.uva.sea.ql.semantic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.domain.Block;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.ast.domain.IFblock;
import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.domain.ReadOnlyQuestion;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
import org.uva.sea.ql.ast.expr.binary.BinaryExpression;
import org.uva.sea.ql.ast.expr.binary.Equal;
import org.uva.sea.ql.ast.expr.binary.GreaterOrEqual;
import org.uva.sea.ql.ast.expr.binary.GreaterThan;
import org.uva.sea.ql.ast.expr.binary.NotEqual;
import org.uva.sea.ql.ast.expr.binary.OR;
import org.uva.sea.ql.ast.expr.binary.SmallerOrEqual;
import org.uva.sea.ql.ast.expr.binary.SmallerThan;
import org.uva.sea.ql.ast.expr.literal.BooleanLiteral;
import org.uva.sea.ql.ast.expr.literal.IntegerLiteral;
import org.uva.sea.ql.ast.expr.literal.Literal;
import org.uva.sea.ql.ast.expr.literal.LiteralExpression;
import org.uva.sea.ql.ast.expr.literal.MoneyLiteral;
import org.uva.sea.ql.ast.expr.literal.StringLiteral;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.ast.expr.type.IntegerType;
import org.uva.sea.ql.ast.expr.type.MoneyType;
import org.uva.sea.ql.ast.expr.type.StringType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.expr.type.UnknownType;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;
import org.uva.sea.ql.ast.expr.unary.UnaryExpression;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;

public class TypeChecker implements QLNodeVisitor {
	private SymbolTable symTable;
	private Message messages;
	private List<String> lableNames = new ArrayList<>();
	
	public TypeChecker(Form form) {
		messages = new Message();
		symTable = new SymbolTable();
		form.accept(this);
	}
	
	@Override
	public Type visit(Block block) {
		
		for (Question q : block.getQuestions()) {
			q.accept(this);
		}
		
		for (IFblock ib: block.getStatements()) {
			ib.accept(this);
		}
		
		return new UnknownType();
		
	}
/** NEED TO CREATE VISIT FOR BINARY AND UNARY EXPRESSIONS 
 *  REARAGE THE CODE
 *  CYCLIC DEPENDENCIES
 *  MULTIPLE VISITORS MAY BE ABSTRACT ACCEPT METHOD
 * */
	
	@Override
	public Type visit(Form form) {
		form.getBody().accept(this);
		return new UnknownType();
	}
	
	@Override
	public Type visit(IFblock statement) {
		for (Question ibq : statement.getBody().getQuestions()) {
			ibq.accept(this);
		}
		
		if(!isBlockConditionBoolean(statement)){
			String msg =statement.toString()+" condition is not of the type boolean";
			messages.addError(msg);
		}
		
		return new UnknownType();
		
	}
	
	public boolean isBlockConditionBoolean(IFblock statement) {
		boolean isBoolean = false;
		Type exprType = statement.getCondition().accept(this);
		if(!checkExprEquality(exprType, new UnknownType())){
			isBoolean = true;
		}
		return isBoolean;
	}
	
	@Override
	public Type visit(Question question) {
		//normalQuestion.getVariableId().accept(this);
		if(hasDuplicateVarDeclaration(question)){
			String msg = question.getVariableId().toString()+" The question has been declared multiple time with different type";
			messages.addError(msg);
		}else{
			symTable.add(question.getVariableId().getIdentifier().getName(),question.getVariableId().getType() );
		}
		
		if(hasDuplicateLablesWarning(question)){
			String msg = "The question text '" + question.getText() + "' has been used more than once";
			messages.addWarning(msg);
		}else{
			lableNames.add(question.getText());
		}
		
		return new UnknownType();
	}
	
	@Override
	public Type visit(ReadOnlyQuestion computedQuestion) {
		if(hasDuplicateVarDeclaration(computedQuestion)){
			String msg = "The question '" + computedQuestion.getVariableId().toString()+ "' has been declared multiple time with different type";
			messages.addError(msg);
		}else{
			symTable.add(computedQuestion.getVariableId().getIdentifier().getName(),computedQuestion.getVariableId().getType() );
		}
		
		if(hasDuplicateLablesWarning(computedQuestion)){
			String msg = "The question text '" + computedQuestion.getText() + "' has been used more than once";
			messages.addWarning(msg);
		}else{
			lableNames.add(computedQuestion.getText());
		}
		
		if(!hasExpectedType(computedQuestion)){
			String msg = "The question variable '"+computedQuestion.getVariableId().toString()+"' in the computed question references to different type.";
			messages.addError(msg);
		}
		return new UnknownType();
	}

	private boolean hasExpectedType(ReadOnlyQuestion question) {
		Type expected = question.getVariableId().getType();
		Type expr	  = question.getExpression().accept(this);
		boolean isExpectedType = false;
		if(expr.equals(expected)){
			isExpectedType = true;
		}
		
		return isExpectedType;
	}
	
	private Boolean hasDuplicateVarDeclaration(Question question) {
		boolean isDuplicateVariable = false;
		String variableName = question.getVariableId().getIdentifier().getName();
		Type type = question.getVariableId().getType();
		if (symTable.contains(variableName)) {
			if(type.equals(symTable.lookupType(variableName)) == false){
				isDuplicateVariable = true;
			}
			
		}
		return isDuplicateVariable;
	}
	
	private Boolean hasDuplicateLablesWarning(Question question) {
		boolean isDuplicatelableName = false;
		
		if (lableNames.contains(question.getText())) {
				isDuplicatelableName = true;
			
		}
		return isDuplicatelableName;
	}

	
	@Override
	public Type visit(Equal eq) {
		return checkBinaryExpression(eq);
	}
	
	@Override
	public Type visit(OR or) {
		return checkBinaryExpression(or);
	}
	
	@Override
	public Type visit(GreaterOrEqual geq) {
		return checkBinaryExpression(geq);
	}
	@Override
	public Type visit(GreaterThan gt) {
		return checkBinaryExpression(gt);
		
	}
	@Override
	public Type visit(SmallerOrEqual leq) {
		return checkBinaryExpression(leq);
		
	}
	@Override
	public Type visit(SmallerThan lt) {
		return checkBinaryExpression(lt);
		
	}
	@Override
	public Type visit(AND and) {
		return checkBinaryExpression(and);
		
	}
	
	@Override
	public Type visit(NotEqual neq) {
		return checkBinaryExpression(neq);
		
	}
	@Override
	public Type visit(Negative neg) {
		return checkUnaryExpression(neg);
		
	}
	@Override
	public Type visit(NOT not) {
		return checkUnaryExpression(not);
	}
	
	@Override
	public Type visit(Positive pos) {
		return checkUnaryExpression(pos);
	}

	
	private Boolean checkExprEquality(Type e1, Type e2) {
		boolean isExprEqual = false;
		if(e1.equals(e2)){
			isExprEqual = true;		
		}
		return isExprEqual;
	}
	
	private Type checkBinaryExpression(BinaryExpression e) {
		Type e1 = e.getFirstExpression().accept(this);
		Type e2 = e.getSecondExpression().accept(this);
		Type typeToReturn = e1;
		//-------------
		if(checkExprEquality(e1, new UnknownType())){
			String msg = e.toString()+" reference to undefined question";
			messages.addError(msg);
		}
		
		if(!checkExprEquality(e1, e2)){
			typeToReturn = new UnknownType();
			String msg = e.toString()+" Binary expression must be of the same type";
			messages.addError(msg);
		}
		return typeToReturn;
	}
	
	private Type checkUnaryExpression(UnaryExpression ue) {
		Type expectedType = new BooleanType();
		Type e =  ue.getExpression().accept(this);
		if(!checkExprEquality(expectedType, e)){
			expectedType = new UnknownType();
			String msg = "The unary expression is not of the type boolean";
			messages.addError(msg);
		}
		return expectedType;
	}
	
	
	@Override
	public Type visit(Div div) {
		return checkBinaryExpression(div);
	}
	
	@Override
	public Type visit(Mul mul) {
		return checkBinaryExpression(mul);
	}

	@Override
	public Type visit(Add add) {
		return checkBinaryExpression(add);
	}
	
	@Override
	public Type visit(Sub sub) {
		return checkBinaryExpression(sub);
	}
	
	@Override
	public Type visit(IntegerLiteral intLiteral) {
		return new IntegerType();
	}
	
	@Override
	public Type visit(BooleanLiteral boolLiteral) {
		return new BooleanType();
	}
	
	@Override
	public Type visit(StringLiteral stringLiteral) {
		return new StringType();
	}
	
	@Override
	public Type visit(MoneyLiteral moneyLiteral) {
		return new MoneyType();
	}
	
	@Override
	public Type visit(Literal<?> literal) {
		return literal.getType();
	}
	
	@Override
	public Type visit(LiteralExpression literalExpression) {
		return literalExpression.getLiteral().accept(this);
	}
	
	@Override
	public Type visit(VarExpr varExpr) {
		Type typeToReturn = getVarExpressionType(varExpr);
		
		return typeToReturn;
		
	}

	private Type getVarExpressionType(VarExpr varExpr) {
		Type typeToReturn = new UnknownType();
		if(symTable.lookupType(varExpr.getIdentifier().getName()) != null){
			typeToReturn = symTable.lookupType(varExpr.getIdentifier().getName());
			varExpr.getIdentifier().setType(typeToReturn);
		}
		return typeToReturn;
	}
	
	public static void main(String[] args) throws IOException {
		//Loading the DSL script into the ANTLR stream.
	    ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());

	    //Passing the input to the lexer to create tokens
	    QLLexer lexer = new QLLexer(input);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    //Passing the tokens to the parser to create the parse tree. 
	    QLParser parser = new QLParser(tokens);
	   
	    FileContext fileContext = parser.file();
	    Form ast = fileContext.form(0).result;
	    TypeChecker typeChecker = new TypeChecker(ast);
	    typeChecker.messages.print();
		
	}
	

}
