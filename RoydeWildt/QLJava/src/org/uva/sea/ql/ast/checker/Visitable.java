package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;

import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public interface Visitable {

    List<? extends Node> accept(Visitor visitor);

}
