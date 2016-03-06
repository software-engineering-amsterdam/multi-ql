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
		
		if (ctx.block().size() > 1) {
			
			Block blhs = (Block) ctx.block(0).accept(this);
			Block brhs = (Block) ctx.block(1).accept(this);
			Condition cond = new CondIfElseStatement(exp, blhs, brhs, line, column);
			blhs.setParent(cond);
			brhs.setParent(cond);
			return cond;
		}
		else {
			
			Block blhs = (Block) ctx.block(0).accept(this);
			Condition cond = new CondIfStatement(exp, blhs, line, column);
			blhs.setParent(cond);
			return cond;
		}
	}
	
	@Override 
	public Question visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		return createQuestion(ctx);
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
		
		switch (operator) {
			case "<":
				return new ExpLessThen(null, lhs, rhs, line, column);
			case ">":
				return new ExpGreaterThen(null, lhs, rhs, line, column);
			case "<=":
				return new ExpLessThenOrEqualTo(null, lhs, rhs, line, column);
			case ">=":
				return new ExpGreaterThenOrEqualTo(null, lhs, rhs, line, column);
			case "!=":
				return new ExpNotEqualTo(null, lhs, rhs, line, column);
			default:
				return new ExpEqualTo(null, lhs, rhs, line, column);
		}
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
		
		if( var == null) {
			
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
		String varName = ctx.varName().getText();
		
		switch (varType.toUpperCase()) {
			case "BOOLEAN":
				return new VarBool(null, varName, line, column);
			case "DATE":
				return new VarDate(null, varName, line, column);
			case "DECIMAL":
				return new VarDecimal(null, varName, line, column);
			case "DOUBLE":
				return new VarDouble(null, varName, line, column);
			case "INTEGER":
				return new VarInt(null, varName, line, column);
			case "MONEY":
				return new VarMoney(null, varName, line, column);
			case "STRING":
				return new VarStr(null, varName, line, column);
			default:
				return new VarGeneric(null, varName, line, column);
		}
	}
	
	private Question createQuestion(QuestionContext ctx) {
		
		Question question = null;
		Variable var = createVariable(ctx);
		varStore.put(var.getName(), var);
		
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
		
		var.setParent(question);
		
		return question;
	}
	
}
