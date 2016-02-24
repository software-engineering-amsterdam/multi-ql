package nl.nicasso.ql.ast;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.type.Type;

public interface Traversable {
	
	public Type accept(TypeCheckerVisitor visitor);
	
	public Literal accept(EvaluatorVisitor visitor);
		
	public void accept(Visitor visitor);
	
	public String toString();

}
