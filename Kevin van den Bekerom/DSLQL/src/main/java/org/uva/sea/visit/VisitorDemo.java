package org.uva.sea.visit;

public class VisitorDemo {
	
	
	public static void main(String [] args) throws Exception {
		IntLiteral ill = new IntLiteral("leftA", 0);
		IntLiteral ilr = new IntLiteral("rightA", 0);
		IntLiteral ill2 = new IntLiteral("leftB", 0);
		IntLiteral ilr2 = new IntLiteral("rightB", 0);
		ilr2.value = null;
		ilr2.type = ExprEnum.INTLITERAL;
		Expr add = new Add(ill, ilr);
		Expr add2 = new Add(ill2, ilr2);
	    Expr add3 = new Add(add, add2);
		Visitor printVisitor = new VisitorPrinter();
		add3.accept(printVisitor);
		
		System.out.println("-------Running the value visitor on a small tree ------------");
		System.out.println("Safe to compute the value of the Add node?? " + canEvaluate(add3));
	}
	
	public static boolean canEvaluate(Expr startNode) {
		ValueVisitor valueVisitor = new ValueVisitor();
		startNode.accept(valueVisitor);
		return valueVisitor.isSafe();
	}
}
