package org.uva.sea.ql.type_checker;

import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.type.Type;

public class IdentifierData {

		private final Type type;
		private final Identifier identifier;
		
		public IdentifierData(Type type, Identifier identifier) {
			this.type = type;
			this.identifier = identifier;
		}
		
		
		public Type getType() {
			return this.type;
		}
		
		public Identifier getId() {
			return this.identifier;
		}
}
