package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Stat{

    public IfElse (Expr cond, List<Stat> stms, List<Stat> elseStms){
        super(cond, stms, elseStms);
    }

    @Override
    public String toString() {
        String ifStmsStrs = "";
        for (Stat stat : getStms()) {
            ifStmsStrs += stat.toString() + ", ";
        }

        String elseStmsStrs = "";
        for (Stat stat : this.getAltStms()) {
            elseStmsStrs += stat.toString() + ", ";
        }

        String ifStmsList= "[" + ifStmsStrs + "]";
        String elseStmsList= "[" + elseStmsStrs + "]";

        return "IfElse(" + getCond().toString() + ", "
                     + ifStmsList.substring(0,ifStmsList.length() - 2) + ", "
                     + elseStmsList.substring(0,elseStmsList.length() - 2) + ")";
    }

}
