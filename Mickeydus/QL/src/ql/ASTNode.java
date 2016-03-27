package ql;

import typechecker.TypecheckInterface;



/**
 *
 * @author Dominique
 */
public abstract class ASTNode {
    public abstract <T> T accept (TypecheckInterface<T> visitor);
}
