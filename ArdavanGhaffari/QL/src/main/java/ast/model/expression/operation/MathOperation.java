package ast.model.expression.operation;

import java.util.HashMap;

public enum MathOperation {
	MUL("*"),
	DIV("/"),
	PLUS("+"),
	MINUS("-");
	
	private String sign;
	
	private static HashMap<String, MathOperation> signOperationMap = new HashMap<>();
	
	private MathOperation(String operationSign) {
		this.sign = operationSign;
	}
	
	static {
		signOperationMap.put(MUL.sign, MUL);
		signOperationMap.put(DIV.sign, DIV);
		signOperationMap.put(PLUS.sign, PLUS);
		signOperationMap.put(MINUS.sign, MINUS);
	}
	
	public static MathOperation getOperation(String sign) {
		return signOperationMap.get(sign);
	}
}
