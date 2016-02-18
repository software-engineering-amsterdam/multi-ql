package org.uva.sea.ql.semantic;

import org.uva.sea.ql.ast.TaxForm.Form;

public class TypeChecker {

	public TypeChecker(Form form) {
		SymbolTable symTable = new SymbolTable();
		form.accept(new VariableChecker(symTable));
		
	}

}
