package ql2.conflict;

import ql2.conflict.Conflict.Level;

public class VariableNotDeclared extends Conflict {

	@Override
	public Level getConflictLevel() {
		return Level.ERROR;
	}
}
