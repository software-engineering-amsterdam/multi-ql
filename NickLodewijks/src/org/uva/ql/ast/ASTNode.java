package org.uva.ql.ast;

public abstract class ASTNode {

	private ASTSourceInfo sourceInfo = new ASTSourceInfo();

	public ASTNode() {

	}

	public void setSourceInfo(ASTSourceInfo info) {
		sourceInfo = info;
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
