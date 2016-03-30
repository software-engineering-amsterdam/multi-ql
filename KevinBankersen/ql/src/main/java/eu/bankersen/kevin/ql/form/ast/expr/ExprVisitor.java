package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.expr.logic.And;
import eu.bankersen.kevin.ql.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.ast.expr.math.Add;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.ast.expr.math.Sub;

public interface ExprVisitor<T, U> {

    T visit(Sub o, U context);

    T visit(Add o, U context);

    T visit(Div o, U context);

    T visit(Mul o, U context);

    T visit(Pos o, U context);

    T visit(Neg o, U context);

    T visit(Or o, U context);

    T visit(And o, U context);

    T visit(Eq o, U context);

    T visit(GEq o, U context);

    T visit(GT o, U context);

    T visit(LEq o, U context);

    T visit(LT o, U context);

    T visit(NEq o, U context);

    T visit(Not o, U context);

    T visit(Literal o, U context);

    T visit(Identifier o, U context);

}
