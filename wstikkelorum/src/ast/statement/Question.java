package ast.statement;

import ast.Visitable;
import ast.Visitor;

public class Question implements Visitable{
	private String id;
	private String str;
	private String type;
	
	public Question(Object object, Object object2, Object object3) {
		this.id = (String)object;
		this.str = (String)object2;
		this.type = (String)object3;
	}
	
	public String getId() {
		return id;
	}

	public String getStr() {
		return str;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
