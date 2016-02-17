package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.TaxForm.Question;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitorAdapter;

public class VariableChecker extends QLNodeVisitorAdapter {
	private Map<String, VarDeclaration> symbols = new HashMap<String, VarDeclaration>();
	public VariableChecker() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void visit(Question question) {
		// TODO Auto-generated method stub
		super.visit(question);
	}

}
