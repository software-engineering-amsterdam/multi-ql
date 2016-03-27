package ql.ast.expression;

import ql.ast.IVisitor;
import ql.ast.type.IntType;
import ql.ast.type.Type;

public class Add extends BinaryExpr {

    public Add(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this.getLhs().accept(visitor);
        this.getRhs().accept(visitor);
    }
}
