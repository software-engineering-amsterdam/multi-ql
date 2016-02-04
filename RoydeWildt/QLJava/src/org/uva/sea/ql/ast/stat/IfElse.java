package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

import java.util.Iterator;
import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Stat{
    Expr cond;
    List<Stat> ifStms;
    List<Stat> elseStms;

    public IfElse (Expr cond, List<Stat> ifStms, List<Stat> elseStms){
        this.cond = cond;
        this.ifStms = ifStms;
        this.elseStms = elseStms;
    }

    @Override
    public String toString() {
        String ifStmsStrs = "";
        for (Stat stat : ifStms) {
            ifStmsStrs += stat.toString() + ", ";
        }

        String elseStmsStrs = "";
        for (Stat stat : elseStms) {
            elseStmsStrs += stat.toString() + ", ";
        }

        String ifStmsList= "[" + elseStmsStrs + "]";
        String elseStmsList= "[" + elseStmsStrs + "]";

        return "IfElse(" + cond.toString() + ", "
                     + ifStmsList.substring(0,ifStmsList.length() - 2) + ", "
                     + elseStmsList.substring(0,elseStmsList.length() - 2) + ")";
    }

}
