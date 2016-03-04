package ql.issue;

public abstract class Issue {
	protected String errorMessage;
	public void print(){
		System.out.println(errorMessage);
	}
}
