package eu.bankersen.kevin.ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import eu.bankersen.kevin.ql.ast.expr.SymbolTabel;
import eu.bankersen.kevin.ql.ast.stat.IFStat;

public class Block {

	private List<Question> questions;
	private List<IFStat> statements;

	public Block() {
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFStat>();
	}
	
	public Boolean checkType(SymbolTabel table){
		
		List<Boolean> check = new ArrayList<Boolean>();
		
		for(Question q: questions){
			check.add(q.checkType(table));
		}
		
		for(IFStat s: statements){
			check.add(s.checkType(table));
		}
		
		return !check.contains(new Boolean(false));
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb;

		sb = new StringBuilder();
		for(Question q: questions){
			sb.append(q.getText() + "\t");
			sb.append(q.getType() + "\n");
		}
		
		for(IFStat s: statements){
			sb.append(s.toString()+ "\n");
		}
		
		
		return sb.toString();
	}

	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFStat statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public List<IFStat> getStatements() {
		return statements;
	}
}
