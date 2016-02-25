package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.IStatVisitable;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Stat extends Node implements IStatVisitable {

    public Stat(int line) {super(line);}


}
