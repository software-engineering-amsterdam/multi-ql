package org.uva.sea.ql.ast.tree.stat;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitable;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Stat extends Node implements StatVisitable {

    public Stat(Token token) {super(token);}


}
