package sc.qls.ast;

public class QLSASTNode {

	private QLSASTSourceInfo sourceInfo = new QLSASTSourceInfo();

	public QLSASTNode() {

	}

	public void setSourceInfo(QLSASTSourceInfo info) {
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
