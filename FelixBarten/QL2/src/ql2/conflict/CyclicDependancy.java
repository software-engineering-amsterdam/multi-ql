package ql2.conflict;

import ql2.conflict.Conflict.Level;

public class CyclicDependancy extends Conflict {

	public CyclicDependancy() {

	}
	
	@Override
	public Level getConflictLevel() {
		return Level.CRITICAL;
	}
}
