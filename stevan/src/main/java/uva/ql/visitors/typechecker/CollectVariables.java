package uva.ql.visitors.typechecker;

import java.util.ArrayList;
import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.interfaces.INodeVisitor;

public class CollectVariables implements INodeVisitor {

	private final ArrayList<AVariable> store = new ArrayList<AVariable>();
	
	public ArrayList<AVariable> getStore() {
		
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
			
			ifStatement.get(i).accept( this );
		}
	}

	@Override
	public void visitQuestion( Question question ) {
		
		question.get(0).accept( this );
	}
	
	@Override
	public <T> void visitExp( T expression ) {}

	@Override
	public void visitVar( AVariable variable ) {
		
		store.add( variable );
	}
	
	@Override
	public void visitNum( ANumber number ) {}
}
