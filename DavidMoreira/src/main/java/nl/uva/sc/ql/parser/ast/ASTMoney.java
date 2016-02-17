package nl.uva.sc.ql.parser.ast;

public class ASTMoney extends ASTNode {

	public ASTMoney() {}
	
	public ASTMoney(String value) {
    	Object value_aux;
		if (value.contains(".")){
			value_aux = Double.valueOf(value);
    	} else {
    		value_aux = Integer.valueOf(value);
    	}
		
		super.setValue(value_aux);
	}
	
	public ASTMoney(Object value) {
		super(value);
	}
	
	@Override
	public String getType() {
		return "Money";
	}
	
}
