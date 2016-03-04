package nl.nicasso.ql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import nl.nicasso.ql.antlr.QLBaseVisitor;
import nl.nicasso.ql.antlr.QLParser;
import nl.nicasso.ql.antlr.QLVisitor;
import nl.nicasso.ql.antlr.QLParser.StatementContext;
import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.expressions.Parenthesis;
import nl.nicasso.ql.ast.expressions.additive.Addition;
import nl.nicasso.ql.ast.expressions.additive.Additive;
import nl.nicasso.ql.ast.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.expressions.conditional.And;
import nl.nicasso.ql.ast.expressions.conditional.Conditional;
import nl.nicasso.ql.ast.expressions.conditional.Not;
import nl.nicasso.ql.ast.expressions.conditional.Or;
import nl.nicasso.ql.ast.expressions.equality.Equal;
import nl.nicasso.ql.ast.expressions.equality.Equality;
import nl.nicasso.ql.ast.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expressions.multiplicative.Multiplicative;
import nl.nicasso.ql.ast.expressions.relational.Greater;
import nl.nicasso.ql.ast.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.expressions.relational.Less;
import nl.nicasso.ql.ast.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.expressions.relational.Relational;
import nl.nicasso.ql.ast.literals.BooleanLit;
import nl.nicasso.ql.ast.literals.IntegerLit;
import nl.nicasso.ql.ast.literals.Literal;
import nl.nicasso.ql.ast.literals.MoneyLit;
import nl.nicasso.ql.ast.literals.StringLit;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.StringType;
import nl.nicasso.ql.ast.types.Type;

public class CreateAST extends QLBaseVisitor<ASTNode> implements QLVisitor<ASTNode> {
	
	@Override
	public Form visitForm(QLParser.FormContext ctx) {
		Identifier id = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		Block block = (Block) ctx.block().accept(this);

		return new Form(id, block, getCodeLocation(ctx));
	}

	@Override
	public Block visitBlock(QLParser.BlockContext ctx) {
		List<Statement> statements = new ArrayList<Statement>();
		
		for (StatementContext st : ctx.statement()) {
			statements.add((Statement) st.accept(this));
		}

		return new Block(statements, getCodeLocation(ctx));
	}

	@Override
	public Question visitQuestionStatement(QLParser.QuestionStatementContext ctx) {
		Type type = (Type) ctx.type.accept(this);
		Identifier id = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		String label = ctx.label.getText();

		return new Question(id, label, type, getCodeLocation(ctx));
	}

