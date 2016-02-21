package ast.model;

import ast.model.literal.Identifier;
import ast.visitor.FormVisitor;

public class Form extends AbstractNode {
	private Identifier identifier;
	private Box box;
	
	public Form(Identifier identifier, Box box) {
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
