package nl.nicasso.ql.semanticAnalysis.symbolTable;

import nl.nicasso.ql.ast.nodes.types.Type;

public class SymbolTableEntry {

	private final Type type;

	public SymbolTableEntry(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}