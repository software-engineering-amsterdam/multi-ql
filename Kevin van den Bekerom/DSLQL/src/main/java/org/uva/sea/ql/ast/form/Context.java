package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.expr.Type;

public class Context {
	private Map<String, Type> questionTypes;
	
	public Context() {
		questionTypes = new HashMap<String, Type>();
	}
	
	public void putQuestionType(String key, Type value) {
		System.out.println("Sometin happend!");
		questionTypes.put(key, value);
	}
	
	public Type getTypeFromQuestion(String questionID) {
		return questionTypes.get(questionID);
	}
	
	public Map<String, Type> getTypeMap() {
		return this.questionTypes;
	}
}
