package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Stat{

    LinkedHashMap<Expr, List<Stat>> stmsList;

    public IfElse (LinkedHashMap<Expr, List<Stat>> stmsList){
        this.stmsList = stmsList;
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public LinkedHashMap<Expr, List<Stat>> getStmsList() {
        return stmsList;
    }
}
