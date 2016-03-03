package ast.typechecker;

import java.util.HashSet;
import java.util.Set;

import ast.model.type.Type;

public class IdentifierInfo {
	private Set<String> dependencies;
	
	private Type type;
	
	public void addDependency(String dependency) {
		if (dependencies == null) {
			dependencies = new HashSet<>();
		}
		dependencies.add(dependency);
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public Set<String> getDependencies() {
		return dependencies;
	}
	
	public void setDependencies(Set<String> dependencies) {
		this.dependencies = dependencies;
	}
}
