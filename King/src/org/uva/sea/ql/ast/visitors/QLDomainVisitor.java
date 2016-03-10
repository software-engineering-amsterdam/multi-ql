package org.uva.sea.ql.ast.visitors;

import org.uva.sea.ql.ast.domain.Block;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.ast.domain.IFblock;
import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.domain.ReadOnlyQuestion;

public interface QLDomainVisitor {
	
	public void visit(Form form);
	
	public void visit(Block block);
	
	public void visit(IFblock statement);
	
	public void visit(Question question);
	
	public void visit(ReadOnlyQuestion readOnlyQuestion);


}
