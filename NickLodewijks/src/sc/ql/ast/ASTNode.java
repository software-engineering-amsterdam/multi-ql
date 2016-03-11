package sc.ql.ast;

public abstract class ASTNode {

	private SourceLocation sourceInfo = new SourceLocation();

	public ASTNode() {

	}

	public void setSourceInfo(SourceLocation info) {
		sourceInfo = info;
	}

	public final String getSourceLocation() {
		return sourceInfo.getSourceLocation();
	}

	public final String getSourceText() {
		return sourceInfo.getSourceText();
	}

	@Override
	public String toString() {
		return getSourceLocation() + " " + getSourceText();
	}
}
