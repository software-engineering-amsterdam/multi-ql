package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;

public interface TypeVisitor<T, U> {

	public T visit(BooleanType type, U context);
	public T visit(MoneyType type, U context);
	public T visit(StringType type, U context);
	public T visit(IntegerType type, U context);

}
