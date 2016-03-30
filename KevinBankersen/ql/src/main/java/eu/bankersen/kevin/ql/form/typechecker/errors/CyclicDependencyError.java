package eu.bankersen.kevin.ql.typechecker.errors;

import java.util.Set;

public class CyclicDependencyError extends TypeCheckError {

    public CyclicDependencyError(int line, String variable, Set<String> dependencySet) {
	super(line, String.format("The variable \"%s\" has a cyclic dependency with %s!", variable, dependencySet));
    }

    public CyclicDependencyError(int line, Set<String> variables, Set<String> dependencySet) {
	super(line, String.format("The expression \"if(%s)\" has a cyclic dependency with its own block %s!", variables,
		dependencySet));
    }

    public CyclicDependencyError(int line, Set<String> identifier, Set<String> ifBlock, Set<String> elseBlock) {
	super(line,
		String.format("The question \"if(%s)\" has a cyclic dependency between its if (%s) else (%s) block!",
			identifier, ifBlock, elseBlock));
    }

}
