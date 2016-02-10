package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.expr.unary.Neg;
import org.uva.sea.ql.ast.expr.unary.Not;
import org.uva.sea.ql.ast.expr.unary.Pos;
import org.uva.sea.ql.ast.stat.If;
import org.uva.sea.ql.ast.stat.IfElse;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.stat.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 5-2-16.
 */
public class DeclVisitor extends CoreVisitor{

    @Override
    public List<? extends Node> visit(Add expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(And expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Div expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Eq expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(GEq expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(GT expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(LEq expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(LT expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Mul expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(NEq expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Or expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Sub expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Neg expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Not expr) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Node> visit(Pos expr) {
        return new ArrayList<>();
    }
}
