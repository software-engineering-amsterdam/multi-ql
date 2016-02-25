package nl.nicasso.ql;

import java.util.ArrayList;

import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.StatementContext;
import org.uva.sea.ql.parser.antlr.QLVisitor;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Identifier;
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
import nl.nicasso.ql.symbolTable.SymbolTable;

public class CreateASTVisitor extends QLBaseVisitor<ASTNode> implements QLVisitor<ASTNode> {

	private boolean debug = false;
	
	private SymbolTable symbolTable;

	CreateASTVisitor(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	@Override
	public ASTNode visitForm(QLParser.FormContext ctx) {
		if (debug)
			System.out.println("Form");

		Identifier id = new Identifier(ctx.identifier.getText());
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
			System.out.println("Question: "+ctx.identifier.getText());

		Type type = (Type) ctx.type.accept(this);
		Identifier id = new Identifier(ctx.identifier.getText());
		String label = ctx.label.getText();

		return new Question(id, label, type);
	}

	@Override
	public ASTNode visitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx) {
		if (debug)
			System.out.println("ComputedQuestion: "+ctx.identifier.getText());

		Type type = (Type) ctx.type.accept(this);
		Identifier id = new Identifier(ctx.identifier.getText());
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
	public ASTNode visitEqualityExpressions(QLParser.EqualityExpressionsContext ctx) {
		if (debug)
			System.out.println("Equality: "+ctx.getText());
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch(ctx.op.getText()) {
			case "==":
				return new Equal(left, right);
			case "!=":
				return new NotEqual(left, right);
			default:
				// Throw error or something
				return null;
		}
	}

	@Override
	public ASTNode visitMultiplicativeExpressions(QLParser.MultiplicativeExpressionsContext ctx) {
		if (debug)
			System.out.println("Multiplicative: "+ctx.getText());
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch(ctx.op.getText()) {
			case "*":
				return new Multiplication(left, right);
			case "/":
				return new Division(left, right);
			default:
				// Throw error or something
				return null;
		}
	}

	@Override
	public ASTNode visitAdditiveExpressions(QLParser.AdditiveExpressionsContext ctx) {
		if (debug)
			System.out.println("Additive: "+ctx.getText());
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch(ctx.op.getText()) {
			case "+":
				return new Addition(left, right);
			case "-":
				return new Subtraction(left, right);
			default:
				// Throw error or something
				return null;
		}
	}

	@Override
	public ASTNode visitRelationalExpressions(QLParser.RelationalExpressionsContext ctx) {
		if (debug)
			System.out.println("Relational: "+ctx.getText());
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch(ctx.op.getText()) {
			case ">":
				return new Greater(left, right);
			case ">=":
				return new GreaterEqual(left, right);
			case "<":
				return new Less(left, right);
			case "<=":
				return new LessEqual(left, right);
			default:
				// Throw error or something
				return null;
		}
	}

	@Override
	public ASTNode visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
		if (debug)
			System.out.println("Identifier: "+ctx.getText());
		
		return new Identifier(ctx.identifier.getText());
	}

	@Override
	public ASTNode visitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx) {
		if (debug)
			System.out.println("Parenthesis: "+ctx.getText());
		
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Parenthesis(expr);
	}

	@Override
	public ASTNode visitNotExpression(QLParser.NotExpressionContext ctx) {
		if (debug)
			System.out.println("Not");
		
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Not(expr);
	}

	@Override
	public ASTNode visitLiteralExpression(QLParser.LiteralExpressionContext ctx) {
		if (debug)
			System.out.println("Literal: "+ctx.getText());
		
		Literal lit = (Literal) ctx.literalValue.accept(this);
		
		return lit;
	}

	@Override
	public ASTNode visitConditionalExpressions(QLParser.ConditionalExpressionsContext ctx) {
		if (debug)
			System.out.println("Conditional: "+ctx.getText());
		
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch(ctx.op.getText()) {
			case "&&":
				return new And(left, right);
			case "||":
				return new Or(left, right);
			default:
				// Throw error or something
				return null;
		}
	}

	@Override
	public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
		if (debug)
			System.out.println("Integer: "+ctx.getText());

		return new IntegerLit(Integer.parseInt(ctx.getText()));
	}

	@Override
	public ASTNode visitBooleanliteral(QLParser.BooleanliteralContext ctx) {
		if (debug)
			System.out.println("Boolean: "+ctx.getText());

		return new BooleanLit(Boolean.parseBoolean(ctx.getText()));
	}

	@Override
	public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
		if (debug)
			System.out.println("String: "+ctx.getText());

		return new StringLit(ctx.getText());
	}

	@Override
	public ASTNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
		if (debug)
			System.out.println("IntegerType: "+ctx.getText());

		return new IntegerType();
	}

	@Override
	public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
		if (debug)
			System.out.println("StringType: "+ctx.getText());

		return new StringType();
	}

	@Override
	public ASTNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
		if (debug)
			System.out.println("BooleanType: "+ctx.getText());

		return new BooleanType();
	}

	@Override
	public ASTNode visitMoneyType(QLParser.MoneyTypeContext ctx) {
		if (debug)
			System.out.println("MoneyType: "+ctx.getText());

		return new MoneyType();
	}

}
