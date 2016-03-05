package uva.ql.visitors;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.antlr4.QLParser.ConditionContext;
import uva.ql.antlr4.QLParser.QuestionContext;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.expressions.ExpAdd;
import uva.ql.ast.expressions.ExpAnd;
import uva.ql.ast.expressions.ExpDivide;
import uva.ql.ast.expressions.ExpEqualTo;
import uva.ql.ast.expressions.ExpGreaterThen;
import uva.ql.ast.expressions.ExpGreaterThenOrEqualTo;
import uva.ql.ast.expressions.ExpLessThen;
import uva.ql.ast.expressions.ExpLessThenOrEqualTo;
import uva.ql.ast.expressions.ExpMinus;
import uva.ql.ast.expressions.ExpMultiply;
import uva.ql.ast.expressions.ExpNot;
import uva.ql.ast.expressions.ExpNotEqualTo;
import uva.ql.ast.expressions.ExpOr;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.questions.abstracts.Question;
import uva.ql.ast.values.ValueBool;
import uva.ql.ast.values.ValueDouble;
import uva.ql.ast.values.ValueInt;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.variables.VarBool;
import uva.ql.ast.variables.VarDate;
import uva.ql.ast.variables.VarDecimal;
import uva.ql.ast.variables.VarDouble;
import uva.ql.ast.variables.VarGeneric;
import uva.ql.ast.variables.VarInt;
import uva.ql.ast.variables.VarMoney;
import uva.ql.ast.variables.VarStr;
import uva.ql.ast.variables.abstracts.Variable;

public class VisitorToAST extends QLBaseVisitor<Object> {

	private final Map<String, Variable> varStore = new HashMap<String, Variable>(0);
	private static final Map<String, Variable> VARTYPE = new HashMap<String, Variable>(0);
	
	static {
		VARTYPE.put("BOOLEAN", new VarBool(null, null, 0, 0));
		VARTYPE.put("DAATE", new VarDate(null, null, 0, 0));
		VARTYPE.put("DECIMAL", new VarDecimal(null, null, 0, 0));
		VARTYPE.put("DOUBLE", new VarDouble(null, null, 0, 0));
		VARTYPE.put("INTEGER", new VarInt(null, null, 0, 0));
		VARTYPE.put("MONEY", new VarMoney(null, null, 0, 0));
		VARTYPE.put("STRING", new VarStr(null, null, 0, 0));
	}

	@Override
	public Form visitForm( @NotNull QLParser.FormContext ctx ) {
		
		String name = ctx.varName().getText();
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Form form = new Form(name, line, column);
		
		for(ParseTree child : ctx.children) {
			
			if (child == ctx.block()) {
				
				Block block = (Block) child.accept(this);
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
		
		for(QuestionContext q : ctx.question()) {
			
			Question question = (Question) q.accept(this);
			question.setParent(block);
			block.add(question);
		}

		for(ConditionContext c : ctx.condition()) {
			
			Condition ifStmnt = (Condition) c.accept(this);
			ifStmnt.setParent(block);
			block.add(ifStmnt);
		}
		
		return block;
	}
	
	@Override
	public Condition visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Expression exp = (Expression) ctx.expression().accept(this);
		Condition cond = null;
		
		if (ctx.block().size() > 1) {
			
			Block lhs = (Block) ctx.block(0).accept(this);
			Block rhs = (Block) ctx.block(1).accept(this);
			cond = new CondIfElseStatement(null, lhs, rhs, line, column);
			lhs.setParent(cond);
			rhs.setParent(cond);
		}
		else {
			
			Block lhs = (Block) ctx.block(0).accept(this);
			cond = new CondIfStatement(null, lhs, line, column);
			lhs.setParent(cond);
		}
		
		exp.setParent(cond);
		cond.setExpression(exp);
		
		return cond;
	}
	
	@Override 
	public Question visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		Question question = createQuestion(ctx);
		
		varStore.put(question.getVariable().getName(), question.getVariable());
		
		return question;
	}
	
	@Override
	public Expression visitExpParentheses( @NotNull QLParser.ExpParenthesesContext ctx ) {
		
		return (Expression) ctx.expression().accept(this);
	}
	
	@Override
	public Expression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		
		Expression exp = null;
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String operator = ctx.getChild(1).getText().intern();
		Expression lhs = (Expression) ctx.getChild(0).accept(this);
		Expression rhs = (Expression) ctx.getChild(2).accept(this);
		lhs.setParent(exp);
		rhs.setParent(exp);
		
