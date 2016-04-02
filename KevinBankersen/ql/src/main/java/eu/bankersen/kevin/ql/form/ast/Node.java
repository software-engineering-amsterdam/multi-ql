package eu.bankersen.kevin.ql.form.ast;

public abstract class Node {

	private final int line;

	public Node(int line) {
		this.line = line;
	}

	public int line() {
		return line;
	}

}
