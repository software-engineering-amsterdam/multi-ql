package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;

public interface StructureVisitor<T> {

	public T visit(Form value);
	public T visit(Block value);
	
}