		if (operator == "*") {
			
			exp = new ExpMultiply(null, lhs, rhs, line, column);
		}
		else {
			
			exp = new ExpDivide(null, lhs, rhs, line, column);
		}
		
		return exp;
	}
	
	@Override
	public Expression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {
		
		Expression exp = null;
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String operator = ctx.getChild(1).getText().intern();
		Expression lhs = (Expression) ctx.getChild(0).accept(this);
		Expression rhs = (Expression) ctx.getChild(2).accept(this);
		lhs.setParent(exp);
		rhs.setParent(exp);
		
		if (operator == "+") {
			
			exp = new ExpAdd(null, lhs, rhs, line, column);
		}
		else {
			
			exp = new ExpMinus(null, lhs, rhs, line, column);
		}
		
		return exp;
	}
	
	@Override
	public Expression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		
		Expression exp = null;
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String operator = ctx.getChild(1).getText().intern();
		Expression lhs = (Expression) ctx.getChild(0).accept(this);
		Expression rhs = (Expression) ctx.getChild(2).accept(this);
		lhs.setParent(exp);
		rhs.setParent(exp);
		
		if (operator == "<") {
			
			exp = new ExpLessThen(null, lhs, rhs, line, column);
		}
		else if (operator == ">") {
			
			exp = new ExpGreaterThen(null, lhs, rhs, line, column);
		}
		else if (operator == "<=") {
			
			exp = new ExpLessThenOrEqualTo(null, lhs, rhs, line, column);
		}
		else if (operator == ">=") {
			
			exp = new ExpGreaterThenOrEqualTo(null, lhs, rhs, line, column);
		}
		else if (operator == "!=") {
			
			exp = new ExpNotEqualTo(null, lhs, rhs, line, column);
		}
		else {
			
			exp = new ExpEqualTo(null, lhs, rhs, line, column);
		}
		
		return exp;
	}
	
	@Override
	public Expression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {

		Expression exp = null;
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String operator = ctx.getChild(1).getText().intern();
		Expression lhs = (Expression) ctx.getChild(0).accept(this);
		Expression rhs = (Expression) ctx.getChild(2).accept(this);
		lhs.setParent(exp);
		rhs.setParent(exp);
		
		if (operator == "&&") {
			
			exp = new ExpAnd(null, lhs, rhs, line, column);
		}
		else {
			
			exp = new ExpOr(null, lhs, rhs, line, column);
		}
		
		return exp;
	}
	
	@Override
	public Expression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		
		Expression exp = null;
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		Expression lhs = (Expression) ctx.getChild(1).accept(this);
		lhs.setParent(exp);
		
		exp = new ExpNot(null, lhs, line, column);
		
		return exp;
	}
	
	@Override
	public Variable visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Variable var = varStore.get(ctx.getText());
		
		if( var == null ) {
			
			var =  new VarGeneric(null, ctx.getText(), line, column);
		}
		
		return var;
	}
	
	@Override
	public Values visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		if(!ctx.DIGIT().isEmpty()) {
			
			return new ValueInt(null, ctx.getText(), line, column);
		}
		else {
			
			return new ValueDouble(null, ctx.getText(), line, column);
		}
	}
	
	@Override
	public Values visitExpBool( @NotNull QLParser.ExpBoolContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		return new ValueBool(null, ctx.getText().intern(), line, column);
	}
	
	private Variable createVariable(QuestionContext ctx) {
		
		Token token = ctx.varName().getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String varType = ctx.varType().getText();
		
		Variable var = VARTYPE.get(varType.toUpperCase());
		
		var = var != null ? var : new VarGeneric(null, null, 0, 0);

		var.setName(ctx.varName().getText());
		var.setLine(line);
		var.setColumn(column);
		
		return var;
	}
	
	private Question createQuestion(QuestionContext ctx) {
		
		Question question = null;
		Variable var = createVariable(ctx);
		var.setParent(question);
		
		String label = ctx.label().getText().substring(1, ctx.label().getText().length()-1);
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		if (ctx.expression() != null) {
			
			Expression exp = (Expression) ctx.expression().accept(this);
			question = new QuestionComputed(null, label, var, exp, line, column);
			exp.setParent(question);
		} 
		else {
			
			question = new QuestionVanilla(null, label, var, line, column);
		}
		
		return question;
	}
	
}
