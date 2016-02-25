package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.TaxForm.Block;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.ast.TaxForm.IFblock;
import org.uva.sea.ql.ast.TaxForm.Question;
import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.semantic.SymbolTable;

public interface QLNodeVisitor {
	public void visit(Form form);
	public void visit(Block block);
	public void visit(VarDeclaration varDeclaration);
	public void visit(VarIdentifier varId);
	public void visit(Add add);
	public void visit(IFblock statement);
	public void visit(Question question);
}
