package sc.qls.ast;

public class ASTNode {

	private ASTSourceInfo sourceInfo = new ASTSourceInfo();

	public ASTNode() {

	}

	public void setSourceInfo(ASTSourceInfo info) {
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
