package uva.ql.visitors;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.AExpression;
import uva.ql.ast.ANode;
import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.INode;

public class VisitorToAST extends QLBaseVisitor<Object> {

	private final Form form = AST.newForm();
	private final Map<String, AVariable> varStore = new HashMap<String, AVariable>(0);

	@Override
	public Form visitForm( @NotNull QLParser.FormContext ctx ) {
		
		form.setName(ctx.varName().getText());
		//System.out.println(ctx.getText());
		
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
		//System.out.println(ctx.getText());
		
		for (int i=0; i<ctx.question().size(); i++) {
			
			Question question = (Question) ctx.question(i).accept(this);
			question.setParent(block);
			block.addChild(question);
		}
		//System.out.println("block size: " + block.size());
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
		//System.out.println(ctx.getText());
		
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
		
		//System.out.println(ctx.getText());
		
		Question question = AST.newQuestion();
		AVariable var = createVariable(ctx.varType().getText());

		question.addChild(var);
		question.setLabel(ctx.label().getText().substring(1, ctx.label().getText().length()-1));
		
		var.setName(ctx.varName().getText());
		//System.out.println("varType: " + var.getVarType());
		var.setParent(question);
		varStore.put(var.getName(), var);
		
		for (int i=0; i<ctx.expression().size(); i++) {
		
			//System.out.println(ctx.expression(i).getText());
			
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
		
		AVariable var = null;
		
		if(varStore.get(ctx.getText()) == null) {
			
			var = AST.newVarGeneric();
		}
		else {
			
			var = varStore.get(ctx.getText());
		}
		
		return var;
	}
	
	@Override
	public AExpression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		
		AExpression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "*") {
			exp = AST.newExprMult();
		}
		else {
			exp = AST.newExprDiv();
		}
		
		AExpression leftExp = (AExpression) ctx.getChild(0).accept(this);
		AExpression rightExp = (AExpression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
		
		return exp;
	}
	
	@Override
	public AExpression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {
		
		AExpression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "+") {
			exp = AST.newExprAdd();
		}
		else {
			exp = AST.newExprMinus();
		}
		
		AExpression leftExp = (AExpression) ctx.getChild(0).accept(this);
		AExpression rightExp = (AExpression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		//System.out.println(rightExp.getExprType());
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
		
		return exp;
	}

	
	@Override
	public AExpression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		
		AExpression exp = null;
		
		switch(ctx.getChild(1).getText().intern()) {
			case "<":
				exp = AST.newExpSmlThen();
				break;
			case ">":
				exp = AST.newExpGrtThen();
				break;
			case "<=":
				exp = AST.newExpSmlEql();
				break;
			case ">=":
				exp = AST.newExpGrtEql();
				break;
			case "!=":
				exp = AST.newExpNotEql();
				break;
			default:
				exp = AST.newExpEql();
				break;
		}
		
		AExpression leftExp = (AExpression) ctx.getChild(0).accept(this);
		AExpression rightExp = (AExpression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
		
		return exp;
	}
	
	@Override
	public AExpression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {
		AExpression exp = null;
		
		if (ctx.getChild(1).getText().intern() == "&&") {
			exp = AST.newExprAnd();
		}
		else {
			exp = AST.newExprOr();
		}
		
		AExpression leftExp = (AExpression) ctx.getChild(0).accept(this);
		AExpression rightExp = (AExpression) ctx.getChild(2).accept(this);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
		
		return exp;
	}
	
	@Override
	public ANumber visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		
		ANumber var = null;
		
		if(!ctx.DIGIT().isEmpty()) {
			var = AST.newNumInt();
			var.setValue(ctx.getText());
		}
		else {
			var = AST.newNumDouble();
			var.setValue(ctx.getText());
		}
		
		return var;
	}
	
	@Override
	public AExpression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		
		AExpression expPar = AST.newExprNot();
		AExpression exp = (AExpression) ctx.getChild(1).accept(this);
		
		expPar.setLeftNode(exp);
		exp.setParent(expPar);
		
		return expPar;
	}
	
	private AVariable createVariable(String type) {
		
		AVariable var = null;
		
		switch(type.toUpperCase()) {
			case "BOOLEAN":
				var = AST.newVarBool();
				break;
			case "STRING":
				var = AST.newVarStr();
				break;
			case "INTEGER":
				var = AST.newVarInt();
				break;
			case "DATE":
				var = AST.newVarDate();
				break;
			case "DOUBLE":
				var = AST.newVarDouble();
				break;
			case "DECIMAL":
				var = AST.newVarDecimal();
				break;
			case "MONEY":
				var = AST.newVarMoney();
				break;
			default:
				var = AST.newVarGeneric();
				break;
		}
		
		return var;
	}
	
}
