package uva.ql.ast.variables;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Date;

public class VarDate extends Variable<String> {

	private Date type = new Date();
	private String value; 
	
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
	public String getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(String date) {
		this.value = date;
	}

	@Override
	public String eval() {
		return this.value;
	}
}
