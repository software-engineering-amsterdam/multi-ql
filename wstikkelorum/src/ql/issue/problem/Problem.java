package ql.issue.problem;

import ql.issue.Issue;

public abstract class Problem extends Issue{
	@Override
	public boolean isProblem() {
		return false;
	}

	@Override
	public boolean isWarning() {
		return true;
	}
}
