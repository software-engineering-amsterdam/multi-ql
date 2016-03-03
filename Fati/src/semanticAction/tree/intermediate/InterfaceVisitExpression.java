package semanticAction.tree.intermediate;
import semanticAction.tree.expressionNode.calculation.*;
import semanticAction.tree.expressionNode.comparison.*;
import semanticAction.tree.expressionNode.literal.*;
import semanticAction.tree.expressionNode.logical.*;
import semanticAction.tree.expressionNode.unary.*;

public interface InterfaceVisitExpression<T> {
	
	//visit variable
	
	public T visit(Identifier e);
	public T visit(Booleanliteral e);
	public T visit(Stringliteral e);
	public T visit(Integerliteral e);
	
	//visit Logical
	
	public T visit(And  e);
	public T visit(OR   e);
	
	// visitor interface visit calculation operator
	
	public T visit(Add  e);
	public T visit(SUB  e);
	public T visit(Time e);
	public T visit(Division  e);
	
	// visit comparison
	
	public T visit(Equal       e);
	public T visit(GreaterThan  e);
	public T visit(LessEqual    e);
	public T visit(LessThan     e);
	public T visit(GreaterEqual e);
	public T visit(NotEqual     e);
	
/// interface visit unary operator
	
	public T visit(Plus e);
	public T visit(Minus e);
	public T visit(NOT e);

	
	
}   
