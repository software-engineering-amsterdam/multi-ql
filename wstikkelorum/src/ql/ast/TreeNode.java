package ql.ast;

public class TreeNode {
	private final int lineNumber;

	public TreeNode(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}
}
