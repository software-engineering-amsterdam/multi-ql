package uva.ql.ast.variables;

import java.util.Observable;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;

public abstract class Variable<T> extends Expression<T> {

	private String name;
	
	public Variable(Node parent, String name, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.name = name;
	}
	
	public abstract T getValue();
	public abstract void setValue(T value);
	
	@Override
	public EnumType getEnumTypeEvaluation() {
		return null;
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
	
	@Override
	public boolean isInValid() {
		return !this.isValid();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLine(int line) {
		this.startLine = line;
	}
	
	public void setColumn(int col) {
		this.startColumn = col;
	}
	
	@Override
	public void update(Observable o, Object arg) {}
	
	
}
