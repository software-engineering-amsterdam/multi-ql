package nl.nicasso.ql.ast;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.type.Type;

public class ASTNode implements Traversable {

	@Override
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}	
	
}