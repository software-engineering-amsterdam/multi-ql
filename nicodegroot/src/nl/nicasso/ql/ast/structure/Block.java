package nl.nicasso.ql.ast.structure;

import java.util.List;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.ast.type.Type;

public class Block extends ASTNode implements Traversable {

	List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
}
