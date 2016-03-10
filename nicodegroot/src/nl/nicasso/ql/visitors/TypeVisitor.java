package nl.nicasso.ql.visitors;

import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.StringType;

public interface TypeVisitor<T, U> {

	public T visit(BooleanType value, U context);
	public T visit(MoneyType value, U context);
	public T visit(StringType value, U context);
	public T visit(IntegerType value, U context);
	
}
