package org.uva.sea.visit;

public class ValueVisitor implements Visitor {
	private boolean safe = true;
	
	@Override
	public void visit(Expr expr) {
		if (expr.type != null) {
		switch (expr.type) {
			case INTLITERAL: if(expr.value == null) { safe = false; }  break;
			case STRINGLITERAL: if(expr.value == null)  { safe = false; } break;
			case BOOLEANLITERAL: if(expr.value == null)  { safe = false; } break;
			default: ; // skip
		}
		}
	}
	
	public boolean isSafe() {
		return this.safe;
	}
}
