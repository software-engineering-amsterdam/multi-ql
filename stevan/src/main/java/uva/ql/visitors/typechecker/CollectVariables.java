package uva.ql.visitors.typechecker;

import java.util.ArrayList;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.visitors.INodeVisitor;

public class CollectVariables implements INodeVisitor {

	private final ArrayList<Variable> store = new ArrayList<Variable>();
	
	public ArrayList<Variable> getStore() {
		
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

		// For each is more readable
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
	public void visitQuestion( QuestionVanilla question ) {
		
		question.get(0).accept( this );
	}
	
	@Override
	public <T> void visitExp( T expression ) {}

	@Override
	public void visitVar( Variable variable ) {
		
		store.add( variable );
	}
	
	@Override
	public void visitNum( Values number ) {}
}
