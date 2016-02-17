package org.uva.sea.ql.ast.TaxForm.interfaces;

import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;

public class QLNodeVisitorAdapter implements QLNodeVisitor {

	public QLNodeVisitorAdapter() {
	}

	@Override
	public void visit(Form form) {
		visit(form);

	}

	@Override
	public void visit(Block block) {
		visit(block);

	}

	@Override
	public void visit(IFblock statement) {
		visit(statement);

	}

	@Override
	public void visit(Question question) {
		visit(question);

	}

}
