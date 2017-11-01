package ql2;

import ql2.ast.*;
import ql2.ast.expression.*;
import ql2.ast.expression.arithmatic.*;
import ql2.ast.expression.logic.And;
import ql2.ast.expression.logic.Equal;
import ql2.ast.expression.logic.GreaterThan;
import ql2.ast.expression.logic.GreaterThanOrEqual;
import ql2.ast.expression.logic.LesserThan;
import ql2.ast.expression.logic.LesserThanOrEqual;
import ql2.ast.expression.logic.Not;
import ql2.ast.expression.logic.NotEqual;
import ql2.ast.expression.logic.Or;
import ql2.ast.literal.*;
import ql2.ast.statement.*;
import ql2.ast.type.*;

public interface Ql2VisitorInterface<T> {

	public T visit(ASTNode node);
	

	public T visit(Block node);
	public T visit(Form node);
	public T visit(Question node);
	public T visit(InputQuestion node);
	public T visit(CalculatedQuestion node);
	public T visit(Questionnaire node);
	// visit expressions
	public T visit(UnaryExpr node);
	public T visit(BinaryExpr node);

	public T visit(Positive node);
	public T visit(Negative node);
	public T visit(Not node);
	// logic
	public T visit(Or node);
	public T visit(And node);
	public T visit(Equal node);
	public T visit(NotEqual node);
	// rel. 	
	public T visit(GreaterThanOrEqual node); // greater than or eq. -> GTEq
	public T visit(GreaterThan node); // Greater than
	
	public T visit(LesserThanOrEqual node); // lesser than or Eq
	public T visit(LesserThan node);  // lesser than

	// arithmatic
	public T visit(Subtract node);
	public T visit(Multiply node);
	public T visit(Addition node);
	public T visit(Divide node);
	// other
	public T visit(IdentityExpr node);
	public T visit(LiteralExpr node);
	// visit statements
	public T visit(Statement node);
	public T visit(IfStatement node);
	public T visit(IfElseStatement node);
	// visit literals
	public T visit(Literal node);
	public T visit(BooleanLiteral node);
	public T visit(CurrencyLiteral node);	
	public T visit(IntegerLiteral node);
	public T visit(StringLiteral node);
	// visit not quite literals. exclude these from project?
	public T visit(QuestionType node);
	public T visit(BooleanType node);
	public T visit(CurrencyType node);	
	public T visit(IntegerType node);
	public T visit(StringType node);

	
}
