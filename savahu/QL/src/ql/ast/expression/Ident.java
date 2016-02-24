package ql.ast.expression;

public class Ident extends Expr {

    private final String _Name;

    public Ident(String name) {
        this._Name = name;
    }

    public String getName() {
        return _Name;
    }
}
