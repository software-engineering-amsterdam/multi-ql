package semanticAction.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
//import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.calculation.Add;
import semanticAction.tree.expressionNode.calculation.Division;
import semanticAction.tree.expressionNode.calculation.SUB;
import semanticAction.tree.expressionNode.calculation.Time;
import semanticAction.tree.expressionNode.comparison.Equal;
import semanticAction.tree.expressionNode.comparison.GreaterEqual;
import semanticAction.tree.expressionNode.comparison.GreaterThan;
import semanticAction.tree.expressionNode.comparison.LessEqual;
import semanticAction.tree.expressionNode.comparison.LessThan;
import semanticAction.tree.expressionNode.comparison.NotEqual;
import semanticAction.tree.expressionNode.literal.*;
import semanticAction.tree.expressionNode.logical.And;
import semanticAction.tree.expressionNode.logical.OR;
import semanticAction.tree.expressionNode.unary.Minus;
import semanticAction.tree.expressionNode.unary.NOT;
import semanticAction.tree.expressionNode.unary.Plus;
import semanticAction.tree.formNode.Form;
import semanticAction.tree.questionNode.CalculatedQuestion;
import semanticAction.tree.questionNode.IfElseQuestion;
import semanticAction.tree.questionNode.IfQuestion;
import semanticAction.tree.questionNode.NormalQuestion;
import semanticAction.tree.questionNode.AbsQuestion;
import semanticAction.tree.typeNode.*;
import antlrProducts.*;

/**
 * Goal of this class is Creating a Abstract syntax tree visitor witch is
 * inherited from QLBaseVisitor. <AbstractSyntaxTree> is visitor type. It means
 * AbstractSyntaxTree visits each node, each node has a Accept method. the
 * visitor calls the accept method and ask for its class. Accept() method calls
 * the visit() method of the visitor to response. means each method is inherited
 * from QLBaseVisitor and implemented base on the requirement of ASTNodeVisitor
 * class. the new entry.
 */
public class AstNodeVisitor extends QLBaseVisitor<AbstractSyntaxTree> {
	public AstNodeVisitor() {
	};

	/**
	 * Method "visitForm" uses a ArrayList to collect the questions. it return a
	 * new Form which is included the form's id and a list of the questions.
	 */
	// @Override
	public AbstractSyntaxTree visitForm(@NotNull QLParser.FormContext context) {
		ArrayList<AbsQuestion> questions = new ArrayList<AbsQuestion>();

		for (QLParser.QuestionContext q : context.question()) {
			questions.add((AbsQuestion) q.accept(this));
		}

		return new Form(context.IDENTIFIER().getText(), questions);
	}

