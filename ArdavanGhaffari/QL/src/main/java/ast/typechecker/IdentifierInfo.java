package ast.typechecker;

import java.util.LinkedList;
import java.util.List;

import ast.model.type.Type;

public class IdentifierInfo {
	private List<String> dependencies;
	
	private Type type;
	
	public void addDependency(String dependency) {
		if (dependencies == null) {
			dependencies = new LinkedList<>();
		}
		dependencies.add(dependency);
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public List<String> getDependencies() {
		return dependencies;
	}
}
