package nl.nicasso.ql.ast.nodes.literals;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.visitors.ExpressionVisitor;

public class IntegerLiteral extends Literal {

	private final Type type;
	private final Integer lit;

	public IntegerLiteral(Integer lit) {
		super(null);
		this.lit = lit;
		this.type = new IntegerType();
	}
	
	public IntegerLiteral(Integer lit, CodeLocation location) {
		super(location);
		this.lit = lit;
		this.type = new IntegerType(location);
	}

	@Override
	public Integer getValue() {
		return lit;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof IntegerLiteral)) {
			return false;
		}
		IntegerLiteral lit2 = (IntegerLiteral) ob;
		return lit.equals(lit2.getValue());
	}
	
	@Override
	public int hashCode(){
	    return lit.hashCode();
    }

}