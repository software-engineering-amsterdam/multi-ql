package ql.issue.warning;

import ql.issue.Issue;

public abstract class Warning extends Issue{
	@Override
	public boolean isProblem() {
		return false;
	}

	@Override
	public boolean isWarning() {
		return true;
	}
}
