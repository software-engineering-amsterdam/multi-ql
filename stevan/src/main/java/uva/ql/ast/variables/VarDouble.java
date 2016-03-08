package uva.ql.ast.variables;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Double;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class VarDouble extends Variable {

	private Type type = new Double();
	
	public VarDouble(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String typeToString() {
		return this.type.getType();
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitVarDouble(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}
}
