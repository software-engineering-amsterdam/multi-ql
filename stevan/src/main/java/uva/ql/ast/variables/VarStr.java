package uva.ql.ast.variables;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Str;

public class VarStr extends Variable<String> {

	private Str type = new Str();
	private String value = "";
	
	public VarStr(Node parent, String name, int startLine, int startColumn) {
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
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String eval() {
		return this.getValue();
	}
}
