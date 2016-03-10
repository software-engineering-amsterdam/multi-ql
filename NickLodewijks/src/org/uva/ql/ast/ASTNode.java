package org.uva.ql.ast;

public abstract class ASTNode {

	private final ASTSourceInfo sourceInfo;

	public ASTNode(ASTSourceInfo sourceInfo) {
		this.sourceInfo = (sourceInfo == null ? new ASTSourceInfo() : sourceInfo);
	}

	public final String getSourceLocation() {
		return sourceInfo.toString();
	}

	public final String getSourceText() {
		return sourceInfo.getText();
	}

	@Override
	public String toString() {
		return getSourceLocation() + " " + getSourceText();
	}
}
