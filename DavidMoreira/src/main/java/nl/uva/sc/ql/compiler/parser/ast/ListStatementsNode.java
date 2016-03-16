package nl.uva.sc.ql.compiler.parser.ast;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sc.ql.compiler.typechecker.Visitor;

public class ListStatementsNode extends Node {

	private List<StatementNode> listStatements;
	
	public ListStatementsNode(){
		listStatements = new ArrayList<StatementNode>();
	}
	
	public void add(StatementNode statement){
		this.listStatements.add(statement);
	}
	
	public List<StatementNode> getListStatements(){
		return this.listStatements;
	}

	@Override
	public String getType() {
		return "None";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);	
	}

	@Override
	public void dump() {
		System.out.println(getClass());
		for (StatementNode s : listStatements){
			s.dump();
		}
	}
}