	/**
	 * THis method uses "casting" to refer the exact type of question because
	 * the AbstractSyntaxTree can not convert to the "AbsType". HashMap<> is
	 * used in AbsQuestion node to make the "identifier" unique.
	 */
	@Override
	public AbstractSyntaxTree visitNormalQuestion(
			@NotNull QLParser.NormalQuestionContext context) {
		AbsType type = (AbsType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);

		return new NormalQuestion(identifier, context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""), (AbsType) context.type().accept(
				this));
	}

	@Override
	public AbstractSyntaxTree visitCalculatedQuestion(
			@NotNull QLParser.CalculatedQuestionContext context) {
		AbsType type = (AbsType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);

		return new CalculatedQuestion(identifier, context.STRINGLITERAL()
				.getText().replaceAll("^\"|\"$", ""), (AbsType) context.type()
				.accept(this), (AbsExpression) context.expression()
				.accept(this));
	}

	@Override
	public AbstractSyntaxTree visitIfQuestion(
			@NotNull QLParser.IfQuestionContext context) {
		List<AbsQuestion> ifquestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifquestions.add((AbsQuestion) q.accept(this));
		}
		return new IfQuestion(
				(AbsExpression) context.expression().accept(this), ifquestions);

	}

	/**
	 * to create ifElsequestion is a ArrayList is used to add the question
	 * for-loop is used to add the ifElsequestion. The method return a new
	 * IfElseQuestion
	 */

	@Override
	public AbstractSyntaxTree visitIfElseQuestion(
			@NotNull QLParser.IfElseQuestionContext context) {

		List<AbsQuestion> ifQuestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifQuestions.add((AbsQuestion) q.accept(this));
		}
		List<AbsQuestion> elseQuestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			elseQuestions.add((AbsQuestion) q.accept(this));
		}
		return new IfElseQuestion((AbsExpression) context.accept(this),
				ifQuestions, elseQuestions);
	}

	// AST visit the subNode of AbsExpression
	/**
	 * uses data structure BinSearchTree Search Tree (BST) to access the
	 * subNodes of AbsExpression. First left node then right node will be
	 * visited by Visitor. / This data Structure is easy to modifiable. The Next
	 * part of the code consist of: developing AbsExpression's calculation,
	 * comparison, logical base of BST.
	 * 
	 */
	@Override
	public AbstractSyntaxTree visitAndExpr(
			@NotNull QLParser.AndExprContext context) {

		return new And((AbsExpression) context.expression(0).accept(this),
				(AbsExpression) context.expression(1).accept(this));
	}

	@Override
	public AbstractSyntaxTree visitORExpr(
			@NotNull QLParser.ORExprContext context) {
		return new OR((AbsExpression) context.expression(0).accept(this),
				(AbsExpression) context.expression(1).accept(this));
	}

	// ASTNode visit Calculation

	@Override
	public AbstractSyntaxTree visitAddSubExpr(
			@NotNull QLParser.AddSubExprContext context) {

		if (context.op.getText().equals("+")) {
			return new Add((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		if (context.op.getText().equals("-")) {
			return new SUB((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public AbstractSyntaxTree visitTimeDivExpr(
			@NotNull QLParser.TimeDivExprContext context) {

		if (context.op.getText().equals("*")) {
			return new Time((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		if (context.op.getText().equals("/")) {
			return new Division((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	// ASTNode visit Comparison
	@Override
	public AbstractSyntaxTree visitCOMPExpr(
			@NotNull QLParser.COMPExprContext context) {

		if (context.op.getText().equals(">")) {
			return new GreaterThan((AbsExpression) context.expression(0)
					.accept(this), (AbsExpression) context.expression(1)
					.accept(this));
		}

		if (context.op.getText().equals(">=")) {
			return new GreaterEqual((AbsExpression) context.expression(0)
					.accept(this), (AbsExpression) context.expression(1)
					.accept(this));
		}

		if (context.op.getText().equals("<")) {
			return new LessThan((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}
		if (context.op.getText().equals("<=")) {
			return new LessEqual((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public AbstractSyntaxTree visitEQUALExpr(
			@NotNull QLParser.EQUALExprContext context) {
		if (context.op.getText().equals("==")) {
			return new Equal(
					(AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}
		if (context.op.getText().equals("!=")) {
			return new NotEqual((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitUnaryExpr(
			@NotNull QLParser.UnaryExprContext context) {
		if (context.op.getText().equals("!")) {
			return new NOT((AbsExpression) context.expression().accept(this));
		}
		if (context.op.getText().equals("+")) {
			return new Plus((AbsExpression) context.expression().accept(this));
		}
		if (context.op.getText().equals("-")) {
			return new Minus((AbsExpression) context.expression().accept(this));
		}
		return null;

	}

	// /....ASTNode visit the subNode of ql type and return a new type of QL
	// each of them

	@Override
	public AbstractSyntaxTree visitBooleanQL_type(
			@NotNull QLParser.BooleanQL_typeContext context) {
		return new BooleanQL_Type();

	}

	@Override
	public AbstractSyntaxTree visitIntegerQL_type(
			@NotNull QLParser.IntegerQL_typeContext context) {
		return new IntegerQL_Type();

	}

	public AbstractSyntaxTree visitStringQL_type(
			@NotNull QLParser.StringQL_typeContext context) {
		return new StringQL_Type();

	}

	// AST Visit AbsExpression literal

	/**
	 * In the literal's subNodes Boolean literal and IntegerLiteral, The
	 * valueOf() method is used to return the object that hold the value of the
	 * represented object. ....................
	 */

	public AbstractSyntaxTree visitBooleanliteral(
			@NotNull QLParser.BooleanliteralContext context) {
		return new Booleanliteral(Boolean.valueOf(context.BOOLEANLITERAL()
				.getText()));

	}

	public AbstractSyntaxTree visitStringliteral(
			@NotNull QLParser.StringliteralContext context) {
		return new Stringliteral(context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""));

	}

	public AbstractSyntaxTree visitIntegerliteral(
			@NotNull QLParser.IntegerliteralContext context) {
		return new Integerliteral(Integer.valueOf(context.INT().getText()));
	}
}