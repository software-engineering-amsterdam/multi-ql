package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.questionnaire.Questionnaire;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.Statement;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.parser.antlr.QLBaseVisitor;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.BlockContext;
import org.uva.sea.ql.parser.antlr.QLParser.FormContext;
import org.uva.sea.ql.parser.antlr.QLParser.StatementContext;


public class MyQLVisitor extends QLBaseVisitor<ASTNode> {
	
	
	@Override 
	public ASTNode visitQuestionnaire(QLParser.QuestionnaireContext ctx) { 
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Questionnaire questionnaire = new Questionnaire(fragment);
		for (FormContext formContext: ctx.form())
			questionnaire.getForms().add((Form) formContext.accept(this));
		return questionnaire; 
	}
	
	
	// if visitBlock below actually needed? CHECK!
	// needed...ctx.block....
	
	@Override 
	public ASTNode visitForm(QLParser.FormContext ctx) { 
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		String id = ctx.Identifier().getText();
		Block block = (Block) ctx.block().accept(this);
		Form form = new Form(id, block, fragment);	
		return  form;
	}
	
	@Override 
	public ASTNode visitBlock(QLParser.BlockContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Block block = new Block(fragment);
		for (StatementContext statementContext: ctx.statement())
			block.getStatements().add((Statement) statementContext.accept(this));
		return block; 
	}
	
	
	@Override 
	public ASTNode visitIf(QLParser.IfContext ctx) { 
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ctx.expression().accept(this);
		Block block = (Block) ctx.block().accept(this);					// mhpws ifBody?
		IfStatement ifStatement = new IfStatement(expression, block, fragment);
		return ifStatement; 
	}
	
	// below to be modified/removed
	
	@Override 
	public ASTNode visitIfElse(QLParser.IfElseContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ((BlockContext) ctx.expression()).accept(this);
		Block block = (Block) ctx.ifBody.accept(this);
		Block elseBlock = (Block) ctx.elseifBody.accept(this);
		IfElseStatement ifElseStatement = new IfElseStatement(expression, block, elseBlock, fragment);
		return ifElseStatement; 
	}
	
	
	
	@Override 
	public ASTNode visitQuestionCompute(QLParser.QuestionComputeContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Identifier id = (Identifier) ctx.identi.accept(this);
		
		// below part to be checked...
		
		// void purifyLabel(String arg) to be created . . . (remove fuckin quotes)
		
//		String fullLabel = ctx.questionLabel().getText();
//		String pureLabel = purifyLabel(fullLabel);
//		StringLiteral label = new StringLiteral(fragment,pureLabel);
//		String finalLabel = label.toString();
				
		StringLiteral label = new StringLiteral(fragment,ctx.questionLabel().getText());
		String finalLabel = label.toString();
		Type type = (Type) ctx.questionType().accept(this);
		Expression expression = (Expression) ctx.expression().accept(this);
		ComputedQuestion computedQuestion = new ComputedQuestion(fragment, id, finalLabel, type, expression);
		return computedQuestion; 
	}
	
	
	
	@Override public ASTNode visitQuestionNormal(QLParser.QuestionNormalContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Identifier id = (Identifier) ctx.identi.accept(this);
		
//		String fullLabel = ctx.questionLabel().getText();
//		String pureLabel = purifyLabel(fullLabel);
//		StringLiteral label = new StringLiteral(fragment,pureLabel);
//		String finalLabel = label.toString();
		
		StringLiteral label = new StringLiteral(fragment,ctx.questionLabel().getText());
		String finalLabel = label.toString();
		Type type = (Type) ctx.questionType().accept(this);
		Question question = new Question(id, finalLabel, type, fragment);
		return question; 
	}
	
	
	@Override public ASTNode visitQuestionIdentifier(QLParser.QuestionIdentifierContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Identifier identifier = new Identifier(fragment, ctx.Identifier().getText());
		return identifier;
	}
	

	// ASK BELOW !
	
	@Override 
	public ASTNode visitQuestionLabel(QLParser.QuestionLabelContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		StringLiteral stringLiteral = new StringLiteral(fragment, ctx.StringLiteral().getText());
		return stringLiteral; 
	}
	
	
	@Override 
	public ASTNode visitTypeInt(QLParser.TypeIntContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		IntType intType = new IntType(fragment);
		return intType; 
	}
	
	
	@Override 
	public ASTNode visitTypeStr(QLParser.TypeStrContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		StrType strType = new StrType(fragment);
		return strType; 
	}
	
	
	@Override 
	public ASTNode visitTypeBool(QLParser.TypeBoolContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		BoolType boolType = new BoolType(fragment);
		return boolType; 
	}
	
	
//	@Override 
//	public ASTNode visitTypeDouble(QLParser.TypeDoubleContext ctx) {
//		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
//		DoubleType doubleType = new DoubleType(fragment);
//		return doubleType;	
//	}
	
		
	@Override 
	public ASTNode visitExprGreater(QLParser.ExprGreaterContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Greater greater = new Greater(fragment, rightExpression, leftExpression);
		return greater;
	}
	
	
	@Override 
	public ASTNode visitExprNotEqual(QLParser.ExprNotEqualContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		NotEqual notEqual = new NotEqual(fragment, rightExpression, leftExpression);
		return notEqual;
	}
	
