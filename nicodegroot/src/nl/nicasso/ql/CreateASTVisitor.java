package nl.nicasso.ql;

import java.util.ArrayList;

import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.StatementContext;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.expression.BooleanExpr;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.MathHighExpr;
import nl.nicasso.ql.ast.expression.MathLowExpr;
import nl.nicasso.ql.ast.expression.NotExpr;
import nl.nicasso.ql.ast.expression.ParenthesisExpr;
import nl.nicasso.ql.ast.expression.RelationExpr;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.StringLit;
import nl.nicasso.ql.ast.statement.ComputedQuestion;
import nl.nicasso.ql.ast.statement.IfElseStatement;
import nl.nicasso.ql.ast.statement.IfStatement;
import nl.nicasso.ql.ast.statement.Question;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.structure.Form;
import nl.nicasso.ql.ast.type.Type;

public class CreateASTVisitor extends QLBaseVisitor<ASTNode> {

	private boolean debug = false;

	@Override
	public ASTNode visitForm(QLParser.FormContext ctx) {
		if (debug)
			System.out.println("Form");

		IdentifierLit id = new IdentifierLit(ctx.identifier.getText());
		Block bl = (Block) ctx.block().accept(this);

		return new Form(id, bl);
	}

	@Override
	public ASTNode visitBlock(QLParser.BlockContext ctx) {
		if (debug)
			System.out.println("Block");

		ArrayList<Statement> statements = new ArrayList<Statement>();

		for (StatementContext st : ctx.statement()) {
			statements.add((Statement) st.accept(this));
		}

		return new Block(statements);
	}

	@Override
	public ASTNode visitQuestionExpr(QLParser.QuestionExprContext ctx) {
		if (debug)
			System.out.println("Question");

		IdentifierLit id = new IdentifierLit(ctx.identifier.getText());
		String label = ctx.label.getText();
		Type type = (Type) ctx.type.accept(this);

		return new Question(id, label, type);
	}

	@Override
	public ASTNode visitComputedQuestionExpr(QLParser.ComputedQuestionExprContext ctx) {
		if (debug)
			System.out.println("ComputedQuestion");

		IdentifierLit id = new IdentifierLit(ctx.identifier.getText());
		String label = ctx.label.getText();
		Type type = (Type) ctx.type.accept(this);
		Expression expr = (Expression) ctx.expr.accept(this);

		return new ComputedQuestion(id, label, type, expr);
	}

	@Override
	public ASTNode visitIfStatementExpr(QLParser.IfStatementExprContext ctx) {
		if (debug)
			System.out.println("ifStatement");

		Expression expr = (Expression) ctx.expr.accept(this);
		Block bl = (Block) ctx.ifBody.accept(this);

		return new IfStatement(expr, bl);
	}

	@Override
	public ASTNode visitIfElseStatementExpr(QLParser.IfElseStatementExprContext ctx) {
		if (debug)
			System.out.println("ifElseStatement");

		Expression expr = (Expression) ctx.expr.accept(this);
		Block blockIf = (Block) ctx.ifBody.accept(this);

		Block blockElse = (Block) ctx.elseBody.accept(this);

		return new IfElseStatement(expr, blockIf, blockElse);
	}

	@Override
	public ASTNode visitNotExpr(QLParser.NotExprContext ctx) {
		if (debug)
			System.out.println("NotExpr");

		Expression expr = (Expression) ctx.expression().accept(this);

		return new NotExpr(expr);
	}

	@Override
	public ASTNode visitMathLowExpr(QLParser.MathLowExprContext ctx) {
		if (debug)
			System.out.println("MathLowExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		String op = ctx.op.getText();

		return new MathLowExpr(left, right, op);
	}

	@Override
	public ASTNode visitBoolExpr(QLParser.BoolExprContext ctx) {
		if (debug)
			System.out.println("BoolExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		String op = ctx.op.getText();

		return new BooleanExpr(left, right, op);
	}

	@Override
	public ASTNode visitRelExpr(QLParser.RelExprContext ctx) {
		if (debug)
			System.out.println("RelExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		String op = ctx.op.getText();

		return new RelationExpr(left, right, op);
	}
	/*
	@Override
	public ASTNode visitLitExpr(QLParser.LitExprContext ctx) {
		return visitChildren(ctx);
	}
	*/
	@Override
	public ASTNode visitParenExpr(QLParser.ParenExprContext ctx) {
		if (debug)
			System.out.println("ParenExpr");

		Expression expr = (Expression) ctx.expression().accept(this);

		return new ParenthesisExpr(expr);
	}

	@Override
	public ASTNode visitMathHighExpr(QLParser.MathHighExprContext ctx) {
		if (debug)
			System.out.println("MathHighExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		String op = ctx.op.getText();

		return new MathHighExpr(left, right, op);
	}

	@Override
	public ASTNode visitLitIdExpr(QLParser.LitIdExprContext ctx) {
		String text = (String) ctx.getText();

		if (debug)
			System.out.println("LitIdExpr: " + text);

		return new IdentifierLit(text);
	}

	@Override
	public ASTNode visitLitIntExpr(QLParser.LitIntExprContext ctx) {
		int integer = Integer.parseInt(ctx.getText());

		if (debug)
			System.out.println("LitIntExpr: " + integer);

		return new IntegerLit(integer);
	}

	@Override
	public ASTNode visitLitBoolExpr(QLParser.LitBoolExprContext ctx) {
		boolean lit = Boolean.getBoolean(ctx.getText());

		if (debug)
			System.out.println("LitBoolExpr: " + lit);

		return new BooleanLit(lit);
	}

	@Override
	public ASTNode visitLitStringExpr(QLParser.LitStringExprContext ctx) {
		String value = ctx.getText();

		if (debug)
			System.out.println("LitStringExpr: " + value);

		return new StringLit(value);
	}

	@Override
	public ASTNode visitQuestionType(QLParser.QuestionTypeContext ctx) {
		String value = ctx.getText();

		if (debug)
			System.out.println("QuestionType: " + value);

		return new Type(value);

	}

}
