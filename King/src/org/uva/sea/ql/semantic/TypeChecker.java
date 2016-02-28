package org.uva.sea.ql.semantic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;
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
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.expr.type.UnknownType;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;
import org.uva.sea.ql.ast.expr.unary.UnaryExpression;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;

public class TypeChecker implements QLNodeVisitor{
	SymbolTable symTable;
	Result result;
	List<String> lableNames = new ArrayList<>();
	public TypeChecker(Form form) {
		result = new Result();
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
	@Override
	public Type visit(VarDeclaration node) {
		return null;
	}
	@Override
	public Type visit(Form form) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Type visit(VarIdentifier varId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type visit(IFblock statement) {
		for (Question ibq : statement.getBody().getQuestions()) {
			ibq.accept(this);
		}
		
		
		return statement.getCondition().accept(this);
		
	}
	@Override
	public Type visit(Question question) {
		String lableName = question.getText();
		String variableName = question.getVariableId().getIdentifier().getName();
		Type type = question.getVariableId().getType();
		if(checkDuplicateDeclaration(variableName,type)){
			String msg = "The variable '" + variableName + "' has been declared multiple time with different type";
			result.addError(msg);
		}else{
			symTable.add(variableName,type );
		}
		
		if(checkDuplicateLables(lableName)){
			String msg = "The lable '" + lableName + "' has been used more than once";
			
			result.addWarning(msg);
		}else{
			lableNames.add(lableName);
		}
		
		return new UnknownType();
		
	}
	
	private Boolean checkDuplicateDeclaration(String variableName,Type type) {
		boolean isDuplicateVariable = false;
		
		if (symTable.contains(variableName)) {
			if(type.equals(symTable.lookupType(variableName)) == false){
				isDuplicateVariable = true;
			}
			
		}
		return isDuplicateVariable;
	}
	
	private Boolean checkDuplicateLables(String lableName) {
		boolean isDuplicatelableName = false;
		
		if (lableNames.contains(lableName)) {
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

	private Boolean checkConditionExpr(String e, Type type) {
		boolean isConditionBoolean = false;
		if(!symTable.lookupType(e).equals(type)){
			isConditionBoolean = true;
		}
		return isConditionBoolean;
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
		Type expectedtype = e1;
		
		if(!checkExprEquality(e1, e2)){
			expectedtype = new UnknownType();
			String msg ="condition must be of the same type";
			result.addError(msg);
		}
		return expectedtype;
	}
	
	private Type checkUnaryExpression(UnaryExpression ue) {
		Type expectedType = new BooleanType();
		Type e =  ue.getExpression().accept(this);
		if(!checkExprEquality(expectedType, e)){
			expectedType = new UnknownType();
			String msg = "Condition is not of the type boolean";
			result.addError(msg);
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
		return intLiteral.getType();
	}
	
	@Override
	public Type visit(BooleanLiteral boolLiteral) {
		return boolLiteral.getType();
	}
	
	@Override
	public Type visit(StringLiteral stringLiteral) {
		return stringLiteral.getType();
	}
	
	@Override
	public Type visit(MoneyLiteral moneyLiteral) {
		return moneyLiteral.getType();
	}
	
	@Override
	public Type visit(Literal<?> literal) {
		return null;
	}
	
	@Override
	public Type visit(LiteralExpression literalExpression) {
		return literalExpression.getLiteral().accept(this);
	}
	
	@Override
	public Type visit(VarExpr varExpr) {
		Type expectedType = new BooleanType();
		String idName = varExpr.getIdentifier().getName();
		if(checkConditionExpr(idName, expectedType)){
			expectedType = new UnknownType();
			String msg = idName+" condition is not of the type boolean";
			result.addError(msg);
		}
		return expectedType;
		
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
	    //System.out.println(ast);
	    TypeChecker typeChecker = new TypeChecker(ast);
	    typeChecker.result.print();
		
	}
	
	

}
