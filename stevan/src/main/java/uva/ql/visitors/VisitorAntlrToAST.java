package uva.ql.visitors;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.antlr4.QLParser.QuestionContext;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.Node;
import uva.ql.ast.condition.CondIfElseStatement;
import uva.ql.ast.condition.CondIfStatement;
import uva.ql.ast.condition.Condition;
import uva.ql.ast.expression.Expression;
import uva.ql.ast.expression.arithmetic.AddExpression;
import uva.ql.ast.expression.arithmetic.DivideExpression;
import uva.ql.ast.expression.arithmetic.MinusExpression;
import uva.ql.ast.expression.arithmetic.MultiplyExpression;
import uva.ql.ast.expression.logical.AndExpression;
import uva.ql.ast.expression.logical.NotExpression;
import uva.ql.ast.expression.logical.OrExpression;
import uva.ql.ast.expression.relational.EqualToExpression;
import uva.ql.ast.expression.relational.GreaterThenExpression;
import uva.ql.ast.expression.relational.GreaterThenOrEqualToExpression;
import uva.ql.ast.expression.relational.LessThenExpression;
import uva.ql.ast.expression.relational.LessThenOrEqualToExpression;
import uva.ql.ast.expression.relational.NotEqualToExpression;
import uva.ql.ast.question.Question;
import uva.ql.ast.question.QuestionComputed;
import uva.ql.ast.question.QuestionVanilla;
import uva.ql.ast.value.Value;
import uva.ql.ast.value.ValueBoolean;
import uva.ql.ast.value.ValueInteger;
import uva.ql.ast.value.ValueMoney;
import uva.ql.ast.variable.Variable;
import uva.ql.ast.variable.VariableBoolean;
import uva.ql.ast.variable.VariableDate;
import uva.ql.ast.variable.VariableInteger;
import uva.ql.ast.variable.VariableMoney;
import uva.ql.ast.variable.VariableString;
import uva.ql.ast.variable.VariableUndefined;

public class VisitorAntlrToAST extends QLBaseVisitor<Object> {

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
		
		for(ParseTree tree : ctx.children) {

			Node node = (Node) tree.accept(this);
			
			if (node != null) {
				node.setParent(block);
				block.add(node);
			}
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
			
			exp = new MultiplyExpression(null, line, column, lhs, rhs);
		}
		else {
			
			exp = new DivideExpression(null, line, column, lhs, rhs);
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
			
			exp = new AddExpression(null, line, column, lhs, rhs);
		}
		else {
			
			exp = new MinusExpression(null, line, column, lhs, rhs);
		}
		
		return exp;
	}
	
	@Override
	public Expression visitExpRelational( @NotNull QLParser.ExpRelationalContext ctx ) {
		
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
				return new LessThenExpression(null, line, column, lhs, rhs);
			case ">":
				return new GreaterThenExpression(null, line, column, lhs, rhs);
			case "<=":
				return new LessThenOrEqualToExpression(null, line, column, lhs, rhs);
			case ">=":
				return new GreaterThenOrEqualToExpression(null, line, column, lhs, rhs);
			case "!=":
				return new NotEqualToExpression(null, line, column, lhs, rhs);
			default:
				return new EqualToExpression(null, line, column, lhs, rhs);
		}
	}
	
	@Override
	public Expression visitExpLogical( @NotNull QLParser.ExpLogicalContext ctx ) {

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
			
			exp = new AndExpression(null, line, column, lhs, rhs);
		}
		else {
			
			exp = new OrExpression(null, line, column, lhs, rhs);
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
		
		exp = new NotExpression(null, line, column, lhs);
		
		return exp;
	}
	
	@Override
	public Variable visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		Variable var = varStore.get(ctx.getText());
		
		if( var == null) {
			
			var =  new VariableUndefined(null, ctx.getText(), line, column);
		}
		
		return var;
	}
	
	@Override
	public Value visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		if(!ctx.DIGIT().isEmpty()) {
			
			return new ValueInteger(null, ctx.getText(), line, column);
		}
		else {
			
			return new ValueMoney(null, ctx.getText(), line, column);
		}
	}
	
	@Override
	public Value visitExpBool( @NotNull QLParser.ExpBoolContext ctx ) {
		
		Token token = ctx.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		
		return new ValueBoolean(null, ctx.getText().intern(), line, column);
	}
	
	private Variable createVariable(QuestionContext ctx) {
		
		Token token = ctx.varName().getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine() + 1;
		String varType = ctx.varType().getText();
		String varName = ctx.varName().getText();
		
		switch (varType.toUpperCase()) {
			case "BOOLEAN":
				return new VariableBoolean(null, varName, line, column);
			case "DATE":
				return new VariableDate(null, varName, line, column);
			case "INTEGER":
				return new VariableInteger(null, varName, line, column);
			case "MONEY":
				return new VariableMoney(null, varName, line, column);
			case "STRING":
				return new VariableString(null, varName, line, column);
			default:
				return new VariableUndefined(null, varName, line, column);
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
			question = new QuestionComputed(null, exp, line, column, label, var);
			exp.setParent(question);
		} 
		else {
			
			question = new QuestionVanilla(null, line, column, label, var);
		}
		
		var.setParent(question);
		
		return question;
	}
	
}
