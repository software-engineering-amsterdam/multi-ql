package nl.uva.sc.ql.parser.ast;

public class ASTString extends ASTNode {

	public ASTString() {}
	
	public ASTString(String value) {
        // strip quotes
		value = value.substring(1, value.length() - 1).replace("\"\"", "\"");
		super.setValue(value);
	}
	
	public ASTString(Object value) {
		super(value);
	}
	
	@Override
	public String getType() {
		return "String";
	}
	
}
