package org.uva.sea.visit;

public class VisitorDemo {
	
	
	public static void main(String [] args) throws Exception {
		IntLiteral ill = new IntLiteral("leftA");
		IntLiteral ilr = new IntLiteral("rightA");
		IntLiteral ill2 = new IntLiteral("leftB");
		IntLiteral ilr2 = new IntLiteral("rightB");
		Expr add = new Add(ill, ilr);
		Expr add2 = new Add(ill2, ilr2);
		Expr add3 = new Add(add, add2);
		Visitor printVisitor = new VisitorPrinter();
		System.out.println(add3.lhs.lhs.name);
		add3.accept(printVisitor);
		
	}
}
