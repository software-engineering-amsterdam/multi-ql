package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

import java.util.Iterator;
import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class If extends Stat{

    public If (Expr cond, List<Stat> stms){
        super(cond, stms);
    }

    @Override
    public String toString() {
        String stmsStrs = "";
        for (Stat stat : getStms()) {
            stmsStrs += stat.toString() + ", ";
        }
        String stmsList= "[" + stmsStrs.substring(0,stmsStrs.length() - 2) + "]";
        return "If(" + getCond().toString() + ", " + stmsList + ")";
    }
}
