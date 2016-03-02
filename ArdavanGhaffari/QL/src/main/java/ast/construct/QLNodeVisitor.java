package ast.construct;

import org.antlr.v4.runtime.ParserRuleContext;

import antlrsources.QLBaseVisitor;
import antlrsources.QLParser.AdditionContext;
import antlrsources.QLParser.BooleanLiteralContext;
import antlrsources.QLParser.BoxContext;
import antlrsources.QLParser.ComputedQuestionContext;
import antlrsources.QLParser.ConjunctionContext;
import antlrsources.QLParser.DecimalLiteralContext;
import antlrsources.QLParser.DisjunctionContext;
import antlrsources.QLParser.DivisionContext;
import antlrsources.QLParser.EqualContext;
import antlrsources.QLParser.FormContext;
import antlrsources.QLParser.GreaterThanContext;
import antlrsources.QLParser.GreaterThanEqualContext;
import antlrsources.QLParser.IdentifierLiteralContext;
import antlrsources.QLParser.IfElseStatementContext;
import antlrsources.QLParser.IfStatementContext;
import antlrsources.QLParser.IntegerLiteralContext;
import antlrsources.QLParser.LessThanContext;
import antlrsources.QLParser.LessThanEqualContext;
import antlrsources.QLParser.MultiplicationContext;
import antlrsources.QLParser.NegationContext;
import antlrsources.QLParser.NotEqualContext;
import antlrsources.QLParser.ParenthesisContext;
import antlrsources.QLParser.QuestionContext;
import antlrsources.QLParser.StatementContext;
import antlrsources.QLParser.StringLiteralContext;
import antlrsources.QLParser.SubtractionContext;
import ast.model.AbstractNode;
import ast.model.Box;
import ast.model.Expression;
import ast.model.Form;
import ast.model.Statement;
import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.Conjunction;
import ast.model.binaryexpression.Disjunction;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThanEqual;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.LessThanEqual;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.NotEqual;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.Identifier;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.statement.ComputedQuestion;
import ast.model.statement.IfElseStatement;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;
import ast.model.type.BooleanType;
import ast.model.type.DecimalType;
import ast.model.type.IntegerType;
import ast.model.type.StringType;
import ast.model.type.Type;
import ast.model.type.UnknownType;
import ast.model.unaryexpression.Negation;

public class QLNodeVisitor extends QLBaseVisitor<AbstractNode>{

	@Override
	public AbstractNode visitForm(FormContext ctx) {
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), getStartLine(ctx));
		Box box = (Box) ctx.box().accept(this);
		return new Form(identifier, box, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitBox(BoxContext ctx) {
		Box box = new Box(getStartLine(ctx));
		for (StatementContext statementContext: ctx.statement()) {
			box.addStatement((Statement) statementContext.accept(this));
		}
		return box;
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
	public AbstractNode visitMultiplication(MultiplicationContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Multiplication(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitDivision(DivisionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Division(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitAddition(AdditionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Addition(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitSubtraction(SubtractionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Subtraction(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitGreaterThan(GreaterThanContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new GreaterThanEqual(leftExpression, rightExpression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitGreaterThanEqual(GreaterThanEqualContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new GreaterThanEqual(leftExpression, rightExpression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitLessThan(LessThanContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new LessThan(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitLessThanEqual(LessThanEqualContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new LessThanEqual(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitEqual(EqualContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Equal(leftExpression, rightExpression, getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitNotEqual(NotEqualContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new NotEqual(leftExpression, rightExpression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitConjunction(ConjunctionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Conjunction(leftExpression, rightExpression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitDisjunction(DisjunctionContext ctx) {
		Expression leftExpression = (Expression) ctx.leftOp.accept(this);
		Expression rightExpression = (Expression) ctx.rightOp.accept(this);
		return new Disjunction(leftExpression, rightExpression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitNegation(NegationContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		return new Negation(expression, getStartLine(ctx));
	}

	@Override
	public AbstractNode visitParenthesis(ParenthesisContext ctx) {
		return (Expression) ctx.expression().accept(this);
	}

	@Override
	public AbstractNode visitIdentifierLiteral(IdentifierLiteralContext ctx) {
		return new Identifier(ctx.getText(), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitIntegerLiteral(IntegerLiteralContext ctx) {
		return new IntegerLiteral(Integer.parseInt(ctx.getText()), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitBooleanLiteral(BooleanLiteralContext ctx) {
		return new BooleanLiteral(Boolean.parseBoolean(ctx.getText()), getStartLine(ctx));
	}

	@Override
	public AbstractNode visitStringLiteral(StringLiteralContext ctx) {
		return new StringLiteral(ctx.getText(), getStartLine(ctx));
	}
	
	@Override
	public AbstractNode visitDecimalLiteral(DecimalLiteralContext ctx) {
		return new DecimalLiteral(Double.parseDouble(ctx.getText()), getStartLine(ctx));
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
			return new DecimalType();
		default:
			return new UnknownType();
		}
	}
	
	private int getStartLine(ParserRuleContext ctx) {
		return ctx.getStart().getLine();
	}
}
