package ast.model.expression.operation;

import java.util.HashMap;

public enum CompareOperation {
	EQUAL("=="),
	NOTEQUAL("!="),
	GREATER(">"),
	GREATEREQUEAL(">="),
	LESS("<"),
	LESSEQUAL("<=");
	
	private String sign;
	
	private static HashMap<String, CompareOperation> signOperationMap = new HashMap<>();
	
	private CompareOperation(String sign) {
		this.sign = sign;
	}
	
	static {
		signOperationMap.put(EQUAL.sign, EQUAL);
		signOperationMap.put(NOTEQUAL.sign, NOTEQUAL);
		signOperationMap.put(GREATER.sign, GREATER);
		signOperationMap.put(GREATEREQUEAL.sign, GREATEREQUEAL);
		signOperationMap.put(LESS.sign, LESS);
		signOperationMap.put(LESSEQUAL.sign, LESSEQUAL);
	}
	
	public static CompareOperation getOperation(String sign) {
		return signOperationMap.get(sign);
	}
}
