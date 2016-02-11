package org.uva.sea.visit;

import org.uva.sea.ql.ast.LeftDFSVisitor;

public class VisitorDemo {
	
	
	public static void main(String [] args) throws Exception {
		IntLiteral ill = new IntLiteral("leftA", 0);
		IntLiteral ilr = new IntLiteral("rightA", 0);
		IntLiteral ill2 = new IntLiteral("leftB", 0);
		IntLiteral ilr2 = new IntLiteral("rightB", 0);
		Expr pos = new Pos(ill);
		pos.name = "pos1";
		ilr2.value = null;
		ilr2.type = ExprEnum.INTLITERAL;
		Expr add = new Add(pos, ilr);
		add.name = "add1";
		Expr add2 = new Add(ill2, ilr2);
		add2.name = "add2";
	    Expr add3 = new Add(add, add2);
	    add3.name = "add3";
		/*Visitor printVisitor = new VisitorPrinter();
		add3.accept(printVisitor);
		
		System.out.println("-------Running the value visitor on a small tree ------------");
		System.out.println("Safe to compute the value of the Add node?? " + canEvaluate(add3));
		
		System.out.println("-------Running the typechecker visitor on a small tree ------------");
		System.out.println("Are the types safe?:  " + typesSafe(add3));*/
	    Visitor v = new ConcreteVisitor();
	    Visitor v2 = new LeftDFSVisitor();
	    add3.accept(v);
	   // add3.accept(v2);
	}
/*	
	public static boolean canEvaluate(Expr startNode) {
		ValueVisitor valueVisitor = new ValueVisitor();
		startNode.accept(valueVisitor);
		return valueVisitor.isSafe();
	}
	
	public static boolean typesSafe(Expr startNode) {
		TypeCheckVisitor typeVisitor = new TypeCheckVisitor();
		startNode.accept(typeVisitor);
		return typeVisitor.typesSafe();
	}
	*/
}
