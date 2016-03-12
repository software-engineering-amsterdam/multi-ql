package uva.ql.ast;

import uva.ql.visitors.interfaces.INodeVisitor;
import uva.ql.visitors.interfaces.typechecker.IArithmeticOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

public class Form extends Block {

	private String name;
	
	public Form(String name, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitForm(this);
	}
	
	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitForm(this);
	}
}
