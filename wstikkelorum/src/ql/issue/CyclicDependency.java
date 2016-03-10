package ql.issue;

public class CyclicDependency extends Issue {
	public CyclicDependency(String identifier1, String identifier2) {
		super.errorMessage = String.format(
				"Cyclic dependency between: %s and %s.", identifier1,
				identifier2);
	}

}