	@Override 
	public ASTNode visitExprPlus(QLParser.ExprPlusContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Add add = new Add(fragment, rightExpression, leftExpression);
		return add;	
	}
	

	@Override 
	public ASTNode visitExprParentheses(QLParser.ExprParenthesesContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ctx.expression().accept(this);
		Parenthesis parenthesis = new Parenthesis(fragment, expression);
		return parenthesis;
	}
	
	
	@Override 
	public ASTNode visitExprLess(QLParser.ExprLessContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Less less = new Less(fragment, rightExpression, leftExpression);
		return less;
	}
	
	
	@Override 
	public ASTNode visitExprNot(QLParser.ExprNotContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ctx.expression().accept(this);
		Not not = new Not(fragment, expression);
		return not;	
	}
	
	
	@Override 
	public ASTNode visitExprLessEqual(QLParser.ExprLessEqualContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		LessOrEqual lessOrEqual = new LessOrEqual(fragment, rightExpression, leftExpression);
		return lessOrEqual;
	}
	
	
	@Override
	public ASTNode visitExprAnd(QLParser.ExprAndContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		And and = new And(fragment, rightExpression, leftExpression);
		return and;	
	}
	
	@Override 
	public ASTNode visitExprPositive(QLParser.ExprPositiveContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ctx.expression().accept(this);
		Positive positive = new Positive(fragment, expression);
		return positive;	
	}
	
	
	@Override 
	public ASTNode visitExprMinus(QLParser.ExprMinusContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Sub sub = new Sub(fragment, rightExpression, leftExpression);
		return sub;	
	}
	
	
	@Override
	public ASTNode visitExprOr(QLParser.ExprOrContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Or or = new Or(fragment, rightExpression, leftExpression);
		return or;
	}
	
	
	@Override 
	public ASTNode visitExprGreaterEqual(QLParser.ExprGreaterEqualContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		GreaterOrEqual greaterOrEqual = new GreaterOrEqual(fragment, rightExpression, leftExpression);
		return greaterOrEqual;
	}
	
	
	// is below needed?
	
//	@Override 
//	public ASTNode visitExprLiteral(QLParser.ExprLiteralContext ctx) {
//		return visitChildren(ctx); 
//	}
	

	@Override 
	public ASTNode visitExprMultiply(QLParser.ExprMultiplyContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Mul mul = new Mul(fragment, rightExpression, leftExpression);
		return mul;
	}
	
	@Override 
	public ASTNode visitExprNegative(QLParser.ExprNegativeContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression expression = (Expression) ctx.expression().accept(this);
		Negative negative = new Negative(fragment, expression);
		return negative;	
	}
	

	@Override 
	public ASTNode visitExprEqual(QLParser.ExprEqualContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Equal equal = new Equal(fragment, rightExpression, leftExpression);
		return equal;
	}
	
	@Override 
	public ASTNode visitExprDivide(QLParser.ExprDivideContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Expression leftExpression = (Expression) ctx.left.accept(this);
		Expression rightExpression = (Expression) ctx.right.accept(this);
		Div div = new Div(fragment, rightExpression, leftExpression);
		return div;
	}
	

	@Override 
	public ASTNode visitLiteralId(QLParser.LiteralIdContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		Identifier identifier = new Identifier(fragment, ctx.Identifier().getText());
		return identifier;
	}
	
	
	@Override 
	public ASTNode visitLiteralInt(QLParser.LiteralIntContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		IntegerLiteral integerLiteral = new IntegerLiteral(fragment, Integer.parseInt(ctx.getText()));
		return integerLiteral;
	}
	

	@Override 
	public ASTNode visitLiteralBool(QLParser.LiteralBoolContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		BooleanLiteral booleanLiteral = new BooleanLiteral(fragment, Boolean.parseBoolean(ctx.getText()));
		return booleanLiteral;
	}
	

	@Override 
	public ASTNode visitLiteralStr(QLParser.LiteralStrContext ctx) {
		CodeFragment fragment = CodeFragment.getCodeFragment(ctx);
		StringLiteral stringLiteral = new StringLiteral(fragment, ctx.StringLiteral().getText());
		return stringLiteral;	
	}
	
}
