package nl.nicasso.ql;

import java.util.ArrayList;

import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.StatementContext;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.QuestionLabel;
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
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.StringType;
import nl.nicasso.ql.ast.type.Type;

public class CreateASTVisitor extends QLBaseVisitor<ASTNode> {

	private boolean debug = true;

	@Override
	public ASTNode visitForm(QLParser.FormContext ctx) {

		if (debug)
			System.out.println("Form");

		IdentifierLit id = (IdentifierLit) ctx.identifier_st().accept(this);
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
	public ASTNode visitIfStatementExpr(QLParser.IfStatementExprContext ctx) {

		if (debug)
			System.out.println("ifStatement");

		Expression expr = (Expression) ctx.expression().accept(this);
		Block bl = (Block) ctx.block().accept(this);

		return new IfStatement(expr, bl);
	}

	@Override
	public ASTNode visitIfElseStatementExpr(QLParser.IfElseStatementExprContext ctx) {

		if (debug)
			System.out.println("ifElseStatement");

		Expression expr = (Expression) ctx.expression().accept(this);
		Block blockIf = (Block) ctx.block(0).accept(this);

		Block blockElse = (Block) ctx.block(1).accept(this);

		return new IfElseStatement(expr, blockIf, blockElse);
	}

	@Override
	public ASTNode visitComputedQuestionExpr(QLParser.ComputedQuestionExprContext ctx) {

		if (debug)
			System.out.println("ComputedQuestion");

		IdentifierLit id = (IdentifierLit) ctx.identifier_st().accept(this);
		QuestionLabel label = (QuestionLabel) ctx.label.accept(this);
		Type type = (Type) ctx.type.accept(this);
		Expression expr = (Expression) ctx.expr.accept(this);

		return new ComputedQuestion(id, label, type, expr);
	}

	@Override
	public ASTNode visitQuestionExpr(QLParser.QuestionExprContext ctx) {

		if (debug)
			System.out.println("Question");

		IdentifierLit id = (IdentifierLit) ctx.identifier.accept(this);
		QuestionLabel label = (QuestionLabel) ctx.label.accept(this);
		Type type = (Type) ctx.type.accept(this);

		return new Question(id, label, type);
	}

	@Override
	public ASTNode visitIdentifier_st(QLParser.Identifier_stContext ctx) {

		if (debug)
			System.out.println("Identifier");

		return new IdentifierLit(ctx.getText());
	}

	@Override
	public ASTNode visitQuestionLabel(QLParser.QuestionLabelContext ctx) {

		if (debug)
			System.out.println("QuestionLabel");

		return new QuestionLabel(ctx.getText());
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

		return new MathLowExpr(left, right);
	}

	@Override
	public ASTNode visitBoolExpr(QLParser.BoolExprContext ctx) {

		if (debug)
			System.out.println("BoolExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);

		return new BooleanExpr(left, right);
	}

	@Override
	public ASTNode visitRelExpr(QLParser.RelExprContext ctx) {

		if (debug)
			System.out.println("RelExpr");

		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);

		return new RelationExpr(left, right);
	}

	/*
	 * @Override public ASTNode visitLitExpr(QLParser.LitExprContext ctx) {
	 * 
	 * 
	 * if (debug) System.out.println("LitExpr");
	 * 
	 * Expression expr = (Expression) ctx.literal().accept(this);
	 * 
	 * return new Literal(expr); }
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

		return new MathHighExpr(left, right);
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
	public ASTNode visitTypeIntExpr(QLParser.TypeIntExprContext ctx) {

		String value = ctx.getText();

		if (debug)
			System.out.println("TypeIntExpr: " + value);

		return new IntegerType(value);
	}

	@Override
	public ASTNode visitTypeStrExpr(QLParser.TypeStrExprContext ctx) {

		String value = ctx.getText();

		if (debug)
			System.out.println("TypeStrExprL " + value);

		return new StringType(value);
	}

	@Override
	public ASTNode visitTypeBoolExpr(QLParser.TypeBoolExprContext ctx) {

		String value = ctx.getText();

		if (debug)
			System.out.println("TypeBoolExpr: " + value);

		return new BooleanType(value);
	}

	@Override
	public ASTNode visitTypeMonExpr(QLParser.TypeMonExprContext ctx) {

		String value = ctx.getText();

		if (debug)
			System.out.println("TypeMonExpr: " + value);

		return new MoneyType(value);
	}
}
