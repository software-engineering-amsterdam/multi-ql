package ast.form;

import ast.visitor.Visitable;
import ast.visitor.Visitor;

public class Form implements Visitable{
	private String id;
	private Body body;

	public Form(String id, Body result) {
		this.id = id;
		this.body = result;
	}
	
	public String getId(){
		return id;
	}
	
	public Body getBody(){
		return body;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
