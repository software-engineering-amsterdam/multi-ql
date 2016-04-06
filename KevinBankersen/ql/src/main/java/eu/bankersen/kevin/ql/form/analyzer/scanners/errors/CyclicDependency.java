package eu.bankersen.kevin.ql.form.analyzer.scanners.errors;

import java.util.Set;

public class CyclicDependency extends ScannerError {

	public CyclicDependency(int line, String variable, Set<String> dependencySet) {
		super(line, String.format("The variable \"%s\" has a cyclic dependency with %s!", variable, dependencySet));
	}

}
