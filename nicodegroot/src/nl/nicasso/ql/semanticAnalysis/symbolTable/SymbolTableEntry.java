package nl.nicasso.ql.semanticAnalysis.symbolTable;

import nl.nicasso.ql.ast.nodes.types.Type;

public class SymbolTableEntry {

	private Type type;
	
	public SymbolTableEntry(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}