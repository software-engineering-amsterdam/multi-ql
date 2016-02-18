package eu.bankersen.kevin.ql.ast.form;

import java.util.ArrayList;

import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;

public class Block extends AbstractForm {

    private ArrayList<AbstractStatement> statements;

    public Block() {
	statements = new ArrayList<AbstractStatement>();
    }

    @Override
    public final void eval() {

	visible(true); // Inside a body meaning the above statement is true, show the questions.

	statements.forEach(statement -> statement.eval());
    }

    public final void checkType() {

	statements.forEach(statement -> statement.checkType());

	visible(false); // We can use the visibility modifier to determine depth.
    }

    public final void show() {
	visible(true);
    }

    public final void hide() {
	visible(false);
    }

    public final void visible(final Boolean vis) {
	statements.forEach(statement -> statement.visible(vis));
    }

    @Override
    public final String toString() {

	StringBuilder sb = new StringBuilder();
	
	statements.forEach(statement -> sb.append(statement.toString()));
	
	return sb.toString();
    }

    public final void add(final AbstractStatement statement) {
	statements.add(statement);
    }

    public final ArrayList<AbstractStatement> getStatements() {
	return statements;
    }
}
