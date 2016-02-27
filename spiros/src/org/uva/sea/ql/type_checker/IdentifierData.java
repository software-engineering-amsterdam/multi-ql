package org.uva.sea.ql.type_checker;

import org.uva.sea.ql.ast.type.Type;

public class IdentifierData {

		private final Type type;
		private final String label;
		
		public IdentifierData(Type type, String label) {
			this.type = type;
			this.label = label;
		}
		
		
		public Type getType() {
			return this.type;
		}
		
		public String getLabel() {
			return this.label;
		}
}
