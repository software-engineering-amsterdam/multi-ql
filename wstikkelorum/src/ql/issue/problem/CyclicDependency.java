package ql.issue.problem;


public class CyclicDependency extends Problem {
	public CyclicDependency(String identifier1, String identifier2) {
		super.errorMessage = String.format(
				"Cyclic dependency between: %s and %s.", identifier1,
				identifier2);
	}
}
