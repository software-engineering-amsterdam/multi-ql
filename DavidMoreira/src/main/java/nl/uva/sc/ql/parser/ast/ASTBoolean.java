package nl.uva.sc.ql.parser.ast;

public class ASTBoolean extends ASTNode {

	public ASTBoolean() {}
	
	public ASTBoolean(String value) {
		super(Boolean.valueOf(value));
	}
	
	public ASTBoolean(Object value) {
		super(value);
	}
	
	@Override
	public String getType() {
		return "Boolean";
	}
		
}
