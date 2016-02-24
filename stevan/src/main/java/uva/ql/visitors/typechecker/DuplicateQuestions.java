package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.INodeVisitor;

public class DuplicateQuestions implements INodeVisitor {

	private final Map<String, Integer> store = new HashMap<String, Integer>(0);
	
	public Map<String, Integer> getResult() {
		
		Map<String, Integer> dups = new HashMap<String, Integer>(0);
		Iterator<Entry<String, Integer>> it = store.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Entry<String, Integer> record = it.next();
			
			if (record.getValue() > 0) {
				
				dups.put(record.getKey(), record.getValue());
			}
		}
		
		return dups;
	}

	@Override
	public void visitForm(Form form) {

		if (form.size() > 0) {
			form.get(0).accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {

		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitIfStmnt(IfStatement ifStatement) {
		
		for(int i=0; i<ifStatement.size(); i++) {
			ifStatement.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion(Question question) {
		
		AVariable variable = (AVariable) question.get(0);
		
		if ( store.containsKey(variable.getName()) ) {
			
			int counter = store.get(variable.getName());
			store.put(variable.getName(), counter+1);
		}
		else {
			
			store.put(variable.getName(), 1);
		}
	}
	
	@Override
	public <T> void visitExp(T expression) {}

	@Override
	public void visitVar(AVariable variable) {}
	
	@Override
	public void visitNum(ANumber number) {}
}
