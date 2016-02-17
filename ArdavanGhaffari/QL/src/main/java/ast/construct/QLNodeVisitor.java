package ast.construct;

import antlrsources.QLBaseVisitor;
import antlrsources.QLParser.BoolExpressionContext;
import antlrsources.QLParser.BooleanLiteralContext;
import antlrsources.QLParser.BoxContext;
import antlrsources.QLParser.CompareExpressionContext;
import antlrsources.QLParser.ComputedQuestionContext;
import antlrsources.QLParser.FormContext;
import antlrsources.QLParser.IdentifierLiteralContext;
import antlrsources.QLParser.IfElseStatementContext;
import antlrsources.QLParser.IfStatementContext;
import antlrsources.QLParser.IntegerLiteralContext;
import antlrsources.QLParser.MathExpressionContext;
import antlrsources.QLParser.NotExpressionContext;
import antlrsources.QLParser.ParExpressionContext;
import antlrsources.QLParser.QuestionContext;
import antlrsources.QLParser.StatementContext;
import antlrsources.QLParser.StringLiteralContext;
import ast.model.AbstractNode;
import ast.model.Box;
import ast.model.Form;
import ast.model.expression.BoolExpression;
import ast.model.expression.CompareExpression;
import ast.model.expression.Expression;
import ast.model.expression.MathExpression;
import ast.model.expression.NotExpression;
import ast.model.expression.literal.BoolLiteral;
import ast.model.expression.literal.Identifier;
import ast.model.expression.literal.IntLiteral;
import ast.model.expression.literal.StringLiteral;
import ast.model.expression.operation.BoolOperation;
import ast.model.expression.operation.CompareOperation;
import ast.model.expression.operation.MathOperation;
import ast.model.statement.ComputedQuestion;
import ast.model.statement.IfElseStatement;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;
import ast.model.statement.Statement;
import ast.model.type.Type;

public class QLNodeVisitor extends QLBaseVisitor<AbstractNode> {

	@Override
	public AbstractNode visitMathExpression(MathExpressionContext ctx) {
		Expression leftExp = (Expression) ctx.leftOp.accept(this);
		Expression rightExp = (Expression) ctx.rightOp.accept(this);
		MathOperation operation = MathOperation.getOperation(ctx.operation.getText());
		return new MathExpression(leftExp, rightExp, operation);
	}

	@Override
	public AbstractNode visitIdentifierLiteral(IdentifierLiteralContext ctx) {
		return new Identifier(ctx.getText());
	}

	@Override
	public AbstractNode visitIntegerLiteral(IntegerLiteralContext ctx) {
		return new IntLiteral(Integer.parseInt(ctx.getText()));
	}

	@Override
	public AbstractNode visitBooleanLiteral(BooleanLiteralContext ctx) {
		return new BoolLiteral(Boolean.parseBoolean(ctx.getText()));
	}

	@Override
	public AbstractNode visitStringLiteral(StringLiteralContext ctx) {
		return new StringLiteral(ctx.getText());
	}

	@Override
	public AbstractNode visitForm(FormContext ctx) {
		String name = ctx.IDENTIFIER().getText();
		Box box = (Box) ctx.box().accept(this);
		return new Form(name, box);
	}

	@Override
	public AbstractNode visitBox(BoxContext ctx) {
		Box box = new Box();
		for (StatementContext statementContext: ctx.statement()) {
			box.addStatement((Statement) statementContext.accept(this));
		}
		return box;
	}

	@Override
	public AbstractNode visitIfStatement(IfStatementContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		Box box = (Box) ctx.box().accept(this);
		return new IfStatement(expression, box);
	}

	@Override
	public AbstractNode visitIfElseStatement(IfElseStatementContext ctx) {
		Expression expression = (Expression) ctx.expression().accept(this);
		Box ifBox = (Box) ctx.ifBox.accept(this);
		Box elseBox = (Box) ctx.elseBox.accept(this);
		return new IfElseStatement(expression, ifBox, elseBox);
	}

	@Override
	public AbstractNode visitQuestion(QuestionContext ctx) {
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
		String label = ctx.STRINGLITERAL().getText();
		Type type = Type.getType(ctx.type().getText());
		return new Question(identifier, label, type);
	}

	@Override
	public AbstractNode visitComputedQuestion(ComputedQuestionContext ctx) {
		Identifier identifier = new Identifier(ctx.IDENTIFIER().getText());
		String label = ctx.STRINGLITERAL().getText();
		Type type = Type.getType(ctx.type().getText());
		Expression expression = (Expression) ctx.expression().accept(this);
		return new ComputedQuestion(identifier, label, type, expression);
	}

	@Override
	public AbstractNode visitParExpression(ParExpressionContext ctx) {
		return (Expression) ctx.expression().accept(this);
	}

	@Override
	public AbstractNode visitBoolExpression(BoolExpressionContext ctx) {
		Expression leftExp = (Expression) ctx.leftOp.accept(this);
		Expression rightExp = (Expression) ctx.rightOp.accept(this);
		BoolOperation operation;
		switch (ctx.operation.getText()) {
		case "&&":
			operation = BoolOperation.AND;
			break;
		case "||":
			operation = BoolOperation.OR;
			break;
		default:
			throw new IllegalArgumentException("Unknown boolean operation " + ctx.operation.getText());
		}
		return new BoolExpression(leftExp, rightExp, operation);
	}

	@Override
	public AbstractNode visitNotExpression(NotExpressionContext ctx) {
		return new NotExpression((Expression) ctx.expression().accept(this));
	}

	@Override
	public AbstractNode visitCompareExpression(CompareExpressionContext ctx) {
		Expression leftExp = (Expression) ctx.leftOp.accept(this);
		Expression rightExp = (Expression) ctx.rightOp.accept(this);
		CompareOperation operation = CompareOperation.getOperation(ctx.operation.getText());
		return new CompareExpression(leftExp, rightExp, operation);
	}

	
}
