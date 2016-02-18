package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTNode;
import uva.ql.ast.ASTNumber;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;
import uva.ql.interfaces.IASTNodeVisitor;

public class DuplicateQuestions implements IASTNodeVisitor {

	private Map<String, Integer> dupQuestions = new HashMap<String, Integer>(0);
	
	@Override
	public void visitVar(ASTVariable variable) {
		
		if ( dupQuestions.containsKey(variable.getName()) ) {
			
			int counter = dupQuestions.get(variable.getName());
			dupQuestions.put(variable.getName(), counter+1);
		}
		else {
			
			dupQuestions.put(variable.getName(), 1);
		}
	}
	
	public Map<String, Integer> getDuplicates() {
		
		Map<String, Integer> dups = new HashMap<String, Integer>(0);
		Iterator<Entry<String, Integer>> it = dupQuestions.entrySet().iterator();
		
		while (it.hasNext()) {
			
			Entry<String, Integer> record = it.next();
			
			if (record.getValue() > 1) {
				
				dups.put(record.getKey(), record.getValue());
			}
		}
		
		return dups;
	}
	
	@Override
	public void visitForm(ASTForm form) {

		if (form.size() > 0) {
			form.get(0).accept(this);
		}
	}

	@Override
	public void visitBlock(ASTBlock block) {

		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitIfStmnt(ASTIfStatement ifStatement) {
		
		for(int i=0; i<ifStatement.size(); i++) {
			ifStatement.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion(ASTQuestion question) {

		for(int i=0; i<question.size(); i++) {
			question.get(i).accept(this);
		}
	}
	
	@Override
	public void visitNode(ASTNode node) {}

	@Override
	public void visitExp(ASTExpression expression) {}

	@Override
	public void visitNum(ASTNumber number) {}

}
