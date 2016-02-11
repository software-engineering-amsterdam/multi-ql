package org.uva.sea.ql.ast.TaxForm.interfaces;

import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;

public interface QLPartVisitor {
	public void visit(Form form);
	public void visit(Block block);
	public void visit(IFblock statement);
	public void visit(Question question);
}
