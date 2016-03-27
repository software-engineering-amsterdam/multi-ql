package ql.ast.expression;

import ql.ast.ASTNode;
import ql.ast.type.Type;

public abstract class Expr extends ASTNode {

    public abstract Type getType();

    private Boolean isLiteral = false;

    public void setLiteral() {
        this.isLiteral = true;
    }

    public Boolean isLiteral() {
        return this.isLiteral;
    }

}
