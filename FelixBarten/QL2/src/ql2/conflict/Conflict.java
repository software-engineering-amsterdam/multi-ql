package ql2.conflict;

public abstract class Conflict {
	protected String errorMsg;
	public enum Level {
		CRITICAL (100), SEVERE (80), ERROR (50), WARNING (10);
		
		private final int weight;
		
		Level(int weight) {
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}
		
	};
	protected Level conflictLevel;
	
	public void logIssues() {
		System.out.println(String.format("Error level %s: %s", getConflictLevel(), errorMsg));
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public Level getConflictLevel() {
		return conflictLevel;
	}

	public void setConflictLevel(Level conflictLevel) {
		this.conflictLevel = conflictLevel;
	}
}
