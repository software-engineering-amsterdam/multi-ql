package uva.ql.visitors.typechecker;

import java.util.ArrayList;
import java.util.HashMap;
import uva.ql.ast.AExpression;
import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IVariable;

public class CyclicDepenUndefQuestions implements INodeVisitor {

	private final HashMap<String, Integer> store = new HashMap<String, Integer>(0);
	private final ArrayList<String> varList = new ArrayList<String>(0);
	private ArrayList<AVariable> exisitingVars = new ArrayList<AVariable>(0);
	
	public CyclicDepenUndefQuestions( ArrayList<AVariable> store ) {
		
		exisitingVars = store;
	}
	
	public HashMap<String, Integer> getResult() {
		
		return store;
	}

	@Override
	public void visitForm( Form form ) {

		if ( form.size() > 0 ) {
			
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
			
			ifStatement.get(i).accept( this );
		}
		
		ifStatement.getExpression().accept( this );
	}

	@Override
	public void visitQuestion( Question question ) {
		
		if ( question.getExpression() != null ) {
			
			question.getExpression().accept( this );
		}
		else {
			
			AVariable var = (AVariable) question.get(0);
			varList.add( var.getName() );
		}
	}
	
	@Override
	public <T> void visitExp( T expression ) {
		AExpression exp = (AExpression) expression;

		if ( exp.getLeftNode() != null ) {
			
			exp.getLeftNode().accept( this );
			
		}
		if ( exp.getRightNode() != null ) {
			
			exp.getRightNode().accept( this );
		}
	}

	@Override
	public void visitVar( AVariable var ) {

		if ( var.getVarType() != IVariable.BOOLEAN && 
				!varList.contains( var.getName() ) ) {
			
			boolean undefQuestion = false;
			
			for( AVariable v : exisitingVars ) {

				if ( v.getName().equalsIgnoreCase( var.getName() ) ) {

					undefQuestion = false;
					break;
				}
				else {
					
					undefQuestion = true;
				}
			}
			
			String msg = "";
			
			if ( undefQuestion ) {
				
				msg = "Error: Undefined question, starting at line: " + var.getLine() + ", column: " + var.getColumn();
			}
			else {
				
				msg = "Error: Cyclic dependency, starting at line: " + var.getLine() + ", column: " + var.getColumn();
			}
			
			store.put( msg, -1 );
		}
	}
	
	@Override
	public void visitNum( ANumber number ) {}
}
