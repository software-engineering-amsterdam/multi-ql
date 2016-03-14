package uva.ql.ast;

import javax.swing.JPanel;

import uva.ql.gui.visitors.IActionListenerVisitor;
import uva.ql.gui.visitors.IGUIVisitor;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.INodeVisitor;

public interface INode {
	
	public void accept(INodeVisitor visitor);
	public void accept(IArithmeticOperatorVisitor visitor);
	public void accept(IBinaryOperatorVisitor visitor);
	public void accept(IUndefinedQuestionVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
	
	public void accept(IGUIVisitor visitor, JPanel panel);
	public void accept(IActionListenerVisitor visitor, JPanel panel);
}
