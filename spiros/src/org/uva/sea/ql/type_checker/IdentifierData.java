package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.type.Type;

public class IdentifierData {

		private final Type type;
		private final String label;
		private final List<String> dependencies;
		
		public IdentifierData(Type type, String label) {
			this.type = type;
			this.label = label;
			this.dependencies = new ArrayList<String>();
		}
		
		
		public Type getType() {
			return this.type;
		}
		
		public String getLabel() {
			return this.label;
		}


		public List<String> getDependencies() {
			return dependencies;
		}
		
		public void setDependencies(String dependency) {
			this.dependencies.add(dependency);
		}
}
