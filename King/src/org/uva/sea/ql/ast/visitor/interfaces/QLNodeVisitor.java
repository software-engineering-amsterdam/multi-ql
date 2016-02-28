package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
import org.uva.sea.ql.ast.expr.binary.Equal;
import org.uva.sea.ql.ast.expr.binary.GreaterOrEqual;
import org.uva.sea.ql.ast.expr.binary.GreaterThan;
import org.uva.sea.ql.ast.expr.binary.NotEqual;
import org.uva.sea.ql.ast.expr.binary.OR;
import org.uva.sea.ql.ast.expr.binary.SmallerOrEqual;
import org.uva.sea.ql.ast.expr.binary.SmallerThan;
import org.uva.sea.ql.ast.expr.literal.BooleanLiteral;
import org.uva.sea.ql.ast.expr.literal.IntegerLiteral;
import org.uva.sea.ql.ast.expr.literal.Literal;
import org.uva.sea.ql.ast.expr.literal.LiteralExpression;
import org.uva.sea.ql.ast.expr.literal.MoneyLiteral;
import org.uva.sea.ql.ast.expr.literal.StringLiteral;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;

public interface QLNodeVisitor {
	
	public Type visit(Form form);
	
	public Type visit(Block block);
	
	public Type visit(VarDeclaration varDeclaration);
	
	public Type visit(VarIdentifier varId);
	
	public Type visit(Add add);
	
	public Type visit(IFblock statement);
	
	public Type visit(Question question);
	
	public Type visit(AND and);

	public Type visit(Div div);

	public Type visit(Equal eq);

	public Type visit(GreaterOrEqual geq);

	public Type visit(GreaterThan gt);

	public Type visit(SmallerOrEqual leq);

	public Type visit(SmallerThan lt);

	public Type visit(Mul mul);

	public Type visit(NotEqual neq);

	public Type visit(Negative neg);

	public Type visit(NOT not);

	public Type visit(OR or);

	public Type visit(Positive pos);

	public Type visit(Sub sub);

	public Type visit(IntegerLiteral intLiteral);

	public Type visit(BooleanLiteral boolLiteral);

	public Type visit(StringLiteral stringLiteral);
	
	public Type visit(MoneyLiteral moneyLiteral);

	public Type visit(LiteralExpression literalExpression);
	
	public Type visit(Literal<?> literal);
	
	public Type visit(VarExpr varExpr);


}
