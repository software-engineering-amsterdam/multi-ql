package nl.nicasso.ql.ast.nodes.types;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.visitors.TypeVisitor;

public abstract class Type extends ASTNode {

	public Type() {

	}

	public Type(CodeLocation location) {
		super(location);
	}

	public abstract Value getDefaultValue();

	public abstract String getTypeName();

	public abstract boolean equals(Object ob);

	public abstract int hashCode();

	public abstract <T, U> T accept(TypeVisitor<T, U> visitor, U context);
}