package eu.bankersen.kevin.ql.ast.form;

public class Form {

	private String name;
	public Block body;

	public Form(String name, Block body) {
		this.name = name;
		this.body = body;
	}
	
	public Boolean checkType(){
		return body.checkType();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb;

		sb = new StringBuilder();
		sb.append("Form: " + name + "\n");
		sb.append(body);
		sb.append("\n");

		return sb.toString();
	}
}

