package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

import java.util.Iterator;
import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class If extends Stat{
    Expr cond;
    List<Stat> stms;

    public If (Expr cond, List<Stat> stms){
        this.cond = cond;
        this.stms = stms;
    }

    @Override
    public String toString() {
        String stmsStrs = "";
        for (Stat stat : stms) {
            stmsStrs += stat.toString() + ", ";
        }
        String stmsList= "[" + stmsStrs.substring(0,stmsStrs.length() - 2) + "]";
        return "If(" + cond.toString() + ", " + stmsList + ")";
    }
}
