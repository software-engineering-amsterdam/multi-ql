package nl.uva.ql.ast;

import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.visitors.FormVisitor;

public class Form extends AbstractNode {
	private Identifier identifier;
	private Box box;
	
	public Form(Identifier identifier, Box box, int line) {
		super(line);
		this.identifier = identifier;
		this.box = box;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Box getBox() {
		return box;
	}
	
	public void accept(FormVisitor formVisitor){
		formVisitor.visit(this);
	}
}
