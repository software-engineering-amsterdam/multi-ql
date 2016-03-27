package ql.ast.expression;

import ql.ast.IVisitor;
import ql.ast.type.BoolType;
import ql.ast.type.IntType;
import ql.ast.type.StringType;
import ql.ast.type.Type;

public class Ident extends Expr {

    private final String _Name;
    private Type _Type;
    private Boolean isLiteral = false;

    public Ident(String name) {
        this._Name = name;
    }

    public String getName() {
        return _Name;
    }

    public void setIntType() {
        this._Type = new IntType();
    }

    public void setBoolType() {
        this._Type = new BoolType();
    }

    public void setStringType() {
        this._Type = new StringType();
    }

    public Boolean isCompatibleToBool() {
        if (this._Type == null) {
            return false;
        }
        return this._Type.isCompatibleToBoolean();
    }

    public Boolean isCompatibleToString() {
        if (this._Type == null) {
            return false;
        }
        return this._Type.isCompatibleToString();
    }

    public Boolean isCompatibleToInt() {
        if (this._Type == null) {
            return false;
        }
        return this._Type.isCompatibleToInteger();
    }

    @Override
    public void setLiteral() {
        this.isLiteral = true;
    }

    @Override
    public Boolean isLiteral() {
        return this.isLiteral;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type getType() {
        return this._Type;
    }
}
