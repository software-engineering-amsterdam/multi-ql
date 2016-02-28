package uva.ql.visitors.typechecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import uva.ql.ast.AExpression;
import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INodeVisitor;

public class ConditionsNotOfTypeBoolean implements INodeVisitor {

	private final HashMap<String, Integer> store = new HashMap<String, Integer>(0);
	private static final Set<Integer> CON_BOOL = new HashSet<Integer>(
			Arrays.asList(
					 IExpression.SML_THEN ,IExpression.GRT_THEN ,IExpression.SML_EQL
					,IExpression.GRT_EQL ,IExpression.NOT_EQL ,IExpression.EQL
					,IExpression.NOT_EXP ,IExpression.AND_EXP ,IExpression.OR_EXP) );
	
	public HashMap<String, Integer> getResult() {
		
		return store;
	}

	@Override
	public void visitForm( Form form ) {

		if( form.size() > 0 ) {
			
			form.get(0).accept( this );
		}
	}

	@Override
	public void visitBlock( Block block ) {

		for( int i=0; i<block.size(); i++ ) {
			
			block.get(i).accept( this );
		}
	}
	
	@Override
	public void visitIfStmnt( IfStatement ifStatement ) {
		
		for( int i=0; i<ifStatement.size(); i++ ) {
			
			ifStatement.getExpression().accept( this );
			ifStatement.get(i).accept( this );
		}
	}
	
	@Override
	public void visitQuestion( Question question ) {}

	@Override
	public <T> void visitExp( T expression ) {
		
		AExpression exp = (AExpression) expression;
		checkExprType( exp );
		
		if ( exp.getLeftNode() != null ) {
			
			exp.getLeftNode().accept( this );
			
		}
		
		if ( exp.getRightNode() != null ) {
			
			exp.getRightNode().accept( this );
		}
	}
	
	@Override
	public void visitVar( AVariable variable ) {}
	
	@Override
	public void visitNum( ANumber number ) {}

	private void checkExprType( AExpression exp ) {
		
		if ( !CON_BOOL.contains(exp.getExprType()) ) {
			
			String msg = "Error: Condition not of type Boolean, starting at line: " + exp.getLine() + ", column: " + exp.getColumn();
			store.put( msg, -1 );
		}
	}
}
