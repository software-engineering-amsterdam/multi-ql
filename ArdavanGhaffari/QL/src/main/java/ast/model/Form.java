package ast.model;

public class Form extends AbstractNode {
	private String name;
	private Box box;
	
	public Form(String name, Box box) {
		this.name = name;
		this.box = box;
	}

	public String getName() {
		return name;
	}

	public Box getBox() {
		return box;
	}
	
}
