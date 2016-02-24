package nl.nicasso.ql;

import java.util.ArrayList;

import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.StatementContext;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Parenthesis;
import nl.nicasso.ql.ast.expression.additive.Addition;
import nl.nicasso.ql.ast.expression.additive.Subtraction;
import nl.nicasso.ql.ast.expression.conditional.And;
import nl.nicasso.ql.ast.expression.conditional.Not;
import nl.nicasso.ql.ast.expression.conditional.Or;
import nl.nicasso.ql.ast.expression.equality.Equal;
import nl.nicasso.ql.ast.expression.equality.NotEqual;
import nl.nicasso.ql.ast.expression.multiplicative.Division;
import nl.nicasso.ql.ast.expression.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expression.relational.Greater;
import nl.nicasso.ql.ast.expression.relational.GreaterEqual;
import nl.nicasso.ql.ast.expression.relational.Less;
import nl.nicasso.ql.ast.expression.relational.LessEqual;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.Literal;
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

	private boolean debug = false;
	private ArrayList<IdentifierLit> types;
	
	CreateASTVisitor() {
		types = new ArrayList<IdentifierLit>();
	}

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
	public ASTNode visitQuestionStatement(QLParser.QuestionStatementContext ctx) {
		if (debug)
			System.out.println("Question");

		Type type = (Type) ctx.type.accept(this);
		IdentifierLit id = new IdentifierLit(ctx.identifier.getText());
		String label = ctx.label.getText();
		
		return new Question(id, label, type);
	}

	@Override
	public ASTNode visitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx) {
		if (debug)
			System.out.println("ComputedQuestion");

		Type type = (Type) ctx.type.accept(this);
		IdentifierLit id = new IdentifierLit(ctx.identifier.getText());
		String label = ctx.label.getText();
		Expression expr = (Expression) ctx.expr.accept(this);

		return new ComputedQuestion(id, label, type, expr);
	}

	@Override
	public ASTNode visitIfStatement(QLParser.IfStatementContext ctx) {
		if (debug)
			System.out.println("ifStatement");

		Expression expr = (Expression) ctx.expr.accept(this);
		Block bl = (Block) ctx.ifBody.accept(this);

		return new IfStatement(expr, bl);
	}

	@Override
	public ASTNode visitIfElseStatement(QLParser.IfElseStatementContext ctx) {
		if (debug)
			System.out.println("ifElseStatement");

		Expression expr = (Expression) ctx.expr.accept(this);
		Block blockIf = (Block) ctx.ifBody.accept(this);

		Block blockElse = (Block) ctx.elseBody.accept(this);

		return new IfElseStatement(expr, blockIf, blockElse);
	}

	@Override
	public ASTNode visitLessEqExpression(QLParser.LessEqExpressionContext ctx) {
		if (debug)
			System.out.println("LessOrEqual");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new LessEqual(left, right);
	}

	@Override
	public ASTNode visitLessExpression(QLParser.LessExpressionContext ctx) {
		if (debug)
			System.out.println("Less");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Less(left, right);
	}

	@Override
	public ASTNode visitDivExpression(QLParser.DivExpressionContext ctx) {
		if (debug)
			System.out.println("Divison");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Division(left, right);
	}

	@Override
	public ASTNode visitParenExpression(QLParser.ParenExpressionContext ctx) {
		if (debug)
			System.out.println("Parenthesis");
		
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Parenthesis(expr);
	}

	@Override
	public ASTNode visitNoteqExpression(QLParser.NoteqExpressionContext ctx) {
		if (debug)
			System.out.println("NotEqual");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new NotEqual(left, right);
	}

	@Override
	public ASTNode visitOrExpression(QLParser.OrExpressionContext ctx) {
		if (debug)
			System.out.println("Or");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Or(left, right);
	}

	@Override
	public ASTNode visitEqExpression(QLParser.EqExpressionContext ctx) {
		if (debug)
			System.out.println("Equal");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Equal(left, right);
	}

	@Override
	public ASTNode visitAndExpression(QLParser.AndExpressionContext ctx) {
		if (debug)
			System.out.println("And");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new And(left, right);
	}

	@Override
	public ASTNode visitNotExpr(QLParser.NotExprContext ctx) {
		if (debug)
			System.out.println("Not");
		
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Not(expr);
	}

	@Override
	public ASTNode visitAddExpression(QLParser.AddExpressionContext ctx) {
		if (debug)
			System.out.println("Add");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Addition(left, right);
	}

	@Override
	public ASTNode visitGreatEqExpression(QLParser.GreatEqExpressionContext ctx) {
		if (debug)
			System.out.println("GreaterEqual");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new GreaterEqual(left, right);
	}

	@Override
	public ASTNode visitLitExpression(QLParser.LitExpressionContext ctx) {
		if (debug)
			System.out.println("Literal: "+ctx.getText());
		
		Literal literal = (Literal) ctx.lit.accept(this);
		
		return literal;
	}
	
	@Override
	public ASTNode visitSubExpression(QLParser.SubExpressionContext ctx) {
		if (debug)
			System.out.println("Subtract");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Subtraction(left, right);
	}

	@Override
	public ASTNode visitGreatExpression(QLParser.GreatExpressionContext ctx) {
		if (debug)
			System.out.println("Greater");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Greater(left, right);
	}

	@Override
	public ASTNode visitMulExpression(QLParser.MulExpressionContext ctx) {
		if (debug)
			System.out.println("Multiplication");
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		return new Multiplication(left, right);
	}

	@Override
	public ASTNode visitIdentifierLiteral(QLParser.IdentifierLiteralContext ctx) {
		if (debug)
			System.out.println("Identifier");
						
		return new IdentifierLit(ctx.getText());
	}

	@Override
	public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
		if (debug)
			System.out.println("Integer");
		
		return new IntegerLit(Integer.parseInt(ctx.getText()));
	}

	@Override
	public ASTNode visitBooleanliteral(QLParser.BooleanliteralContext ctx) {
		if (debug)
			System.out.println("Boolean");
		
		return new BooleanLit(Boolean.parseBoolean(ctx.getText()));
	}

	@Override
	public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
		if (debug)
			System.out.println("String");
		
		return new StringLit(ctx.getText());
	}

	@Override
	public ASTNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
		if (debug)
			System.out.println("IntegerType");
		
		return new IntegerType();
	}

	@Override
	public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
		if (debug)
			System.out.println("StringType");
		
		return new StringType();
	}

	@Override
	public ASTNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
		if (debug)
			System.out.println("BooleanType");
		
		return new BooleanType();
	}

	@Override
	public ASTNode visitMoneyType(QLParser.MoneyTypeContext ctx) {
		if (debug)
			System.out.println("MoneyType");
		
		return new MoneyType();
	}

}
