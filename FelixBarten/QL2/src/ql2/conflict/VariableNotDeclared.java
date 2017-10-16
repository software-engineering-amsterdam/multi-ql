package ql2.conflict;

import ql2.conflict.Conflict.Level;

public class VariableNotDeclared extends Conflict {
	
	private String id;
	private Object val; 

	public VariableNotDeclared(String key) {
		this.id = key;
		this.errorMsg = String.format("Variable '%s' is not declared before use", id);
	}
	
	public VariableNotDeclared(String key, Object value) {
		this.id = key;
		this.val = value;
		this.errorMsg = String.format("Variable %s is not declared before use", id);
	}

	@Override
	public Level getConflictLevel() {
		return Level.SEVERE;
	}
}
