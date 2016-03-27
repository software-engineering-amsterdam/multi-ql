/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

import java.util.Collections;
import java.util.List;
import ql.ast.ASTNode;
import ql.ast.IVisitor;
import ql.ast.question.Question;
import ql.ast.statement.Statement;

/**
 *
 * @author sander
 */
public class Block extends ASTNode {

    private final List<Statement> _Statements;
    private final List<Question> _Questions;

    public Block(List<Statement> statements, List<Question> questions) {
        this._Statements = statements;
        this._Questions = questions;
    }

    public List<Statement> GetStatements() {
        return Collections.unmodifiableList(_Statements);
    }

    public List<Question> GetQuestions() {
        return Collections.unmodifiableList(_Questions);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this._Questions.stream().forEach((Question q) -> {
            q.accept(visitor);
        });
        this._Statements.stream().forEach((Statement s) -> {
            s.accept(visitor);
        });
    }

}
