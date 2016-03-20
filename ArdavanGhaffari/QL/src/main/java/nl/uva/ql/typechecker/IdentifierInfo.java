package nl.uva.ql.typechecker;

import java.util.HashSet;
import java.util.Set;

import nl.uva.ql.ast.type.Type;

public class IdentifierInfo {
	private Set<String> dependencies = new HashSet<>();
	
	private Type type;
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public Set<String> getDependencies() {
		return dependencies;
	}
	
	public void updateDependencies(Set<String> dependencies) {
		/* TODO: Use this
		 * initialize your your dependencies either at line 9 or in the constructor
		assert dependencies != null;		
		this.dependencies.addAll(dependencies);*/
		this.dependencies.addAll(dependencies);
	}
	
}
