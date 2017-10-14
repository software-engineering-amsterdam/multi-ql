package ql2.conflict;

public abstract class Conflict {
	private String errorMsg;
	
	void logIssues() {
		System.out.println(errorMsg);
	}
}
