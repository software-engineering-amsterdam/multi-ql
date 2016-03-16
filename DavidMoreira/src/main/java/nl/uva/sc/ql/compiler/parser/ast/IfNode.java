package nl.uva.sc.ql.compiler.parser.ast;

import java.util.List;

import nl.uva.sc.ql.compiler.typechecker.Visitor;

public class IfNode extends StatementNode {

	private List<ConditionBlockNode> conditions;
	private BlockNode elseBlock;

	public IfNode(List<ConditionBlockNode> conditions, BlockNode elseBlock){
		this.conditions = conditions;
		this.elseBlock = elseBlock;
	}
	
	public BlockNode getElseBlock(){
		return this.elseBlock;
	}
	
	public List<ConditionBlockNode> getConditions(){
		return this.conditions;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String getType() {
		return "None";
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		for (ConditionBlockNode cbn : conditions){
			cbn.dump();
		}
		if (getElseBlock() != null){
			getElseBlock().dump();
		}
	}
}
