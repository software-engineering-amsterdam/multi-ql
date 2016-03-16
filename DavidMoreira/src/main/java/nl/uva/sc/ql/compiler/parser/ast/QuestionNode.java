package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.typechecker.SymbolTable;
import nl.uva.sc.ql.compiler.typechecker.Visitor;

public class QuestionNode extends StatementNode {

	private String question;
	private String identifier;
	private String type;
	
	public QuestionNode(String question, String identifier, String type){
		this.question = question;
		this.identifier = identifier;
		this.type = type;
	}
	
	public String getQuestion(){
		return this.question;
	}

	public String getIdentifier(){
		return this.identifier;
	}
	
	public void addThisToSymbolTable(SymbolTable symbolTable){
		symbolTable.add(identifier, this);
		symbolTable.add(question, this);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public void dump() {
		System.out.println(this.getClass());
		System.out.println(getQuestion());
		System.out.println(getIdentifier());
		System.out.println(getType());
	}
}
