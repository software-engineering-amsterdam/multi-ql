package uva.ql.ast.variables;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Date;

public class VarDate extends Variable<Date> {

	private Date type = new Date();
	private Date value; 
	
	public VarDate(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}

	@Override
	public Date getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(Date date) {
		this.value = date;
	}

	@Override
	public Date eval() {
		// TODO Auto-generated method stub
		return null;
	}
}
