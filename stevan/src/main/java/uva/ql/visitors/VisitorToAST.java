package uva.ql.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.Question;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.numbers.abstracts.Number;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Bool;

public class VisitorToAST extends QLBaseVisitor<Object> {

	private final Map<String, Variable> varStore = new HashMap<String, Variable>(0);

	@Override
	public Form visitForm( @NotNull QLParser.FormContext ctx ) {
		
		String name = ctx.varName().getText();
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Form form = new Form(name, line, column);
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			if (ctx.getChild(i) == ctx.block()) {
				
				Block block = (Block) ctx.getChild(i).accept(this);
				block.setParent(form);
				form.add(block);
			}
		}
		
		return form;
	}
	
	@Override
	public Block visitBlock( @NotNull QLParser.BlockContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Block block = new Block(null, line, column);
		
		for (int i=0; i<ctx.question().size(); i++) {
			
			Question question = (Question) ctx.question(i).accept(this);
			question.setParent(block);
			block.add(question);
		}

		for (int i=0; i<ctx.condition().size(); i++) {
			
			Condition ifStmnt = (Condition) ctx.condition(i).accept(this);
			ifStmnt.setParent(block);
			block.add(ifStmnt);
		}
		
		return block;
	}
	
	@Override
	public CondIfStatement visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		CondIfStatement ifStmnt = new CondIfStatement(null, line, column);
		
		for (int i=0; i<ctx.expression().size(); i++) {
			
			Expression exp = (Expression) ctx.expression(i).accept(this);
			exp.setParent(ifStmnt);
			ifStmnt.setExpression(exp);
		}
		
		for (int i=0; i<ctx.block().size(); i++) {
			
			Block block = (Block) ctx.block(i).accept(this);
			block.setParent(ifStmnt);
			ifStmnt.add(block);
		}

		return ifStmnt;
	}
	
	@Override 
	public Question visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		Question question = AST.newQuestion();
		Variable var = createVariable(ctx.varType().getText());

		question.addChild(var);
		question.setLabel(ctx.label().getText().substring(1, ctx.label().getText().length()-1));
		question.setLine(ctx.getStart().getLine());
		question.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		var.setName(ctx.varName().getText());
		var.setLine(ctx.varName().getStart().getLine());
		var.setColumn(ctx.varName().getStart().getCharPositionInLine() + 1);
		var.setParent(question);
		
		varStore.put(var.getName(), var);
		
		for ( int i=0; i<ctx.expression().size(); i++ ) {
		
			Expression exp = (Expression) ctx.expression(i).accept(this);
			exp.setParent(question);
			question.setExpression(exp);
		}

		return question;
	}
	
	@Override
	public Expression visitExpParentheses( @NotNull QLParser.ExpParenthesesContext ctx ) {
		
		return (Expression) ctx.expression().accept(this);
	}
	
	@Override
	public Variable visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		Variable var = varStore.get(ctx.getText());
		
		if( varStore.get(ctx.getText()) == null ) {
			
			if ( Boolean.parseBoolean(ctx.getText()) ) {
				Bool bool = AST.newVarBool();
				bool.setValue( Boolean.parseBoolean(ctx.getText()) );
				bool.setLine( ctx.varName().getStart().getLine() );
				bool.setColumn( ctx.varName().getStart().getCharPositionInLine() + 1 );
				return bool;
			} 
			else {
				var = AST.newVarGeneric();
				var.setName( ctx.getText() );
				var.setLine( ctx.varName().getStart().getLine() );
				var.setColumn( ctx.varName().getStart().getCharPositionInLine() + 1 );
			}			
		}
		
		return var;
	}
	
	@Override
	public Expression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		
		Expression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "*") {
			exp = AST.newExprMult();
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		else {
			exp = AST.newExprDiv();
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		
		visitExprChildren(ctx, exp);
		
		return exp;
	}
	
	@Override
	public Expression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {
		
		Expression exp = null;
		
		if (ctx.getChild(1).getText().equals("+")) {
			exp = AST.newExprAdd();
			/*AExpression lhs = (AExpression)ctx.getChild(0).accept(this);
			AExpression rhs = (AExpression)ctx.getChild(2).accept(this);
			return new Add(lhs, rhs);*/
			
			// AST should be immutable, no setters
			// The best is to add the parameters to the constructor...
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		else {
			exp = AST.newExprMinus();
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		
		visitExprChildren(ctx, exp);
		
		return exp;
	}
	
	@Override
	public Expression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		
		Expression exp = createExpEquality(ctx.getChild(1).getText().intern());
		exp.setLine(ctx.getStart().getLine());
		exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		visitExprChildren(ctx, exp);
		
		return exp;
	}
	
	@Override
	public Expression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {
		Expression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "&&") {
			exp = AST.newExprAnd();
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		else {
			exp = AST.newExprOr();
			exp.setLine(ctx.getStart().getLine());
			exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		
		visitExprChildren(ctx, exp);
		
		return exp;
	}
	
	@Override
	public Number visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		
		Number var = null;
		
		if(!ctx.DIGIT().isEmpty()) {
			var = AST.newNumInt();
			var.setValue(ctx.getText());
			var.setLine(ctx.getStart().getLine());
			var.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		else {
			var = AST.newNumDouble();
			var.setValue(ctx.getText());
			var.setLine(ctx.getStart().getLine());
			var.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		}
		
		return var;
	}
	
	@Override
	public Expression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		
		Expression expPar = AST.newExprNot();
		Expression exp = (Expression) ctx.getChild(1).accept(this);
		
		expPar.setLine(ctx.getStart().getLine());
		expPar.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		expPar.setLeftNode(exp);
		exp.setParent(expPar);
		
		return expPar;
	}
	
	private Variable createVariable(String type) {
		
		Variable var = VAR.get(type.toUpperCase());
		var = var != null ? var : AST.newVarGeneric();
		
		return var;
	}
	
	private Expression createExpEquality(String type) {

		Expression exp = EXP.get(type);
		exp = exp != null ? exp : AST.newExpEql();
		
		return exp;
	}
	
	private void visitExprChildren(QLParser.ExpressionContext ctx, Expression exp) {
		
		Expression leftExp = (Expression) ctx.getChild(0).accept(this);
		Expression rightExp = (Expression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
	}
	
	private List<Integer> getLineColumn(Token token) {
		
		List<Integer> list = new ArrayList<Integer>(0);
		list.add(token.getLine());
		list.add(token.getCharPositionInLine() + 1);
		
		return list;
	}
}
