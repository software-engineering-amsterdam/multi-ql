package org.uva.sea.ql.ast.expr.type;

public class Type {

	private String name;
	
	public Type(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean equals(Type type) {
		// TODO Auto-generated method stub
		return name.equals(type.getName());
	}

}
