package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.ast.block.Block;


public interface FormVisitor {

	public void visitForm(Form form);
	public void visitBlock(Block block);
	
}