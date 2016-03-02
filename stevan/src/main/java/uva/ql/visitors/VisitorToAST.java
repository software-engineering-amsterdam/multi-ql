package uva.ql.visitors;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.AExpression;
import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.ast.variables.Bool;

public class VisitorToAST extends QLBaseVisitor<Object> {

	private final Form form = AST.newForm();
	private final Map<String, AVariable> varStore = new HashMap<String, AVariable>(0);
	private final static Map<String, AVariable> VAR = new HashMap<String, AVariable>(0);
	private final static Map<String, AExpression> EXP = new HashMap<String, AExpression>(0);
	
	static {
		VAR.put("BOOLEAN", AST.newVarBool());
		VAR.put("STRING", AST.newVarStr());
		VAR.put("INTEGER", AST.newVarInt());
		VAR.put("DATE", AST.newVarDate());
		VAR.put("DOUBLE", AST.newVarDouble());
		VAR.put("DECIMAL", AST.newVarDecimal());
		VAR.put("MONEY", AST.newVarMoney());
		
		EXP.put("<", AST.newExpSmlThen());
		EXP.put(">", AST.newExpGrtThen());
		EXP.put("<=", AST.newExpSmlEql());
		EXP.put(">=", AST.newExpGrtEql());
		EXP.put("!=", AST.newExpNotEql());
	}

	@Override
	public Form visitForm( @NotNull QLParser.FormContext ctx ) {
		
		form.setName(ctx.varName().getText());
		form.setLine(ctx.getStart().getLine());
		form.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			if (ctx.getChild(i) == ctx.block()) {
				
				Block block = (Block) ctx.getChild(i).accept(this);
				block.setParent(form);
				form.addChild(block);
			}
		}
		
		return form;
	}
	
	@Override
	public Block visitBlock( @NotNull QLParser.BlockContext ctx ) {
		
		Block block = AST.newBlock();
		
		for (int i=0; i<ctx.question().size(); i++) {
			
			Question question = (Question) ctx.question(i).accept(this);
			question.setParent(block);
			block.addChild(question);
		}

		for (int i=0; i<ctx.condition().size(); i++) {
			
			// Assuming that its an IfStatment... make an ASTCondition node?!
			IfStatement ifStmnt = (IfStatement) ctx.condition(i).accept(this);
			ifStmnt.setParent(block);
			block.addChild(ifStmnt);
		}
		
		return block;
	}
	
	@Override
	public IfStatement visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		IfStatement ifStmnt = AST.newIfStatement();
		ifStmnt.setLine(ctx.getStart().getLine());
		ifStmnt.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		for (int i=0; i<ctx.expression().size(); i++) {
			
			AExpression exp = (AExpression) ctx.expression(i).accept(this);
			exp.setParent(ifStmnt);
			ifStmnt.setExpression(exp);
		}
		
		for (int i=0; i<ctx.block().size(); i++) {
			
			Block block = (Block) ctx.block(i).accept(this);
			block.setParent(ifStmnt);
			ifStmnt.addChild(block);
		}

		return ifStmnt;
	}
	
	@Override 
	public Question visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		Question question = AST.newQuestion();
		AVariable var = createVariable(ctx.varType().getText());

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
		
			AExpression exp = (AExpression) ctx.expression(i).accept(this);
			exp.setParent(question);
			question.setExpression(exp);
		}

		return question;
	}
	
	@Override
	public AExpression visitExpParentheses( @NotNull QLParser.ExpParenthesesContext ctx ) {
		
		return (AExpression) ctx.expression().accept(this);
	}
	
	@Override
	public AVariable visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		AVariable var = varStore.get(ctx.getText());
		
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
	public AExpression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		
		AExpression exp = null;
		
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
	public AExpression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {
		
		AExpression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "+") {
			exp = AST.newExprAdd();
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
	public AExpression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		
		AExpression exp = createExpEquality(ctx.getChild(1).getText().intern());
		exp.setLine(ctx.getStart().getLine());
		exp.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		visitExprChildren(ctx, exp);
		
		return exp;
	}
	
	@Override
	public AExpression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {
		AExpression exp = null;
		
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
	public ANumber visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		
		ANumber var = null;
		
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
	public AExpression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		
		AExpression expPar = AST.newExprNot();
		AExpression exp = (AExpression) ctx.getChild(1).accept(this);
		
		expPar.setLine(ctx.getStart().getLine());
		expPar.setColumn(ctx.getStart().getCharPositionInLine() + 1);
		
		expPar.setLeftNode(exp);
		exp.setParent(expPar);
		
		return expPar;
	}
	
	private AVariable createVariable(String type) {
		
		AVariable var = VAR.get(type.toUpperCase());
		var = var != null ? var : AST.newVarGeneric();
		
		return var;
	}
	
	private AExpression createExpEquality(String type) {

		AExpression exp = EXP.get(type);
		exp = exp != null ? exp : AST.newExpEql();
		
		return exp;
	}
	
	private void visitExprChildren(QLParser.ExpressionContext ctx, AExpression exp) {
		
		AExpression leftExp = (AExpression) ctx.getChild(0).accept(this);
		AExpression rightExp = (AExpression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
	}
}
