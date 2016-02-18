package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.expr.Variable;
import org.uva.sea.ql.ast.stat.*;

public class Form {
	private String name;
	private Block mainBlock;
	private Map<String, Variable> questionVariableMap;
	private Context context;

	public Form(String name, Block block) {
		this.name = name;
		this.mainBlock = block;
		questionVariableMap = new HashMap<String, Variable>();
		this.context = new Context();
	}

	public String getName() {
		return name;
	}
	
	public Variable getQuestionVariableMap(String key) {
		return questionVariableMap.get(key);
	}
	
	public Block getMainBlock() {
		return this.mainBlock;
	}
	
	private void initializeQVMap() {
		
	}
	
}
