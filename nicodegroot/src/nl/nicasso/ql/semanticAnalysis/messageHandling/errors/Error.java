package nl.nicasso.ql.semanticAnalysis.messageHandling.errors;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;

public abstract class Error extends Message {
	
	protected final CodeLocation location;
	
	public Error(CodeLocation location) {
		this.location = location;
	}
	
	public CodeLocation getLocation() {
		return location;
	}

}
