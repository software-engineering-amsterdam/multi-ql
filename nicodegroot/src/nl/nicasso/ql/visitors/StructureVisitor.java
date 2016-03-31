package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;

public interface StructureVisitor<T, U> {

	public T visit(Form structure, U context);
	public T visit(Block structure, U context);

}
