package ql;

import ql.ast.Block;
import ql.ast.ComputedQuestion;
import ql.ast.Form;
import ql.ast.InputQuestion;
import ql.ast.Question;
import ql.ast.QuestionType;
import ql.ast.Questionnaire;
import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryExpr;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.Ge;
import ql.ast.expression.IdentityExpr;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Le;
import ql.ast.expression.LiteralExpr;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.Or;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.UnaryExpr;
import ql.ast.literal.BooleanLiteral;
import ql.ast.literal.IntegerLiteral;
import ql.ast.literal.Literal;
import ql.ast.literal.MoneyLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.statement.IfElseStatement;
import ql.ast.statement.IfStatement;
import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.MoneyType;
import ql.ast.type.StringType;

public interface QLVisitorInterface<T> {

	public T visit(ASTNode node);
	

	public T visit(Block node);
	public T visit(Form node);
	public T visit(Question node);
	public T visit(InputQuestion node);
	public T visit(ComputedQuestion node);
	public T visit(Questionnaire node);
	// visit expressions
	public T visit(Add node);
	public T visit(And node);
	public T visit(BinaryExpr node);
	public T visit(Div node);
	public T visit(Eq node);
	public T visit(Ge node);
	public T visit(GEq node);
	public T visit(GT node);
	public T visit(Le node);
	public T visit(LEq node);
	public T visit(LT node);
	public T visit(Mul node);
	public T visit(Neg node);
	public T visit(NEq node);
	public T visit(Not node);
	public T visit(Or node);
	public T visit(Pos node);
	public T visit(Sub node);
	public T visit(UnaryExpr node);
	public T visit(IdentityExpr node);
	public T visit(LiteralExpr node);
	// visit statements
	public T visit(IfStatement node);
	public T visit(IfElseStatement node);
	// visit literals
	public T visit(Literal node);
	public T visit(BooleanLiteral node);
	public T visit(MoneyLiteral node);	
	public T visit(IntegerLiteral node);
	public T visit(StringLiteral node);
	// visit not quite literals. exclude these from project?
	public T visit(QuestionType node);
	public T visit(BooleanType node);
	public T visit(MoneyType node);	
	public T visit(IntegerType node);
	public T visit(StringType node);

	
}
