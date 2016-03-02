package uva.ql.visitors.typechecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import uva.ql.ast.AExpression;
import uva.ql.ast.ANode;
import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;
import uva.ql.interfaces.IVariable;
import uva.ql.visitors.INodeVisitor;

public class OperandsToOperators implements INodeVisitor {

	private final HashMap<String, Integer> store = new HashMap<String, Integer>(0);
	private static final Set<Integer> OPER_OPER = new HashSet<Integer>(0);
	
	static {
		OPER_OPER.add(IExpression.MULTIPLY_EXP);
		OPER_OPER.add(IExpression.DIVIDE_EXP);
		OPER_OPER.add(IExpression.MINUS_EXP);
		OPER_OPER.add(IExpression.ADD_EXP);
		OPER_OPER.add(IVariable.BOOLEAN);
		OPER_OPER.add(IVariable.STRING);
		OPER_OPER.add(IVariable.DATE);
		OPER_OPER.add(IVariable.DECIMAL);
		OPER_OPER.add(IVariable.MONEY);
	}
	
	public HashMap<String, Integer> getResult() {
		
		return store;
	}

	@Override
	public void visitForm( Form form ) {

		if ( form.size() > 0 ) {
			
			form.get(0).accept(this);
		}
	}

	@Override
	public void visitBlock( Block block ) {

		for( int i=0; i<block.size(); i++ ) {
			
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitIfStmnt( IfStatement ifStatement ) {
		
		for( int i=0; i<ifStatement.size(); i++ ) {
			
			ifStatement.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion( Question question ) {

		if ( question.getExpression() != null ) {
			
			question.getExpression().accept(this);
		}
	}
	
	@Override
	public <T> void visitExp( T expression ) {
		
		AExpression exp = (AExpression) expression;
		checkExprType( exp );

		if ( exp.getLeftNode() != null ) {
			
			exp.getLeftNode().accept(this);
			
		}
		
		if ( exp.getRightNode() != null ) {
			
			exp.getRightNode().accept(this);
		}
	}

	@Override
	public void visitVar( AVariable var ) {
		
		if ( !OPER_OPER.contains(var.getVarType()) ) {
			
			writeErrorMsg( var );
		}
	}

	@Override
	public void visitNum( ANumber number ) {}

	private void checkExprType( AExpression exp ) {
		
		if ( !OPER_OPER.contains(exp.getExprType()) ) {
		
			writeErrorMsg( exp );
		}
	}

	private void writeErrorMsg( ANode node ) {
		
		String msg = "";
				
		if ( node.getNodeType() == INode.EXPRESSION ) {
			
			msg = String.format("Error: Expression not of type operand, starting at line: %s, column: %s", node.getLine(), node.getColumn());
		}
		else {
			
			msg = String.format("Error: Operands of invalid type to Operator, starting at line: %s, column: %s", node.getLine(), node.getColumn());
		}
		
		store.put( msg, -1 );
	}
}
