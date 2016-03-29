package nl.uva.ql.ast.builder;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import nl.uva.ql.ast.AbstractNode;
import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.Form;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Conjunction;
import nl.uva.ql.ast.expression.binaryexpression.Disjunction;
import nl.uva.ql.ast.expression.binaryexpression.Division;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThan;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.LessThan;
import nl.uva.ql.ast.expression.binaryexpression.LessThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.Multiplication;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.expression.binaryexpression.Subtraction;
import nl.uva.ql.ast.expression.unaryexpression.Negation;
import nl.uva.ql.ast.literal.BooleanLiteral;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.ast.literal.StringLiteral;
import nl.uva.ql.ast.statement.ComputedQuestion;
import nl.uva.ql.ast.statement.IfElseStatement;
import nl.uva.ql.ast.statement.IfStatement;
import nl.uva.ql.ast.statement.Question;
import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.ast.type.BooleanType;
import nl.uva.ql.ast.type.IntegerType;
import nl.uva.ql.ast.type.MoneyType;
import nl.uva.ql.ast.type.StringType;
import nl.uva.ql.ast.type.Type;
import nl.uva.ql.ast.type.UnknownType;

import org.antlr.v4.runtime.ParserRuleContext;

import antlrsources.QLBaseVisitor;
import antlrsources.QLParser.AdditionSubtractionContext;
import antlrsources.QLParser.AndOrContext;
import antlrsources.QLParser.BooleanLiteralContext;
import antlrsources.QLParser.BoxContext;
import antlrsources.QLParser.ComparisonContext;
import antlrsources.QLParser.ComputedQuestionContext;
import antlrsources.QLParser.EqualityContext;
import antlrsources.QLParser.FormContext;
import antlrsources.QLParser.IdentifierLiteralContext;
import antlrsources.QLParser.IfElseStatementContext;
import antlrsources.QLParser.IfStatementContext;
import antlrsources.QLParser.IntegerLiteralContext;
import antlrsources.QLParser.MoneyLiteralContext;
import antlrsources.QLParser.MultiplyDivisionContext;
import antlrsources.QLParser.NegationContext;
import antlrsources.QLParser.ParenthesisContext;
import antlrsources.QLParser.QuestionContext;
import antlrsources.QLParser.StatementContext;
import antlrsources.QLParser.StringLiteralContext;

public class ASTBuilder extends QLBaseVisitor<AbstractNode> {

	private final String START_END_QUOTATION_PATTERN = "^\"|\"$";
	
	@Override
	public AbstractNode visitForm(FormContext ctx) {
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), getStartLine(ctx));
		Box box = (Box) ctx.box().accept(this);
		return new Form(identifier, box, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitBox(BoxContext ctx) {
		List<Statement> statements = new LinkedList<>();
		for (StatementContext statementContext: ctx.statement()) {
			statements.add((Statement) statementContext.accept(this));
		}
		return new Box(getStartLine(ctx), statements);
	}

	@Override
	public AbstractNode visitIfStatement(IfStatementContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		Box box = (Box) ctx.box().accept(this);
		return new IfStatement(expression, box, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitIfElseStatement(IfElseStatementContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		Box ifBox = (Box) ctx.ifBox.accept(this);
		Box elseBox = (Box) ctx.elseBox.accept(this);
		return new IfElseStatement(expression, ifBox, elseBox, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitQuestion(QuestionContext ctx) {
		int line = getStartLine(ctx);
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), line);
		String label = ctx.STRINGLITERAL().getText();
		Type type = getType(ctx.type().getText());
		return new Question(identifier, label, type, line);
	}

	@Override
	public AbstractNode visitComputedQuestion(ComputedQuestionContext ctx) {
		int line = getStartLine(ctx);
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), line);
		String label = ctx.STRINGLITERAL().getText();
		Type type = getType(ctx.type().getText());
		Expression expression = (Expression) ctx.expression().accept(this);
		return new ComputedQuestion(identifier, label, type, expression, line);
	}

	@Override
	public AbstractNode visitNegation(NegationContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		return new Negation(expression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitMultiplyDivision(MultiplyDivisionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		int line = getStartLine(ctx);
		
		switch (ctx.operation.getText()) {
		case "*":
			return new Multiplication(leftExpression, rightExpression, line);
		case "/":
			return new Division(leftExpression, rightExpression, line);
		default:
			throw new IllegalStateException("Unexpected operation " + ctx.operation.getText());
		}
	}

	@Override
	public AbstractNode visitEquality(EqualityContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		int line = getStartLine(ctx);
		
		switch (ctx.operation.getText()) {
		case "==":
			return new Equal(leftExpression, rightExpression, line);
		case "!=":
			return new NotEqual(leftExpression, rightExpression, line);
		default:
			throw new IllegalStateException("Unexpected operation " + ctx.operation.getText());
		}
	}

	@Override
	public AbstractNode visitAdditionSubtraction(AdditionSubtractionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		int line = getStartLine(ctx);
		
		switch (ctx.operation.getText()) {
		case "+":
			return new Addition(leftExpression, rightExpression, line);
		case "-":
			return new Subtraction(leftExpression, rightExpression, line);
		default:
			throw new IllegalStateException("Unexpected operation " + ctx.operation.getText());
		}
	}

	@Override
	public AbstractNode visitParenthesis(ParenthesisContext ctx) {
		return (Expression) ctx.expression().accept(this);
	}

	@Override
	public AbstractNode visitComparison(ComparisonContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		int line = getStartLine(ctx);
		
		switch (ctx.operation.getText()) {
		case ">=" :
			return new GreaterThanEqual(leftExpression, rightExpression, line);
		case ">":
			return new GreaterThan(leftExpression, rightExpression, line);
		case "<=":
			return new LessThanEqual(leftExpression, rightExpression, line);
		case "<":
			return new LessThan(leftExpression, rightExpression, line);
		default:
			throw new IllegalStateException("Unexpected operation " + ctx.operation.getText());
		}
	}

	@Override
	public AbstractNode visitAndOr(AndOrContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		int line = getStartLine(ctx);
		
		switch (ctx.operation.getText()) {
		case "&&":
			return new Conjunction(leftExpression, rightExpression, line);
		case "||":
			return new Disjunction(leftExpression, rightExpression, line);
		default:
			throw new IllegalStateException("Unexpected operation " + ctx.operation.getText());
		}
	}

	@Override
	public AbstractNode visitBooleanLiteral(BooleanLiteralContext ctx) {
		return new BooleanLiteral(Boolean.parseBoolean(ctx.getText()), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitIntegerLiteral(IntegerLiteralContext ctx) {
		return new IntegerLiteral(Integer.parseInt(ctx.getText()), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitIdentifierLiteral(IdentifierLiteralContext ctx) {
		return new Identifier(ctx.getText(), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitStringLiteral(StringLiteralContext ctx) {
		return new StringLiteral(ctx.getText().replaceAll(START_END_QUOTATION_PATTERN, ""), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitMoneyLiteral(MoneyLiteralContext ctx) {
		return new MoneyLiteral(new BigDecimal(ctx.getText()), getStartLine(ctx));
	}
	
	private Type getType(String type){
		switch(type.toLowerCase()){
		case "string":
			return new StringType();
		case "boolean":
			return new BooleanType();
		case "integer":
			return new IntegerType();
		case "money":
			return new MoneyType();
		default:
			return new UnknownType();
		}
	}
	
	private int getStartLine(ParserRuleContext ctx) {
		return ctx.getStart().getLine();
	}

}
