package ql.ast;

public abstract class ASTNode {

    public abstract void accept(IVisitor visitor);

    public abstract void visitChildren(IVisitor visitor);
}
