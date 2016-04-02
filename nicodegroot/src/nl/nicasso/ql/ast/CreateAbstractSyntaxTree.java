package nl.nicasso.ql.ast;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.nicasso.ql.antlr.QLBaseVisitor;
import nl.nicasso.ql.antlr.QLParser;
import nl.nicasso.ql.antlr.QLParser.StatementContext;
import nl.nicasso.ql.antlr.QLVisitor;
import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.expressions.Parenthesis;
import nl.nicasso.ql.ast.nodes.expressions.additive.Addition;
import nl.nicasso.ql.ast.nodes.expressions.additive.Additive;
import nl.nicasso.ql.ast.nodes.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.nodes.expressions.conditional.And;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Conditional;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Or;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equal;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equality;
import nl.nicasso.ql.ast.nodes.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplicative;
import nl.nicasso.ql.ast.nodes.expressions.relational.Greater;
import nl.nicasso.ql.ast.nodes.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.nodes.expressions.relational.Less;
import nl.nicasso.ql.ast.nodes.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.nodes.expressions.relational.Relational;
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.Literal;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;

public class CreateAbstractSyntaxTree extends QLBaseVisitor<ASTNode> implements QLVisitor<ASTNode> {

	private static final String GRAMMAR_ERROR = "The {0} in there grammar are flawed and have lead to a incorrect case.";

	private Form abstractSyntaxTree;

	public CreateAbstractSyntaxTree(ParseTree parseTree) {
		abstractSyntaxTree = (Form) parseTree.accept(this);
	}

	public Form getAbstractSyntaxTree() {
		return abstractSyntaxTree;
	}

	public String getGrammerErrorMessage(String expression) {
		return MessageFormat.format(CreateAbstractSyntaxTree.GRAMMAR_ERROR, expression);
	}

	@Override
	public Form visitForm(QLParser.FormContext ctx) {
		Identifier identifier = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		Block block = (Block) ctx.block().accept(this);

		return new Form(identifier, block, getCodeLocation(ctx));
	}

	@Override
	public Block visitBlock(QLParser.BlockContext ctx) {
		List<Statement> statements = new ArrayList<Statement>();

		for (StatementContext curentStatement : ctx.statement()) {
			statements.add((Statement) curentStatement.accept(this));
		}

		return new Block(statements, getCodeLocation(ctx));
	}

	@Override
	public Question visitQuestionStatement(QLParser.QuestionStatementContext ctx) {
		Type type = (Type) ctx.type.accept(this);
		Identifier identifier = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		String label = removeStringQuotes(ctx.label.getText());

		return new Question(identifier, label, type, getCodeLocation(ctx));
	}

	@Override
	public ComputedQuestion visitComputedQuestionStatement(QLParser.ComputedQuestionStatementContext ctx) {
		Type type = (Type) ctx.type.accept(this);
		Identifier identifier = new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
		String label = removeStringQuotes(ctx.label.getText());
		Expression expression = (Expression) ctx.expr.accept(this);

		return new ComputedQuestion(identifier, label, type, expression, getCodeLocation(ctx));
	}

	@Override
	public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {
		Expression expression = (Expression) ctx.expr.accept(this);
		Block block = (Block) ctx.ifBody.accept(this);

		return new IfStatement(expression, block, getCodeLocation(ctx));
	}

	@Override
	public IfElseStatement visitIfElseStatement(QLParser.IfElseStatementContext ctx) {
		Expression expression = (Expression) ctx.expr.accept(this);
		Block blockIf = (Block) ctx.ifBody.accept(this);
		Block blockElse = (Block) ctx.elseBody.accept(this);

		return new IfElseStatement(expression, blockIf, blockElse, getCodeLocation(ctx));
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
			throw new AssertionError(getGrammerErrorMessage("EqualityExpressions"));
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
			throw new AssertionError(getGrammerErrorMessage("MultiplicativeExpressions"));
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
			throw new AssertionError(getGrammerErrorMessage("AdditiveExpressions"));
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
			throw new AssertionError(getGrammerErrorMessage("RelationalExpressions"));
		}
	}

	@Override
	public Identifier visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
		return new Identifier(ctx.identifier.getText(), getCodeLocation(ctx));
	}

	@Override
	public Parenthesis visitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx) {
		Expression expression = (Expression) ctx.expr.accept(this);

		return new Parenthesis(expression, getCodeLocation(ctx));
	}

	@Override
	public Not visitNotExpression(QLParser.NotExpressionContext ctx) {
		Expression expression = (Expression) ctx.expr.accept(this);

		return new Not(expression, getCodeLocation(ctx));
	}

	@Override
	public Literal visitLiteralExpression(QLParser.LiteralExpressionContext ctx) {
		Literal literal = (Literal) ctx.literalValue.accept(this);

		return literal;
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
			throw new AssertionError(getGrammerErrorMessage("ConditionalExpressions"));
		}
	}

	@Override
	public IntegerLiteral visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
		return new IntegerLiteral(Integer.parseInt(ctx.getText()), getCodeLocation(ctx));
	}

	@Override
	public MoneyLiteral visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
		return new MoneyLiteral(BigDecimal.valueOf(Double.parseDouble(ctx.getText())), getCodeLocation(ctx));
	}

	@Override
	public BooleanLiteral visitBooleanliteral(QLParser.BooleanliteralContext ctx) {
		return new BooleanLiteral(Boolean.parseBoolean(ctx.getText()), getCodeLocation(ctx));
	}

	@Override
	public StringLiteral visitStringLiteral(QLParser.StringLiteralContext ctx) {
		return new StringLiteral(removeStringQuotes(ctx.getText()), getCodeLocation(ctx));
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

	private String removeStringQuotes(String targetString) {
		return targetString.substring(1, targetString.length() - 1);
	}

}
