package org.uva.sea.ql.semantic;


import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class VariableChecker implements QLNodeVisitor {
	SymbolTable symTable;
	public VariableChecker(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	@Override
	public void visit(VarDeclaration node) {
		if (node == null) {
			System.err.println("Reference to undefined question " + node);
			return;
		}
		String variableName = node.getIdentifier().getName();
		if (symTable.contains(variableName)) {
			System.err.println("Duplicate variable. The variable '" + variableName + "' has already been declared");
			return;
		}
		symTable.add(variableName, node.getType().getType());
	}

	@Override
	public void visit(VarIdentifier varId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Add add) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTNODE node) {
		// TODO Auto-generated method stub
		
	}


}