	@Override
	public ComputedQuestion visitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx) {
		Type type = (Type) ctx.type.accept(this);
		Identifier id = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		String label = ctx.label.getText();
		Expression expr = (Expression) ctx.expr.accept(this);

		return new ComputedQuestion(id, label, type, expr, getCodeLocation(ctx));
	}

	@Override
	public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {
		Expression expr = (Expression) ctx.expr.accept(this);
		Block block = (Block) ctx.ifBody.accept(this);

		return new IfStatement(expr, block, getCodeLocation(ctx));
	}

	@Override
	public IfElseStatement visitIfElseStatement(QLParser.IfElseStatementContext ctx) {
		Expression expr = (Expression) ctx.expr.accept(this);
		Block blockIf = (Block) ctx.ifBody.accept(this);

		Block blockElse = (Block) ctx.elseBody.accept(this);

		return new IfElseStatement(expr, blockIf, blockElse, getCodeLocation(ctx));
	}

	@Override
	public Equality visitEqualityExpressions(QLParser.EqualityExpressionsContext ctx) {
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch (ctx.op.getText()) {
			case "==":
				return new Equal(left, right, getCodeLocation(ctx));
			case "!=":
				return new NotEqual(left, right, getCodeLocation(ctx));
			default:
				throw new AssertionError("The EqualityExpressions in there grammar are flawed "
						+ "and have lead to a incorrect case.");
		}
	}

	@Override
	public Multiplicative visitMultiplicativeExpressions(QLParser.MultiplicativeExpressionsContext ctx) {
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch (ctx.op.getText()) {
			case "*":
				return new Multiplication(left, right, getCodeLocation(ctx));
			case "/":
				return new Division(left, right, getCodeLocation(ctx));
			default:
				throw new AssertionError("The MultiplicativeExpressions in there grammar are flawed "
						+ "and have lead to a incorrect case.");
		}
	}

	@Override
	public Additive visitAdditiveExpressions(QLParser.AdditiveExpressionsContext ctx) {
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch (ctx.op.getText()) {
			case "+":
				return new Addition(left, right, getCodeLocation(ctx));
			case "-":
				return new Subtraction(left, right, getCodeLocation(ctx));
			default:
				throw new AssertionError("The AdditiveExpressions in there grammar are flawed "
						+ "and have lead to a incorrect case.");
		}
	}

	@Override
	public Relational visitRelationalExpressions(QLParser.RelationalExpressionsContext ctx) {
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch (ctx.op.getText()) {
			case ">":
				return new Greater(left, right, getCodeLocation(ctx));
			case ">=":
				return new GreaterEqual(left, right, getCodeLocation(ctx));
			case "<":
				return new Less(left, right, getCodeLocation(ctx));
			case "<=":
				return new LessEqual(left, right, getCodeLocation(ctx));
			default:
				throw new AssertionError("The RelationalExpressions in there grammar are flawed "
						+ "and have lead to a incorrect case.");
		}
	}

	@Override
	public Identifier visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
		return new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
	}

	@Override
	public Parenthesis visitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx) {
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Parenthesis(expr, getCodeLocation(ctx));
	}

	@Override
	public Not visitNotExpression(QLParser.NotExpressionContext ctx) {
		Expression expr = (Expression) ctx.expr.accept(this);
		
		return new Not(expr, getCodeLocation(ctx));
	}

	@Override
	public Literal visitLiteralExpression(QLParser.LiteralExpressionContext ctx) {
		Literal lit = (Literal) ctx.literalValue.accept(this);
		
		return lit;
	}

	@Override
	public Conditional visitConditionalExpressions(QLParser.ConditionalExpressionsContext ctx) {
		Expression left = (Expression) ctx.left.accept(this);
		Expression right = (Expression) ctx.right.accept(this);
		
		switch (ctx.op.getText()) {
			case "&&":
				return new And(left, right, getCodeLocation(ctx));
			case "||":
				return new Or(left, right, getCodeLocation(ctx));
			default:
				throw new AssertionError("The ConditionalExpressions in there grammar are flawed "
						+ "and have lead to a incorrect case.");
		}
	}

	@Override
	public IntegerLit visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
		return new IntegerLit(Integer.parseInt(ctx.getText()), getCodeLocation(ctx));
	}
	
	@Override 
	public MoneyLit visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
		return new MoneyLit(BigDecimal.valueOf(Double.parseDouble(ctx.getText())), getCodeLocation(ctx));
	}

	@Override
	public BooleanLit visitBooleanliteral(QLParser.BooleanliteralContext ctx) {
		return new BooleanLit(Boolean.parseBoolean(ctx.getText()), getCodeLocation(ctx));
	}

	@Override
	public StringLit visitStringLiteral(QLParser.StringLiteralContext ctx) {
		return new StringLit(ctx.getText(), getCodeLocation(ctx));
	}
	
	@Override
	public IntegerType visitIntegerType(QLParser.IntegerTypeContext ctx) {
		return new IntegerType(getCodeLocation(ctx));
	}

	@Override
	public StringType visitStringType(QLParser.StringTypeContext ctx) {
		return new StringType(getCodeLocation(ctx));
	}

	@Override
	public BooleanType visitBooleanType(QLParser.BooleanTypeContext ctx) {
		return new BooleanType(getCodeLocation(ctx));
	}

	@Override
	public MoneyType visitMoneyType(QLParser.MoneyTypeContext ctx) {
		return new MoneyType(getCodeLocation(ctx));
	}
	
	private CodeLocation getCodeLocation(ParserRuleContext ctx) {
		return new CodeLocation(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

}
