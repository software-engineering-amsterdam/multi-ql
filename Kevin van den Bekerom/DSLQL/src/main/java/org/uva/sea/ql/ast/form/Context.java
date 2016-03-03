package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.type.Undefined;

import java.util.HashMap;
import java.util.Map;

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
		if (questionTypes.containsKey(questionID)) {
			return questionTypes.get(questionID);
		} else {
			return new Undefined();
		}
		
	}
	
	public Map<String, Type> getTypeMap() {
		return this.questionTypes;
	}
}
