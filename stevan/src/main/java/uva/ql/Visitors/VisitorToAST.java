package uva.ql.Visitors;

import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.antlr4.QLParser.QuestionContext;
import uva.ql.ast.AST;
import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTNode;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;

public class VisitorToAST extends QLBaseVisitor<Object> {
	
/*	private ASTQuestion question = AST.newQuestion();
	private ASTVariable varNode = AST.newVariable();
	private ASTExpression exp = AST.newExpression();
	private ASTIfStatement ifStmnt = AST.newIfStatement();*/

	@Override
	public ASTForm visitForm( @NotNull QLParser.FormContext ctx ) {
		
		ASTForm form = AST.newForm();
		form.setName(ctx.varName().getText());
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			if (ctx.getChild(i) == ctx.block()) {
				ASTBlock block = AST.newBlock();
				block = (ASTBlock) ctx.block().accept(this);
				form.addChild(block);
				block.setParent(form);
			}
		}
		
		return form;
	}
	
	@Override
	public ASTBlock visitBlock( @NotNull QLParser.BlockContext ctx ) {
		
		ASTBlock block = AST.newBlock();
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			if (ctx.getChild(i) == ctx.question(i)) {
				ASTQuestion question = AST.newQuestion();
				question = (ASTQuestion) visitQuestion((QuestionContext) ctx.question());
				question.setParent(block);
				block.addChild(question);
			}
			else if (ctx.getChild(i) == ctx.condition()) {
				// Assuming that its an IfStatment... make an ASTCondition node?!
				ASTIfStatement ifStmnt = AST.newIfStatement();
				ifStmnt = (ASTIfStatement) ctx.getChild(i).accept(this);
				ifStmnt.setParent(block);
				block.addChild(ifStmnt);
			}
			ctx.getChild(i).accept(this);
		}
		
		return block;
	}
	
	@Override
	public ASTIfStatement visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		ASTIfStatement ifStmnt = AST.newIfStatement();
		/*ifStmnt.setParent(block);
		block.addChild(ifStmnt);
		exp = AST.newExpression();
		exp.setParent(ifStmnt);
		ifStmnt.setExpression(exp);*/
		
		for (int i=0; i<ctx.getChildCount(); i++) {
				
			ctx.getChild(i).accept(this);
		}

		//System.out.println("Create IfStatement - " + ctx.getText());
		
		//System.out.println("StartVisit IfCondition");
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			//System.out.println("\tVisit children of IfStatement: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
			
		}
		//System.out.println("EndVisit IfCondition");
		return ifStmnt;
	}
	
	@Override 
	public ASTQuestion visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		//System.out.println("Create Question");
		
		ASTQuestion question = AST.newQuestion();
		/*question.setLabel(ctx.label().getText().substring(1, ctx.label().getText().length()-1));
		question.setParent(block);
		block.addChild(question);
		//System.out.println(ctx.parent.getText());
		varNode = AST.newVariable();
		varNode.setName(ctx.varName().getText());
		varNode.setType(ctx.varType().getText());
		varNode.setParent(question);
		question.addChild(varNode);*/
		
		/*if(!ctx.expression().isEmpty()) {
			
			question.setComputed(true);
			exp = AST.newExpression();
			exp.setParent(question);
			
			for (int i=0; i<ctx.getChildCount(); i++) {
				
				ctx.getChild(i).accept(this);
			}
		}*/

		return question;
	}
	
/*	@Override
	public ASTVariable visitVarName( @NotNull QLParser.VarNameContext ctx ) {
		
		varNode = AST.newVariable();
		varNode.setName(ctx.getText());
		//System.out.println("Create Variable Name - " + ctx.getText());
		//System.out.println(ctx.getChildCount());
		//System.out.println(ctx.getChild(0).getText());
		
		return varNode;
	}

	@Override
	public ASTVariable visitVarType( @NotNull QLParser.VarTypeContext ctx ) {
		
		varNode = AST.newVariable();
		//System.out.println("Create Variable - " + ctx.getText());
		//System.out.println(ctx.getChildCount());
		//System.out.println(ctx.getChild(0).getText());

		return varNode;
	}
	
	@Override
	public ASTExpression visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		System.out.println(ctx.getText());
		if (exp.getParent() != null) {
			exp = AST.newExpression();
		}
		exp.setExpressionType(ASTExpression.SINGLE_EXP);
		exp.setParent(null);
		
		exp.setLeftNode(null);
		return exp;
	}
	
	@Override
	public ASTExpression visitExpParentheses( @NotNull QLParser.ExpParenthesesContext ctx ) {
		
		//exp.setExpressionType(ctx.);
		//System.out.println("Create expParentheses - " + ctx.getText() + " - " + ctx.getChildCount());
		//System.out.println(exp.getLeftNode());
		
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			//System.out.println("\tVisit children of Expression: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
			//System.out.println(ctx.getChild(i).getText());
		}

		return exp;
	}
	
	@Override
	public ASTExpression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {
		System.out.println("Create expPlusMinus - " + ctx.getText() + " - " + ctx.getChildCount());
		//System.out.println(exp.getLeftNode());
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println(ctx.getChild(i).getText());
		}
		
		return exp;
	}
	
	@Override
	public ASTExpression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {
		return exp;
	}
	*/
	
}
