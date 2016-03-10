package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;

public interface StructureVisitor<T, U> {

	public T visit(Form value, U context);
	public T visit(Block value, U context);
	
}
