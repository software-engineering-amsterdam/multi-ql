package uva.ql.ast;

import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

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
	public EnumType getType() {
		return EnumType.FORM;
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
}
