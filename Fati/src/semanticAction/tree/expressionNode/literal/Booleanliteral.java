package semanticAction.tree.expressionNode.literal;

import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.BooleanQL_Type;
public class Booleanliteral extends AbsLiteral {
	

		private final Boolean booleanLiteral;

		public Booleanliteral (Boolean booleanLiteral) {
			this.booleanLiteral = booleanLiteral;
		}
		
		public Boolean getVariable(){
			return booleanLiteral;
		}
		
		@Override
		public String toString() {
			return booleanLiteral.toString();
		}
		
		@Override
		public <T> T accept(InterfaceVisitExpression<T> visitor) {
			return visitor.visit(this);
		}

		@Override
		public BooleanQL_Type getType() {
			return new BooleanQL_Type();
		}
	}


