package nl.nicasso.ql.visitor;

import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.structure.Form;

public interface StructureVisitor<T> extends Visitor<T> {

	public T visit(Form value);
	public T visit(Block value);
	
}
