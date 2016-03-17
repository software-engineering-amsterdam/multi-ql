package nl.nicasso.ql.semanticAnalysis.messageHandling.warnings;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;

public abstract class Warning extends Message {

	private final CodeLocation location;
	
	public Warning(CodeLocation location) {
		this.location = location;
		this.messageType = "Warning";
	}
	
	public CodeLocation getLocation() {
		return location;
	}
	
}