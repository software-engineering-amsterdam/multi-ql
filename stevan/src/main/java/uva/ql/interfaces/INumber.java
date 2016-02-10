package uva.ql.interfaces;

import uva.ql.ast.ASTVariable;

public interface INumber {

	public final static int INT 		= ASTVariable.INT;
	public final static int DOUBLE 		= ASTVariable.DOUBLE;
	
	public String getValue();
	public void setValue(String value);
	public int getType();
	public void setType(int type);
}
