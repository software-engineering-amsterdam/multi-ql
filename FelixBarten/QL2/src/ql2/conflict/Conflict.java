package ql2.conflict;

public abstract class Conflict {
	protected String errorMsg;
	public enum Level {
		CRITICAL, SEVERE, ERROR, WARNING
	};
	protected Level conflictLevel;
	
	public void logIssues() {
		System.out.println(errorMsg);
	}

	public Level getConflictLevel() {
		return conflictLevel;
	}

	public void setConflictLevel(Level conflictLevel) {
		this.conflictLevel = conflictLevel;
	}
}
